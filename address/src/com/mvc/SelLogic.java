package com.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class SelLogic {
	Connection conn = null;//�����ϴ� Ŭ����
	PreparedStatement pstmt = null;//���� ��ɾ� ���������� ���ɿ��� Ŭ����(SELECT���� ����Ŭ ������ �������� ��ü)
	ResultSet rs = null;//Ŀ���� �����ϴ� Ŭ����
	
	public AddressVO[] selectAddress(AddressVO avo) {
		System.out.println("SelLogicȣ�� ����");
		System.out.println(avo.getJcb_idx());
		System.out.println(avo.getJcb_text());
		AddressVO avos[] = null;	
		String column=null;
		System.out.println("�� : "+avo.getJcb_text());
		System.out.println("�÷� : "+avo.getJcb_idx());
		switch(avo.getJcb_idx()){
		case 0:
			column="addr_list";
			break;
        case 1:
           column="addr_name";
           break;
        case 2:
           column="addr_address";
           break;
        case 3:
           column="addr_phone";
           break;
        case 4:
           column="addr_mail";
           break;
        case 5:
        	column="addr_room";
        	break;
        case 6:
        	column="addr_comments";
           break;
        }
		System.out.println(column);
		//String where=null;
		StringBuilder sql = new StringBuilder();
           sql.append("SELECT addr_no, addr_list, addr_name, addr_address");
           sql.append(", addr_phone, addr_mail, addr_room, addr_comments FROM address");
           sql.append(" where ");
           sql.append(column);
           sql.append(" like '%"+avo.getJcb_text()+"%'");
           sql.append(" order by addr_list desc");
       
           System.out.println("sql�� : "+sql.toString());
		try {//����ó�� ����
			Class.forName(DeptDB_Connection._DRIVER);
			conn = DriverManager.getConnection(DeptDB_Connection._URL
											, DeptDB_Connection._USER
											, DeptDB_Connection._PW);
			 
			
			pstmt = conn.prepareStatement(sql.toString()); 
			//pstmt.setString(1, "%"+avo.getJcb_text()+"%");
			rs = pstmt.executeQuery(sql.toString());//SQL���� ���� ���� DB��
		
			Vector<AddressVO> v = new Vector<AddressVO>();//�ڹٿ��� �����ϴ� �ڷᱸ�� - �����ֱ�, �߰����
			while(rs.next()){//Ŀ���� ���ϰ��� ���̴� ? ��:��ȸ����� �����մϴ�. �ƴϿ�:��ȸ��� ����
				//System.out.println(rs.getString(1));
				avo = new AddressVO();
				avo.setAddr_no(rs.getInt("ADDR_NO"));
				avo.setAddr_list(rs.getString("ADDR_LIST"));
				avo.setAddr_name(rs.getString("ADDR_NAME"));
				avo.setAddr_address(rs.getString("ADDR_ADDRESS"));
				avo.setAddr_phone(rs.getString("ADDR_PHONE"));
				avo.setAddr_mail(rs.getString("ADDR_MAIL"));
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
		return avos;
		
	}
	
}
