package com.shenglei.over;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private String[] urls = {"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1018364764,1223529536&fm=27&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1844620239,2457201616&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3746075707,1914896074&fm=27&gp=0.jpg",
            "https://pic1.zhimg.com/50/v2-071ce950b93fed2d79ca5b7efeab1bf5_hd.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        final ImageOverlapView2 imageOverlapView = findViewById(R.id.ImageOverlapView);
//        imageOverlapView.setData(Arrays.asList(urls));
        final List<Bitmap> bitmaps = new ArrayList<>();
        for (final String url : urls) {
            Glide.with(this).load(url).asBitmap().
                    into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            bitmaps.add(resource);
                            if (bitmaps.size() == urls.length) {
                                imageOverlapView.setData(bitmaps, ScreenUtils.dip2px(MainActivity.this, 40),
                                        ScreenUtils.dip2px(MainActivity.this, 1.5f),
                                        ScreenUtils.dip2px(MainActivity.this, 6.5f)
                                );
                            }
                        }
                    });
        }
    }

}


