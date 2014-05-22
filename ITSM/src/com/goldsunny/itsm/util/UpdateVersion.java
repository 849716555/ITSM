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
 * 下载更新版本
 * 
 * @author yangwy
 * @version 1.0
 * @created 2014-5-10 下午8:23:12
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
	boolean isHomeCome=false;//是否从首页进来

	public UpdateVersion(Context context) {
		mContext = context;
		VersionNo = CommonClass.getAppVersionName(mContext);
		baseDataBo = new BaseDataBo();
		systemService = new SystemService();
		progress = new ProgressDialog(mContext);
		URL = GlobalData.DownloadURL;
	}

	/**
	 * 描述:手动检查是否有更新
	 */
	public void HandCheckVersion() {
		isHomeCome=false;
		progress = ProgressDialog.show(mContext, "请等待...", "正在检查新版本，请稍后...", true);
		new Thread(new checkClientVer()).start();
	}

	/**
	 * 描述:自动检查是否有更新
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
				// 得到访问内容并保存在输入流中。
				is = connection.getInputStream();
				XmlPullParser parser = Xml.newPullParser();
				parser.setInput(is, "utf-8");// 设置解析的数据源
				int type = parser.getEventType();
				while (type != XmlPullParser.END_DOCUMENT) {
					switch (type) {
					case XmlPullParser.START_TAG:
						if ("version".equals(parser.getName())) {
							ServerVersion=parser.nextText(); // 获取版本号
						} else if ("url".equals(parser.getName())) {
							URL=parser.nextText(); // 获取要升级的APK文件
						} else if ("description".equals(parser.getName())) {
							descri=parser.nextText(); // 获取该文件的信息
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
			Log.i("version", "version:"+ServerVersion+" 本地版本："+VersionNo+VersionNo.equals(ServerVersion));
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
				DialogHelper.showTost(mContext, "检查版本失败,请检查网络连接");
				progress.dismiss();
				break;
			case 3:
				DialogHelper.showTost(mContext, "已经是最新版本,不需要升级");
				progress.dismiss();
				break;
			default:
				break;
			}
		};
	};

	private void showUpDateDialog() {

		Builder builder = new Builder(mContext);
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

	private Handler showDiao = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			progress = new ProgressDialog(mContext);
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
			fileLeng = len;
			Message horiMsg = new Message();
			showDiao.sendMessage(horiMsg);
			int count = 0;
			Log.e("down", String.valueOf(len));

			if (is != null) {
				File file = new File(SD_PATH + fileName);
				// 如果文件存在，则删除该文件。
				if (file.exists()) {
					file.delete();
				}
				// RandomAccessFile随机访问的文件类，可以从指定访问位置，为以后实现断点下载提供支持
				RandomAccessFile randomAccessFile = new RandomAccessFile(SD_PATH + fileName, "rwd");
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
			// 鐢ㄦ埛鍚嶅拰瀵嗙爜閿欒
			else {
				DialogHelper.showTost(mContext, "下载文件失败,请检查网络连接");
			}
		}
	};

	public void install() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse("file://" + SD_PATH + fileName), "application/vnd.android.package-archive");
		mContext.startActivity(intent);
	}
}
