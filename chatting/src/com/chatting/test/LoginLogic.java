package com.chatting.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class LoginLogic implements ActionListener{
	LoginView loginview = new LoginView();
	DBConnection dbMgr = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public ChatVO selectChat(ChatVO cvo) {
		System.out.println("loginLogic selectChat 호출 성공");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT mem_id, mem_pw FROM chat_member where mem_id=? and mem_pw=?");
		String rmem_id = null;
		String rmem_pw = null;
		ChatVO rcvo = null;
		try {
			dbMgr = new DBConnection();
			Class.forName(dbMgr._DRIVER);
			con = DriverManager.getConnection(dbMgr._URL, dbMgr._USER, dbMgr._PW);
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, cvo.getMem_id());
			pstmt.setString(2, cvo.getMem_pw());
			rs = pstmt.executeQuery();
			if(rs.next()){
				rcvo = new ChatVO();
				rmem_id = rs.getString("mem_id");
				rmem_pw = rs.getString("mem_pw");
				rcvo.setMem_id(rmem_id);
				rcvo.setMem_pw(rmem_pw);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return rcvo;
		
	}
	public ChatVO idCheck(ChatVO cvo) {
		System.out.println("loginLogic idCheck 호출 성공");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT decode(mem_id,null,'none',mem_id) mem_id ");
		sql.append("FROM 			");
	    sql.append("  (SELECT                                           ");
	    sql.append("    CASE  ");
	    sql.append("   WHEN mem_id =? THEN mem_id  ");
	    sql.append("  END mem_id                        ");
	    sql.append("  FROM chat_member                    ");
	    sql.append("  ORDER BY mem_id asc    )  ");
	    sql.append("   WHERE rownum = 1       ");
	   
	  
		String rmem_id = null;
		ChatVO rcvo = null;
		try {
			dbMgr = new DBConnection();
			Class.forName(dbMgr._DRIVER);
			con = DriverManager.getConnection(dbMgr._URL, dbMgr._USER, dbMgr._PW);
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, cvo.getMem_id());
			rs = pstmt.executeQuery();
			if(rs.next()){
				rcvo = new ChatVO();
				rmem_id = rs.getString("mem_id");
				rcvo.setMem_id(rmem_id);
			}
		} catch (Exception e) {
			System.out.println("Exception : "+e.toString());
		}
		return rcvo;
	}	
/*	public static void main(String[] args) {
		
	}*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
