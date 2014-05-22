package com.goldsunny.itsm.model;

import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.MarshalDate;
import org.ksoap2.serialization.PropertyInfo;

import android.R.integer;
import android.util.Log;

public class Equ_EquipmentMDL implements KvmSerializable {
	private String OID;
	private String ThingID;
	private String Code;
	private String Name;
	private String SeqCode;
	private String UnitType;
	private String UnitName;
	private String BrandID;
	private String BrandName;
	private String Model;
	private String TechnicalParam;
	private String LeadingFeatures;
	private String MainApplication;
	private String PeripheralInterface;
	private String OtherTechnicalParam;
	private String AssetID;
	private String SupplyCompanyID;
	private String SupplyCompanyName;
	private String FactoryID;
	private String FactoryName;
	private double UnitPrice;
	private Date BuyDate;
	private Date InstallDate;
	private Date BeginUseDate;
	private int LimitMonthNumber;
	private String MatchOperator;
	private String DepreciationType;
	private String DepreciationName;
	private String CompanyID;
	private String RoadID;
	private String RoadName;
	private String ManagerID;
	private String Manager;
	private String UserID;
	private String UserName;
	private String DepartmentID;
	private String Department;
	private String LocationID;
	private String LocationName;
	private String PosDescription;
	private String SystemClass;
	private String BuStatus;
	private String Remark;
	private String Status;
	private int Seq;
	private String AccountCode;
	private int Timestamp;

	private String SystemName;
	private String EqStatus;
	private String AssetName;
	
	private int pageTotalCount;
	private int TotalPage;
	private int Number;


	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getThingID() {
		return ThingID;
	}

