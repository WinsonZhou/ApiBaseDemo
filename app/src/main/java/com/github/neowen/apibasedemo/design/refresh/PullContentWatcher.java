package com.github.neowen.apibasedemo.design.refresh;

import android.view.View;

public interface PullContentWatcher {

    View getStick();

    boolean onTop();

    void setOnDrag(boolean onDrag);

}
