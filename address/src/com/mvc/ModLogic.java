package com.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ModLogic {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	public AddressVO updateAddress(AddressVO avo) {
		System.out.println("UPDATE logic ȣ�⼺��");
		/*
		 *	�������� ����Ŭ ������ �����Ϸ��� ���ڿ��� ��ƾ� �ϴµ� StringŬ������ ����Ͽ�
		 *	+�����ڸ� ����ϸ� ������ ���� ��ŭ ��ü�� �Ź� ���λ�����
		 *  �̶� StringBuilderŬ����(�̱۽����忡�� ����)�� �̿���
		 *  �̶� StringBufferŬ����(��Ƽ�����忡�� ����)�� �̿���
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
				System.out.println("���� ����~");
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
