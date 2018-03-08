package com.mvc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


 

public class AddressDialog extends JDialog implements ActionListener {
	//�����
	static AddressBook aBook = null;
	static AddressVO pvo = null;
	
	//AddressBook abook = null;
	/*String font = "-";
	Font jt_font = new Font(font, Font.BOLD,1);
	String ft = jt_font.toString();*/
	
	//�۾�ü
 	String ham = "���ʷ� ����";
 	String md = "MD�̼�ü";
 	String serif = "Serif";
 	
 	Font f_color = new Font(serif, Font.PLAIN,13);
  	Font button = new Font(ham, Font.BOLD,13);
  	
 	//����
 	Color darkYellow = new Color(218,165,32);
 	Color darkGreen = new Color(55, 107, 47);
  	Color distle = new Color(216,223,216);
  	Color rosyBrown = new Color(188,143,143);
  	Color black = new Color(0,0,0);
  	Color maroon = new Color(80,00,00);
  	Color fireBrick = new Color(178,22,22);
  	Color goldenRoad = new Color(184, 86, 11);
  
  	   //JPanel 	 panel = new JPanel();
  	   JButton   jbtn_confirm = new JButton("Ȯ��");
	   JButton   jbtn_exit = new JButton("�ݱ�");
	   JLabel     jlb_name = new JLabel("�̸�");
	   JLabel     jlb_list = new JLabel("��������");
	   JLabel     jlb_phone = new JLabel("��ȣ");
	   JLabel	  jlb_phone1 = new JLabel("-");
	   JLabel	  jlb_phone2 = new JLabel("-");
	   JLabel     jlb_address = new JLabel("�ּ�");
	   JLabel     jlb_mail = new JLabel("����");
	   JLabel     jlb_room = new JLabel("��������");
	   JLabel     jlb_comment = new JLabel("������");
	   JTextField jtf_name = new JTextField();
	   JTextField jtf_phone1 = new JTextField(3);
	   JTextField jtf_phone2 = new JTextField(4);
	   JTextField jtf_phone3 = new JTextField(4);	   
	   JTextField jtf_address = new JTextField();
	   JTextField jtf_mail = new JTextField();
	   String     roomList[]  = {"2�η�","4�η�","VIP��"};
	   String     List[]  = {"ȣ��", "����", "�Խ�Ʈ�Ͽ콺"};
	   JComboBox  jcb_list = new JComboBox(List);
	   JComboBox  jcb_room = new JComboBox(roomList);
	   JTextArea  jta_comment = new JTextArea(10,40);
	   JScrollPane jsp_comment = 
	                         new JScrollPane(jta_comment);
	   AddressVO avo = new AddressVO();
	   JOptionPane jop_addr = new JOptionPane();
		
		
		
		
	//������
		public AddressDialog(){
			//dialogDisplay();
		}

