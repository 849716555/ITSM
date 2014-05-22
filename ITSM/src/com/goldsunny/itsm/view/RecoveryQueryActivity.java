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
import android.os.Message;
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
import com.goldsunny.itsm.businesslogic.RecoveryMainBo;
import com.goldsunny.itsm.model.Mai_FaultReportMDL;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.view.XListView.IXListViewListener;

/** 
 * 故障查询列表    
 * @author yangwy       
 * @version 1.0     
 * @created 2014-5-15 下午4:38:24
 */
public class RecoveryQueryActivity extends Activity implements OnClickListener,IXListViewListener {
	Button btnRecoveryQueryToTop,btnQueryRecovery; 
	ProgressBar recover_query_progresbar;
	private int pageIndex = 1,toalPage=0; 
	ArrayList<HashMap<String, String>> logList;
	HashMap<String, Mai_FaultReportMDL> InformationsList = new HashMap<String, Mai_FaultReportMDL>();
	XListView listRecoveryQuery; 
	FalultMainItem recoveryMainItem;
	RecoveryMainBo recoveryMainBo;
	FalultReportBo falultReportBo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.recoveryquery);
		SysApplication.getInstance().addActivity(this);

		logList = new ArrayList<HashMap<String, String>>();
		btnRecoveryQueryToTop = (Button) findViewById(R.id.btnRecoveryQueryToTop);
		btnQueryRecovery = (Button) findViewById(R.id.btnQueryRecovery);
		recover_query_progresbar=(ProgressBar)findViewById(R.id.recover_query_progresbar);
		listRecoveryQuery = (XListView) findViewById(R.id.listRecoveryQuery);
		recoveryMainItem=new FalultMainItem(this,logList); 
		listRecoveryQuery.setPullLoadEnable(true);
		listRecoveryQuery.setAdapter(recoveryMainItem);
		listRecoveryQuery.setXListViewListener(this);
		
		btnRecoveryQueryToTop.setOnClickListener(this);
		btnQueryRecovery.setOnClickListener(this);
		listRecoveryQuery.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Mai_FaultReportMDL faultReportMDL=(Mai_FaultReportMDL)InformationsList.get(arg2+"");
				GlobalData.faultReportMDL = faultReportMDL;
				Intent i = new Intent(RecoveryQueryActivity.this,
						RecoveryMianViewActivity.class);
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
		case R.id.btnRecoveryQueryToTop:
			GlobalData.faulList=null;
			finish();
			break;
		case R.id.btnQueryRecovery:
			Intent queryInit=new Intent(this,RecoveryQueryKeyActivity.class);
			startActivityForResult(queryInit, 20);
			break;
		default:
			break;
		}
	}
	
	Handler handler=new Handler()
	{
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			logList.clear();
			recover_query_progresbar.setVisibility(View.VISIBLE);
			recoveryMainItem.notifyDataSetChanged();
			initData(); 
			super.handleMessage(msg);
		}
		
	};
	
	String key,beginDate,endDate,place,status;//查询关键字
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(resultCode==20)
		{
			key=data.getExtras().getString("key");
			beginDate=data.getExtras().getString("beginDate");
			endDate=data.getExtras().getString("endDate");
			place=data.getExtras().getString("place");
			status=data.getExtras().getString("status");
			Message msg=new Message();
			msg.what=1;
			handler.sendMessage(msg);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			GlobalData.faulList=null;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void BackToTop() {
		Intent i = new Intent(RecoveryQueryActivity.this, HomeActivity.class)
				.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
		finish();
	}
 

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		logList.clear();
		pageIndex=1;
		RecoveAsyncTask recoveAsyncTask=new RecoveAsyncTask();
		recoveAsyncTask.execute(null);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub 
		pageIndex++;
		pageIndex++;
		if(pageIndex<=toalPage)
		{
			RecoveAsyncTask recoveAsyncTask=new RecoveAsyncTask();
			recoveAsyncTask.execute(null); 
		}else {
			listRecoveryQuery.stopLoadMore();
			DialogHelper.showTost(this, "没有更多数据！");
		}  
		
	}
	
	private void onLoad() {
		listRecoveryQuery.stopRefresh();
		listRecoveryQuery.stopLoadMore();
		Date now =new Date();
		SimpleDateFormat simple=new SimpleDateFormat("HH:mm:ss");
		listRecoveryQuery.setRefreshTime(simple.format(now));
	} 
	
	private class RecoveAsyncTask extends AsyncTask<String, String , Boolean>
	{ 
		@Override
		protected Boolean doInBackground(String... params) {
			String userId=GlobalData.employeeMDL.getID(); 
			try { 
				HashMap<String, String> query=new HashMap<String, String>();
				query.put("key", key);
				query.put("beginDate",beginDate );
				query.put("endDate",endDate );
				query.put("place",place );
				query.put("status",status ); 
				List<Mai_FaultReportMDL> list=recoveryMainBo.queryRecovery(query, pageIndex);
				if(list!=null && list.size()>0)
				{
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
				} else {
					toalPage=0;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
			return true;
		} 

		@Override
		protected void onPostExecute(Boolean result) { 
			onLoad();
			if(toalPage<2){
				listRecoveryQuery.setFooterView(View.GONE);
			}
			recover_query_progresbar.setVisibility(View.GONE);
			recoveryMainItem.notifyDataSetChanged();
			super.onPostExecute(result);
		}
		
	}
	
}
