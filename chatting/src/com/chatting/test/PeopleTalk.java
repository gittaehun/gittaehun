package com.chatting.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PeopleTalk extends JDialog implements ActionListener{
	JLabel jlb_title 	= new JLabel("단톡방");
	JPanel jp_north 	= new JPanel();
	JPanel jp_center	= new JPanel();
	JPanel jp_center_1 	= new JPanel();
	JPanel jp_center_2 	= new JPanel();
	JPanel jp_south		= new JPanel();
	JPanel jp_south_e	= new JPanel();
	JPanel jp_center_e 	= new JPanel();
	
	JTextField jtf_msg 	= new JTextField();
	JTextArea  jta_display = new JTextArea(30,40);
    JScrollPane jsp_display = new JScrollPane(jta_display
                                                    , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
                                                    , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                                                    );
    
	String cols[] = {"단톡 이용자"};
	String data [][] = new String[0][1];
	DefaultTableModel dtm_person = new DefaultTableModel(data, cols);
	JTable jt_person = new JTable(dtm_person);
	JScrollPane jsp_person = new JScrollPane(jt_person
											, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
											, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String cols1[] = {"실시간 접속자"};
	String data1[][] = new String[0][1];
	DefaultTableModel dtm_person1 = new DefaultTableModel(data1, cols1);
	JTable jt_person1 = new JTable(dtm_person1);
	JScrollPane jsp_person1 = new JScrollPane(jt_person1
			, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	JButton jbtn_confirm	= new JButton("전송");
	JButton jbtn_invite		= new JButton("초대");
	JButton jbtn_exit		= new JButton("닫기");
	
	
	FriendList fl = null;
	String roomTitle=null;
	String otherName=null;
	
	
	public PeopleTalk(){
		
	}
	public PeopleTalk(FriendList fl){
		this.fl=fl;
		//initDisplay_peopleTalk();
		//FriendListThread flt = new FriendListThread(this);
	}
	
	public void initDisplay_peopleTalk(String roomTitle, String nickName){
		this.roomTitle=roomTitle;
		jbtn_confirm.addActionListener(this);
		jbtn_invite.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jtf_msg.addActionListener(this);
		
		jp_north.setLayout(new FlowLayout());
		jp_north.add(jlb_title);
		
		jp_center.setLayout(new BorderLayout());
		//jp_center.add("East", jp_center_e);
		jp_center.add("West", jsp_display);
		//jp_center_1.add(jta_dp);
		//jp_center_e.setLayout(new BorderLayout());
		jp_center.add("Center", jp_center_e);
		
		jp_center_e.setLayout(new GridLayout(2,1));
		jp_center_e.add(jsp_person);
		jp_center_e.add(jsp_person1);
	
		jp_south.setLayout(new BorderLayout());
		jp_south.add(jtf_msg);
		jp_south.add("East", jp_south_e);
		jp_south_e.setLayout(new GridLayout(1,3));
		jp_south_e.add(jbtn_confirm);
		jp_south_e.add(jbtn_invite);
		jp_south_e.add(jbtn_exit);
		this.setTitle(roomTitle+"["+nickName+"]");
		this.setSize(800, 600);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.add("North", jp_north);
		this.add("Center", jp_center);
		this.add("South", jp_south);
		jta_display.setLineWrap(true);
		jta_display.setEditable(false);
		jtf_msg.requestFocus();
		
		jt_person.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jt_person1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==jbtn_confirm || (obj==jtf_msg)){
			try {
				String text = jtf_msg.getText();
				fl.oos.writeObject(Protocol.TALK+"#"+roomTitle+"#"+fl.nickName+"#"+text);
				
				jtf_msg.setText("");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(obj==jbtn_invite){
			int rows[] = jt_person1.getSelectedRows();

			
			if(rows.length>0){
			for(int i=0; i<rows.length;i++){
				otherName = (String)dtm_person1.getValueAt(rows[i], 0);
				
			}
			//JOptionPane.showMessageDialog(this, otherName);
			try {
				fl.oos.writeObject(Protocol.TALK_INVITE+"#"+roomTitle+"#"+fl.nickName+"#"+otherName);
				//룸이름과 초대한 사람 이름과 초대된 사람 이름 전송
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(rows.length==0){
			JOptionPane.showMessageDialog(this, "제발좀 한명 선택후 누르세요");
		}
		}else if(obj==jbtn_exit){
			jta_display.setText("");
			
			for(int i=0;i<dtm_person.getRowCount();i++){
				if(fl.nickName.equals(dtm_person.getValueAt(i, 0))){
					String outName = (String)dtm_person.getValueAt(i, 0);
					try {
						fl.oos.writeObject(Protocol.TALK_OUT+"#"+outName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			
			this.dispose();
		}
	}

}
