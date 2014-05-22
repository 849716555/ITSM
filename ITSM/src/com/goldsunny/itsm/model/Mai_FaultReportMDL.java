package com.goldsunny.itsm.model;

import java.util.Date;

/**     
* 类名称：Mai_FaultReportMDL
* 类描述：   故障报修实体类  
* 创建人：yangwy   
* 创建时间：2014-4-10 下午2:23:43     
* @version     
*/ 
public class Mai_FaultReportMDL {
	private String OID;
	private String RoadID;
	private String RoadName;
	private String LocationID;
	private String LocationName;
	private String Code;
	private String FaultType;
	private String FaultLevel;
	private String SystemClass;
	private String LocationDesc;
	private int PosMiles;
	private String OperatorID;
	private String OperatorName;
	private Date ApplyDate;
	private String Reportor;
	private Date ReportTime;
	private String ReportorTeamID;
	private String FaultSource;
	private Date FaultTime;
	private String FaultDesc;
	private String DeviceName;
	private String PreAdvice;
	private String BuStatus;
	private String MTeamID;
	private String MTeamName;
	private Date LimitTime;
	private String Remark;
	private String Status;
	private String RecoveryMainID;
	private String LocaleCheckID;
	private String FinalCheckID;
	private int IsPlaceOnFile;
	private int IsDownload;
	private String DeviceCode;
	private double X;
	private double Y;
	private int pageTotalCount;
	private int TotalPage;

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
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

	public String getLocationID() {
		return LocationID;
	}

	public void setLocationID(String locationID) {
		LocationID = locationID;
	}

	public String getLocationName() {
		return LocationName;
	}

	public void setLocationName(String locationName) {
		LocationName = locationName;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getFaultType() {
		return FaultType;
	}

	public void setFaultType(String faultType) {
		FaultType = faultType;
	}

	public String getFaultLevel() {
		return FaultLevel;
	}

	public void setFaultLevel(String faultLevel) {
		FaultLevel = faultLevel;
	}

	public String getSystemClass() {
		return SystemClass==null?"":SystemClass;
	}

	public void setSystemClass(String systemClass) {
		SystemClass = systemClass;
	}

	public String getLocationDesc() {
		return LocationDesc==null?"":LocationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		LocationDesc = locationDesc;
	}

	public int getPosMiles() {
		return PosMiles;
	}

	public void setPosMiles(int posMiles) {
		PosMiles = posMiles;
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

	public String getReportor() {
		return Reportor;
	}

	public void setReportor(String reportor) {
		Reportor = reportor;
	}

	public Date getReportTime() {
		return ReportTime;
	}

	public void setReportTime(Date reportTime) {
		ReportTime = reportTime;
	}

	public String getReportorTeamID() {
		return ReportorTeamID==null?"":ReportorTeamID;
	}

	public void setReportorTeamID(String reportorTeamID) {
		ReportorTeamID = reportorTeamID;
	}

	public String getFaultSource() {
		return FaultSource;
	}

	public void setFaultSource(String faultSource) {
		FaultSource = faultSource;
	}

	public Date getFaultTime() {
		return FaultTime;
	}

	public void setFaultTime(Date faultTime) {
		FaultTime = faultTime;
	}

	public String getFaultDesc() {
		return FaultDesc;
	}

	public void setFaultDesc(String faultDesc) {
		FaultDesc = faultDesc;
	}

	public String getDeviceName() {
		return DeviceName==null?"":DeviceName;
	}

	public void setDeviceName(String deviceName) {
		DeviceName = deviceName;
	}

	public String getPreAdvice() {
		return PreAdvice==null?"":PreAdvice;
	}

	public void setPreAdvice(String preAdvice) {
		PreAdvice = preAdvice;
	}

	public String getBuStatus() {
		return BuStatus;
	}

	public void setBuStatus(String buStatus) {
		BuStatus = buStatus;
	}

	public String getMTeamID() {
		return MTeamID==null?"":MTeamID;
	}

	public void setMTeamID(String mTeamID) {
		MTeamID = mTeamID;
	}

	public String getMTeamName() {
		return MTeamName==null?"":MTeamName;
	}

	public void setMTeamName(String mTeamName) {
		MTeamName = mTeamName;
	}

	public Date getLimitTime() {
		return LimitTime;
	}

	public void setLimitTime(Date limitTime) {
		LimitTime = limitTime;
	}

	public String getRemark() {
		return Remark==null?"":Remark;
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

	public String getRecoveryMainID() {
		return RecoveryMainID==null?"":RecoveryMainID;
	}

	public void setRecoveryMainID(String recoveryMainID) {
		RecoveryMainID = recoveryMainID;
	}

	public String getLocaleCheckID() {
		return LocaleCheckID==null?"":LocaleCheckID;
	}

	public void setLocaleCheckID(String localeCheckID) {
		LocaleCheckID = localeCheckID;
	}

	public String getFinalCheckID() {
		return FinalCheckID==null?"":FinalCheckID;
	}

	public void setFinalCheckID(String finalCheckID) {
		FinalCheckID = finalCheckID;
	}

	public int getIsPlaceOnFile() {
		return IsPlaceOnFile;
	}

	public void setIsPlaceOnFile(int isPlaceOnFile) {
		IsPlaceOnFile = isPlaceOnFile;
	}

	public int getIsDownload() {
		return IsDownload;
	}

	public void setIsDownload(int isDownload) {
		IsDownload = isDownload;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public String getDeviceCode() {
		return DeviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		DeviceCode = deviceCode;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public int getTotalPage() {
		return TotalPage;
	}

	public void setTotalPage(int totalPage) {
		TotalPage = totalPage;
	}

	
}
