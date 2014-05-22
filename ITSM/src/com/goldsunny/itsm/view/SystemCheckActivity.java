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
				DialogHelper.showTost(SystemCheckActivity.this, "����ɹ���");  
			break;
		case R.id.btnBackToSSTop:
			 back();
			break; 
		case R.id.btnCheckClientVer:
			//����Ƿ��а汾����
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
			return "IP��ʽ����";

		String WSPort = tbWSServerPort.getText().toString();
		String WebSitePort = tbWebSitePort.getText().toString();
		if (!ObjectHelper.isNumeric(WSPort))
			return "�˿ڸ�ʽ����";
		try {
			SharedPreferences sharedPreferences = getSharedPreferences("SystemSet", Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();// ��ȡ�༭��
			editor.putString("ServerIp", ipString);
			editor.putString("WSPort", WSPort);
			editor.putString("WebSitePort", WebSitePort);
			editor.commit();// �ύ�޸�
		} catch (Exception e) {
			return "�����쳣";
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
	
	//������
	private void showCheckVerDia() {
		progress = ProgressDialog.show(SystemCheckActivity.this, "��ȴ�...",
				"���ڼ���°汾�����Ժ�...", true);
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
						"���汾ʧ��,������������");
				progress.dismiss();
				break;
			case 3:
				DialogHelper.showTost(SystemCheckActivity.this,
						"�Ѿ������°汾,����Ҫ����");
				progress.dismiss();
				break;
			default:
				break;
			}
		};
	};

	private void showUpDateDialog() {

		Builder builder = new Builder(SystemCheckActivity.this);
		builder.setMessage("�����°汾����������������");
		builder.setTitle("��ʾ");
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				showDownloadDia();
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

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
			progress.setTitle("������ʾ����");  
			progress.setMessage("�������ذ�װ�������Ժ󡣡���");    
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
			// ������������
			URL myUrl = new URL(URL);
			URLConnection connection = myUrl.openConnection();
			connection.connect();

			// �õ��������ݲ��������������С�
			is = connection.getInputStream();
			// �õ��ļ����ܳ��ȡ�ע�������п�����ò����ļ���С���׳��쳣
			int len = connection.getContentLength(); 
			fileLeng=len;
			Message horiMsg=new Message();
			showDiao.sendMessage(horiMsg);
			int count=0;
			Log.e("down", String.valueOf(len));

			if (is != null) {
				File file = new File(SD_PATH + fileName);
				// ����ļ����ڣ���ɾ�����ļ���
				if (file.exists()) {
					file.delete();
				}
				// RandomAccessFile������ʵ��ļ��࣬���Դ�ָ������λ�ã�Ϊ�Ժ�ʵ�ֶϵ������ṩ֧��
				RandomAccessFile randomAccessFile = new RandomAccessFile(
						SD_PATH + fileName, "rwd");
				byte[] buffer = new byte[4096];
				int length = -1;
				while ((length = is.read(buffer)) != -1) {
					randomAccessFile.write(buffer, 0, length);
					Message msg = new Message();
					msg.arg1 = len;// ���ļ���С����
					// ��what��������ʾ��ǰ��״̬
					msg.what = 0;
					// arg2��ʾ����ѭ����ɵĽ���
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
			// 用户名和密码错误
			else {
				DialogHelper.showTost(SystemCheckActivity.this,
						"�����ļ�ʧ��,������������");
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
