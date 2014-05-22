package com.goldsunny.itsm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.goldsunny.itsm.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class UpdateService extends Service {
	private int titleId = 0;
	private String notificationTitle = "机电运维管理系统";
	private File updateDir = null;
	private File updateFile = null;
	private NotificationManager updateNotificationManager = null;
	private Notification updateNotification = null;
	private Intent updateIntent;
	private PendingIntent pendingIntent = null;
	private final static int DOWNLAOD_COMPLETE = 1;
	private final static int DOWNLAOD_FAIL = 0;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		titleId = intent.getIntExtra("titleId", 0);
		if (android.os.Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			updateDir = new File(Environment.getExternalStorageDirectory(),
					GlobalData.downloadDir);
			updateFile = new File(updateDir.getPath(), getResources()
					.getString(titleId) + ".apk");
		}
		this.updateNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		this.updateNotification = new Notification();

		//updateIntent = new Intent(this, SystemSettingActivity.class);
		//pendingIntent = PendingIntent.getActivity(this, 0, updateIntent, 0);
		updateNotification.icon = R.drawable.ic_launcher;
		updateNotification.setLatestEventInfo(this, notificationTitle, "0%",
				pendingIntent);
		updateNotificationManager.notify(0, updateNotification);

		new Thread(new updateRunnable()).start();
		return super.onStartCommand(intent, flags, startId);
	}

	private Handler updateHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case DOWNLAOD_COMPLETE:
				Uri uri = Uri.fromFile(updateFile);
				Intent installIntent = new Intent(Intent.ACTION_VIEW);
				installIntent.setDataAndType(uri,
						"application/vnd.android.package-archive");
				pendingIntent = PendingIntent.getActivity(UpdateService.this,
						0, installIntent, 0);
				updateNotification.defaults = Notification.DEFAULT_SOUND;
				updateNotification.setLatestEventInfo(UpdateService.this,
						notificationTitle, "下载完成，点击安装", pendingIntent);
				updateNotificationManager.notify(0, updateNotification);
				break;
			case DOWNLAOD_FAIL:
				updateNotification.setLatestEventInfo(UpdateService.this,
						notificationTitle, "下载完成，点击安装", pendingIntent);
				updateNotificationManager.notify(0, updateNotification);
				break;
			default:
				break;
			}
		};
	};

	class updateRunnable implements Runnable {
		Message message = updateHandler.obtainMessage();

		@Override
		public void run() {
			// TODO Auto-generated method stub
			message.what = DOWNLAOD_COMPLETE;
			try {
				if (!updateDir.exists()) {
					updateDir.mkdirs();
				}
				if (!updateFile.exists()) {
					updateFile.createNewFile();
				}
				long downloadSize = downloadUpdateFile("", updateFile);
				if (downloadSize > 0) {
					updateHandler.sendMessage(message);
				}
			} catch (Exception e) {
				// TODO: handle exception
				message.what = DOWNLAOD_FAIL;
				updateHandler.sendMessage(message);
			}
		}

	}

	public long downloadUpdateFile(String downloadUrl, File saveFile)
			throws Exception {
		int downloadCount=0;
		int currentSize=0;
		int updateTotalSize=0;
		long totalSize = 0;
		HttpURLConnection httpURLConnection=null;
		InputStream is=null;
		FileOutputStream fos=null;
		try {
		URL	url=new URL(downloadUrl);
		httpURLConnection=(HttpURLConnection)url.openConnection();
		httpURLConnection.setRequestProperty("User-Agent", "PacificHttpClient");
		if(currentSize>0)
		{
			httpURLConnection.setRequestProperty("RANGE", "bytes="+currentSize+"-");
		}
		httpURLConnection.setConnectTimeout(10000);
		httpURLConnection.setReadTimeout(20000);
		updateTotalSize=httpURLConnection.getContentLength();
		if(httpURLConnection.getResponseCode()==404)
		{
			throw new Exception("fail!");
		}
		is=httpURLConnection.getInputStream();
		fos=new FileOutputStream(saveFile,false);
		byte[] buffer=new byte[4096];
		int readSize=0;
		while ((readSize=is.read(buffer))>-1) {
			fos.write(buffer, 0, readSize);
			totalSize+=readSize;
			if((downloadCount==0)||(int)(totalSize*100)/updateTotalSize-10>downloadCount)
			{
				downloadCount+=10;
				updateNotification.setLatestEventInfo(UpdateService.this, "正在下载", (int)totalSize*100/updateTotalSize+"%", pendingIntent);
				updateNotificationManager.notify(0, updateNotification);
			}
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			if(httpURLConnection!=null)
			{
				httpURLConnection.disconnect();
			}
			if(is!=null)
			{
				is.close();
			}
			if(fos!=null)
			{
				fos.close();
			}
				
		}
		return totalSize;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
