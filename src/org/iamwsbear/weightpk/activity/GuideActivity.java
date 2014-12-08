package org.iamwsbear.weightpk.activity;

import java.util.ArrayList;
import java.util.List;

import org.iamwsbear.weightpk.R;
import org.iamwsbear.weightpk.adapter.VerticalPagerAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ryanharter.viewpager.ViewPager;
import com.ryanharter.viewpager.ViewPager.OnPageChangeListener;

public class GuideActivity extends Activity implements OnPageChangeListener,
        OnClickListener {

    private ViewPager viewPager;

    private VerticalPagerAdapter adapter;

    private List<View> list = new ArrayList<View>();

    private View finalGuideView;

    private Animation clockHourAnimal, clockMinuteAnimal, commonNextAnimal,
            chapterDownAnimal, chapterUpAnimal,
            commAlphaAnimal, vibratorAnimal, weixinAnimal;

    private ImageView clockHourPoint, clockMinutePoint, firstNext, fristText,
            secondImport, secondNext, secondSuperise, secondChapter,
            thirdWeixinImg, thirdNext, finalRocket, finalLeft1, finalLeft2,
            finalRight1, finalRight2;

    private LinearLayout secondMainLayout;

    private int fx1, fy1, tx1, ty1;
    private int fx2, fy2, tx2, ty2;
    private int fx3, fy3, tx3, ty3;
    private int fx4, fy4, tx4, ty4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initView();
        initAnimal();
        initAdapter();
        initDo();
        initCount();
    }

    @SuppressLint({ "InflateParams", "HandlerLeak" })
    private void initView() {
        View firstGuideView = LayoutInflater.from(this).inflate(
                R.layout.guidepage_first_layout, null);
        clockHourPoint = (ImageView) firstGuideView
                .findViewById(R.id.guidepage_first_hour_point);
        clockMinutePoint = (ImageView) firstGuideView
                .findViewById(R.id.guidepage_first_minute_point);
        firstNext = (ImageView) firstGuideView
                .findViewById(R.id.guidepage_first_next);
        fristText = (ImageView) firstGuideView
                .findViewById(R.id.guidepage_first_text);
        View secondGuideView = LayoutInflater.from(this).inflate(
                R.layout.guidepage_second_layout, null);
        secondImport = (ImageView) secondGuideView
                .findViewById(R.id.guidepage_second_coming);
        secondNext = (ImageView) secondGuideView
                .findViewById(R.id.guidepage_second_next);
        secondSuperise = (ImageView) secondGuideView
                .findViewById(R.id.guidepage_second_suprise);
        secondChapter = (ImageView) secondGuideView
                .findViewById(R.id.guidepage_second_chapter);
        secondMainLayout = (LinearLayout) secondGuideView
                .findViewById(R.id.guidepage_second_main);
        View thirdGuideView = LayoutInflater.from(this).inflate(
                R.layout.guidepage_third_layout, null);
        thirdWeixinImg = (ImageView) thirdGuideView
                .findViewById(R.id.guidepage_third_weixin);
        thirdNext = (ImageView) thirdGuideView
                .findViewById(R.id.guidepage_third_next);
        finalGuideView = LayoutInflater.from(this).inflate(
                R.layout.guidepage_final_layout, null);
        finalRocket = (ImageView) finalGuideView
                .findViewById(R.id.guidepage_final_rocket);
        finalLeft1 = (ImageView) finalGuideView
                .findViewById(R.id.guidepage_final_cloud_left1);
        finalLeft2 = (ImageView) finalGuideView
                .findViewById(R.id.guidepage_final_cloud_left2);
        finalRight1 = (ImageView) finalGuideView
                .findViewById(R.id.guidepage_final_cloud_right1);
        finalRight2 = (ImageView) finalGuideView
                .findViewById(R.id.guidepage_final_cloud_right2);
        list.add(firstGuideView);
        list.add(secondGuideView);
        list.add(thirdGuideView);
        list.add(finalGuideView);
    }

    private void initAnimal() {
        clockHourAnimal = AnimationUtils.loadAnimation(GuideActivity.this,
                R.anim.animal_circle_hour_scale);
        clockMinuteAnimal = AnimationUtils.loadAnimation(GuideActivity.this,
                R.anim.animal_circle_minute_scale);
        commonNextAnimal = AnimationUtils.loadAnimation(GuideActivity.this,
                R.anim.animal_bottom_next);
        chapterDownAnimal = AnimationUtils.loadAnimation(GuideActivity.this,
                R.anim.animal_chapter_down);
        chapterUpAnimal = AnimationUtils.loadAnimation(GuideActivity.this,
                R.anim.animal_chapter_up);
        commAlphaAnimal = AnimationUtils.loadAnimation(GuideActivity.this,
                R.anim.animal_comm_alpha);
        vibratorAnimal = AnimationUtils.loadAnimation(GuideActivity.this,
                R.anim.animal_vibrator);
        weixinAnimal = AnimationUtils.loadAnimation(GuideActivity.this,
                R.anim.animal_weixin_run);

        chapterDownAnimal.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                secondImport.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                secondImport.startAnimation(chapterUpAnimal);
                secondMainLayout.startAnimation(vibratorAnimal);
                secondSuperise.startAnimation(commAlphaAnimal);
            }
        });
        chapterUpAnimal.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                secondChapter.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                secondSuperise.setVisibility(View.VISIBLE);
            }
        });
        weixinAnimal.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                thirdWeixinImg.startAnimation(weixinAnimal);
            }
        });
    }

    private void initAdapter() {
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        adapter = new VerticalPagerAdapter(list);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
    }

    private void initDo() {
        startAniaml(0);
    }

    private void initCount() {
        finalGuideView.getViewTreeObserver().addOnGlobalLayoutListener(
                new OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        RelativeLayout centerLayout = (RelativeLayout) finalGuideView
                                .findViewById(R.id.guidepage_final_relativelayout);
                        int top = centerLayout.getTop();
                        int bottom = centerLayout.getBottom();
                        DisplayMetrics dm = new DisplayMetrics();
                        dm = GuideActivity.this.getApplicationContext()
                                .getResources().getDisplayMetrics();
                        int w = dm.widthPixels;
                        fx1 = finalLeft1.getTop() + finalLeft1.getHeight();
                        fy1 = -finalLeft1.getTop() - finalLeft1.getHeight();
                        tx1 = -finalLeft1.getWidth() - finalLeft1.getLeft();
                        ty1 = finalLeft1.getTop() + finalLeft1.getLeft()
                                + finalLeft1.getWidth();
                        fx2 = finalLeft2.getTop() + finalLeft2.getHeight();
                        fy2 = -finalLeft2.getTop() - finalLeft2.getHeight();
                        tx2 = -finalLeft2.getWidth() - finalLeft2.getLeft();
                        ty2 = finalLeft2.getTop() + finalLeft2.getLeft()
                                + finalLeft2.getWidth();
                        fx3 = w - finalRight1.getLeft();
                        fy3 = -(w - finalRight1.getLeft());
                        tx3 = -(bottom - top - finalRight1.getTop());
                        ty3 = bottom - top - finalRight1.getTop();
                        fx4 = w - finalRight2.getLeft();
                        fy4 = -(w - finalRight2.getLeft());
                        tx4 = -(bottom - top - finalRight2.getTop());
                        ty4 = bottom - top - finalRight2.getTop();
                    }
                });
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
            break;
        case 1:
            secondSuperise.setVisibility(View.INVISIBLE);
            secondChapter.setVisibility(View.INVISIBLE);
            secondSuperise.clearAnimation();
            secondNext.startAnimation(commonNextAnimal);
            secondImport.startAnimation(chapterDownAnimal);
            break;
        case 2:
            thirdWeixinImg.setImageResource(R.drawable.animallist_weixin);
            ((AnimationDrawable) thirdWeixinImg.getDrawable()).start();
            thirdWeixinImg.startAnimation(weixinAnimal);
            thirdNext.startAnimation(commonNextAnimal);
            break;
        case 3:
            LinearInterpolator lin = new LinearInterpolator();
            finalRocket.setImageResource(R.drawable.animallist_rocket_fly);
            ((AnimationDrawable) finalRocket.getDrawable()).start();
            final TranslateAnimation transAnimation2 = new TranslateAnimation(
                    fx1, tx1, fy1, ty1);
            transAnimation2.setDuration(800);
            transAnimation2.setRepeatCount(Animation.INFINITE);
            transAnimation2.setRepeatMode(Animation.RESTART);
            transAnimation2.setInterpolator(lin);

            final TranslateAnimation transAnimation3 = new TranslateAnimation(
                    fx2, tx2, fy2, ty2);
            transAnimation3.setDuration(1200);
            transAnimation3.setRepeatCount(Animation.INFINITE);
            transAnimation3.setRepeatMode(Animation.RESTART);
            transAnimation3.setInterpolator(lin);

            final TranslateAnimation transAnimation4 = new TranslateAnimation(
                    fx3, tx3, fy3, ty3);
            transAnimation4.setDuration(1200);
            transAnimation4.setRepeatCount(Animation.INFINITE);
            transAnimation4.setRepeatMode(Animation.RESTART);
            transAnimation4.setInterpolator(lin);

            final TranslateAnimation transAnimation5 = new TranslateAnimation(
                    fx4, tx4, fy4, ty4);
            transAnimation5.setDuration(800);
            transAnimation5.setRepeatCount(Animation.INFINITE);
            transAnimation5.setRepeatMode(Animation.RESTART);
            transAnimation5.setInterpolator(lin);

            finalLeft1.startAnimation(transAnimation2);
            finalLeft2.startAnimation(transAnimation3);
            finalRight1.startAnimation(transAnimation4);
            finalRight2.startAnimation(transAnimation5);
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
