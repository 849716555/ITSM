package com.goldsunny.itsm.util;

import android.content.Context;
import android.content.Intent;

public class ServiceHelper {

	public static void startService(Context c) {

		Intent uploadCheckBasicServiceIntent = new Intent();
	//	uploadCheckBasicServiceIntent.setClass(c, HGCSService.class);
		c.startService(uploadCheckBasicServiceIntent);
		
		
//		Intent LoadCardServiceIntent = new Intent();
//		LoadCardServiceIntent.setClass(c, BTCardService.class);
//		c.startService(LoadCardServiceIntent);
	}
	public static void stopService(Context c)
	{
		
		Intent uploadCheckBasicServiceIntent = new Intent();
	//	uploadCheckBasicServiceIntent.setClass(c, HGCSService.class);
		c.stopService(uploadCheckBasicServiceIntent);
//		Intent LoadCardServiceIntent = new Intent();
//		LoadCardServiceIntent.setClass(c, BTCardService.class);
//		c.stopService(LoadCardServiceIntent);
//		Intent LoadCardServiceIntent = new Intent();
//		LoadCardServiceIntent.setClass(c, BTCardService.class);
//		c.stopService(LoadCardServiceIntent);
	}
}
