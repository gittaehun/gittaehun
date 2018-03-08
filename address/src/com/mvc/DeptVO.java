package com.mvc;

public class DeptVO {
	int deptno =0;
	String dname = "";
	String loc = null;
	
	//getter는 값을 읽어올 때 사용합니다
	public int getDeptno() {
		return deptno;
	}
	//setter는 값을 저장할 때 사용합니다
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}

}
