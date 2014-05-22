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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.businesslogic.InspPlanBo;
import com.goldsunny.itsm.model.Mai_InspPLanDetailMDL;
import com.goldsunny.itsm.model.Mai_InspPlanMDL;
import com.goldsunny.itsm.model.Mai_InspRecordDetailMDL;
import com.goldsunny.itsm.model.Mai_InspRecordMDL;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.view.XListView.IXListViewListener;

public class InspRecDetailListAcivity  extends Activity implements IXListViewListener,OnItemClickListener {

	private Button btnInspRecordDetailToTop;
	private ProgressBar insp_record_detail_progresbar;
	List<HashMap<String, String>> logList;
	private XListView listInsp;
	private FalultMainItem listItem;
	private int pageIndex=1,toalPage=0;
	private InspPlanBo inspPlanBo;
	private String inspPlanId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.insp_main);
		SysApplication.getInstance().addActivity(this); 
		inspPlanId=getIntent().getStringExtra("inspPlanId");
		btnInspRecordDetailToTop=(Button)findViewById(R.id.btnInspRecordDetailToTop);
		btnInspRecordDetailToTop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				back();
			}
		});
		insp_record_detail_progresbar=(ProgressBar)findViewById(R.id.insp_record_detail_progresbar);
		logList=new ArrayList<HashMap<String,String>>();
		listInsp = (XListView) findViewById(R.id.litviewInsp_main); 
		listItem=new FalultMainItem(this,logList);
		listInsp.setPullRefreshEnable(true);
		listInsp.setPullLoadEnable(true);
		listInsp.setAdapter(listItem);
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
		Intent intent=new Intent(this,InspExecuActivity.class);
		intent.putExtra("count", String.valueOf(arg2)); 
		startActivity(intent);
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
				List<Mai_InspRecordDetailMDL> list=inspPlanBo.getInspPlanDetalList(inspPlanId, pageIndex);
				if(list!=null && list.size()>0)
				{
					Mai_InspRecordDetailMDL insPlanDetailMDL=null;
					for(int i=0;i<list.size();i++)
					{
						insPlanDetailMDL=new Mai_InspRecordDetailMDL();
						insPlanDetailMDL=list.get(i);
						HashMap<String, String> map=new HashMap<String, String>();
						map.put("title", insPlanDetailMDL.getCheck_ItemName());
						map.put("describe", insPlanDetailMDL.getCheck_Req());
						map.put("oid", insPlanDetailMDL.getOID());
						logList.add(map); 
						//toalPage=faultReportMDL.getTotalPage();//赋值总页数
					} 
					GlobalData.inspRecordList=list;
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
			insp_record_detail_progresbar.setVisibility(View.GONE);
			listItem.notifyDataSetChanged();
			super.onPostExecute(result);
		}
		
	}
}
