package com.zhujohnle.mobidevos.framework.core.log.i;

import org.json.JSONException;

import javax.xml.transform.TransformerException;


public interface IResolver {
    /***
     * 解析json
     * @param msg
     * @return
     */
    String json(String msg) throws JSONException;

    /***
     * 解析xml
     * @param msg
     * @return
     */
    String xml(String msg) throws TransformerException;

    /***
     * 获取栈信息
     * @return
     */
    String getStackInfo();
}
