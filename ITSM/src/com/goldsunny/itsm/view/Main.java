package com.goldsunny.itsm.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.webservicebll.SystemService;

public class Main extends Activity { 
	TextView startuptbview;
	ProgressBar bar;
	SystemService systemService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);    
			GlobalData.SystemConfig = CommonClass.GetSystemConfig(GlobalData.AppConfig);
			systemService = new SystemService();
			if (!CommonClass.checkNet(Main.this)) {
				GotoHome();
				return;
			}
			else {
				GotoHome();
				return;
			} 
		 
	}
	private void GotoHome()
	{ 
		Intent i = new Intent(Main.this, LoginActivity.class);
		startActivity(i);
		Main.this.finish();
	} 
	
	public String GetDeviceID()
	{
		final TelephonyManager tManager=(TelephonyManager)getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
		return tManager.getDeviceId();
	}

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 如果是返回键,直接返回到桌面
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
					GotoHome();
				}
		return false;
	}
}
