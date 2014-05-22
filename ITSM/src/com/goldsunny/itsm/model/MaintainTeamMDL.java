package com.goldsunny.itsm.model;

public class MaintainTeamMDL {
	private String OID;
	private String CompanyID;
	private String CompanyName;
	private String Code;
	private String Name;
	private String Phone;
	private String Contacts;
	private String Remark;
	private String Status;
	private double Seq;
	private String staionId;//Â·¶ÎID
	private String staionName;//Â·¶ÎÃû³Æ

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getCompanyID() {
		return CompanyID;
	}

	public void setCompanyID(String companyID) {
		CompanyID = companyID;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getContacts() {
		return Contacts;
	}

	public void setContacts(String contacts) {
		Contacts = contacts;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public double getSeq() {
		return Seq;
	}

	public void setSeq(double seq) {
		Seq = seq;
	}

	public String getStaionId() {
		return staionId;
	}

	public void setStaionId(String staionId) {
		this.staionId = staionId;
	}

	public String getStaionName() {
		return staionName;
	}

	public void setStaionName(String staionName) {
		this.staionName = staionName;
	}
	
}
