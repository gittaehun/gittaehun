package com.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class AddLogic {
	

	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public AddressVO insertAddress(AddressVO avo) {
		// TODO Auto-generated method stub
		System.out.println("add logic ȣ�⼺��");
		/*
		 *	�������� ����Ŭ ������ �����Ϸ��� ���ڿ��� ��ƾ� �ϴµ� StringŬ������ ����Ͽ�
		 *	+�����ڸ� ����ϸ� ������ ���� ��ŭ ��ü�� �Ź� ���λ�����
		 *  �̶� StringBuilderŬ����(�̱۽����忡�� ����)�� �̿���
		 *  �̶� StringBufferŬ����(��Ƽ�����忡�� ����)�� �̿���
		 */
		System.out.println(avo.getAddr_phone());
		StringBuilder sql = new StringBuilder();
	sql.append("insert into address(ADDR_NO, ADDR_LIST, ADDR_NAME, ADDR_ADDRESS, ");
	sql.append("ADDR_PHONE, ADDR_MAIL, ADDR_ROOM, ADDR_COMMENTS)");
	sql.append(" values(seq_address.nextval,?,?,?,?");
	sql.append(",?,?,?)");
	
	int result=0;
		try {                                                              
			Class.forName(DeptDB_Connection._DRIVER);
			conn = DriverManager.getConnection(DeptDB_Connection._URL
											  ,DeptDB_Connection._USER
											  ,DeptDB_Connection._PW);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, avo.getAddr_list());
			pstmt.setString(2, avo.getAddr_name());
			pstmt.setString(3, avo.getAddr_address());
			pstmt.setString(4, avo.getAddr_phone());
			pstmt.setString(5, avo.getAddr_mail());
			pstmt.setString(6, avo.getAddr_room());
			pstmt.setString(7, avo.getAddr_comments());
			
			result = pstmt.executeUpdate();
			if(result == 1){
				System.out.println("�Է� ����~");
				avo.setResult(result);
			}
		}catch(ClassNotFoundException ce){
			System.out.println("����̹� Ŭ������ ��ã�ƿ�");
		}catch (SQLException se) {
			System.out.println(se.toString());
			System.out.println("[[query]]"+sql.toString());	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return avo;
	}
	

	
	
	

	
}
