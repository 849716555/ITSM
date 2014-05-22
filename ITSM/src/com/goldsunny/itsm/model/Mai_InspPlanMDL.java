package com.goldsunny.itsm.model;

import java.util.Date;

import android.R.integer;

public class Mai_InspPlanMDL {
	private String OID;
	private String PlannersID;
	private String Planners;
	private String Planing_DeptID;
	private String Planing_Dept;
	private Date Planing_Date;
	private Date Begin_Date;
	private Date End_Date;
	private String Plan_Title;
	private String Plan_Memo;
	private String Perform_DeptID;
	private String Perform_Dept;
	private String FlowStatus;
	private String NeedStartFlow;
	private int IsPlaceOnFile;
	private String OperatorID;
	private String OperatorName;
	private Date ApplyDate;
	private int Timestamp;
	private String PlanStatus;
	
	private int ItemCount;

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getPlannersID() {
		return PlannersID;
	}

	public void setPlannersID(String plannersID) {
		PlannersID = plannersID;
	}

	public String getPlanners() {
		return Planners;
	}

	public void setPlanners(String planners) {
		Planners = planners;
	}

	public String getPlaning_DeptID() {
		return Planing_DeptID;
	}

	public void setPlaning_DeptID(String planing_DeptID) {
		Planing_DeptID = planing_DeptID;
	}

	public String getPlaning_Dept() {
		return Planing_Dept;
	}

	public void setPlaning_Dept(String planing_Dept) {
		Planing_Dept = planing_Dept;
	}

	public Date getPlaning_Date() {
		return Planing_Date;
	}

	public void setPlaning_Date(Date planing_Date) {
		Planing_Date = planing_Date;
	}

	public Date getBegin_Date() {
		return Begin_Date;
	}

	public void setBegin_Date(Date begin_Date) {
		Begin_Date = begin_Date;
	}

	public Date getEnd_Date() {
		return End_Date;
	}

	public void setEnd_Date(Date end_Date) {
		End_Date = end_Date;
	}

	public String getPlan_Title() {
		return Plan_Title;
	}

	public void setPlan_Title(String plan_Title) {
		Plan_Title = plan_Title;
	}

	public String getPlan_Memo() {
		return Plan_Memo;
	}

	public void setPlan_Memo(String plan_Memo) {
		Plan_Memo = plan_Memo;
	}

	public String getPerform_DeptID() {
		return Perform_DeptID;
	}

	public void setPerform_DeptID(String perform_DeptID) {
		Perform_DeptID = perform_DeptID;
	}

	public String getPerform_Dept() {
		return Perform_Dept;
	}

	public void setPerform_Dept(String perform_Dept) {
		Perform_Dept = perform_Dept;
	}

	public String getFlowStatus() {
		return FlowStatus;
	}

	public void setFlowStatus(String flowStatus) {
		FlowStatus = flowStatus;
	}

	public String getNeedStartFlow() {
		return NeedStartFlow;
	}

	public void setNeedStartFlow(String needStartFlow) {
		NeedStartFlow = needStartFlow;
	}

	public int getIsPlaceOnFile() {
		return IsPlaceOnFile;
	}

	public void setIsPlaceOnFile(int isPlaceOnFile) {
		IsPlaceOnFile = isPlaceOnFile;
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

	public int getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(int timestamp) {
		Timestamp = timestamp;
	}

	public int getItemCount() {
		return ItemCount;
	}

	public void setItemCount(int itemCount) {
		ItemCount = itemCount;
	}

	public String getPlanStatus() {
		return PlanStatus;
	}

	public void setPlanStatus(String planStatus) {
		PlanStatus = planStatus;
	}
}
