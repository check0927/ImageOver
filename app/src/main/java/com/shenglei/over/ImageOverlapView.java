package com.shenglei.over;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 生活-图片重叠view
 * Created by wushenglei on 2017/12/6.
 */

public class ImageOverlapView extends FrameLayout {
    private Paint paint;
    private Bitmap bmp;
    private Context context;

    public ImageOverlapView(Context context) {
        super(context);
        init(context);
    }

    public ImageOverlapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageOverlapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ImageOverlapView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        super.onLayout(b, i, i1, i2, i3);
    }

    private void init(Context context) {
        this.context = context;

    }

    public void setData(List<String> url) {
        if (url.size() > 0) {
            //设置重叠的长度
            int left = ScreenUtils.dip2px(context, (url.size() - 1) * 12);
            for (int i = 0; i < url.size(); i++) {
                if (i == 8) {
                    break;
                }
                CircleImageView imageView = new CircleImageView(context);
                imageView.setBorderColor(Color.parseColor("#cccccc"));
                imageView.setBorderWidth(ScreenUtils.dip2px(context, 0.5f));
                FrameLayout.LayoutParams layoutParams = new LayoutParams(ScreenUtils.dip2px(context, 18), ScreenUtils.dip2px(context, 18));
                layoutParams.setMargins(left, 0, 0, 0);
                Glide.with(context.getApplicationContext()).load(url.get(i)).into(imageView);
                addView(imageView, layoutParams);
                left -= ScreenUtils.dip2px(context, 12);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
