package com.goldsunny.itsm.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.model.Mai_InspRecordDetailMDL;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.SysApplication;

/**
 * 执行巡检计划    
 * @author yangwy       
 * @version 1.0     
 * @created 2014-5-21 下午1:59:37
 */
public class InspExecuActivity extends Activity implements OnClickListener,OnItemClickListener{

	private TextView txtIPItem_CheckItemName,txtIPItem_Request,txtIPItem_Equ;
	private Button btnIPItem_Pre,btnIPItem_Next;
	private EditText txtIPItem_Code;
	int count, totalCount;
	List<HashMap<String, String>> logList;
	SimpleAdapter arrayAdapte;// 列表适配器
	private Mai_InspRecordDetailMDL inspRecDetail;
	private ListView listViewEqu;//巡检设备列表

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.insp_equ);
		SysApplication.getInstance().addActivity(this); 
		count=Integer.parseInt(getIntent().getStringExtra("count"));//当前巡检位置
		totalCount=GlobalData.inspRecordList.size();//需要巡检的总数
		inspRecDetail=GlobalData.inspRecordList.get(count-1);
		init();
		initButtionShow();
		logList=new ArrayList<HashMap<String,String>>();
		arrayAdapte = new SimpleAdapter(this, logList, R.layout.list_item_insp_detail_equ, new String[] { "equName" },
				new int[] { R.id.item_text_equname});
		listViewEqu=(ListView)findViewById(R.id.listIPItem_Equ);
		listViewEqu.setAdapter(arrayAdapte);
		listViewEqu.setOnItemClickListener(this);
	}
	
	public void init()
	{
		btnIPItem_Pre=(Button)findViewById(R.id.btnIPItem_Pre);
		btnIPItem_Next=(Button)findViewById(R.id.btnIPItem_Next);
		btnIPItem_Next.setOnClickListener(this);
		btnIPItem_Pre.setOnClickListener(this);
		txtIPItem_CheckItemName=(TextView)findViewById(R.id.txtIPItem_CheckItemName);
		txtIPItem_Request=(TextView)findViewById(R.id.txtIPItem_Request);
		txtIPItem_CheckItemName.setText(inspRecDetail.getCheck_ItemName());
		txtIPItem_Request.setText(inspRecDetail.getCheck_Req());
	}
	
	public void initButtionShow()
	{ 
		if(count==1){
			btnIPItem_Pre.setVisibility(View.INVISIBLE);
		}else {
			btnIPItem_Pre.setVisibility(View.VISIBLE);
		}
		if (count==totalCount) {
			btnIPItem_Next.setVisibility(View.INVISIBLE);
		}else {
			btnIPItem_Next.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
