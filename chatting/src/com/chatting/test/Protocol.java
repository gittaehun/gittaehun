package com.chatting.test;

public class Protocol {
	public static final int ROOM_IN  = 100; //대화방 입장
	public static final int TALK  = 200; //다자간 대화
	public static final int TALK_IN = 210; //실시간 접속 유저
	public static final int TALK_INVITE = 220;
	public static final int TALK_OUT = 230;//단톡방에서 유저나갈시
	public static final int ONE  = 300; //1:1대화 (내가:메시지처리)
	public static final int ONE_IN  = 310; //1:1대화 (상대방 창띄우기)
	public static final int CHANGE   = 400; //대화명 변경
	public static final int ROOM_OUT = 500; //대화방 나감 (내가)
	public static final int CHAT_IN = 600;
	public static final int OVERLAP = 700;
	
}
