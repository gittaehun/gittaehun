package com.chatting.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class DBConnection {
	public static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String _URL    = "jdbc:oracle:thin:@192.168.0.116:1521:ORCL11";
	public static final String _USER   = "scott";
	public static final String _PW      = "tiger";
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs   = null;
		try {
			Class.forName(_DRIVER);
			con = DriverManager.getConnection(_URL, _USER, _PW);
			String sql = "SELECT mem_id, mem_pw, mem_name, mem_tel, mem_gender FROM chat_member";
			stmt = con.createStatement();//전형클래스 로딩해주는 문장
			rs = stmt.executeQuery(sql);
			ChatVO cvo = null;
			ChatVO cvos[] = null;
			Vector<ChatVO> v = new Vector<ChatVO>();//자료구조-끼워넣기,추가가능
			while(rs.next()){//커서에 리턴값이 참이니? 네:조회결과가 존재합니다. 아니오:조회결과 없음.
				cvo = new ChatVO();
				cvo.setMem_id(rs.getString("mem_id"));
				cvo.setMem_pw(rs.getString("mem_pw"));
				cvo.setMem_name(rs.getString("mem_name"));
				cvo.setMem_tel(rs.getString("mem_tel"));
				cvo.setMem_gender(rs.getString("mem_gender"));
				//ChatVO안에 멤버변수에 값을 담는 코드
				v.add(cvo);
			}
			cvos = new ChatVO[v.size()];
			//Vector안에 담겨있는 cvo의 주소번지를 ChatVO[]안에 복사해주는 메소드
			v.copyInto(cvos);
			for(int i=0;i<cvos.length;i++){
				//cvos안에는 ChatVO클래스의 주소번지를 담을 수 있다.
				System.out.println(cvos[i]);
				System.out.println(cvos[i].getMem_id()
								+","+cvos[i].getMem_pw()
								+","+cvos[i].getMem_name()
								+","+cvos[i].getMem_tel()
								+","+cvos[i].getMem_gender()
								);
			}
		} catch (Exception e) {
			System.out.println("에러발생 : "+e.toString());
		}
		if(con!=null) System.out.println("오라클 서버와 연결통로 생성");
	}

}