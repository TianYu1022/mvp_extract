package com.tianyu.login_mvp_extract.model;

import com.tianyu.login_mvp_extract.callback.LoginCallback;

public interface LoginModel {
    void login(String username, String password, LoginCallback loginCallback);
}