	//ȭ���� Ÿ��Ʋ�� ȭ���� Ȱ��ȭ ���θ� �����ϴ� �޼ҵ�
		/*public void set(String title, boolean isView){
			this.setTitle(title);
			this.setVisible(isView);
		}*/
	//ȭ��ó��
	/*	public void set(AddressBook aBook, String title, boolean isView){
			this.aBook=aBook;
			this.setTitle(title);
			this.setVisible(isView);
		}
*/
		public void dialogDisplay(AddressVO pvo, AddressBook aBook, String title, boolean isView){
			/*************�̺�Ʈ ó��*****************/
				jbtn_exit.addActionListener(this);
				jbtn_confirm.addActionListener(this);
			/****************��*******************/
				this.pvo = pvo;
				this.aBook=aBook;
				this.setTitle(title);
				this.setVisible(isView);
			this.setSize(700, 500);
		
			 this.setLayout(null);
		       jlb_list.setBounds(20, 20, 100, 20);
		       jcb_list.setBounds(120, 20, 350, 20);
		       jlb_name.setBounds(20, 45, 100, 20);
		       jtf_name.setBounds(120, 45, 350, 20);
		       jlb_address.setBounds(20, 70, 100, 20);
		       jtf_address.setBounds(120, 70, 350, 20);
		       jlb_phone.setBounds(20, 95, 100, 20);
		       jtf_phone1.setBounds(120, 95, 72, 20);
		       jlb_phone1.setBounds(196, 95, 10, 20);
		       jtf_phone2.setBounds(208, 95, 73, 20);
		       jlb_phone2.setBounds(286, 95, 10, 20);
		       jtf_phone3.setBounds(297, 95, 73, 20);
		       jlb_mail.setBounds(20, 120, 100, 20);
		       jtf_mail.setBounds(120, 120, 350, 20);
		       jlb_room.setBounds(20, 145, 100, 20);
		       jcb_room.setBounds(120, 145, 350, 20);
		       jlb_comment.setBounds(20, 170, 100, 20);
		       jsp_comment.setBounds(120, 170, 350, 145);
		       jbtn_confirm.setBounds(120, 327, 70, 20);
		       jbtn_exit.setBounds(210, 327, 70, 20);
		       //this.add("North",panel);
		       this.add(jlb_list);
		       this.add(jcb_list);
		       this.add(jlb_name);
		       this.add(jtf_name);
		       this.add(jlb_address);
		       this.add(jtf_address);
		       this.add(jlb_phone);
		       this.add(jtf_phone1);
		       this.add(jlb_phone1);
		       this.add(jtf_phone2);
		       this.add(jlb_phone2);
		       this.add(jtf_phone3);
		       this.add(jlb_mail);
		       this.add(jtf_mail);
		       this.add(jlb_room);
		       this.add(jcb_room);
		       this.add(jlb_comment);
		       this.add(jsp_comment);
		       this.add(jbtn_confirm);
		       this.add(jbtn_exit);
		       
		       jtf_name.setDocument(new JTextFieldLimit(40));
		       jtf_address.setDocument(new JTextFieldLimit(30));
		       jtf_phone1.setDocument(new JTextFieldLimit(3));
		       jtf_phone2.setDocument(new JTextFieldLimit(4));
		       jtf_phone3.setDocument(new JTextFieldLimit(4));
		       jtf_mail.setDocument(new JTextFieldLimit(50));
		       jta_comment.setDocument(new JTextFieldLimit(430));
		       
		       this.setResizable(false);
		       
		     //���̾�α� ���󺯰�  
		     this.getContentPane().setBackground(Color.DARK_GRAY);
		     //���̾�α� �󺧻�����
		     jlb_list.setForeground(Color.white);
		     jlb_name.setForeground(Color.white);
		     jlb_address.setForeground(Color.white);
		     jlb_phone.setForeground(Color.white);
		     jlb_phone1.setForeground(Color.white);
		     jlb_phone2.setForeground(Color.white);
		     jlb_mail.setForeground(Color.white);
		     jlb_room.setForeground(Color.white);
		     jlb_comment.setForeground(Color.white);
		     //��ư����
		     jbtn_confirm.setFont(button);
			 jbtn_confirm.setBackground(rosyBrown);
			 jbtn_confirm.setForeground(maroon);
			 jbtn_exit.setFont(button);
			 jbtn_exit.setBackground(rosyBrown);
			 jbtn_exit.setForeground(maroon);
		}
		
		/*public void setValue(AddressVO avo){
			//�Է��� ���� ������ ���� - ��簪�� null�� ����
			//avo�� ���� ������� ���� ���
			//-->���� �Է��ϴ� ���� ��ƾ���
			//�Է��϶��� �Է¹޴� â�� ����д�.
			if(avo==null){
				setName("");
				setAddress("");
				setPhone1("");
				setPhone2("");
				setPhone3("");
				setMail("");
				setGender("��");
				setComment("");
			}
			//��ȸ, �����ÿ��� Value Object���� ���� ������ ����
			//avo�� ���� ����ִ� ���
			//-->����Ŭ �������� �о�� ���� ������ �����ϱ�
			else{
				setName(avo.getAddr_name());
				setAddress(avo.getAddr_address());
				setPhone(avo.getAddr_phone());
				setMail(avo.getAddr_mail());
				setGender("��");
				setComment(avo.getAddr_comments());
			}
		
		}*/
		
		
		
		//���÷��� ������ �����ϰų� �о���� getter/setter
		
