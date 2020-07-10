package cn.knowbox.privacypolicy;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class BaseApp extends Application {
    private static Context mContext;
    private static Context mAppContextTmp;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppContextTmp = this;
        if (isProcessValid()) {
            mContext = this;
            Log.d("qifa", "主工程初始化oncreate");
        } else {
            Log.d("qifa", "隐私协议oncreate");
        }
    }

    /*
     * 获得全局上下文
     */
    public static Context getAppContext() {
        if (mContext == null) {
            return mAppContextTmp;
        }
        return mContext;
    }

    /*
     * 获得合法的进程名
     */
    public String[] getValidProcessNames() {
        return new String[]{"cn.knowbox.privacypolicy"};
    }

    /*
     * 是否是合法进程
     */
    public boolean isProcessValid() {
        String process[] = getValidProcessNames();
        if (process == null || process.length == 0) {
            return true;
        }
        String processName = ProcessUtils.getProcessName(this);
        for (int i = 0; i < process.length; i++) {
            if (process != null && process[i].equals(processName)) {
                return true;
            }
        }
        return false;
    }
}
