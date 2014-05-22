package com.goldsunny.itsm.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.MyLocationOverlay.LocationMode;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.goldsunny.itsm.R;
import com.goldsunny.itsm.util.SysApplication;

public class MyNearFaultActivity extends Activity {

	private View viewCache = null;
	Button btnRecoveryMainToTop, btnShowLocation, requestLocButton;
	private locationOverlay myLocationOverlay = null;
	MapView mMapView = null; // ��ͼView
	private MapController mMapController = null;
	// ��λ���
	LocationClient mLocClient;
	LocationData locData = null;
	public MyLocationListenner myListener = new MyLocationListenner();

	boolean isRequest = false;// �Ƿ��ֶ���������λ
	boolean isFirstLoc = true;// �Ƿ��״ζ�λ
	private ListView mapList;
	private LinearLayout inserLayout;// չʾ��ϸ

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		SysApplication.getInstance().addActivity(this);
		/**
		 * ʹ�õ�ͼsdkǰ���ȳ�ʼ��BMapManager. BMapManager��ȫ�ֵģ���Ϊ���MapView���ã�����Ҫ��ͼģ�鴴��ǰ������
		 * ���ڵ�ͼ��ͼģ�����ٺ����٣�ֻҪ���е�ͼģ����ʹ�ã�BMapManager�Ͳ�Ӧ������
		 */
		MapApplication app = (MapApplication) this.getApplication();
		if (app.mBMapManager == null) {
			app.mBMapManager = new BMapManager(getApplicationContext());
			app.mBMapManager.init(MapApplication.strKey, new MapApplication.MyGeneralListener());
		}

		viewCache = getLayoutInflater().inflate(R.layout.my_fault_loaction, null);
		// mainLayout=(RelativeLayout)findViewById(R.layout.faultloaction);
		setContentView(viewCache);
		SysApplication.getInstance().addActivity(this);
		requestLocButton = (Button) findViewById(R.id.btnLocation);
		OnClickListener btnClickListener = new OnClickListener() {
			public void onClick(View v) {
				requestLocClick();
				requestLocButton.setText("��λ");
			}
		};
		requestLocButton.setOnClickListener(btnClickListener);
		// ��ͼ��ʼ��
		mMapView = (MapView) findViewById(R.id.bmapView);
		mMapController = mMapView.getController();
		mMapView.getController().setZoom(14);
		mMapView.getController().enableClick(true);
		mMapView.setBuiltInZoomControls(true);
		// ��λ��ʼ��
		mLocClient = new LocationClient(this);
		locData = new LocationData();
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// ��gps
		option.setCoorType("bd09ll"); // ������������
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();

		// ��λͼ���ʼ��
		myLocationOverlay = new locationOverlay(mMapView);
		// ���ö�λ����
		myLocationOverlay.setData(locData);
		// ��Ӷ�λͼ��
		mMapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		// �޸Ķ�λ���ݺ�ˢ��ͼ����Ч
		mMapView.refresh();
		// initOverlay();
		init();
		btnRecoveryMainToTop = (Button) findViewById(R.id.btnRecoveryMainToTop);
		btnRecoveryMainToTop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
			}
		});

	}

	private List<Map<String, Object>> mapeList = new ArrayList<Map<String, Object>>();// �б�����
	SimpleAdapter arrayAdapte;// �б�������

	public void init() {
		mapList = (ListView) findViewById(R.id.list_Fault_Map);
		arrayAdapte = new SimpleAdapter(this, mapeList, R.layout.map_lisnt_item, new String[] { "name" },
				new int[] { R.id.text_map_main_item });
		mapList.setAdapter(arrayAdapte);
		mapList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (arg2 < 3) {

				}

			}
		});
		inserLayout = (LinearLayout) findViewById(R.id.showDetail);
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				break;
			case 2:
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}

	};

	/**
	 * �ֶ�����һ�ζ�λ����
	 */
	public void requestLocClick() {
		isRequest = true;
		mLocClient.requestLocation();
	}

	/**
	 * ��λSDK��������
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;

			locData.latitude = location.getLatitude();
			locData.longitude = location.getLongitude();
			// �������ʾ��λ����Ȧ����accuracy��ֵΪ0����
			locData.accuracy = location.getRadius();
			// �˴��������� locData�ķ�����Ϣ, �����λ SDK δ���ط�����Ϣ���û������Լ�ʵ�����̹�����ӷ�����Ϣ��
			locData.direction = location.getDerect();
			// ���¶�λ����
			myLocationOverlay.setData(locData);
			// ����ͼ������ִ��ˢ�º���Ч
			mMapView.refresh();
			// ���ֶ�����������״ζ�λʱ���ƶ�����λ��
			if (isRequest || isFirstLoc) {
				// �ƶ���ͼ����λ��
				Log.d("LocationOverlay", "receive location, animate to it");
				mMapController.animateTo(new GeoPoint((int) (locData.latitude * 1e6), (int) (locData.longitude * 1e6)));
				isRequest = false;
				myLocationOverlay.setLocationMode(LocationMode.NORMAL);
				requestLocButton.setText("��λ");
			}
			// �״ζ�λ���
			isFirstLoc = false;
		}

		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null) {
				return;
			}
		}
	}

	// �̳�MyLocationOverlay��дdispatchTapʵ�ֵ������
	public class locationOverlay extends MyLocationOverlay {

		public locationOverlay(MapView mapView) {
			super(mapView);
			// TODO Auto-generated constructor stub
		}

	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// �˳�ʱ���ٶ�λ
		if (mLocClient != null)
			mLocClient.stop();
		mMapView.destroy();
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mMapView.onRestoreInstanceState(savedInstanceState);
	}
}
