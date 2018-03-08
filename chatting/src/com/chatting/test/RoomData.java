package com.chatting.test;

import java.util.Vector;

public class RoomData {
	String title =null;
   Vector<String> userVC = new Vector<String>(); //방에 참여하는 인원 대화명
      
   
   public RoomData(String title){
	   this.title=title;
	   
   }


public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}


public Vector<String> getUserVC() {
	return userVC;
}


public void setUserVC(Vector<String> userVC) {
	this.userVC = userVC;
}


  
}