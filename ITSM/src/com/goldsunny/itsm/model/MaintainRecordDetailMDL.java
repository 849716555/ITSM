package com.goldsunny.itsm.model;

public class MaintainRecordDetailMDL {
	private String OID;
	private String MaiPlanDetailID;
	private String MaiRecordID;
	private String MaintainItem;
	private String Frequency;
	private String Maiclaim;
	private int FreqNum;
	private String TollStationsID;
	private String TollStations;
	private String InspLaneID;
	private String InspLane;
	private String MaiResult;
	private String FaultReportID;

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getMaiPlanDetailID() {
		return MaiPlanDetailID;
	}

	public void setMaiPlanDetailID(String maiPlanDetailID) {
		MaiPlanDetailID = maiPlanDetailID;
	}

	public String getMaiRecordID() {
		return MaiRecordID;
	}

	public void setMaiRecordID(String maiRecordID) {
		MaiRecordID = maiRecordID;
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

	public String getMaiclaim() {
		return Maiclaim;
	}

	public void setMaiclaim(String maiclaim) {
		Maiclaim = maiclaim;
	}

	public int getFreqNum() {
		return FreqNum;
	}

	public void setFreqNum(int freqNum) {
		FreqNum = freqNum;
	}

	public String getTollStationsID() {
		return TollStationsID;
	}

	public void setTollStationsID(String tollStationsID) {
		TollStationsID = tollStationsID;
	}

	public String getTollStations() {
		return TollStations;
	}

	public void setTollStations(String tollStations) {
		TollStations = tollStations;
	}

	public String getInspLaneID() {
		return InspLaneID;
	}

	public void setInspLaneID(String inspLaneID) {
		InspLaneID = inspLaneID;
	}

	public String getInspLane() {
		return InspLane;
	}

	public void setInspLane(String inspLane) {
		InspLane = inspLane;
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
}
