package com.tianyu.login_mvp_extract.callback;

import com.tianyu.login_mvp_extract.base.BaseCallback;
import com.tianyu.login_mvp_extract.bean.LoginBean;

public interface LoginCallback extends BaseCallback<LoginBean> {
    @Override
    void onSuccess(LoginBean loginBean);

    @Override
    void onFail(String error);
}
