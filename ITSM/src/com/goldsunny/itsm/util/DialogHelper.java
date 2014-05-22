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
		new AlertDialog.Builder(context).setTitle("��ʾ").setMessage(mess).setNegativeButton("ȷ��", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		}).show();
	}

	/** 
	 * ����:  ��ʾ��
	 * @param context
	 * @param mess
	 */
	public static void showTost(Context context, String mess) {
		Toast toast=Toast.makeText(context, mess, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0); 
		toast.show();
	}
	
	/** 
	 * ����:  �����ɹ���ʾ
	 * @param context
	 * @param mess
	 * @param isSucces �Ƿ�����ɹ�
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
