package com.goldsunny.itsm.model;

public class Mai_InspRecordDetailMDL {
	private String OID;
	private String Insp_RecordID;
	private String Insp_PlanDetalID;
	private String Toll_StationsID;
	private String Toll_Stations;
	private String Insp_LaneID;
	private String Insp_Lane;
	private String Inspection_Item;
	private String Frequency;
	private int Freq_Num;
	private String Check_ItemName;
	private String Check_Req;
	private String Insp_Result;
	private String Insp_Memo;
	private String FaultReportID;

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getInsp_RecordID() {
		return Insp_RecordID;
	}

	public void setInsp_RecordID(String insp_RecordID) {
		Insp_RecordID = insp_RecordID;
	}

	public String getInsp_PlanDetalID() {
		return Insp_PlanDetalID;
	}

	public void setInsp_PlanDetalID(String insp_PlanDetalID) {
		Insp_PlanDetalID = insp_PlanDetalID;
	}

	public String getToll_StationsID() {
		return Toll_StationsID;
	}

	public void setToll_StationsID(String toll_StationsID) {
		Toll_StationsID = toll_StationsID;
	}

	public String getToll_Stations() {
		return Toll_Stations;
	}

	public void setToll_Stations(String toll_Stations) {
		Toll_Stations = toll_Stations;
	}

	public String getInsp_LaneID() {
		return Insp_LaneID;
	}

	public void setInsp_LaneID(String insp_LaneID) {
		Insp_LaneID = insp_LaneID;
	}

	public String getInsp_Lane() {
		return Insp_Lane;
	}

	public void setInsp_Lane(String insp_Lane) {
		Insp_Lane = insp_Lane;
	}

	public String getInspection_Item() {
		return Inspection_Item;
	}

	public void setInspection_Item(String inspection_Item) {
		Inspection_Item = inspection_Item;
	}

	public String getFrequency() {
		return Frequency;
	}

	public void setFrequency(String frequency) {
		Frequency = frequency;
	}

	public int getFreq_Num() {
		return Freq_Num;
	}

	public void setFreq_Num(int freq_Num) {
		Freq_Num = freq_Num;
	}

	public String getCheck_ItemName() {
		return Check_ItemName;
	}

	public void setCheck_ItemName(String check_ItemName) {
		Check_ItemName = check_ItemName;
	}

	public String getCheck_Req() {
		return Check_Req;
	}

	public void setCheck_Req(String check_Req) {
		Check_Req = check_Req;
	}

	public String getInsp_Result() {
		return Insp_Result;
	}

	public void setInsp_Result(String insp_Result) {
		Insp_Result = insp_Result;
	}

	public String getInsp_Memo() {
		return Insp_Memo;
	}

	public void setInsp_Memo(String insp_Memo) {
		Insp_Memo = insp_Memo;
	}

	public String getFaultReportID() {
		return FaultReportID==null?"":FaultReportID;
	}

	public void setFaultReportID(String faultReportID) {
		FaultReportID = faultReportID;
	}
}
