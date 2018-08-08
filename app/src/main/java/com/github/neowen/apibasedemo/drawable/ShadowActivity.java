package com.github.neowen.apibasedemo.drawable;

import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.neowen.apibasedemo.BaseActivity;
import com.github.neowen.apibasedemo.R;

public class ShadowActivity extends BaseActivity {

    private static final String TAG = ShadowActivity.class.getSimpleName();


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shadow_layout);

        MyShadowView myShadowView = (MyShadowView) findViewById(R.id.my_shadow_view);
        ShadowDrawable sd = new ShadowDrawable(myShadowView);
        myShadowView.setBackground(sd);
        myShadowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}