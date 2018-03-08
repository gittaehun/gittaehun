package com.mvc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;



public class AddressBook extends JFrame implements ActionListener,KeyListener,FocusListener{
	//선언부
		//String path ="E://dev_cosmo//dev_java//src//com//images//";
		AddressDialog aDialog = new AddressDialog();
		//AddressVO pvo = null;
		AddressVO avos[] = null;
		AddressController ac = new AddressController();
		public static AddressBook aBook = new AddressBook();
		public static AddressVO pvo = new AddressVO();
		 boolean focusYn = false;
		 boolean focusNo = false;
		 	//글씨체
		 	String ham = "함초롬 돋움";
		 	String md = "MD이솝체";
		 	String serif = "Serif";
		 	//색상
		  Color darkYellow = new Color(218,165,32);
		  Color darkGreen = new Color(55, 107, 47);
		  Color distle = new Color(216,223,216);
		  Color rosyBrown = new Color(188,143,143);
		  Color black = new Color(0,0,0);
		  Color maroon = new Color(80,00,00);
		  Color fireBrick = new Color(178,22,22);
		  Color goldenRoad = new Color(184, 86, 11);
		  
		  Font f_color = new Font(serif, Font.PLAIN,13);
		  Font button = new Font(ham, Font.BOLD,13);
		  
		//JToolBar jtb_address = new JToolBar();
		JMenuBar jmb_address = new JMenuBar();
		JMenu 	 jm_address	= new JMenu("업소관리");
		JMenuItem jmi_add	= new JMenuItem("업소등록");
		JMenuItem jmi_mod	= new JMenuItem("업소수정");
		JMenuItem jmi_del	= new JMenuItem("업소삭제");
		//JMenuItem jmi_sel	= new JMenuItem("상세조회");
		JMenuItem jmi_search	= new JMenuItem("전체조회");
		JMenuItem jmi_exit	= new JMenuItem("나가기");
		JPanel jp_north = new JPanel();
		JPanel jp_center = new JPanel();
	    JPanel jp_south = new JPanel();
	    JButton jbtn_add = new JButton("업소등록");
	    JButton jbtn_mod = new JButton("업소수정");
	    JButton jbtn_del = new JButton("업소삭제");      
	    JButton jbtn_search = new JButton("업소조회");
	    JButton jbtn_sel = new JButton("상세조회");
	    JButton jbtn_exit = new JButton("닫기");
	    JComboBox jcb = new JComboBox();
	    JTextField jtf = new JTextField(20);
	    JTextField jtf1 = new JTextField(20);
	    String cols[]   = {"순번","업소종류", "이름", "주소", "번호", "메일", "객실정보", "상세정보"};
	    String data[][] = new String[0][9];
	    DefaultTableModel dtm_addr = new DefaultTableModel(data, cols);
	    JTable jt_addr = new JTable(dtm_addr);
	    JScrollPane jsp_addr = new JScrollPane(jt_addr);
	    JOptionPane jop_addr = new JOptionPane();
	    boolean isView = true;
	    
