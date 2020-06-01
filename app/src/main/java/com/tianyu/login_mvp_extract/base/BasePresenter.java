package com.tianyu.login_mvp_extract.base;

import java.util.ArrayList;

/**
 * 有界泛型 mView
 * 封装presenter
 *
 * @param <V>
 */
public abstract class BasePresenter<V extends BaseView> {
    protected V mView;
    private ArrayList<BaseModel> models = new ArrayList<>();

    public void setmView(V mView) {
        this.mView = mView;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void onDestroy() {
        //打断 p 层和 v层的联系
        mView = null;
        if (models.size() > 0) {
            //掐断网络请求
            for (BaseModel model : models) {
                model.onDestroy();
            }
            models.clear();
        }
    }

    public void addModel(BaseModel model) {
        models.add(model);
    }
}
