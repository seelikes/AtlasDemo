package sites.android.ltt.com.sites.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.taobao.atlas.dex.util.FileUtils;
import com.taobao.atlas.update.AtlasUpdater;
import com.taobao.atlas.update.model.UpdateInfo;

import java.io.File;

/**
 * Created by hezaiyan on 18-4-6.
 */

public class UpdateUtils {
    public static void update(Context context) {
        try {
            String versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            File updateInfo = new File(context.getExternalCacheDir(), "update-" + versionName + ".json");

            if (!updateInfo.exists()) {
                Log.e("update", "更新信息不存在，请先 执行 buildTpatch.sh");
//                toast("更新信息不存在，请先 执行 buildTpatch.sh", context);
                return;
            }

            String jsonStr = new String(FileUtils.readFile(updateInfo));
            UpdateInfo info = JSON.parseObject(jsonStr, UpdateInfo.class);

            File patchFile = new File(context.getExternalCacheDir(), "patch-" + info.updateVersion + "@" + info.baseVersion + ".tpatch");
            AtlasUpdater.update(info, patchFile);
            Log.e("update", "update success");
//            toast("更新成功，请重启app", context);
        }
        catch (Throwable e) {
            e.printStackTrace();
//            toast("更新失败, " + e.getMessage(), context);
            Toast.makeText(context, "update fail", Toast.LENGTH_LONG).show();
            Toast.makeText(context, "Throwable: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
