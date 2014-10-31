package org.iamwsbear.weightpk.activity;

import java.util.ArrayList;
import java.util.List;

import org.iamwsbear.weightpk.R;
import org.iamwsbear.weightpk.adapter.VerticalPagerAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ryanharter.viewpager.ViewPager;
import com.ryanharter.viewpager.ViewPager.OnPageChangeListener;

public class GuideActivity extends Activity implements OnPageChangeListener,
		OnClickListener {

	private ViewPager viewPager;

	private VerticalPagerAdapter adapter;

	private List<View> list = new ArrayList<View>();

	private Animation clockHourAnimal, clockMinuteAnimal, commonNextAnimal, textAlphaAnimal;

	private ImageView clockHourPoint, clockMinutePoint, firstNext, fristText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_activity);
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
		firstNext = (ImageView) firstGuide.findViewById(R.id.guidepage_first_next);
		fristText = (ImageView) firstGuide.findViewById(R.id.guidepage_first_text);

	}

	private void initAnimal() {
		clockHourAnimal = AnimationUtils.loadAnimation(GuideActivity.this,
				R.anim.animal_circle_hour_scale);
		clockMinuteAnimal = AnimationUtils.loadAnimation(GuideActivity.this,
				R.anim.animal_circle_minute_scale);
		commonNextAnimal = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.animal_bottom_next);
		textAlphaAnimal = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.animal_common_text_alpha);
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
			firstNext.startAnimation(commonNextAnimal);
			fristText.startAnimation(textAlphaAnimal);
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

		default:
			break;
		}
	}
}
