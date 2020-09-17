package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.Text;


public class NewsDetailAbilitySlice extends AbilitySlice {

    private final String TAG = "NewsDetailAbilitySlice";

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        LogUtil.lifeCycleInfo(TAG, "onStart()");

        setUIContent(ResourceTable.Layout_ability_news_detail);

        Text newsContent = (Text) findComponentById(ResourceTable.Id_news_content);
        newsContent.setText(ResourceTable.String_news_content);
        Image icon = (Image) findComponentById(ResourceTable.Id_news_icon);
        icon.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                Intent secondIntent = new Intent();
                // 指定待启动FA的bundleName和abilityName
                Operation operation = new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withBundleName("com.example.myapplication")
                        .withAbilityName("com.example.myapplication.SecondAbility")
                        .build();
                secondIntent.setOperation(operation);
                startAbility(secondIntent); // 通过AbilitySlice的startAbility接口实现启动另一个页面
                LogUtil.clickInfo("跳转-SecondAbility-");
            }
        });
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
