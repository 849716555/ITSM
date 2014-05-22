package com.goldsunny.itsm.view;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.HttpClient;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.businesslogic.BaseDataBo;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.NetHelper;
import com.goldsunny.itsm.util.ObjectHelper;
import com.goldsunny.itsm.util.UpdateVersion;
import com.goldsunny.itsm.webservicebll.SystemService;

public class SystemCheckActivity extends Activity implements OnClickListener {
	TextView tbServerIp, tbWebSitePort, tbWSServerPort;
	Button btnSystemSetteingSave, btnBackToSSTop,btnCheckClientVer;
	String conAdress;
	HttpClient httpClient;
	SystemService systemService;
	BaseDataBo baseDataBo;
	ProgressDialog progress;
	String VersionNo = "";
	Thread loadDataThread;
	String URL ;
	String SD_PATH = "/sdcard/";
	String fileName = "ITSM.apk";
	int downloadResult = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.systemcheck);
		baseDataBo=new BaseDataBo();
		systemService=new SystemService();
		setInit();
		initDate();
	}

	public void setInit() {
		tbServerIp = (TextView) findViewById(R.id.tbServerIp);
		tbWebSitePort = (TextView) findViewById(R.id.tbWebSitePort);
		tbWSServerPort = (TextView) findViewById(R.id.tbWSServerPort);
		btnSystemSetteingSave = (Button) findViewById(R.id.btnSystemSetteingSave); 
		btnBackToSSTop = (Button) findViewById(R.id.btnBackToSSTop);
		btnCheckClientVer = (Button) findViewById(R.id.btnCheckClientVer); 
		btnSystemSetteingSave.setOnClickListener(this); 
		btnBackToSSTop.setOnClickListener(this);
		btnCheckClientVer.setOnClickListener(this);
		
		VersionNo = CommonClass.getAppVersionName(SystemCheckActivity.this);
		URL = GlobalData.DownloadURL;
	}

	public void initDate() {
		SharedPreferences preferences = getSharedPreferences("SystemSet", Activity.MODE_PRIVATE);
		String ipString = preferences.getString("ServerIp", "");
		String WSPort = preferences.getString("WSPort", "");
		String WebSitePort = preferences.getString("WebSitePort", "");
		GlobalData.SystemConfig.setServerIp(ipString);
		GlobalData.SystemConfig.setWSServerPort(WSPort);
		GlobalData.SystemConfig.setWebSitePort(WebSitePort);
		tbServerIp.setText(ipString);
		tbWebSitePort.setText(WebSitePort);
		tbWSServerPort.setText(WSPort);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSystemSetteingSave:
			String result = saveSystem();
			if (!result.equals("")) {
				DialogHelper.showTost(SystemCheckActivity.this, result);
				return;
			} else
				DialogHelper.showTost(SystemCheckActivity.this, "保存成功！");  
			break;
		case R.id.btnBackToSSTop:
			 back();
			break; 
		case R.id.btnCheckClientVer:
			//检查是否有版本更新
			UpdateVersion update=new UpdateVersion(this);
			update.HandCheckVersion();
			break;
		default:
			break;
		}
	}
	 
	 
	 
	
	 

	public String saveSystem() {
		String ipString = tbServerIp.getText().toString();
		if (!ObjectHelper.isIP(ipString))
			return "IP格式错误";

		String WSPort = tbWSServerPort.getText().toString();
		String WebSitePort = tbWebSitePort.getText().toString();
		if (!ObjectHelper.isNumeric(WSPort))
			return "端口格式错误";
		try {
			SharedPreferences sharedPreferences = getSharedPreferences("SystemSet", Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();// 获取编辑器
			editor.putString("ServerIp", ipString);
			editor.putString("WSPort", WSPort);
			editor.putString("WebSitePort", WebSitePort);
			editor.commit();// 提交修改
		} catch (Exception e) {
			return "保存异常";
		}

		GlobalData.SystemConfig.setServerIp(ipString);
		GlobalData.SystemConfig.setWSServerPort(WSPort);
		GlobalData.SystemConfig.setWebSitePort(WebSitePort);
		return "";
	}
 

	 

	public void back() {
		//Intent i = new Intent(SystemCheckActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//startActivity(i);
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			back();
		}
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}
	
	//检查更新
	private void showCheckVerDia() {
		progress = ProgressDialog.show(SystemCheckActivity.this, "请等待...",
				"正在检查新版本，请稍后...", true);
		new Thread(new checkClientVer()).start();
	}

	class checkClientVer implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (!systemService.checkWSConnected()) {
				Message message = new Message();
				message.what = 2;
				downloadAPK.sendMessage(message);
				return;
			}
			if (!VersionNo.equals(baseDataBo.getVersionNo())) { 
				Message message = new Message();
				message.what = 1;
				downloadAPK.sendMessage(message);
			} else {
				Message message = new Message();
				message.what = 3;
				downloadAPK.sendMessage(message);
			}
		}
	}

	Handler downloadAPK = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				showUpDateDialog();
				break;
			case 2:
				DialogHelper.showTost(SystemCheckActivity.this,
						"检查版本失败,请检查网络连接");
				progress.dismiss();
				break;
			case 3:
				DialogHelper.showTost(SystemCheckActivity.this,
						"已经是最新版本,不需要升级");
				progress.dismiss();
				break;
			default:
				break;
			}
		};
	};

	private void showUpDateDialog() {

		Builder builder = new Builder(SystemCheckActivity.this);
		builder.setMessage("发现新版本，建议马上升级？");
		builder.setTitle("提示");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				showDownloadDia();
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				progress.dismiss();
				dialog.dismiss();
			}
		});
		builder.create().show();

	}

	private void showDownloadDia() {
		progress.dismiss(); 
		
		new Thread(new downloadFileHandler()).start();
	}

	class downloadFileHandler implements Runnable {

		public void run() {
			// TODO Auto-generated method stub
			downloadFile();
		}
	}

	private Handler showDiao=new Handler(){

		@Override
		public void handleMessage(Message msg) { 
			progress = new ProgressDialog(SystemCheckActivity.this);  
			progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
			progress.setTitle("下载提示……");  
			progress.setMessage("正在下载安装包，请稍后。。。");    
			progress.setIndeterminate(false);   
			progress.setMax(fileLeng);  
			progress.show();  
			super.handleMessage(msg);
		}
		
	};
	private int fileLeng;
	private void downloadFile() {
		InputStream is = null;
		downloadResult = 0;
		try {
			// 创建、打开连接
			URL myUrl = new URL(URL);
			URLConnection connection = myUrl.openConnection();
			connection.connect();

			// 得到访问内容并保存在输入流中。
			is = connection.getInputStream();
			// 得到文件的总长度。注意这里有可能因得不到文件大小而抛出异常
			int len = connection.getContentLength(); 
			fileLeng=len;
			Message horiMsg=new Message();
			showDiao.sendMessage(horiMsg);
			int count=0;
			Log.e("down", String.valueOf(len));

			if (is != null) {
				File file = new File(SD_PATH + fileName);
				// 如果文件存在，则删除该文件。
				if (file.exists()) {
					file.delete();
				}
				// RandomAccessFile随机访问的文件类，可以从指定访问位置，为以后实现断点下载提供支持
				RandomAccessFile randomAccessFile = new RandomAccessFile(
						SD_PATH + fileName, "rwd");
				byte[] buffer = new byte[4096];
				int length = -1;
				while ((length = is.read(buffer)) != -1) {
					randomAccessFile.write(buffer, 0, length);
					Message msg = new Message();
					msg.arg1 = len;// 将文件大小保存
					// 用what变量来标示当前的状态
					msg.what = 0;
					// arg2标示本次循环完成的进度
					msg.arg2 = length;
					count=count+4096;
					progress.setProgress(count);
				}
				downloadResult = 1;
				is.close();
				randomAccessFile.close();

			}
		} catch (Exception e) {
			downloadResult = 2;
			Log.e("down", e.toString());
		} finally {
			Message message = new Message();
			message.what = 2;
			Bundle bundle = new Bundle();
			bundle.putInt("error", downloadResult);
			message.setData(bundle);
			downloadHandler.sendMessage(message);
		}
	}

	Handler downloadHandler = new Handler() {
		public void handleMessage(Message msg) {
			downloadResult = msg.getData().getInt("error");
			if (progress != null) {
				progress.dismiss();
			}
			if (downloadResult == 1) {
				// sysInfoDAL.SetSysVerNo(newAppVersion);
				install();
			}
			// 鐢ㄦ埛鍚嶅拰瀵嗙爜閿欒
			else {
				DialogHelper.showTost(SystemCheckActivity.this,
						"下载文件失败,请检查网络连接");
			}
		}
	};

	public void install() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse("file://" + SD_PATH + fileName),
				"application/vnd.android.package-archive");
		startActivity(intent);
	}
}
