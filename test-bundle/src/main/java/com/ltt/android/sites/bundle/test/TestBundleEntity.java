package com.ltt.android.sites.bundle.test;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class TestBundleEntity extends BaseObservable {
    @Bindable
    private String aad;
    @Bindable
    private String bbc;

    public TestBundleEntity() {

    }

    public String getAad() {
        return aad;
    }

    public void setAad(String aad) {
        this.aad = aad;
    }

    public String getBbc() {
        return bbc;
    }

    public void setBbc(String bbc) {
        this.bbc = bbc;
    }
}
