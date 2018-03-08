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
	
	JButton jbtn_person	= new JButton("1:1대화");
	JButton jbtn_people	= new JButton("단톡방");
	JButton jbtn_chat	= new JButton("채팅방");
	JButton jbtn_exit	= new JButton("나가기");
	JButton jbtn_memo	= new JButton("메모");
	JButton jbtn_change	= new JButton("닉네임 변경");
	
	JLabel jlb_title 	= new JLabel("톡톡");
	
	String cols[] = {"접속친구"};
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
		nickName = JOptionPane.showInputDialog("사용할  대화명을 입력하세요");
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
		/***********이벤트시작***********/
		jbtn_person.addActionListener(this);
		jbtn_people.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jbtn_memo.addActionListener(this);
		jbtn_change.addActionListener(this);
		/***********이벤트끄읕***********/
		//jframe 설정
		this.setLayout(new BorderLayout());//jframe 레이아웃 설정
		this.add("North", jp_north);//jp_north 패널을 north에 붙임
		this.add("Center", jsp_person);
		this.setTitle(nickName+"님의 톡톡");//jframe 타이틀
		this.setSize(400, 800);//jframe 사이즈
		this.setVisible(true);//jframe를 보여줌
		
		jp_north.setLayout(new BorderLayout());//jp_north 레이아웃 설정
		jp_north.add("Center", jp_north_n);//jp_north_n 패널을 jp_north의 센터에둠
		jp_north.add("South", jp_north_s);//jp_north_s 패널을 jp_north의 아래에둠
		
		jp_north_n.setLayout(new FlowLayout());
		jp_north_n.add(jlb_title);//라벨을 붙임
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
		
		if(obj == jbtn_person){//1:1대화 버튼 클릭스
			personTalk = new PersonTalk(this);
			
			int row = jt_person.getSelectedRow();
			if(row>=0){
			name = (String) dtm_person.getValueAt(row, 0);
			if(name.equals(nickName)){
				JOptionPane.showMessageDialog(this, "자신은 안되요");
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
				JOptionPane.showMessageDialog(this, "한명을선택하세요");
			}

		}else if(obj == jbtn_people){//단톡방 버튼 클릭시
			//peopleList = new ArrayList<PeopleTalk>();
			//Object o_names[] = new Object[rows.length];
			//int cols[] = jt_person.getSelectedColumns();
			int row = jt_person.getSelectedRow();
			if(row>=0){
			String otherName = (String)dtm_person.getValueAt(row,0);
			if(otherName.equals(nickName)){
				JOptionPane.showMessageDialog(this, "자신은 안되요");
			}else{
			Vector<String> v_name = new Vector<String>();
			v_name.add(nickName);
				peopleTalk.dtm_person.addRow(v_name);
			v_name = new Vector<String>();	
				v_name.add(dtm_person.getValueAt(row, 0).toString());
				peopleTalk.dtm_person.addRow(v_name);
			
			
			//int rows[]=jt_person.getSelectedRows();
			roomTitle = JOptionPane.showInputDialog("사용할  단톡명을 입력하세요");
			try {
				oos.writeObject(Protocol.TALK_IN+"#"+roomTitle+"#"+this.nickName+"#"+otherName); 
				
			} catch (Exception e) {
				
				// TODO: handle exception
			}
			peopleTalk.initDisplay_peopleTalk(roomTitle, this.nickName);
			
		   }
		 }else{
			 JOptionPane.showMessageDialog(this, "한명을선택하세요");
		 }
		}else if(obj == jbtn_chat){//채팅방 클릭시
			
		}else if(obj == jbtn_exit){//나가기 클릭시김
			this.exitChat();
		}else if(obj == jbtn_memo){//메모 누를시
			i++;
			new Memo(this);
		}else if(obj == jbtn_change){//대화명 변경 누를시
			String afterName = JOptionPane.showInputDialog("변경할 대화명을 입력하시오.");
			if(afterName == null || afterName.trim().length()<1){
				JOptionPane.showInputDialog(this, "변경할 대화명을 입력하세요.", "INFO", JOptionPane.INFORMATION_MESSAGE);
				return;//actionPerformed
			}
			//보내야 하는 메세지 내용을 적어보세요.
			//400#현재대화명#변경대화명
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


