package com.example.myapplication.service;

import com.example.myapplication.util.LogUtil;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.event.commonevent.CommonEventData;
import ohos.event.commonevent.CommonEventManager;
import ohos.rpc.RemoteException;

public class CommonEventUtil {

    public static void sendInsertFinishEvent(){
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
