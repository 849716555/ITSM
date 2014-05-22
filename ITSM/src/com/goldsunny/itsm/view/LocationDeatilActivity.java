package com.goldsunny.itsm.view;
 
import com.goldsunny.itsm.R;

import android.app.Activity; 
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class LocationDeatilActivity extends Activity implements OnClickListener{

	private  Button btcance;
	private LinearLayout linLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loactiondetailview);
	}
	
	public void init()
	{
		btcance=(Button)findViewById(R.id.btn_cancel);
		btcance.setOnClickListener(this);
		linLayout=(LinearLayout)findViewById(R.id.dialog_layout);
		linLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_cancel:
			finish();
			break;
		case R.id.dialog_layout:
			finish();
			 break;
		default:
			break;
		}
		
	}
	
	
}
