package com.tianyu.login_mvp_extract.model;

import com.tianyu.login_mvp_extract.api.ApiService;
import com.tianyu.login_mvp_extract.base.BaseModel;
import com.tianyu.login_mvp_extract.base.BaseObserver;
import com.tianyu.login_mvp_extract.bean.LoginBean;
import com.tianyu.login_mvp_extract.callback.LoginCallback;
import com.tianyu.login_mvp_extract.utils.HttpManager;
import com.tianyu.login_mvp_extract.utils.RxUtil;

public class ImpLoginModel extends BaseModel implements LoginModel {

    @Override
    public void login(String username, String password, LoginCallback loginCallback) {
        HttpManager.getHttpManager()
                .getApiService(ApiService.baseLoginUrl, ApiService.class)//获取接口服务对象
                .login(username, password)
                .compose(RxUtil.rxObservableTransformer())//RxUtil 切换线程
                .subscribe(new BaseObserver<LoginBean>(this) {
                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        int errorCode = loginBean.getErrorCode();
                        if (errorCode == 0) {
                            loginCallback.onSuccess(loginBean);
                        } else {
                            loginCallback.onFail(loginBean.getErrorMsg());
                        }
                    }

                    @Override
                    public void onFail(String error) {
                        loginCallback.onFail(error);
                    }
                });
    }
}
