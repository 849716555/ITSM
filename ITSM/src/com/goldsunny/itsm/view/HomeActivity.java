package com.goldsunny.itsm.view;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.businesslogic.BaseDataBo;
import com.goldsunny.itsm.model.MaintainTeamMDL;
import com.goldsunny.itsm.model.StockMDL;
import com.goldsunny.itsm.model.Sys_DictListMDL;
import com.goldsunny.itsm.model.Syst_LocationMDL;
import com.goldsunny.itsm.model.Syst_RoadMDL;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.util.XmlHelper;

public class HomeActivity extends Activity implements OnClickListener {

	Button btnFaultHandle, btnRecoverMain,btnLoginOut,btnHomeRecoveryQuery,btnStockQuery,btnMyLocation;
	TextView tbviewUserName, tbviewLoginTime, tbviewUserStation, tbviewLastLoginTime, txtTask;
	ProgressDialog progress;
	ListView listView;
	List<String> list = new ArrayList<String>();
	ArrayAdapter<String> arrayAdapte; 
	int count = 0;
	private Handler listHandler = new Handler();
	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// listHandler.postAtTime(this, 1000*1);//一秒之后执行
			Log.v("AAA", "执行代码");
			list.clear();
			arrayAdapte.notifyDataSetChanged();
			listHandler.postDelayed(this, 1000 * 20);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home);
		SysApplication.getInstance().addActivityHome(this);
		listView = (ListView) findViewById(R.id.listFaultRecord);
		arrayAdapte = new ArrayAdapter<String>(this, R.layout.timelistd, list);
		listView.setAdapter(arrayAdapte);
		listHandler.postAtTime(runnable, 1000 * 120);
		initData();
		init();
		setListener();
	}

	public void init() {
		btnFaultHandle = (Button) findViewById(R.id.btnFaultHandle);
		btnLoginOut = (Button) findViewById(R.id.btnLoginOut);
		btnRecoverMain=(Button)findViewById(R.id.btnRecoverMain);
		btnHomeRecoveryQuery=(Button)findViewById(R.id.btnHomeRecoveryQuery);
		btnStockQuery=(Button)findViewById(R.id.btnStockQuery);
		btnMyLocation=(Button)findViewById(R.id.btnMyLocation);
		// 加载基础数据
		BaseDateAsyncTask baseDateAsyncTask = new BaseDateAsyncTask();
		baseDateAsyncTask.execute(null);
	}

	public void setListener() {
		btnFaultHandle.setOnClickListener(this);
		btnLoginOut.setOnClickListener(this);
		btnRecoverMain.setOnClickListener(this);
		btnHomeRecoveryQuery.setOnClickListener(this);
		btnStockQuery.setOnClickListener(this);
		btnMyLocation.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnFaultHandle:
			Intent intentFaul = new Intent(this, FalultReportActivity.class);
			startActivity(intentFaul);
			//finish();
			break;
		case R.id.btnRecoverMain:
			Intent intentRecover = new Intent(this, RecoveryMainActivity.class);
			startActivity(intentRecover);
			//finish();
			break;
		case R.id.btnHomeRecoveryQuery:
			Intent intentRecoverQuery = new Intent(this, RecoveryQueryActivity.class);
			startActivity(intentRecoverQuery);
			break;
		case R.id.btnStockQuery:
			Intent intentStoreQuery = new Intent(this, StoreQueryKeyActivity.class);
			startActivity(intentStoreQuery);
			break;
		case R.id.btnMyLocation:
			Intent intentLocation = new Intent(this, MyNearFaultActivity.class);
			startActivity(intentLocation);
			break;
		case R.id.btnLoginOut:
			Intent intentLogin = new Intent(this, LoginActivity.class);
			intentLogin.putExtra("isHomeBack", "true");
			startActivity(intentLogin);
			finish();
			break;
		default:
			break;
		}
	}

	private void initData() {

	}

	public void update() {

	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (progress != null)
				progress.dismiss();
			switch (msg.what) {
			case 1:
				Intent i = new Intent(HomeActivity.this, LoginActivity.class);
				i.putExtra("isHomeBack", "true");
				startActivity(i);
				HomeActivity.this.finish();
				break;
			case 2:
				SysApplication.getInstance().exit(HomeActivity.this);
				break;
			default:
				break;
			}
		};
	};

	class loginOutThread implements Runnable {
		int what = 0;

		public loginOutThread(int what) {
			this.what = what;
		}

		public loginOutThread() {
		}

		@Override
		public void run() {
			Message message = new Message();
			message.what = what;
			handler.sendMessage(message);
		}
	}

	public void showLogoutAlert(final int what, final String msg) {
		Builder builder = new Builder(HomeActivity.this);
		builder.setMessage("确定" + msg + "吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				new Thread(new loginOutThread(what)).start();
				progress = ProgressDialog.show(HomeActivity.this, "请等待...", "正在" + msg, true);
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	@Override
	protected void onResume() {
		System.gc();
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			showLogoutAlert(2, "退出系统");
		}
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	// 异步加载基础数据
	private class BaseDateAsyncTask extends AsyncTask<String, String, Boolean> {

		@Override
		protected Boolean doInBackground(String... arg0) {
			BaseDataBo baseDataBo = new BaseDataBo();
			try {
				//路段
				List<Syst_RoadMDL> roadlist = baseDataBo.getRoad();
				GlobalData.roadMDLs = roadlist;
				String roadId = roadlist.get(0).getOID();
				//收费站维护队
				List<Syst_LocationMDL> locaList = baseDataBo.getTollStation(roadId);
				GlobalData.tollStation = locaList;
				//仓库
				List<StockMDL> stockList=baseDataBo.getStockList();
				GlobalData.stockList=stockList;
				//加载维护度信息
				GlobalData.teamId=baseDataBo.GetMaintainTeam();
				
				List<Sys_DictListMDL> dictList = baseDataBo.getDictList();
				// 创建数据字典XML
				String result = XmlHelper.createDicXml(dictList);
				OutputStream outStream = openFileOutput("dict.xml", MODE_PRIVATE);
				OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream);
				outStreamWriter.write(result);
				outStreamWriter.close();
				outStream.close();   
				List<MaintainTeamMDL> mainTeamList=baseDataBo.getTeanMDL();
				XmlHelper.writeMeaTeam(mainTeamList, getApplicationContext());
			} catch (Exception e) {
				e.printStackTrace();
				DialogHelper.showTost(getApplicationContext(), "数据初始化失败");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
		}

	}

}
