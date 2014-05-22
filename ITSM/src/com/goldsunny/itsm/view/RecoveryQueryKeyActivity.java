package com.goldsunny.itsm.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.model.Sys_DictListMDL;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.ObjectHelper;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.util.XmlHelper;
import com.goldsunny.itsm.view.tools.AbstractSpinerAdapter;
import com.goldsunny.itsm.view.tools.SpinerPopWindow;

public class RecoveryQueryKeyActivity extends Activity {

	Button btnBackToH, btnQuery;
	EditText txtQueryKey, textRecovery_Begin_Date, textRecovery_End_Date, textRecovery_Place, txtBuStatus;
	private Calendar c = null;
	String dateString = "", statusId,locationId; 
	private final int selectLocaton=2000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.recovery_query_key);
		SysApplication.getInstance().addActivity(this);
		btnBackToH = (Button) findViewById(R.id.btnBackToH);
		btnQuery = (Button) findViewById(R.id.btnQuery);
		txtQueryKey = (EditText) findViewById(R.id.txtQueryKey);
		textRecovery_Begin_Date = (EditText) findViewById(R.id.textRecovery_Begin_Date);
		textRecovery_End_Date = (EditText) findViewById(R.id.textRecovery_End_Date);
		textRecovery_Place = (EditText) findViewById(R.id.textRecovery_Place);
		txtBuStatus = (EditText) findViewById(R.id.txtBuStatus);
		btnBackToH.setOnClickListener(onClickListener);
		btnQuery.setOnClickListener(onClickListener);
		textRecovery_Begin_Date.setOnTouchListener(onTouchListener);
		textRecovery_End_Date.setOnTouchListener(onTouchListener);
		textRecovery_Place.setOnTouchListener(onTouchListener);
		txtBuStatus.setOnTouchListener(onTouchListener);
		initPopWindow();
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnBackToH:
				finish();
				break;
			case R.id.btnQuery:
				Intent data = new Intent();
				data.putExtra("key", txtQueryKey.getText().toString());
				Date date = ObjectHelper.Convert2Date(textRecovery_Begin_Date.getText().toString(),
						"yyyy-MM-dd HH:mm:ss");
				if (date != null)
					data.putExtra("beginDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
				else {
					data.putExtra("beginDate", "");
				}
				date = ObjectHelper.Convert2Date(textRecovery_End_Date.getText().toString(), "yyyy-MM-dd HH:mm:ss");
				if (date != null)
					data.putExtra("endDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
				else {
					data.putExtra("endDate", "");
				}
				data.putExtra("place", locationId);
				data.putExtra("status", statusId);
				setResult(20, data);
				finish();
				break;
			default:
				break;
			}
		}

	};

	private OnTouchListener onTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				switch (v.getId()) {
				case R.id.textRecovery_Begin_Date:
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(textRecovery_Begin_Date.getWindowToken(), 0);
					onCreateDialog(R.id.textRecovery_Begin_Date).show();
					break;
				case R.id.textRecovery_End_Date:
					InputMethodManager immt = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					immt.hideSoftInputFromWindow(textRecovery_End_Date.getWindowToken(), 0);
					onCreateDialog(R.id.textRecovery_End_Date).show();
					break;
				case R.id.textRecovery_Place:
					Intent locaIntent = new Intent(RecoveryQueryKeyActivity.this, LocationTreeActivty.class);
					locaIntent.putExtra("pid", "");
					startActivityForResult(locaIntent, selectLocaton); 
					break;
				case R.id.txtBuStatus:
					showPopWindow();
					break;

				default:
					break;
				}
			}
			return false;
		}
	};
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(selectLocaton==requestCode&&resultCode==2000)
		{
			locationId = data.getStringExtra("oid");
			String locationName = data.getStringExtra("name");
			if(!CommonClass.isNullorEmpty(locationName))
			{
				textRecovery_Place.setText(locationName);
			} 
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		c = Calendar.getInstance();
		switch (id) {
		case R.id.textRecovery_Begin_Date:
			dialog = new DatePickerDialog(RecoveryQueryKeyActivity.this, new OnDateSetListener() {

				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					dateString = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
					textRecovery_Begin_Date.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
				}
			}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
			break;
		case R.id.textRecovery_End_Date:
			dialog = new DatePickerDialog(RecoveryQueryKeyActivity.this, new OnDateSetListener() {

				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					dateString = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
					textRecovery_End_Date.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
				}
			}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
			break;
		default:
			break;
		}
		return dialog;

	};

	// 状态下拉框
	private SpinerPopWindow statusPopWindow;
	private List<Sys_DictListMDL> dictList;
	private List<String> dictValueList;

	public void initPopWindow() {
		dictList = new ArrayList<Sys_DictListMDL>();
		dictValueList = new ArrayList<String>();
		try {
			dictList = XmlHelper.readDictXml(getApplicationContext(), GlobalData.reportStatus);
			if (dictList != null && dictList.size() > 0) {
				dictValueList = new ArrayList<String>();
				for (Sys_DictListMDL dic : dictList) {
					dictValueList.add(dic.getListName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		statusPopWindow = new SpinerPopWindow(this);
		statusPopWindow.refreshData(dictValueList, 0);
		statusPopWindow.setItemListener(new AbstractSpinerAdapter.IOnItemSelectListener() {
			@Override
			public void onItemClick(int pos) {
				statusId = dictList.get(pos).getOID();
				txtBuStatus.setText(dictList.get(pos).getListName());
			}
		});
	}
	
	public void showPopWindow()
	{
		statusPopWindow.setWidth(txtBuStatus.getWidth());
		statusPopWindow.showAsDropDown(txtBuStatus);
	}

}
