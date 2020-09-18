package com.example.myapplication.service;

import com.example.myapplication.data.NewsDataAbility;
import com.example.myapplication.data.NewsDataUtil;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.rpc.IRemoteObject;
import ohos.utils.net.Uri;

public class ServiceAbility extends Ability {

    public static final String TAG = "ServiceAbility";

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        LogUtil.serviceInfo(TAG, "onStart()");
    }

    @Override
    protected void onCommand(Intent intent, boolean restart, int startId) {
        super.onCommand(intent, restart, startId);
        LogUtil.serviceInfo(TAG, "onCommand()");
    }

//    region 连接Service
    @Override
    protected IRemoteObject onConnect(Intent intent) {
        LogUtil.serviceInfo(TAG, "onConnect()");
        return super.onConnect(intent);
    }

    @Override
    protected void onDisconnect(Intent intent) {
        super.onDisconnect(intent);
        LogUtil.serviceInfo(TAG, "onDisconnect()");
    }
//    end region

    @Override
    protected void onBackground() {
        super.onBackground();
        LogUtil.serviceInfo(TAG, "onBackground()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.serviceInfo(TAG, "onStop()");
    }

    public static Intent getServiceAbilityIntentInLocal(){
        Intent intent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")
                .withBundleName("com.example.myapplication")
                .withAbilityName("com.example.myapplication.service.ServiceAbility")
                .build();
        intent.setOperation(operation);
        return intent;
    }
}
