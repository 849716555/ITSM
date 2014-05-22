package com.goldsunny.itsm.model;

import java.util.Date;

public class Sys_DictListMDL {
	private String OID;
	private String Sys_DictOID;
	private String ListName;
	private String IsDefault;
	private String IsValid;
	private double SortCode;
	
	public String getOID() {
		return OID;
	}
	public void setOID(String oID) {
		OID = oID;
	}
	public String getSys_DictOID() {
		return Sys_DictOID;
	}
	public void setSys_DictOID(String sys_DictOID) {
		Sys_DictOID = sys_DictOID;
	}
	public String getListName() {
		return ListName;
	}
	public void setListName(String listName) {
		ListName = listName;
	}
	public String getIsDefault() {
		return IsDefault;
	}
	public void setIsDefault(String isDefault) {
		IsDefault = isDefault;
	}
	public String getIsValid() {
		return IsValid;
	}
	public void setIsValid(String isValid) {
		IsValid = isValid;
	}
	public double getSortCode() {
		return SortCode;
	}
	public void setSortCode(double sortCode) {
		SortCode = sortCode;
	}
	
}
