package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.IAbilityConnection;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.bundle.ElementName;
import ohos.rpc.IRemoteObject;

import static com.example.myapplication.service.ServiceAbility.getServiceAbilityIntentInLocal;

public class SecondAbilitySlice extends AbilitySlice implements Component.ClickedListener {

    private final String TAG = "SecondAbilitySlice";

    private IAbilityConnection serviceConnect = new IAbilityConnection() {
        @Override
        public void onAbilityConnectDone(ElementName elementName, IRemoteObject iRemoteObject, int i) {

        }

        @Override
        public void onAbilityDisconnectDone(ElementName elementName, int i) {

        }
    };

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        LogUtil.lifeCycleInfo(TAG, "onStart()");

        setUIContent(ResourceTable.Layout_ability_second);

        initButton();
    }

    private void initButton() {
        Button button1 = (Button) findComponentById(ResourceTable.Id_jump_main);
        Button button2 = (Button) findComponentById(ResourceTable.Id_start_service);
        Button button3 = (Button) findComponentById(ResourceTable.Id_stop_service);
        Button button4 = (Button) findComponentById(ResourceTable.Id_connect_service);
        Button button5 = (Button) findComponentById(ResourceTable.Id_disconnect_service);
        button1.setClickedListener(this);
        button2.setClickedListener(this);
        button3.setClickedListener(this);
        button4.setClickedListener(this);
        button5.setClickedListener(this);
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

    @Override
    public void onClick(Component component) {
        switch (component.getId()) {
            case ResourceTable.Id_jump_main:
                jumpFirstAbility();
                break;
            case ResourceTable.Id_start_service:
                LogUtil.clickInfo("启动Service");
                startService();
                break;
            case ResourceTable.Id_stop_service:
                LogUtil.clickInfo("停止Service");
                stopService();
                break;
            case ResourceTable.Id_connect_service:
                LogUtil.clickInfo("连接Service");
                connectService();
                break;
            case ResourceTable.Id_disconnect_service:
                LogUtil.clickInfo("断连Service");
                disconnectService();
                break;
            default:
                break;
        }
    }

    private void jumpFirstAbility() {
        Intent secondIntent = new Intent();
        // 指定待启动FA的bundleName和abilityName
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")
                .withBundleName("com.example.myapplication")
                .withAbilityName("com.example.myapplication.FirstAbility")
//                        .withAction("$String:news_detail_action")
                .build();
        secondIntent.setOperation(operation);
        startAbility(secondIntent); // 通过AbilitySlice的startAbility接口实现启动另一个页面
        LogUtil.clickInfo("跳转-FirstAbility-新闻模块");
    }

    private void startService(){
        startAbility(getServiceAbilityIntentInLocal());
    }

    private void stopService(){
        stopAbility(getServiceAbilityIntentInLocal());
    }

    private void connectService(){
        connectAbility(getServiceAbilityIntentInLocal(), serviceConnect);
    }

    private void disconnectService(){
        disconnectAbility(serviceConnect);
    }

}
