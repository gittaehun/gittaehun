package com.chatting.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class JoinView extends JFrame implements ActionListener{
	JFrame     jf_join     = new JFrame();//전체화면
	JPanel 	   jp_west    = new JPanel();//속지
	JPanel	   jp_button	= new JPanel();
	JMenuBar   jmb         = new JMenuBar();
	JMenu      jm_status   = new JMenu("메뉴");//메뉴아이콘
	JMenuItem  jmi_clear   = new JMenuItem("초기화");//모든 텍스트필드 빈칸
	JMenuItem  jmi_exit    = new JMenuItem("나가기");//회원가입창 종료
	JLabel jlb_id = new JLabel("아이디");//텍스트필드 왼쪽에 붙을 라벨
	JLabel jlb_pw = new JLabel("비밀번호");
	JLabel jlb_checkpw = new JLabel("비밀번호 확인");
	JLabel jlb_name = new JLabel("이름");
	JLabel jlb_tel = new JLabel("전화번호");
	JLabel jlb_gender = new JLabel("성별");
	JTextField jtf_id = new JTextField();//입력창
	JTextField jtf_pw = new JTextField();
	JTextField jtf_checkpw = new JTextField();
	JTextField jtf_name = new JTextField();
	String genderList[]   = {"남자","여자"};
	JComboBox jcb_gender = new JComboBox(genderList);//성별 
	JTextField jtf_tel = new JTextField();
	JButton jbtn_cancel = new JButton("취소");//취소버튼
	JButton jbtn_ok = new JButton("확인");//확인버튼
	JButton jbtn_check = new JButton("중복검사");//ID중복검사 버튼
	LoginView loginview = new LoginView();
	JoinController jCtrl = new JoinController();
	
public void initDisplay(){
	jf_join.setJMenuBar(jmb);
	jlb_id.setBounds(20, 20, 100, 20);
	jtf_id.setBounds(120, 20, 150, 20);
	jlb_pw.setBounds(20, 60, 100, 20);
	jtf_pw.setBounds(120, 60, 150, 20);
	jlb_checkpw.setBounds(20, 110, 100, 20);
	jtf_checkpw.setBounds(120, 110, 150, 20);
	jlb_name.setBounds(20, 150, 100, 20);
	jtf_name.setBounds(120, 150, 150, 20);
	jlb_gender.setBounds(20, 230, 100, 20);
	jcb_gender.setBounds(120, 230, 150, 20);
	jlb_tel.setBounds(20, 270, 100, 20);
	jtf_tel.setBounds(120, 270, 150, 20);
	jbtn_check.setBounds(280, 20, 100, 20);
	jm_status.add(jmi_clear);//메뉴에 초기화 추가
	jm_status.add(jmi_exit);//메뉴에 나가기 추가
	this.add(jlb_id);
	this.add(jtf_id);
	this.add(jbtn_check);
	this.add(jlb_pw);
	this.add(jtf_pw);
	this.add(jlb_checkpw);
	this.add(jtf_checkpw);
	this.add(jlb_name);
	this.add(jtf_name);
	this.add(jlb_gender);
	this.add(jcb_gender);
	this.add(jlb_tel);
	this.add(jtf_tel);
	jp_button.add(jbtn_ok);
	jp_button.add(jbtn_cancel);
	this.add(jp_west);
	this.add(jp_button,"South");
	this.setSize(400,500);
	this.setTitle("회원가입");
	this.setLocation(350, 200);
	this.setVisible(true);
	jbtn_cancel.addActionListener(this);
	jbtn_ok.addActionListener(this);
	jbtn_check.addActionListener(this);
}

public String getMem_Gender(){//남자면 1, 여자면 0
	if("남자".equals(jcb_gender.getSelectedItem())){
		return "1";
	}
	else return "0";
}
public void setMem_Gender(String mem_gender){
	if("1".equals(mem_gender)){
	}
	else jcb_gender.setSelectedItem("여자");		
}
@Override
public void actionPerformed(ActionEvent ae) {
	Object obj = ae.getSource();
	if(obj==jbtn_cancel){
		dispose();
	}else if(obj==jbtn_check){
		//사용자가 입력한 아이디
		String user_id = jtf_id.getText();

		//System.out.println("회원가입 완료");
		ChatVO cvo = new ChatVO();
		cvo.setCommand("idCheck");
		cvo.setMem_id(user_id);
		ChatVO cvo2 = jCtrl.send(cvo);		
		//JOptionPane.showMessageDialog(this, "사용할 수 없는 아이디입니다."+cvo2.getMem_id()+","+loginview);
		if("none".equals(cvo2.getMem_id())){//asdf, none
			JOptionPane.showMessageDialog(this, "사용할 수 있는 아이디입니다.");
		}else{
			JOptionPane.showMessageDialog(this, "사용할 수 없는 아이디입니다.");
			return;
		}
			
	}
	else if(obj==jbtn_ok){
		//입력건에 처리
		String user_id = jtf_id.getText();

		//System.out.println("회원가입 완료");
		ChatVO cvo = new ChatVO();
		cvo.setCommand("idCheck");
		cvo.setMem_id(user_id);
		ChatVO cvo1 = jCtrl.send(cvo);
		if(jtf_id.getText().length()<=0){
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
		}
		if(jtf_pw.getText().length()<=0||jtf_checkpw.getText().length()<=0){
			JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
		}
		else if(!jtf_pw.getText().equals(jtf_checkpw.getText())){
			JOptionPane.showMessageDialog(null, "비밀번호가 서로 일치하지 않습니다.");
		}
		else if(jtf_tel.getText().length()<=0){
			JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.");
		}else if(!"none".equals(cvo1.getMem_id())){
			JOptionPane.showMessageDialog(this, "아이디를 확인하세요.");
		}
		else{
			JoinController jCtrl = new JoinController();
			//System.out.println("회원가입 완료");
			ChatVO cvo2 = new ChatVO();
			cvo.setCommand("insert");
			cvo.setMem_id(jtf_id.getText());
			cvo.setMem_pw(jtf_pw.getText());
			cvo.setMem_name(jtf_name.getText());
			cvo.setMem_tel(jtf_tel.getText());
			cvo.setMem_gender((String) jcb_gender.getSelectedItem());
			ChatVO cvo3 = jCtrl.send(cvo);
			//this.dispose()호출하면 사용한 자원회수당함.
			JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
			jCtrl.refreshData();
			this.dispose();
			
		}
		}
	}
		
}


