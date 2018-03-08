package com.chatting.test;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.chatting.test.FriendList;


public class LoginView extends JFrame implements ActionListener{
	JLabel jlb_id = new JLabel("아이디");
	JLabel jlb_pw = new JLabel("비밀번호");
	JTextField jtf_id = new JTextField();
	JTextField jtf_pw = new JTextField();
	JButton jbtn_login = new JButton("로그인");
	JButton jbtn_join = new JButton("회원가입");
	JPanel jp_north = new JPanel();
	JPanel jp_button = new JPanel();
	ChatVO cvo = null;
	ChatVO cvo2 = null;
	JoinController jctrl = null;
	FriendList friendlist = new FriendList();
	private void initDisplay() {
		jp_north.add(jlb_id);
		jp_north.add(jtf_id);
		jp_north.add(jlb_pw);
		jp_north.add(jtf_pw);
		jp_button.add(jbtn_login);
		jp_button.add(jbtn_join);
		jlb_id.setBounds(20, 20, 100, 20);
		jtf_id.setBounds(120, 20, 150, 20);
		jlb_pw.setBounds(20, 60, 100, 20);
		jtf_pw.setBounds(120, 60, 150, 20);
		this.add(jlb_id);
		this.add(jtf_id);
		this.add(jlb_pw);
		this.add(jtf_pw);
		this.add(jp_north);
		this.add(jp_button,"South");
		this.setTitle("로그인");
		this.setSize(300, 200);
		this.setLocation(750, 400);
		this.setVisible(true);
		jbtn_join.addActionListener(this);
		jbtn_login.addActionListener(this);
	}
	public static void main(String[] args) {
		LoginView lV = new LoginView();
		lV.initDisplay();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		JoinView jview = new JoinView();
		if(obj==jbtn_login){
			if(jtf_id.getText().length()<=0){
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
				return;
			}
			else if(jtf_pw.getText().length()<=0){
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
				return;
			}
			else{
				cvo = new ChatVO();
				cvo.setCommand("select");
				cvo.setMem_id(jtf_id.getText());
				cvo.setMem_pw(jtf_pw.getText());
				jctrl= new JoinController();
				cvo2=jctrl.send(cvo);		
				if(cvo2!=null){
					JOptionPane.showMessageDialog(this, "로그인성공");
					this.dispose();
				//friendlist.initDisplay();
				try {
					friendlist = new FriendList("192.168.0.113", 8923, cvo.getMem_id());
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
				else{
					JOptionPane.showMessageDialog(null, "로그인실패.", "로그인 실패", 0);
				}
			}
		}		
		else if(obj==jbtn_join){
			jview.initDisplay();
		}

			
	}

}
