package com.goldsunny.itsm.view;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;

public class MapApplication extends Application {

	private static MapApplication mInstance = null;
	public boolean m_bKeyRight = true;
	BMapManager mBMapManager = null;
    //RHpErIQ0FLoygLvgLzIzd9uv ����key TbPBEPplu3FNi2gUz2oC5UNw ���Ի���key
	public static final String strKey = "RHpErIQ0FLoygLvgLzIzd9uv";

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		initEngineManager(this);
	}

	public void initEngineManager(Context context) {
		if (mBMapManager == null) {
			mBMapManager = new BMapManager(context);
		}

		if (!mBMapManager.init(strKey, new MyGeneralListener())) {
			Toast.makeText(MapApplication.getInstance().getApplicationContext(), "BMapManager  ��ʼ������!",
					Toast.LENGTH_LONG).show();
		}
	}

	public static MapApplication getInstance() {
		return mInstance;
	}

	// �����¼���������������ͨ�������������Ȩ��֤�����
	static class MyGeneralListener implements MKGeneralListener {

		@Override
		public void onGetNetworkState(int iError) {
			if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
				Toast.makeText(MapApplication.getInstance().getApplicationContext(), "���������������", Toast.LENGTH_LONG)
						.show();
			} else if (iError == MKEvent.ERROR_NETWORK_DATA) {
				Toast.makeText(MapApplication.getInstance().getApplicationContext(), "������ȷ�ļ���������", Toast.LENGTH_LONG)
						.show();
			}
		}

		@Override
		public void onGetPermissionState(int iError) {
			// ����ֵ��ʾkey��֤δͨ��
			if (iError != 0) {
				// ��ȨKey����
				Toast.makeText(MapApplication.getInstance().getApplicationContext(),
						"������ȷ����ȨKey,������������������Ƿ������� " , Toast.LENGTH_LONG).show();
				MapApplication.getInstance().m_bKeyRight = false;
			} else {
				MapApplication.getInstance().m_bKeyRight = true; 
			}
		}
	}
}