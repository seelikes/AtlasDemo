package sites.android.ltt.com.sites.test.remote.bundle;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import sites.android.ltt.com.sites.test.remote.bundle.databinding.ActivityTestRemoteBundleBinding;

public class TestRemoteBundleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestRemoteBundleBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_test_remote_bundle);
        TestRemoteBundleEntity entity = new TestRemoteBundleEntity();
        entity.setWww("TestRemoteBundleActivity");
        binding.setRemote(entity);
    }

    public void onButtonClick(View view) {
        Toast.makeText(this, "onButtonClick", Toast.LENGTH_SHORT).show();
    }
}
