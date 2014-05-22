package com.goldsunny.itsm.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.TextView;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.businesslogic.BaseDataBo;
import com.goldsunny.itsm.businesslogic.EquipmentBO;
import com.goldsunny.itsm.businesslogic.FalultReportBo;
import com.goldsunny.itsm.model.Equ_EquipmentMDL;
import com.goldsunny.itsm.model.Mai_FaultReportMDL;
import com.goldsunny.itsm.model.MaintainTeamMDL;
import com.goldsunny.itsm.model.Sys_DictListMDL;
import com.goldsunny.itsm.model.Sys_FileMDL;
import com.goldsunny.itsm.model.Syst_LocationMDL;
import com.goldsunny.itsm.model.Syst_RoadMDL;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.ImageUtil;
import com.goldsunny.itsm.util.IntentIntegrator;
import com.goldsunny.itsm.util.IntentResult;
import com.goldsunny.itsm.util.ObjectHelper;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.util.XmlHelper;
import com.goldsunny.itsm.view.tools.AbstractSpinerAdapter;
import com.goldsunny.itsm.view.tools.FaultImageAdapter;
import com.goldsunny.itsm.view.tools.SpinerPopWindow;

public class FaultReportDetail extends Activity {
	SharedPreferences sharedPrefs;
	Button btnFaultDetailToTop, btnFault_Save, btnFault_Cancel, btnFault_Report, addImage;
	TextView tbFault_ReportTime, tbFault_Reportor;
	EditText tbFault_DeviceCode, tbFault_Device, tbFault_Lane, tbFault_Remark, tbFault_Road, tbFault_Level,
			tbFault_Source, tbFault_Station, tbFault_Type, tbFault_MTeam, tbFault_systemClass;
	Gallery iamgeGridView;
	FaultImageAdapter adepter;
	Mai_FaultReportMDL faultReportMDL;
	boolean isHasChange = false;
	Syst_RoadMDL road;
	BaseDataBo baseDataBo;
	FalultReportBo falultReportBo;
	public final static int TO_SELECT_PHOTO = 200, SHOW_IMAGE_VIEW = 300, SET_STATION_VALUE = 1000,
			SET_Team_Value = 4000, SET_ROAD_VALUE = 5000, SET_FAUL_TYPE_VALUE = 5001, SET_Fault_Source_VALUE = 5002,
			SET_Fault_SystemClass_VALUE = 5004, SET_Fault_Level_VALUE = 5003;
	private String roadID, roadName;
	private String stationID = "", stationName;
	private String locationID, locationName;
	private String teamId, teamName;
	private String faulType, faulLeve, faulSource, equmentId, equmentCode, equmentName, systemClassId, systemClassName;
	private String picPath, picPath1, picPath2;
	private List<String> stationList = new ArrayList<String>();// 收费站下拉框
	private List<Sys_FileMDL> imageList = new ArrayList<Sys_FileMDL>();//图片数据
	private List<Sys_FileMDL> oldImageList = new ArrayList<Sys_FileMDL>();//保存原始数据，确定新增删除内容
	private boolean isReport = false; 
	private int postion;//记录查看图片的位置
	Bitmap bm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.faultdetail);
		SysApplication.getInstance().addActivity(this);
		// sharedPrefs =
		// getSharedPreferences(CheckBasicConstants.SHARED_PREFERENCE_NAME, 0);
		btnFaultDetailToTop = (Button) findViewById(R.id.btnFaultDetailToTop);
		btnFault_Save = (Button) findViewById(R.id.btnFault_Save);
		btnFault_Cancel = (Button) findViewById(R.id.btnFault_Cancel);
		btnFault_Report = (Button) findViewById(R.id.btnFault_Report);
		tbFault_DeviceCode = (EditText) findViewById(R.id.tbFault_DeviceCode);
		tbFault_Device = (EditText) findViewById(R.id.tbFault_Device);
		tbFault_Lane = (EditText) findViewById(R.id.tbFault_Lane);
		tbFault_Level = (EditText) findViewById(R.id.tbFault_Level);
		tbFault_Remark = (EditText) findViewById(R.id.tbFault_Remark);
		tbFault_Reportor = (TextView) findViewById(R.id.tbFault_Reportor);
		tbFault_ReportTime = (TextView) findViewById(R.id.tbFault_ReportTime);
		tbFault_Road = (EditText) findViewById(R.id.tbFault_Road);
		tbFault_Source = (EditText) findViewById(R.id.tbFault_Source);
		tbFault_Station = (EditText) findViewById(R.id.tbFault_Station);
		tbFault_Type = (EditText) findViewById(R.id.tbFault_Type);
		tbFault_MTeam = (EditText) findViewById(R.id.tbFault_MTeam);
		tbFault_systemClass = (EditText) findViewById(R.id.tbFault_systemClass);
		addImage = (Button) findViewById(R.id.addImage);
		addImage.setOnClickListener(onClickListener);
		btnFaultDetailToTop.setOnClickListener(onClickListener);
		tbFault_Station.setOnTouchListener(onTouchListener);
		tbFault_Road.setOnTouchListener(onTouchListener);
		tbFault_MTeam.setOnTouchListener(onTouchListener);
		tbFault_Type.setOnTouchListener(onTouchListener);
		tbFault_Source.setOnTouchListener(onTouchListener);
		tbFault_Lane.setOnTouchListener(onTouchListener);
		tbFault_Level.setOnTouchListener(onTouchListener);
		tbFault_systemClass.setOnTouchListener(onTouchListener);
		tbFault_DeviceCode.setOnTouchListener(onScanDeviceListener);
		btnFault_Save.setOnClickListener(onClickListener);
		btnFault_Report.setOnClickListener(onClickListener);

		adepter = new FaultImageAdapter(imageList, this);
		iamgeGridView = (Gallery) findViewById(R.id.gridviewFaultDetail);
		iamgeGridView.setAdapter(adepter);
		iamgeGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				if (!CommonClass.isNullorEmpty(imageList.get(arg2).getFilePath())) {
					Intent ImageShow = new Intent(FaultReportDetail.this, ShowImageActivty.class);
					Bundle bundle = new Bundle();
					String fullPath = imageList.get(arg2).getFilePath();
					fullPath =GlobalData.PhotoPath+ fullPath.substring(fullPath.lastIndexOf("/") + 1);
					bundle.putString("path", fullPath);
					ImageShow.putExtras(bundle);
					postion=arg2;
					startActivityForResult(ImageShow, SHOW_IMAGE_VIEW);
				}
			}
		});
		baseDataBo = new BaseDataBo();
		falultReportBo = new FalultReportBo();
		initView();
		intStation();
		FaluDetalAsyncTask FaluDetalAsyncTask=new FaluDetalAsyncTask();
		FaluDetalAsyncTask.execute(null);
	}

	private void initView() {
		try {
			if (GlobalData.faultReportMDL != null) {
				faultReportMDL = GlobalData.faultReportMDL;
				tbFault_DeviceCode
						.setText(faultReportMDL.getDeviceCode() == null ? "" : faultReportMDL.getDeviceCode());
				tbFault_Device.setText(faultReportMDL.getDeviceName() == null ? "" : faultReportMDL.getDeviceName());
				tbFault_Lane.setText(faultReportMDL.getLocationName());
				tbFault_MTeam.setText(faultReportMDL.getMTeamName() == null ? "" : faultReportMDL.getMTeamName());
				tbFault_Remark.setText(faultReportMDL.getFaultDesc());
				tbFault_Reportor.setText(faultReportMDL.getReportor());
				tbFault_ReportTime.setText(ObjectHelper.Convert2String(faultReportMDL.getReportTime(), "yyyy-MM-dd"));
				tbFault_Road.setText(faultReportMDL.getRoadName());
				teamId = faultReportMDL.getMTeamID();
				teamName = faultReportMDL.getMTeamName();
				faulType = faultReportMDL.getFaultType();

				tbFault_Type.setText(XmlHelper.getDictByID(faulType, getApplicationContext()).getListName());
				faulLeve = faultReportMDL.getFaultLevel();
				tbFault_Level.setText(XmlHelper.getDictByID(faulLeve, getApplicationContext()).getListName());
				faulSource = faultReportMDL.getFaultSource();
				tbFault_Source.setText(XmlHelper.getDictByID(faulSource, getApplicationContext()).getListName());
				locationID = faultReportMDL.getLocationID();
				locationName = faultReportMDL.getLocationName();
				systemClassId = faultReportMDL.getSystemClass();
				tbFault_systemClass
						.setText(XmlHelper.getDictByID(systemClassId, getApplicationContext()).getListName());
				stationByEqui();

			} else {
				tbFault_DeviceCode.setText("");
				tbFault_Lane.setText("");
				Sys_DictListMDL dict = XmlHelper.getDictByID("11801", getApplicationContext());
				faulLeve = dict.getOID();
				tbFault_Level.setText(dict.getListName());
				tbFault_MTeam.setText("");
				tbFault_Remark.setText("");
				tbFault_Reportor.setText(GlobalData.employeeMDL.getEmplName());
				tbFault_ReportTime.setText(ObjectHelper.Convert2String(new Date(), "yyyy-MM-dd"));
				tbFault_Road.setText("");
				dict = XmlHelper.getDictByID("12005", getApplicationContext());
				faulSource = dict.getOID();
				tbFault_Source.setText(dict.getListName());
				tbFault_Station.setText("");
				dict = XmlHelper.getDictByID("11701", getApplicationContext());
				faulType = dict.getOID();
				tbFault_Type.setText(dict.getListName());
				dict = XmlHelper.getDictByID(GlobalData.systemClass + "01", getApplicationContext());
				systemClassId = dict.getOID();
				tbFault_systemClass.setText(dict.getListName());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private OnFocusChangeListener saveSharePrefsListener = new OnFocusChangeListener() {
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
		}

	};

	private View.OnClickListener onClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnFaultDetailToTop:
				finish();
				break;
			case R.id.btnFault_Save:
				// SaveData(false);
				isReport = false;
				threadSave(false);
				break;
			case R.id.btnFault_Cancel:
				BackToTop();
				break;
			case R.id.btnFault_Report:
				isReport = true;
				threadSave(true);
				break;
			case R.id.addImage:
				Intent intent = new Intent(FaultReportDetail.this, SelectPicActivity.class);
				startActivityForResult(intent, TO_SELECT_PHOTO);
				break; 
			default:
				break;
			}
		}

	};

	private View.OnTouchListener onTouchListener = new View.OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				int pos = 0;
				switch (v.getId()) {
				case R.id.tbFault_Type:
					// 故障类别
					DictList("117");
					witchDict = SET_FAUL_TYPE_VALUE;
					showSpinWindow(SET_FAUL_TYPE_VALUE);
					break;
				case R.id.tbFault_Level:
					// 故障等级
					DictList("118");
					witchDict = SET_Fault_Level_VALUE;
					showSpinWindow(SET_Fault_Level_VALUE);
					break;
				case R.id.tbFault_Source:
					// 故障源
					DictList("120");
					witchDict = SET_Fault_Source_VALUE;
					showSpinWindow(SET_Fault_Source_VALUE);
					break;
				case R.id.tbFault_systemClass:
					// 所属系统
					DictList(GlobalData.systemClass);
					witchDict = SET_Fault_SystemClass_VALUE;
					showSpinWindow(SET_Fault_SystemClass_VALUE);
					break;
				case R.id.tbFault_MTeam:
					if (tbFault_Station.getText().toString().trim().equals("")) {
						DialogHelper.showDialog(FaultReportDetail.this, "请先选择收费站");
					} else {
						// 选择维护队
						initTeamPop();
						showSpinWindow(SET_Team_Value);
					}
					break;
				case R.id.tbFault_Road:
					// 选择路段
					showSpinWindow(SET_ROAD_VALUE);
					break;
				case R.id.tbFault_Station:
					// dictListFault_Road是否为空
					if (!tbFault_Road.getText().toString().trim().equals("")) {
						showSpinWindow(SET_STATION_VALUE);
					} else {
						DialogHelper.showDialog(FaultReportDetail.this, "请先选择路段");
					}
					break;
				case R.id.tbFault_Lane:
					// dictListFault_Station是否为空
					Intent locaIntent = new Intent(FaultReportDetail.this, LocationTreeActivty.class);
					locaIntent.putExtra("pid", stationID);
					startActivityForResult(locaIntent, 2000);
					break;
				default:
					break;
				}
			}
			return true;
		}
	};
	private View.OnTouchListener onScanDeviceListener = new View.OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (event.getAction() == MotionEvent.ACTION_UP) {
				int curX = (int) event.getX();
				if (curX > v.getWidth() - 60) {
					if (!tbFault_DeviceCode.getText().toString().trim().equals("")) {
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(tbFault_DeviceCode.getWindowToken(), 0);
						// BindData();
					} else {
						IntentIntegrator integrator = new IntentIntegrator(FaultReportDetail.this);
						integrator.initiateScan();
					}

				}
			}
			return false;
		}
	};
	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				DialogHelper.showTostSuccese(FaultReportDetail.this, getResources().getString(R.string.save_succese),
						true);
				if (isReport)
					BackToTop();
				break;
			case 2:
				DialogHelper.showTostSuccese(FaultReportDetail.this, getResources().getString(R.string.save_faile),
						false);
				break;
			case 3:
				String errString = msg.getData().getString("msg");
				DialogHelper.showDialog(FaultReportDetail.this, errString);
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}

	};

	public void threadSave(final boolean isReport) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String bl = SaveData(isReport);
				 
				Message msg = new Message();
				if ("true".equals(bl))
					msg.what = 1;
				else if ("false".equals(bl))
					msg.what = 2;
				else {
					Bundle data = new Bundle();
					data.putString("msg", bl);
					msg.setData(data);
					msg.what = 3;
				}
				Log.i("thread", "thread执行");
				mHandler.sendMessage(msg);
			}
		}).start();
	}

	private String SaveData(boolean isReport) {
		String bl = "false";
		try {
			String errString = GetData(isReport);
			if (!errString.equals("")) {
				return errString;
			}
			Date curDate = new Date();
			String buStatus = isReport ? "12102" : "12101";
			faultReportMDL.setBuStatus(buStatus);
			if (faultReportMDL.getOID() == null) {
//				UUID id = UUID.randomUUID();
				faultReportMDL.setOID("");
				faultReportMDL.setApplyDate(curDate);
				faultReportMDL.setCode(GlobalData.employeeMDL.getEmplCode()
						+ ObjectHelper.Convert2String(new Date(), "yyyyMMddHHmmss"));
				faultReportMDL.setStatus("121");
				faultReportMDL.setOperatorID(GlobalData.employeeMDL.getID());
				faultReportMDL.setOperatorName(GlobalData.employeeMDL.getEmplName());
				faultReportMDL.setReportor(GlobalData.employeeMDL.getEmplName());
				faultReportMDL.setReportTime(curDate);
				faultReportMDL.setFaultTime(curDate);
				faultReportMDL.setReportorTeamID(GlobalData.teamId);// 需求不明确，待确认 

			}  
			String result= falultReportBo.saveFaultReport(faultReportMDL, oldImageList, imageList);
			if(!CommonClass.isNullorEmpty(result))
			{
				bl="true";
				if(faultReportMDL.getOID()== null)
					faultReportMDL.setOID(result);
				//清空图片列表状态值
				for(int i=0;i<imageList.size();i++)
				{
					imageList.get(i).setOID("");
				}
				oldImageList=imageList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bl;
	}
	 

	private String GetData(boolean isReport) {
		String errString = "";
		if (faultReportMDL == null) {
			faultReportMDL = new Mai_FaultReportMDL();
		}
		if (!tbFault_Road.getText().toString().trim().equals("")) {
			faultReportMDL.setRoadID(roadID);
			faultReportMDL.setRoadName(roadName);
		} else {
			errString += "路段不允许为空,";
		}
		if (!tbFault_Lane.getText().toString().trim().equals("")) {
			faultReportMDL.setLocationID(locationID);
			faultReportMDL.setLocationName(locationName);
		} else {
			errString += "地理位置不允许为空,";
		}
		if (!tbFault_Type.getText().toString().trim().equals("")) {
			faultReportMDL.setFaultType(faulType);
		} else {
			errString += "故障分类不允许为空,";
		}
		if (!tbFault_Level.getText().toString().trim().equals("")) {
			faultReportMDL.setFaultLevel(faulLeve);
		} else {
			errString += "故障等级不允许为空,";
		}
		if (!tbFault_Source.getText().toString().trim().equals("")) {
			faultReportMDL.setFaultSource(faulSource);
		} else {
			errString += "故障来源不允许为空,";
		}
		if (!tbFault_systemClass.getText().toString().trim().equals("")) {
			faultReportMDL.setSystemClass(systemClassId);
		} else {
			errString += "所属系统不允许为空,";
		}
		if (!tbFault_Remark.getText().toString().trim().equals("")) {
			faultReportMDL.setFaultDesc(tbFault_Remark.getText().toString().trim());
		} else {
			errString += "故障描述不允许为空,";
		}
		if (!tbFault_MTeam.getText().toString().trim().equals("")) {
			faultReportMDL.setMTeamID(teamId);
			faultReportMDL.setMTeamName(teamName);
		} else {
			errString += "维护单位不允许为空,";
		}
		if (!tbFault_DeviceCode.getText().toString().trim().equals("")) {
			faultReportMDL.setDeviceCode(equmentCode);
			faultReportMDL.setDeviceName(tbFault_DeviceCode.getText().toString().trim());
		}
		if (!tbFault_DeviceCode.getText().toString().trim().equals("")) {
			EquipmentBO equipmentBO = new EquipmentBO();
			Equ_EquipmentMDL equ = equipmentBO.getEquMentByCode(tbFault_DeviceCode.getText().toString().trim());
			if (equ != null) {
				equmentName = equ.getName();
				equmentCode = equ.getCode();
				faultReportMDL.setDeviceCode(equmentCode);
				faultReportMDL.setDeviceName(equmentName);
			} else {
				errString += "编号：" + tbFault_DeviceCode.getText().toString().trim() + "不存在;";
			}
		}
		return errString;
	}

	private void GoBack() {
		if (isHasChange) {
			Builder builder = new Builder(FaultReportDetail.this);
			builder.setMessage("确定不保存信息,返回上级界面？");
			builder.setTitle("提示");
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					Intent intentC = new Intent(FaultReportDetail.this, FalultReportActivity.class)
							.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intentC);
					finish();
					dialog.dismiss();

				}
			});
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			builder.create().show();
		} else {
			Intent intentC = new Intent(FaultReportDetail.this, FalultReportActivity.class)
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentC);
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// BackToTop();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK && requestCode == TO_SELECT_PHOTO) {
			picPath = data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH);
			Log.i("IMAGE_FAL", "最终选择的图片=" + picPath);
			UUID id = UUID.randomUUID();
			final String newPath = GlobalData.PhotoPath + id.toString() + picPath.substring(picPath.lastIndexOf("."));
			ImageUtil.compressCopy(picPath, newPath, 50);
			Sys_FileMDL file = new Sys_FileMDL(); 
			file.setOID(id.toString());
			file.setFileName(id.toString() + picPath.substring(picPath.lastIndexOf(".")));
			file.setFile_Type(picPath.substring(picPath.lastIndexOf(".")+1));
			file.setFilePath(newPath);
			file.setOperate("add");
			imageList.add(file);
			adepter.notifyDataSetChanged(); 
			// bm = ImageUtil.loadBitmap(picPath, true);
			// bm = ThumbnailUtils.extractThumbnail(bm, 40, 30);生成缩略图片
		}
		if (resultCode == Activity.RESULT_OK && requestCode == SHOW_IMAGE_VIEW) {
			//删除图片操作
			imageList.remove(postion);
			adepter.notifyDataSetChanged();
		}
		if (resultCode == 2000 && requestCode == 2000) {
			// 地址位置选择
			locationID = data.getStringExtra("oid");
			locationName = data.getStringExtra("name");

			Message msg = new Message();
			msg.what = 2;
			handler.sendMessage(msg);

		}
		// TODO Auto-generated method stub
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if (result != null) {
			final String contents = result.getContents();
			if (contents != null) {
				equmentCode = contents;
				Log.i("scan", "条形码：" + contents);
				new Thread(new Runnable() {

					@Override
					public void run() {
						EquipmentBO equipmentBO = new EquipmentBO();
						Equ_EquipmentMDL equ = equipmentBO.getEquMentByCode(contents);
						Bundle bundle = new Bundle();
						if (equ != null) {
							equmentName = equ.getName();
							equmentCode = equ.getCode();
							bundle.putString("isOK", "true");
						} else {
							bundle.putString("isOK", "false");
						}
						Message msg = new Message();
						msg.setData(bundle);
						msg.what = 1;
						handler.sendMessage(msg);

					}
				}).start();

			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String results = msg.getData().getString("isOK");
				if ("false".equals(results))
					DialogHelper.showDialog(FaultReportDetail.this, "编号：" + equmentCode + "设备信息不存在");
				else {
					tbFault_DeviceCode.setText(equmentCode);
					tbFault_Device.setText(equmentName);
				}
				break;
			case 2:
				tbFault_Lane.setText(locationName);
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}

	};

	public void stationByEqui() {
		if (GlobalData.tollStation != null && locationName != null) {
			for (Syst_LocationMDL loca : GlobalData.tollStation) {
				String name = loca.getName();
				if (locationName.contains(name)) {
					stationID = loca.getOID();
					stationName = loca.getName();
					tbFault_Station.setText(stationName);
					return;
				}
			}
		}
	}

	private void BackToTop() {
		GoBack();
	}

	private String GetKey(HashMap<String, String> map, String value) {
		for (String key : map.keySet()) {
			if (map.get(key).equals(value)) {
				return key;
			}
		}
		return "";
	}

	// 初始下拉框数据
	public void intStation() {
		try {
			final List<Syst_LocationMDL> station = GlobalData.tollStation;
			for (int i = 0; i < station.size(); i++) {
				Syst_LocationMDL location = station.get(i);
				stationList.add(location.getName());
			}
			mSpinerPopWindow = new SpinerPopWindow(this);
			mSpinerPopWindow.refreshData(stationList, 0);
			mSpinerPopWindow.setItemListener(new AbstractSpinerAdapter.IOnItemSelectListener() {
				@Override
				public void onItemClick(int pos) {
					Syst_LocationMDL location = station.get(pos);
					String valueString = location.getName();
					stationID = location.getOID();
					stationName = location.getName();
					setDate(pos, SET_Team_Value, valueString);
				}
			});
			List<String> valList = new ArrayList<String>();
			if (GlobalData.roadMDLs != null) {
				valList.add(GlobalData.roadMDLs.get(0).getName());
				roadID = GlobalData.roadMDLs.get(0).getOID();
				roadName = GlobalData.roadMDLs.get(0).getName();
				tbFault_Road.setText(roadName);
			}
			roadPopWindow = new SpinerPopWindow(this);
			roadPopWindow.refreshData(valList, 0);
			roadPopWindow.setItemListener(new AbstractSpinerAdapter.IOnItemSelectListener() {
				@Override
				public void onItemClick(int pos) {
					tbFault_Road.setText(roadName);
				}
			});
			dictPopWindow = new SpinerPopWindow(this);
			dictPopWindow.setItemListener(new AbstractSpinerAdapter.IOnItemSelectListener() {
				@Override
				public void onItemClick(int pos) {

					setDictValue(pos);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<MaintainTeamMDL> teamList;

	// 维护队下拉框
	public void initTeamPop() {
		try {
			List<String> valList = new ArrayList<String>();
			teamList = XmlHelper.getMainTeam(stationName, getApplicationContext());
			if (teamList != null && teamList.size() > 0) {
				for (MaintainTeamMDL mainTeam : teamList) {
					valList.add(mainTeam.getName());
				}
			}
			teamPopWindow = new SpinerPopWindow(this);
			teamPopWindow.refreshData(valList, 0);
			teamPopWindow.setItemListener(new AbstractSpinerAdapter.IOnItemSelectListener() {
				@Override
				public void onItemClick(int pos) {
					teamId = teamList.get(pos).getOID();
					teamName = teamList.get(pos).getName();
					tbFault_MTeam.setText(teamName);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 下拉框选数据赋值
	public void setDate(int select, int wtich, String value) {
		switch (wtich) {
		case SET_Team_Value:
			tbFault_Station.setText(value);
			break;

		default:
			break;
		}
	}

	// 下拉框
	private SpinerPopWindow mSpinerPopWindow;
	private SpinerPopWindow teamPopWindow;
	private SpinerPopWindow roadPopWindow;
	private SpinerPopWindow dictPopWindow;

	private void showSpinWindow(int menth) {
		switch (menth) {
		case SET_STATION_VALUE:
			mSpinerPopWindow.setWidth(tbFault_Station.getWidth());
			mSpinerPopWindow.showAsDropDown(tbFault_Station);
			break;
		case SET_ROAD_VALUE:
			roadPopWindow.setWidth(tbFault_Road.getWidth());
			roadPopWindow.showAsDropDown(tbFault_Road);
			break;
		case SET_Team_Value:
			teamPopWindow.setWidth(tbFault_MTeam.getWidth());
			teamPopWindow.showAsDropDown(tbFault_MTeam);
			break;
		case SET_FAUL_TYPE_VALUE:
			dictPopWindow.setWidth(tbFault_Type.getWidth());
			dictPopWindow.showAsDropDown(tbFault_Type);
			break;
		case SET_Fault_Level_VALUE:
			dictPopWindow.setWidth(tbFault_Level.getWidth());
			dictPopWindow.showAsDropDown(tbFault_Level);
			break;
		case SET_Fault_Source_VALUE:
			dictPopWindow.setWidth(tbFault_Source.getWidth());
			dictPopWindow.showAsDropDown(tbFault_Source);
			break;
		case SET_Fault_SystemClass_VALUE:
			dictPopWindow.setWidth(tbFault_systemClass.getWidth());
			dictPopWindow.showAsDropDown(tbFault_systemClass);
			break;
		default:
			break;
		}

	}

	private List<Sys_DictListMDL> dictList;
	private List<String> dictValueList;

	public void DictList(String dictPid) {
		try {
			dictList = XmlHelper.readDictXml(getApplicationContext(), dictPid);
			if (dictList != null && dictList.size() > 0) {
				dictValueList = new ArrayList<String>();
				for (Sys_DictListMDL dic : dictList) {
					dictValueList.add(dic.getListName());
				}
			}
			dictPopWindow.refreshData(dictValueList, 0);
			dictPopWindow.notifyDataSetChanged();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int witchDict;// 记录选择

	public void setDictValue(int pos) {
		switch (witchDict) {
		case SET_FAUL_TYPE_VALUE:
			faulType = dictList.get(pos).getOID();
			tbFault_Type.setText(dictList.get(pos).getListName());
			break;
		case SET_Fault_Level_VALUE:
			faulLeve = dictList.get(pos).getOID();
			tbFault_Level.setText(dictList.get(pos).getListName());
			break;
		case SET_Fault_Source_VALUE:
			faulSource = dictList.get(pos).getOID();
			tbFault_Source.setText(dictList.get(pos).getListName());
			break;
		case SET_Fault_SystemClass_VALUE:
			systemClassId = dictList.get(pos).getOID();
			tbFault_systemClass.setText(dictList.get(pos).getListName());
			break;
		default:
			break;
		}
	}

	/**    
	 * @author 异步加载图片列表     
	 * @version 1.0     
	 * @created 2014-5-14 下午2:29:38
	 */
	private class FaluDetalAsyncTask extends AsyncTask<String, String, Boolean> {
		@Override
		protected Boolean doInBackground(String... params) {
			try {
				if (faultReportMDL != null) {
					List<Sys_FileMDL> fileList = baseDataBo.getFileByBizOid(faultReportMDL.getOID());
					if(fileList!=null && fileList.size()>0)
					{
						oldImageList=fileList;
						imageList.addAll(fileList);
					} 
				}

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			adepter.notifyDataSetChanged();
			super.onPostExecute(result);
		}

	}
}
