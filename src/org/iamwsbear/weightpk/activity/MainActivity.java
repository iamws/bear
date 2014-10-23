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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.ryanharter.viewpager.ViewPager;
import com.ryanharter.viewpager.ViewPager.OnPageChangeListener;

public class MainActivity extends Activity implements OnPageChangeListener {

	private ViewPager viewPager;

	private VerticalPagerAdapter adapter;

	private List<View> list = new ArrayList<View>();

	private Animation clockHourAnimal, clockMinuteAnimal;

	private ImageView clockHourPoint, clockMinutePoint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		initView();
		initAnimal();
		initAdapter();
		initDo();
	}

	@SuppressLint("InflateParams")
	private void initView() {
		View firstGuide = LayoutInflater.from(this).inflate(
				R.layout.guidepage_first_layout, null);
		list.add(firstGuide);
		clockHourPoint = (ImageView) firstGuide
				.findViewById(R.id.guidepage_first_hour_point);
		clockMinutePoint = (ImageView) firstGuide
				.findViewById(R.id.guidepage_first_minute_point);
	}

	private void initAnimal() {
		clockHourAnimal = AnimationUtils.loadAnimation(MainActivity.this,
				R.anim.animal_circle_hour_scale);
		clockMinuteAnimal = AnimationUtils.loadAnimation(MainActivity.this,
				R.anim.animal_circle_minute_scale);
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
}
