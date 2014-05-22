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
import com.goldsunny.itsm.businesslogic.StoreQueryBo;
import com.goldsunny.itsm.model.StoreMDL;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.view.XListView.IXListViewListener;

/**
 * 仓库查询列表
 * 
 * @author yangwy
 * @version 1.0
 * @created 2014-5-15 下午4:38:53
 */
public class StoreQuerListActivity extends Activity implements OnItemClickListener, IXListViewListener {

	Button btnRecoveryQueryToTop;
	ProgressBar recover_query_progresbar;
	List<HashMap<String, String>> logList; 
	List<StoreMDL> storeList;
	private XListView listStore;
	private StoreListAdapter storeListAdapter;
	private String key, name, brand, store, type;
	private int pageIndex = 1, toalPage = 0;
	StoreQueryBo storeQueryBo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.store_query_list);
		SysApplication.getInstance().addActivity(this); 
		btnRecoveryQueryToTop = (Button) findViewById(R.id.btnRecoveryQueryToTop);
		recover_query_progresbar = (ProgressBar) findViewById(R.id.recover_query_progresbar);
		logList=new ArrayList<HashMap<String,String>>();
		storeList=new ArrayList<StoreMDL>();
		listStore = (XListView) findViewById(R.id.list_store_list); 
		storeListAdapter = new StoreListAdapter(this, logList);
		listStore.setAdapter(storeListAdapter);
		listStore.setXListViewListener(this);
		listStore.setPullLoadEnable(true);
		listStore.setPullRefreshEnable(true);
		listStore.setOnItemClickListener(this);

		btnRecoveryQueryToTop.setOnClickListener(onClickListener);
		initData();
		storeQueryBo = new StoreQueryBo();
		StoreQueryAsyncTask storeQueryAsyncTask=new StoreQueryAsyncTask();
		storeQueryAsyncTask.execute(null);
	}

	public void initData() {
		// 初始化查询条件数据
		Intent querData = getIntent();
		key = querData.getStringExtra("key");
		name = querData.getStringExtra("name");
		brand = querData.getStringExtra("brand");
		store = querData.getStringExtra("store");
		type = querData.getStringExtra("type");

	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnRecoveryQueryToTop:
				finish();
				break;

			default:
				break;
			}
		}
	};

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		StoreMDL store=storeList.get(arg2-1);
		if(!"12802".equals(store.getShapeType()))
		{
			Intent equIntent=new Intent(this,StoreQueryEquListAcivity.class);
			equIntent.putExtra("thingId", store.getOID());
			startActivity(equIntent);
		}

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		logList.clear();
		pageIndex = 1;
		StoreQueryAsyncTask recoveAsyncTask = new StoreQueryAsyncTask();
		recoveAsyncTask.execute(null);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		pageIndex++;  
		if (pageIndex <= toalPage) {
			StoreQueryAsyncTask recoveAsyncTask = new StoreQueryAsyncTask();
			recoveAsyncTask.execute(null);
		} else {
			listStore.stopLoadMore();
			listStore.setFooterView(View.GONE);
			DialogHelper.showTost(this, "已是最后一页！");
		}

	}

	private void onLoad() {
		listStore.stopRefresh();
		listStore.stopLoadMore();
		Date now = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("HH:mm:ss");
		listStore.setRefreshTime(simple.format(now));
	}

	private class StoreQueryAsyncTask extends AsyncTask<String, String, Boolean> {
		@Override
		protected Boolean doInBackground(String... params) {
			try {
				HashMap<String, String> query = new HashMap<String, String>();
				query.put("key", key);
				query.put("name", name);
				query.put("brand", brand);
				query.put("store", store);
				query.put("type", type); 
				List<StoreMDL> list = storeQueryBo.getStoreList(query, pageIndex); 
				if(list!=null && list.size()>0)
				{
					StoreMDL storeMDL=null;
					HashMap<String, String> map=null; 
					for(int i=0;i<list.size();i++)
					{
						storeMDL=list.get(i);
						map=new HashMap<String, String>();
						map.put("title", storeMDL.getName());
						map.put("describe", "设备数量："+storeMDL.getStockNumber()); 
						logList.add(map);
					}
					storeList=list;
					toalPage=list.get(0).getTotalPage();
				}else {
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
				listStore.setFooterView(View.GONE);
			}
			recover_query_progresbar.setVisibility(View.GONE);
			storeListAdapter.notifyDataSetChanged();
			super.onPostExecute(result);
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		storeListAdapter.notifyDataSetChanged();
		super.onResume();
	}
	
	
	
	 

}
