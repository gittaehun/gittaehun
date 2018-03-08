package com.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class SelLogic {
	Connection conn = null;//연결하는 클래스
	PreparedStatement pstmt = null;//디비로 명령어 날리기위한 전령역할 클래스(SELECT문을 오라클 서버에 전달해줄 객체)
	ResultSet rs = null;//커서를 조작하는 클래스
	
	public AddressVO[] selectAddress(AddressVO avo) {
		System.out.println("SelLogic호출 성공");
		System.out.println(avo.getJcb_idx());
		System.out.println(avo.getJcb_text());
		AddressVO avos[] = null;	
		String column=null;
		System.out.println("값 : "+avo.getJcb_text());
		System.out.println("컬럼 : "+avo.getJcb_idx());
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
       
           System.out.println("sql문 : "+sql.toString());
		try {//예외처리 문장
			Class.forName(DeptDB_Connection._DRIVER);
			conn = DriverManager.getConnection(DeptDB_Connection._URL
											, DeptDB_Connection._USER
											, DeptDB_Connection._PW);
			 
			
			pstmt = conn.prepareStatement(sql.toString()); 
			//pstmt.setString(1, "%"+avo.getJcb_text()+"%");
			rs = pstmt.executeQuery(sql.toString());//SQL실행 쿼리 날림 DB로
		
			Vector<AddressVO> v = new Vector<AddressVO>();//자바에서 제공하는 자료구조 - 끼워넣기, 추가기능
			while(rs.next()){//커서에 리턴값이 참이니 ? 네:조회결과가 존재합니다. 아니오:조회결과 없음
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
				
				////////////////////////////////////여기까지는 멤버변수에 값을 담는코드
				//////////////////////////아래부터는 조회된 로우의 갯수만큼 Vector라는 자료구조에 담는 코드
				v.add(avo);//avo를 벡터 v안에 담아주세요 
			}
			avos = new AddressVO[v.size()];//avo의 사이즈
			v.copyInto(avos);//Vector안에 담겨있는 avo의 주소번지를 DeptVO[]안에 복사해주는 메소드
			for(int i=0;i<avos.length;i++){
                System.out.println(avos[i]);//v.copyInto(dvos);로 인해 배열안에 Vector가 가지고 있는 주소번지 초기화
          }
			/*for(int i=0;i<avos.length;i++){
				//avos안에는 DeptVO클래스의 주소번지를 담을수 있다.
				System.out.println(avos[i]);
			//insert here - 출력해보세요
				System.out.println(avos[i].getAddr_no()+", "+avos[i].getAddr_name()
									+", "+avos[i].getAddr_address()+", "+avos[i].getAddr_mail()
									+", "+avos[i].getAddr_phone()+", "+avos[i].getAddr_gender()+", "+avos[i].getAddr_comments());
			}*/
		} catch (Exception e) {
			e.getMessage();
		}
		//if(con != null) System.out.println("오라클 서버와 연결통로 생성");
		//System.out.println("alllogic : "+avos+" 길이 : "+avos.length);
		return avos;
		
	}
	
}
