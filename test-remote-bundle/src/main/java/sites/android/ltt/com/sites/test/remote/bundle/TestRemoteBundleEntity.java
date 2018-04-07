package sites.android.ltt.com.sites.test.remote.bundle;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;

public class TestRemoteBundleEntity extends BaseObservable implements Serializable {
    @Bindable
    private String www;

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
        notifyPropertyChanged(BR.www);
    }
}
