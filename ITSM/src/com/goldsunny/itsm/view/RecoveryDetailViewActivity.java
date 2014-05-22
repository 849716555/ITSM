package com.goldsunny.itsm.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.businesslogic.RecoveryMainBo;
import com.goldsunny.itsm.model.Mai_RecoveryDetailMDL;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.util.XmlHelper;

public class RecoveryDetailViewActivity extends Activity {
	Button btnRecoveryDetailViewToTop,btnDetail_Cancel;
	TextView tbRecoveryMode, tbEqument_Name, tbEqument_Replace, tbDetail_Reamk; 
	private Mai_RecoveryDetailMDL recoverDetailMDL;
	RecoveryMainBo recoveryMainBo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.recover_detail_view);
		SysApplication.getInstance().addActivity(this);
		recoveryMainBo=new RecoveryMainBo();
		btnRecoveryDetailViewToTop=(Button)findViewById(R.id.btnRecoveryDetailViewToTop); 
		btnDetail_Cancel=(Button)findViewById(R.id.btnDetail_Cancel);
		tbRecoveryMode=(TextView)findViewById(R.id.tbRecoveryMode);
		tbEqument_Name=(TextView)findViewById(R.id.tbEqument_Name);
		tbEqument_Replace=(TextView)findViewById(R.id.tbEqument_Replace);
		tbDetail_Reamk=(TextView)findViewById(R.id.tbDetail_Reamk); 
		btnDetail_Cancel.setOnClickListener(onClickListener); 
		btnRecoveryDetailViewToTop.setOnClickListener(onClickListener); 
		initData();  
	}
	
	public void initData()
	{
		recoverDetailMDL=GlobalData.recoverDeatil;
		if(recoverDetailMDL!=null)
		{
			try {
				tbRecoveryMode.setText(XmlHelper.getDictByID(recoverDetailMDL.getRecoveryMode(), getApplicationContext()).getListName()); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tbEqument_Name.setText(recoverDetailMDL.getName());
			tbEqument_Replace.setText(recoverDetailMDL.getReplaceName());
			tbDetail_Reamk.setText(recoverDetailMDL.getRemark());
		} 
	}
 
	 
	private View.OnClickListener onClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnRecoveryDetailViewToTop:
				GlobalData.recoverDeatil=null;
				finish();
				break; 
			case R.id.btnDetail_Cancel:
				GlobalData.recoverDeatil=null;
				finish();
				break;
			default:
				break;
			}
		}

	}; 
	 
	
	 
	 
	 
	 
	
	
}
