package com.goldsunny.itsm.view;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.businesslogic.EquipmentBO;
import com.goldsunny.itsm.businesslogic.RecoveryMainBo;
import com.goldsunny.itsm.model.Equ_EquipmentMDL;
import com.goldsunny.itsm.model.Mai_RecoveryDetailMDL;
import com.goldsunny.itsm.model.Sys_DictListMDL;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.IntentIntegrator;
import com.goldsunny.itsm.util.IntentResult;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.util.XmlHelper;
import com.goldsunny.itsm.view.tools.AbstractSpinerAdapter;
import com.goldsunny.itsm.view.tools.SpinerPopWindow;

public class RecoveryDetailShowActivity extends Activity {
	Button btnRecoveryDetailToTop, btnDetail_Save, btnDetail_Cancel;
	EditText tbRecoveryMode, tbEqument_Name, tbEqument_Replace, tbDetail_Reamk;
	private String sacnMethd="";
	private String equmentCode,recoveryMode;
	private Mai_RecoveryDetailMDL recoverDetailMDL;
	RecoveryMainBo recoveryMainBo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.recover_detail_show);
		SysApplication.getInstance().addActivity(this);
		recoveryMainBo=new RecoveryMainBo();
		btnRecoveryDetailToTop=(Button)findViewById(R.id.btnRecoveryDetailToTop);
		btnDetail_Save=(Button)findViewById(R.id.btnDetail_Save);
		btnDetail_Cancel=(Button)findViewById(R.id.btnDetail_Cancel);
		tbRecoveryMode=(EditText)findViewById(R.id.tbRecoveryMode);
		tbEqument_Name=(EditText)findViewById(R.id.tbEqument_Name);
		tbEqument_Replace=(EditText)findViewById(R.id.tbEqument_Replace);
		tbDetail_Reamk=(EditText)findViewById(R.id.tbDetail_Reamk);
		
		btnRecoveryDetailToTop.setOnClickListener(onClickListener);
		btnDetail_Cancel.setOnClickListener(onClickListener);
		btnDetail_Save.setOnClickListener(onClickListener); 
		initData(); 
		initModeType();
		tbRecoveryMode.setOnTouchListener(onTouchListener);
		tbEqument_Name.setOnTouchListener(onScanDeviceListener);
		tbEqument_Replace.setOnTouchListener(onScanDeviceListener);  
	}
	
	public void initData()
	{
		recoverDetailMDL=GlobalData.recoverDeatil;
		if(recoverDetailMDL!=null)
		{
			try {
				tbRecoveryMode.setText(XmlHelper.getDictByID(recoverDetailMDL.getRecoveryMode(), getApplicationContext()).getListName());
				recoveryMode=recoverDetailMDL.getRecoveryMode();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tbEqument_Name.setText(recoverDetailMDL.getName());
			tbEqument_Replace.setText(recoverDetailMDL.getReplaceName());
			tbDetail_Reamk.setText(recoverDetailMDL.getRemark());
		} 
	}

	private View.OnTouchListener onTouchListener = new View.OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (event.getAction() == MotionEvent.ACTION_UP) {
				dictPopWindow.setWidth(tbRecoveryMode.getWidth());
				dictPopWindow.showAsDropDown(tbRecoveryMode);
			}
			return false;
		}
	};
	
	//扫描设备 条形码
	private View.OnTouchListener onScanDeviceListener = new View.OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (event.getAction() == MotionEvent.ACTION_UP) {
				boolean bl=false;
				int curX = (int) event.getX();
				if (curX > v.getWidth() - 60) { 
					switch (v.getId()) {
					case R.id.tbEqument_Name:
						sacnMethd="tbEqument_Name";//故障设备
						if (!tbEqument_Name.getText().toString().trim().equals(""))
							bl=true;
						break;
					case R.id.tbEqument_Replace:
						sacnMethd="tbEqument_Replace";//替换设备
						if (!tbEqument_Replace.getText().toString().trim().equals(""))
							bl=true;
						break;
					default:
						break;
					}  
					if (bl) {
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(tbEqument_Name.getWindowToken(), 0);
						// BindData();
					} else {
						IntentIntegrator integrator = new IntentIntegrator(RecoveryDetailShowActivity.this);
						integrator.initiateScan();
					} 
				}
			}
			return false;
		}
	};

	private View.OnClickListener onClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnRecoveryDetailToTop:
				GlobalData.recoverDeatil=null;
				finish();
				break;
			case R.id.btnDetail_Save:
				threadSave();
				break;
			case R.id.btnDetail_Cancel:
				finish();
				break;
			default:
				break;
			}
		}

	};
	Equ_EquipmentMDL equ=null;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if (result != null) {
			String contents = result.getContents();
			if (contents != null) {
				equmentCode=contents;
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						EquipmentBO equipmentBO = new EquipmentBO();
						equ = equipmentBO.getEquMentByCode(equmentCode);
						Message msg=new Message();
						msg.what=1;
						handler.sendMessage(msg); 
					}
				}).start();
				Log.i("scan", "条形码：" + contents); 
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	Handler handler=new Handler()
	{

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				sacnInitData();
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	
	//扫描条形码初始化数据
	public void sacnInitData()
	{ 
		if(recoverDetailMDL ==null)
			recoverDetailMDL=new Mai_RecoveryDetailMDL();
		if(equ!=null)
		{
			if("tbEqument_Name".equals(sacnMethd))
			{
				tbEqument_Name.setText(equ.getName());
				recoverDetailMDL.setThingID(equ.getThingID());
				recoverDetailMDL.setEquipmentID(equ.getOID());
				recoverDetailMDL.setCode(equ.getCode());
				recoverDetailMDL.setName(equ.getName());
				recoverDetailMDL.setBrandName(equ.getBrandName());
				recoverDetailMDL.setModel(equ.getModel());
				recoverDetailMDL.setUnitName(equ.getUnitName());
			}else if("tbEqument_Replace".equals(sacnMethd)) {
				tbEqument_Replace.setText(equ.getName());
				recoverDetailMDL.setReplaceThingID(equ.getThingID());
				recoverDetailMDL.setReplaceCode(equ.getCode());
				recoverDetailMDL.setReplaceEquipmentID(equ.getOID());
				recoverDetailMDL.setReplaceName(equ.getName());
			}
		} 
		else {
			DialogHelper.showDialog(RecoveryDetailShowActivity.this, "编号："+equmentCode+"设备信息不存在");
		}
	}
	
	Handler mHandler=new Handler()
	{

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				DialogHelper.showTostSuccese(RecoveryDetailShowActivity.this, getResources().getString(R.string.save_succese),true);
				setResult(RESULT_OK);
				finish();
				break;
			case 2:
				DialogHelper.showTostSuccese(RecoveryDetailShowActivity.this, getResources().getString(R.string.save_faile),false); 
				break;
			case 3:
				String errString= msg.getData().getString("msg");
				DialogHelper.showDialog(RecoveryDetailShowActivity.this, errString);
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	
	public void threadSave()
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() { 
				String bl=SaveData();
				Message msg=new Message(); 
				if("true".equals(bl))
					msg.what=1;
				else if("false".equals(bl))
					msg.what=2; 
				else{
					Bundle data=new Bundle(); 
					data.putString("msg", bl);
					msg.setData(data);
					msg.what=3; 
				}
				mHandler.sendMessage(msg);
			}
		}).start();
	}

	private String SaveData() {
		boolean bl=false; 
		String errString=GetData();
		if(!"".equals(errString))
		{ 
			return errString;
		} 
		if(recoverDetailMDL.getOID() ==null)
		{
			UUID id = UUID.randomUUID();
			Intent data=getIntent();
			String recoveryMainID= data.getStringExtra("recoverMainId");
			recoverDetailMDL.setRecoveryMainID(recoveryMainID);
			recoverDetailMDL.setOID(id.toString());
			bl=recoveryMainBo.addRecoverDetail(recoverDetailMDL);
		}else {
			bl=recoveryMainBo.updateRecoverDetail(recoverDetailMDL); 
		} 
		if(bl)
		{
			return "true";
		}else {
			return "false";
		} 
	}

	private String GetData() {
		String errString = "";
		if(recoverDetailMDL==null)
		{
			recoverDetailMDL=new Mai_RecoveryDetailMDL();
		}
		if(!"".equals(tbRecoveryMode.getText()))
		{
			recoverDetailMDL.setRecoveryMode(recoveryMode);
		}else {
			errString=errString+"处理方式不能为空;";
		}
		if("".equals(tbEqument_Name.getText()))
		{
			errString=errString+"故障设备不能为空;";
		}
		if("".equals(tbEqument_Replace.getText()))
		{
			errString=errString+"替换设备不能为空；";
		}
		if(!tbDetail_Reamk.getText().toString().equals(""))
		{
			recoverDetailMDL.setRemark(tbDetail_Reamk.getText().toString());
		}
		return errString;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			GlobalData.recoverDeatil=null;
		}
		return super.onKeyDown(keyCode, event);
	} 
	
	//处理方式下拉框数据
	private List<Sys_DictListMDL> dictList;
	private List<String> dictValueList;
	private SpinerPopWindow dictPopWindow;
	public void initModeType()
	{
		try { 
			dictList = XmlHelper.readDictXml(getApplicationContext(), "124");
			if (dictList != null && dictList.size() > 0) {
				dictValueList = new ArrayList<String>();
				for (Sys_DictListMDL dic : dictList) {
					dictValueList.add(dic.getListName());
				}
			}
			dictPopWindow = new SpinerPopWindow(this);
			dictPopWindow.refreshData(dictValueList, 0);
			dictPopWindow.setItemListener(new AbstractSpinerAdapter.IOnItemSelectListener() {
				@Override
				public void onItemClick(int pos) {
					recoveryMode=dictList.get(pos).getOID();
					tbRecoveryMode.setText(dictList.get(pos).getListName());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
