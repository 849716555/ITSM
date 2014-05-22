package com.goldsunny.itsm.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.businesslogic.InspPlanBo;
import com.goldsunny.itsm.model.Mai_FaultReportMDL;
import com.goldsunny.itsm.model.Mai_InspPlanMDL;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.view.XListView.IXListViewListener;

/**
 * 巡检计划
 * 
 * @author yangwy
 * @version 1.0
 * @created 2014-5-20 下午4:41:18
 */
public class InspMainActivity extends Activity implements IXListViewListener,OnItemClickListener{

	private Button btnInspMainToTop;
	private ProgressBar insp_plan_progresbar;
	List<HashMap<String, String>> logList;
	private XListView listInsp;
	private FalultMainItem inspMainItem;
	private int pageIndex=1,toalPage=0;
	private InspPlanBo inspPlanBo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.insp_main);
		SysApplication.getInstance().addActivity(this); 
		btnInspMainToTop=(Button)findViewById(R.id.btnInspMainToTop);
		btnInspMainToTop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				back();
			}
		});
		insp_plan_progresbar=(ProgressBar)findViewById(R.id.insp_plan_progresbar);
		logList=new ArrayList<HashMap<String,String>>();
		listInsp = (XListView) findViewById(R.id.litviewInsp_main); 
		inspMainItem=new FalultMainItem(this,logList);
		listInsp.setPullRefreshEnable(true);
		listInsp.setPullLoadEnable(true);
		listInsp.setAdapter(inspMainItem);
		listInsp.setXListViewListener(this);
		listInsp.setOnItemClickListener(this);
		InspMainAsyncTask inspMainAsyncTask=new InspMainAsyncTask();
		inspMainAsyncTask.execute(null);
	}
	
	public void back(){
		finish();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		logList.clear();
		pageIndex=1;
		InspMainAsyncTask recoveAsyncTask=new InspMainAsyncTask();
		recoveAsyncTask.execute(null);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		pageIndex++;
		pageIndex++;
		if(pageIndex<=toalPage)
		{
			InspMainAsyncTask recoveAsyncTask=new InspMainAsyncTask();
			recoveAsyncTask.execute(null); 
		}else {
			listInsp.stopLoadMore();
			DialogHelper.showTost(this, "已经加载全部数据！");
		}
	}
	
	private void onLoad() {
		listInsp.stopRefresh();
		listInsp.stopLoadMore();
		Date now =new Date();
		SimpleDateFormat simple=new SimpleDateFormat("HH:mm:ss");
		listInsp.setRefreshTime(simple.format(now));
	} 
	
	private class InspMainAsyncTask extends AsyncTask<String, String , Boolean>
	{ 
		@Override
		protected Boolean doInBackground(String... params) { 
			try {  
				List<Mai_InspPlanMDL> list=inspPlanBo.getInspPlanMainList( pageIndex);
				if(list!=null && list.size()>0)
				{
					for(int i=0;i<list.size();i++)
					{
						Mai_InspPlanMDL inspMainMDL=new Mai_InspPlanMDL();
						inspMainMDL=list.get(i);
						HashMap<String, String> map=new HashMap<String, String>();
						map.put("title", inspMainMDL.getPlan_Title());
						map.put("describe", inspMainMDL.getPlanners());
						map.put("oid", inspMainMDL.getOID());
						logList.add(map); 
						//toalPage=faultReportMDL.getTotalPage();//赋值总页数
					} 
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
				listInsp.setFooterView(View.GONE);
			}
			insp_plan_progresbar.setVisibility(View.GONE);
			inspMainItem.notifyDataSetChanged();
			super.onPostExecute(result);
		}
		
	}
	
}