		public String getName(){
			return jtf_name.getText();
		}
		public void setName(String name){
			jtf_name.setText(name);
		}
		public String getList(){
			if("ȣ��".equals(jcb_list.getSelectedItem())){
				return "ȣ��";
			}else if ("����".equals(jcb_list.getSelectedItem())){
					return "����";
			}else
				return "�Խ�Ʈ�Ͽ콺";
		}
		public void setList(String list){
			if("ȣ��".equals(list)){
				jcb_list.setSelectedItem("ȣ��");
			}else if("����".equals(list)){
				jcb_list.setSelectedItem("����"); 
			}else
				jcb_list.setSelectedItem("�Խ�Ʈ�Ͽ콺");
		}
		public String getAddress(){
			return jtf_address.getText();
		}
		public void setAddress(String address){
			jtf_address.setText(address);
		}
		public String getPhone(){
			return avo.getAddr_phone();
		}
		public void setPhone(String phone){
			String hp[] = phone.split("-");
			jtf_phone1.setText(hp[0]);
			jtf_phone2.setText(hp[1]);
			jtf_phone3.setText(hp[2]);
		}
		public String getPhone1(){
			return jtf_phone1.getText();
		}
		public void setPhone1(String phone1){
			jtf_phone1.setText(phone1);
		}
		public String getPhone2(){
			return jtf_phone2.getText();
		}
		public void setPhone2(String phone2){
			jtf_phone2.setText(phone2);
		}
		public String getPhone3(){
			return jtf_phone3.getText();
		}
		public void setPhone3(String phone3){
			jtf_phone3.setText(phone3);
		}
		public String getMail(){
			return jtf_mail.getText();
		}
		public void setMail(String mail){
			System.out.println(mail);
			jtf_mail.setText(mail);
		}
		public String getRoom(){
			if("2�η�".equals(jcb_room.getSelectedItem())){
				return "2�η�";
			}else if ("4�η�".equals(jcb_room.getSelectedItem())){
					return "4�η�";
			}else
				return "VIP��";
		}
		public void setRoom(String room){
			if("2�η�".equals(room)){
				jcb_room.setSelectedItem("2�η�");
			}else if("4�η�".equals(room)){
				jcb_room.setSelectedItem("4�η�"); 
			}else
				jcb_room.setSelectedItem("VIP��");
		}
		public String getComment(){
			return jta_comment.getText();
		}
		public void setComment(String comments){
			jta_comment.setText(comments);
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			if(obj==jbtn_confirm){
				//����ڰ� ȭ�鿡 �Է��� ���� ��Ƽ� ��Ʈ�� ������ send�޼ҵ忡 �Ķ���ͷ� �Ѱ���
				//AddressVO pvo = null;
				AddressController ac = new AddressController();
				jtf_name.setEditable(true);
				jtf_address.setEditable(true);
				jtf_phone1.setEditable(true);
				jtf_phone2.setEditable(true);
				jtf_phone3.setEditable(true);
				jtf_mail.setEditable(true);
				jta_comment.setEditable(true);
		
				/*jcb_list.setEditable(true);
				jcb_room.setEditable(true);*/
				
				if("���ҵ��".equals(this.getTitle())){
					if("".equals(jtf_name.getText()) || "".equals(jtf_phone1.getText()) || "".equals(jtf_phone2.getText()) || "".equals(jtf_phone3.getText()) ||"".equals(jtf_address.getText())){
						jop_addr.showMessageDialog(this, "�Է����ּ���");
						return;
					}
					pvo = new AddressVO();
					pvo.setCommand("insert");
					System.out.println(pvo.getCommand());
					pvo.setAddr_name(getName());
					pvo.setAddr_list(getList());
					pvo.setAddr_address(getAddress());
					pvo.setAddr_phone(getPhone1()+"-"+getPhone2()+"-"+getPhone3());
					pvo.setAddr_mail(getMail());
					pvo.setAddr_room(getRoom());
					pvo.setAddr_comments(getComment());
					AddressVO rvo = ac.send(pvo);
					}
				else if("����ȸ".equals(this.getTitle())){
					
				}else if("���Ҽ���".equals(this.getTitle())){
					//pvo=new AddressVO();
					ac=new AddressController();
					System.out.println("update : "+pvo);
					pvo.setCommand("update");
					pvo.setAddr_name(getName());
					pvo.setAddr_list(getList());
					pvo.setAddr_address(getAddress());
					pvo.setAddr_phone(getPhone1()+"-"+getPhone2()+"-"+getPhone3());
					pvo.setAddr_mail(getMail());
					pvo.setAddr_room(getRoom());
					pvo.setAddr_comments(getComment());
					AddressVO rvo =ac.send(pvo);
					//System.out.println("getAddr_num"+pvo.getAddr_no());
				}
				
				this.dispose(); //�ڽ�â ����(�ڿ�ȸ��)
				aBook.refreshData();
				//setValue(pvo);
				jtf_name.setText("");
				jcb_list.setSelectedItem("ȣ��");
				jtf_address.setText("");
				jtf_phone1.setText("");
				jtf_phone2.setText("");
				jtf_phone3.setText("");
				jtf_mail.setText("");
				jcb_room.setSelectedItem("2�η�");
				jta_comment.setText("");
			}
			else if(obj==jbtn_exit){
				this.setVisible(false);
				//setValue(pvo);
				jtf_name.setText("");
				jtf_address.setText("");
				jtf_phone1.setText("");
				jtf_phone2.setText("");
				jtf_phone3.setText("");
				jtf_mail.setText("");
				jcb_room.setSelectedItem("2�η�");
				jta_comment.setText("");
				
				jtf_name.setEditable(true);
				jtf_address.setEditable(true);
				jtf_phone1.setEditable(true);
				jtf_phone2.setEditable(true);
				jtf_phone3.setEditable(true);
				jtf_mail.setEditable(true);
				jta_comment.setEditable(true);
		
				jcb_list.setEditable(true);
				jcb_room.setEditable(true);
			}
		}
	}

class JTextFieldLimit extends PlainDocument {
	   private int limit;
	   private boolean toUppercase = false;
	    JTextFieldLimit(int limit) {
	      super();
	      this.limit = limit;
	   }
	    JTextFieldLimit(int limit, boolean upper) {
	      super();
	      this.limit = limit;
	      this.toUppercase = upper;
	   }
	 
	   public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	      if (str == null) {
	         return;
	      }
	      if ( (getLength() + str.length()) <= limit) {
	         if (toUppercase) {
	            str = str.toUpperCase();
	         }
	         super.insertString(offset, str, attr);
	      }
	   }
}
	

		
		
		
	

