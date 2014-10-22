package org.iamwsbear.weightpk;

import com.ryanharter.viewpager.ViewPager;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		initView();
	}
	
	private void initView(){
		viewPager = (ViewPager) findViewById(R.id.main_viewpager);
	}
	
	


}
