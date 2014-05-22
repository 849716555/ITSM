package com.goldsunny.itsm.model;

import android.R.integer;

public class Syst_LocationMDL {
	private String OID;
	private String PID;
	private String RoadID;
	private String RoadName;
	private String Code;
	private String Name;
	private double BeginMiles;
	private double EndMiles;
	private String PosType;
	private int Level; 
	private String LocationDetail;
	private String Remark;
	private String Status;
	private int Sons;//0代表子节点
	private double Seq;

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
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

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getBeginMiles() {
		return BeginMiles;
	}

	public void setBeginMiles(double beginMiles) {
		BeginMiles = beginMiles;
	}

	public double getEndMiles() {
		return EndMiles;
	}

	public void setEndMiles(double endMiles) {
		EndMiles = endMiles;
	}

	public String getPosType() {
		return PosType;
	}

	public void setPosType(String posType) {
		PosType = posType;
	}

	public String getLocationDetail() {
		return LocationDetail;
	}

	public void setLocationDetail(String locationDetail) {
		LocationDetail = locationDetail;
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

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public int getSons() {
		return Sons;
	}

	public void setSons(int sons) {
		Sons = sons;
	}

	 

	
	
	
	
}
