package com.goldsunny.itsm.util;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.goldsunny.itsm.model.SystemConfigMDL;

public class CommonClass {
	public static boolean checkSDCard() {
		// TODO Auto-generated method stub
		/* 判断存储卡是否存在 */
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0) {
				return "";
			}
		} catch (Exception e) {
			Log.e("VersionInfo", "Exception", e);
		}
		return versionName;
	}

	public static boolean checkNet(Context context) {// 获取手机所有连接管理对象（包括对wi-fi,

		// net等连接的管理）

		try {

			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (connectivity != null) {

				// 获取网络连接管理的对象

				NetworkInfo info = connectivity.getActiveNetworkInfo();

				// System.out.println(">>>>>>>>>>>>Net:"+info);

				if (info == null || !info.isAvailable()) {

					return false;

				} else {

					return true;

				}

				// if (info != null && info.isConnected()) {

				// // 判断当前网络是否已经连接

				// if (info.getState() == NetworkInfo.State.CONNECTED) {

				// return true;

				// }

				// }

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;

	}

	public static void showExitAlert(final Context context) {
		Builder builder = new Builder(context);
		builder.setMessage("确定退出程序吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				try {

					SysApplication.getInstance().exit(context);

				} catch (Exception e) {
					// TODO: handle exception
				}

				dialog.dismiss();

			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	public static int UploadStringToInt(String value) {
		if (value.equals("未上传"))
			return 0;
		else if (value.equals("已上传"))
			return 1;
		else
			return 0;
	}

	public static String UploadIntToString(int value) {
		String result = "";
		switch (value) {
		case 1:
			result = "已上传";
			break;
		default:
			result = "未上传";
			break;
		}
		return result;
	}

	public static String GetGuid() {
		Date date = new Date();
		String guidString = GetGuid(
				ObjectHelper.Convert2String(date, "yyyyMMddHHmmssSSS"), 0, 0);
		return guidString;
	}

	public static String GetGuid(int laneId) {
		Date date = new Date();
		Random random = new Random();
		String dateString = GetGuid(
				ObjectHelper.Convert2String(date, "yyyyMMddHHmmssSSS"), laneId,
				random.nextInt());
		return dateString;
	}

	public static String GetGuid(String dateString, int laneId, int recordId) {

		if (recordId < 0)
			recordId = 0 - recordId;
		String lanesString = StringPlusCount(
				ObjectHelper.Convert2String(laneId), 4, "0");
		String recordIdString = StringPlusCount(
				ObjectHelper.Convert2String(recordId), 3, "0");
		String AreaRoadStationNoString = getAreaRoadStationNoString();
		String GuidString = dateString + AreaRoadStationNoString + lanesString
				+ recordIdString;
		return GuidString;
	}

	public static String StringPlusCount(String str, int count, String c) {

		int strLen = str.length();
		if (strLen > count)
			return str.substring(0, count - 1);
		while (strLen < count) {
			str = c + str;
			strLen += 1;
		}
		return str;
	}

	private static String getAreaRoadStationNoString() {
		String areaNoString = ObjectHelper
				.Convert2String(GlobalData.SystemConfig.getCurrAreaNo());

		String roadString = StringPlusCount(
				ObjectHelper.Convert2String(GlobalData.SystemConfig
						.getCurrRoadNo()), 4, "0");

		String stationsString = StringPlusCount(
				ObjectHelper.Convert2String(GlobalData.SystemConfig
						.getCurrStationNo()), 4, "0");

		return areaNoString + roadString + stationsString;
	}

	public static SystemConfigMDL GetSystemConfig(HashMap<String, String> maps) {
		SystemConfigMDL configMDL = new SystemConfigMDL();
		if (maps == null)
			return configMDL;
		Iterator<Entry<String, String>> entrySetIterator = maps.entrySet()
				.iterator();
		for (Iterator iter = maps.entrySet().iterator(); iter.hasNext();) {
			try {
				Map.Entry element = (Map.Entry) iter.next();
				String keyObject = element.getKey().toString();
				String valueObject = element.getValue().toString();
				if (keyObject.equals("CurrAreaNo"))
					configMDL.setCurrAreaNo(ObjectHelper
							.Convert2Int(valueObject));
				else if (keyObject.equals("CurrRoadNo"))
					configMDL.setCurrRoadNo(ObjectHelper
							.Convert2Int(valueObject));
				else if (keyObject.equals("CurrStationNo"))
					configMDL.setCurrStationNo(ObjectHelper
							.Convert2Int(valueObject));
				else if (keyObject.equals("ServerIp"))
					configMDL.setServerIp(valueObject);
				else if (keyObject.equals("WebSitePort"))
					configMDL.setWebSitePort(valueObject);
				else if (keyObject.equals("DefalutBTAddress"))
					configMDL.setDefalutBTAddress(valueObject);
				else if (keyObject.equals("IsDataSyn"))
					configMDL.setIsDataSyn(valueObject.equals("1") ? true
							: false);
				else if (keyObject.equals("IsAutoMatch"))
					configMDL.setIsAutoMatch(valueObject.equals("1") ? true
							: false);
				else if (keyObject.equals("IsTimeSyn"))
					configMDL.setIsTimeSyn(valueObject.equals("1") ? true
							: false);
				else if (keyObject.equals("DownloadAPKUrl"))
					configMDL.setDownloadAPKUrl(valueObject);
				else if (keyObject.equals("WSServerIP"))
					configMDL.setWSServerIP(valueObject);
				else if (keyObject.equals("WSServerPort"))
					configMDL.setWSServerPort(valueObject);
				else if (keyObject.equals("DefalutLaneId"))
					configMDL.setDefalutLaneId(ObjectHelper
							.Convert2Int(valueObject));
				else if (keyObject.equals("DelDataDay"))
					configMDL.setDelDataDay(ObjectHelper
							.Convert2Int(valueObject));
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		if (configMDL.getServerIp() != null
				&& configMDL.getWSServerPort() != null)
			configMDL.setServerWebservice("http://" + configMDL.getServerIp()
					+ ":" + configMDL.getWSServerPort() + "/");
		if (configMDL.getServerIp() != null) {
			if (configMDL.getWebSitePort() != null
					&& !configMDL.getWebSitePort().equals(""))
				configMDL.setServerWebsite("http://" + configMDL.getServerIp()
						+ ":" + configMDL.getWebSitePort());
			else
				configMDL.setServerWebsite("http://" + configMDL.getServerIp());
		}
		return configMDL;

	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.width=LayoutParams.MATCH_PARENT;
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
	
	public static boolean isNullorEmpty(String str)
	{
		if(str!=null && !"".equals(str))
		{
			 return false;
		}
		return true;
	} 
	
}
