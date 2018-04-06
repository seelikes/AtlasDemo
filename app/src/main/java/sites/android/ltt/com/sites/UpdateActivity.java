package sites.android.ltt.com.sites;

import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sites.android.ltt.com.sites.utils.UpdateUtils;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    public void onUpdate(View view) {
        Observable.just(view)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<View>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(View lll) {
                        try {
                            UpdateUtils.update(UpdateActivity.this.getBaseContext());
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            Log.e(MainActivity.class.getSimpleName(), "onNavigationItemSelected.accept.DI1211, e: " + e.getMessage());
                            throw e;
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        Process.killProcess(Process.myPid());
                    }
                });
    }
}
