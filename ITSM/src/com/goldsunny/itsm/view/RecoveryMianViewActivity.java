package com.goldsunny.itsm.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.goldsunny.itsm.R;
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

public class RecoveryMianViewActivity extends Activity {
	Button btnRecoveryMainViewToTop, btnRecovery_Cancel,btnFaultView;
	TextView tbRecovery_Reason, tbRecovery_Solution, tbRecovery_Operator, tbRecovery_PlanTime, tbRecovery_PlanDate,
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
	RecoveryMainBo recoveryMainBo;
	FalultReportBo falultReportBo;

	private ListView detailListView;
	private List<Map<String, Object>> dateList = new ArrayList<Map<String, Object>>();// 列表数据
	private List<Mai_RecoveryDetailMDL> detalList = new ArrayList<Mai_RecoveryDetailMDL>();// 维修明细数据
	SimpleAdapter arrayAdapte;// 列表适配器
	private final static String RecoveryMode = "12401";// 整机替换
	private int scanSeq = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.recovery_main_view);
		SysApplication.getInstance().addActivity(this);
		btnRecoveryMainViewToTop = (Button) findViewById(R.id.btnRecoveryMainViewToTop);  
		btnRecovery_Cancel = (Button) findViewById(R.id.btnRecovery_Cancel); 
		btnFaultView = (Button) findViewById(R.id.btnFaultView);
		tbRecovery_Reason = (TextView) findViewById(R.id.tbRecovery_Reason);
		tbRecovery_Solution = (TextView) findViewById(R.id.tbRecovery_Solution);
		tbRecovery_Operator = (TextView) findViewById(R.id.tbRecovery_Operator);
		tbRecovery_PlanTime = (TextView) findViewById(R.id.tbRecovery_PlanTime);
		tbRecovery_PlanDate = (TextView) findViewById(R.id.tbRecovery_PlanDate);
		tbRecovery_FaultDetail = (TextView) findViewById(R.id.tbRecovery_FaultDetail);
		addRecoverDeatil=(ImageView)findViewById(R.id.addRecoverDetailM);
		btnRecoveryMainViewToTop.setOnClickListener(onClickListener); 
		btnRecovery_Cancel.setOnClickListener(onClickListener);
		btnFaultView.setOnClickListener(onClickListener);
		falultReportBo = new FalultReportBo();
		recoveryMainBo = new RecoveryMainBo(); 
		threadInit(); 
		detailListView = (ListView) findViewById(R.id.list_recover_deatil);
		arrayAdapte = new SimpleAdapter(this, dateList, R.layout.list_item_recover_detail, new String[] { "equ","replaceEqu" },
				new int[] { R.id.item_text_equ,R.id.item_text_replace });
		detailListView.setAdapter(arrayAdapte);
		detailListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				GlobalData.recoverDeatil = detalList.get(arg2);
				Intent detail = new Intent(RecoveryMianViewActivity.this, RecoveryDetailViewActivity.class);
				startActivityForResult(detail, BIND_AUTO_CREATE);  
			}
		});
		setListViewHeightBasedOnChildren(detailListView);
		RecoveAsyncTask task=new RecoveAsyncTask();
		task.execute(null);
	} 

	public void threadInit()
	{
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
					Message msg=new Message();
					msg.what=4;
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
			} 
		}
	}
	
	public void initDetailList()
	{
		try {
			if (recoveryMainMDL != null) {
				detalList = recoveryMainBo.getRecoverDeatalList(recoveryMainMDL.getOID());
				if(detalList!=null && detalList.size()>0)
				{
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
  
	private View.OnClickListener onClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnRecoveryMainViewToTop:
				finish();
				break; 
			case R.id.btnRecovery_Cancel:
				finish();
				break; 
			case R.id.btnFaultView:
				Intent viewFault=new Intent(RecoveryMianViewActivity.this,FaultReportViewActivity.class);
				startActivity(viewFault);
			default:
				break;
			}
		}

	};
	
	Handler mHandler=new Handler()
	{

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				DialogHelper.showTost(RecoveryMianViewActivity.this, "保存信息成功"); 
				break;
			case 2:
				DialogHelper.showTost(RecoveryMianViewActivity.this, "保存信息失败");
				break;
			case 3:
				String errString= msg.getData().getString("msg");
				DialogHelper.showDialog(RecoveryMianViewActivity.this, errString);
				break;
			case 4:
				//初始化数据
				InitView();
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	 
 
	
 

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
		Intent intentC = new Intent(RecoveryMianViewActivity.this, RecoveryMainActivity.class)
				.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intentC);
		finish();
	}
	
	private class RecoveAsyncTask extends AsyncTask<String, String , Boolean>
	{ 
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
