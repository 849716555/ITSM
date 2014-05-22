package com.goldsunny.itsm.util;

import java.io.IOException;

import com.goldsunny.itsm.R;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;

public class HardWareHelper {

	public static void playVibrator(Context c)
	{
		try {
			Vibrator vibrator;
			vibrator = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);     
			if(vibrator==null)return;
	        long[] pattern = {1000,1000}; // OFF/ON/OFF/ON...     
	        vibrator.vibrate(pattern, -1);//-1不重复，非-1为从pattern的指定下标开始重复
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void playLoadCardSound(Context c)
	{
		MediaPlayer mMediaPlayer = new MediaPlayer();
		AudioManager audioManager=(AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
		try {
			audioManager.adjustVolume(AudioManager.ADJUST_RAISE, 0);
			AssetFileDescriptor file = c.getResources().openRawResourceFd(R.raw.speech);
			mMediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
		             file.getLength());
			mMediaPlayer.setVolume(100, 100);
			mMediaPlayer.prepare();
			mMediaPlayer.start();
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
