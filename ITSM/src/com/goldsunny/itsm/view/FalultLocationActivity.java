package com.goldsunny.itsm.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.Ground;
import com.baidu.mapapi.map.GroundOverlay;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.MyLocationOverlay.LocationMode;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.goldsunny.itsm.R;
import com.goldsunny.itsm.model.Mai_FaultReportMDL;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.GeoPointUtil;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.SysApplication;

/**
 * 此demo用来展示如何结合定位SDK实现定位，并使用MyLocationOverlay绘制定位位置同时展示如何使用自定义图标绘制并点击时弹出泡泡
 * 
 */
@SuppressLint("ShowToast")
public class FalultLocationActivity extends Activity {
	private enum E_BUTTON_TYPE {
		LOC, COMPASS, FOLLOW
	}

	private int flag = 0;

	private E_BUTTON_TYPE mCurBtnType;

	// 定位相关
	LocationClient mLocClient;
	LocationData locData = null;
	public MyLocationListenner myListener = new MyLocationListenner();

	// 定位图层
	locationOverlay myLocationOverlay = null;
	// 弹出泡泡图层
	private PopupOverlay pop = null;// 弹出泡泡图层，浏览节点时使用
	private TextView popupText = null;// 泡泡view
	private View viewCache = null;

	// 地图相关，使用继承MapView的MyLocationMapView目的是重写touch事件实现泡泡处理
	// 如果不处理touch事件，则无需继承，直接使用MapView即可
	MyLocationMapView mMapView = null; // 地图View
	private MapController mMapController = null;
 
	Button requestLocButton = null;
	Button btnRecoveryMainToTop,btnShowLocation;
	boolean isRequest = false;// 是否手动触发请求定位
	boolean isFirstLoc = true;// 是否首次定位

	private MyOverlay mOverlay = null;
	private ArrayList<OverlayItem> mItems = null;
	private View popupInfo = null;
	private View popupLeft = null;
	private View popupRight = null;
	private Button button, btnRetun;
	private MapView.LayoutParams layoutParam = null;
	private OverlayItem mCurItem = null;
	private ListView mapList; 

	// ground overlay
	private GroundOverlay mGroundOverlay;
	private Ground mGround;
	private double mLon5 = 116.380338;
	private double mLat5 = 39.92235;
	private double mLon6 = 116.414977;
	private double mLat6 = 39.947246;

	private Button btShowLocation;
	private LinearLayout inserLayout;
	View view;
	private RelativeLayout mainLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		/**
		 * 使用地图sdk前需先初始化BMapManager. BMapManager是全局的，可为多个MapView共用，它需要地图模块创建前创建，
		 * 并在地图地图模块销毁后销毁，只要还有地图模块在使用，BMapManager就不应该销毁
		 */
		MapApplication app = (MapApplication) this.getApplication();
		if (app.mBMapManager == null) {
			app.mBMapManager = new BMapManager(getApplicationContext());
			app.mBMapManager.init(MapApplication.strKey, new MapApplication.MyGeneralListener());
		}
		
		viewCache = getLayoutInflater().inflate(R.layout.faultloaction, null);
		// mainLayout=(RelativeLayout)findViewById(R.layout.faultloaction);
		setContentView(viewCache);
		SysApplication.getInstance().addActivity(this);
		requestLocButton = (Button) findViewById(R.id.btnLocation);
		OnClickListener btnClickListener = new OnClickListener() {
			public void onClick(View v) {
				requestLocClick();
				myLocationOverlay.setLocationMode(LocationMode.NORMAL);
				requestLocButton.setText("定位");
			}
		};
		requestLocButton.setOnClickListener(btnClickListener);
		// 地图初始化
		mMapView = (MyLocationMapView) findViewById(R.id.bmapView);
		mMapController = mMapView.getController();
		mMapView.getController().setZoom(14);
		mMapView.getController().enableClick(true);
		mMapView.setBuiltInZoomControls(true); 
		// 定位初始化
		mLocClient = new LocationClient(this);
		locData = new LocationData();
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();

