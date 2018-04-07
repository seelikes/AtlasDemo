package com.ltt.android.sites.bundle.test;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ltt.android.sites.bundle.test.databinding.ActivityTestBundleBinding;

public class TestBundleActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TestBundleActivity.class.getSimpleName(), "onCreate.UL1212LP.DI1211, enter.");
        TestBundleEntity entity = new TestBundleEntity();
        entity.setAad("Aad: onCreate");
        entity.setBbc("Bbc: entity");
        ActivityTestBundleBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_test_bundle);
        binding.setEntity(entity);
    }
}
