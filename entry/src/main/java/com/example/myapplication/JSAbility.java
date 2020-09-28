package com.example.myapplication;

import com.example.myapplication.service.ServiceForJsInternalAbility;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.ace.ability.AceAbility;

public class JSAbility extends AceAbility {



    @Override
    public void onStart(Intent intent) {
//        setInstanceName("default2");
        super.onStart(intent);
        ServiceForJsInternalAbility.register(this);
    }

    @Override
    public void onStop() {
        ServiceForJsInternalAbility.deregister();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtil.debugInfo("onBackPressed");
    }

    @Override
    public void pushPage(String url, IntentParams params) {
        super.pushPage(url, params);
        LogUtil.debugInfo(url);
    }
}
