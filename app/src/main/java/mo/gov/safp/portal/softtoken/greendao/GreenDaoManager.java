package mo.gov.safp.portal.softtoken.greendao;


import mo.gov.safp.portal.base.CustomApplication;

public final class GreenDaoManager {

    private static GreenDaoManager mInstance;
    private DaoSession mDaoSession;

    private GreenDaoManager() {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(CustomApplication.getInstance(), "gov-smart-app-1.1");
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(CustomApplication.getInstance(), "gov-smart-app-2.0", null);

        mDaoSession = new DaoMaster(helper.getWritableDb()).newSession();
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }
}
