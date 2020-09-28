package com.example.myapplication.service;

import com.example.myapplication.data.NewsDataAbility;
import com.example.myapplication.data.NewsDataUtil;
import com.example.myapplication.data.db.News;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.rpc.*;
import ohos.utils.zson.ZSONObject;

import java.util.HashMap;
import java.util.Map;

public class ServiceForJsAbility extends Ability {

    private static final String TAG = "ServiceForJsAbility";
    private MyRemote remote = new MyRemote();

    // FA在请求PA服务时会调用AbilityconnectAbility连接PA，连接成功后，需要在onConnect返回一个remote对象，供FA向PA发送消息
    @Override
    protected IRemoteObject onConnect(Intent intent) {
        super.onConnect(intent);
        LogUtil.debugInfo("返回remote对象");
        return remote.asObject();
    }

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
    }

    class MyRemote extends RemoteObject implements IRemoteBroker {
        private static final int ERROR = -1;
        private static final int SUCCESS = 0;
        private static final int MEG_CODE_INSERT = 1001;

        MyRemote() {
            super("MyService_MyRemote");
        }

        @Override
        public boolean onRemoteRequest(int code, MessageParcel data, MessageParcel reply, MessageOption option) {
            switch (code) {
                case MEG_CODE_INSERT: {
                    LogUtil.serviceInfo(TAG, "请求码code =" + code);
                    String zsonStr = data.readString();
                    News news = ZSONObject.stringToClass(zsonStr, News.class);

                    boolean isSuccess = NewsDataUtil.insertNews(NewsDataAbility.getOrmContext(), news);
                    LogUtil.serviceInfo(TAG, "插入新闻isSuccess =" + isSuccess);
                    int resultCode = ERROR;
                    if (isSuccess){
                        resultCode = SUCCESS;
                        //发送广播通知回调
                        CommonEventUtil.sendInsertFinishEvent();
                    }
                    // 返回结果仅支持可序列化的Object类型
                    Map<String, Object> zsonResult = new HashMap<>();
                    zsonResult.put("code", resultCode);
                    reply.writeString(ZSONObject.toZSONString(zsonResult));
                    break;
                }
                default: {
                    LogUtil.serviceInfo(TAG, "请求码code=" + code);
                    Map<String, Object> zsonResult = new HashMap<>();
                    zsonResult.put("code", ERROR);
                    reply.writeString(ZSONObject.toZSONString(zsonResult));
                    return false;
                }
            }
            return true;
        }

        @Override
        public IRemoteObject asObject() {
            return this;
        }
    }

}
