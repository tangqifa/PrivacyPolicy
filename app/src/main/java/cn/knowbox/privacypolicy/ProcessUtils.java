package cn.knowbox.privacypolicy;

import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;
import java.util.List;

public class ProcessUtils {

    /*
     * 获得进程名
     */
    public static String getProcessName(Context context) {
        if (context == null) {
            return "";
        }
        int pid = ProcessUtils.getPid();
        String processName = ProcessUtils.getProcessName(context, pid);
        return processName;
    }

    /*
     * 获得当前进程的ID
     */
    public static int getPid() {
        int pid = android.os.Process.myPid();
        return pid;
    }

    /*
     * 获得包名
     */
    public static String getProcessName(Context context, int pID) {
        if (context == null) {
            return "";
        }

        String processName = null;
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return "";
        }
        List<ActivityManager.RunningAppProcessInfo> l = am.getRunningAppProcesses();
        if (l == null || l.isEmpty()) {
            return "";
        }
        Iterator<ActivityManager.RunningAppProcessInfo> i = l.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = i
                    .next();
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
            }
        }
        return processName;
    }
}
