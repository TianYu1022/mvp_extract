package com.tianyu.login_mvp_extract.base;

/**
 * 有界泛型 mView
 * 封装presenter
 *
 * @param <V>
 */
public abstract class BasePresenter<V extends BaseView> {
    protected V mView;

    public void setmView(V mView) {
        this.mView = mView;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();
}
