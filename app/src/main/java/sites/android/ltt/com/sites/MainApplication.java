package sites.android.ltt.com.sites;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.taobao.atlas.bundleInfo.AtlasBundleInfoManager;
import android.taobao.atlas.framework.Atlas;
import android.taobao.atlas.framework.BundleInstaller;
import android.taobao.atlas.runtime.ActivityTaskMgr;
import android.taobao.atlas.runtime.ClassNotFoundInterceptorCallback;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.taobao.schedulers.AndroidSchedulers;

import org.osgi.framework.BundleException;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(MainApplication.class.getSimpleName(), "onCreate.UL1212LP.DI1211, enter.");

        Atlas.getInstance().setClassNotFoundInterceptorCallback(new ClassNotFoundInterceptorCallback() {
            @Override
            public Intent returnIntent(final Intent intent) {
                final String className = intent.getComponent().getClassName();
                final String bundleName = AtlasBundleInfoManager.instance().getBundleForComponet(className);

                Log.i(MainApplication.class.getSimpleName(), "onCreate.returnIntent.UL1212LP.DI1211, className: " + className);
                Log.i(MainApplication.class.getSimpleName(), "onCreate.returnIntent.UL1212LP.DI1211, bundleName: " + bundleName);

                if (!TextUtils.isEmpty(bundleName) && !AtlasBundleInfoManager.instance().isInternalBundle(bundleName)) {
                    //远程bundle
                    Activity activity = ActivityTaskMgr.getInstance().peekTopActivity();
                    File remoteBundleFile = new File(activity.getExternalCacheDir(),"lib" + bundleName.replace(".","_") + ".so");

                    String path = "";
                    if (remoteBundleFile.exists()){
                        path = remoteBundleFile.getAbsolutePath();
                    }
                    else {
                        Toast.makeText(activity, " 远程bundle不存在，请确定 : " + remoteBundleFile.getAbsolutePath() , Toast.LENGTH_LONG).show();
                        Log.i(MainApplication.class.getSimpleName(), "onCreate.returnIntent.UL1212LP.DI1211, remoteBundleFile not exist");
                        return intent;
                    }

                    PackageInfo info = activity.getPackageManager().getPackageArchiveInfo(path, 0);
                    try {
                        Atlas.getInstance().installBundle(info.packageName, new File(path));
                    }
                    catch (BundleException e) {
                        Toast.makeText(activity, " 远程bundle 安装失败，" + e.getMessage() , Toast.LENGTH_LONG).show();
                        Log.i(MainApplication.class.getSimpleName(), "onCreate.returnIntent.UL1212LP.DI1211, BundleException: " + e.getMessage());
                        e.printStackTrace();
                        return intent;
                    }
                    Log.i(MainApplication.class.getSimpleName(), "onCreate.returnIntent.UL1212LP.DI1211, activity will be launched next line");
                    ActivityTaskMgr.getInstance().peekTopActivity().startActivities(new Intent[]{intent});
//                    Atlas.getInstance().installBundleTransitivelyAsync(new String[]{path}, new BundleInstaller.InstallListener() {
//                        @Override
//                        public void onFinished() {
//                            Observable.just(MainApplication.class)
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .subscribe(new Consumer<Class<MainApplication>>() {
//                                        @Override
//                                        public void accept(Class<MainApplication> mainApplicationClass) throws Exception {
//                                            Log.i(MainApplication.class.getSimpleName(), "onCreate.returnIntent.UL1212LP.DI1211, activity will be launched next line");
//                                            ActivityTaskMgr.getInstance().peekTopActivity().startActivities(new Intent[]{intent});
//                                        }
//                                    });
//                        }
//                    });
                }

                return intent;
            }
        });
    }
}
