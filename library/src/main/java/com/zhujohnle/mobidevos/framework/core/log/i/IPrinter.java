package com.zhujohnle.mobidevos.framework.core.log.i;


import com.zhujohnle.mobidevos.framework.core.log.config.LogConfig;

import org.json.JSONException;

import javax.xml.transform.TransformerException;

/**
 * 作者:zft
 * 日期:2018/2/5 0005.
 */

public interface IPrinter {
    void init(LogConfig config);

    //    Verbose
    void v(String... msg);

    //    Debug
    void d(String... msg);

    //    Info
    void i(String... msg);

    //    Warm
    void w(String... msg);

    //    Error
    void e(String... msg);

    //    Json
    void json(int type, String... msg) throws JSONException;

    //    Xml
    void xml(String... msg) throws TransformerException;


}
