package com.nfc.lyndon.businesscard.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M, V> {

    public M mModel;

    private V mView;

    public WeakReference<V> mViewRef;


    public void attachModelView(M pModel, V pView) {

        mViewRef = new WeakReference<V>(pView);

        this.mView = mViewRef.get();

        this.mModel = pModel;
    }


    public V getView() {
        if (isAttach()) {
            return mViewRef.get();
        } else {
            return null;
        }
    }

    public boolean isAttach() {
        return null != mViewRef && null != mViewRef.get();
    }


    public void onDetach() {
        if (null != mViewRef) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
