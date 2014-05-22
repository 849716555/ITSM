package com.goldsunny.itsm.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.businesslogic.BaseDataBo;
import com.goldsunny.itsm.businesslogic.FalultReportBo;
import com.goldsunny.itsm.businesslogic.RecoveryMainBo;
import com.goldsunny.itsm.model.Equ_EquipmentMDL;
import com.goldsunny.itsm.model.Mai_FaultReportMDL;
import com.goldsunny.itsm.model.Mai_RecoveryDetailMDL;
import com.goldsunny.itsm.model.Mai_RecoveryMainMDL;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.ObjectHelper;
import com.goldsunny.itsm.util.SysApplication;

public class RecoveryDetailActivity extends Activity {
	Button btnRecoveryDetailToTop, btnRecovery_Save, btnRecovery_Cancel, btnRecovery_Finish, btnFaultView;
	EditText tbRecovery_Reason, tbRecovery_Solution, tbRecovery_Operator, tbRecovery_PlanTime, tbRecovery_PlanDate,
			tbRecovery_FaultDetail;
	ImageView addRecoverDeatil;
	Mai_FaultReportMDL faultReportMDL;
	Mai_RecoveryMainMDL recoveryMainMDL;
	Equ_EquipmentMDL replaceEqu, faultEqu;
	private final static int DATE_DIALOG = 0;
	private final static int TIME_DIALOG = 1;
	private Calendar c = null;
	String dateString = "";
	String timeString = "";
	Mai_RecoveryDetailMDL recoveryDetailMDL;
	private RecoveryMainBo recoveryMainBo;
	private FalultReportBo falultReportBo;
	private BaseDataBo baseDataBo;

