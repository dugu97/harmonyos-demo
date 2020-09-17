package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import com.example.myapplication.data.ListHolder;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;

public class FirstAbilitySlice extends AbilitySlice {

    private final String TAG = "FirstAbilitySlice";

    private ListHolder listHolder;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        LogUtil.lifeCycleInfo(TAG, "onStart()");
        listHolder = new ListHolder(this);
        setUIContent(listHolder.createComponent());
    }

    @Override
    protected void onActive() {
        super.onActive();
        LogUtil.lifeCycleInfo(TAG, "onActive()");
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        LogUtil.lifeCycleInfo(TAG, "onInactive()");
    }

    @Override
    protected void onBackground() {
        super.onBackground();
        LogUtil.lifeCycleInfo(TAG, "onBackground()");
    }

    @Override
    protected void onForeground(Intent intent) {
        super.onForeground(intent);
        LogUtil.lifeCycleInfo(TAG, "onForeground()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.lifeCycleInfo(TAG, "onStop()");
    }
}
