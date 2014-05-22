package com.goldsunny.itsm.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.util.ImageUtil;
import com.goldsunny.itsm.util.SysApplication;

public class ShowImageActivty extends Activity {

	private TouchImageView imgView;
	private Button btnDelPhot,btnCance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.showimage);
		SysApplication.getInstance().addActivity(this);
		Bundle bundle = getIntent().getExtras();
		String path = bundle.getString("path");
		//Bitmap bm = BitmapFactory.decodeFile(path);
		Bitmap bm=ImageUtil.loadBitmap(path, true);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		imgView = (TouchImageView) findViewById(R.id.showImageView);
		imgView.setImageBitmap(bm);
		imgView.initImageView(dm.widthPixels, dm.heightPixels - 80); 
		imgView.setBackgroundColor(getResources().getColor(R.color.black));
		btnDelPhot = (Button) findViewById(R.id.btnDelPhoto);
		btnDelPhot.setOnClickListener(new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				setResult(RESULT_OK, null);
				finish();
			}
		});
		btnCance=(Button)findViewById(R.id.btnCance);
		btnCance.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				finish();
			}
		});
		String canDel=getIntent().getStringExtra("canDel");
		if("false".equals(canDel))
		{
			// «∑Òø…“‘…æ≥˝Õº∆¨
			btnDelPhot.setVisibility(View.GONE);
		}
	}

}
