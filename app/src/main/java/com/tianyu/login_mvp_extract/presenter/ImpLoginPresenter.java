package com.tianyu.login_mvp_extract.presenter;

import android.text.TextUtils;

import com.tianyu.login_mvp_extract.base.BasePresenter;
import com.tianyu.login_mvp_extract.bean.LoginBean;
import com.tianyu.login_mvp_extract.callback.LoginCallback;
import com.tianyu.login_mvp_extract.model.ImpLoginModel;

public
class ImpLoginPresenter extends BasePresenter implements LoginPresenter, LoginCallback {

    private ImpLoginModel model;

    @Override
    public void login(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            mView.onFail("姓名不能为空");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mView.onFail("密码不能为空");
            return;
        }

        model.login(username, password, this);
    }

    @Override
    protected void initModel() {
        model = new ImpLoginModel();
        addModel(model);
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        mView.onSuccess(loginBean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
