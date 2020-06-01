package com.tianyu.login_mvp_extract.api;

import com.tianyu.login_mvp_extract.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    String baseLoginUrl = "https://www.wanandroid.com/";

    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginBean> login(@Field("username") String username, @Field("password") String password);
}
