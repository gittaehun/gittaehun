package com.mvc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
public class DelLogic {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	public AddressVO deleteAddress(AddressVO avo) {
		System.out.println("del logic 호출성공");
		/*
		 *	쿼리문을 오라클 서버로 전송하려면 문자열로 담아야 하는데 String클래스를 사용하여
		 *	+연산자를 사용하면 덧셉한 갯수 만큼 객체가 매번 새로생성됨
		 *  이때 StringBuilder클래스(싱글스레드에서 안전)를 이용함
		 *  이때 StringBuffer클래스(멀티스레드에서 안전)를 이용함
		 */
		StringBuilder sql = new StringBuilder();
		Object a= avo.getAddr_no();
	
	sql.append("delete from address where addr_no=");//? or addr_no= ?");
	sql.append(a);
	System.out.println(a);
	int result=0;
		try {                                                              
			Class.forName(DeptDB_Connection._DRIVER);
			conn = DriverManager.getConnection(DeptDB_Connection._URL
											  ,DeptDB_Connection._USER
											  ,DeptDB_Connection._PW);
			pstmt = conn.prepareStatement(sql.toString());
			
			result = pstmt.executeUpdate();
			if(result == 1){
				System.out.println("삭제 성공~");
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
