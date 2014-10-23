package org.iamwsbear.weightpk.activity;

import java.util.ArrayList;
import java.util.List;

import org.iamwsbear.weightpk.R;
import org.iamwsbear.weightpk.adapter.VerticalPagerAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.ryanharter.viewpager.ViewPager;
import com.ryanharter.viewpager.ViewPager.OnPageChangeListener;

public class MainActivity extends Activity implements OnPageChangeListener, OnClickListener {

	private ViewPager viewPager;

	private VerticalPagerAdapter adapter;

	private List<View> list = new ArrayList<View>();

	private Animation clockHourAnimal, clockMinuteAnimal;

	private ImageView clockHourPoint, clockMinutePoint;

	private Button btn;

	private int i = 0;

	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		initView();
		initAnimal();
		initAdapter();
		initDo();
	}

	@SuppressLint({ "InflateParams", "HandlerLeak" })
	private void initView() {
		View firstGuide = LayoutInflater.from(this).inflate(
				R.layout.guidepage_first_layout, null);
		list.add(firstGuide);
		clockHourPoint = (ImageView) firstGuide
				.findViewById(R.id.guidepage_first_hour_point);
		clockMinutePoint = (ImageView) firstGuide
				.findViewById(R.id.guidepage_first_minute_point);
		btn = (Button) firstGuide.findViewById(R.id.guidepage_first_click);
		btn.setOnClickListener(this);

		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				super.handleMessage(msg);
				if (msg.what == 1) {
					clockHourPoint.clearAnimation();
					clockMinutePoint.clearAnimation();
					btn.setVisibility(View.VISIBLE);
				}
			};
		};
	}

	private void initAnimal() {
		clockHourAnimal = AnimationUtils.loadAnimation(MainActivity.this,
				R.anim.animal_circle_hour_scale);
		clockMinuteAnimal = AnimationUtils.loadAnimation(MainActivity.this,
				R.anim.animal_circle_minute_scale);
		clockMinuteAnimal.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				Log.i("wangsong", "start-------------");
				if (i == 0) {
					new Thread() {
						@Override
						public void run() {
							try {
								Thread.sleep(10000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							Message msg = Message.obtain();
							msg.what = 1;
							handler.sendMessage(msg);
						}
					}.start();
					i ++;
				}
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {

			}
		});
	}

	private void initAdapter() {
		viewPager = (ViewPager) findViewById(R.id.main_viewpager);
		adapter = new VerticalPagerAdapter(list);
		viewPager.setAdapter(adapter);
	}

	private void initDo() {
		startAniaml(0);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		startAniaml(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	private void startAniaml(int position) {
		switch (position) {
		case 0:
			clockHourPoint.startAnimation(clockHourAnimal);
			clockMinutePoint.startAnimation(clockMinuteAnimal);
			break;
		case 1:
			break;
		case 2:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.guidepage_first_click:
			btn.setVisibility(View.INVISIBLE);
			startAniaml(0);
			break;

		default:
			break;
		}
	}
}
