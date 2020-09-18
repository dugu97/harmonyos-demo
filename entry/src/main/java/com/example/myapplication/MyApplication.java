package com.example.myapplication;

import com.example.myapplication.data.NewsDataAbility;
import com.example.myapplication.data.NewsDataUtil;
import com.example.myapplication.service.ServiceAbility;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilityLifecycleCallbacks;
import ohos.aafwk.ability.AbilityPackage;
import ohos.app.Context;
import ohos.app.ElementsCallback;
import ohos.bluetooth.BluetoothDeviceClass;
import ohos.utils.net.Uri;


public class MyApplication extends AbilityPackage implements AbilityLifecycleCallbacks{

    private static MyApplication application;

    public static MyApplication getApplication() {
        return application;
    }

    @Override
    public void onInitialize() {
        super.onInitialize();
        application = this;
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
