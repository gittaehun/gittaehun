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
		System.out.println("del logic ȣ�⼺��");
		/*
		 *	�������� ����Ŭ ������ �����Ϸ��� ���ڿ��� ��ƾ� �ϴµ� StringŬ������ ����Ͽ�
		 *	+�����ڸ� ����ϸ� ������ ���� ��ŭ ��ü�� �Ź� ���λ�����
		 *  �̶� StringBuilderŬ����(�̱۽����忡�� ����)�� �̿���
		 *  �̶� StringBufferŬ����(��Ƽ�����忡�� ����)�� �̿���
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
