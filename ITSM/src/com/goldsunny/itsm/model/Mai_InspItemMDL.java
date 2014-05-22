package com.goldsunny.itsm.model;

public class Mai_InspItemMDL {
	private String OID;
	private String InspectionID;
	private String InspectionItem;
	private String Frequency;
	private int FreqNum;
	private String CheckItemName;
	private String CheckReq;
	private String Status;
	private double Seq;

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getInspectionID() {
		return InspectionID;
	}

	public void setInspectionID(String inspectionID) {
		InspectionID = inspectionID;
	}

	public String getInspectionItem() {
		return InspectionItem;
	}

	public void setInspectionItem(String inspectionItem) {
		InspectionItem = inspectionItem;
	}

	public String getFrequency() {
		return Frequency;
	}

	public void setFrequency(String frequency) {
		Frequency = frequency;
	}

	public int getFreqNum() {
		return FreqNum;
	}

	public void setFreqNum(int freqNum) {
		FreqNum = freqNum;
	}

	public String getCheckItemName() {
		return CheckItemName;
	}

	public void setCheckItemName(String checkItemName) {
		CheckItemName = checkItemName;
	}

	public String getCheckReq() {
		return CheckReq;
	}

	public void setCheckReq(String checkReq) {
		CheckReq = checkReq;
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
}
