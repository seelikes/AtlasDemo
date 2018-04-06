package com.ltt.android.sites.bundle.test;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ltt.android.sites.bundle.test.databinding.ActivityTestBundleBinding;

public class TestBundleActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestBundleEntity entity = new TestBundleEntity();
        entity.setAad("Aad: onCreate");
        entity.setBbc("Bbc: entity");
        ActivityTestBundleBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_test_bundle);
        binding.setEntity(entity);
    }
}
