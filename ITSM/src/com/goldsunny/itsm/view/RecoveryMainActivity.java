package com.goldsunny.itsm.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.businesslogic.FalultReportBo;
import com.goldsunny.itsm.businesslogic.RecoveryMainBo;
import com.goldsunny.itsm.model.Mai_FaultReportMDL;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.view.XListView.IXListViewListener;

public class RecoveryMainActivity extends Activity implements OnClickListener,IXListViewListener {
	Button btnRecoveryMainToTop; 
	ProgressBar recover_main_progresbar;
	private int pageIndex = 1,toalPage=0;
	SimpleAdapter adapter;
	ArrayList<HashMap<String, String>> logList;
	HashMap<String, Mai_FaultReportMDL> InformationsList = new HashMap<String, Mai_FaultReportMDL>();
	XListView listRecoveryMain;
	FalultMainItem recoveryMainItem;
	RecoveryMainBo recoveryMainBo;
	FalultReportBo falultReportBo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.recoverymain);
		SysApplication.getInstance().addActivity(this);

		logList = new ArrayList<HashMap<String, String>>();
		btnRecoveryMainToTop = (Button) findViewById(R.id.btnRecoveryMainToTop);
		recover_main_progresbar = (ProgressBar) findViewById(R.id.recover_main_progresbar);
		listRecoveryMain = (XListView) findViewById(R.id.listRecoveryMain);
		recoveryMainItem=new FalultMainItem(this,logList); 
		listRecoveryMain.setPullLoadEnable(true);
		listRecoveryMain.setAdapter(recoveryMainItem);
		listRecoveryMain.setXListViewListener(this);
		
		btnRecoveryMainToTop.setOnClickListener(this);
		listRecoveryMain.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Mai_FaultReportMDL faultReportMDL=(Mai_FaultReportMDL)InformationsList.get(arg2+"");
				GlobalData.faultReportMDL = faultReportMDL;
				Intent i = new Intent(RecoveryMainActivity.this,
						RecoveryDetailActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		recoveryMainBo=new RecoveryMainBo();
		falultReportBo=new FalultReportBo();
		initData();
	}

	public void initData(){
		RecoveAsyncTask recoveAsyncTask =new RecoveAsyncTask();
		recoveAsyncTask.execute(null);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnRecoveryMainToTop:
			BackToTop();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			//finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	private void BackToTop() {
		Intent i = new Intent(RecoveryMainActivity.this, HomeActivity.class)
				.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
		finish();
	}
 

	@Override
	public void onRefresh() {
		logList.clear();
		RecoveAsyncTask rec=new RecoveAsyncTask();
		rec.execute(null);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		pageIndex++;
		if(pageIndex<=toalPage)
		{
			RecoveAsyncTask rec=new RecoveAsyncTask();
			rec.execute(null);
		}else {
			listRecoveryMain.stopLoadMore();
			DialogHelper.showTost(this, "没有更多数据！");
		}  
	}
	
	private void onLoad() {
		listRecoveryMain.stopRefresh();
		listRecoveryMain.stopLoadMore();
		Date now =new Date();
		SimpleDateFormat simple=new SimpleDateFormat("HH:mm:ss");
		listRecoveryMain.setRefreshTime(simple.format(now));
	} 
	
	private class RecoveAsyncTask extends AsyncTask<String, String , Boolean>
	{ 
		@Override
		protected Boolean doInBackground(String... params) {
			String userId=GlobalData.employeeMDL.getID(); 
			try { 
				List<Mai_FaultReportMDL> list=recoveryMainBo.getRecoverDoList("12102", 1);
				for(int i=0;i<list.size();i++)
				{
					Mai_FaultReportMDL faultReportMDL=new Mai_FaultReportMDL();
					faultReportMDL=list.get(i);
					HashMap<String, String> map=new HashMap<String, String>();
					map.put("title", faultReportMDL.getLocationName());
					map.put("describe", faultReportMDL.getFaultDesc());
					logList.add(map);
					String count=String.valueOf((logList.size()));
					InformationsList.put(count, faultReportMDL);
					toalPage=faultReportMDL.getTotalPage();//赋值总页数
				}
				GlobalData.faulList=list;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
			return true;
		} 

		@Override
		protected void onPostExecute(Boolean result) { 
			onLoad();
			recover_main_progresbar.setVisibility(View.GONE);
			recoveryMainItem.notifyDataSetChanged();
			super.onPostExecute(result);
		}
		
	}
	
}
