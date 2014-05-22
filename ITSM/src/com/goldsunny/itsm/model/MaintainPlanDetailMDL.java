package com.goldsunny.itsm.model;

import java.util.Date;

public class MaintainPlanDetailMDL {
	private String OID;
	private String MaintainItemID;
	private String MaintainPlanID;
	private String MaintainItem;
	private String Frequency;
	private int FreqNum;
	private String Maiclaim;
	private Date MaiMPlanDate;
	
	private String MaiResult;
	private String FaultReportID;
	private String MTStationsID;
	private String MTStations;
	private String MTLaneID;
	private String MTLane;
	private String MTRecordDetailID;

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getMaintainItemID() {
		return MaintainItemID;
	}

	public void setMaintainItemID(String maintainItemID) {
		MaintainItemID = maintainItemID;
	}

	public String getMaintainPlanID() {
		return MaintainPlanID;
	}

	public void setMaintainPlanID(String maintainPlanID) {
		MaintainPlanID = maintainPlanID;
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

	public Date getMaiMPlanDate() {
		return MaiMPlanDate;
	}

	public void setMaiMPlanDate(Date maiMPlanDate) {
		MaiMPlanDate = maiMPlanDate;
	}

	public String getMaiResult() {
		return MaiResult;
	}

	public void setMaiResult(String maiResult) {
		MaiResult = maiResult;
	}

	public String getFaultReportID() {
		return FaultReportID;
	}

	public void setFaultReportID(String faultReportID) {
		FaultReportID = faultReportID;
	}

	public String getMTStationsID() {
		return MTStationsID;
	}

	public void setMTStationsID(String mTStationsID) {
		MTStationsID = mTStationsID;
	}

	public String getMTStations() {
		return MTStations;
	}

	public void setMTStations(String mTStations) {
		MTStations = mTStations;
	}

	public String getMTLaneID() {
		return MTLaneID;
	}

	public void setMTLaneID(String mTLaneID) {
		MTLaneID = mTLaneID;
	}

	public String getMTLane() {
		return MTLane;
	}

	public void setMTLane(String mTLane) {
		MTLane = mTLane;
	}

	public String getMTRecordDetailID() {
		return MTRecordDetailID;
	}

	public void setMTRecordDetailID(String mTRecordDetailID) {
		MTRecordDetailID = mTRecordDetailID;
	}
}
