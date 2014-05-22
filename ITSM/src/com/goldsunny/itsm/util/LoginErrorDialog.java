package com.goldsunny.itsm.util;


import com.goldsunny.itsm.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginErrorDialog  extends Dialog implements android.view.View.OnClickListener{
	Context context;
	Button button;
	public LoginErrorDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	public LoginErrorDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.loginerrordialog);
		button=(Button)this.findViewById(R.id.btnLoginError);
		button.setOnClickListener(this);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		dismiss();
	}

}
