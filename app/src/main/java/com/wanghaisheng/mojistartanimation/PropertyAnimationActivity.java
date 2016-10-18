package com.wanghaisheng.mojistartanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import static com.wanghaisheng.mojistartanimation.R.id.iv3_icon2;

/**
 * Author: sheng on 2016/10/17 20:45
 * Email: 1392100700@qq.com
 */

public class PropertyAnimationActivity extends AppCompatActivity {

    private Context mContext;

    //垂直的ViewPager
    private ViewPager mViewPager;
    private VerticalFragementPagerAdapter mPagerAdapter;

    //ViewPager中的View集合
    private List<View> mPagers = new ArrayList<>();

    private ImageView iv_1_fixed,iv_1_icon1,iv_1_icon2,iv_1_next;

    private ImageView iv_2_fixed,iv_2_icon1,iv_2_next;

    private ImageView iv_3_fixed,iv_3_icon1,iv_3_icon2,iv_3_icon3,iv_3_icon4,iv_3_icon5
            ,iv_3_next;
    //四个云朵的动画
    private int fx1, fy1, tx1, ty1;
    private int fx2, fy2, tx2, ty2;
    private int fx3, fy3, tx3, ty3;
    private int fx4, fy4, tx4, ty4;

    private ImageView iv_4_icon1,iv_4_fixed;

    private RelativeLayout mCenterLayout;

    //时钟跳动动画
    private AnimationDrawable t1_icon1_animationDrawable;
    //飞机动画
    private AnimationDrawable t3_icon5_animationDrawable;

    //顶部文字动画
    private AnimatorSet mTopAnimation;
    //底部next图标的动画
    private AnimatorSet mBottomNextAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        initView();

        initData();

