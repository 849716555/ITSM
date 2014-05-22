package com.goldsunny.itsm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.goldsunny.itsm.model.EmployeeMDL;
import com.goldsunny.itsm.model.Equ_AssetMDL;
import com.goldsunny.itsm.model.Equ_EquToThingMDL;
import com.goldsunny.itsm.model.Equ_EquipmentMDL;
import com.goldsunny.itsm.model.Equ_LifeLogMDL;
import com.goldsunny.itsm.model.MTeamLocationMDL;
import com.goldsunny.itsm.model.MTeamPersonMDL;
import com.goldsunny.itsm.model.Mai_FaultReportMDL;
import com.goldsunny.itsm.model.Mai_InspEquMDL;
import com.goldsunny.itsm.model.Mai_InspItemMDL;
import com.goldsunny.itsm.model.Mai_InspPLanDetailMDL;
import com.goldsunny.itsm.model.Mai_InspPlanMDL;
import com.goldsunny.itsm.model.Mai_InspRecordDetailMDL;
import com.goldsunny.itsm.model.Mai_InspRecordMDL;
import com.goldsunny.itsm.model.Mai_InspTypeMDL;
import com.goldsunny.itsm.model.Mai_NeedEquipmentMDL;
import com.goldsunny.itsm.model.Mai_RecoveryDetailMDL;
import com.goldsunny.itsm.model.Mai_RecoveryMainMDL;
import com.goldsunny.itsm.model.MaintainItemMDL;
import com.goldsunny.itsm.model.MaintainPlanDetailMDL;
import com.goldsunny.itsm.model.MaintainPlanMDL;
import com.goldsunny.itsm.model.MaintainRecordDetailMDL;
import com.goldsunny.itsm.model.MaintainRecordMDL;
import com.goldsunny.itsm.model.MaintainStepMDL;
import com.goldsunny.itsm.model.MaintainTeamMDL;
import com.goldsunny.itsm.model.MaintainTypeMDL;
import com.goldsunny.itsm.model.NeedMaintainEQUMDL;
import com.goldsunny.itsm.model.RealMaintainEQUMDL;
import com.goldsunny.itsm.model.StockMDL;
import com.goldsunny.itsm.model.StoreMDL;
import com.goldsunny.itsm.model.Sys_DictListMDL;
import com.goldsunny.itsm.model.Sys_FileMDL;
import com.goldsunny.itsm.model.Syst_LocationMDL;
import com.goldsunny.itsm.model.Syst_RoadMDL;

