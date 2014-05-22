package com.goldsunny.itsm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

/**
 * 异步加载图片
 * 
 * @author yangwy
 * @version 1.0
 * @created 2014-5-13 上午11:25:06
 */
public class AsyncBitmapLoader {
	/**
	 * 内存图片软引用缓冲
	 */
	private HashMap<String, SoftReference<String>> imageCache = null;

	public AsyncBitmapLoader() {
		imageCache = new HashMap<String, SoftReference<String>>();
	}

	public String loadBitmap(final ImageView imageView,final String imageURL,final ImageCallBack imageCallBack) {
		// 在内存缓存中，则返回Bitmap对象
		if (imageCache.containsKey(imageURL)) {
			SoftReference<String> reference = imageCache.get(imageURL);
			String bitmap = reference.get();
			if (bitmap != null) {
				return bitmap;
			}
		} else {
			/**
			 * 加上一个对本地缓存的查找
			 */
			String bitmapName = imageURL.substring(imageURL.lastIndexOf("/") + 1);
			File cacheDir = new File(GlobalData.PhotoPath);
			File[] cacheFiles = cacheDir.listFiles();
			int i = 0;
			if (null != cacheFiles) {
				for (; i < cacheFiles.length; i++) {
					if (bitmapName.equals(cacheFiles[i].getName())) {
						break;
					}
				}

				if (i < cacheFiles.length) {
					return GlobalData.PhotoPath + bitmapName;
				}
			}
		}

		final Handler handler = new Handler() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see android.os.Handler#handleMessage(android.os.Message)
			 */
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				imageCallBack.imageLoad(imageView, (Bitmap) msg.obj);
			}
		};

		// 如果不在内存缓存中，也不在本地（被jvm回收掉），则开启线程下载图片
		new Thread() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				// TODO Auto-generated method stub
				FileOutputStream fos=null;
				InputStream bitmapIs=null;
				File dir = new File(GlobalData.PhotoPath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				try {
					String filePath=GlobalData.PhotoPath + imageURL.substring(imageURL.lastIndexOf("/") + 1);
					File bitmapFile = new File(GlobalData.PhotoPath + imageURL.substring(imageURL.lastIndexOf("/") + 1));
					if (!bitmapFile.exists()) {
						bitmapFile.createNewFile();
					}
					URL url = new URL(imageURL);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					bitmapIs = connection.getInputStream();

					Bitmap bitmap = BitmapFactory.decodeStream(bitmapIs);
					imageCache.put(imageURL, new SoftReference<String>(filePath));
					Message msg = handler.obtainMessage(0, bitmap);
					handler.sendMessage(msg);
					fos = new FileOutputStream(bitmapFile);
					String fileType=imageURL.substring(imageURL.lastIndexOf("/") + 1);
					bitmap.compress(ImageUtil.getCompressType(fileType), 100, fos);
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (fos != null)
							fos.close();
						if(bitmapIs!=null)
							bitmapIs.close();
					} catch (IOException e) { 
						e.printStackTrace();
					}
				}
			}
		}.start();

		return null;
	}

	public interface ImageCallBack {
		public void imageLoad(ImageView imageView, Bitmap bitmap);
	}
}
