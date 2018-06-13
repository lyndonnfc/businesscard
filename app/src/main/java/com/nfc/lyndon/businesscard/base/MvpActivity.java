package com.nfc.lyndon.businesscard.base;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.nfc.lyndon.businesscard.R;

import butterknife.ButterKnife;

public abstract class MvpActivity<T extends BasePresenter, M extends BaseModel> extends BaseActivity {

    public T mPresenter;

    public M mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //内部获取第一个类型参数的真实类型  ，反射new出对象
        mPresenter = initPresenter();
        //内部获取第二个类型参数的真实类型  ，反射new出对象
        mModel = initModel();
        //使得P层绑定M层和V层，持有M和V的引用
        mPresenter.attachModelView(mModel, this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    public abstract void initView();

    protected abstract T initPresenter();

    protected abstract M initModel();
}