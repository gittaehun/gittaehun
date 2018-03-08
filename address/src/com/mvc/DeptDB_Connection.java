package com.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


public class DeptDB_Connection {
	public static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";//����̹� ����(��θ� ���ִ°� ����), static--���� ���� final--�������
	public static final String _URL = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";//Uniform Resource Locate(URL), thin����̹� ���(���������ڰ� ������), ip, port, sid����
	public static final String _USER = "scott"; //�����̸�
	public static final String _PW = "tiger"; //���
	
	public static void main(String[] args) {
		Connection con = null;//�����ϴ� Ŭ����
		Statement stmt = null;//���� ��ɾ� ���������� ���ɿ��� Ŭ����(SELECT���� ����Ŭ ������ �������� ��ü)
		ResultSet rs = null;//Ŀ���� �����ϴ� Ŭ����
		try {//����ó�� ����
			Class.forName(_DRIVER);
			con = DriverManager.getConnection(_URL, _USER, _PW);
			String sql = "SELECT deptno, dname, loc FROM dept";
			stmt = con.createStatement();//����Ŭ���� �ε����ִ� ����
			rs = stmt.executeQuery(sql);//SQL���� ���� ���� DB��
			DeptVO dvo = null;
			DeptVO dvos[] = null;
			Vector<DeptVO> v = new Vector<DeptVO>();//�ڹٿ��� �����ϴ� �ڷᱸ�� - �����ֱ�, �߰����
			while(rs.next()){//Ŀ���� ���ϰ��� ���̴� ? ��:��ȸ����� �����մϴ�. �ƴϿ�:��ȸ��� ����
				//System.out.println(rs.getString(1));
				dvo = new DeptVO();
				dvo.setDeptno(rs.getInt("deptno"));
				dvo.setDname(rs.getString("dname"));
				dvo.setLoc(rs.getString("loc"));
				////////////////////////////////////��������� ��������� ���� ����ڵ�
				//////////////////////////�Ʒ����ʹ� ��ȸ�� �ο��� ������ŭ Vector��� �ڷᱸ���� ��� �ڵ�
				v.add(dvo);//dvo�� ���� v�ȿ� ����ּ��� 
			}
			dvos = new DeptVO[v.size()];//dvo�� ������
			v.copyInto(dvos);//Vector�ȿ� ����ִ� dvo�� �ּҹ����� DeptVO[]�ȿ� �������ִ� �޼ҵ�
			for(int i=0;i<dvos.length;i++){
				//dvos�ȿ��� DeptVOŬ������ �ּҹ����� ������ �ִ�.
				System.out.println(dvos[i]);
				//insert here - ����غ�����
				System.out.println(dvos[i].getDeptno()+", "+dvos[i].getDname()+", "+dvos[i].getLoc());
			}
		} catch (Exception e) {
			System.out.println("�����߻� : "+e.toString());
		}
		//if(con != null) System.out.println("����Ŭ ������ ������� ����");
		
		
	}

}
