package com.tianyu.login_mvp_extract.callback;

import com.tianyu.login_mvp_extract.bean.LoginBean;

public interface LoginCallback {
    void onSuccess(LoginBean loginBean);

    void onFail(String error);
}