	private ListView detailListView;
	private List<Map<String, Object>> dateList = new ArrayList<Map<String, Object>>();// 列表数据
	private List<Mai_RecoveryDetailMDL> detalList = new ArrayList<Mai_RecoveryDetailMDL>();// 维修明细数据
	SimpleAdapter arrayAdapte;// 列表适配器
	private final static String RecoveryMode = "12401";// 整机替换
	private int scanSeq = 1;
	boolean isFinsh = false;
	private Date date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.recoverydetail);
		SysApplication.getInstance().addActivity(this);
		btnRecoveryDetailToTop = (Button) findViewById(R.id.btnRecoveryDetailToTop);
		btnRecovery_Save = (Button) findViewById(R.id.btnRecovery_Save);
		btnFaultView = (Button) findViewById(R.id.btnFaultView);
		btnRecovery_Cancel = (Button) findViewById(R.id.btnRecovery_Cancel);
		btnRecovery_Finish = (Button) findViewById(R.id.btnRecovery_Finish);
		tbRecovery_Reason = (EditText) findViewById(R.id.tbRecovery_Reason);
		tbRecovery_Solution = (EditText) findViewById(R.id.tbRecovery_Solution);
		tbRecovery_Operator = (EditText) findViewById(R.id.tbRecovery_Operator);
		tbRecovery_PlanTime = (EditText) findViewById(R.id.tbRecovery_PlanTime);
		tbRecovery_PlanDate = (EditText) findViewById(R.id.tbRecovery_PlanDate);
		tbRecovery_FaultDetail = (EditText) findViewById(R.id.tbRecovery_FaultDetail);
		addRecoverDeatil = (ImageView) findViewById(R.id.addRecoverDetailM);
		btnRecoveryDetailToTop.setOnClickListener(onClickListener);
		btnRecovery_Save.setOnClickListener(onClickListener);
		addRecoverDeatil.setOnClickListener(onClickListener);
		btnFaultView.setOnClickListener(onClickListener);
		btnRecovery_Cancel.setOnClickListener(onClickListener);
		btnRecovery_Finish.setOnClickListener(onClickListener);
		//tbRecovery_PlanTime.setOnTouchListener(onTouchListener);
		//tbRecovery_PlanDate.setOnTouchListener(onTouchListener);
		falultReportBo = new FalultReportBo();
		recoveryMainBo = new RecoveryMainBo();
		baseDataBo = new BaseDataBo();
		threadInit();
		detailListView = (ListView) findViewById(R.id.list_recover_deatil);
		arrayAdapte = new SimpleAdapter(this, dateList, R.layout.list_item_recover_detail, new String[] { "equ","replaceEqu" },
				new int[] { R.id.item_text_equ,R.id.item_text_replace });
		detailListView.setAdapter(arrayAdapte);
		detailListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				GlobalData.recoverDeatil = detalList.get(arg2);
				Intent detail = new Intent(RecoveryDetailActivity.this, RecoveryDetailShowActivity.class);
				startActivityForResult(detail, BIND_AUTO_CREATE);
			}
		});
		setListViewHeightBasedOnChildren(detailListView);
		RecoveAsyncTask task = new RecoveAsyncTask();
		task.execute(null);
	}

	public void threadInit() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (GlobalData.faultReportMDL != null) {
					faultReportMDL = GlobalData.faultReportMDL;
					try {
						recoveryMainMDL = recoveryMainBo.GetRecoveryMainInfoByReportID(faultReportMDL.getOID());
					} catch (Exception e) {
						e.printStackTrace();
					}
					initDetailList();
					Message msg = new Message();
					msg.what = 4;
					mHandler.sendMessage(msg);
				} else {
					date = baseDataBo.getSystemDate();
					Message msg = new Message();
					msg.what = 6;
					mHandler.sendMessage(msg);
				}
			}
		}).start();
	}

	private void InitView() {
		replaceEqu = null;
		if (GlobalData.faultReportMDL != null) {

			tbRecovery_FaultDetail.setText(faultReportMDL.getFaultDesc());
			if (recoveryMainMDL != null) {
				GlobalData.recoveryMainMDL = recoveryMainMDL;
				tbRecovery_Operator.setText(recoveryMainMDL.getOperatorName());
				tbRecovery_Reason.setText(recoveryMainMDL.getFaultReason());
				tbRecovery_Solution.setText(recoveryMainMDL.getSolution());
				tbRecovery_PlanDate.setText(ObjectHelper.Convert2String(recoveryMainMDL.getActualRecoveryTime(),
						"yyyy年MM月dd日"));
				tbRecovery_PlanTime.setText(ObjectHelper.Convert2String(recoveryMainMDL.getActualRecoveryTime(),
						"HH时mm分"));
				if (recoveryMainMDL.getBuStatus().equals("12105")) {
					btnRecovery_Finish.setVisibility(View.GONE);
					btnRecovery_Save.setVisibility(View.GONE);
				}
			} else {
				tbRecovery_PlanDate.setText(ObjectHelper.Convert2String(new Date(), "yyyy年MM月dd日"));
				tbRecovery_PlanTime.setText(ObjectHelper.Convert2String(new Date(), "HH时mm分"));
				tbRecovery_Operator.setText(GlobalData.employeeMDL.getEmplName());
				tbRecovery_Reason.setText("");
				tbRecovery_Solution.setText("");
			}
		}
	}

	public void initDetailList() {
		try {
			if (recoveryMainMDL != null) {
				detalList = recoveryMainBo.getRecoverDeatalList(recoveryMainMDL.getOID());
				if (detalList != null && detalList.size() > 0) {
					for (Mai_RecoveryDetailMDL recoveryDetailMDL : detalList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("equ", recoveryDetailMDL.getName() + "( " + recoveryDetailMDL.getCode()+")");
						map.put("replaceEqu", recoveryDetailMDL.getReplaceName() + "( " + recoveryDetailMDL.getReplaceCode()+")");
						dateList.add(map);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private View.OnTouchListener onTouchListener = new View.OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (event.getAction() == MotionEvent.ACTION_UP) {
				switch (v.getId()) {
				case R.id.tbRecovery_PlanDate:
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(tbRecovery_PlanDate.getWindowToken(), 0);
					onCreateDialog(DATE_DIALOG).show();
					break;
				case R.id.tbRecovery_PlanTime:
					InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm1.hideSoftInputFromWindow(tbRecovery_PlanTime.getWindowToken(), 0);
					onCreateDialog(TIME_DIALOG).show();
					break;
				}
			}
			return false;
		}
	};

	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		c = Calendar.getInstance();
		switch (id) {
		case DATE_DIALOG:
			dialog = new DatePickerDialog(RecoveryDetailActivity.this, new OnDateSetListener() {

				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					dateString = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
					tbRecovery_PlanDate.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
				}
			}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
			break;
		case TIME_DIALOG:
			dialog = new TimePickerDialog(RecoveryDetailActivity.this, new OnTimeSetListener() {

				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					timeString = hourOfDay + ":" + minute + ":00";
					tbRecovery_PlanTime.setText(hourOfDay + "时" + minute + "分");
				}
			}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);
			break;
		default:
			break;
		}
		return dialog;

	};

	private View.OnClickListener onClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnRecoveryDetailToTop:
				finish();
				break;
			case R.id.btnRecovery_Save:
				isFinsh = false;
				threadSave(false);
				break;
			case R.id.btnRecovery_Cancel:
				finish();
				break;
			case R.id.btnRecovery_Finish:
				isFinsh = true;
				threadSave(true);
				break;
			case R.id.addRecoverDetailM:
				if (recoveryMainMDL != null && recoveryMainMDL.getOID() != null) {
					Intent detail = new Intent(RecoveryDetailActivity.this, RecoveryDetailShowActivity.class);
					detail.putExtra("recoverMainId", recoveryMainMDL.getOID());
					startActivityForResult(detail, BIND_AUTO_CREATE);
				} else {
					DialogHelper.showDialog(RecoveryDetailActivity.this, "请先保存！");
				}
				break;
			case R.id.btnFaultView:
				Intent viewFault = new Intent(RecoveryDetailActivity.this, FaultReportViewActivity.class);
				startActivity(viewFault);
			default:
				break;
			}
		}

	};

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				DialogHelper.showTostSuccese(RecoveryDetailActivity.this,
						getResources().getString(R.string.save_succese), true);
				if (isFinsh) {
					Intent lisIntent = new Intent(RecoveryDetailActivity.this, RecoveryMainActivity.class)
							.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(lisIntent);
					finish();
				}
				break;
			case 2:
				DialogHelper.showTostSuccese(RecoveryDetailActivity.this,
						getResources().getString(R.string.save_faile), false);
				break;
			case 3:
				String errString = msg.getData().getString("msg");
				DialogHelper.showDialog(RecoveryDetailActivity.this, errString);
				break;
			case 4:
				// 初始化数据
				InitView();
				break;
			case 5:
				// 更新明细列表
				arrayAdapte.notifyDataSetChanged();
				break;
			case 6:
				//更新系统时间
				if(date!=null)
				{
					tbRecovery_PlanDate.setText(ObjectHelper.Convert2String(date, "yyyy年MM月dd日"));
					tbRecovery_PlanTime.setText(ObjectHelper.Convert2String(date, "HH时mm分"));
				} 
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
				mHandler.sendMessage(msg);
			}
		}).start();
	}

	private String SaveData(boolean isFinish) {
		try {
			String errString = GetData(isFinish);
			Date curDate = new Date();
			String buStatus = isFinish ? "12105" : "12103";// 12105:已排障,12103:排障中
			if (!errString.equals("")) {
				return errString;
			}
			recoveryMainMDL.setSolution(tbRecovery_Solution.getText().toString().trim());
			recoveryMainMDL.setFaultReason(tbRecovery_Reason.getText().toString().trim());
			recoveryMainMDL.setOperatorID(GlobalData.employeeMDL.getID());
			recoveryMainMDL.setOperatorName(GlobalData.employeeMDL.getEmplName());
			recoveryMainMDL.setBuStatus(buStatus);
			boolean bl = false;
			if (recoveryMainMDL.getOID() == null) {
				UUID id = UUID.randomUUID();
				recoveryMainMDL.setOID(id.toString());
				recoveryMainMDL.setFaultReportID(faultReportMDL.getOID());
				recoveryMainMDL.setRoadID(faultReportMDL.getRoadID());
				recoveryMainMDL.setRoadName(faultReportMDL.getRoadName());
				recoveryMainMDL.setCode("GZWH" + GlobalData.employeeMDL.getEmplCode()
						+ ObjectHelper.Convert2String(curDate, "yyyyMMddHHmmss"));
				recoveryMainMDL.setApplyDate(curDate);
				recoveryMainMDL.setRecTime(curDate);
				recoveryMainMDL.setMTeamID(faultReportMDL.getMTeamID());
				recoveryMainMDL.setMTeamName(faultReportMDL.getMTeamName());
				recoveryMainMDL.setMTeamMan(GlobalData.employeeMDL.getEmplName());
				bl = recoveryMainBo.addRecoveryMain(recoveryMainMDL);
				if (bl) {
					faultReportMDL.setBuStatus(buStatus);
					faultReportMDL.setRecoveryMainID(recoveryMainMDL.getOID());
					bl = falultReportBo.updateFalutReprot(faultReportMDL);
				}
			} else {
				bl = recoveryMainBo.updateRecoveryMain(recoveryMainMDL);
				if (bl && isFinish) {
					faultReportMDL.setBuStatus(buStatus);
					bl = falultReportBo.updateFalutReprot(faultReportMDL);
				}
			}
			if (bl) {
				return "true";
			} else {
				recoveryMainMDL = null;
				return "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "false";
	}

	private String GetData(boolean isReport) {
		String errString = "";
		if (recoveryMainMDL == null) {
			recoveryMainMDL = new Mai_RecoveryMainMDL();
		}
		if(!isReport)
		{
			//是否是完成排障，如果是完成排障,取系统时间赋值
			if (!tbRecovery_PlanDate.getText().toString().trim().equals("")
					&& !tbRecovery_PlanTime.getText().toString().trim().equals("")) {
				Date date = ObjectHelper.Convert2Date(dateString + " " + timeString, "yyyy-MM-dd HH:mm:ss");
				recoveryMainMDL.setPlanRecoveryTime(date);
				recoveryMainMDL.setActualRecoveryTime(date);
			} else {
				if (tbRecovery_PlanDate.getText().toString().trim().equals("")) {
					errString += "日期不允许为空,";
				}
				if (tbRecovery_PlanTime.getText().toString().trim().equals("")) {
					errString += "时间不允许为空,";
				}
			}
		}else
		{
			date=baseDataBo.getSystemDate();
			if(date!=null)
			{
				recoveryMainMDL.setPlanRecoveryTime(date);
				recoveryMainMDL.setActualRecoveryTime(date);
			}else {
				errString="取系统时间错误；";
			}
		} 
		return errString;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					initDetailList();
					Message msg = new Message();
					msg.what = 5;
					mHandler.sendMessage(msg);
				}
			}).start();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	private void GoBack() {
		GlobalData.faultReportMDL = null;
		GlobalData.recoveryMainMDL = null;
		Intent intentC = new Intent(RecoveryDetailActivity.this, RecoveryMainActivity.class)
				.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intentC);
		finish();
	}

	private class RecoveAsyncTask extends AsyncTask<String, String, Boolean> {
		@Override
		protected Boolean doInBackground(String... params) {
			try {
				InitView();
				initDetailList();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			arrayAdapte.notifyDataSetChanged();
			super.onPostExecute(result);
		}

	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0); // 计算子项View 的宽高
			totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
	}
}
