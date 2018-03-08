package com.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


public class DeptDB_Connection {
	public static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";//드라이버 지정(경로를 써주는걸 지향), static--재사용 가능 final--상수가됨
	public static final String _URL = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";//Uniform Resource Locate(URL), thin드라이버 방식(동시접속자가 많을시), ip, port, sid설정
	public static final String _USER = "scott"; //계정이름
	public static final String _PW = "tiger"; //비번
	
	public static void main(String[] args) {
		Connection con = null;//연결하는 클래스
		Statement stmt = null;//디비로 명령어 날리기위한 전령역할 클래스(SELECT문을 오라클 서버에 전달해줄 객체)
		ResultSet rs = null;//커서를 조작하는 클래스
		try {//예외처리 문장
			Class.forName(_DRIVER);
			con = DriverManager.getConnection(_URL, _USER, _PW);
			String sql = "SELECT deptno, dname, loc FROM dept";
			stmt = con.createStatement();//전령클래스 로딩해주는 문장
			rs = stmt.executeQuery(sql);//SQL실행 쿼리 날림 DB로
			DeptVO dvo = null;
			DeptVO dvos[] = null;
			Vector<DeptVO> v = new Vector<DeptVO>();//자바에서 제공하는 자료구조 - 끼워넣기, 추가기능
			while(rs.next()){//커서에 리턴값이 참이니 ? 네:조회결과가 존재합니다. 아니오:조회결과 없음
				//System.out.println(rs.getString(1));
				dvo = new DeptVO();
				dvo.setDeptno(rs.getInt("deptno"));
				dvo.setDname(rs.getString("dname"));
				dvo.setLoc(rs.getString("loc"));
				////////////////////////////////////여기까지는 멤버변수에 값을 담는코드
				//////////////////////////아래부터는 조회된 로우의 갯수만큼 Vector라는 자료구조에 담는 코드
				v.add(dvo);//dvo를 벡터 v안에 담아주세요 
			}
			dvos = new DeptVO[v.size()];//dvo의 사이즈
			v.copyInto(dvos);//Vector안에 담겨있는 dvo의 주소번지를 DeptVO[]안에 복사해주는 메소드
			for(int i=0;i<dvos.length;i++){
				//dvos안에는 DeptVO클래스의 주소번지를 담을수 있다.
				System.out.println(dvos[i]);
				//insert here - 출력해보세요
				System.out.println(dvos[i].getDeptno()+", "+dvos[i].getDname()+", "+dvos[i].getLoc());
			}
		} catch (Exception e) {
			System.out.println("에러발생 : "+e.toString());
		}
		//if(con != null) System.out.println("오라클 서버와 연결통로 생성");
		
		
	}

}
