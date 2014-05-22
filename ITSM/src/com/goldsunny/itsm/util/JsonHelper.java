package com.goldsunny.itsm.util;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper {
	public static String GetJsonString(String val)
	{
		String reString="";
		if(val.toLowerCase().equals("null"))
			return reString;
		return val;
	}
	
	public static String GetString(JSONObject jsonObject,String key)
	{
		try {
			return GetJsonString(jsonObject.getString(key));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "";
		}
	}
	public static long GetLong(JSONObject jsonObject,String key)
	{
		try {
			return ObjectHelper.Convert2Long(GetJsonString(jsonObject.getString(key)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return 0;
		}
	}
	public static Date GetDate(JSONObject jsonObject,String key)
	{
		try {
			return ObjectHelper.Convert2Date(GetJsonString(jsonObject.getString(key)));
		} catch (Exception e) {
			// TODO: handle exception
			return new Date();
		}
	}
	public static Date GetDate(JSONObject jsonObject,String key,String dateType)
	{
		try {
			return ObjectHelper.Convert2Date(GetJsonString(jsonObject.getString(key)),dateType);
		} catch (Exception e) {
			// TODO: handle exception
			return new Date();
		}
	}
	public static int GetInt(JSONObject jsonObject,String key)
	{
		try {
			return ObjectHelper.Convert2Int(GetJsonString(jsonObject.getString(key)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return 0;
		}
	}
	public static double GetDouble(JSONObject jsonObject,String key)
	{
		try {
			
			return ObjectHelper.Convert2Double(GetJsonString(jsonObject.getString(key)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return 0;
		}
	}
}
