package com.chatting.test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;



public class FriendServerThread extends Thread{
   Socket            socket = null;
   ObjectOutputStream   oos = null;
   ObjectInputStream    ois = null;
   String          chatName = null;
   FriendServer          fs = null;
   String 		  roomTitle = null;
   
   
   public FriendServerThread(){
      
   }
   public FriendServerThread(FriendServer fs){
      this.fs=fs;
      socket = fs.client;
      
      try {
         oos = new ObjectOutputStream(socket.getOutputStream());
         ois = new ObjectInputStream(socket.getInputStream());
         String msg = (String)ois.readObject();
         StringTokenizer st = new StringTokenizer(msg,"#");
          st.nextToken();
          chatName = st.nextToken();
          fs.jta_log.append("###"+chatName+"###님이 입장\n");
          for(FriendServerThread fst:fs.ud.t_globalUser){
             if(chatName!=null){
                for(int i=0; i<fs.ud.t_globalUser.size();i++){
                   if(chatName.equals(fs.ud.t_globalUser.get(i).chatName)){
                      fs.jta_log.append("같은 아이디 존재");
                      fs.ud.t_globalUser.remove(chatName);
                      oos.writeObject(Protocol.OVERLAP+"#"+chatName);
                   }else{
                      String currentName = fst.chatName;
                      this.send(Protocol.ROOM_IN+"#"+currentName);    
                      break;
                   }
                }
             }
          }
          fs.ud.t_globalUser.add(this);
          this.broadCasting(msg);
          
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   //대화방에 참여한 모든 친구들 정보를 얻어와서 전송한다.
    public void broadCasting(String message){
    	for(FriendServerThread fst:fs.ud.t_globalUser){
    		//System.out.println("브로드캐스팅"+message);
    		fst.send(message);
    	}
    }
    //단톡방 초대시 유저중 그전 유저들에게만 메시지 보내기
    public void broadCasting_room(String message){
        for(int i=0;i<fs.ud.t_userVC.size()-1;i++){
        	//System.out.println("브로드캐스팅"+message);
        	fs.ud.t_userVC.get(i).send(message);
        }
     }
    public void broadCasting_out(String message){
    	for(FriendServerThread fst:fs.ud.t_userVC){
    		fst.send(message);
    	}
    }
    
    
         
    //대화방에 참여한 친구들에게 실제로 말하는 코드임.
    //귓속말과 같은 1:1 대화등을 고려하여 send 메소드를 분리함
    public void send(String message){
       //JOptionPane.showMessageDialog(fs, "send호출");
       try {
         this.oos.writeObject(message);
      } catch (Exception e) {
         e.printStackTrace();
      }
    }
    
    @Override
    public synchronized void run(){
       boolean isStop = false;
       try {
         while(!isStop){
            String msg = (String)ois.readObject();
            fs.jta_log.append(msg+"\n");
            fs.jta_log.setCaretPosition(fs.jta_log.getDocument().getLength());
            
            StringTokenizer st = null;
            int protocol = 0;
            if(msg!=null){
               st = new StringTokenizer(msg,"#");
               protocol = Integer.parseInt(st.nextToken());
            }
            switch(protocol){
               case Protocol.TALK:{
                  
                  String roomTitle = st.nextToken();
                  String nickName = st.nextToken();
                  String message  = st.nextToken();
                  /*for(RoomData rl : fs.roomList){
                	  if(roomTitle.equals(rl.title)){
                		  for(FriendServerThread fst:fs.ud.t_userVC){
                			  for(int i=0;i<rl.userVC.size();i++){
                				  if(fst.chatName.equals(rl.userVC.get(i))){
                					  fst.oos.writeObject(Protocol.TALK+"#"+roomTitle+"#"+message);
                				  }
                				  
                			  }
                		  }
                	  }                	  
                  }*/
                  
                  for(FriendServerThread fst:fs.ud.t_userVC){
                	  fst.send(Protocol.TALK+"#"+roomTitle+"#"+nickName+"#"+message);
                  }
                  
               }break;
               case Protocol.TALK_IN:{
            	    String roomTitle = st.nextToken();
            	    String myName = st.nextToken();
            	    String otherName = st.nextToken();
            	    //JOptionPane.showMessageDialog(fs, "개설자, 최초1명 :"+myName+", "+otherName);
            	    fs.ud.t_userVC.add(this);//최초 방 개설자 쓰레드 저장
            	    
            	   // JOptionPane.showMessageDialog(fs, "벡터에 들은 개설자이름"+fs.ud.t_userVC.get(0).chatName);
            	    fs.rd = new RoomData(roomTitle);//방이름 저장
            	    fs.rd.userVC.add(myName);//개설자 이름저장
            	    fs.rd.userVC.add(otherName);//개설자가 선택한 최초 1명 이름 저장
            	    for(FriendServerThread fst:fs.ud.t_globalUser){
                        if(otherName.equals(fst.chatName)){//최초1명과 같은 유저일때
                           fs.ud.t_userVC.add(fst);//최초1명 쓰레드 저장
                          // JOptionPane.showMessageDialog(fs, "벡터에 들은 최초1명이름"+fs.ud.t_userVC.get(1).chatName);
                           
                           fst.send(Protocol.TALK_IN+"#"+roomTitle+"#"+myName+"#"+otherName);//최초1명에게 메시지 전달
                           
                        }
            	    
            	    }
            	    fs.roomList.add(fs.rd);
               }break;
               case Protocol.TALK_INVITE:{
            	   String roomTitle = st.nextToken();
            	   String myName    = st.nextToken();
            	   String otherName = st.nextToken();
            	   int i=0;
            	   fs.rd.userVC.add(otherName);//초대된 사람 대화명 저장
            	   for(FriendServerThread fst:fs.ud.t_globalUser){
            		   if(otherName.equals(fst.chatName)){//초대된사람 소켓 일때
            			   //초대된 사람 소켓으로 메시지 전송
            			   fs.ud.t_userVC.add(fst);//초대된 사람 소켓 저장
            			   for(FriendServerThread fst2:fs.ud.t_userVC){
            				   i++;
            				   	String ex_user = fst2.chatName;
            				   	fs.jta_log.append("이전사용자 : "+ex_user+"\n");
            				   fst.send(Protocol.TALK_INVITE+"#"+roomTitle+"#"+myName+"#"+ex_user+"#"+i);
            			   }
            			  
            		   }
            	   }
            	   this.broadCasting_room(Protocol.TALK_INVITE+"#"+roomTitle+"#"+myName+"#"+otherName+"#"+0);  
            		
               }break;
               case Protocol.TALK_OUT:{
            	   String outName = st.nextToken();
            	   this.broadCasting_out(Protocol.TALK_OUT+"#"+outName);
               }break;
               case Protocol.ONE:{
          
                  String chatName = st.nextToken();
                  String name    = st.nextToken();
                  String message      = st.nextToken();
                  
                  for(FriendServerThread fst:fs.ud.t_globalUser){
                	  if(fst.chatName.equals(name)){
                		  //JOptionPane.showMessageDialog(fs, "fst"+chatName+","+name);
                		  fst.send(Protocol.ONE+"#"+chatName+"#"+name+"#"+message);
                	  }
                  }
                  this.send(Protocol.ONE+"#"+chatName+"#"+name+"#"+message);
                  //JOptionPane.showMessageDialog(fs, "this"+chatName+","+name);
               }break;
               case Protocol.ONE_IN:{
                  String chatName = st.nextToken();
                  String name    = st.nextToken();
                  fs.jta_log.append("보낼사람 : "+name+"\n");
                  for(int i=0;i<fs.ud.t_globalUser.size();i++){
                     if(name.equals(fs.ud.t_globalUser.get(i).chatName)){
                        
                        fs.ud.t_globalUser.get(i).send(Protocol.ONE_IN+"#"+chatName+"#"+name);
                        
                     }else if(chatName.equals(fs.ud.t_globalUser.get(i).chatName)){
                     }
                  }
               }break;
               case Protocol.CHANGE:{
            	 //현재 내가 사용하던 대화명
					String nickName = st.nextToken();
					//변경한 대화명
					String afterName = st.nextToken();
					broadCasting(Protocol.CHANGE+"#"+nickName+"#"+afterName);
					this.chatName = afterName;
               }break;
               case Protocol.ROOM_OUT:{
                  String nickName = st.nextToken();
                  String out = st.nextToken();
                  fs.ud.t_globalUser.remove(this);
                  broadCasting(Protocol.ROOM_OUT+"#"+nickName+"#"+out);
               }break;
            }
         }
      } catch (Exception e) {
         // TODO: handle exception
      }
    }
   
}