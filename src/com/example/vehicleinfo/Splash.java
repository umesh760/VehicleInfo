package com.example.vehicleinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

/**
 * Created by Umesh on 12/18/2015.
 */
public class Splash extends Activity {
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
		LinearLayout splashLayout = (LinearLayout) findViewById(R.id.item_image);

		fadeIn.setAnimationListener(new AnimationListener() {

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
			
					Handler hd = new Handler();

					hd.postDelayed(new Runnable() {

						@Override
						public void run() {
							intent = new Intent(Splash.this, MainActivity.class);
							startActivity(intent);
							overridePendingTransition(R.anim.left, R.anim.right);
							finish();
						}

					}, 2000);
				}

		
		});

		splashLayout.startAnimation(fadeIn);

	}

}