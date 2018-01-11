package com.shenglei.over;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by wushenglei on 2018/1/10.
 */

public class CircleImageDrawable2 extends Drawable {

    private static Paint mPaint;
    private Bitmap mBitmap;
    private Paint mBorderPaint;
    private int mBorderSize;
    private int mBitmapSize;

    public CircleImageDrawable2(Bitmap bitmap, int size, int bo) {
        mBitmap = bitmap;
        this.mBitmapSize = size;
        mBorderSize = bo;
        init();
    }

    public void init() {
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        // 设置想要的大小
        // 计算缩放比例
        if (mBitmapSize == 0) {
            mBitmapSize = width;
        }
        float scaleWidth = ((float) mBitmapSize) / width;
        float scaleHeight = ((float) mBitmapSize) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        if (mBitmap != null && !mBitmap.isRecycled()) {
            Bitmap newbm = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix,
                    true);
            BitmapShader bitmapShader = new BitmapShader(newbm, Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP);
            if (mPaint == null) {
                mPaint = new Paint();
                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
                mPaint.setAntiAlias(true);
            }
            mPaint.setShader(bitmapShader);
            if (mBorderPaint == null) {
                mBorderPaint = new Paint();
                mBorderPaint.setAntiAlias(true);
                mBorderPaint.setStyle(Paint.Style.STROKE);
                mBorderPaint.setStrokeWidth(mBorderSize);
                mBorderPaint.setColor(Color.parseColor("#cccccc"));
            }
        }

    }

    public void setBorderColor(int color) {
        mBorderPaint.setColor(color);
        invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {
        if (mPaint != null) {
            canvas.drawCircle((mBitmapSize) / 2, (mBitmapSize) / 2, (mBitmapSize - mBorderSize) / 2, mPaint);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
            if (mBorderSize != 0) {
                mBorderPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
                canvas.drawCircle(mBitmapSize / 2, mBitmapSize / 2, (mBitmapSize) / 2, mBorderPaint);
            }
        }
    }

    @Override
    public int getIntrinsicWidth() {
        return mBitmapSize;
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmapSize;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }


}