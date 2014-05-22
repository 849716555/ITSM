package com.goldsunny.itsm.util;

import com.goldsunny.itsm.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DialogHelper {
	public static void showDialog(Context context, String mess) {
		new AlertDialog.Builder(context).setTitle("提示").setMessage(mess).setNegativeButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		}).show();
	}

	/** 
	 * 描述:  提示框
	 * @param context
	 * @param mess
	 */
	public static void showTost(Context context, String mess) {
		Toast toast=Toast.makeText(context, mess, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0); 
		toast.show();
	}
	
	/** 
	 * 描述:  操作成功提示
	 * @param context
	 * @param mess
	 * @param isSucces 是否操作成功
	 */
	public static void showTostSuccese(Context context, String mess,boolean isSucces)
	{
		Toast toast=Toast.makeText(context, mess, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout toastView =(LinearLayout)toast.getView();
		ImageView iamgeView=new ImageView(context);
		if(isSucces)
			iamgeView.setImageResource(R.drawable.dui);
		else {
			iamgeView.setImageResource(R.drawable.cuo);
		}
		toastView.addView(iamgeView,0);
		toast.show();
	}

	
}
