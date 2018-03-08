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
	//�����
		//String path ="E://dev_cosmo//dev_java//src//com//images//";
		AddressDialog aDialog = new AddressDialog();
		//AddressVO pvo = null;
		AddressVO avos[] = null;
		AddressController ac = new AddressController();
		public static AddressBook aBook = new AddressBook();
		public static AddressVO pvo = new AddressVO();
		 boolean focusYn = false;
		 boolean focusNo = false;
		 	//�۾�ü
		 	String ham = "���ʷ� ����";
		 	String md = "MD�̼�ü";
		 	String serif = "Serif";
		 	//����
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
		JMenu 	 jm_address	= new JMenu("���Ұ���");
		JMenuItem jmi_add	= new JMenuItem("���ҵ��");
		JMenuItem jmi_mod	= new JMenuItem("���Ҽ���");
		JMenuItem jmi_del	= new JMenuItem("���һ���");
		//JMenuItem jmi_sel	= new JMenuItem("����ȸ");
		JMenuItem jmi_search	= new JMenuItem("��ü��ȸ");
		JMenuItem jmi_exit	= new JMenuItem("������");
		JPanel jp_north = new JPanel();
		JPanel jp_center = new JPanel();
	    JPanel jp_south = new JPanel();
	    JButton jbtn_add = new JButton("���ҵ��");
	    JButton jbtn_mod = new JButton("���Ҽ���");
	    JButton jbtn_del = new JButton("���һ���");      
	    JButton jbtn_search = new JButton("������ȸ");
	    JButton jbtn_sel = new JButton("����ȸ");
	    JButton jbtn_exit = new JButton("�ݱ�");
	    JComboBox jcb = new JComboBox();
	    JTextField jtf = new JTextField(20);
	    JTextField jtf1 = new JTextField(20);
	    String cols[]   = {"����","��������", "�̸�", "�ּ�", "��ȣ", "����", "��������", "������"};
	    String data[][] = new String[0][9];
	    DefaultTableModel dtm_addr = new DefaultTableModel(data, cols);
	    JTable jt_addr = new JTable(dtm_addr);
	    JScrollPane jsp_addr = new JScrollPane(jt_addr);
	    JOptionPane jop_addr = new JOptionPane();
	    boolean isView = true;
	    
	//������
	    public AddressBook(){
	    	
	    	bookDisplay();
	    	refreshData2();
	    }
	  
	    
	//ȭ��ó��
		public void bookDisplay(){
			/*******************�̺�Ʈ ó������********************/
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
			
			jtf.addKeyListener(this); //����Űó���� ����
		    jtf.addFocusListener(this);

			/*******************�̺�Ʈ ó�� ��*********************/
	   
		  
		  
		    
			jm_address.add(jmi_add);
			jm_address.add(jmi_mod);
			jm_address.add(jmi_del);
			jm_address.add(jmi_search);
			jm_address.add(jmi_exit);			
			jmb_address.add(jm_address);
			this.setJMenuBar(jmb_address);

	        jcb.addItem("���ھ���");
	        jcb.addItem("�̸�");
	        jcb.addItem("�ּ�");
	        jcb.addItem("��ȣ");
	        jcb.addItem("����");
	        jcb.addItem("��������");
	        jcb.addItem("������");
	        
  
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
			this.setTitle("���ھ��� ����");
			jt_addr.getColumn("����").setWidth(0);
		    jt_addr.getColumn("����").setMinWidth(0);
		    jt_addr.getColumn("����").setMaxWidth(0);
		    
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

	//���θ޼ҵ�
		public static void main(String[] args) {
			
			//aBook.bookDisplay();

			//aBook.setDefaultLookAndFeelDecorated(true);
	    
			/*for(int i=0;i<fonts.length;i++)
			System.out.println(fonts[i]);*/
		}
		
		public void refreshData(){
		      //���� ������ ���̺� ��ȸ�� ����� �ִٸ� �ϴ� ��� ��������.
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
		    	  //Controller�������� �Ѱܹ��� ��ü �����͸� ���̺� ����ϱ�
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
			//���� ������ ���̺� ��ȸ�� ����� �ִٸ� �ϴ� ��� ��������.
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
			//�˻� ?
			else if(obj==jbtn_search || obj==jmi_search){
				//dtm_addr.removeRow(ac.allSend(pvo).length);
			
				refreshData2();
				jtf.setText("");
				
			}
			
			//�Է¹�ư �����ž�?
			else if(obj==jbtn_add || obj==jmi_add){
				aDialog.dialogDisplay(pvo, aBook, jbtn_add.getText(), isView);
			
				
			}
			
			//������ư Ŭ���߾�?
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
					jop_addr.showMessageDialog(this, "�������ּ���");
				}
			}
			//���� ?
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
					System.out.println("�� : "+dtm_addr.getValueAt(tb, 0));
					 pvo.setAddr_no(dtm_addr.getValueAt(tb, 0));
					  ac.send(pvo);
					  refreshData();
				}else{
					jop_addr.showMessageDialog(this, "�������ּ���");
				}
					  
			}
			//����ȸ?
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
						jop_addr.showMessageDialog(this, "�������ּ���");
					}
				}

		}
		 @Override
		   public void focusGained(FocusEvent fe) {
			 //focusYn = fe.isTemporary();
		        focusYn = true; //��Ŀ���� �ؽ�Ʈ������ ������
		   }

		   @Override
		   public void focusLost(FocusEvent fe) {
		       //focusNo = fe.isTemporary(); 
			   focusNo = false; //��Ŀ���� �ؽ�Ʈ������ ������
		   }

		   @Override
		   public void keyPressed(KeyEvent e) {
		        if(focusYn==true){
		            if(e.getKeyCode() == KeyEvent.VK_ENTER){ //����Ű�� ����������
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
System.out.println(vvos[i]);//v.copyInto(dvos);�� ���� �迭�ȿ� Vector�� ������ �ִ� �ּҹ��� �ʱ�ȭ
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

