package com.chatting.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PersonTalk extends JFrame implements ActionListener{

   FriendList fl = null;
   
   JPanel jp_north = new JPanel();
   JPanel jp_center = new JPanel();
   JPanel jp_south = new JPanel();
   JPanel jp_south_first = new JPanel();
   
   JLabel jlb_title = new JLabel("1:1대화");
   
   JButton jbtn_confirm = new JButton("전송");
   JButton jbtn_cancel = new JButton("취소");   
   
   JTextField jtf_msg = new JTextField();
   
   JTextArea  jta_display = new JTextArea(22,40);
    JScrollPane jsp_display = new JScrollPane(jta_display
                                                    , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
                                                    , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                                                    );
    String nickName = null;
    String otherName = null;
    public PersonTalk(){}
    
    public PersonTalk(FriendList fl){
       this.fl = fl;
       //initDisplay();
    }
   public void initDisplay(String nickName, String name){
      this.nickName = nickName;
      this.otherName = name;
      jbtn_cancel.addActionListener(this);
      jbtn_confirm.addActionListener(this);
      jtf_msg.addActionListener(this);
      this.setSize(470,500);
      this.setLayout(new BorderLayout());
      this.add("North", jp_north);
      this.add("Center", jp_center);
      this.add("South", jp_south);
      this.setTitle(nickName+"과(와) "+name+"님의 대화창");
      
      jp_north.setLayout(new FlowLayout());
      jp_north.add(jlb_title);
      
      jp_center.add(jsp_display);
      
      jp_south.setLayout(new BorderLayout());
      jp_south.add("Center", jtf_msg);
      jp_south.add("East", jp_south_first);
      jp_south_first.setLayout(new GridLayout());
      jp_south_first.add(jbtn_confirm);
      jp_south_first.add(jbtn_cancel);
      
      this.setResizable(false);
      this.setVisible(true);
      jta_display.setLineWrap(true);
      jta_display.setEditable(false);
      jtf_msg.requestFocus();
      
      
   }
   @Override
   public void actionPerformed(ActionEvent ae) {
      Object obj = ae.getSource();
      
      if((obj == jbtn_confirm) || (obj==jtf_msg)){
         try {
            String text = jtf_msg.getText();
            fl.oos.writeObject(Protocol.ONE+"#"+nickName+"#"+otherName+"#"+text);
         } catch (Exception e) {
            // TODO: handle exception
         }
         jtf_msg.setText("");
      }else if(obj == jbtn_cancel){
         this.setVisible(false);
         this.dispose();
      }
      
   }
}