package com.github.neowen.apibasedemo.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.neowen.apibasedemo.BaseFragment;
import com.github.neowen.apibasedemo.R;

public class TestDialogFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.test_dialog, container, false);
        Handler handler = new Handler();

        return root;
    }

    public void getInfo(){

    }
}
