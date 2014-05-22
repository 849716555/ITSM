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
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.businesslogic.FalultReportBo;
import com.goldsunny.itsm.model.Mai_FaultReportMDL;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.view.XListView.IXListViewListener;
import com.goldsunny.itsm.webservicebll.SystemService;
import com.goldsunny.itsm.webservicebll.SystemServiceDate;
 
/**     
* @ClassName：FalultReportActivity   
* @Description：故障报修列表
* @author：yangwy   
* @date 2014-4-10 
*/ 
public class FalultReportActivity extends Activity implements OnClickListener , IXListViewListener,OnItemClickListener {
	Button btnRecoveryMainToTop,btnFalutLocation,btnAddFault;
	ProgressBar falut_list_progresbar;
	XListView listRecoveryMain;
	FalultMainItem recoveryMainItem;
	SystemService systemService;
	SystemServiceDate systemServiceDate;
	FalultReportBo falultReportBo;
	public final static int requestAddFal=1000;
	private Handler mHandler;
	int pageIndex = 1,toalPage=0;
	int pageSize = 1000; 
	List<HashMap<String, String>> logList=new ArrayList<HashMap<String,String>>();
	HashMap<String, Mai_FaultReportMDL> InformationsList = new HashMap<String, Mai_FaultReportMDL>();
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.falultreportlist);
		SysApplication.getInstance().addActivity(this); 
		init(); 
		recoveryMainItem=new FalultMainItem(this,logList); 
		listRecoveryMain.setPullLoadEnable(true);
		listRecoveryMain.setAdapter(recoveryMainItem);
		listRecoveryMain.setXListViewListener(this);
		listRecoveryMain.setPullRefreshEnable(true);
		mHandler=new Handler();
		setListener();
	} 

	 
	public void init()
	{
		systemService=new SystemService();
		systemServiceDate=new SystemServiceDate();
		falultReportBo=new FalultReportBo();
		btnRecoveryMainToTop = (Button) findViewById(R.id.btnRecoveryMainToTop);
		btnFalutLocation=(Button)findViewById(R.id.btnFalutLocation);
		listRecoveryMain = (XListView) findViewById(R.id.litviewRecove); 
		falut_list_progresbar=(ProgressBar)findViewById(R.id.falut_list_progresbar);
		btnAddFault=(Button)findViewById(R.id.btnAddFault);
		btnRecoveryMainToTop.setOnClickListener(this);
		btnFalutLocation.setOnClickListener(this);
		listRecoveryMain.setOnItemClickListener(this);
		btnAddFault.setOnClickListener(this);
	}
	
	public void setListener()
	{ 
		FaluAsyncTask faluAsyncTask=new FaluAsyncTask();
		faluAsyncTask.execute(null);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnFalutLocation:
			Intent falLocation=new Intent(this,FalultLocationActivity.class);
			GlobalData.faultReportMDL=null;
			startActivity(falLocation);
			finish();
			break;
		case R.id.btnAddFault:
			GlobalData.faultReportMDL=null;
			Intent addFault=new Intent(this,FaultReportDetail.class)
			.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(addFault);
			//startActivityForResult(addFault, requestAddFal);
			//finish();
			break;
		case R.id.btnRecoveryMainToTop:
			BackToTop();
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Mai_FaultReportMDL faultReportMDL=(Mai_FaultReportMDL)InformationsList.get(arg2+"");
		GlobalData.faultReportMDL=faultReportMDL;
		Intent detail=new Intent(this,FaultReportDetail.class);
		startActivity(detail);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			BackToTop();
		}
		return super.onKeyDown(keyCode, event);
	}

	private void BackToTop() {
		Intent i = new Intent(FalultReportActivity.this, HomeActivity.class)
				.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
		finish();
	}
	
	 
	
	@Override
	public void onRefresh() { 
		logList.clear();
		pageIndex=1;
		FaluAsyncTask faul=new FaluAsyncTask();
		faul.execute(null); 
	}

	@Override
	public void onLoadMore() {  
		pageIndex++;
		if(pageIndex<=toalPage)
		{
			FaluAsyncTask faul=new FaluAsyncTask();
			faul.execute(null);
		}else {
			listRecoveryMain.stopLoadMore();
			listRecoveryMain.setFooterView(View.GONE);
			DialogHelper.showTost(this, "已经是最后一页！");
		} 
		
	}
	
	private void onLoad() {
		listRecoveryMain.stopRefresh();
		listRecoveryMain.stopLoadMore();
		Date now =new Date();
		SimpleDateFormat simple=new SimpleDateFormat("HH:mm:ss");
		listRecoveryMain.setRefreshTime(simple.format(now));
	} 
	

	
	private class FaluAsyncTask extends AsyncTask<String, String , Boolean>
	{ 
		@Override
		protected Boolean doInBackground(String... params) {
			String userId=GlobalData.employeeMDL.getID(); 
			try { 
				List<Mai_FaultReportMDL> list=falultReportBo.getFalReportList(userId, "12101", pageIndex);
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
			falut_list_progresbar.setVisibility(View.GONE);
			onLoad(); 
			if(toalPage<2){
				listRecoveryMain.setFooterView(View.GONE);
			}
			recoveryMainItem.notifyDataSetChanged();
			super.onPostExecute(result);
		}
		
	}
	

}
