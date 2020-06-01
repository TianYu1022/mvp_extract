package com.tianyu.login_mvp_extract.view;

import com.tianyu.login_mvp_extract.base.BaseView;
import com.tianyu.login_mvp_extract.bean.LoginBean;

public interface LoginView extends BaseView<LoginBean> {
    @Override
    void onSuccess(LoginBean loginBean);

    @Override
    void onFail(String error);
}
