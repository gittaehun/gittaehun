package com.chatting.test;

import java.awt.FlowLayout;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FriendServer extends JFrame {
	//선언부		
			JLabel jlb_sv = new JLabel("Server");
			JPanel jp_north = 
					new JPanel();
			JPanel jp_center = new JPanel();
			JTextArea jta_log = new JTextArea(50,50);
			JTextField jtf_msg = new JTextField(30);
			JButton jbtn_send = new JButton("전송");
			JScrollPane jsp_log = new JScrollPane(jta_log
					                             , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
					                             , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			int port=0;
			ServerSocket 	   server = null;
			Socket 		 	   client = null;
			boolean 		   isStop = false;
			FriendServerThread fsThread = null;
			UserData ud = new UserData();
			RoomData rd = null;
			Vector<RoomData> roomList = new Vector<RoomData>();
	
	//생성자
		public FriendServer(){
			
		}
		
		public FriendServer(int port){
			ud.t_globalUser = new Vector<FriendServerThread>();
			
			this.port=port;
			initDisplay();
			try {
				server = new ServerSocket(port);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Echo Server Start!!");
			while(!isStop){
				try {
					System.out.println("Listen Status.....");
					client = server.accept();
					if(client!=null){
						jta_log.append("접속자 ip"+client.getInetAddress().getHostAddress()+"\n");
						fsThread =new FriendServerThread(this);
						fsThread.start();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		void initDisplay(){
			this.setTitle("서버 로그 출력창");
			//jta_log.append("Server Ready....\n");
			this.setSize(500, 400);
			this.setVisible(true);
			
			
			jp_north.setLayout(new FlowLayout());
			jp_north.add(jlb_sv);
			
			//jp_center.setLayout(new BorderLayout());
			//jp_center.add(jtf_msg);
			//jp_center.add("East", jbtn_send);
			
			this.add("Center", jsp_log);
			this.add("North",jp_north);
			this.add("South", jp_center);
		}
	public static void main(String[] args){
		new FriendServer(8923);
	}
}
