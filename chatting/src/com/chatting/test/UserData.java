package com.chatting.test;

import java.util.Vector;

public class UserData {
	Vector<FriendServerThread> t_globalUser = new Vector<FriendServerThread>(); //전체 유저
	Vector<FriendServerThread> t_userVC = new Vector<FriendServerThread>(); //1:1대화방 유저 쓰레드(1) 방접속 유저쓰레드(2)
		
}
