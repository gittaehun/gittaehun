package com.chatting.test;

public class ChatVO {
	String mem_id = null;
	String mem_pw = null;
	String mem_checkpw = null;
	String mem_name = null;
	String mem_tel = null;
	String mem_gender = null;
	private String command = null;
	private String name = null;
	private String id = null;
	private String contents = null;
	
	/*public static void main(String args[]) {
		ChatVO cvo = new ChatVO();
	}*/

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_checkpw() {
		return mem_checkpw;
	}

	public void setMem_checkpw(String mem_checkpw) {
		this.mem_checkpw = mem_checkpw;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_tel() {
		return mem_tel;
	}

	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}

	public String getMem_gender() {
		return mem_gender;
	}

	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}


}
