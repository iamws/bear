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
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ryanharter.viewpager.ViewPager;
import com.ryanharter.viewpager.ViewPager.OnPageChangeListener;

public class GuideActivity extends Activity implements OnPageChangeListener,
		OnClickListener {

	private ViewPager viewPager;

	private VerticalPagerAdapter adapter;

	private List<View> list = new ArrayList<View>();

	private Animation clockHourAnimal, clockMinuteAnimal, commonNextAnimal, textAlphaAnimal, commRightAnimal, commLeftAnimal, chapterAnimal;

	private ImageView clockHourPoint, clockMinutePoint, firstNext, fristText, secondDog, secondCat, secondImport, secondNext, secondSuperise, secondChapter;

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
		View firstGuideView = LayoutInflater.from(this).inflate(
				R.layout.guidepage_first_layout, null);
		clockHourPoint = (ImageView) firstGuideView
				.findViewById(R.id.guidepage_first_hour_point);
		clockMinutePoint = (ImageView) firstGuideView
				.findViewById(R.id.guidepage_first_minute_point);
		firstNext = (ImageView) firstGuideView.findViewById(R.id.guidepage_first_next);
		fristText = (ImageView) firstGuideView.findViewById(R.id.guidepage_first_text);
		View secondGuideView = LayoutInflater.from(this).inflate(
				R.layout.guidepage_second_layout, null);
		secondDog = (ImageView) secondGuideView.findViewById(R.id.guidepage_second_dog);
		secondCat = (ImageView) secondGuideView.findViewById(R.id.guidepage_second_miao);
		secondImport =(ImageView) secondGuideView.findViewById(R.id.guidepage_second_coming);
		secondNext =(ImageView) secondGuideView.findViewById(R.id.guidepage_second_next);
		secondSuperise =(ImageView) secondGuideView.findViewById(R.id.guidepage_second_suprise);
		list.add(firstGuideView);
		list.add(secondGuideView);
	}

	private void initAnimal() {
		clockHourAnimal = AnimationUtils.loadAnimation(GuideActivity.this,
				R.anim.animal_circle_hour_scale);
		clockMinuteAnimal = AnimationUtils.loadAnimation(GuideActivity.this,
				R.anim.animal_circle_minute_scale);
		commonNextAnimal = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.animal_bottom_next);
		textAlphaAnimal = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.animal_common_text_alpha);
		commLeftAnimal = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.animal_pic_left);
		commRightAnimal = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.animal_pic_right);
		chapterAnimal = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.animal_chapter);
		commLeftAnimal.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				secondDog.setVisibility(View.GONE);
				secondCat.setVisibility(View.VISIBLE);
				secondCat.startAnimation(commRightAnimal);
			}
		});
		commRightAnimal.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				secondCat.setVisibility(View.GONE);
				secondSuperise.setVisibility(View.VISIBLE);
				secondImport.setVisibility(View.VISIBLE);
				secondImport.startAnimation(chapterAnimal);
			}
		});
	}

	private void initAdapter() {
		viewPager = (ViewPager) findViewById(R.id.main_viewpager);
		adapter = new VerticalPagerAdapter(list);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);
		viewPager.setOffscreenPageLimit(0);
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
			secondSuperise.setVisibility(View.INVISIBLE);
			break;
		case 1:
			secondNext.startAnimation(commonNextAnimal);
			secondDog.startAnimation(commLeftAnimal);
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
