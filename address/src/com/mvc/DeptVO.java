package com.mvc;

public class DeptVO {
	int deptno =0;
	String dname = "";
	String loc = null;
	
	//getter�� ���� �о�� �� ����մϴ�
	public int getDeptno() {
		return deptno;
	}
	//setter�� ���� ������ �� ����մϴ�
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
