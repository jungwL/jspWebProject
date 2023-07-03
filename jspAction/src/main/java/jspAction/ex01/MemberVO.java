package jspAction.ex01;

import java.sql.Date;

public class MemberVO { //데이터를 저장후 불러오는 역할을수행한다.
	//필드 데이터 베이스 컬럼과 동일하게 준다.
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	public MemberVO() {
		
	}
	public MemberVO(String id, String pwd, String name , String email) {
		this.id=id;
		this.pwd=pwd;
		this.name=name;
		this.email=email;
	}
	//접근제한자 떄문에 getter setter로 호출해야한다.
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
}
