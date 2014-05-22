package com.goldsunny.itsm.model;

public class MTeamPersonMDL {
	private String OID;
	private String MTeamID;
	private String EmployeeID;
	private int CanMT;
	private int CanLocaleCheck;
	private int CanFinalCheck;
	private int CanSelectFinalCheck;
	private int CanReport;

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getMTeamID() {
		return MTeamID;
	}

	public void setMTeamID(String mTeamID) {
		MTeamID = mTeamID;
	}

	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}

	public int getCanMT() {
		return CanMT;
	}

	public void setCanMT(int canMT) {
		CanMT = canMT;
	}

	public int getCanLocaleCheck() {
		return CanLocaleCheck;
	}

	public void setCanLocaleCheck(int canLocaleCheck) {
		CanLocaleCheck = canLocaleCheck;
	}

	public int getCanFinalCheck() {
		return CanFinalCheck;
	}

	public void setCanFinalCheck(int canFinalCheck) {
		CanFinalCheck = canFinalCheck;
	}

	public int getCanSelectFinalCheck() {
		return CanSelectFinalCheck;
	}

	public void setCanSelectFinalCheck(int canSelectFinalCheck) {
		CanSelectFinalCheck = canSelectFinalCheck;
	}

	public int getCanReport() {
		return CanReport;
	}

	public void setCanReport(int canReport) {
		CanReport = canReport;
	}
}
