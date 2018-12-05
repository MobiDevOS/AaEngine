package com.zhujohnle.mobidevos.framework.core.log;


import android.util.Log;

import com.zhujohnle.mobidevos.framework.core.log.config.LogConfig;
import com.zhujohnle.mobidevos.framework.core.log.i.IPrinter;

import org.json.JSONException;

import javax.xml.transform.TransformerException;

/**
 * 作者:zft
 * 日期:2018/2/5 0005.
 */

public class FLog {
    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;
    public static final int JSON = 8;
    public static final int XML = 9;
    public static final int EJSON = 10;

    private static boolean isDebug = true;

    private static IPrinter printer = new FPrinter();

    static {
        LogConfig config = new LogConfig();
        config.setShowThreadInfo(true);
        printer.init(config);
    }

    public static void init(boolean isDebug) {
        FLog.isDebug = isDebug;
    }

    public static void v(String... msg) {
        print(VERBOSE, msg);
    }

    public static void d(String... msg) {
        print(DEBUG, msg);
    }

    public static void i(String... msg) {
        print(INFO, msg);
    }

    public static void w(String... msg) {
        print(WARN, msg);
    }

    public static void e(String... msg) {
        print(ERROR, msg);
    }

    public static void json(String... msg) {
        print(JSON, msg);
    }
    public static void eJson(String... msg){
        print(EJSON,msg);
    }

    public static void xml(String msg) {
        print(XML, msg);
    }

    private static void print(int log, String... msg) {
        if (!isDebug) return;
        switch (log) {
            case VERBOSE:
                printer.v(msg);
                break;
            case DEBUG:
                printer.d(msg);
                break;
            case INFO:
                printer.i(msg);
                break;
            case WARN:
                printer.w(msg);
                break;
            case ERROR:
                printer.e(msg);
                break;

            case JSON:
                try {
                    printer.json(Log.DEBUG, msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case XML:
                try {
                    printer.xml(msg);
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
                break;
            case EJSON:
                try {
                    printer.json(Log.ERROR, msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
