package com.goldsunny.itsm.model;

import java.util.Date;

public class MaintainPlanMDL {
	private String OID;
	private String PlannersID;
	private String Planners;
	private String PlaningDeptID;
	private Date PlaningDate;
	private String PlaningDept;
	private String PlaningYearMonth;
	private String PerformDeptID;
	private String PerformDept;
	private String PlanTitle;
	private String Remark;
	private String FlowStatus;
	private String NeedStartFlow;
	private int IsPlaceOnFile;
	private String OperatorID;
	private String OperatorName;
	private Date ApplyDate;
	private int Timestamp;
	private String PlanStatus;

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

	public String getPlaningDeptID() {
		return PlaningDeptID;
	}

	public void setPlaningDeptID(String planingDeptID) {
		PlaningDeptID = planingDeptID;
	}

	public Date getPlaningDate() {
		return PlaningDate;
	}

	public void setPlaningDate(Date planingDate) {
		PlaningDate = planingDate;
	}

	public String getPlaningDept() {
		return PlaningDept;
	}

	public void setPlaningDept(String planingDept) {
		PlaningDept = planingDept;
	}

	public String getPlaningYearMonth() {
		return PlaningYearMonth;
	}

	public void setPlaningYearMonth(String planingYearMonth) {
		PlaningYearMonth = planingYearMonth;
	}

	public String getPerformDeptID() {
		return PerformDeptID;
	}

	public void setPerformDeptID(String performDeptID) {
		PerformDeptID = performDeptID;
	}

	public String getPerformDept() {
		return PerformDept;
	}

	public void setPerformDept(String performDept) {
		PerformDept = performDept;
	}

	public String getPlanTitle() {
		return PlanTitle;
	}

	public void setPlanTitle(String planTitle) {
		PlanTitle = planTitle;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
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

	public String getPlanStatus() {
		return PlanStatus;
	}

	public void setPlanStatus(String planStatus) {
		PlanStatus = planStatus;
	}
}
