package com.nfc.lyndon.businesscard.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends BasePresenter, M extends BaseModel> extends Fragment {

    public T mPresenter;

    public M mModel;

    protected Context mContext;

    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        //内部获取第一个类型参数的真实类型  ，反射new出对象
        mPresenter = initPresenter();
        //内部获取第二个类型参数的真实类型  ，反射new出对象
        mModel = initModel();
        //使得P层绑定M层和V层，持有M和V的引用
        mPresenter.attachModelView(mModel, this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentId(),container,false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected abstract int getContentId();

    protected abstract void initView();

    protected abstract T initPresenter();

    protected abstract M initModel();
}
