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
	//선언부
	static AddressBook aBook = null;
	static AddressVO pvo = null;
	
	//AddressBook abook = null;
	/*String font = "-";
	Font jt_font = new Font(font, Font.BOLD,1);
	String ft = jt_font.toString();*/
	
	//글씨체
 	String ham = "함초롬 돋움";
 	String md = "MD이솝체";
 	String serif = "Serif";
 	
 	Font f_color = new Font(serif, Font.PLAIN,13);
  	Font button = new Font(ham, Font.BOLD,13);
  	
 	//색상
 	Color darkYellow = new Color(218,165,32);
 	Color darkGreen = new Color(55, 107, 47);
  	Color distle = new Color(216,223,216);
  	Color rosyBrown = new Color(188,143,143);
  	Color black = new Color(0,0,0);
  	Color maroon = new Color(80,00,00);
  	Color fireBrick = new Color(178,22,22);
  	Color goldenRoad = new Color(184, 86, 11);
  
  	   //JPanel 	 panel = new JPanel();
  	   JButton   jbtn_confirm = new JButton("확인");
	   JButton   jbtn_exit = new JButton("닫기");
	   JLabel     jlb_name = new JLabel("이름");
	   JLabel     jlb_list = new JLabel("업소종류");
	   JLabel     jlb_phone = new JLabel("번호");
	   JLabel	  jlb_phone1 = new JLabel("-");
	   JLabel	  jlb_phone2 = new JLabel("-");
	   JLabel     jlb_address = new JLabel("주소");
	   JLabel     jlb_mail = new JLabel("메일");
	   JLabel     jlb_room = new JLabel("객실정보");
	   JLabel     jlb_comment = new JLabel("상세정보");
	   JTextField jtf_name = new JTextField();
	   JTextField jtf_phone1 = new JTextField(3);
	   JTextField jtf_phone2 = new JTextField(4);
	   JTextField jtf_phone3 = new JTextField(4);	   
	   JTextField jtf_address = new JTextField();
	   JTextField jtf_mail = new JTextField();
	   String     roomList[]  = {"2인룸","4인룸","VIP룸"};
	   String     List[]  = {"호텔", "모텔", "게스트하우스"};
	   JComboBox  jcb_list = new JComboBox(List);
	   JComboBox  jcb_room = new JComboBox(roomList);
	   JTextArea  jta_comment = new JTextArea(10,40);
	   JScrollPane jsp_comment = 
	                         new JScrollPane(jta_comment);
	   AddressVO avo = new AddressVO();
	   JOptionPane jop_addr = new JOptionPane();
		
		
		
		
	//생성자
		public AddressDialog(){
			//dialogDisplay();
		}

	//화면의 타이틀과 화면의 활성화 여부를 셋팅하는 메소드
		/*public void set(String title, boolean isView){
			this.setTitle(title);
			this.setVisible(isView);
		}*/
	//화면처리
	/*	public void set(AddressBook aBook, String title, boolean isView){
			this.aBook=aBook;
			this.setTitle(title);
			this.setVisible(isView);
		}
*/
		public void dialogDisplay(AddressVO pvo, AddressBook aBook, String title, boolean isView){
			/*************이벤트 처뤼*****************/
				jbtn_exit.addActionListener(this);
				jbtn_confirm.addActionListener(this);
			/****************끝*******************/
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
		       
		     //다이얼로그 색상변경  
		     this.getContentPane().setBackground(Color.DARK_GRAY);
		     //다이얼로그 라벨색변경
		     jlb_list.setForeground(Color.white);
		     jlb_name.setForeground(Color.white);
		     jlb_address.setForeground(Color.white);
		     jlb_phone.setForeground(Color.white);
		     jlb_phone1.setForeground(Color.white);
		     jlb_phone2.setForeground(Color.white);
		     jlb_mail.setForeground(Color.white);
		     jlb_room.setForeground(Color.white);
		     jlb_comment.setForeground(Color.white);
		     //버튼변경
		     jbtn_confirm.setFont(button);
			 jbtn_confirm.setBackground(rosyBrown);
			 jbtn_confirm.setForeground(maroon);
			 jbtn_exit.setFont(button);
			 jbtn_exit.setBackground(rosyBrown);
			 jbtn_exit.setForeground(maroon);
		}
		
		/*public void setValue(AddressVO avo){
			//입력을 위한 윈도우 설정 - 모든값을 null로 셋팅
			//avo에 값이 들어있지 않은 경우
			//-->새로 입력하는 값을 담아야함
			//입력일때는 입력받는 창을 비워둔다.
			if(avo==null){
				setName("");
				setAddress("");
				setPhone1("");
				setPhone2("");
				setPhone3("");
				setMail("");
				setGender("남");
				setComment("");
			}
			//조회, 수정시에는 Value Object에서 받은 값으로 셋팅
			//avo에 값이 들어있는 경우
			//-->오라클 서버에서 읽어온 값을 가지고 있으니깐
			else{
				setName(avo.getAddr_name());
				setAddress(avo.getAddr_address());
				setPhone(avo.getAddr_phone());
				setMail(avo.getAddr_mail());
				setGender("남");
				setComment(avo.getAddr_comments());
			}
		
		}*/
		
		
		
		//각컬럼의 값들을 설정하거나 읽어오는 getter/setter
		
		public String getName(){
			return jtf_name.getText();
		}
		public void setName(String name){
			jtf_name.setText(name);
		}
		public String getList(){
			if("호텔".equals(jcb_list.getSelectedItem())){
				return "호텔";
			}else if ("모텔".equals(jcb_list.getSelectedItem())){
					return "모텔";
			}else
				return "게스트하우스";
		}
		public void setList(String list){
			if("호텔".equals(list)){
				jcb_list.setSelectedItem("호텔");
			}else if("모텔".equals(list)){
				jcb_list.setSelectedItem("모텔"); 
			}else
				jcb_list.setSelectedItem("게스트하우스");
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
			if("2인룸".equals(jcb_room.getSelectedItem())){
				return "2인룸";
			}else if ("4인룸".equals(jcb_room.getSelectedItem())){
					return "4인룸";
			}else
				return "VIP룸";
		}
		public void setRoom(String room){
			if("2인룸".equals(room)){
				jcb_room.setSelectedItem("2인룸");
			}else if("4인룸".equals(room)){
				jcb_room.setSelectedItem("4인룸"); 
			}else
				jcb_room.setSelectedItem("VIP룸");
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
				//사용자가 화면에 입력한 값을 담아서 컨트롤 계층의 send메소드에 파라미터로 넘겨줌
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
				
				if("업소등록".equals(this.getTitle())){
					if("".equals(jtf_name.getText()) || "".equals(jtf_phone1.getText()) || "".equals(jtf_phone2.getText()) || "".equals(jtf_phone3.getText()) ||"".equals(jtf_address.getText())){
						jop_addr.showMessageDialog(this, "입력해주세요");
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
				else if("상세조회".equals(this.getTitle())){
					
				}else if("업소수정".equals(this.getTitle())){
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
				
				this.dispose(); //자식창 닫음(자원회수)
				aBook.refreshData();
				//setValue(pvo);
				jtf_name.setText("");
				jcb_list.setSelectedItem("호텔");
				jtf_address.setText("");
				jtf_phone1.setText("");
				jtf_phone2.setText("");
				jtf_phone3.setText("");
				jtf_mail.setText("");
				jcb_room.setSelectedItem("2인룸");
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
				jcb_room.setSelectedItem("2인룸");
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
	

		
		
		
	

