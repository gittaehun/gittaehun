package com.mvc;

public class AddressVO {
	
	//private int addr_no = 0;
	private int result=0;
	private String addr_list = null;
	private String addr_name = null;
	private String addr_phone = null;
	private String addr_address = null;
	private String addr_mail = null;
	private String addr_room = null;
	private String addr_comments = null;
	private Object addr_no = null;
	private String jcb_text=null;
	private int jcb_idx = 0;
	//private AddressVO avo[] = null;
	//DB테이블에 없는 컬럼이지만 업무처리를 위해서 필요한 변수를 추가
	//사용자가 누른 버튼에 따라 업무를 구별해야함
	//command 변수를 사용하기로함
	private String command = null;
	//입력, 수정, 삭제의 경우에는 오라클로부터 반환값이 정수이다.
	//1이면 입력, 수정, 삭제를 성공했다.
	//0이면 실패
	/*public int getAddr_no() {
		return addr_no;
	}
	public void setAddr_no(int addr_no) {
		this.addr_no = addr_no;
		System.out.println("addressVO : "+this.addr_no);
	}*/
	public String getAddr_name() {
		return addr_name;
	}
	public void setAddr_name(String addr_name) {
		this.addr_name = addr_name;
	}
	public String getAddr_address() {
		return addr_address;
	}
	public void setAddr_address(String addr_address) {
		this.addr_address = addr_address;
	}
	public String getAddr_mail() {
		return addr_mail;
	}
	public void setAddr_mail(String addr_mail) {
		this.addr_mail = addr_mail;
	}
	public String getAddr_room() {
		return addr_room;
	}
	public void setAddr_room(String addr_room) {
		this.addr_room = addr_room;
	}
	public String getAddr_comments() {
		return addr_comments;
	}
	public void setAddr_comments(String addr_comments) {
		this.addr_comments = addr_comments;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	/*public AddressVO[] getAvo() {
		return avo;
	}
	public void setAvo(AddressVO[] avo) {
		this.avo = avo;
	}*/
	public Object getAddr_no() {
		return addr_no;
	}
	public void setAddr_no(Object addr_no) {
		this.addr_no = addr_no;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getAddr_phone() {
		return addr_phone;
	}
	public void setAddr_phone(String addr_phone) {
		this.addr_phone = addr_phone;
	}
	public String getJcb_text() {
		return jcb_text;
	}
	public void setJcb_text(String jcb_text) {
		this.jcb_text = jcb_text;
	}
	public int getJcb_idx() {
		return jcb_idx;
	}
	public void setJcb_idx(int jcb_idx) {
		this.jcb_idx = jcb_idx;
	}
	public String getAddr_list() {
		return addr_list;
	}
	public void setAddr_list(String addr_list) {
		this.addr_list = addr_list;
	}
	
	

	
}