package com.wanghaisheng.mojistartanimation;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import static com.wanghaisheng.mojistartanimation.R.id.iv3_icon2;

public class MainActivity extends AppCompatActivity {

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
    private Animation mTopAnimation;
    //底部next图标的动画
    private Animation mBottomNextAnimation;


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

    /**
     * 为指定的View设置动画
     * @param position
     */
    private void animal(int position) {

        switch (position) {
            case 0:
                if(preIndex > position) {
                    iv_2_icon1.setVisibility(View.INVISIBLE);
                }

                //时钟跳动帧动画
                iv_1_icon1.setImageResource(R.drawable.t1_frame_animation);
                t1_icon1_animationDrawable = (AnimationDrawable) iv_1_icon1.getDrawable();
                t1_icon1_animationDrawable.start();

                //钟表盘中间的电池转圈效果
                Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.tutorial_rotate);
                LinearInterpolator linearInterpolator = new LinearInterpolator();
                animation.setInterpolator(linearInterpolator);
                iv_1_icon2.startAnimation(animation);

                mTopAnimation = AnimationUtils.loadAnimation(mContext,R.anim.tutorial_top_scale);
                mBottomNextAnimation = AnimationUtils.loadAnimation(mContext,R.anim.tutorial_bottom);
                iv_1_fixed.startAnimation(mTopAnimation);
                iv_1_next.startAnimation(mBottomNextAnimation);
                break;
            case 1:
                //需要把上一页的动画取消，不然会崩溃
                if(preIndex < position) {
                    t1_icon1_animationDrawable.stop();
                    iv_1_icon2.getAnimation().cancel();
                    iv_1_icon2.setVisibility(View.INVISIBLE);
                } else {
                    iv_3_icon1.getAnimation().cancel();
                    iv_3_icon2.getAnimation().cancel();
                    iv_3_icon3.getAnimation().cancel();
                    iv_3_icon4.getAnimation().cancel();

                    iv_3_icon1.setVisibility(View.INVISIBLE);
                    iv_3_icon2.setVisibility(View.INVISIBLE);
                    iv_3_icon3.setVisibility(View.INVISIBLE);
                    iv_3_icon4.setVisibility(View.INVISIBLE);
                    t3_icon5_animationDrawable.stop();
                }

                Animation animation1 = AnimationUtils.loadAnimation(mContext,R.anim.tutorial_scale);
                iv_2_icon1.setVisibility(View.VISIBLE);
                iv_2_icon1.startAnimation(animation1);

                iv_2_fixed.startAnimation(mTopAnimation);
                iv_2_next.startAnimation(mBottomNextAnimation);
                break;
            case 2:
                if(preIndex>position && iv_4_icon1.getAnimation()!=null) {
                    iv_4_icon1.getAnimation().cancel();
                }

                iv_3_icon5.setImageResource(R.drawable.t3_frame_animation);
                t3_icon5_animationDrawable = (AnimationDrawable) iv_3_icon5.getDrawable();

                final TranslateAnimation translateAnimation1 = new TranslateAnimation(fx1,fy1,tx1,ty1);
                translateAnimation1.setDuration(800);
                translateAnimation1.setRepeatCount(Animation.INFINITE);
                translateAnimation1.setRepeatMode(Animation.RESTART);
                linearInterpolator = new LinearInterpolator();
                translateAnimation1.setInterpolator(linearInterpolator);

                final TranslateAnimation translateAnimation2 = new TranslateAnimation(fx2,fy2,tx2,ty2);
                translateAnimation2.setDuration(1200);
                translateAnimation2.setRepeatCount(Animation.INFINITE);
                translateAnimation2.setRepeatMode(Animation.RESTART);
                translateAnimation2.setInterpolator(linearInterpolator);

                final TranslateAnimation translateAnimation3 = new TranslateAnimation(fx3,fy3,tx3,ty3);
                translateAnimation3.setDuration(800);
                translateAnimation3.setRepeatCount(Animation.INFINITE);
                translateAnimation3.setRepeatMode(Animation.RESTART);
                translateAnimation3.setInterpolator(linearInterpolator);

                final TranslateAnimation translateAnimation4 = new TranslateAnimation(fx4,fy4,tx4,ty4);
                translateAnimation4.setDuration(1200);
                translateAnimation4.setRepeatCount(Animation.INFINITE);
                translateAnimation4.setRepeatMode(Animation.RESTART);
                translateAnimation4.setInterpolator(linearInterpolator);

                flag3 = true;

                new Handler(){
                    @Override
                    public void dispatchMessage(Message msg) {
                        if(flag3)
                            super.dispatchMessage(msg);
                    }

                    @Override
                    public void handleMessage(Message msg) {
                        if(msg.what == 1) {
                            iv_3_icon1.setVisibility(View.VISIBLE);
                            iv_3_icon2.setVisibility(View.VISIBLE);
                            iv_3_icon3.setVisibility(View.VISIBLE);
                            iv_3_icon4.setVisibility(View.VISIBLE);

                            iv_3_icon1.startAnimation(translateAnimation1);
                            iv_3_icon2.startAnimation(translateAnimation2);
                            iv_3_icon3.startAnimation(translateAnimation3);
                            iv_3_icon4.startAnimation(translateAnimation4);

                            t3_icon5_animationDrawable.start();
                        }
                    }
                }.sendEmptyMessageDelayed(1,1000);

                iv_3_fixed.startAnimation(mTopAnimation);
                iv_3_next.startAnimation(mBottomNextAnimation);
                break;
            case 3:
                flag3 = false;
                if(iv_3_icon1.getAnimation() != null) {
                    iv_3_icon1.getAnimation().cancel();
                    iv_3_icon2.getAnimation().cancel();
                    iv_3_icon3.getAnimation().cancel();
                    iv_3_icon4.getAnimation().cancel();
                }

                iv_3_icon1.setVisibility(View.INVISIBLE);
                iv_3_icon2.setVisibility(View.INVISIBLE);
                iv_3_icon3.setVisibility(View.INVISIBLE);
                iv_3_icon4.setVisibility(View.INVISIBLE);
                if(t3_icon5_animationDrawable != null) {
                    t3_icon5_animationDrawable.stop();
                }

                int pivot = Animation.RELATIVE_TO_SELF;
                RotateAnimation rotateAnimation = new RotateAnimation(0, 10, pivot,
                        0.47f, pivot, 0.05f);
                //执行前的等待时间
//                rotateAnimation.setStartOffset(500);
                rotateAnimation.setRepeatMode(Animation.RESTART);
                //设置重复次数
                rotateAnimation.setRepeatCount(Animation.INFINITE);
                rotateAnimation.setDuration(3000);
                CycleInterpolator cycleInterpolator = new CycleInterpolator(3.0f);
                rotateAnimation.setInterpolator(cycleInterpolator);
                iv_4_icon1.startAnimation(rotateAnimation);

                iv_4_fixed.startAnimation(mTopAnimation);
                break;

            default:
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
