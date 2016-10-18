package com.wanghaisheng.mojistartanimation;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * Author: sheng on 2016/10/17 10:18
 * Email: 1392100700@qq.com
 */

public class DensityUtil {

    private DisplayMetrics dm;
    private Context mContext;

    /**
     * 获取屏幕的宽度
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        return dm.heightPixels;
    }

    /**
     * dp转px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context,float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpValue,context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context,float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale + 0.5f*(pxValue>=0?1:-1));
    }

}
