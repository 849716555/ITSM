package com.goldsunny.itsm.view;

import java.util.List;

import org.json.JSONArray;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.model.EmployeeMDL;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.Json2EntitiesUtil;
import com.goldsunny.itsm.util.LoginErrorDialog;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.util.UpdateVersion;
import com.goldsunny.itsm.webservicebll.SystemService;
import com.goldsunny.itsm.webservicebll.SystemServiceDate;

public class LoginActivity extends Activity {
	EditText tbUserName, tbPassword;
	Button btnLogin, btnSystemSetting;
	CheckBox checkRemberm, checkAutoLogic;
	SystemService systemService;
	SystemServiceDate systemServiceDate;
	ProgressDialog pDialog;
	String URL = "http://192.168.1.103:8081/HGCS.apk";
	String SD_PATH = "/sdcard/";
	String fileName = "sccc1.apk";
	int downloadResult = 0;
	boolean isLogining = false;
	String newAppVersion = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		SysApplication.getInstance().addActivity(this);
		systemService = new SystemService();
		systemServiceDate = new SystemServiceDate();
		tbUserName = (EditText) findViewById(R.id.tbUserName);
		tbPassword = (EditText) findViewById(R.id.tbPassWord);
		checkRemberm = (CheckBox) findViewById(R.id.checkRembM);
		checkAutoLogic = (CheckBox) findViewById(R.id.checkAutoLogin);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnSystemSetting = (Button) findViewById(R.id.btnSystemSetting);
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new loginThread()).start();
				pDialog = ProgressDialog.show(LoginActivity.this, "请等待...", "正在登录", true);
			}
		});
		btnSystemSetting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Message message = new Message();
				message.what = 4;
				mHandler.sendMessage(message);
			}
		});
		initDate();
		Intent inte = getIntent();
		String isHome = inte.getStringExtra("isHomeBack");
		if (CommonClass.isNullorEmpty(isHome) || !"true".equals(isHome)) {
			autoLogin();
		}
		//检查是否有版本更新
		UpdateVersion update=new UpdateVersion(this);
		update.AutoCheckVersion();
	}

	public void initDate() {
		SharedPreferences preferences = getSharedPreferences("SystemSet", Activity.MODE_PRIVATE);
		String ipString = preferences.getString("ServerIp", "");
		String WSPort = preferences.getString("WSPort", "");
		String WebSitePort = preferences.getString("WebSitePort", "");

		preferences = getSharedPreferences("LogicSet", Activity.MODE_PRIVATE);
		String rembPsw = preferences.getString("rembPsw", "");
		String autoLogic = preferences.getString("autoLogic", "");
		Log.v("logic", "rembPsw:" + rembPsw + "  autoLogic:" + autoLogic);
		if (!CommonClass.isNullorEmpty(rembPsw) && "true".equals(rembPsw)) {
			checkRemberm.setChecked(true);
		}
		if (!CommonClass.isNullorEmpty(autoLogic) && "true".equals(autoLogic)) {
			checkAutoLogic.setChecked(true);
		}
		GlobalData.SystemConfig.setServerIp(ipString);
		GlobalData.SystemConfig.setWSServerPort(WSPort);
		GlobalData.SystemConfig.setWebSitePort(WebSitePort);
		if (checkRemberm.isChecked()) {
			preferences = getSharedPreferences("UserInfo", Activity.MODE_PRIVATE);
			String userName = preferences.getString("userName", "");
			String passWord = preferences.getString("passWord", "");
			if (!CommonClass.isNullorEmpty(userName)) {
				tbUserName.setText(userName);
			}
			if (!CommonClass.isNullorEmpty(passWord)) {
				tbPassword.setText(passWord);
			}
		}
	}

	public void autoLogin() {
		if (checkAutoLogic.isChecked()) {
			new Thread(new loginThread()).start();
			pDialog = ProgressDialog.show(LoginActivity.this, "请等待...", "正在登录", true);
		}
	}

	class loginThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			GetLoginInfo(tbUserName.getText().toString().trim(), tbPassword.getText().toString().trim());
		}
	}

	private void GetLoginInfo(String userName, String passWord) {
		if (checkRemberm.isChecked()) {
			// 保存用户登录信息
			SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();// 获取编辑器
			editor.putString("userName", userName);
			editor.putString("passWord", passWord);
			editor.commit();// 提交修改
		}
		saveLogicSet();// 保存用户登录设置
		String result = systemServiceDate.UsreLogin(userName, passWord);
		try {
			if (!CommonClass.isNullorEmpty(result)) {
				if (!CommonClass.isNullorEmpty(result)) {
					JSONArray jsonArray = new JSONArray(result);
					List<EmployeeMDL> empList = Json2EntitiesUtil.GetEmployees(jsonArray);
					if (empList.size() > 0) {
						GlobalData.employeeMDL = empList.get(0);
						Log.i("login", "login:" + GlobalData.employeeMDL.getEmplName());
					} 
				}
				Message message = new Message();
				message.what = 3;
				mHandler.sendMessage(message);
			} else {
				Message message = new Message();
				message.what = 2;
				mHandler.sendMessage(message);
			}
		} catch (Exception e) {
			Log.i("login", "login:异常信息");
			Message message = new Message();
			message.what = 2;
			mHandler.sendMessage(message);
		}
	}

	public void saveLogicSet() {
		SharedPreferences sharedPreferences = getSharedPreferences("LogicSet", Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();// 获取编辑器
		editor.putString("rembPsw", String.valueOf(checkRemberm.isChecked()));
		editor.putString("autoLogic", String.valueOf(checkAutoLogic.isChecked()));
		editor.commit();// 提交修改
	}

	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (pDialog != null) {
				pDialog.dismiss();
			}
			switch (msg.what) {
			case 2:
				Dialog dialog = new LoginErrorDialog(LoginActivity.this, R.style.MyDialog);
				dialog.show();
				break;
			case 3:
				Intent i = new Intent(LoginActivity.this, HomeActivity.class);
				startActivity(i);
				LoginActivity.this.finish();
				break;
			case 4:
				Intent iSysSet = new Intent(LoginActivity.this, SystemCheckActivity.class);
				startActivity(iSysSet); 
				break;
			default:
				break;
			}
		};
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		// 如果是返回键,直接返回到桌面
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			CommonClass.showExitAlert(LoginActivity.this);
		}

		return super.onKeyDown(keyCode, event);
	} 
	

}
