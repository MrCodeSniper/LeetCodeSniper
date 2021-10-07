package mo.gov.safp.portal.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseApplication extends Application {

    private static List<Activity> mActivities = Collections.synchronizedList(new LinkedList<Activity>());
    private static AtomicInteger webCounter = new AtomicInteger();

    final static int MAX_WEBVIEW_COUNT = 5;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityListener();
    }

    protected void registerActivityListener() {
        this.registerActivityLifecycleCallbacks(new ActivityLifecycle());
    }

    public static int activityCount() {
        return null != mActivities ? mActivities.size() : 0;
    }

    public static Activity getCurrentActivity() {
        if (isEmptyActivity()) {
            return null;
        }
        Activity activity = mActivities.get(mActivities.size() - 1);
        return activity;
    }

    public static boolean runningActivity(Class<?> activityClass) {
        if (isEmptyActivity()) {
            return false;
        }

        for (Activity activity : mActivities) {
            if (null != activity && activity.getClass().equals(activityClass)) {
                return true;
            }
        }
        return false;
    }

    public static boolean finishActivity(Class<?> activityClass) {
        if (isEmptyActivity()) {
            return false;
        }

        for (Activity activity : mActivities) {
            if (null != activity && activity.getClass().equals(activityClass)) {
                activity.finish();
                return true;
            }
        }
        return false;
    }


    public static List<Activity> getAllActivity() {
        if (isEmptyActivity()) {
            return new ArrayList<Activity>();
        }
        return new ArrayList<Activity>(mActivities);
    }

    public static void finishAllActivity() {
        if (isEmptyActivity()) {
            return;
        }

        for (Activity activity : mActivities) {
            if (null != activity) {
                activity.finish();
            }
        }
    }

    public static void exitApp() {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public static void releaseWebActivity() {
        if (isEmptyActivity()) {
            return;
        }

        Iterator<Activity> iter = mActivities.iterator();
        while (iter.hasNext()) {
            Activity activity = iter.next();
            if (activity == null) {
                iter.remove();
            }
        }
    }

    private static boolean isEmptyActivity() {
        return mActivities == null || mActivities.isEmpty();
    }

    class ActivityLifecycle implements ActivityLifecycleCallbacks {

        static final String LOG_TAG = "ActivityLifecycle";

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (null == mActivities) {
                return;
            }

            Log.v(LOG_TAG, "onActivityCreated");

            //这里采用 弱引用
            WeakReference<Activity> weekReferece = new WeakReference<Activity>(activity);
            mActivities.add(weekReferece.get());

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            if (null == activity && isEmptyActivity()) {
                return;
            }

            Log.v(LOG_TAG, "onActivityDestroyed");

            if (mActivities.contains(activity)) {
                mActivities.remove(activity);
            }

        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }
    }

}