		// 定位图层初始化
		myLocationOverlay = new locationOverlay(mMapView);
		// 设置定位数据
		myLocationOverlay.setData(locData);
		// 添加定位图层
		mMapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		// 修改定位数据后刷新图层生效
		mMapView.refresh();
		initOverlay();
		init();
		btnRecoveryMainToTop=(Button)findViewById(R.id.btnRecoveryMainToTop);
		btnRecoveryMainToTop.setOnClickListener(new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
			}
		});
		btnShowLocation=(Button)findViewById(R.id.btnShowLocation);
		btnShowLocation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			GeoPoint point=	mMapView.getMapCenter();
			int lat=mMapView.getLatitudeSpan();
			DialogHelper.showDialog(FalultLocationActivity.this, "当前地理位置:"+point.getLatitudeE6()+" 纬度信息："+lat);
			}
		});
		
	}

	private List<Map<String, Object>> mapeList = new ArrayList<Map<String, Object>>();//列表数据
	SimpleAdapter arrayAdapte;//列表适配器

	public void init() {
		mapList = (ListView) findViewById(R.id.list_Fault_Map); 
		arrayAdapte = new SimpleAdapter(this, mapeList, R.layout.map_lisnt_item, new String[] { "name" },
				new int[] { R.id.text_map_main_item });
		mapList.setAdapter(arrayAdapte);
		mapList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if(arg2<3)
				{
					GlobalData.faultReportMDL= mapFaulList.get(arg2);
					Intent detail=new Intent(FalultLocationActivity.this,FaultReportDetail.class);
					startActivity(detail);
				}
				
			}});
		inserLayout = (LinearLayout) findViewById(R.id.showDetail);
		/*
		 * btShowLocation=(Button)findViewById(R.id.btShowLocation);
		 * btShowLocation.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { Log.v("mylocation", "地理坐标" +
		 * mLocClient.getLastKnownLocation().getLatitude()); String
		 * msg="经度："+mLocClient.getLastKnownLocation().getLatitude()+" /r/n"
		 * +"纬度："+mLocClient.getLastKnownLocation().getLongitude(); new
		 * AlertDialog
		 * .Builder(FalultLocationActivity.this).setTitle("位置信息").setMessage
		 * (msg).setPositiveButton("确定", null).show();
		 * 
		 * } });
		 */
	}

	public void initOverlay() {
		/**
		 * 创建自定义overlay
		 */
		mOverlay = new MyOverlay(getResources().getDrawable(R.drawable.icon_marka), mMapView);
		/**
		 * 准备overlay 数据
		 */ 
		if (GlobalData.faulList != null) {
			HashMap<String, Integer> geoMap = GeoPointUtil.faultGeoPoint(GlobalData.faulList);
			Log.i("location", "loca:+location" + geoMap.size());
			int count=0; 
			for (Entry<String, Integer> ent : geoMap.entrySet()) {
				String geo = ent.getKey();
				count=ent.getValue();
				String[] geos = geo.split(";");
				double x = Double.valueOf(geos[0]);
				double y = Double.valueOf(geos[1]); 
				Log.i("location", "loca:坐标位置" + geos[0] + geos[1]);
				GeoPoint p = new GeoPoint((int) (y * 1E6), (int) (x * 1E6));
				OverlayItem item = new OverlayItem(p, "覆盖物1", "");
				item.setMarker(getResources().getDrawable(GeoPointUtil.LocationImage(count)));
				mOverlay.addItem(item);
			}
		}
		/**
		 * 将item 添加到overlay中 注意： 同一个itme只能add一次
		 */ 
		mItems = new ArrayList<OverlayItem>();
		mItems.addAll(mOverlay.getAllItem());

		/**
		 * 将overlay 添加至MapView中
		 */
		mMapView.getOverlays().add(mOverlay);
		/**
		 * 刷新地图
		 */
		mMapView.refresh();

		button = new Button(this);
		button.setBackgroundResource(R.drawable.popup);

	}

	/**
	 * 手动触发一次定位请求
	 */
	public void requestLocClick() {
		isRequest = true;
		mLocClient.requestLocation();
	}

	/**
	 * 修改位置图标
	 * 
	 * @param marker
	 */
	public void modifyLocationOverlayIcon(Drawable marker) {
		// 当传入marker为null时，使用默认图标绘制
		myLocationOverlay.setMarker(marker);
		// 修改图层，需要刷新MapView生效
		mMapView.refresh();
	}

	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;

			locData.latitude = location.getLatitude();
			locData.longitude = location.getLongitude();
			// 如果不显示定位精度圈，将accuracy赋值为0即可
			locData.accuracy = location.getRadius();
			// 此处可以设置 locData的方向信息, 如果定位 SDK 未返回方向信息，用户可以自己实现罗盘功能添加方向信息。
			locData.direction = location.getDerect();
			// 更新定位数据
			myLocationOverlay.setData(locData);
			// 更新图层数据执行刷新后生效
			mMapView.refresh();
			// 是手动触发请求或首次定位时，移动到定位点
			if (isRequest || isFirstLoc) {
				// 移动地图到定位点
				Log.d("LocationOverlay", "receive location, animate to it");
				mMapController.animateTo(new GeoPoint((int) (locData.latitude * 1e6), (int) (locData.longitude * 1e6)));
				isRequest = false;
				myLocationOverlay.setLocationMode(LocationMode.NORMAL);
				requestLocButton.setText("定位");
				mCurBtnType = E_BUTTON_TYPE.FOLLOW;
			}
			// 首次定位完成
			isFirstLoc = false;
		}

		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null) {
				return;
			}
		}
	}

	// 继承MyLocationOverlay重写dispatchTap实现点击处理
	public class locationOverlay extends MyLocationOverlay {

		public locationOverlay(MapView mapView) {
			super(mapView);
			// TODO Auto-generated constructor stub
		}

	}

	public class MyOverlay extends ItemizedOverlay {

		public MyOverlay(Drawable defaultMarker, MapView mapView) {
			super(defaultMarker, mapView);
		}

		@Override
		public boolean onTap(int index) {
			OverlayItem item = getItem(index); 
			x=item.getPoint().getLongitudeE6()/1E6;
			y=item.getPoint().getLatitudeE6()/1E6;
			Log.v("mylocation", "myloca x:" + x+"Y:"+y);
			Message msg=new Message();
			msg.what=2;
			handler.sendMessage(msg); 
			inserLayout.setVisibility(View.VISIBLE);
			return true;
		}

		@Override
		public boolean onTap(GeoPoint pt, MapView mMapView) {
			inserLayout.setVisibility(View.GONE);
			if (pop != null) {
				pop.hidePop();
				mMapView.removeView(button);
			}
			return false;
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
		// 退出时销毁定位
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

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Intent intentact = new Intent(FalultLocationActivity.this, FalultReportActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intentact);
				break;
			case 2:
				updateList();
				arrayAdapte.notifyDataSetChanged();
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}

	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Message msg = new Message();
			msg.what = 1;
			handler.sendMessage(msg);
		}
		return super.onKeyDown(keyCode, event);
	}

	// 显示详情数据
	private double x;
	private double y;
	private List<Mai_FaultReportMDL> mapFaulList = new ArrayList<Mai_FaultReportMDL>();

	// 更新详情数据库
	public void updateList()
		{
			if(GlobalData.faulList!=null)
			{
				List<Mai_FaultReportMDL> faulList=GlobalData.faulList;
				Mai_FaultReportMDL faultReport=null;
				mapeList.clear();
				mapFaulList.clear();
				int count=1;
				for(int i=0;i<faulList.size();i++)
				{ 
					faultReport=faulList.get(i);
					if(faultReport.getX()==x && faultReport.getY()==y)
					{
						if(count<3)
						{
							Map<String, Object> m=new HashMap<String, Object>();
							m.put("name", faultReport.getLocationName());
							mapeList.add(m);
							mapFaulList.add(faultReport);
						} 
						count++;
					}
				}
				if(count>2)
				{
					Map<String, Object> m=new HashMap<String, Object>();
					m.put("name", "查看其它："+(count-1)+"条信息"); 
					mapeList.add(m);
					Log.v("mylocation", "myloca:列表 运行");
				}
			}
		}
}

/**
 * 继承MapView重写onTouchEvent实现泡泡处理操作
 * 
 * @author hejin
 * 
 */
class MyLocationMapView extends MapView {
	static PopupOverlay pop = null;// 弹出泡泡图层，点击图标使用

	public MyLocationMapView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyLocationMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyLocationMapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!super.onTouchEvent(event)) {
			// 消隐泡泡
			if (pop != null && event.getAction() == MotionEvent.ACTION_UP)
				pop.hidePop();
		}
		return true;
	}

}
