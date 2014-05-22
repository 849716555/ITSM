package com.goldsunny.itsm.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.webservicebll.SystemService;

public class NetHelper {
	
	public static boolean GetConnectStatuByIP(String ip) {
		boolean result = false;
		try {

			InetAddress ia = InetAddress.getByName(ip);
			if (ia.isReachable(10000))
				result = true;
		} catch (UnknownHostException e) {
			Log.e("InetAddress", e + "");
			// TODO Auto-generated catch block

		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("InetAddress", e + "");

		}
		return result;
	}
	
	public static  boolean GetConnectStatuByWebsite(String url) {
		boolean result = false;
		
		try {
			if(url==null || (!url.substring(0, 7).equals("http://") && !url.substring(0, 8).equals("https://")))return false;
			HttpClient client=new DefaultHttpClient();
			HttpPost request;
			request = new HttpPost(new URI(url));
			HttpResponse response=client.execute(request);
			int Status = response.getStatusLine().getStatusCode();
			if(Status==200)
				result=true;
		} catch (UnknownHostException e) {
			Log.e("GetConnectStatuByWebsite", e+"");
			// TODO Auto-generated catch block
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("GetConnectStatuByWebsite", e+"");
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static  boolean GetConnectStatuByWebservice(String url) {
		boolean result = false;
		try {
			if(!url.substring(0, 7).equals("http://") && !url.substring(0, 8).equals("https://"))return false;
			SystemService systemService = new SystemService();
			result=systemService.checkWSConnected();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public static String getLocalIpAddress() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); 
			en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); 
			enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress()) {
	                    return inetAddress.getHostAddress().toString();
	                }
	            }
	        }
	    } catch (SocketException ex) {
	        Log.e("getLocalIpAddress", ex.toString());
	    }
	    return null;
	}
	
	public static boolean GetConnectStatuByFTP(Context c ) {
		FtpHelper ftpHelper = new FtpHelper();
		InputStream isInputStream =  c.getResources().openRawResource(R.raw.ftptest);
		String resultString = ftpHelper.uploadFile(isInputStream, "/ftpFile/", ObjectHelper.Convert2String(new Date(), "yyyyMMddHHmmss"));
		if(resultString.equals(""))
			return true;
		else
			return false;
	}
}
