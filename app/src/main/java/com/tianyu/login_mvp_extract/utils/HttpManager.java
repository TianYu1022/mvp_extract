package com.tianyu.login_mvp_extract.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tianyu.login_mvp_extract.app.MvpApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络工具类
 */
public class HttpManager {
    //通过双检索单例获取本类对象
    private static volatile HttpManager httpManager;
    private final Retrofit.Builder builder;

    public static HttpManager getHttpManager() {
        if (httpManager == null) {
            synchronized (HttpManager.class) {
                if (httpManager == null) {
                    httpManager = new HttpManager();
                }
            }
        }
        return httpManager;
    }

    /**
     * 在空参构造中获取 retrofit的 builder 类对象
     */
    public HttpManager() {
        builder = new Retrofit.Builder()
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    /**
     * 自定义OK客户端
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .cache(new Cache(
                        new File(MvpApplication.getApp().getCacheDir(), "PATH_CACHE")
                        , 1024 * 1024 * 50))//缓存
                .addNetworkInterceptor(new MyCacheIntercepter())//网络拦截器
                .addInterceptor(new MyCacheIntercepter())
                .connectTimeout(10, TimeUnit.SECONDS)//连接超时
                .readTimeout(10, TimeUnit.SECONDS)//读取超时
                .writeTimeout(10, TimeUnit.SECONDS)//写入超时
                .retryOnConnectionFailure(true)//连接失败重试
                .build();
    }

    /**
     * clazz传过来class是谁就返回谁
     * 获取接口服务对象
     *
     * @param baseUrl
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getApiService(String baseUrl, Class<T> clazz) {
        return builder.baseUrl(baseUrl).build().create(clazz);
    }

    //网络拦截器
    public class MyCacheIntercepter implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (!isNetworkAvailable(MvpApplication.getApp())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();

            }

            Response originalResponse = chain.proceed(request);

            if (isNetworkAvailable(MvpApplication.getApp())) {
                int maxAge = 0;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 15 * 60;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }
}
