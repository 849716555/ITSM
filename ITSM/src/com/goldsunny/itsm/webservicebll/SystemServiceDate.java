package com.goldsunny.itsm.webservicebll;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import android.util.Log;

import com.goldsunny.itsm.dataaccess.SoapObjectHelper;
import com.goldsunny.itsm.util.MyAndroidHttpTransport;

/**     
* �����ƣ�webservice ����
* ��������   
* �����ˣ�yangwy   
* ����ʱ�䣺2014-4-10 ����11:50:44     
* @version     
*/  
public class SystemServiceDate extends ServiceBase {

	private int timeOut = 20000;

	public boolean checkWSConnected() {
		String methodName = "CheckStatu";
		String result = "";
		try {
			Object soapObject = (Object) GetEnvelopeResponse(methodName, GetServiceUrl());
			result = soapObject.toString();
			if (result.equals("ok"))
				return true;
			else
				return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}
	
	public String UsreLogin(String userName,String password)
	{
		Object result = "";
		try {
			String methodName="UserLogin";
			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("userName", userName);
			request.addProperty("password", password);
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
			Log.i("login", "login"+result);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SoapObjectHelper.parseNullString(result.toString());
	}
	
	
	/** 
	 * @Title: webservice �ӿ�
	 * @Description: TODO 
	 * @param query �������
	 * @param methodName �������� 
	 * @return: String 
	 */
	public String HandeMenthod(HashMap<String, String> params,String methodName)
	{
		Object result = "";
		try {

			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			if(params!=null)
			{
				Iterator iterator=params.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry entry = (Map.Entry) iterator.next();
					Object key = entry.getKey();
					Object val = entry.getValue();
					request.addProperty(key.toString(), val);
				}
			}
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
		} catch (Exception e) {
			Log.e("error", methodName+"�������쳣 "+e.toString());
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
