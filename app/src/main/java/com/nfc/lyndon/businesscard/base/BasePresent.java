package com.nfc.lyndon.businesscard.base;

public abstract class BasePresent<T> {

    public T view;

    public void attach(T view) {
        this.view = view;
    }

    public void detach() {
        this.view = null;
    }
}
