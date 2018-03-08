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
			stmt = con.createStatement();//����Ŭ���� �ε����ִ� ����
			rs = stmt.executeQuery(sql);
			ChatVO cvo = null;
			ChatVO cvos[] = null;
			Vector<ChatVO> v = new Vector<ChatVO>();//�ڷᱸ��-�����ֱ�,�߰�����
			while(rs.next()){//Ŀ���� ���ϰ��� ���̴�? ��:��ȸ����� �����մϴ�. �ƴϿ�:��ȸ��� ����.
				cvo = new ChatVO();
				cvo.setMem_id(rs.getString("mem_id"));
				cvo.setMem_pw(rs.getString("mem_pw"));
				cvo.setMem_name(rs.getString("mem_name"));
				cvo.setMem_tel(rs.getString("mem_tel"));
				cvo.setMem_gender(rs.getString("mem_gender"));
				//ChatVO�ȿ� ��������� ���� ��� �ڵ�
				v.add(cvo);
			}
			cvos = new ChatVO[v.size()];
			//Vector�ȿ� ����ִ� cvo�� �ּҹ����� ChatVO[]�ȿ� �������ִ� �޼ҵ�
			v.copyInto(cvos);
			for(int i=0;i<cvos.length;i++){
				//cvos�ȿ��� ChatVOŬ������ �ּҹ����� ���� �� �ִ�.
				System.out.println(cvos[i]);
				System.out.println(cvos[i].getMem_id()
								+","+cvos[i].getMem_pw()
								+","+cvos[i].getMem_name()
								+","+cvos[i].getMem_tel()
								+","+cvos[i].getMem_gender()
								);
			}
		} catch (Exception e) {
			System.out.println("�����߻� : "+e.toString());
		}
		if(con!=null) System.out.println("����Ŭ ������ ������� ����");
	}

}