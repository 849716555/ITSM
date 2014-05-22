package com.goldsunny.itsm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class ObjectHelper {
	public static String Convert2String(Date date, String type) {
		if (date == null)
			return "";
		DateFormat format = new SimpleDateFormat(type);
		String strString = format.format(date);
		return strString;
	}
	public static boolean Convert2Boolen(int val)
	{
		if(val==0)
			return false;
		else
			return true;
	}
	public static boolean isNumeric(String str) {
		try {
			Integer.valueOf(str.toString());
			return true;
		} catch (Exception e) {
			// TODO: handle exceptionet
			return false;
		}
		
	}
	/**
	 * @Name: Convert2MathCount
	 * @Author: 陈伟炎
	 * @Date: 2012-8-11 
	 * @param count
	 * @param obj
	 * @return
	 * @Description: 返回小数点后几位数
	 */
	public static String Convert2MathCount(int count,Object obj)
	{
		if(obj==null)return "";
		try {
			return String.format("%."+count+"f", obj);
		} catch (Exception e) {
			// TODO: handle exception
			return obj.toString();
		}
	}
	
	public static String Convert2String(Object value) {
		try {
			return String.valueOf(value);
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
	
	public static long Convert2Long(Object object) {
		try {
			long l = Long.parseLong(object.toString());
			return Long.parseLong(object.toString());
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public static double Convert2Double(Object object) {
		try {

			return Double.parseDouble(object.toString());
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	
	
	public static Date Convert2Date(String dateString)
	{
		return Convert2Date(dateString,"yyyy-MM-dd HH:mm:ss");
	}
	public static Date Convert2Date(String dateString, String type) {
		if (dateString == null || dateString.trim().equals("") || dateString.trim().equals("null"))
			return null;
		DateFormat df = new SimpleDateFormat(type);
		Date date = new Date();
		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block\
			Log.e("StringToDate", dateString + "    " + e);
			e.printStackTrace();
		}
		return date;
	}
	public static int Convert2Int(Object obj) {
		try {
			return  (int)Double.parseDouble(obj.toString().trim()); 
		} catch (Exception e) {
			// TODO: handle exceptionet
			return 0;
		}
	}
	public static List<String> Convert2ListString(String str, String regularExpression) {
		List<String> strings = new LinkedList<String>();
		if (!str.trim().equals("") && !str.toLowerCase().equals("null")) {
			try {
				String[] arrayStrings = str.split(regularExpression); 
				for (String s : arrayStrings) {
					strings.add(s);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return strings;
	}

	public static float Convert2Float(String val) {
		try {
			return Float.valueOf(val);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public static boolean isIP(String ip) {
		Pattern pattern = Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))");
		Matcher matcher = pattern.matcher(ip);
		return matcher.matches();
	}
	
	public static String bytestohexcode(byte[] abc) {
		char[] myco = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer s = new StringBuffer(abc.length * 2);
		for (int i = 0; i < abc.length; i++) {
			s.append(myco[(abc[i] >> 4) & 0x0f]);
			s.append(myco[abc[i] & 0x0f]);
		}

		return s.toString();
	}
	
	public static String GetSFCardIDBybytes(byte[] abc)
	{
		return GetSFCardID(bytestohexcode(abc));
	}
	
	public static String GetSFCardID(String cardId)
	{
		String newcardIdString=cardId;
		try {
			String cardId1 = cardId.substring(0, 2);
			String cardId2 = cardId.substring(2, 4);
			String cardId3 = cardId.substring(4, 6);
			String cardId4 = cardId.substring(6, 8);
			newcardIdString = cardId4 + cardId3 + cardId2 + cardId1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return newcardIdString;
	}

	
}
