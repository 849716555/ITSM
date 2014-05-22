package com.goldsunny.itsm.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

import org.xmlpull.v1.XmlPullParser;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Xml;

import com.goldsunny.itsm.businesslogic.BaseDataBo;
import com.goldsunny.itsm.webservicebll.SystemService;

/**
 * ���ظ��°汾
 * 
 * @author yangwy
 * @version 1.0
 * @created 2014-5-10 ����8:23:12
 */
public class UpdateVersion {

	SystemService systemService;
	BaseDataBo baseDataBo;
	ProgressDialog progress;
	String VersionNo = "";
	Thread loadDataThread;
	String URL;
	String SD_PATH = "/sdcard/";
	String fileName = "ITSM.apk";
	int downloadResult = 0;
	Context mContext;
	InputStream is = null;
	String ServerVersion,descri;
	boolean isHomeCome=false;//�Ƿ����ҳ����

	public UpdateVersion(Context context) {
		mContext = context;
		VersionNo = CommonClass.getAppVersionName(mContext);
		baseDataBo = new BaseDataBo();
		systemService = new SystemService();
		progress = new ProgressDialog(mContext);
		URL = GlobalData.DownloadURL;
	}

	/**
	 * ����:�ֶ�����Ƿ��и���
	 */
	public void HandCheckVersion() {
		isHomeCome=false;
		progress = ProgressDialog.show(mContext, "��ȴ�...", "���ڼ���°汾�����Ժ�...", true);
		new Thread(new checkClientVer()).start();
	}

	/**
	 * ����:�Զ�����Ƿ��и���
	 */
	public void AutoCheckVersion() {
		isHomeCome=true;
		new Thread(new checkClientVer()).start();
	}

	class checkClientVer implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				URL myUrl = new URL(URL);
				URLConnection connection = myUrl.openConnection();
				connection.connect();
				// �õ��������ݲ��������������С�
				is = connection.getInputStream();
				XmlPullParser parser = Xml.newPullParser();
				parser.setInput(is, "utf-8");// ���ý���������Դ
				int type = parser.getEventType();
				while (type != XmlPullParser.END_DOCUMENT) {
					switch (type) {
					case XmlPullParser.START_TAG:
						if ("version".equals(parser.getName())) {
							ServerVersion=parser.nextText(); // ��ȡ�汾��
						} else if ("url".equals(parser.getName())) {
							URL=parser.nextText(); // ��ȡҪ������APK�ļ�
						} else if ("description".equals(parser.getName())) {
							descri=parser.nextText(); // ��ȡ���ļ�����Ϣ
						}
						break;
					}
					type = parser.next();
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			if (!systemService.checkWSConnected()) {
				Message message = new Message();
				message.what = 2;
				downloadAPK.sendMessage(message);
				return;
			} 
			Log.i("version", "version:"+ServerVersion+" ���ذ汾��"+VersionNo+VersionNo.equals(ServerVersion));
			double version=Double.parseDouble(VersionNo);
			double SerVions=Double.parseDouble(ServerVersion); 
			if (version !=SerVions ) {  
				Message message = new Message();
				message.what = 1;
				downloadAPK.sendMessage(message);
			} else {
				if(!isHomeCome){
					Message message = new Message();
					message.what = 3;
					downloadAPK.sendMessage(message);
				} 
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
				DialogHelper.showTost(mContext, "���汾ʧ��,������������");
				progress.dismiss();
				break;
			case 3:
				DialogHelper.showTost(mContext, "�Ѿ������°汾,����Ҫ����");
				progress.dismiss();
				break;
			default:
				break;
			}
		};
	};

	private void showUpDateDialog() {

		Builder builder = new Builder(mContext);
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

	private Handler showDiao = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			progress = new ProgressDialog(mContext);
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
			fileLeng = len;
			Message horiMsg = new Message();
			showDiao.sendMessage(horiMsg);
			int count = 0;
			Log.e("down", String.valueOf(len));

			if (is != null) {
				File file = new File(SD_PATH + fileName);
				// ����ļ����ڣ���ɾ�����ļ���
				if (file.exists()) {
					file.delete();
				}
				// RandomAccessFile������ʵ��ļ��࣬���Դ�ָ������λ�ã�Ϊ�Ժ�ʵ�ֶϵ������ṩ֧��
				RandomAccessFile randomAccessFile = new RandomAccessFile(SD_PATH + fileName, "rwd");
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
					count = count + 4096;
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
				DialogHelper.showTost(mContext, "�����ļ�ʧ��,������������");
			}
		}
	};

	public void install() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse("file://" + SD_PATH + fileName), "application/vnd.android.package-archive");
		mContext.startActivity(intent);
	}
}
