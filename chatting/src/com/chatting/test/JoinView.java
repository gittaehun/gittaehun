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
	JFrame     jf_join     = new JFrame();//��üȭ��
	JPanel 	   jp_west    = new JPanel();//����
	JPanel	   jp_button	= new JPanel();
	JMenuBar   jmb         = new JMenuBar();
	JMenu      jm_status   = new JMenu("�޴�");//�޴�������
	JMenuItem  jmi_clear   = new JMenuItem("�ʱ�ȭ");//��� �ؽ�Ʈ�ʵ� ��ĭ
	JMenuItem  jmi_exit    = new JMenuItem("������");//ȸ������â ����
	JLabel jlb_id = new JLabel("���̵�");//�ؽ�Ʈ�ʵ� ���ʿ� ���� ��
	JLabel jlb_pw = new JLabel("��й�ȣ");
	JLabel jlb_checkpw = new JLabel("��й�ȣ Ȯ��");
	JLabel jlb_name = new JLabel("�̸�");
	JLabel jlb_tel = new JLabel("��ȭ��ȣ");
	JLabel jlb_gender = new JLabel("����");
	JTextField jtf_id = new JTextField();//�Է�â
	JTextField jtf_pw = new JTextField();
	JTextField jtf_checkpw = new JTextField();
	JTextField jtf_name = new JTextField();
	String genderList[]   = {"����","����"};
	JComboBox jcb_gender = new JComboBox(genderList);//���� 
	JTextField jtf_tel = new JTextField();
	JButton jbtn_cancel = new JButton("���");//��ҹ�ư
	JButton jbtn_ok = new JButton("Ȯ��");//Ȯ�ι�ư
	JButton jbtn_check = new JButton("�ߺ��˻�");//ID�ߺ��˻� ��ư
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
	jm_status.add(jmi_clear);//�޴��� �ʱ�ȭ �߰�
	jm_status.add(jmi_exit);//�޴��� ������ �߰�
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
	this.setTitle("ȸ������");
	this.setLocation(350, 200);
	this.setVisible(true);
	jbtn_cancel.addActionListener(this);
	jbtn_ok.addActionListener(this);
	jbtn_check.addActionListener(this);
}

public String getMem_Gender(){//���ڸ� 1, ���ڸ� 0
	if("����".equals(jcb_gender.getSelectedItem())){
		return "1";
	}
	else return "0";
}
public void setMem_Gender(String mem_gender){
	if("1".equals(mem_gender)){
	}
	else jcb_gender.setSelectedItem("����");		
}
@Override
public void actionPerformed(ActionEvent ae) {
	Object obj = ae.getSource();
	if(obj==jbtn_cancel){
		dispose();
	}else if(obj==jbtn_check){
		//����ڰ� �Է��� ���̵�
		String user_id = jtf_id.getText();

		//System.out.println("ȸ������ �Ϸ�");
		ChatVO cvo = new ChatVO();
		cvo.setCommand("idCheck");
		cvo.setMem_id(user_id);
		ChatVO cvo2 = jCtrl.send(cvo);		
		//JOptionPane.showMessageDialog(this, "����� �� ���� ���̵��Դϴ�."+cvo2.getMem_id()+","+loginview);
		if("none".equals(cvo2.getMem_id())){//asdf, none
			JOptionPane.showMessageDialog(this, "����� �� �ִ� ���̵��Դϴ�.");
		}else{
			JOptionPane.showMessageDialog(this, "����� �� ���� ���̵��Դϴ�.");
			return;
		}
			
	}
	else if(obj==jbtn_ok){
		//�Է°ǿ� ó��
		String user_id = jtf_id.getText();

		//System.out.println("ȸ������ �Ϸ�");
		ChatVO cvo = new ChatVO();
		cvo.setCommand("idCheck");
		cvo.setMem_id(user_id);
		ChatVO cvo1 = jCtrl.send(cvo);
		if(jtf_id.getText().length()<=0){
			JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.");
		}
		if(jtf_pw.getText().length()<=0||jtf_checkpw.getText().length()<=0){
			JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.");
		}
		else if(!jtf_pw.getText().equals(jtf_checkpw.getText())){
			JOptionPane.showMessageDialog(null, "��й�ȣ�� ���� ��ġ���� �ʽ��ϴ�.");
		}
		else if(jtf_tel.getText().length()<=0){
			JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �Է����ּ���.");
		}else if(!"none".equals(cvo1.getMem_id())){
			JOptionPane.showMessageDialog(this, "���̵� Ȯ���ϼ���.");
		}
		else{
			JoinController jCtrl = new JoinController();
			//System.out.println("ȸ������ �Ϸ�");
			ChatVO cvo2 = new ChatVO();
			cvo.setCommand("insert");
			cvo.setMem_id(jtf_id.getText());
			cvo.setMem_pw(jtf_pw.getText());
			cvo.setMem_name(jtf_name.getText());
			cvo.setMem_tel(jtf_tel.getText());
			cvo.setMem_gender((String) jcb_gender.getSelectedItem());
			ChatVO cvo3 = jCtrl.send(cvo);
			//this.dispose()ȣ���ϸ� ����� �ڿ�ȸ������.
			JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
			jCtrl.refreshData();
			this.dispose();
			
		}
		}
	}
		
}


