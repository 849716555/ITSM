package com.goldsunny.itsm.util;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.goldsunny.itsm.view.MapApplication;

public class SysApplication extends MapApplication {
	private List<Activity> mList = new LinkedList<Activity>();
	private static SysApplication instance;

	public SysApplication() {
		
	}
	
	@Override  
    public void onCreate() {  
        super.onCreate();  
        CrashHandler crashHandler = CrashHandler.getInstance();  
        // 注册crashHandler  
        crashHandler.init(getApplicationContext());  
        // 发送以前没发送的报告(可选)  
        crashHandler.sendPreviousReportsToServer();   
    } 

	public synchronized static SysApplication getInstance() {
		if (null == instance) {
			instance = new SysApplication();
		}
		return instance;
	}

	// add Activity
	public void addActivity(Activity activity) {
		mList.add(activity);
		
	}

	public void addActivityHome(Activity mActivity) {
		for (Activity activity : mList) {
			if (activity != null)
				activity.finish();
		}
		mList.clear();
		mList.add(mActivity);
		
	}
	
	public void exit(Context c) {
		try {
			ServiceHelper.stopService(c);
			for (Activity activity : mList) {
				if (activity != null)
					activity.finish();
			}
			
			ActivityManager am = (ActivityManager)c.getSystemService (Context.ACTIVITY_SERVICE);
			am.killBackgroundProcesses(c.getPackageName());
			
		} catch (Exception e) {
			Log.e("exit", e+"");
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
	
	 
	 
}
