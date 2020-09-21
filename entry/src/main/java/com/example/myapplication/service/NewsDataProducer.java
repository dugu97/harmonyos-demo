package com.example.myapplication.service;

import com.example.myapplication.data.NewsDataAbility;
import com.example.myapplication.data.NewsDataUtil;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.app.Context;
import ohos.event.commonevent.CommonEventData;
import ohos.event.commonevent.CommonEventManager;
import ohos.hiviewdfx.HiLog;
import ohos.rpc.RemoteException;
import ohos.utils.net.Uri;

public class NewsDataProducer extends Thread{

    private Context context;

    public NewsDataProducer(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        super.run();
//        两种方式插入新闻相关数据
//        NewsDataUtil.insertNews(NewsDataAbility.getOrmContext());
        NewsDataUtil.insertNewsWithDataModel(DataAbilityHelper.creator(context), Uri.parse(NewsDataAbility.AUTHORITY));

//      发送广播通知回调
        sendInsertFinishEvent();
    }

    private void sendInsertFinishEvent(){
        try {
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withAction("com.my.insertFinish")
                    .build();
            intent.setOperation(operation);
            CommonEventData eventData = new CommonEventData(intent);
            CommonEventManager.publishCommonEvent(eventData);
        } catch (RemoteException e) {
//            HiLog.info(LABEL, "publishCommonEvent occur exception.");
            LogUtil.debugInfo("publishCommonEvent occur exception.");
        }
    }
}
