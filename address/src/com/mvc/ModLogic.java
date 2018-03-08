package com.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ModLogic {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	public AddressVO updateAddress(AddressVO avo) {
		System.out.println("UPDATE logic 호출성공");
		/*
		 *	쿼리문을 오라클 서버로 전송하려면 문자열로 담아야 하는데 String클래스를 사용하여
		 *	+연산자를 사용하면 덧셉한 갯수 만큼 객체가 매번 새로생성됨
		 *  이때 StringBuilder클래스(싱글스레드에서 안전)를 이용함
		 *  이때 StringBuffer클래스(멀티스레드에서 안전)를 이용함
		 */
		
		System.out.println(avo.getAddr_no());
		StringBuilder sql = new StringBuilder();
		sql.append("update address ");
		sql.append("set ADDR_LIST = '"+avo.getAddr_list()+"', ADDR_NAME='"+avo.getAddr_name()+"', ADDR_ADDRESS='"+avo.getAddr_address()+"', ADDR_PHONE='"+avo.getAddr_phone()+"'");
		sql.append(", ADDR_MAIL='"+avo.getAddr_mail()+"', ADDR_ROOM='"+avo.getAddr_room()+"', ADDR_COMMENTS='"+avo.getAddr_comments()+"'");
		sql.append("where ADDR_NO="+avo.getAddr_no());
	int result=0;                                                        
		try {                                                              
			Class.forName(DeptDB_Connection._DRIVER);
			conn = DriverManager.getConnection(DeptDB_Connection._URL
											  ,DeptDB_Connection._USER
											  ,DeptDB_Connection._PW);
			pstmt = conn.prepareStatement(sql.toString());
			
			/*pstmt.setString(1, avo.getAddr_name());
			pstmt.setString(2, avo.getAddr_address());
			pstmt.setString(3, avo.getAddr_phone());
			pstmt.setString(4, avo.getAddr_mail());
			pstmt.setString(5, avo.getAddr_gender());
			pstmt.setString(6, avo.getAddr_comments());
			pstmt.setObject(7, avo.getAddr_no());*/
			
			result = pstmt.executeUpdate();
			if(result == 1){
				System.out.println("수정 성공~");
				avo.setResult(result);
			}
		}catch(ClassNotFoundException ce){
			System.out.println("드라이버 클래스를 못찾아요");
		}catch (SQLException se) {
			System.out.println(se.toString());
			System.out.println("[[query]]"+sql.toString());	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return avo;
	}
	
}
