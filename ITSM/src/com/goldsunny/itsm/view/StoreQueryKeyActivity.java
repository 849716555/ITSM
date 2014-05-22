package com.goldsunny.itsm.view;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.model.StockMDL;
import com.goldsunny.itsm.model.Sys_DictListMDL;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.util.XmlHelper;
import com.goldsunny.itsm.view.tools.AbstractSpinerAdapter;
import com.goldsunny.itsm.view.tools.SpinerPopWindow;

/**
 * 库存查询关键字查询界面
 * 
 * @author yangwy
 * @version 1.0
 * @created 2014-5-15 下午3:31:04
 */
public class StoreQueryKeyActivity extends Activity {

	private Button btnBackToH, btnQuery;
	private EditText txtQueryKey, text_equ_name, text_equ_brand, text_que_store, txt_equ_type;
	private String types,stores;
	private boolean[] selected;
	private String[] value, result;
	private List<Sys_DictListMDL> dictList;
	private List<StockMDL> stockList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.store_query_key);
		SysApplication.getInstance().addActivity(this);
		btnBackToH = (Button) findViewById(R.id.btnBackToH);
		btnQuery = (Button) findViewById(R.id.btnQuery);
		txtQueryKey = (EditText) findViewById(R.id.txtQueryKey);
		text_equ_name = (EditText) findViewById(R.id.text_equ_name);
		text_equ_brand = (EditText) findViewById(R.id.text_equ_brand);
		text_que_store = (EditText) findViewById(R.id.text_que_store);
		txt_equ_type = (EditText) findViewById(R.id.txt_equ_type);
		text_que_store.setOnTouchListener(onTouchListener);
		txt_equ_type.setOnTouchListener(onTouchListener);
		btnBackToH.setOnClickListener(onClickListener);
		btnQuery.setOnClickListener(onClickListener);
		try {
			dictList = XmlHelper.readDictXml(getApplicationContext(), GlobalData.shareType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	private OnTouchListener onTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				switch (v.getId()) {
				case R.id.text_que_store:  
					if(GlobalData.stockList!=null && GlobalData.stockList.size()>0)
					{
						stockList=GlobalData.stockList;
						value=new String[stockList.size()];
						selected=new boolean[stockList.size()];
						for(int i=0;i<stockList.size();i++)
						{
							value[i]=stockList.get(i).getName();
							selected[i]=false;
						}
					}else {
						value=new String[0];
						selected=new boolean[0];
					}
					Builder builder = new AlertDialog.Builder(StoreQueryKeyActivity.this);
					builder.setTitle("多选列表对话框");
					builder.setIcon(android.R.drawable.ic_dialog_info); 
					builder.setMultiChoiceItems(value, selected, mutiListener);
					DialogInterface.OnClickListener btnListener = new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int which) {
							String selectedStr = "";
							stores="";
							for (int i = 0; i < selected.length; i++) {
								if (selected[i] == true) {
									selectedStr = selectedStr + " " + value[i];
									stores=stores+stockList.get(i).getOID()+",";
								}
							}

							text_que_store.setText(selectedStr);
						}
					};
					builder.setPositiveButton("确定", btnListener);
					builder.create();
					builder.show();
					break;
				case R.id.txt_equ_type:
					value=new String[dictList.size()];
					selected=new boolean[dictList.size()];
					for (int i = 0; i < dictList.size(); i++) {
						value[i]=dictList.get(i).getListName();
						selected[i]=false;
					}
					Builder builderType = new AlertDialog.Builder(StoreQueryKeyActivity.this);
					builderType.setTitle("多选列表对话框");
					builderType.setIcon(android.R.drawable.ic_dialog_info);
					
					builderType.setMultiChoiceItems(value, selected, mutiListener);
					DialogInterface.OnClickListener btnListener2 = new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int which) {
							String selectedStr = "";
							types="";
							for (int i = 0; i < selected.length; i++) {
								if (selected[i] == true) {
									selectedStr = selectedStr + " " + value[i];
									types=types+dictList.get(i).getOID()+",";
								}
							}

							txt_equ_type.setText(selectedStr);
						}
					};
					builderType.setPositiveButton("确定", btnListener2);
					builderType.create();
					builderType.show();
					break;
				default:
					break;
				}
			}
			return false;
		}
	};
	DialogInterface.OnMultiChoiceClickListener mutiListener = new DialogInterface.OnMultiChoiceClickListener() {

		@Override
		public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
			selected[which] = isChecked;
		}
	};

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnBackToH:
				finish();
				break;
			case R.id.btnQuery:
				goQuery();
				break;
			default:
				break;
			}
		}
	};

	public void goQuery() {
		String key = txtQueryKey.getText().toString();
		String name = text_equ_name.getText().toString();
		String brand = text_equ_brand.getText().toString(); 
		Intent storeIntent = new Intent(this, StoreQuerListActivity.class);
		storeIntent.putExtra("key", key);
		storeIntent.putExtra("name", name);
		storeIntent.putExtra("brand", brand);
		storeIntent.putExtra("store", stores);
		storeIntent.putExtra("type", types);
		startActivity(storeIntent);
	}

	 

}
