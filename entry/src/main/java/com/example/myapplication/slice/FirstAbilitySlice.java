package com.example.myapplication.slice;

import com.example.myapplication.MyApplication;
import com.example.myapplication.ResourceTable;
import com.example.myapplication.data.ListHolder;
import com.example.myapplication.data.ListItemProvider;
import com.example.myapplication.data.NewsDataAbility;
import com.example.myapplication.data.NewsDataUtil;
import com.example.myapplication.data.db.News;
import com.example.myapplication.data.db.NewsStore;
import com.example.myapplication.service.ServiceAbility;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.ListContainer;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.eventhandler.EventRunner;
import ohos.eventhandler.InnerEvent;
import ohos.utils.net.Uri;

import static com.example.myapplication.data.NewsDataAbility.DATABASE_NAME;
import static com.example.myapplication.data.NewsDataAbility.DATABASE_NAME_ALIAS;

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
        LogUtil.debugInfo("A");

        try {
            DatabaseHelper helper = new DatabaseHelper(this); // context入参类型为ohos.app.Context，注意不要使用slice.getContext()来获取context，请直接传入slice，否则会出现找不到类的报错。
            OrmContext context = helper.getOrmContext(DATABASE_NAME_ALIAS, DATABASE_NAME, NewsStore.class);
            NewsDataUtil.insertThreeNews(context, Uri.parse(NewsDataAbility.AUTHORITY));

            LogUtil.debugInfo("B");
            ListItemProvider itemProvider = (ListItemProvider) listHolder.getListContainer().getItemProvider();
            itemProvider.setNews(NewsDataUtil.queryNews(context, Uri.parse(NewsDataAbility.AUTHORITY)));

            LogUtil.debugInfo("C");
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.debugInfo(e.getMessage());
        }
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
