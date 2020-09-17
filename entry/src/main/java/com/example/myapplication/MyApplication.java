package com.example.myapplication;

import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilityLifecycleCallbacks;
import ohos.aafwk.ability.AbilityPackage;
import ohos.app.ElementsCallback;


public class MyApplication extends AbilityPackage implements AbilityLifecycleCallbacks{
    @Override
    public void onInitialize() {
        super.onInitialize();
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
