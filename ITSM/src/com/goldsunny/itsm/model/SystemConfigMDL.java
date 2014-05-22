package com.goldsunny.itsm.model;

import com.goldsunny.itsm.util.CommonClass;

public class SystemConfigMDL {
	private int CurrAreaNo;
	private int CurrRoadNo;
	private int CurrStationNo;
	private String AppVersionNo;
	private String DataBaseVersionNo;
	private String ServerIp;
	private String ServerWebservice;
	private String ServerWebsite;
	private String DefalutBTAddress;
	private boolean IsAutoMatch;
	private boolean IsTimeSyn;
	private String DownloadAPKUrl;
	private String WSServerIP;
	private String WSServerPort;
	private int DefalutLaneId;
	private String IP;
	private String WebSitePort;
	private boolean IsDataSyn;
	private int DelDataDay;

	// IsDataSyn
	public boolean getIsDataSyn() {
		return IsDataSyn;
	}

	public void setIsDataSyn(boolean val) {
		this.IsDataSyn = val;
	}

	// DelDataDay
	public int getDelDataDay() {
		return DelDataDay;
	}

	public void setDelDataDay(int val) {
		this.DelDataDay = val;
	}

	// DefalutLaneId
	public int getDefalutLaneId() {
		return DefalutLaneId;
	}

	public void setDefalutLaneId(int val) {
		this.DefalutLaneId = val;
	}

	// WebSitePort
	public String getWebSitePort() {
		return WebSitePort;
	}

	public void setWebSitePort(String val) {
		this.WebSitePort = val;
	}

	// WSServerIP
	public String getWSServerIP() {
		return WSServerIP;
	}

	public void setWSServerIP(String val) {
		this.WSServerIP = val;
	}

	// IP
	public String getIP() {
		return IP;
	}

	public void setIP(String val) {
		this.IP = val;
	}

	// WSServerPort
	public String getWSServerPort() {
		return WSServerPort;
	}

	public void setWSServerPort(String val) {
		this.WSServerPort = val;
	}

	// DownloadAPKUrl
	public String getDownloadAPKUrl() {
		return DownloadAPKUrl;
	}

	public void setDownloadAPKUrl(String val) {
		this.DownloadAPKUrl = val;
	}

	// DefalutBTAddress
	public String getDefalutBTAddress() {
		return DefalutBTAddress;
	}

	public void setDefalutBTAddress(String val) {
		this.DefalutBTAddress = val;
	}

	// CurrAreaNo
	public int getCurrAreaNo() {
		return CurrAreaNo;
	}

	public void setCurrAreaNo(int val) {
		this.CurrAreaNo = val;
	}

	// CurrStationNo
	public int getCurrStationNo() {
		return CurrStationNo;
	}

	public void setCurrStationNo(int val) {
		this.CurrStationNo = val;
	}

	// IsTimeSyn
	public boolean getIsTimeSyn() {
		return IsTimeSyn;
	}

	public void setIsTimeSyn(boolean val) {
		this.IsTimeSyn = val;
	}

	// IsAutoMatch
	public boolean getIsAutoMatch() {
		return IsAutoMatch;
	}

	public void setIsAutoMatch(boolean val) {
		this.IsAutoMatch = val;
	}

	// CurrRoadNo
	public int getCurrRoadNo() {
		return CurrRoadNo;
	}

	public void setCurrRoadNo(int val) {
		this.CurrRoadNo = val;
	}

	// AppVersionNo
	public String getAppVersionNo() {
		return AppVersionNo;
	}

	public void setAppVersionNo(String val) {
		this.AppVersionNo = val;
	}

	// ServerIp
	public String getServerIp() {
		return ServerIp;
	}

	public void setServerIp(String val) {
		this.ServerIp = val;
	}

	// ServerWebservice
	public String getServerWebservice() {
		ServerWebservice = "http://" + ServerIp + ":" + WSServerPort;
		return ServerWebservice;
	}
	
	/** 
	 * √Ë ˆ:  ªÒ»°Õ¯’æµÿ÷∑
	 * @return
	 */
	public String getServerWeb()
	{
		return "http://" + ServerIp + ":" + WebSitePort+"/";
	}

	public void setServerWebservice(String val) {
		this.ServerWebservice = val;
	}

	// ServerWebsite
	public String getServerWebsite() {
		ServerWebsite=	"http://" + ServerIp + ":" + WebSitePort;
		return ServerWebsite;
	}

	public void setServerWebsite(String val) {
		this.ServerWebsite = val;
	}

	// DataBaseVersionNo
	public String getDataBaseVersionNo() {
		return DataBaseVersionNo;
	}

	public void setDataBaseVersionNo(String val) {
		this.DataBaseVersionNo = val;
	}
}