	//생성자
	    public AddressBook(){
	    	
	    	bookDisplay();
	    	refreshData2();
	    }
	  
	    
	//화면처리
		public void bookDisplay(){
			/*******************이벤트 처리시작********************/
			jbtn_add.addActionListener(this);
			jbtn_mod.addActionListener(this);
			jbtn_del.addActionListener(this);
			jbtn_sel.addActionListener(this);
			jbtn_search.addActionListener(this);
			jbtn_exit.addActionListener(this);
			
			
			jmi_add.addActionListener(this);
			jmi_mod.addActionListener(this);
			jmi_del.addActionListener(this);
			jmi_search.addActionListener(this);
			jmi_exit.addActionListener(this);
			
			jtf.addKeyListener(this); //엔터키처리를 위함
		    jtf.addFocusListener(this);

			/*******************이벤트 처리 끝*********************/
	   
		  
		  
		    
			jm_address.add(jmi_add);
			jm_address.add(jmi_mod);
			jm_address.add(jmi_del);
			jm_address.add(jmi_search);
			jm_address.add(jmi_exit);			
			jmb_address.add(jm_address);
			this.setJMenuBar(jmb_address);

	        jcb.addItem("숙박업소");
	        jcb.addItem("이름");
	        jcb.addItem("주소");
	        jcb.addItem("번호");
	        jcb.addItem("메일");
	        jcb.addItem("객실정보");
	        jcb.addItem("상세정보");
	        
  
	        jp_north.setLayout(new FlowLayout());
	        jp_north.add(jcb);
	        jp_north.add(jtf);
	        jp_north.add(jbtn_search);

	        jp_south.setLayout(new FlowLayout());
	        jp_south.add(jbtn_add);
	        jp_south.add(jbtn_mod);
	        jp_south.add(jbtn_del);
	        jp_south.add(jbtn_sel);
	        jp_south.add(jbtn_exit);
	        
	        this.add("North", jp_north);
	        this.add("Center", jp_center);
	        this.add("South", jp_south);
	        this.add("Center", jsp_addr);
			this.setSize(800, 550);
			this.setTitle("숙박업소 관리");
			jt_addr.getColumn("순번").setWidth(0);
		    jt_addr.getColumn("순번").setMinWidth(0);
		    jt_addr.getColumn("순번").setMaxWidth(0);
		    
		    jt_addr.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    //jt_addr.setSelectionMode();

		   jt_addr.setBackground(darkGreen);
		   jt_addr.setForeground(Color.WHITE);
		   jp_north.setBackground(Color.DARK_GRAY);
		   jp_south.setBackground(Color.DARK_GRAY);
		   
		   jt_addr.setFont(f_color);
		   jt_addr.getTableHeader().setBackground(Color.DARK_GRAY);
		   jt_addr.getTableHeader().setForeground(Color.white);
		   
		   jsp_addr.getViewport().setBackground(darkYellow);
		   
		   jbtn_add.setFont(button);
		   jbtn_add.setBackground(rosyBrown);
		   jbtn_add.setForeground(maroon);
		   jbtn_mod.setFont(button);
		   jbtn_mod.setBackground(rosyBrown);
		   jbtn_mod.setForeground(maroon);
		   jbtn_del.setFont(button);
		   jbtn_del.setBackground(rosyBrown);
		   jbtn_del.setForeground(maroon);
		   jbtn_sel.setFont(button);
		   jbtn_sel.setBackground(rosyBrown);
		   jbtn_sel.setForeground(maroon);
		   jbtn_search.setFont(button);
		   jbtn_search.setBackground(rosyBrown);
		   jbtn_search.setForeground(maroon);
		   jbtn_exit.setFont(button);
		   jbtn_exit.setBackground(rosyBrown);
		   jbtn_exit.setForeground(maroon);
		   
		   this.setResizable(false);
		   this.setVisible(true);
		  
		}

	//메인메소드
		public static void main(String[] args) {
			
			//aBook.bookDisplay();

			//aBook.setDefaultLookAndFeelDecorated(true);
	    
			/*for(int i=0;i<fonts.length;i++)
			System.out.println(fonts[i]);*/
		}
		
		public void refreshData(){
		      //만일 이전에 테이블에 조회된 결과가 있다면 일단 모두 삭제하자.
		      while(dtm_addr.getRowCount()>0){
		         dtm_addr.removeRow(0);
		      }
		      
		      AddressController 
		       aCtrl = new AddressController();
		      AddressVO vvos[]=null;
		   /* if("".equals(jtf.getText())){
		    	  AddressVO pvo = new AddressVO();
		    	  pvo.setCommand("selectall");
		    	  vvos=aCtrl.allSend(pvo);
		     }*/
		     if(!"".equals(jtf.getText())){
		    	  AddressVO pvo = new AddressVO();	
		    	  pvo.setCommand("select");
		    	  pvo.setJcb_text(jtf.getText());
		    	  pvo.setJcb_idx(jcb.getSelectedIndex());
		    	  System.out.println(jtf.getText());
		    	  vvos=aCtrl.allSend(pvo);
		    	  //System.out.println(vvos);
		      }
		    	  //Controller계층에서 넘겨받은 전체 데이터를 테이블에 출력하기
		       for(int i=0;i<vvos.length;i++){
					 Vector oneRow = new Vector();
					 oneRow.add(vvos[i].getAddr_no());
					 oneRow.addElement(vvos[i].getAddr_list());
		             oneRow.addElement(vvos[i].getAddr_name());
		             oneRow.addElement(vvos[i].getAddr_address());
		             oneRow.addElement(vvos[i].getAddr_phone());
		             oneRow.addElement(vvos[i].getAddr_mail());
		             oneRow.addElement(vvos[i].getAddr_room());
		             oneRow.addElement(vvos[i].getAddr_comments());
		            
		             dtm_addr.addRow(oneRow);
		            
				}
		   }
		
