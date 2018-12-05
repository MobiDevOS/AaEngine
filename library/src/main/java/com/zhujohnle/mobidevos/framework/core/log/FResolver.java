package com.zhujohnle.mobidevos.framework.core.log;


import com.zhujohnle.mobidevos.framework.core.log.i.IResolver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * 作者:zft
 * 日期:2018/2/5 0005.
 */

public class FResolver implements IResolver {
    public static String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public String json(String msg) throws JSONException {
        String result = "";
        if (msg.startsWith("{")) {    //对象json
            JSONObject jObj = new JSONObject(msg);
            result = jObj.toString(4);
        } else if (msg.startsWith("[")) {  //数组json
            JSONArray jArray = new JSONArray(msg);
            result = jArray.toString(4);
        } else {
            result = msg;
        }
        return result;
    }

    @Override
    public String xml(String msg) throws TransformerException {
        Source xmlInput = new StreamSource(new StringReader(msg));
        StreamResult xmlOutput = new StreamResult(new StringWriter());
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(xmlInput, xmlOutput);
        String message = xmlOutput.getWriter().toString().replaceFirst(">", ">" + LINE_SEPARATOR);
        return message;

    }

    @Override
    public String getStackInfo() {
        StringBuilder perTrace = new StringBuilder();
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        for (int i = 0; i < traces.length; i++) {
            StackTraceElement element = traces[i];
            if (element.isNativeMethod()) {
                continue;
            }
            String className = element.getClassName();
            if (className.startsWith("android.")
                    || className.contains("com.android")
                    || className.contains("java.lang")
                    || className.contains(this.getClass().getPackage().getName())
                    ) {
                continue;
            }
            perTrace
                    .append(element.getClassName())
                    .append('.')
                    .append(element.getMethodName())
                    .append("(")
                    .append(element.getFileName())
                    .append(':')
                    .append(element.getLineNumber())
                    .append(")");
            break;
        }
        return perTrace.toString();
    }
}
