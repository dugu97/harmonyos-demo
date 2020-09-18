package com.example.myapplication.slice;

import com.example.myapplication.MyApplication;
import com.example.myapplication.data.ListHolder;
import com.example.myapplication.data.ListItemProvider;
import com.example.myapplication.data.NewsDataAbility;
import com.example.myapplication.data.NewsDataUtil;
import com.example.myapplication.service.ServiceAbility;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.utils.net.Uri;

public class FirstAbilitySlice extends AbilitySlice {

    private final String TAG = "FirstAbilitySlice";

    private ListHolder listHolder;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        LogUtil.lifeCycleInfo(TAG, "onStart()");
        listHolder = new ListHolder(this);
        setUIContent(listHolder.createComponent());

        startAbility(ServiceAbility.getServiceAbilityIntentInLocal());

        NewsDataUtil.insertNews(MyApplication.getOrmContext(), Uri.parse(NewsDataAbility.AUTHORITY));

        ListItemProvider itemProvider = (ListItemProvider) listHolder.getListContainer().getItemProvider();
        itemProvider.setNews(NewsDataUtil.queryNews(MyApplication.getOrmContext(), Uri.parse(NewsDataAbility.AUTHORITY)));
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