		public void refreshData2(){
			//만일 이전에 테이블에 조회된 결과가 있다면 일단 모두 삭제하자.
			while(dtm_addr.getRowCount()>0){
				dtm_addr.removeRow(0);
			}
			AddressController 
			aCtrl = new AddressController();
			List<AddressVO> addressList = new ArrayList<AddressVO>();
			if("".equals(jtf.getText())){
				AddressVO pvo = new AddressVO();
				pvo.setCommand("selectall");
				addressList=aCtrl.allsend2(pvo);
			}
			
			Iterator<AddressVO> iter = addressList.iterator();
			while(iter.hasNext()){
				AddressVO avo = iter.next();
				Vector<Object> oneRow = new Vector<Object>();
				oneRow.add(avo.getAddr_no());
				oneRow.addElement(avo.getAddr_list());
				oneRow.addElement(avo.getAddr_name());
				oneRow.addElement(avo.getAddr_address());
				oneRow.addElement(avo.getAddr_phone());
				oneRow.addElement(avo.getAddr_mail());
				oneRow.addElement(avo.getAddr_room());
				oneRow.addElement(avo.getAddr_comments());
				
				dtm_addr.addRow(oneRow);
			}
			
		}
		
		

		
		@Override
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			Vector<AddressVO> v = new Vector<AddressVO>();
			
			if(obj==jbtn_exit ||obj==jmi_exit){
				System.exit(0);
			}
			//검색 ?
			else if(obj==jbtn_search || obj==jmi_search){
				//dtm_addr.removeRow(ac.allSend(pvo).length);
			
				refreshData2();
				jtf.setText("");
				
			}
			
			//입력버튼 누른거야?
			else if(obj==jbtn_add || obj==jmi_add){
				aDialog.dialogDisplay(pvo, aBook, jbtn_add.getText(), isView);
			
				
			}
			
