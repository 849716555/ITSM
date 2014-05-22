package com.goldsunny.itsm.model;

public class DataConfigMDL {
	private String ID;
	private String Name;
	private String DataType;
	private String DataVer;
	private String DataName;
	private int UpdateLevel;
	private String Remark;

	// ID
	public String getID() {
		return ID;
	}

	public void setID(String val) {
		this.ID = val;
	}

	// Remark
	public String getRemark() {
		return Remark;
	}

	public void setRemark(String val) {
		this.Remark = val;
	}

	// Name
	public String getName() {
		return Name;
	}

	public void setName(String val) {
		this.Name = val;
	}

	// DataType
	public String getDataType() {
		return DataType;
	}

	public void setDataType(String val) {
		this.DataType = val;
	}

	// DataVer
	public String getDataVer() {
		return DataVer;
	}

	public void setDataVer(String val) {
		this.DataVer = val;
	}

	// DataName
	public String getDataName() {
		return DataName;
	}

	public void setDataName(String val) {
		this.DataName = val;
	}

	// UpdateLevel
	public int getUpdateLevel() {
		return UpdateLevel;
	}

	public void setUpdateLevel(int val) {
		this.UpdateLevel = val;
	}
}
