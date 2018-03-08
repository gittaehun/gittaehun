package com.chatting.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

public class FriendListThread extends Thread{
   FriendList fl = null;
   StringTokenizer st = null;
   PersonTalk2 personTalk2 = null;
   PeopleTalk2 peopleTalk2 = null;
   //ArrayList<FriendListThread> flt_arr = new ArrayList<FriendListThread>();
   public FriendListThread(FriendList fl){
      this.fl=fl;
      personTalk2 = new PersonTalk2(fl);
      peopleTalk2 = new PeopleTalk2(fl);
      //flt_arr.add(this);
   }
   
   
   @Override
   public synchronized void run(){
      String msg ="";
      boolean isStop = false;
      
      while(!isStop){
         try {
            
            msg = (String)fl.ois.readObject();
            //JOptionPane.showMessageDialog(fl, "듣기:"+msg);
            int protocol=0;
            if(msg!=null){
               st=new StringTokenizer(msg, "#");
               protocol = Integer.parseInt(st.nextToken());
            }
            
            //System.out.println(msg);
            switch(protocol){
               case Protocol.ROOM_IN:{
                  String nickName = st.nextToken();
                  
                  Vector<String> v_names = new Vector<String>();
                  v_names.add(nickName);
                  fl.dtm_person.addRow(v_names);
                  fl.peopleTalk.dtm_person1.addRow(v_names);
                  peopleTalk2.dtm_person1.addRow(v_names);
               }break;
               case Protocol.TALK:{
            	  String roomTitle = st.nextToken();
                  String nickName = st.nextToken();
                  String message = st.nextToken();
                  
                  fl.peopleTalk.jta_display.append(nickName+"님 : "+message+"\n");
                  peopleTalk2.jta_display.append(nickName+"님 : "+message+"\n");
               }break;
               case Protocol.TALK_IN:{
            	   String roomTitle = st.nextToken();
            	   String myName = st.nextToken();
            	   String otherName = st.nextToken();
            	   //JOptionPane.showMessageDialog(fl, msg);
            	   peopleTalk2.initDisplay_PeopleTalk2(roomTitle, fl.nickName);//최초 1명 화면킴
            	   Vector<String> v_name = new Vector<String>();
            	   v_name.add(myName);
            	   peopleTalk2.dtm_person.addRow(v_name);
            	   v_name = new Vector<String>();
            	   v_name.add(otherName);
            	   peopleTalk2.dtm_person.addRow(v_name);
               }
               break;
               case Protocol.TALK_INVITE:{
            	   String roomTitle = st.nextToken();
            	   String myName = st.nextToken();
            	   String otherName = st.nextToken();
            	   String seq = st.nextToken();
            	   // JOptionPane.showMessageDialog(fl, otherName);
            	   if("1".equals(seq)){
            		   peopleTalk2.initDisplay_PeopleTalk2(roomTitle, fl.nickName);
            		   Vector<String> userVC = new Vector<String>();
            		   userVC.add(otherName);
            		   peopleTalk2.dtm_person.addRow(userVC);
            		   fl.peopleTalk.dtm_person.addRow(userVC);            		   
            	   }else{
            		   Vector<String> userVC = new Vector<String>();
            		   userVC.add(otherName);
            		   peopleTalk2.dtm_person.addRow(userVC);
            		   fl.peopleTalk.dtm_person.addRow(userVC);            		   
            		   
            	   }

               }break;
               case Protocol.TALK_OUT:{
            	   String outName=st.nextToken();
            	   
            	   for(int i=0;i<fl.peopleTalk.dtm_person.getRowCount();i++){
            		   if(outName.equals(fl.peopleTalk.dtm_person.getValueAt(i, 0))){
            			   fl.peopleTalk.dtm_person.removeRow(i);
            		   }
            	   }
            	   for(int i=0;i<peopleTalk2.dtm_person.getRowCount();i++){
            		   if(outName.equals(peopleTalk2.dtm_person.getValueAt(i, 0))){
            			   peopleTalk2.dtm_person.removeRow(i);
            		   }
            	   }
               }break;
               case Protocol.ONE:{
                  
                  String nickName = st.nextToken();
                  String name = st.nextToken();
                  String message = st.nextToken();
                  //JOptionPane.showMessageDialog(fl, msg);
                  fl.personTalk.jta_display.append(nickName+" : "+message+"\n");
                  personTalk2.jta_display.append(nickName+" : "+message+"\n");
               }break;
               case Protocol.ONE_IN:{
                  //JOptionPane.showMessageDialog(fl, "11");
                  String nickName = st.nextToken();
                  String name = st.nextToken();
                  personTalk2.initDisplay(name, nickName);
                  
               }break;
               case Protocol.OVERLAP:{
                  
                     JOptionPane.showMessageDialog(fl,"같은이름이 존재합니다!재접속 !", "INFO", JOptionPane.INFORMATION_MESSAGE);
                     fl.exitChat1();
                  }break;
               case Protocol.CHANGE:{
            	   String nickName = st.nextToken();
   		    	String afterName = st.nextToken();
   		    	//테이블에서 변경전 대화명을 찾아서 변경후 로 변경한다.
   		    	for(int i=0;i<fl.dtm_person.getRowCount();i++) {
   		    		//대화명 변경전에 현재 테이블에서 가져온 대화명을 받음.
   		    		String n1 = (String)fl.dtm_person.getValueAt(i,0);
   		    		if(n1.equals(nickName)) {
   		    			//변경할 대화명으로 테이블에 대화명 변경
   		    			fl.dtm_person.setValueAt(afterName,i,0);
   		    			break;
   		    		} ///// end of if
   		    	} ///// end of for
   		    	//fl.jsp_person.append(nickName+"님이 "+afterName+"님으로 변경 되었습니다.\n");
   		    	//fl.jsp_person.setCaretPosition(fl.dtm_person.getDocument().getLength());
   		    	//채팅 팝업창에 타이틀바에도 대화명을 변경 한다.
   		    	if(nickName.equals(fl.nickName)) {
   		    		fl.setTitle(afterName+"님의 대화창");
   		    		fl.nickName = afterName;
               }
               }break;
               case Protocol.ROOM_OUT:{
                     String nickName = st.nextToken();
                     String out = st.nextToken();
                     if("700".equals(out)){
                        
                     }else if("500".equals(out)){
                     try {
                     
                  } catch (Exception e) {
                     e.printStackTrace();
                  }
                     }
                     
                     for(int i=0;i<fl.dtm_person.getRowCount();i++){//로우수보다 작은동안 돈다
                         //대화명 변경전에 현재 테이블에서 가져온 대화명을 받음.
                            String n1 = fl.dtm_person.getValueAt(i, 0).toString();
                            if(n1.equals(nickName)){
                               //변경할 대화명으로 테이블내에 대화명 변경
                               fl.dtm_person.removeRow(i);
                            }
                     }
               }break;
             
            }
         
         }catch(Exception e){
            e.printStackTrace();
         }
      }
      }
}