			//수정버튼 클릭했어?
			else if(obj==jbtn_mod || obj==jmi_mod){
				int table = jt_addr.getSelectedRow();

				if(table >= 0){
					pvo = new AddressVO();
					System.out.println("book : "+pvo);
					pvo.setAddr_no(dtm_addr.getValueAt(table, 0));
					aDialog.dialogDisplay(pvo, aBook,jbtn_mod.getText(), isView);
						
					//System.out.println(dtm_addr.getValueAt(table, 0));
						aDialog.setList(dtm_addr.getValueAt(table, 1).toString());
						aDialog.setName(dtm_addr.getValueAt(table, 2).toString());
						aDialog.setAddress(dtm_addr.getValueAt(table, 3).toString());
						aDialog.setPhone(dtm_addr.getValueAt(table, 4).toString());
						aDialog.setMail(dtm_addr.getValueAt(table, 5).toString());
						aDialog.setRoom(dtm_addr.getValueAt(table, 6).toString());
						aDialog.setComment(dtm_addr.getValueAt(table, 7).toString());
				
						}else{
					jop_addr.showMessageDialog(this, "선택해주세요");
				}
			}
			//삭제 ?
			else if(obj==jbtn_del || obj==jmi_del){
				//aDialog.dialogDisplay(jbtn_del.getText(), isView);
				int table = jt_addr.getSelectedRow();
				System.out.println(table);
				if(table >= 0){
					pvo = new AddressVO();
					pvo.setCommand("delete");
					int tb = jt_addr.getSelectedRow();
					//int table[] =jt_addr.getSelectedRows();
					System.out.println("table.length : "+tb);
					System.out.println("값 : "+dtm_addr.getValueAt(tb, 0));
					 pvo.setAddr_no(dtm_addr.getValueAt(tb, 0));
					  ac.send(pvo);
					  refreshData();
				}else{
					jop_addr.showMessageDialog(this, "선택해주세요");
				}
					  
			}
			//상세조회?
			else if(obj==jbtn_sel){
				
				int table = jt_addr.getSelectedRow();
				//System.out.println(table);
				if(table >= 0){
				pvo = new AddressVO();
				System.out.println(dtm_addr.getValueAt(table, 5));
					aDialog.dialogDisplay(pvo, aBook,jbtn_sel.getText(), isView);
					//System.out.println(dtm_addr.getValueAt(table, 1));
					aDialog.setList(dtm_addr.getValueAt(table, 1).toString());
					aDialog.setName(dtm_addr.getValueAt(table, 2).toString());
					aDialog.setAddress(dtm_addr.getValueAt(table, 3).toString());
					aDialog.setPhone(dtm_addr.getValueAt(table, 4).toString());
					aDialog.setMail(dtm_addr.getValueAt(table, 5).toString());
					aDialog.setRoom(dtm_addr.getValueAt(table, 6).toString());
					aDialog.setComment(dtm_addr.getValueAt(table, 7).toString());
					
					aDialog.jtf_name.setEditable(false);
					aDialog.jtf_address.setEditable(false);
					aDialog.jtf_phone1.setEditable(false);
					aDialog.jtf_phone2.setEditable(false);
					aDialog.jtf_phone3.setEditable(false);
					aDialog.jtf_mail.setEditable(false);
					aDialog.jta_comment.setEditable(false);
			
					/*//aDialog.jcb_list.set
					aDialog.jcb_room.setEditable(false);*/
					
					}else{
						jop_addr.showMessageDialog(this, "선택해주세요");
					}
				}

		}
		 @Override
		   public void focusGained(FocusEvent fe) {
			 //focusYn = fe.isTemporary();
		        focusYn = true; //포커스가 텍스트안으로 들어왔음
		   }

		   @Override
		   public void focusLost(FocusEvent fe) {
		       //focusNo = fe.isTemporary(); 
			   focusNo = false; //포커스가 텍스트안으로 들어왔음
		   }

		   @Override
		   public void keyPressed(KeyEvent e) {
		        if(focusYn==true){
		            if(e.getKeyCode() == KeyEvent.VK_ENTER){ //엔터키가 눌러졌으면
		               jbtn_search.doClick(); 
		            }
		        }
		           
		   }
		
		   @Override
		   public void keyReleased(KeyEvent e){}
		   @Override
		   public void keyTyped(KeyEvent e) {}

	}



/*			while(dtm_addr.getRowCount()>0){
dtm_addr.removeRow(0);
}

try {

pvo = new AddressVO();
pvo.setCommand("selectall");

AddressVO vvos[] = ac.allSend(pvo);	
for(int i=0;i<vvos.length;i++){
System.out.println(vvos[i]);//v.copyInto(dvos);로 인해 배열안에 Vector가 가지고 있는 주소번지 초기화
}

for(int i=0;i<vvos.length;i++){
Vector oneRow = new Vector();
oneRow.add(vvos[i].getAddr_no());
oneRow.addElement(vvos[i].getAddr_name());
oneRow.addElement(vvos[i].getAddr_address());
oneRow.addElement(vvos[i].getAddr_phone());
oneRow.addElement(vvos[i].getAddr_mail());
oneRow.addElement(vvos[i].getAddr_gender());
oneRow.addElement(vvos[i].getAddr_comments());

dtm_addr.addRow(oneRow);

}

}catch (Exception e) {
e.printStackTrace();
}	*/



/*jtb_address.add(jbtn_add);
jtb_address.add(jbtn_mod);
jtb_address.add(jbtn_del);
jtb_address.add(jbtn_search);
jtb_address.add(jbtn_exit);*/

