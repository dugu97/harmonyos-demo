package com.example.myapplication.service;

import com.example.myapplication.data.NewsDataAbility;
import com.example.myapplication.data.NewsDataUtil;
import com.example.myapplication.data.db.News;
import com.example.myapplication.util.LogUtil;
import ohos.ace.ability.AceInternalAbility;
import ohos.app.AbilityContext;
import ohos.location.RequestParam;
import ohos.rpc.IRemoteObject;
import ohos.rpc.MessageOption;
import ohos.rpc.MessageParcel;
import ohos.rpc.RemoteException;
import ohos.utils.zson.ZSONObject;

import java.util.HashMap;
import java.util.Map;

public class ServiceForJsInternalAbility extends AceInternalAbility {

    private static final String TAG = ServiceForJsInternalAbility.class.getSimpleName();

    private static final String BUNDLE_NAME = "com.example.myapplication";
    private static final String ABILITY_NAME = "com.example.myapplication.service.ServiceForJsInternalAbility";
    private static final int ERROR = -1;
    private static final int SUCCESS = 0;
    private static final int MEG_CODE_INSERT = 1001;

    private static ServiceForJsInternalAbility instance;
    private AbilityContext abilityContext;

    public ServiceForJsInternalAbility() {
        super(BUNDLE_NAME, ABILITY_NAME);
    }

    public boolean onRemoteRequest(int code, MessageParcel data, MessageParcel reply, MessageOption option) {
        switch (code) {
            case MEG_CODE_INSERT: {
                String zsonStr = data.readString();
                News news = ZSONObject.stringToClass(zsonStr, News.class);                // 返回结果当前仅支持String，对于复杂结构可以序列化为ZSON字符串上报
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
                // SYNC
                if (option.getFlags() == MessageOption.TF_SYNC) {
                    reply.writeString(ZSONObject.toZSONString(zsonResult));
                } else {
                    // ASYNC
                    MessageParcel reponseData = MessageParcel.obtain();
                    reponseData.writeString(ZSONObject.toZSONString(zsonResult));
                    IRemoteObject remoteReply = reply.readRemoteObject();
                    try {
                        remoteReply.sendRequest(0, reponseData, MessageParcel.obtain(), new MessageOption());
                        reponseData.reclaim();
                    } catch (RemoteException exception) {
                        return false;
                    }
                }
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

    /**
     * Internal ability registration.
     */
    public static void register(AbilityContext abilityContext) {
        instance = new ServiceForJsInternalAbility();
        instance.onRegister(abilityContext);
    }

    private void onRegister(AbilityContext abilityContext) {
        this.abilityContext = abilityContext;
        this.setInternalAbilityHandler((code, data, reply, option) -> this.onRemoteRequest(code, data, reply, option));
    }

    /**
     * Internal ability deregistration.
     */
    public static void deregister() {
        instance.onDeregister();
    }

    private void onDeregister() {
        abilityContext = null;
        this.setInternalAbilityHandler(null);
    }
}
