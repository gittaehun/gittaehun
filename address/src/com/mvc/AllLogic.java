package com.mvc;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class AllLogic {

	public AllLogic(){
	 
	}
	//AddressBook ab = new AddressBook();
	
	public AddressVO[] allAddress(AddressVO avo) {
		AddressVO avos[] = null;		
		Connection con = null;//�����ϴ� Ŭ����
		Statement stmt = null;//���� ��ɾ� ���������� ���ɿ��� Ŭ����(SELECT���� ����Ŭ ������ �������� ��ü)
		ResultSet rs = null;//Ŀ���� �����ϴ� Ŭ����
		try {//����ó�� ����
			System.out.println("all Logic ȣ��");
			Class.forName(DeptDB_Connection._DRIVER);
			con = DriverManager.getConnection(DeptDB_Connection._URL
											, DeptDB_Connection._USER
											, DeptDB_Connection._PW);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT addr_no, addr_list,addr_name");
			sql.append("	  , addr_address, addr_mail");
			sql.append("	  , addr_phone, addr_room");
			sql.append("	  , addr_comments FROM address order by addr_list desc");
			stmt = con.createStatement();//����Ŭ���� �ε����ִ� ����
			rs = stmt.executeQuery(sql.toString());//SQL���� ���� ���� DB��
			
			Vector<AddressVO> v = new Vector<AddressVO>();//�ڹٿ��� �����ϴ� �ڷᱸ�� - �����ֱ�, �߰����
			while(rs.next()){//Ŀ���� ���ϰ��� ���̴� ? ��:��ȸ����� �����մϴ�. �ƴϿ�:��ȸ��� ����
				//System.out.println(rs.getString(1));
				avo = new AddressVO();
				avo.setAddr_no(rs.getInt("ADDR_NO"));
				avo.setAddr_name(rs.getString("ADDR_NAME"));
				avo.setAddr_list(rs.getString("ADDR_LIST"));
				avo.setAddr_address(rs.getString("ADDR_ADDRESS"));
				avo.setAddr_mail(rs.getString("ADDR_MAIL"));
				avo.setAddr_phone(rs.getString("ADDR_PHONE"));
				avo.setAddr_room(rs.getString("ADDR_ROOM"));
				avo.setAddr_comments(rs.getString("ADDR_COMMENTS"));
				////////////////////////////////////��������� ��������� ���� ����ڵ�
				//////////////////////////�Ʒ����ʹ� ��ȸ�� �ο��� ������ŭ Vector��� �ڷᱸ���� ��� �ڵ�
				v.add(avo);//avo�� ���� v�ȿ� ����ּ��� 
			}
			avos = new AddressVO[v.size()];//avo�� ������
			v.copyInto(avos);//Vector�ȿ� ����ִ� avo�� �ּҹ����� DeptVO[]�ȿ� �������ִ� �޼ҵ�
			for(int i=0;i<avos.length;i++){
                System.out.println(avos[i]);//v.copyInto(dvos);�� ���� �迭�ȿ� Vector�� ������ �ִ� �ּҹ��� �ʱ�ȭ
          }
			/*for(int i=0;i<avos.length;i++){
				//avos�ȿ��� DeptVOŬ������ �ּҹ����� ������ �ִ�.
				System.out.println(avos[i]);
			//insert here - ����غ�����
				System.out.println(avos[i].getAddr_no()+", "+avos[i].getAddr_name()
									+", "+avos[i].getAddr_address()+", "+avos[i].getAddr_mail()
									+", "+avos[i].getAddr_phone()+", "+avos[i].getAddr_gender()+", "+avos[i].getAddr_comments());
			}*/
		} catch (Exception e) {
			e.getMessage();
		}
		//if(con != null) System.out.println("����Ŭ ������ ������� ����");
		//System.out.println("alllogic : "+avos+" ���� : "+avos.length);
		System.out.println("avos : "+avos);
		return avos;
	}
	public List<AddressVO> allAddress2(AddressVO avo) {
		SqlMapClient sqlMap = null;
		List<AddressVO> addressList = new ArrayList<AddressVO>();
		try {//����ó�� ����
			String resource = "com/ibatis/SqlMapConfig.xml";
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
			addressList = sqlMap.queryForList("allAddress2");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addressList;
	}
	




}
