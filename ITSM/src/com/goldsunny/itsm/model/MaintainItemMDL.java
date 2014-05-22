package com.goldsunny.itsm.model;

public class MaintainItemMDL {
	private String OID;
	private String MaintainID;
	private String MaintainItem;
	private String Frequency;
	private int FreqNum;
	private String Maiclaim;
	private String Status;
	private double Seq;

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getMaintainID() {
		return MaintainID;
	}

	public void setMaintainID(String maintainID) {
		MaintainID = maintainID;
	}

	public String getMaintainItem() {
		return MaintainItem;
	}

	public void setMaintainItem(String maintainItem) {
		MaintainItem = maintainItem;
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

	public String getMaiclaim() {
		return Maiclaim;
	}

	public void setMaiclaim(String maiclaim) {
		Maiclaim = maiclaim;
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
