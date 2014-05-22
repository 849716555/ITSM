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
import com.goldsunny.itsm.businesslogic.StoreQueryBo;
import com.goldsunny.itsm.model.Equ_EquipmentMDL;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.view.XListView.IXListViewListener;

/** 
 *库存查询详细设备列表     
 * @author yangwy       
 * @version 1.0     
 * @created 2014-5-16 上午10:05:33
 */
public class StoreQueryEquListAcivity extends Activity implements OnItemClickListener, IXListViewListener {

	Button btnRecoveryQueryToTop;
	ProgressBar recover_query_progresbar;
	List<HashMap<String, String>> logList;  
	private XListView listStore;
	private StoreListAdapter storeListAdapter; 
	private int pageIndex = 1, toalPage = 0;
	private String thingId;
	StoreQueryBo storeQueryBo; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.store_query_equdetail_list);
		SysApplication.getInstance().addActivity(this); 
		btnRecoveryQueryToTop = (Button) findViewById(R.id.btnRecoveryQueryToTop);
		recover_query_progresbar = (ProgressBar) findViewById(R.id.recover_query_progresbar);
		logList=new ArrayList<HashMap<String,String>>(); 
		listStore = (XListView) findViewById(R.id.list_store_detail_list); 
		storeListAdapter = new StoreListAdapter(this, logList);
		listStore.setAdapter(storeListAdapter);
		listStore.setXListViewListener(this);
		listStore.setPullLoadEnable(true);
		listStore.setPullRefreshEnable(true);
		listStore.setOnItemClickListener(this); 
		btnRecoveryQueryToTop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//返回
				finish(); 
			}
		}); 
		storeQueryBo = new StoreQueryBo();
		thingId=getIntent().getStringExtra("thingId");
		StoreQueryEquAsyncTask storeQueryEquAsyncTask=new StoreQueryEquAsyncTask();
		storeQueryEquAsyncTask.execute(null);
	}

	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) { 
		
	}
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		logList.clear();
		pageIndex = 1;
		StoreQueryEquAsyncTask storeQueryEquAsyncTask = new StoreQueryEquAsyncTask();
		storeQueryEquAsyncTask.execute(null);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		pageIndex++;  
		if (pageIndex <= toalPage) {
			StoreQueryEquAsyncTask storeQueryEquAsyncTask = new StoreQueryEquAsyncTask();
			storeQueryEquAsyncTask.execute(null);
		} else {
			listStore.stopLoadMore();
			listStore.setFooterView(View.GONE);
			DialogHelper.showTost(this, "没有更多数据！");
		}

	}
	
	private void onLoad() {
		listStore.stopRefresh();
		listStore.stopLoadMore();
		Date now = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("HH:mm:ss");
		listStore.setRefreshTime(simple.format(now));
	}
	
	private class StoreQueryEquAsyncTask extends AsyncTask<String, String, Boolean> {
		@Override
		protected Boolean doInBackground(String... params) {
			try { 
				List<Equ_EquipmentMDL> list = storeQueryBo.getEqmentListByThingId(thingId, pageIndex); 
				if(list!=null && list.size()>0)
				{
					Equ_EquipmentMDL equ=null;
					HashMap<String, String> map=null; 
					for(int i=0;i<list.size();i++)
					{
						equ=list.get(i);
						map=new HashMap<String, String>();
						map.put("title", equ.getName());
						map.put("describe", "设备数量："+equ.getNumber()); 
						logList.add(map);
					} 
					toalPage=list.get(0).getTotalPage();
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
			recover_query_progresbar.setVisibility(View.GONE);
			if(toalPage<2){
				listStore.setFooterView(View.GONE);
			}
			storeListAdapter.notifyDataSetChanged();
			super.onPostExecute(result);
		}

	}

}
