package com.chatting.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.chatting.test.DBConnection;
public class JoinLogic {
	Connection con = null;
	PreparedStatement pstmt = null;
	public ChatVO insertChat(ChatVO cvo) {
		System.out.println("joinLogic insertChat 호출 성공");
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO chat_member(mem_id, mem_pw, mem_name");
		sql.append("          , mem_tel, mem_gender)");
		sql.append(" VALUES(?");
		sql.append(",?,?");
		sql.append(",?,?)");
		int result = 0;
		try {
			Class.forName(DBConnection._DRIVER);
			con = DriverManager.
					getConnection(DBConnection._URL
							     ,DBConnection._USER
							     ,DBConnection._PW);
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, cvo.getMem_id());
			pstmt.setString(2, cvo.getMem_pw());
			pstmt.setString(3, cvo.getMem_name());
			pstmt.setString(4, cvo.getMem_tel());
			pstmt.setString(5, cvo.getMem_gender());
			result = pstmt.executeUpdate();
			if(result == 1){
				System.out.println("입력 성공");
			}
		} catch (ClassNotFoundException ce){
			System.out.println("드라이버 클래스를 못찾아요");
		} catch (SQLException se) {
			System.out.println(se.toString());
			System.out.println("[[query]]"+sql.toString());
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;


	}
}