        animal(0);
    }

    /**
     * 初始化View
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        LayoutInflater inflater = LayoutInflater.from(this);

        //第一个View
        View view1 = inflater.inflate(R.layout.layout_turorial_1,null);
        iv_1_fixed = (ImageView) view1.findViewById(R.id.iv1_fixed);
        iv_1_icon1 = (ImageView) view1.findViewById(R.id.iv1_icon1);
        iv_1_icon2 = (ImageView) view1.findViewById(R.id.iv1_icon2);
        iv_1_next = (ImageView)  view1.findViewById(R.id.iv1_next);
        mPagers.add(view1);

        //第二个View
        View view2 = inflater.inflate(R.layout.layout_turorial_2,null);
        iv_2_fixed = (ImageView) view2.findViewById(R.id.iv2_fixed);
        iv_2_icon1 = (ImageView) view2.findViewById(R.id.iv2_icon1);
        iv_2_next = (ImageView)  view2.findViewById(R.id.iv2_next);
        mPagers.add(view2);

        //第三个View
        View view3 = inflater.inflate(R.layout.layout_turorial_3,null);
        iv_3_fixed = (ImageView) view3.findViewById(R.id.iv3_fixed);
        iv_3_icon1 = (ImageView) view3.findViewById(R.id.iv3_icon1);
        iv_3_icon2 = (ImageView) view3.findViewById(iv3_icon2);
        iv_3_icon3 = (ImageView) view3.findViewById(R.id.iv3_icon3);
        iv_3_icon4 = (ImageView) view3.findViewById(R.id.iv3_icon4);
        iv_3_icon5 = (ImageView) view3.findViewById(R.id.iv3_icon5);
        iv_3_next = (ImageView)  view3.findViewById(R.id.iv3_next);
        mCenterLayout = (RelativeLayout) view3.findViewById(R.id.center_layout);
        mPagers.add(view3);
        view3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int h1 = mCenterLayout.getTop();
                int h2 = mCenterLayout.getBottom();

                int w = DensityUtil.getScreenWidth(getApplicationContext());

                fx1 = iv_3_icon1.getTop() + iv_3_icon1.getHeight();
                fy1 = -iv_3_icon1.getTop() - iv_3_icon1.getHeight();
                tx1 = -iv_3_icon1.getWidth() - iv_3_icon1.getLeft();
                ty1 = iv_3_icon1.getTop() + iv_3_icon1.getLeft()
                        + iv_3_icon1.getWidth();

                fx2 = iv_3_icon2.getTop() + iv_3_icon2.getHeight();
                fy2 = -iv_3_icon2.getTop() - iv_3_icon2.getHeight();
                tx2 = -iv_3_icon2.getWidth() - iv_3_icon2.getLeft();
                ty2 = iv_3_icon2.getTop() + iv_3_icon2.getLeft()
                        + iv_3_icon2.getWidth();

                fx3 = w - iv_3_icon3.getLeft();
                fy3 = -(w - iv_3_icon3.getLeft());
                tx3 = -(h2 - h1 - iv_3_icon3.getTop());
                ty3 = h2 - h1 - iv_3_icon3.getTop();

                fx4 = w - iv_3_icon4.getLeft();
                fy4 = -(w - iv_3_icon4.getLeft());
                tx4 = -(h2 - h1 - iv_3_icon4.getTop());
                ty4 = h2 - h1 - iv_3_icon4.getTop();
            }
        });

        //第四个View
        View view4 = inflater.inflate(R.layout.layout_turorial_4,null);
        iv_4_fixed = (ImageView) view4.findViewById(R.id.iv4_fixed);
        iv_4_icon1 = (ImageView) view4.findViewById(R.id.iv4_icon1);
        mPagers.add(view4);
    }

    private void initData() {
        mPagerAdapter = new VerticalFragementPagerAdapter();
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                animal(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //上一页的index，用于取消上一页的动画
    private int preIndex;
    private boolean flag3 = false;

    private AnimatorSet animation1;
    private AnimatorSet animation2;

    private ObjectAnimator transAnimator1;
    private ObjectAnimator transAnimator2;
    private ObjectAnimator transAnimator3;
    private ObjectAnimator transAnimator4;

    private ObjectAnimator topAnimator;

    private void animal(int position) {
        switch (position) {
            case 0:
                if(preIndex>position) {
                    iv_2_icon1.setVisibility(View.INVISIBLE);
                }

                iv_1_icon1.setImageResource(R.drawable.t1_frame_animation);
                t1_icon1_animationDrawable = (AnimationDrawable) iv_1_icon1.getDrawable();
                t1_icon1_animationDrawable.start();

                animation1 = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,R.animator.turorial_rotate);
                animation1.setInterpolator(new LinearInterpolator());

                iv_1_icon2.setVisibility(View.VISIBLE);
                animation1.setTarget(iv_1_icon2);
                animation1.start();

                mTopAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,R.animator.tutorial_top_scale);
                mTopAnimation.setTarget(iv_1_fixed);
                mTopAnimation.start();

                mBottomNextAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,R.animator.tutorial_bottom);
                mBottomNextAnimation.setTarget(iv_1_next);
                mBottomNextAnimation.start();

                break;
            case 1:
                //停止以前的动画
                if(preIndex < position) {
                    //停止前一页的动画
                    t1_icon1_animationDrawable.stop();
                    animation1.cancel();
                    iv_1_icon2.setVisibility(View.INVISIBLE);
                } else {
                    flag3 = false;
                    if(transAnimator1!=null && transAnimator1.isRunning()) {
                        transAnimator1.cancel();
                        transAnimator2.cancel();
                        transAnimator3.cancel();
                        transAnimator4.cancel();

                        iv_3_icon1.setVisibility(View.INVISIBLE);
                        iv_3_icon2.setVisibility(View.INVISIBLE);
                        iv_3_icon3.setVisibility(View.INVISIBLE);
                        iv_3_icon4.setVisibility(View.INVISIBLE);

                        t3_icon5_animationDrawable.stop();
                    }
                }

                //缩放动画
                animation2 = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,R.animator.tutorial_scale);
                iv_2_icon1.setVisibility(View.VISIBLE);
                animation2.setTarget(iv_2_icon1);
                animation2.start();

                mTopAnimation.setTarget(iv_2_fixed);
                mTopAnimation.start();

                mBottomNextAnimation.setTarget(iv_2_next);
                mBottomNextAnimation.start();

                break;
            case 2:
                if(preIndex > position && topAnimator!=null && topAnimator.isRunning()) {
                    topAnimator.cancel();
                }

                iv_3_icon5.setImageResource(R.drawable.t3_frame_animation);
                t3_icon5_animationDrawable = (AnimationDrawable) iv_3_icon5.getDrawable();

                PropertyValuesHolder pvhX1 = PropertyValuesHolder.ofFloat("translationX",fx1,tx1);
                PropertyValuesHolder pvhY1 = PropertyValuesHolder.ofFloat("translationY",fy1,ty1);
                transAnimator1 = ObjectAnimator.ofPropertyValuesHolder(iv_3_icon1,pvhX1,pvhY1);
                transAnimator1.setDuration(800);
                transAnimator1.setRepeatMode(ValueAnimator.RESTART);
                transAnimator1.setRepeatCount(ValueAnimator.INFINITE);
                transAnimator1.setInterpolator(new LinearInterpolator());

                PropertyValuesHolder pvhX2 = PropertyValuesHolder.ofFloat("translationX",fx2,tx2);
                PropertyValuesHolder pvhY2 = PropertyValuesHolder.ofFloat("translationY",fy2,ty2);
                transAnimator2 = ObjectAnimator.ofPropertyValuesHolder(iv_3_icon2,pvhX2,pvhY2);
                transAnimator2.setDuration(1200);
                transAnimator2.setRepeatMode(ValueAnimator.RESTART);
                transAnimator2.setRepeatCount(ValueAnimator.INFINITE);
                transAnimator2.setInterpolator(new LinearInterpolator());

                PropertyValuesHolder pvhX3 = PropertyValuesHolder.ofFloat("translationX",fx3,tx3);
                PropertyValuesHolder pvhY3 = PropertyValuesHolder.ofFloat("translationY",fy3,ty3);
                transAnimator3 = ObjectAnimator.ofPropertyValuesHolder(iv_3_icon3,pvhX3,pvhY3);
                transAnimator3.setDuration(1200);
                transAnimator3.setRepeatMode(ValueAnimator.RESTART);
                transAnimator3.setRepeatCount(ValueAnimator.INFINITE);
                transAnimator3.setInterpolator(new LinearInterpolator());

                PropertyValuesHolder pvhX4 = PropertyValuesHolder.ofFloat("translationX",fx4,tx4);
                PropertyValuesHolder pvhY4 = PropertyValuesHolder.ofFloat("translationY",fy4,ty4);
                transAnimator4 = ObjectAnimator.ofPropertyValuesHolder(iv_3_icon4,pvhX4,pvhY4);
                transAnimator4.setDuration(800);
                transAnimator4.setRepeatMode(ValueAnimator.RESTART);
                transAnimator4.setRepeatCount(ValueAnimator.INFINITE);
                transAnimator4.setInterpolator(new LinearInterpolator());

                flag3 = true;

                new Handler(){
                    @Override
                    public void dispatchMessage(Message msg) {
                        if(flag3) {
                            super.dispatchMessage(msg);
                        }
                    }

                    @Override
                    public void handleMessage(Message msg) {
                        iv_3_icon1.setVisibility(View.VISIBLE);
                        iv_3_icon2.setVisibility(View.VISIBLE);
                        iv_3_icon3.setVisibility(View.VISIBLE);
                        iv_3_icon4.setVisibility(View.VISIBLE);

                        transAnimator1.start();
                        transAnimator2.start();
                        transAnimator3.start();
                        transAnimator4.start();

                        t3_icon5_animationDrawable.start();
                    }
                }.sendEmptyMessageDelayed(1,1000);

                mTopAnimation.setTarget(iv_3_fixed);
                mTopAnimation.start();

                mBottomNextAnimation.setTarget(iv_3_next);
                mBottomNextAnimation.start();

                break;
            case 3:
                //停止上一页的动画
                if(transAnimator1.isRunning()) {
                    transAnimator1.cancel();
                    transAnimator2.cancel();
                    transAnimator3.cancel();
                    transAnimator4.cancel();
                }

                iv_3_icon1.setVisibility(View.INVISIBLE);
                iv_3_icon2.setVisibility(View.INVISIBLE);
                iv_3_icon3.setVisibility(View.INVISIBLE);
                iv_3_icon4.setVisibility(View.INVISIBLE);

                t1_icon1_animationDrawable.stop();

                topAnimator = ObjectAnimator.ofFloat(iv_4_icon1,"rotation",0f,10f);
                topAnimator.setDuration(3000);
                topAnimator.setStartDelay(500);
                topAnimator.setRepeatCount(ValueAnimator.INFINITE);
                CycleInterpolator cycleInterpolator = new CycleInterpolator(3.0f);
                topAnimator.setInterpolator(cycleInterpolator);
                iv_4_icon1.setPivotX(iv_4_icon1.getWidth()*0.47f);
                iv_4_icon1.setPivotY(iv_4_icon1.getHeight()*0.05f);

                topAnimator.setTarget(iv_4_icon1);
                topAnimator.start();

                break;
        }

        preIndex = position;
    }

    private class VerticalFragementPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(mPagers.get(position));
            return mPagers.get(position);

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

}
