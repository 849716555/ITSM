package com.goldsunny.itsm.webservicebll;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import com.goldsunny.itsm.dataaccess.SoapObjectHelper;
import com.goldsunny.itsm.util.MyAndroidHttpTransport;

public class SystemService extends ServiceBase {
	
	//private String GetServiceUrl() = "";
	private int timeOut=20000;
	
	public boolean checkWSConnected() {
		String methodName = "CheckStatu";
		String result = "";
		try {
			Object soapObject = (Object) GetEnvelopeResponse(methodName, GetServiceUrl());
			result = soapObject.toString();
			if(result.equals("ok"))
				return true;
			else
				return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	
	}
	public String GetSysVerNo() {
		String methodName = "GetSysVerNo";
		String result = "";
		try {
			Object soapObject = (Object) GetEnvelopeResponse(methodName, GetServiceUrl());
			result = soapObject.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public String GetEquipments(String timestamp,String num) {
		return HandlerMethod(timestamp,num,"GetEquipmentsByTS");
	} 
	
	public String GetEquAssets(String timestamp,String num) {
		return HandlerMethod(timestamp,num,"GetEquAssetsByTS");
	}
	public String GetEquToThings(String timestamp,String num) {
		return HandlerMethod(timestamp,num,"GetEquToThingsByTS");
	}
	public String GetEqu_LifeLogs(String timestamp,String num) {
		return HandlerMethod(timestamp,num,"GetEqu_LifeLogsByTS");
	}
	public String GetDictLists(String num) {
		String methodName = "GetDictList";
		Object result = "";
		try {

			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("num", num);
		
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SoapObjectHelper.parseNullString(result.toString());
	} 
	public String GetEmployees() {
		return HandleMethod("GetOrg_Employee");
	} 
	public String GetLocations() {
		return HandleMethod("GetAllLocation");
	} 
	public String GetFaultReports(String userID) {
		return HandleUserMethod(userID,"GetFaultReports");
	} 
	public String GetMTeamPerson() {
		return HandleMethod("GetMTeamPerson");
	} 
	public String GetMaintainTeam() {
		return HandleMethod("GetMaintainTeam");
	} 
	public String GetMTeamLocation() {
		return HandleMethod("GetMTeamLocation");
	} 
	public String GetSyst_Road() {
		return HandleMethod("GetSyst_Road");
	} 
	public String GetInspType() {
		return HandleMethod("GetInspType");
	} 
	public String GetInspItem() {
		return HandleMethod("GetInspItem");
	}
	public String GetInspPlan(String userID,String num) {
		return HandleUserMethod(userID,"GetInspPlan");
	}
	public String GetInspPlanDetail(String userID) {
		return HandleUserMethod(userID,"GetInspPlanDetail");
	}
	public String GetInspNeedEqu(String userID) {
		return HandleUserMethod(userID,"GetInspNeedEqu");
	}
	public String GetMTType() {
		return HandleMethod("GetMTType");
	}
	public String GetMTItem() {
		return HandleMethod("GetMTItem");
	}
	public String GetMTStep() {
		return HandleMethod("GetMTStep");
	}
	public String GetMTPlan(String userID,String num) {
		return HandleUserMethod(userID,"GetMTPlan");
	}
	public String GetMTPlanDetail(String userID) {
		return HandleUserMethod(userID,"GetMTPlanDetail");
	}
	public String GetMTNeedEqu(String userID) {
		return HandleUserMethod(userID,"GetMTNeedEqu");
	}
	
	private String HandlerMethod(String timestamp,String num,String methodName)
	{
		Object result = "";
		try {

			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("timestamp", timestamp);
			request.addProperty("num", num);
		
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SoapObjectHelper.parseNullString(result.toString());
	}
	private String HandleUserMethod(String userID,String methodName)
	{
		Object result = "";
		try {

			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("userid", userID);
		
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SoapObjectHelper.parseNullString(result.toString());
	}
	private String HandleMethod(String methodName)
	{
		Object result = "";
		try {

			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
		
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SoapObjectHelper.parseNullString(result.toString());
	}
}
