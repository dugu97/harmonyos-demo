package com.example.myapplication.util;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogConstString;
import ohos.hiviewdfx.HiLogLabel;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtil {

    private final static String CLICK_INFO = "click";
    private final static String LIFECYCLE_INFO = "lifecycle";
    private final static String SERVICE_INFO = "service";
    private final static String DATA_ABILITY_INFO = "dataAbility";
    private final static String DEBUG_INFO = "mydebug";

    public static void info(String tag, String meg){
        Logger.getLogger(Level.INFO.getName()).info(tag + ":" + meg);
    }

    public static void clickInfo(String meg){
        Logger.getLogger(Level.INFO.getName()).info(CLICK_INFO + ":" + meg);
    }

    public static void lifeCycleInfo(String tag, String meg){
        Logger.getLogger(Level.INFO.getName()).info(LIFECYCLE_INFO + "_" + tag + ":" + meg);
    }

    public static void serviceInfo(String tag, String meg){
        Logger.getLogger(Level.INFO.getName()).info(SERVICE_INFO + "_" + tag + ":" + meg);
    }

    public static void dataInfo(String meg){
        Logger.getLogger(Level.INFO.getName()).info(DATA_ABILITY_INFO  + ":" + meg);
    }

    public static void debugInfo(String meg){
        Logger.getLogger(Level.INFO.getName()).info(DEBUG_INFO  + ":" + meg);
    }
}
