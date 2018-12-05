package com.zhujohnle.mobidevos.framework.http.core.interceptor;


import com.zhujohnle.mobidevos.framework.http.HttpEngineKeys;
import com.zhujohnle.mobidevos.utils.SPUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Allen on 2017/5/11.
 * <p>
 *
 * @author Allen
 * 接受服务器发的cookie   并保存到本地
 */

public class ReceivedCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        //这里获取请求返回的cookie
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            SPUtils.getInstance().put(HttpEngineKeys.COOKIE, cookies);
        }

        return originalResponse;
    }
}
