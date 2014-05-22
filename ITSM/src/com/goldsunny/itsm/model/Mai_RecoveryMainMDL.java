package com.goldsunny.itsm.model;

import java.util.Date;

public class Mai_RecoveryMainMDL {
	private String OID;
	private String FaultReportID;
	private String RoadID;
	private String RoadName;
	private String Code;
	private String OperatorID;
	private String OperatorName;
	private Date ApplyDate;
	private Date RecTime;
	private Date PlanRecoveryTime;
	private String FaultReason;
	private String Solution;
	private String MTeamID;
	private String MTeamName;
	private String MTeamMan;
	private double RepairPrice;
	private double CalculatePrice;
	private String BuStatus;
	private Date ActualRecoveryTime;
	private int IsPlaceOnFile;

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getFaultReportID() {
		return FaultReportID;
	}

	public void setFaultReportID(String faultReportID) {
		FaultReportID = faultReportID;
	}

	public String getRoadID() {
		return RoadID;
	}

	public void setRoadID(String roadID) {
		RoadID = roadID;
	}

	public String getRoadName() {
		return RoadName;
	}

	public void setRoadName(String roadName) {
		RoadName = roadName;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getOperatorID() {
		return OperatorID;
	}

	public void setOperatorID(String operatorID) {
		OperatorID = operatorID;
	}

	public String getOperatorName() {
		return OperatorName;
	}

	public void setOperatorName(String operatorName) {
		OperatorName = operatorName;
	}

	public Date getApplyDate() {
		return ApplyDate;
	}

	public void setApplyDate(Date applyDate) {
		ApplyDate = applyDate;
	}

	public Date getRecTime() {
		return RecTime;
	}

	public void setRecTime(Date recTime) {
		RecTime = recTime;
	}

	public Date getPlanRecoveryTime() {
		return PlanRecoveryTime;
	}

	public void setPlanRecoveryTime(Date planRecoveryTime) {
		PlanRecoveryTime = planRecoveryTime;
	}

	public String getFaultReason() {
		return FaultReason==null?"":FaultReason;
	}

	public void setFaultReason(String faultReason) {
		FaultReason = faultReason;
	}

	public String getSolution() {
		return Solution==null?"":Solution;
	}

	public void setSolution(String solution) {
		Solution = solution;
	}

	public String getMTeamID() {
		return MTeamID;
	}

	public void setMTeamID(String mTeamID) {
		MTeamID = mTeamID;
	}

	public String getMTeamName() {
		return MTeamName;
	}

	public void setMTeamName(String mTeamName) {
		MTeamName = mTeamName;
	}

	public String getMTeamMan() {
		return MTeamMan==null?"":MTeamMan;
	}

	public void setMTeamMan(String mTeamMan) {
		MTeamMan = mTeamMan;
	}

	public double getRepairPrice() {
		return RepairPrice;
	}

	public void setRepairPrice(double repairPrice) {
		RepairPrice = repairPrice;
	}

	public double getCalculatePrice() {
		return CalculatePrice;
	}

	public void setCalculatePrice(double calculatePrice) {
		CalculatePrice = calculatePrice;
	}

	public String getBuStatus() {
		return BuStatus;
	}

	public void setBuStatus(String buStatus) {
		BuStatus = buStatus;
	}

	public Date getActualRecoveryTime() {
		return ActualRecoveryTime;
	}

	public void setActualRecoveryTime(Date actualRecoveryTime) {
		ActualRecoveryTime = actualRecoveryTime;
	}

	public int getIsPlaceOnFile() {
		return IsPlaceOnFile;
	}

	public void setIsPlaceOnFile(int isPlaceOnFile) {
		IsPlaceOnFile = isPlaceOnFile;
	}
}
