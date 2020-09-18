package com.example.myapplication;

import com.example.myapplication.data.NewsDataAbility;
import com.example.myapplication.data.NewsDataUtil;
import com.example.myapplication.data.db.NewsStore;
import com.example.myapplication.service.ServiceAbility;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilityLifecycleCallbacks;
import ohos.aafwk.ability.AbilityPackage;
import ohos.app.Context;
import ohos.app.ElementsCallback;
import ohos.bluetooth.BluetoothDeviceClass;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.utils.net.Uri;

import static com.example.myapplication.data.NewsDataAbility.DATABASE_NAME;
import static com.example.myapplication.data.NewsDataAbility.DATABASE_NAME_ALIAS;


public class MyApplication extends AbilityPackage implements AbilityLifecycleCallbacks{

    private static OrmContext ormContext;

    public static OrmContext getOrmContext() {
        return ormContext;
    }

    @Override
    public void onInitialize() {
        super.onInitialize();
        DatabaseHelper helper = new DatabaseHelper(this); // context入参类型为ohos.app.Context，注意不要使用slice.getContext()来获取context，请直接传入slice，否则会出现找不到类的报错。
        ormContext = helper.getOrmContext(DATABASE_NAME_ALIAS, DATABASE_NAME, NewsStore.class);
        registerCallbacks(this, null);
    }

    @Override
    public void onAbilityStart(Ability ability) {
        LogUtil.lifeCycleInfo(ability.getLocalClassName(), "onStart()");
    }

    @Override
    public void onAbilityActive(Ability ability) {
        LogUtil.lifeCycleInfo(ability.getLocalClassName(), "onActive()");
    }

    @Override
    public void onAbilityInactive(Ability ability) {
        LogUtil.lifeCycleInfo(ability.getLocalClassName(), "onInactive()");
    }

    @Override
    public void onAbilityForeground(Ability ability) {
        LogUtil.lifeCycleInfo(ability.getLocalClassName(), "onForeground()");
    }

    @Override
    public void onAbilityBackground(Ability ability) {
        LogUtil.lifeCycleInfo(ability.getLocalClassName(), "onBackground()");
    }

    @Override
    public void onAbilityStop(Ability ability) {
        LogUtil.lifeCycleInfo(ability.getLocalClassName(), "onStop()");
    }
}
