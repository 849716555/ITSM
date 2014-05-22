package com.goldsunny.itsm.webservicebll;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.MyAndroidHttpTransport;

public class ServiceBase {
	protected String serviceNameSpace= "http://ITSM.android/";
	protected String serviceIp=GlobalData.SystemConfig.getServerIp();
	protected String servicePort=GlobalData.SystemConfig.getWSServerPort();
	String UrlPath="";
	protected  ServiceBase() {
		serviceIp=GlobalData.SystemConfig.getServerIp();
		servicePort=GlobalData.SystemConfig.getWSServerPort();
	}
	
	protected String GetServiceIp() {
		return GlobalData.SystemConfig.getServerIp();
	}
	protected String GetServicePort() {
		return GlobalData.SystemConfig.getWSServerPort();
	}
	protected String GetServiceUrl()
	{
		return "http://" + GetServiceIp() + ":" + GetServicePort() + "/phonews.asmx?wsdl";
	}
	
	protected SoapSerializationEnvelope GetEnvelope(SoapObject request) {
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = request;  
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;
        return envelope;
		
	}
	protected Object GetEnvelopeResponse(String methodName,String serviceUrl) throws IOException, Exception
	{
		String sopaAction = serviceNameSpace+methodName;
        SoapObject request = new SoapObject(serviceNameSpace, methodName); 
        SoapSerializationEnvelope envelope = GetEnvelope(request);
        MyAndroidHttpTransport ht = new MyAndroidHttpTransport(serviceUrl);
        ht.call(sopaAction, envelope);
        return envelope.getResponse();
	}
}
