package com.goldsunny.itsm.dataaccess;

public class SoapObjectHelper {

	public static String parseNullString(String str)
	{
		if(str.equals("anyType{}"))
			return "";
		else
			return str;
	}
}
