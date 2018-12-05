package com.zhujohnle.mobidevos.framework.http.core.interceptor;


import com.zhujohnle.mobidevos.framework.core.log.FLog;

import java.io.IOException;
import java.util.List;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * zft
 * 2017/4/21.
 */

public class FtLogInterceptor implements Interceptor {
    private final boolean isShow = true;

    private long startTime = 0;
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        logForRequest(request);

        Response response = chain.proceed(request);
        logForResponse(response);
        return response;
    }


    private void logForResponse(Response response) {
        if (!isShow) return;
        long duration = System.currentTimeMillis() - startTime;
        StringBuilder sb = new StringBuilder();
        ResponseBody responseBody = response.body();
        Request request = response.request();
        HttpUrl url = request.url();
        int code = response.code();
        long l = responseBody.contentLength();
        sb.append("请求时长(毫秒)："+duration).append(" ; contentLength:"+l).append("\r\n");
        sb.append("Url:" + url).append("\r\n");
        sb.append("Code:" + code).append("\r\n");
        MediaType mediaType = responseBody.contentType();
        if (mediaType != null) {
            sb.append("ContentType:" + mediaType.toString()).append("\r\n");
            if (isText(mediaType)) {
                BufferedSource source = responseBody.source();
                try {
                    source.request(Long.MAX_VALUE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Buffer buffer = source.buffer().clone();
                String s = buffer.readUtf8();
                FLog.json("HttpResponse",sb.toString(), s);
                return;
            } else {
                sb.append("Content : " + " maybe [file part] , too large too print , ignored!").append("\r\n");
            }
        } else {

        }
        FLog.json("HttpResponse",sb.toString());
    }

    private void logForRequest(Request request) {
        if (!isShow) return;
        startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        HttpUrl url = request.url();
        String method = request.method();
        RequestBody body = request.body();


        String paramsString = bodyToString(request);
        sb.append("Url:" + url);
        if (!"".equalsIgnoreCase(paramsString)) {
            sb.append("?" + paramsString);
        }
        sb.append("\r\n");
        sb.append("Method:" + method).append("\r\n");
        if (body != null) {
            MediaType mediaType = body.contentType();
            if (mediaType != null) {
                sb.append("ContentType:" + mediaType.toString());
            }
        }
        Headers headers = request.headers();

        for(int i = 0;i<headers.size();i++) {
            String name = headers.name(i);
            String value = headers.get(name);
            sb.append(name).append(":").append(value).append("\r\n");
        }
//        Set<String> names = headers.names();
//        for (String name : names) {
//            List<String> values = headers.values(name);
//            String listLog = getListLog(values);
//            sb.append(names).append(":").append(listLog).append("\r\n");
//        }
        FLog.json("HttpRequest",sb.toString());
    }

    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            if (copy == null) return "";
            final Buffer buffer = new Buffer();
            if (copy.body() == null) return "";
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }

    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    )
                return true;
        }
        return false;
    }

    private String getListLog(List<String> list) {
        StringBuffer sb = new StringBuffer();
        for (String value : list) {
            sb.append(value).append(" ; ");
        }
        return sb.toString();
    }
}
