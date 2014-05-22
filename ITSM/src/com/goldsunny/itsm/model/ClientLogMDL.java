package com.goldsunny.itsm.model;

import java.util.Date;

public class ClientLogMDL {

	private String ID;
	private String Name;
	private String IP;
	private int StationNo;
	private String StationName;
	private int LaneNo;
	private String OperatorNo;
	private String OperatorName;
	private int SquadNo;
	private Date SquadDate;
	private Date LastTime;
	private Date LoginTime;
	private String Remark;
	private boolean IsUpload;

	// Remark
	public String getRemark() {
		return Remark;
	}

	public void setRemark(String value) {
		this.Remark = value;
	}

	// IsUpload
	public boolean getIsUpload() {
		return IsUpload;
	}

	public void setIsUpload(boolean value) {
		this.IsUpload = value;
	}

	// LoginTime
	public Date getLoginTime() {
		return LoginTime;
	}

	public void setLoginTime(Date value) {
		this.LoginTime = value;
	}

	// LastTime
	public Date getLastTime() {
		return LastTime;
	}

	public void setLastTime(Date value) {
		this.LastTime = value;
	}

	// SquadDate
	public Date getSquadDate() {
		return SquadDate;
	}

	public void setSquadDate(Date value) {
		this.SquadDate = value;
	}

	// SquadNo
	public int getSquadNo() {
		return SquadNo;
	}

	public void setSquadNo(int value) {
		this.SquadNo = value;
	}

	// OperatorName
	public String getOperatorName() {
		return OperatorName;
	}

	public void setOperatorName(String value) {
		this.OperatorName = value;
	}

	// OperatorNo
	public String getOperatorNo() {
		return OperatorNo;
	}

	public void setOperatorNo(String value) {
		this.OperatorNo = value;
	}// ID

	public String getID() {
		return ID;
	}

	public void setID(String value) {
		this.ID = value;
	}

	// Name
	public String getName() {
		return Name;
	}

	public void setName(String value) {
		this.Name = value;
	}

	// IP
	public String getIP() {
		return IP;
	}

	public void setIP(String value) {
		this.IP = value;
	}

	// StationNo
	public int getStationNo() {
		return StationNo;
	}

	public void setStationNo(int value) {
		this.StationNo = value;
	}

	// StationName
	public String getStationName() {
		return StationName;
	}

	public void setStationName(String value) {
		this.StationName = value;
	}

	// LaneNo
	public int getLaneNo() {
		return LaneNo;
	}

	public void setLaneNo(int value) {
		this.LaneNo = value;
	}
}
