package com.goldsunny.itsm.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.goldsunny.itsm.R;

public class Splash extends Activity {

	private final int SPLASH_DISPLAY_LENGHT = 3000; // 延迟三秒
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		final View view=View.inflate(this, R.layout.splash, null);
		setContentView(view);
		AlphaAnimation animation=new AlphaAnimation(0.5f, 1.0f);
		animation.setDuration(3000);
		view.setAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub 
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				Intent mainIntent = new Intent(Splash.this, Main.class); 
				Splash.this.startActivity(mainIntent); 
				Splash.this.finish();
			}
		});
		/*
		 * imageView=(ImageView)findViewById(R.id.welcome_img); AlphaAnimation
		 * alphaAnimation = new AlphaAnimation(0.3f, 1.0f); //设透明度(from,to)
		 * alphaAnimation.setDuration(3000); //设置延迟时间
		 * imageView.setImageDrawable(
		 * getResources().getDrawable(R.drawable.homebg));
		 * imageView.startAnimation(alphaAnimation);
		 * alphaAnimation.setAnimationListener(new MyAlphaAnimation()); new
		 * Handler().postDelayed(new Runnable() {
		 * 
		 * @Override public void run() {
		 * 
		 * Intent mainIntent = new Intent(Splash.this, Main.class);
		 * 
		 * Splash.this.startActivity(mainIntent);
		 * 
		 * Splash.this.finish();
		 * 
		 * } }, SPLASH_DISPLAY_LENGHT);
		 */
	}
	
	/*
	private class MyAlphaAnimation implements AnimationListener 
	{

		@Override
		public void onAnimationEnd(Animation animation) { 
			Intent mainIntent = new Intent(Splash.this, Main.class); 
			Splash.this.startActivity(mainIntent);

			Splash.this.finish();
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			Intent mainIntent = new Intent(Splash.this, Main.class); 
			Splash.this.startActivity(mainIntent);

			Splash.this.finish();
			
		}

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		} 
	}
	*/
}
