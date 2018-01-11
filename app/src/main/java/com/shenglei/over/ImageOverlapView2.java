package com.shenglei.over;

/**
 * Created by wushenglei on 2018/1/10.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by wushenglei on 2018/1/8.
 */

public class ImageOverlapView2 extends View {


    private int mSize;
    private List<Bitmap> mBitmaps;
    private int mBorderSize;
    private int mBorderColor;
    private int mOverPadding;
    private List<Bitmap> bitmaps;

    public ImageOverlapView2(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {

    }

    public ImageOverlapView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageOverlapView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ImageOverlapView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int height = resolveSize(500, heightMeasureSpec);
//        setMeasuredDimension(height, height);
    }

    public void setData(List<Bitmap> bitmaps, int size, int borderSize, int overPadding) {
        mBitmaps = bitmaps;
        mSize = size;
        mBorderSize = borderSize;
        mOverPadding = overPadding;
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        invalidate();

    }

    public void setSize(int size) {
        mSize = size;
        invalidate();
    }

    public void setBorderSize(int size) {
        mBorderSize = size;
        invalidate();
    }

    //暂未设置到CircleImageDrawable里去
    public void setBorderColor(int color) {
        mBorderColor = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            if (mBitmaps != null) {
                if (mSize != 0) {
                    for (int bitmapIndex = 0; bitmapIndex < mBitmaps.size(); bitmapIndex++) {
                        Bitmap bitmap = mBitmaps.get(bitmapIndex);
                        CircleImageDrawable2 circleImageDrawable = new CircleImageDrawable2(bitmap, mSize, mBorderSize);
                        circleImageDrawable.draw(canvas);
                        canvas.translate(mSize - mOverPadding, 0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
