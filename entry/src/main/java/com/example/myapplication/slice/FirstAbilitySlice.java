package com.example.myapplication.slice;

import com.example.myapplication.data.ListHolder;
import com.example.myapplication.data.ListItemProvider;
import com.example.myapplication.data.NewsDataAbility;
import com.example.myapplication.data.NewsDataUtil;
import com.example.myapplication.data.db.News;
import com.example.myapplication.service.ServiceAbility;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.content.Intent;
import ohos.event.commonevent.*;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import ohos.eventhandler.InnerEvent;
import ohos.rpc.RemoteException;
import ohos.utils.net.Uri;

import java.util.List;

public class FirstAbilitySlice extends AbilitySlice {

    private final String TAG = "FirstAbilitySlice";

    public static final int CODE_QUERY_FINISH = 2;


    private ListHolder listHolder;
    private List<News> newsList;

    private MyCommonEventSubscriber subscriber;

    EventRunner runnerA = EventRunner.create("queryRunner"); // 内部会新建一个线程
    private MyEventHandler handler;

    public class MyEventHandler extends EventHandler {
        private MyEventHandler(EventRunner runner) {
            super(runner);
        }

        @Override
        public void processEvent(InnerEvent event) {
            super.processEvent(event);
            if (event == null) {
                return;
            }

            int eventId = event.eventId;

            switch (eventId) {
                case CODE_QUERY_FINISH:
                    ListItemProvider itemProvider = (ListItemProvider) listHolder.getListContainer().getItemProvider();
                    if (newsList != null) {
                        itemProvider.setNews(newsList);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        LogUtil.lifeCycleInfo(TAG, "onStart()");
        listHolder = new ListHolder(this);
        setUIContent(listHolder.createComponent());

        handler = new MyEventHandler(runnerA);

        registerCommonEvent();

//        NewsDataUtil.insertNews(NewsDataAbility.getOrmContext());
//        ListItemProvider itemProvider = (ListItemProvider) listHolder.getListContainer().getItemProvider();
//        itemProvider.setNews(NewsDataUtil.queryNews(NewsDataAbility.getOrmContext()));

//        try {
//            LogUtil.debugInfo("A");
//            NewsDataUtil.insertNewsWithDataModel(DataAbilityHelper.creator(getApplicationContext()), Uri.parse(NewsDataAbility.AUTHORITY));
//            ListItemProvider itemProvider = (ListItemProvider) listHolder.getListContainer().getItemProvider();
//            LogUtil.debugInfo("B");
//            itemProvider.setNews(NewsDataUtil.queryNewsWithDataModel(DataAbilityHelper.creator(getApplicationContext()), Uri.parse(NewsDataAbility.AUTHORITY)));
//            LogUtil.debugInfo("C");
//        }catch (Exception e){
//            LogUtil.debugInfo(e.getMessage());
//        }

    }

    private void registerCommonEvent() {
        String event = "com.my.insertFinish";
        MatchingSkills matchingSkills = new MatchingSkills();
        matchingSkills.addEvent(event); // 自定义事件
        matchingSkills.addEvent(CommonEventSupport.COMMON_EVENT_SCREEN_ON); // 亮屏事件
        CommonEventSubscribeInfo subscribeInfo = new CommonEventSubscribeInfo(matchingSkills);
        subscriber = new MyCommonEventSubscriber(subscribeInfo);
        try {
            CommonEventManager.subscribeCommonEvent(subscriber);
        } catch (RemoteException e) {
            LogUtil.debugInfo("subscribeCommonEvent occur exception.");
        }
    }

    private void unregisterCommonEvent(){
        try {
            CommonEventManager.unsubscribeCommonEvent(subscriber);
        } catch (RemoteException e) {
            LogUtil.debugInfo("unsubscribeCommonEvent occur exception.");
        }
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
        unregisterCommonEvent();
        LogUtil.lifeCycleInfo(TAG, "onStop()");
    }

    class MyCommonEventSubscriber extends CommonEventSubscriber {
        MyCommonEventSubscriber(CommonEventSubscribeInfo info) {
            super(info);
        }

        @Override
        public void onReceiveEvent(CommonEventData commonEventData) {

            handler.postTask(new Runnable() {
                @Override
                public void run() {
                    newsList = NewsDataUtil.queryNews(NewsDataAbility.getOrmContext());
                    handler.sendEvent(CODE_QUERY_FINISH);
                }
            });

//            new Thread(() -> {
//                ListItemProvider itemProvider = (ListItemProvider) listHolder.getListContainer().getItemProvider();
//                itemProvider.setNews(NewsDataUtil.queryNews(NewsDataAbility.getOrmContext()));
////                itemProvider.setNews(NewsDataUtil.queryNewsWithDataModel(DataAbilityHelper.creator(getApplicationContext()), Uri.parse(NewsDataAbility.AUTHORITY)));
//            }).run();
        }
    }

}
