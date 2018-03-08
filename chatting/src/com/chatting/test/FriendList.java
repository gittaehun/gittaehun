package com.chatting.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;



public class FriendList extends JFrame implements ActionListener{
	
	JPanel jp_north 	= new JPanel();
	JPanel jp_north_n 	= new JPanel();
	JPanel jp_north_s 	= new JPanel();
	
	JPanel jp_south 	= new JPanel();
	
	JButton jbtn_person	= new JButton("1:1��ȭ");
	JButton jbtn_people	= new JButton("�����");
	JButton jbtn_chat	= new JButton("ä�ù�");
	JButton jbtn_exit	= new JButton("������");
	JButton jbtn_memo	= new JButton("�޸�");
	JButton jbtn_change	= new JButton("�г��� ����");
	
	JLabel jlb_title 	= new JLabel("����");
	
	String cols[] = {"����ģ��"};
	String data [][] = new String[0][1];
	DefaultTableModel dtm_person = new DefaultTableModel(data, cols);
	JTable jt_person = new JTable(dtm_person);
	JScrollPane jsp_person = new JScrollPane(jt_person
											, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
											, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	PeopleTalk peopleTalk = new PeopleTalk(this);
	PersonTalk personTalk = new PersonTalk(this);
	String ip = "";
	int port = 0;
	Socket mySocket = null;
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	String nickName;
	int i =0;
	
	String name=null;
	String roomTitle = null;
	String id =null;
	
	
	public FriendList(){
		
	}
	public FriendList(String ip, int port, String id) throws Exception{
		nickName = JOptionPane.showInputDialog("�����  ��ȭ���� �Է��ϼ���");
		this.id=id;
		initDisplay();
		this.ip=ip;
		this.port=port;
		//System.out.println(nickName);
		mySocket = new Socket(ip, port);
		try {
			oos = new ObjectOutputStream(mySocket.getOutputStream());
			ois = new ObjectInputStream(mySocket.getInputStream());
			
			oos.writeObject(Protocol.ROOM_IN+"#"+nickName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FriendListThread flt = new FriendListThread(this);
		flt.start();
		
	}
	
	public void initDisplay(){
		/***********�̺�Ʈ����***********/
		jbtn_person.addActionListener(this);
		jbtn_people.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jbtn_memo.addActionListener(this);
		jbtn_change.addActionListener(this);
		/***********�̺�Ʈ����***********/
		//jframe ����
		this.setLayout(new BorderLayout());//jframe ���̾ƿ� ����
		this.add("North", jp_north);//jp_north �г��� north�� ����
		this.add("Center", jsp_person);
		this.setTitle(nickName+"���� ����");//jframe Ÿ��Ʋ
		this.setSize(400, 800);//jframe ������
		this.setVisible(true);//jframe�� ������
		
		jp_north.setLayout(new BorderLayout());//jp_north ���̾ƿ� ����
		jp_north.add("Center", jp_north_n);//jp_north_n �г��� jp_north�� ���Ϳ���
		jp_north.add("South", jp_north_s);//jp_north_s �г��� jp_north�� �Ʒ�����
		
		jp_north_n.setLayout(new FlowLayout());
		jp_north_n.add(jlb_title);//���� ����
		jp_north_s.setLayout(new GridLayout(1,2));
		jp_north_s.add(jbtn_memo);
		jp_north_s.add(jbtn_change);
		
		this.add("South", jp_south);
		jp_south.setLayout(new GridLayout(2,2));
		jp_south.add(jbtn_person);
		jp_south.add(jbtn_people);
		jp_south.add(jbtn_chat);
		jp_south.add(jbtn_exit);
		
		

		jt_person.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
		
	
	/*public static void main(String[] args) throws Exception {
		new FriendList("127.0.0.1", 8923);
	}*/


	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		
		if(obj == jbtn_person){//1:1��ȭ ��ư Ŭ����
			personTalk = new PersonTalk(this);
			
			int row = jt_person.getSelectedRow();
			if(row>=0){
			name = (String) dtm_person.getValueAt(row, 0);
			if(name.equals(nickName)){
				JOptionPane.showMessageDialog(this, "�ڽ��� �ȵǿ�");
			}else{
				//PersonTalk personTalk = new PersonTalk(this);
				try {
					oos.writeObject(Protocol.ONE_IN+"#"+nickName+"#"+name);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				//PersonTalk personTalk = new PersonTalk(this);
				personTalk.initDisplay(nickName, name);
				
			}
			}else{
				JOptionPane.showMessageDialog(this, "�Ѹ��������ϼ���");
			}

		}else if(obj == jbtn_people){//����� ��ư Ŭ����
			//peopleList = new ArrayList<PeopleTalk>();
			//Object o_names[] = new Object[rows.length];
			//int cols[] = jt_person.getSelectedColumns();
			int row = jt_person.getSelectedRow();
			if(row>=0){
			String otherName = (String)dtm_person.getValueAt(row,0);
			if(otherName.equals(nickName)){
				JOptionPane.showMessageDialog(this, "�ڽ��� �ȵǿ�");
			}else{
			Vector<String> v_name = new Vector<String>();
			v_name.add(nickName);
				peopleTalk.dtm_person.addRow(v_name);
			v_name = new Vector<String>();	
				v_name.add(dtm_person.getValueAt(row, 0).toString());
				peopleTalk.dtm_person.addRow(v_name);
			
			
			//int rows[]=jt_person.getSelectedRows();
			roomTitle = JOptionPane.showInputDialog("�����  ������� �Է��ϼ���");
			try {
				oos.writeObject(Protocol.TALK_IN+"#"+roomTitle+"#"+this.nickName+"#"+otherName); 
				
			} catch (Exception e) {
				
				// TODO: handle exception
			}
			peopleTalk.initDisplay_peopleTalk(roomTitle, this.nickName);
			
		   }
		 }else{
			 JOptionPane.showMessageDialog(this, "�Ѹ��������ϼ���");
		 }
		}else if(obj == jbtn_chat){//ä�ù� Ŭ����
			
		}else if(obj == jbtn_exit){//������ Ŭ���ñ�
			this.exitChat();
		}else if(obj == jbtn_memo){//�޸� ������
			i++;
			new Memo(this);
		}else if(obj == jbtn_change){//��ȭ�� ���� ������
			String afterName = JOptionPane.showInputDialog("������ ��ȭ���� �Է��Ͻÿ�.");
			if(afterName == null || afterName.trim().length()<1){
				JOptionPane.showInputDialog(this, "������ ��ȭ���� �Է��ϼ���.", "INFO", JOptionPane.INFORMATION_MESSAGE);
				return;//actionPerformed
			}
			//������ �ϴ� �޼��� ������ �������.
			//400#�����ȭ��#�����ȭ��
			try {
				oos.writeObject(Protocol.CHANGE+"#"+nickName+"#"+afterName);
				//this.nickName=afterName;
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public void exitChat(){
   	 try{
     	   oos.writeObject(Protocol.ROOM_OUT+"#"+this.nickName+"#"+"500");
     	   }
     	   catch(Exception e){
     		   e.printStackTrace();
     	   }
   	 System.exit(0);
    }
    public void exitChat1(){
   	 try{
   		 oos.writeObject(Protocol.ROOM_OUT+"#"+this.nickName+"#"+"700");
   	 }
   	 catch(Exception e){
   		 e.printStackTrace();
   	 }
   	 System.exit(0);
    }

}