	public void setThingID(String thingID) {
		ThingID = thingID;
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

	public String getSeqCode() {
		return SeqCode;
	}

	public void setSeqCode(String seqCode) {
		SeqCode = seqCode;
	}

	public String getUnitType() {
		return UnitType;
	}

	public void setUnitType(String unitType) {
		UnitType = unitType;
	}

	public String getUnitName() {
		return UnitName;
	}

	public void setUnitName(String unitName) {
		UnitName = unitName;
	}

	public String getBrandID() {
		return BrandID;
	}

	public void setBrandID(String brandID) {
		BrandID = brandID;
	}

	public String getBrandName() {
		return BrandName;
	}

	public void setBrandName(String brandName) {
		BrandName = brandName;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	public String getTechnicalParam() {
		return TechnicalParam;
	}

	public void setTechnicalParam(String technicalParam) {
		TechnicalParam = technicalParam;
	}

	public String getLeadingFeatures() {
		return LeadingFeatures;
	}

	public void setLeadingFeatures(String leadingFeatures) {
		LeadingFeatures = leadingFeatures;
	}

	public String getMainApplication() {
		return MainApplication;
	}

	public void setMainApplication(String mainApplication) {
		MainApplication = mainApplication;
	}

	public String getPeripheralInterface() {
		return PeripheralInterface;
	}

	public void setPeripheralInterface(String peripheralInterface) {
		PeripheralInterface = peripheralInterface;
	}

	public String getOtherTechnicalParam() {
		return OtherTechnicalParam;
	}

	public void setOtherTechnicalParam(String otherTechnicalParam) {
		OtherTechnicalParam = otherTechnicalParam;
	}

	public String getAssetID() {
		return AssetID;
	}

	public void setAssetID(String assetID) {
		AssetID = assetID;
	}

	public String getSupplyCompanyID() {
		return SupplyCompanyID;
	}

	public void setSupplyCompanyID(String supplyCompanyID) {
		SupplyCompanyID = supplyCompanyID;
	}

	public String getSupplyCompanyName() {
		return SupplyCompanyName;
	}

	public void setSupplyCompanyName(String supplyCompanyName) {
		SupplyCompanyName = supplyCompanyName;
	}

	public String getFactoryID() {
		return FactoryID;
	}

	public void setFactoryID(String factoryID) {
		FactoryID = factoryID;
	}

	public String getFactoryName() {
		return FactoryName;
	}

	public void setFactoryName(String factoryName) {
		FactoryName = factoryName;
	}

	public double getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		UnitPrice = unitPrice;
	}

	public Date getBuyDate() {
		return BuyDate;
	}

	public void setBuyDate(Date buyDate) {
		BuyDate = buyDate;
	}

	public Date getInstallDate() {
		return InstallDate;
	}

	public void setInstallDate(Date installDate) {
		InstallDate = installDate;
	}

	public Date getBeginUseDate() {
		return BeginUseDate;
	}

	public void setBeginUseDate(Date beginUseDate) {
		BeginUseDate = beginUseDate;
	}

	public int getLimitMonthNumber() {
		return LimitMonthNumber;
	}

	public void setLimitMonthNumber(int limitMonthNumber) {
		LimitMonthNumber = limitMonthNumber;
	}

	public String getMatchOperator() {
		return MatchOperator;
	}

	public void setMatchOperator(String matchOperator) {
		MatchOperator = matchOperator;
	}

	public String getDepreciationType() {
		return DepreciationType;
	}

	public void setDepreciationType(String depreciationType) {
		DepreciationType = depreciationType;
	}

	public String getDepreciationName() {
		return DepreciationName;
	}

	public void setDepreciationName(String depreciationName) {
		DepreciationName = depreciationName;
	}

	public String getCompanyID() {
		return CompanyID;
	}

	public void setCompanyID(String companyID) {
		CompanyID = companyID;
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

	public String getManagerID() {
		return ManagerID;
	}

	public void setManagerID(String managerID) {
		ManagerID = managerID;
	}

	public String getManager() {
		return Manager;
	}

	public void setManager(String manager) {
		Manager = manager;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getDepartmentID() {
		return DepartmentID;
	}

	public void setDepartmentID(String departmentID) {
		DepartmentID = departmentID;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
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

	public String getPosDescription() {
		return PosDescription;
	}

	public void setPosDescription(String posDescription) {
		PosDescription = posDescription;
	}

	public String getSystemClass() {
		return SystemClass;
	}

	public void setSystemClass(String systemClass) {
		SystemClass = systemClass;
	}

	public String getBuStatus() {
		return BuStatus;
	}

	public void setBuStatus(String buStatus) {
		BuStatus = buStatus;
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

	public int getSeq() {
		return Seq;
	}

	public void setSeq(int seq) {
		Seq = seq;
	}

	public String getAccountCode() {
		return AccountCode;
	}

	public void setAccountCode(String accountCode) {
		AccountCode = accountCode;
	}

	public int getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(int timestamp) {
		Timestamp = timestamp;
	}

	public String getSystemName() {
		return SystemName;
	}

	public void setSystemName(String systemName) {
		SystemName = systemName;
	}

	public String getEqStatus() {
		return EqStatus;
	}

	public void setEqStatus(String eqStatus) {
		EqStatus = eqStatus;
	}

	public String getAssetName() {
		return AssetName;
	}

	public void setAssetName(String assetName) {
		AssetName = assetName;
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

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub

		try {

			if (arg0 == 0)
				return OID;
			else if (arg0 == 1)
				return ThingID;
			else if (arg0 == 2)
				return Code;
			else if (arg0 == 3)
				return Name;
			else if (arg0 == 4)
				return SeqCode;
			else if (arg0 == 5)
				return UnitType;
			else if (arg0 == 6)
				return UnitName;
			else if (arg0 == 7)
				return BrandID;
			else if (arg0 == 8)
				return BrandName;
			else if (arg0 == 9)
				return Model;
			else if (arg0 == 10)
				return TechnicalParam;
			else if (arg0 == 11)
				return LeadingFeatures;
			else if (arg0 == 12)
				return MainApplication;
			else if (arg0 == 13)
				return PeripheralInterface;
			else if (arg0 == 14)
				return OtherTechnicalParam;
			else if (arg0 == 15)
				return AssetID;
			else if (arg0 == 16)
				return SupplyCompanyID;
			else if (arg0 == 17)
				return SupplyCompanyName;
			else if (arg0 == 18)
				return FactoryID;
			else if (arg0 == 19)
				return FactoryName;
			else if (arg0 == 20)
				return UnitPrice;
			else if (arg0 == 21)
				return BuyDate;
			else if (arg0 == 22)
				return InstallDate;
			else if (arg0 == 23)
				return BeginUseDate;
			if (arg0 == 24)
				return LimitMonthNumber;
			else if (arg0 == 25)
				return MatchOperator;
			else if (arg0 == 26)
				return DepreciationType;
			else if (arg0 == 27)
				return DepreciationName;
			else if (arg0 == 28)
				return CompanyID;
			else if (arg0 == 29)
				return RoadID;
			else if (arg0 == 30)
				return RoadName;
			else if (arg0 == 31)
				return ManagerID;
			else if (arg0 == 32)
				return Manager;
			else if (arg0 == 33)
				return UserID;
			else if (arg0 == 34)
				return UserName;
			else if (arg0 == 35)
				return DepartmentID;
			else if (arg0 == 36)
				return Department;
			else if (arg0 == 37)
				return LocationID;
			else if (arg0 == 38)
				return LocationName;
			else if (arg0 == 39)
				return PosDescription;
			else if (arg0 == 40)
				return SystemClass;
			else if (arg0 == 41)
				return BuStatus;
			else if (arg0 == 42)
				return Remark;
			else if (arg0 == 43)
				return Status;
			else if (arg0 == 44)
				return Seq;
			else if (arg0 == 45)
				return AccountCode;
			else if (arg0 == 46)
				return Timestamp;
			else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 46;
	}

	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
		// TODO Auto-generated method stub
		try {
			switch (arg0) {
			case 0:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "OID";
				break;
			case 1:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "ThingID";
				break;
			case 2:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "Code";
				break;
			case 3:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "Name";
				break;
			case 4:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "SeqCode";
				break;
			case 5:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "UnitType";
				break;
			case 6:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "UnitName";
				break;
			case 7:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "BrandID";
				break;
			case 8:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "BrandName";
				break;
			case 9:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "Model";
				break;
			case 10:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "TechnicalParam";
				break;
			case 11:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "LeadingFeatures";
				break;
			case 12:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "MainApplication";
				break;
			case 13:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "PeripheralInterface";
				break;
			case 14:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "OtherTechnicalParam";
				break;
			case 15:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "AssetID";
				break;
			case 16:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "SupplyCompanyID";
				break;
			case 17:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "SupplyCompanyName";
				break;
			case 18:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "FactoryID";
				break;
			case 19:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "FactoryName";
				break;
			case 20:
				info.type = PropertyInfo.INTEGER_CLASS;
				info.name = "UnitPrice";
				break;
			case 21:
				info.type = MarshalDate.DATE_CLASS;
				info.name = "BuyDate";
				break;
			case 22:
				info.type = MarshalDate.DATE_CLASS;
				info.name = "InstallDate";
				break;
			case 23:
				info.type = MarshalDate.DATE_CLASS;
				info.name = "BeginUseDate";
				break;
			case 24:
				info.type = PropertyInfo.INTEGER_CLASS;
				info.name = "LimitMonthNumber";
				break;
			case 25:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "MatchOperator";
				break;
			case 26:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "DepreciationType";
				break;
			case 27:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "DepreciationName";
				break;
			case 28:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "CompanyID";
				break;
			case 29:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "RoadID";
				break;
			case 30:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "RoadName";
				break;
			case 31:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "ManagerID";
				break;
			case 32:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "Manager";
				break;
			case 33:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "UserID";
				break;
			case 34:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "UserName";
				break;
			case 35:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "DepartmentID";
				break;
			case 36:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "Department";
				break;
			case 37:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "LocationID";
				break;
			case 38:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "LocationName";
				break;
			case 39:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "PosDescription";
				break;
			case 40:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "SystemClass";
				break;
			case 41:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "BuStatus";
				break;
			case 42:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "Remark";
				break;
			case 43:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "Status";
				break;
			case 44:
				info.type = PropertyInfo.INTEGER_CLASS;
				info.name = "Seq";
				break;
			case 45:
				info.type = PropertyInfo.STRING_CLASS;
				info.name = "AccountCode";
				break;
			case 46:
				info.type = PropertyInfo.INTEGER_CLASS;
				info.name = "Timestamp";
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("checkbasicMDLP", String.valueOf(arg0));
		}
	}

	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
