package com.shenglei.over;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 *获取屏幕信息以及转换dp,px的工具类
 * @author WuShenglei
 * @date 2017/11/02
 */

public class ScreenUtils {
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    public static int sp2px(Context context, float spValue) {
                float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
                return (int) (spValue * fontScale + 0.5f);
             }
    /**
     * 根据手机的分辨率从 dp 单位 转成为 px(像素)
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
    /**
     * 获取状态栏/通知栏的高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }
    /**
     * 获取屏幕的宽高
     */
    private void measure(Context context) {
        Point point = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //不含虚拟按键
        windowManager.getDefaultDisplay().getSize(point);
        //包含虚拟按键
        //windowManager.getDefaultDisplay().getRealSize(point);
        //屏幕宽度
        int height = point.y;
        //屏幕高度
        int width = point.x;
    }
    public static int getScreenWidth(Context context){
        Point point = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //不含虚拟按键
        windowManager.getDefaultDisplay().getSize(point);
        return point.x;
    }
    public static int getScreenHeight(Context context){
        Point point = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //不含虚拟按键
        windowManager.getDefaultDisplay().getSize(point);
        return point.y;
    }
}
