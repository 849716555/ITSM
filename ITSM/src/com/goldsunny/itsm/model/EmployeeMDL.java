package com.goldsunny.itsm.model;

import java.util.Date;

public class EmployeeMDL {
	private String ID;
	private String EmplCode;
	private String EmplName;
	private String PassWord;
	private String isOperator;
	private String EmplState;
	
	private Date LoginTime;


	// EmplCode
	public String getID() {
		return ID;
	}

	public void setID(String val) {
		this.ID = val;
	}

	// EmplCode
	public String getEmplCode() {
		return EmplCode;
	}

	public void setEmplCode(String val) {
		this.EmplCode = val;
	}

	// EmplName
	public String getEmplName() {
		return EmplName;
	}

	public void setEmplName(String val) {
		this.EmplName = val;
	}

	// PassWord
	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String val) {
		this.PassWord = val;
	}

	public String getIsOperator() {
		return isOperator;
	}

	public void setIsOperator(String isOperator) {
		this.isOperator = isOperator;
	}

	public String getEmplState() {
		return EmplState;
	}

	public void setEmplState(String emplState) {
		EmplState = emplState;
	}

	public Date getLoginTime() {
		return LoginTime;
	}

	public void setLoginTime(Date loginTime) {
		LoginTime = loginTime;
	}

}