public class Json2EntitiesUtil {
	public static List<Equ_EquipmentMDL> GetEquipments(JSONArray jsonArray) {
		List<Equ_EquipmentMDL> datas = new LinkedList<Equ_EquipmentMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Equ_EquipmentMDL data = new Equ_EquipmentMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setThingID(JsonHelper.GetString(jsonObject2, "ThingID"));
				data.setCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setName(JsonHelper.GetString(jsonObject2, "Name"));
				data.setSeqCode(JsonHelper.GetString(jsonObject2, "SeqCode"));
				data.setUnitType(JsonHelper.GetString(jsonObject2, "UnitType"));
				data.setUnitName(JsonHelper.GetString(jsonObject2, "UnitName"));
				data.setBrandID(JsonHelper.GetString(jsonObject2, "BrandID"));
				data.setBrandName(JsonHelper.GetString(jsonObject2, "BrandName"));
				data.setModel(JsonHelper.GetString(jsonObject2, "Model"));
				data.setTechnicalParam(JsonHelper.GetString(jsonObject2, "TechnicalParam"));
				data.setLeadingFeatures(JsonHelper.GetString(jsonObject2, "LeadingFeatures"));
				data.setMainApplication(JsonHelper.GetString(jsonObject2, "MainApplication"));
				data.setPeripheralInterface(JsonHelper.GetString(jsonObject2, "PeripheralInterface"));
				data.setOtherTechnicalParam(JsonHelper.GetString(jsonObject2, "OtherTechnicalParam"));
				data.setAssetID(JsonHelper.GetString(jsonObject2, "AssetID"));
				data.setSupplyCompanyID(JsonHelper.GetString(jsonObject2, "SupplyCompanyID"));
				data.setSupplyCompanyName(JsonHelper.GetString(jsonObject2, "SupplyCompanyName"));
				data.setFactoryID(JsonHelper.GetString(jsonObject2, "FactoryID"));
				data.setFactoryName(JsonHelper.GetString(jsonObject2, "FactoryName"));
				data.setUnitPrice(JsonHelper.GetDouble(jsonObject2, "UnitPrice"));
				data.setBuyDate(JsonHelper.GetDate(jsonObject2, "BuyDate"));
				data.setInstallDate(JsonHelper.GetDate(jsonObject2, "InstallDate"));
				data.setBeginUseDate(JsonHelper.GetDate(jsonObject2, "BeginUseDate"));
				data.setMatchOperator(JsonHelper.GetString(jsonObject2, "MatchOperator"));
				data.setLimitMonthNumber(JsonHelper.GetInt(jsonObject2, "LimitMonthNumber"));
				data.setDepreciationType(JsonHelper.GetString(jsonObject2, "DepreciationType"));
				data.setDepreciationName(JsonHelper.GetString(jsonObject2, "DepreciationName"));
				data.setCompanyID(JsonHelper.GetString(jsonObject2, "CompanyID"));
				data.setRoadID(JsonHelper.GetString(jsonObject2, "RoadID"));
				data.setRoadName(JsonHelper.GetString(jsonObject2, "RoadName"));
				data.setManagerID(JsonHelper.GetString(jsonObject2, "ManagerID"));
				data.setManager(JsonHelper.GetString(jsonObject2, "Manager"));
				data.setUserID(JsonHelper.GetString(jsonObject2, "UserID"));
				data.setUserName(JsonHelper.GetString(jsonObject2, "UserName"));
				data.setDepartmentID(JsonHelper.GetString(jsonObject2, "DepartmentID"));
				data.setDepartment(JsonHelper.GetString(jsonObject2, "Department"));
				data.setLocationID(JsonHelper.GetString(jsonObject2, "LocationID"));
				data.setLocationName(JsonHelper.GetString(jsonObject2, "LocationName"));
				data.setPosDescription(JsonHelper.GetString(jsonObject2, "PosDescription"));
				data.setSystemClass(JsonHelper.GetString(jsonObject2, "SystemClass"));
				data.setBuStatus(JsonHelper.GetString(jsonObject2, "BuStatus"));
				data.setRemark(JsonHelper.GetString(jsonObject2, "Remark"));
				data.setStatus(JsonHelper.GetString(jsonObject2, "Status"));
				data.setSeq(JsonHelper.GetInt(jsonObject2, "Seq"));
				data.setAccountCode(JsonHelper.GetString(jsonObject2, "AccountCode"));
				data.setTimestamp(JsonHelper.GetInt(jsonObject2, "Timestamp"));
				data.setNumber(JsonHelper.GetInt(jsonObject2, "Number"));
				data.setPageTotalCount(JsonHelper.GetInt(jsonObject2, "pageTotalCount"));
				data.setTotalPage(JsonHelper.GetInt(jsonObject2, "TotalPage"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Equ_EquipmentMDL>();
	}
	public static List<EmployeeMDL> GetEmployees(JSONArray jsonArray) {
		List<EmployeeMDL> datas = new LinkedList<EmployeeMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				EmployeeMDL data = new EmployeeMDL();
				data.setID(JsonHelper.GetString(jsonObject2, "ID"));
				data.setEmplCode(JsonHelper.GetString(jsonObject2, "EmplCode"));
				data.setEmplName(JsonHelper.GetString(jsonObject2, "EmplName"));
				data.setEmplState(JsonHelper.GetString(jsonObject2, "EmplState"));
				data.setIsOperator(JsonHelper.GetString(jsonObject2, "IsOperator"));
				data.setPassWord(JsonHelper.GetString(jsonObject2, "PassWord"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<EmployeeMDL>();
	}
	public static List<Equ_AssetMDL> GetEqu_Assets(JSONArray jsonArray) {
		List<Equ_AssetMDL> datas = new LinkedList<Equ_AssetMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Equ_AssetMDL data = new Equ_AssetMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setAssetStandardID(JsonHelper.GetString(jsonObject2, "AssetStandardID"));
				data.setAssetClassID(JsonHelper.GetString(jsonObject2, "AssetClassID"));
				data.setAssetClassName(JsonHelper.GetString(jsonObject2, "AssetClassName"));
				data.setAssetClassCode(JsonHelper.GetString(jsonObject2, "AssetClassCode"));
				data.setRoadID(JsonHelper.GetString(jsonObject2, "RoadID"));
				data.setRoadName(JsonHelper.GetString(jsonObject2, "RoadName"));
				data.setCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setName(JsonHelper.GetString(jsonObject2, "Name"));
				data.setBrandName(JsonHelper.GetString(jsonObject2, "BrandName"));
				data.setModel(JsonHelper.GetString(jsonObject2, "Model"));
				data.setLocationName(JsonHelper.GetString(jsonObject2, "LocationName"));
				data.setLocationID(JsonHelper.GetString(jsonObject2, "LocationID"));
				data.setUnitName(JsonHelper.GetString(jsonObject2, "UnitName"));
				data.setManagerDeptID(JsonHelper.GetString(jsonObject2, "ManagerDeptID"));
				data.setManagerDeptName(JsonHelper.GetString(jsonObject2, "ManagerDeptName"));
				data.setUserDepartmentID(JsonHelper.GetString(jsonObject2, "UserDepartmentID"));
				data.setUserDepartmentName(JsonHelper.GetString(jsonObject2, "UserDepartmentName"));
				data.setUserID(JsonHelper.GetString(jsonObject2, "UserID"));
				data.setUserName(JsonHelper.GetString(jsonObject2, "UserName"));
				data.setProducePath(JsonHelper.GetString(jsonObject2, "ProducePath"));
				data.setFinanceCode(JsonHelper.GetString(jsonObject2, "FinanceCode"));
				data.setProduceDate(JsonHelper.GetDate(jsonObject2, "ProduceDate"));
				data.setStartUsingDate(JsonHelper.GetDate(jsonObject2, "StartUsingDate"));
				data.setTotalassetsOld(JsonHelper.GetDouble(jsonObject2, "TotalassetsOld"));
				data.setHasUseMonth(JsonHelper.GetInt(jsonObject2, "HasUseMonth"));
				data.setAccumDepletion(JsonHelper.GetString(jsonObject2, "AccumDepletion"));
				data.setNetWorth(JsonHelper.GetDouble(jsonObject2, "NetWorth"));
				data.setAssetState(JsonHelper.GetString(jsonObject2, "AssetState"));
				data.setRepairCost(JsonHelper.GetDouble(jsonObject2, "RepairCost"));
				data.setRemark(JsonHelper.GetString(jsonObject2, "Remark"));
				data.setStatus(JsonHelper.GetString(jsonObject2, "Status"));
				data.setSeq(JsonHelper.GetInt(jsonObject2, "Seq"));
				data.setCreateTime(JsonHelper.GetDate(jsonObject2, "CreateTime"));
				data.setOperator(JsonHelper.GetString(jsonObject2, "Operator"));
				data.setDepreciationName(JsonHelper.GetString(jsonObject2, "DepreciationName"));
				data.setDepreciationType(JsonHelper.GetString(jsonObject2, "DepreciationType"));
				data.setTimestamp(JsonHelper.GetInt(jsonObject2, "Timestamp"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Equ_AssetMDL>();
	}
	public static List<Equ_EquToThingMDL> GetEquToThings(JSONArray jsonArray) {
		List<Equ_EquToThingMDL> datas = new LinkedList<Equ_EquToThingMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Equ_EquToThingMDL data = new Equ_EquToThingMDL();
				data.setApplyDate(JsonHelper.GetDate(jsonObject2, "ApplyDate"));
				data.setBrandName(JsonHelper.GetString(jsonObject2, "BrandName"));
				data.setCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setEquipmentID(JsonHelper.GetString(jsonObject2, "EquipmentID"));
				data.setModel(JsonHelper.GetString(jsonObject2, "Model"));
				data.setName(JsonHelper.GetString(jsonObject2, "Name"));
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setOperatorID(JsonHelper.GetString(jsonObject2, "OperatorID"));
				data.setOperatorName(JsonHelper.GetString(jsonObject2, "OperatorName"));
				data.setUnitName(JsonHelper.GetString(jsonObject2, "UnitName"));
				data.setTimestamp(JsonHelper.GetInt(jsonObject2, "Timestamp"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Equ_EquToThingMDL>();
	}
	public static List<Equ_LifeLogMDL> GetEqu_LifeLogs(JSONArray jsonArray) {
		List<Equ_LifeLogMDL> datas = new LinkedList<Equ_LifeLogMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Equ_LifeLogMDL data = new Equ_LifeLogMDL();
				data.setEquipmentStatus(JsonHelper.GetString(jsonObject2, "EquipmentStatus"));
				data.setInTime(JsonHelper.GetDate(jsonObject2, "InTime"));
				data.setKeyDesc(JsonHelper.GetString(jsonObject2, "KeyDesc"));
				data.setEquipmentID(JsonHelper.GetString(jsonObject2, "EquipmentID"));
				data.setLiftDesc(JsonHelper.GetString(jsonObject2, "LiftDesc"));
				data.setOccuDate(JsonHelper.GetDate(jsonObject2, "OccuDate"));
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setReceiptDetailID(JsonHelper.GetString(jsonObject2, "ReceiptDetailID"));
				data.setReceiptMainID(JsonHelper.GetString(jsonObject2, "ReceiptMainID"));
				data.setReceiptType(JsonHelper.GetString(jsonObject2, "ReceiptType"));
				data.setSeq(JsonHelper.GetInt(jsonObject2, "Seq"));
				data.setTimestamp(JsonHelper.GetInt(jsonObject2, "Timestamp"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Equ_LifeLogMDL>();
	}
	public static List<Sys_DictListMDL> GetDictLists(JSONArray jsonArray) {
		List<Sys_DictListMDL> datas = new LinkedList<Sys_DictListMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Sys_DictListMDL data = new Sys_DictListMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setSys_DictOID(JsonHelper.GetString(jsonObject2, "Sys_DictOID"));
				data.setListName(JsonHelper.GetString(jsonObject2, "ListName"));
				data.setIsDefault(JsonHelper.GetString(jsonObject2, "IsDefault"));
				data.setIsValid(JsonHelper.GetString(jsonObject2, "IsValid"));
				data.setSortCode(JsonHelper.GetDouble(jsonObject2, "SortCode"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Sys_DictListMDL>();
	}
	public static List<Syst_LocationMDL> GetLocations(JSONArray jsonArray) {
		List<Syst_LocationMDL> datas = new LinkedList<Syst_LocationMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Syst_LocationMDL data = new Syst_LocationMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setBeginMiles(JsonHelper.GetDouble(jsonObject2, "BeginMiles"));
				data.setCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setEndMiles(JsonHelper.GetDouble(jsonObject2, "EndMiles"));
				data.setLocationDetail(JsonHelper.GetString(jsonObject2, "LocationDetail"));
				data.setName(JsonHelper.GetString(jsonObject2, "Name"));
				data.setLevel(JsonHelper.GetInt(jsonObject2, "Level"));
				data.setPID(JsonHelper.GetString(jsonObject2, "PID"));
				data.setPosType(JsonHelper.GetString(jsonObject2, "PosType"));
				data.setRemark(JsonHelper.GetString(jsonObject2, "Remark"));
				data.setRoadID(JsonHelper.GetString(jsonObject2, "RoadID"));
				data.setRoadName(JsonHelper.GetString(jsonObject2, "RoadName"));
				data.setSeq(JsonHelper.GetDouble(jsonObject2, "Seq"));
				data.setSons(JsonHelper.GetInt(jsonObject2, "Sons"));
				data.setStatus(JsonHelper.GetString(jsonObject2, "Status"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Syst_LocationMDL>();
	}
	public static List<Syst_RoadMDL> GetRoads(JSONArray jsonArray) {
		List<Syst_RoadMDL> datas = new LinkedList<Syst_RoadMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Syst_RoadMDL data = new Syst_RoadMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setCompanyID(JsonHelper.GetString(jsonObject2, "CompanyID"));
				data.setCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setCompanyName(JsonHelper.GetString(jsonObject2, "CompanyName"));
				data.setShortName(JsonHelper.GetString(jsonObject2, "ShortName"));
				data.setName(JsonHelper.GetString(jsonObject2, "Name"));
				data.setSeq(JsonHelper.GetDouble(jsonObject2, "Seq"));
				data.setStatus(JsonHelper.GetString(jsonObject2, "Status"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Syst_RoadMDL>();
	}
	public static List<MTeamPersonMDL> GetMTeamPersons(JSONArray jsonArray) {
		List<MTeamPersonMDL> datas = new LinkedList<MTeamPersonMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				MTeamPersonMDL data = new MTeamPersonMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setMTeamID(JsonHelper.GetString(jsonObject2, "MTeamID"));
				data.setEmployeeID(JsonHelper.GetString(jsonObject2, "EmployeeID"));
				data.setCanSelectFinalCheck(JsonHelper.GetInt(jsonObject2, "CanSelectFinalCheck"));
				data.setCanReport(JsonHelper.GetInt(jsonObject2, "CanReport"));
				data.setCanMT(JsonHelper.GetInt(jsonObject2, "CanMT"));
				data.setCanLocaleCheck(JsonHelper.GetInt(jsonObject2, "CanLocaleCheck"));
				data.setCanFinalCheck(JsonHelper.GetInt(jsonObject2, "CanFinalCheck"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<MTeamPersonMDL>();
	}
	public static List<MTeamLocationMDL> GetMTeamLocation(JSONArray jsonArray) {
		List<MTeamLocationMDL> datas = new LinkedList<MTeamLocationMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				MTeamLocationMDL data = new MTeamLocationMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setMTeamID(JsonHelper.GetString(jsonObject2, "MTeamID"));
				data.setLocationID(JsonHelper.GetString(jsonObject2, "LocationID"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<MTeamLocationMDL>();
	}
	public static List<MaintainTeamMDL> GetMaintainTeams(JSONArray jsonArray) {
		List<MaintainTeamMDL> datas = new LinkedList<MaintainTeamMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				MaintainTeamMDL data = new MaintainTeamMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setCompanyID(JsonHelper.GetString(jsonObject2, "CompanyID"));
				data.setCompanyName(JsonHelper.GetString(jsonObject2, "CompanyName"));
				data.setContacts(JsonHelper.GetString(jsonObject2, "Contacts"));
				data.setName(JsonHelper.GetString(jsonObject2, "Name"));
				data.setPhone(JsonHelper.GetString(jsonObject2, "Phone"));
				data.setRemark(JsonHelper.GetString(jsonObject2, "Remark"));
				data.setSeq(JsonHelper.GetDouble(jsonObject2, "Seq"));
				data.setStatus(JsonHelper.GetString(jsonObject2, "Status"));
				data.setStaionId(JsonHelper.GetString(jsonObject2, "StationID"));
				data.setStaionName(JsonHelper.GetString(jsonObject2, "StationName"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<MaintainTeamMDL>();
	}
	public static List<Mai_FaultReportMDL> GetFaultReports(JSONArray jsonArray) {
		List<Mai_FaultReportMDL> datas = new LinkedList<Mai_FaultReportMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Mai_FaultReportMDL data = new Mai_FaultReportMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setApplyDate(JsonHelper.GetDate(jsonObject2, "ApplyDate"));
				data.setCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setDeviceName(JsonHelper.GetString(jsonObject2, "DeviceName"));
				data.setFaultDesc(JsonHelper.GetString(jsonObject2, "FaultDesc"));
				data.setFaultLevel(JsonHelper.GetString(jsonObject2, "FaultLevel"));
				data.setFaultSource(JsonHelper.GetString(jsonObject2, "FaultSource"));
				data.setFaultTime(JsonHelper.GetDate(jsonObject2, "FaultTime"));
				data.setFaultType(JsonHelper.GetString(jsonObject2, "FaultType"));
				data.setFinalCheckID(JsonHelper.GetString(jsonObject2, "FinalCheckID"));
				data.setIsPlaceOnFile(JsonHelper.GetInt(jsonObject2, "IsPlaceOnFile"));
				data.setLimitTime(JsonHelper.GetDate(jsonObject2, "LimitTime"));
				data.setLocaleCheckID(JsonHelper.GetString(jsonObject2, "LocaleCheckID"));
				data.setLocationDesc(JsonHelper.GetString(jsonObject2, "LocationDesc"));
				data.setMTeamID(JsonHelper.GetString(jsonObject2, "MTeamID"));
				data.setMTeamName(JsonHelper.GetString(jsonObject2, "MTeamName"));
				data.setOperatorID(JsonHelper.GetString(jsonObject2, "OperatorID"));
				data.setOperatorName(JsonHelper.GetString(jsonObject2, "OperatorName"));
				//data.setPosMiles(JsonHelper.GetInt(jsonObject2, "PosMiles"));
				data.setPosMiles(0);
				data.setPreAdvice(JsonHelper.GetString(jsonObject2, "PreAdvice"));
				data.setRecoveryMainID(JsonHelper.GetString(jsonObject2, "RecoveryMainID"));
				data.setReportor(JsonHelper.GetString(jsonObject2, "Reportor"));
				data.setReportorTeamID(JsonHelper.GetString(jsonObject2, "ReportorTeamID"));
				data.setReportTime(JsonHelper.GetDate(jsonObject2, "ReportTime"));
				data.setRoadID(JsonHelper.GetString(jsonObject2, "RoadID"));
				data.setRoadName(JsonHelper.GetString(jsonObject2, "RoadName"));
				data.setLocationID(JsonHelper.GetString(jsonObject2, "LocationID"));
				data.setLocationName(JsonHelper.GetString(jsonObject2, "LocationName"));
				data.setSystemClass(JsonHelper.GetString(jsonObject2, "SystemClass"));
				data.setBuStatus(JsonHelper.GetString(jsonObject2, "BuStatus"));
				data.setRemark(JsonHelper.GetString(jsonObject2, "Remark"));
				data.setStatus(JsonHelper.GetString(jsonObject2, "Status"));
				data.setDeviceCode(JsonHelper.GetString(jsonObject2, "deviceCode"));
				data.setPageTotalCount(JsonHelper.GetInt(jsonObject2, "pageTotalCount"));
				data.setTotalPage(JsonHelper.GetInt(jsonObject2, "TotalPage"));
				data.setX(JsonHelper.GetDouble(jsonObject2, "X"));
				data.setY(JsonHelper.GetDouble(jsonObject2, "Y"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Mai_FaultReportMDL>();
	}
	public static List<Mai_RecoveryMainMDL> GetRecoveryMains(JSONArray jsonArray) {
		List<Mai_RecoveryMainMDL> datas = new LinkedList<Mai_RecoveryMainMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Mai_RecoveryMainMDL data = new Mai_RecoveryMainMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setApplyDate(JsonHelper.GetDate(jsonObject2, "ApplyDate"));
				data.setCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setActualRecoveryTime(JsonHelper.GetDate(jsonObject2, "ActualRecoveryTime"));
				data.setCalculatePrice(JsonHelper.GetDouble(jsonObject2, "CalculatePrice"));
				data.setFaultReason(JsonHelper.GetString(jsonObject2, "FaultReason"));
				data.setFaultReportID(JsonHelper.GetString(jsonObject2, "FaultReportID"));
				data.setMTeamMan(JsonHelper.GetString(jsonObject2, "MTeamMan"));
				data.setPlanRecoveryTime(JsonHelper.GetDate(jsonObject2, "PlanRecoveryTime"));
				data.setRecTime(JsonHelper.GetDate(jsonObject2, "RecTime"));
				data.setIsPlaceOnFile(JsonHelper.GetInt(jsonObject2, "IsPlaceOnFile"));
				data.setRepairPrice(JsonHelper.GetDouble(jsonObject2, "RepairPrice"));
				data.setSolution(JsonHelper.GetString(jsonObject2, "Solution"));
				data.setMTeamID(JsonHelper.GetString(jsonObject2, "MTeamID"));
				data.setMTeamName(JsonHelper.GetString(jsonObject2, "MTeamName"));
				data.setOperatorID(JsonHelper.GetString(jsonObject2, "OperatorID"));
				data.setOperatorName(JsonHelper.GetString(jsonObject2, "OperatorName"));
				data.setRoadID(JsonHelper.GetString(jsonObject2, "RoadID"));
				data.setRoadName(JsonHelper.GetString(jsonObject2, "RoadName"));
				data.setBuStatus(JsonHelper.GetString(jsonObject2, "BuStatus"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Mai_RecoveryMainMDL>();
	}
	
	public static List<Mai_RecoveryDetailMDL> GetRecoveryDetails(JSONArray jsonArray) {
		List<Mai_RecoveryDetailMDL> datas = new LinkedList<Mai_RecoveryDetailMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Mai_RecoveryDetailMDL data = new Mai_RecoveryDetailMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setBrandName(JsonHelper.GetString(jsonObject2, "BrandName"));
				data.setCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setEquipmentID(JsonHelper.GetString(jsonObject2, "EquipmentID"));
				data.setFaultThingID(JsonHelper.GetString(jsonObject2, "ThingID"));
				data.setModel(JsonHelper.GetString(jsonObject2, "Model"));
				data.setName(JsonHelper.GetString(jsonObject2, "Name"));
				data.setNumber(JsonHelper.GetInt(jsonObject2, "Number"));
				data.setRecoveryMainID(JsonHelper.GetString(jsonObject2, "RecoveryMainID"));
				data.setRecoveryMode(JsonHelper.GetString(jsonObject2, "RecoveryMode"));
				data.setRemark(JsonHelper.GetString(jsonObject2, "Remark"));
				data.setReplaceCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setReplaceEquipmentID(JsonHelper.GetString(jsonObject2, "ReplaceEquipmentID"));
				data.setReplaceName(JsonHelper.GetString(jsonObject2, "ReplaceName"));
				data.setReplaceThingID(JsonHelper.GetString(jsonObject2, "ReplaceThingID"));
				data.setSeq(JsonHelper.GetDouble(jsonObject2, "Seq"));
				data.setThingID(JsonHelper.GetString(jsonObject2, "ThingID"));  
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Mai_RecoveryDetailMDL>();
	}
	
	public static List<Mai_InspTypeMDL> GetInspTypes(JSONArray jsonArray) {
		List<Mai_InspTypeMDL> datas = new LinkedList<Mai_InspTypeMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Mai_InspTypeMDL data = new Mai_InspTypeMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setPID(JsonHelper.GetString(jsonObject2, "PID"));
				data.setCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setName(JsonHelper.GetString(jsonObject2, "Name"));
				data.setStatus(JsonHelper.GetString(jsonObject2, "Status"));
				data.setSeq(JsonHelper.GetDouble(jsonObject2, "Seq"));
				
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Mai_InspTypeMDL>();
	}
	public static List<Mai_InspItemMDL> GetInspItems(JSONArray jsonArray) {
		List<Mai_InspItemMDL> datas = new LinkedList<Mai_InspItemMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Mai_InspItemMDL data = new Mai_InspItemMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setInspectionID(JsonHelper.GetString(jsonObject2, "InspectionID"));
				data.setInspectionItem(JsonHelper.GetString(jsonObject2, "InspectionItem"));
				data.setFrequency(JsonHelper.GetString(jsonObject2, "Frequency"));
				data.setFreqNum(JsonHelper.GetInt(jsonObject2, "FreqNum"));
				data.setCheckItemName(JsonHelper.GetString(jsonObject2, "CheckItemName"));
				data.setCheckReq(JsonHelper.GetString(jsonObject2, "CheckReq"));
				data.setStatus(JsonHelper.GetString(jsonObject2, "Status"));
				data.setSeq(JsonHelper.GetDouble(jsonObject2, "Seq"));
				
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Mai_InspItemMDL>();
	}
	public static List<Mai_InspPlanMDL> GetInspPlans(JSONArray jsonArray) {
		List<Mai_InspPlanMDL> datas = new LinkedList<Mai_InspPlanMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Mai_InspPlanMDL data = new Mai_InspPlanMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setPlannersID(JsonHelper.GetString(jsonObject2, "PlannersID"));
				data.setPlanners(JsonHelper.GetString(jsonObject2, "Planners"));
				data.setPlaning_DeptID(JsonHelper.GetString(jsonObject2, "PlaningDeptID"));
				data.setPlaning_Dept(JsonHelper.GetString(jsonObject2, "PlaningDept"));
				data.setPlaning_Date(JsonHelper.GetDate(jsonObject2, "PlaningDate"));
				data.setBegin_Date(JsonHelper.GetDate(jsonObject2, "BeginDate"));
				data.setEnd_Date(JsonHelper.GetDate(jsonObject2, "EndDate"));
				data.setPlan_Title(JsonHelper.GetString(jsonObject2, "PlanTitle"));
				data.setPlan_Memo(JsonHelper.GetString(jsonObject2, "PlanMemo"));
				data.setPerform_DeptID(JsonHelper.GetString(jsonObject2, "PerformDeptID"));
				data.setPerform_Dept(JsonHelper.GetString(jsonObject2, "PerformDept"));
				data.setFlowStatus(JsonHelper.GetString(jsonObject2, "FlowStatus"));
				data.setNeedStartFlow(JsonHelper.GetString(jsonObject2, "NeedStartFlow"));
				data.setIsPlaceOnFile(JsonHelper.GetInt(jsonObject2, "IsPlaceOnFile"));
				data.setOperatorID(JsonHelper.GetString(jsonObject2, "OperatorID"));
				data.setOperatorName(JsonHelper.GetString(jsonObject2, "OperatorName"));
				data.setApplyDate(JsonHelper.GetDate(jsonObject2, "ApplyDate"));
				data.setTimestamp(JsonHelper.GetInt(jsonObject2, "Timestamp"));
				
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Mai_InspPlanMDL>();
	}
	public static List<Mai_InspPLanDetailMDL> GetInspPlanDetails(JSONArray jsonArray) {
		List<Mai_InspPLanDetailMDL> datas = new LinkedList<Mai_InspPLanDetailMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Mai_InspPLanDetailMDL data = new Mai_InspPLanDetailMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setInsp_PlanID(JsonHelper.GetString(jsonObject2, "InspPlanID"));
				data.setInsp_ItemID(JsonHelper.GetString(jsonObject2, "InspItemID"));
				data.setInspection_Item(JsonHelper.GetString(jsonObject2, "InspectionItem"));
				data.setFrequency(JsonHelper.GetString(jsonObject2, "Frequency"));
				data.setFreq_Num(JsonHelper.GetInt(jsonObject2, "FreqNum"));
				data.setCheck_ItemName(JsonHelper.GetString(jsonObject2, "CheckItemName"));
				data.setCheck_Req(JsonHelper.GetString(jsonObject2, "CheckReq"));
				
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Mai_InspPLanDetailMDL>();
	}
	public static List<Mai_NeedEquipmentMDL> GetInspNeedEqus(JSONArray jsonArray) {
		List<Mai_NeedEquipmentMDL> datas = new LinkedList<Mai_NeedEquipmentMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Mai_NeedEquipmentMDL data = new Mai_NeedEquipmentMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setThingID(JsonHelper.GetString(jsonObject2, "ThingID"));
				data.setInspPlanDetailID(JsonHelper.GetString(jsonObject2, "InspPlanDetailID"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<Mai_NeedEquipmentMDL>();
	}
	public static List<MaintainTypeMDL> GetMTType(JSONArray jsonArray) {
		List<MaintainTypeMDL> datas = new LinkedList<MaintainTypeMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				MaintainTypeMDL data = new MaintainTypeMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setPID(JsonHelper.GetString(jsonObject2, "PID"));
				data.setCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setName(JsonHelper.GetString(jsonObject2, "Name"));
				data.setStatus(JsonHelper.GetString(jsonObject2, "Status"));
				data.setSeq(JsonHelper.GetDouble(jsonObject2, "Seq"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<MaintainTypeMDL>();
	}
	public static List<MaintainItemMDL> GetMTItem(JSONArray jsonArray) {
		List<MaintainItemMDL> datas = new LinkedList<MaintainItemMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				MaintainItemMDL data = new MaintainItemMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setMaintainID(JsonHelper.GetString(jsonObject2, "MaintainID"));
				data.setMaintainItem(JsonHelper.GetString(jsonObject2, "MaintainItem"));
				data.setFrequency(JsonHelper.GetString(jsonObject2, "Frequency"));
				data.setFreqNum(JsonHelper.GetInt(jsonObject2, "FreqNum"));
				data.setMaiclaim(JsonHelper.GetString(jsonObject2, "Maiclaim"));
				data.setStatus(JsonHelper.GetString(jsonObject2, "Status"));
				data.setSeq(JsonHelper.GetDouble(jsonObject2, "Seq"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<MaintainItemMDL>();
	}
	public static List<MaintainStepMDL> GetMTStep(JSONArray jsonArray) {
		List<MaintainStepMDL> datas = new LinkedList<MaintainStepMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				MaintainStepMDL data = new MaintainStepMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setMaintainItemID(JsonHelper.GetString(jsonObject2, "MaintainItemID"));
				data.setMaintainStep(JsonHelper.GetString(jsonObject2, "MaintainStep"));
				data.setSeq(JsonHelper.GetDouble(jsonObject2, "Seq"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<MaintainStepMDL>();
	}
	public static List<MaintainPlanMDL> GetMTPlan(JSONArray jsonArray) {
		List<MaintainPlanMDL> datas = new LinkedList<MaintainPlanMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				MaintainPlanMDL data = new MaintainPlanMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setPlannersID(JsonHelper.GetString(jsonObject2, "PlannersID"));
				data.setPlanners(JsonHelper.GetString(jsonObject2, "Planners"));
				data.setPlaningDeptID(JsonHelper.GetString(jsonObject2, "PlaningDeptID"));
				data.setPlaningDate(JsonHelper.GetDate(jsonObject2, "PlaningDate"));
				data.setPlaningDept(JsonHelper.GetString(jsonObject2, "PlaningDept"));
				data.setPlaningYearMonth(JsonHelper.GetString(jsonObject2, "PlaningYearMonth"));
				data.setPerformDeptID(JsonHelper.GetString(jsonObject2, "PerformDeptID"));
				data.setPerformDept(JsonHelper.GetString(jsonObject2, "PerformDept"));
				data.setPlanTitle(JsonHelper.GetString(jsonObject2, "PlanTitle"));
				data.setRemark(JsonHelper.GetString(jsonObject2, "Remark"));
				data.setFlowStatus(JsonHelper.GetString(jsonObject2, "FlowStatus"));
				data.setNeedStartFlow(JsonHelper.GetString(jsonObject2, "NeedStartFlow"));
				data.setIsPlaceOnFile(JsonHelper.GetInt(jsonObject2, "IsPlaceOnFile"));
				data.setOperatorID(JsonHelper.GetString(jsonObject2, "OperatorID"));
				data.setOperatorName(JsonHelper.GetString(jsonObject2, "OperatorName"));
				data.setApplyDate(JsonHelper.GetDate(jsonObject2, "ApplyDate"));
				data.setTimestamp(JsonHelper.GetInt(jsonObject2, "Timestamp"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<MaintainPlanMDL>();
	}
	public static List<MaintainPlanDetailMDL> GetMTPlanDetails(JSONArray jsonArray) {
		List<MaintainPlanDetailMDL> datas = new LinkedList<MaintainPlanDetailMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				MaintainPlanDetailMDL data = new MaintainPlanDetailMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setMaintainPlanID(JsonHelper.GetString(jsonObject2, "MaintainPlanID"));
				data.setMaintainItemID(JsonHelper.GetString(jsonObject2, "MaintainItemID"));
				data.setMaintainItem(JsonHelper.GetString(jsonObject2, "MaintainItem"));
				data.setFrequency(JsonHelper.GetString(jsonObject2, "Frequency"));
				data.setFreqNum(JsonHelper.GetInt(jsonObject2, "FreqNum"));
				data.setMaiclaim(JsonHelper.GetString(jsonObject2, "Maiclaim"));
				data.setMaiMPlanDate(JsonHelper.GetDate(jsonObject2, "MaiMPlanDate"));
				
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<MaintainPlanDetailMDL>();
	}
	
	/** 
	 * 描述:  JSON 转 附件
	 * @param jsonArray
	 * @return
	 */
	public static List<Sys_FileMDL> GetFileList(JSONArray jsonArray)
	{
		List<Sys_FileMDL> datas = new LinkedList<Sys_FileMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				Sys_FileMDL data = new Sys_FileMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setBizOID(JsonHelper.GetString(jsonObject2, "bizOID"));
				data.setFileName(JsonHelper.GetString(jsonObject2, "FileName"));
				data.setFile_Type(JsonHelper.GetString(jsonObject2, "File_Type"));
				data.setFileExtName(JsonHelper.GetString(jsonObject2, "FileExtName"));
				data.setFileSize(JsonHelper.GetDouble(jsonObject2, "FileSize"));
				data.setFileContent(JsonHelper.GetString(jsonObject2, "FileContent"));
				data.setFilePath(JsonHelper.GetString(jsonObject2, "FilePath")); 
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return datas;
	}
	
	public static List<NeedMaintainEQUMDL> GetMTNeedEqus(JSONArray jsonArray) {
		List<NeedMaintainEQUMDL> datas = new LinkedList<NeedMaintainEQUMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				NeedMaintainEQUMDL data = new NeedMaintainEQUMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setThingID(JsonHelper.GetString(jsonObject2, "ThingID"));
				data.setMaiPlanDetailID(JsonHelper.GetString(jsonObject2, "MaiPlanDetailID"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return new LinkedList<NeedMaintainEQUMDL>();
	}
	public static String GetFaultReport2Json(Mai_FaultReportMDL faultReportMDL) {
		String jsonString = "{";
		jsonString += "\"OID\":\""+faultReportMDL.getOID()+"\",";
		jsonString += "\"RoadID\":\""+faultReportMDL.getRoadID()+"\",";
		jsonString += "\"RoadName\":\"" + faultReportMDL.getRoadName() + "\",";
		jsonString += "\"LocationID\":\"" + faultReportMDL.getLocationID() + "\",";
		jsonString += "\"LocationName\":\"" + faultReportMDL.getLocationName() + "\",";
		jsonString += "\"Code\":\"" + faultReportMDL.getCode() + "\",";
		jsonString += "\"FaultType\":\"" + faultReportMDL.getFaultType() + "\",";
		jsonString += "\"FaultLevel\":\"" + faultReportMDL.getFaultLevel() + "\",";
		jsonString += "\"OperatorID\":\""+faultReportMDL.getOperatorID()+"\",";
		jsonString += "\"OperatorName\":\""+faultReportMDL.getOperatorName()+"\",";
		jsonString += "\"ApplyDate\":\"" + ObjectHelper.Convert2String(faultReportMDL.getApplyDate(), "yyyy-MM-dd HH:mm:ss") + "\",";
		jsonString += "\"Reportor\":\"" + faultReportMDL.getReportor() + "\",";
		jsonString += "\"ReportTime\":\"" + ObjectHelper.Convert2String(faultReportMDL.getReportTime(), "yyyy-MM-dd HH:mm:ss") + "\",";
		jsonString += "\"ReportorTeamID\":\"" + faultReportMDL.getReportorTeamID() + "\",";
		jsonString += "\"FaultSource\":\"" + faultReportMDL.getFaultSource() + "\",";
		jsonString += "\"FaultTime\":\"" + ObjectHelper.Convert2String(faultReportMDL.getFaultTime(), "yyyy-MM-dd HH:mm:ss") + "\",";
		jsonString += "\"FaultDesc\":\"" + faultReportMDL.getFaultDesc() + "\",";
		jsonString += "\"DeviceName\":\""+faultReportMDL.getDeviceName()+"\",";
		jsonString += "\"DeviceCode\":\""+faultReportMDL.getDeviceCode()+"\",";
		jsonString += "\"PreAdvice\":\""+faultReportMDL.getPreAdvice()+"\",";
		jsonString += "\"BuStatus\":\"" + faultReportMDL.getBuStatus() + "\",";
		jsonString += "\"MTeamID\":\"" + faultReportMDL.getMTeamID() + "\",";
		jsonString += "\"MTeamName\":\"" + faultReportMDL.getMTeamName() + "\",";
		jsonString += "\"LimitTime\":\"" + ObjectHelper.Convert2String(faultReportMDL.getLimitTime(), "yyyy-MM-dd HH:mm:ss") + "\",";
		jsonString += "\"Remark\":\"" + faultReportMDL.getRemark() + "\",";
		jsonString += "\"Status\":\"" + faultReportMDL.getStatus() + "\",";
		jsonString += "\"RecoveryMainID\":\""+faultReportMDL.getRecoveryMainID()+"\",";
		jsonString += "\"LocaleCheckID\":\""+faultReportMDL.getLocaleCheckID()+"\",";
		jsonString += "\"FinalCheckID\":\"" + faultReportMDL.getFinalCheckID() + "\",";
		jsonString += "\"IsPlaceOnFile\":\"" + faultReportMDL.getIsPlaceOnFile() + "\",";
		jsonString += "\"SystemClass\":\"" + faultReportMDL.getSystemClass() + "\",";
		jsonString += "\"LocationDesc\":\"" + faultReportMDL.getLocationDesc() + "\",";
		jsonString += "\"PosMiles\":\"" + faultReportMDL.getPosMiles() + "\"";
		jsonString += "}";
		return jsonString;
	}
	public static String GetRecoveryMain2Json(Mai_RecoveryMainMDL faultReportMDL) {
		String jsonString = "{";
		jsonString += "\"OID\":\""+faultReportMDL.getOID()+"\",";
		jsonString += "\"FaultReportID\":\""+faultReportMDL.getFaultReportID()+"\",";
		jsonString += "\"RoadID\":\""+faultReportMDL.getRoadID()+"\",";
		jsonString += "\"RoadName\":\"" + faultReportMDL.getRoadName() + "\",";
		jsonString += "\"Code\":\"" + faultReportMDL.getCode() + "\",";
		jsonString += "\"OperatorID\":\""+faultReportMDL.getOperatorID()+"\",";
		jsonString += "\"OperatorName\":\""+faultReportMDL.getOperatorName()+"\",";
		jsonString += "\"ApplyDate\":\"" + ObjectHelper.Convert2String(faultReportMDL.getApplyDate(), "yyyy-MM-dd HH:mm:ss") + "\",";
		jsonString += "\"RecTime\":\"" + ObjectHelper.Convert2String(faultReportMDL.getRecTime(), "yyyy-MM-dd HH:mm:ss") + "\",";
		jsonString += "\"PlanRecoveryTime\":\"" + ObjectHelper.Convert2String(faultReportMDL.getPlanRecoveryTime(), "yyyy-MM-dd HH:mm:ss") + "\",";
		jsonString += "\"FaultReason\":\"" + faultReportMDL.getFaultReason() + "\",";
		jsonString += "\"Solution\":\"" + faultReportMDL.getSolution() + "\",";
		jsonString += "\"BuStatus\":\"" + faultReportMDL.getBuStatus() + "\",";
		jsonString += "\"MTeamID\":\"" + faultReportMDL.getMTeamID() + "\",";
		jsonString += "\"MTeamName\":\"" + faultReportMDL.getMTeamName() + "\",";
		jsonString += "\"MTeamMan\":\"" + faultReportMDL.getMTeamMan() + "\",";
		jsonString += "\"RepairPrice\":\"" + faultReportMDL.getRepairPrice() + "\",";
		jsonString += "\"CalculatePrice\":\"" + faultReportMDL.getCalculatePrice() + "\",";
		jsonString += "\"ActualRecoveryTime\":\"" + ObjectHelper.Convert2String(faultReportMDL.getActualRecoveryTime(), "yyyy-MM-dd HH:mm:ss") + "\",";
		jsonString += "\"IsPlaceOnFile\":\"" + faultReportMDL.getIsPlaceOnFile() + "\"";
		jsonString += "}";
		return jsonString;
	}
	public static String GetRecoveryDetail2Json(Mai_RecoveryDetailMDL faultReportMDL) {
		String jsonString = "{";
		jsonString += "\"OID\":\""+faultReportMDL.getOID()+"\",";
		jsonString += "\"RecoveryMainID\":\""+faultReportMDL.getRecoveryMainID()+"\",";
		jsonString += "\"ThingID\":\""+faultReportMDL.getThingID()+"\",";
		jsonString += "\"EquipmentID\":\"" + faultReportMDL.getEquipmentID() + "\",";
		jsonString += "\"Code\":\"" + faultReportMDL.getCode() + "\",";
		jsonString += "\"Name\":\""+faultReportMDL.getName()+"\",";
		jsonString += "\"BrandName\":\"" + faultReportMDL.getBrandName() + "\",";
		jsonString += "\"Model\":\"" + faultReportMDL.getModel() + "\",";
		jsonString += "\"UnitName\":\"" + faultReportMDL.getUnitName() + "\",";
		jsonString += "\"FaultThingID\":\"" + faultReportMDL.getFaultThingID() + "\",";
		jsonString += "\"RecoveryMode\":\"" + faultReportMDL.getRecoveryMode() + "\",";
		jsonString += "\"ReplaceThingID\":\"" + faultReportMDL.getReplaceThingID() + "\",";
		jsonString += "\"ReplaceEquipmentID\":\"" + faultReportMDL.getReplaceEquipmentID() + "\",";
		jsonString += "\"ReplaceCode\":\"" + faultReportMDL.getReplaceCode() + "\",";
		jsonString += "\"ReplaceName\":\"" + faultReportMDL.getReplaceName() + "\",";
		jsonString += "\"Remark\":\"" + faultReportMDL.getRemark() + "\",";
		jsonString += "\"Seq\":\"" + faultReportMDL.getSeq() + "\",";
		jsonString += "\"Number\":\"" + faultReportMDL.getNumber() + "\"";
		jsonString += "}";
		return jsonString;
	}
	public static String GetInspRecord2Json(Mai_InspRecordMDL inspRecordMDL) {
		String jsonString = "{";
		jsonString += "\"OID\":\""+inspRecordMDL.getOID()+"\",";
		jsonString += "\"InspPlanID\":\""+inspRecordMDL.getInsp_PlanID()+"\",";
		jsonString += "\"InspManID\":\""+inspRecordMDL.getInsp_ManID()+"\",";
		jsonString += "\"InspMan\":\"" + inspRecordMDL.getInsp_Man() + "\",";
		jsonString += "\"InspDate\":\"" + inspRecordMDL.getInsp_Date() + "\",";
		jsonString += "\"InspTime\":\""+inspRecordMDL.getInsp_Time()+"\",";
		jsonString += "\"InspDeptID\":\"" + inspRecordMDL.getInsp_DeptID() + "\",";
		jsonString += "\"InspDept\":\"" + inspRecordMDL.getInsp_Dept() + "\",";
		jsonString += "\"FlowStatus\":\"" + inspRecordMDL.getFlowStatus() + "\",";
		jsonString += "\"NeedStartFlow\":\"" + inspRecordMDL.getNeedStartFlow() + "\",";
		jsonString += "\"IsPlaceOnFile\":\"" + inspRecordMDL.getIsPlaceOnFile() + "\",";
		jsonString += "\"OperatorID\":\"" + inspRecordMDL.getOperatorID() + "\",";
		jsonString += "\"OperatorName\":\"" + inspRecordMDL.getOperatorName() + "\",";
		jsonString += "\"ApplyDate\":\"" + inspRecordMDL.getApplyDate() + "\"";
		jsonString += "}";
		return jsonString;
	}
	public static String GetInspRecordDetail2Json(Mai_InspRecordDetailMDL ipRecordDetailMDL) {
		String jsonString = "{";
		jsonString += "\"OID\":\""+ipRecordDetailMDL.getOID()+"\",";
		jsonString += "\"InspRecordID\":\""+ipRecordDetailMDL.getInsp_RecordID()+"\",";
		jsonString += "\"InspPlanDetalID\":\""+ipRecordDetailMDL.getInsp_PlanDetalID()+"\",";
		jsonString += "\"TollStationsID\":\"" + ipRecordDetailMDL.getToll_StationsID() + "\",";
		jsonString += "\"TollStations\":\"" + ipRecordDetailMDL.getToll_Stations() + "\",";
		jsonString += "\"InspLaneID\":\""+ipRecordDetailMDL.getInsp_LaneID()+"\",";
		jsonString += "\"InspLane\":\""+ipRecordDetailMDL.getInsp_Lane()+"\",";
		jsonString += "\"InspectionItem\":\"" + ipRecordDetailMDL.getInspection_Item() + "\",";
		jsonString += "\"Frequency\":\"" + ipRecordDetailMDL.getFrequency() + "\",";
		jsonString += "\"FreqNum\":\"" + ipRecordDetailMDL.getFreq_Num() + "\",";
		jsonString += "\"CheckItemName\":\"" + ipRecordDetailMDL.getCheck_ItemName() + "\",";
		jsonString += "\"CheckReq\":\"" + ipRecordDetailMDL.getCheck_Req() + "\",";
		jsonString += "\"InspResult\":\"" + ipRecordDetailMDL.getInsp_Result() + "\",";
		jsonString += "\"InspMemo\":\"" + ipRecordDetailMDL.getInsp_Memo() + "\",";
		jsonString += "\"FaultReportID\":\"" + ipRecordDetailMDL.getFaultReportID() + "\"";
		jsonString += "}";
		return jsonString;
	}
	public static String GetInspEqu2Json(Mai_InspEquMDL inspEquMDL) {
		String jsonString = "{";
		jsonString += "\"OID\":\""+inspEquMDL.getOID()+"\",";
		jsonString += "\"InspRecordDetailID\":\""+inspEquMDL.getInspRecordDetailID()+"\",";
		jsonString += "\"ThingID\":\""+inspEquMDL.getThingID()+"\",";
		jsonString += "\"ThingStatus\":\"" + inspEquMDL.getThingStatus() + "\",";
		jsonString += "\"ThingMemo\":\"" + inspEquMDL.getThingMemo() + "\"";
		jsonString += "}";
		return jsonString;
	}
	public static String GetMTRecord2Json(MaintainRecordMDL mtRecordMDL) {
		String jsonString = "{";
		jsonString += "\"OID\":\""+mtRecordMDL.getOID()+"\",";
		jsonString += "\"MaiPlanID\":\""+mtRecordMDL.getMaiPlanID()+"\",";
		jsonString += "\"MaiManID\":\""+mtRecordMDL.getMaiManID()+"\",";
		jsonString += "\"MaiMan\":\"" + mtRecordMDL.getMaiMan() + "\",";
		jsonString += "\"MaiDate\":\"" + mtRecordMDL.getMaiDate() + "\",";
		jsonString += "\"MaiDeptID\":\"" + mtRecordMDL.getMaiDeptID() + "\",";
		jsonString += "\"MaiDept\":\"" + mtRecordMDL.getMaiDept() + "\",";
		jsonString += "\"FlowStatus\":\"" + mtRecordMDL.getFlowStatus() + "\",";
		jsonString += "\"NeedStartFlow\":\"" + mtRecordMDL.getNeedStartFlow() + "\",";
		jsonString += "\"IsPlaceOnFile\":\"" + mtRecordMDL.getIsPlaceOnFile() + "\",";
		jsonString += "\"OperatorID\":\"" + mtRecordMDL.getOperatorID() + "\",";
		jsonString += "\"OperatorName\":\"" + mtRecordMDL.getOperatorName() + "\",";
		jsonString += "\"ApplyDate\":\"" + mtRecordMDL.getApplyDate() + "\"";
		jsonString += "}";
		return jsonString;
	}
	public static String GetMTRecordDetail2Json(MaintainRecordDetailMDL mtRecordDetailMDL) {
		String jsonString = "{";
		jsonString += "\"OID\":\""+mtRecordDetailMDL.getOID()+"\",";
		jsonString += "\"MaiRecordID\":\""+mtRecordDetailMDL.getMaiRecordID()+"\",";
		jsonString += "\"MaiPlanDetailID\":\""+mtRecordDetailMDL.getMaiPlanDetailID()+"\",";
		jsonString += "\"TollStationsID\":\"" + mtRecordDetailMDL.getTollStationsID() + "\",";
		jsonString += "\"TollStations\":\"" + mtRecordDetailMDL.getTollStations() + "\",";
		jsonString += "\"InspLaneID\":\""+mtRecordDetailMDL.getInspLaneID()+"\",";
		jsonString += "\"InspLane\":\""+mtRecordDetailMDL.getInspLane()+"\",";
		jsonString += "\"MaintainItem\":\"" + mtRecordDetailMDL.getMaintainItem() + "\",";
		jsonString += "\"Frequency\":\"" + mtRecordDetailMDL.getFrequency() + "\",";
		jsonString += "\"FreqNum\":\"" + mtRecordDetailMDL.getFreqNum() + "\",";
		jsonString += "\"Maiclaim\":\"" + mtRecordDetailMDL.getMaiclaim() + "\",";
		jsonString += "\"MaiResult\":\"" + mtRecordDetailMDL.getMaiResult() + "\",";
		jsonString += "\"FaultReportID\":\"" + mtRecordDetailMDL.getFaultReportID() + "\"";
		jsonString += "}";
		return jsonString;
	}
	public static String GetMTEqu2Json(RealMaintainEQUMDL inspEquMDL) {
		String jsonString = "{";
		jsonString += "\"OID\":\""+inspEquMDL.getOID()+"\",";
		jsonString += "\"MaiRecordDetailID\":\""+inspEquMDL.getMaiRecordDetailID()+"\",";
		jsonString += "\"ThingID\":\""+inspEquMDL.getThingID()+"\"";
		jsonString += "}";
		return jsonString;
	}
	public static String GetSysFile2Json(Sys_FileMDL sys_FileMDL) {
		String jsonString = "{";
		jsonString += "\"OID\":\""+sys_FileMDL.getOID()+"\",";
		jsonString += "\"BizOID\":\""+sys_FileMDL.getBizOID()+"\",";
		jsonString += "\"FileName\":\""+sys_FileMDL.getFileName()+"\",";
		jsonString += "\"File_Type\":\"" + sys_FileMDL.getFile_Type() + "\",";
		jsonString += "\"FileExtName\":\"" + sys_FileMDL.getFileExtName() + "\",";
		jsonString += "\"FileSize\":\""+sys_FileMDL.getFileSize()+"\",";
		jsonString += "\"SaveType\":\""+sys_FileMDL.getSaveType()+"\",";
		jsonString += "\"FileContent\":\"" + sys_FileMDL.getFileContent() + "\",";
		jsonString += "\"Operate\":\"" + sys_FileMDL.getOperate() + "\",";
		jsonString += "\"FilePath\":\"" + sys_FileMDL.getFilePath() + "\""; 
		jsonString += "}";
		return jsonString;
	}
	
	/** 
	 * 描述:  根据JSON 获取库存查询列表
	 * @param jsonArray
	 * @return
	 */
	public static List<StoreMDL> GetStoreList(JSONArray jsonArray)
	{
		List<StoreMDL> datas=new ArrayList<StoreMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				StoreMDL data = new StoreMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setThingClassID(JsonHelper.GetString(jsonObject2, "ThingClassID"));
				data.setThingClassName(JsonHelper.GetString(jsonObject2, "ThingClassName"));
				data.setName(JsonHelper.GetString(jsonObject2, "Name"));
				data.setCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setBrandName(JsonHelper.GetString(jsonObject2, "BrandName"));
				data.setModel(JsonHelper.GetString(jsonObject2, "Model"));
				data.setUnitName(JsonHelper.GetString(jsonObject2, "UnitName"));
				data.setThingID(JsonHelper.GetString(jsonObject2, "ThingID"));
				data.setShapeType(JsonHelper.GetString(jsonObject2, "ShapeType"));
				data.setStockNumber(JsonHelper.GetInt(jsonObject2, "StockNumber"));
				data.setAmount(JsonHelper.GetInt(jsonObject2, "Amount"));
				data.setPageTotalCount(JsonHelper.GetInt(jsonObject2, "pageTotalCount"));
				data.setTotalPage(JsonHelper.GetInt(jsonObject2, "TotalPage"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return datas;
	}
	
	/** 
	 * 描述:  获取仓库
	 * @param jsonArray
	 * @return
	 */
	public static List<StockMDL> GetStockList(JSONArray jsonArray)
	{
		List<StockMDL> datas=new ArrayList<StockMDL>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
				StockMDL data = new StockMDL();
				data.setOID(JsonHelper.GetString(jsonObject2, "OID"));
				data.setRoadID(JsonHelper.GetString(jsonObject2, "RoadID"));
				data.setRoadName(JsonHelper.GetString(jsonObject2, "RoadName"));
				data.setName(JsonHelper.GetString(jsonObject2, "Name"));
				data.setCode(JsonHelper.GetString(jsonObject2, "Code"));
				data.setLocationID(JsonHelper.GetString(jsonObject2, "LocationID"));
				data.setLocationName(JsonHelper.GetString(jsonObject2, "LocationName"));
				data.setManager(JsonHelper.GetString(jsonObject2, "Manager"));
				data.setPhone(JsonHelper.GetString(jsonObject2, "Phone"));
				data.setCreateTime(JsonHelper.GetString(jsonObject2, "CreateTime"));
				data.setState(JsonHelper.GetString(jsonObject2, "State"));
				data.setStatus(JsonHelper.GetString(jsonObject2, "Status"));
				data.setRemark(JsonHelper.GetString(jsonObject2, "Remark"));
				data.setSeq(JsonHelper.GetInt(jsonObject2, "Seq"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return datas;
	}
	
	/** 
	 * @Title: MapToJason 
	 * @Description: Map转成jason
	 * @param param
	 * @return 
	 * @return: String
	 */
	public static String MapToJason(HashMap<String, String> param) {
		String str = "[";
		if (param != null) {
			Iterator keys = param.keySet().iterator(); 
			while (keys.hasNext()) {
				str=str+"{";
				Object key = keys.next();// key
				Object value = param.get(key);// 上面key对应的value
				str=str+"\""+key.toString()+"\":\""+value.toString()+"\"},";
			}
			str=str.substring(0, str.lastIndexOf(","));
		}
		str=str+"]";
		return str.toString();
	}
}
