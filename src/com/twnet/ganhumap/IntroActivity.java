package com.twnet.ganhumap;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class IntroActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().hide();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_intro);
		final TextView welcomeText = (TextView)findViewById(R.id.welcome_textview);
		ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
		animator.setDuration(1000);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				welcomeText.animate().alpha((float)animation.getAnimatedValue());
				
			}
		});
			
		
	}
}
