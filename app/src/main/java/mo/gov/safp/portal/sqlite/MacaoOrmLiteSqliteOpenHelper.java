package mo.gov.safp.portal.sqlite;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.alibaba.sqlcrypto.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.List;


public class MacaoOrmLiteSqliteOpenHelper extends OrmLiteSqliteOpenHelper {
    /**
     * 数据库名称
     */
    private static final String DB_NAME = "mo_gov_storage.db";
    /**
     * 当前数据库版本
     */
    private static final int DB_VERSION = 1;
    /**
     * 数据库加密密钥，mPaaS 支持数据库加密，使数据在设备上更安全，若为 null 则不加密。
     * 注意：密码只能设置一次，不提供修改密码的 API；不支持对未加密的库设置密码进行加密（会导致闪退）。
     */
    private static final String DB_PASSWORD = "mpaas";

    public MacaoOrmLiteSqliteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
//        setPassword(null);
    }

    /**
     * 数据库创建时的回调函数
     *
     * @param sqLiteDatabase   数据库
     * @param connectionSource 连接
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            // 创建User表
//            TableUtils.createTableIfNotExists(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, LoginResultData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 数据库更新时的回调函数
     *
     * @param database         数据库
     * @param connectionSource 连接
     * @param oldVersion       旧数据库版本
     * @param newVersion       新数据库版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            Log.d("chenhongDB","onUpgrade");
            TableUtils.dropTable(connectionSource, LoginResultData.class, true);
            TableUtils.createTableIfNotExists(connectionSource, LoginResultData.class);
//            ThreadPoolManager.getInstance().execute(() -> {
//                UserCenterClient serviceClient = MPRpc.getRpcProxy(UserCenterClient.class);
//                MPRpc.getRpcInvokeContext(serviceClient).addRequestHeader(ConstantsKt.TOKEN, UserCenterManager.getInstance().getToken());
//                MPRpc.getRpcInvokeContext(serviceClient).addRequestHeader(ConstantsKt.ACCEPT_LANGUAGE, LanguageSPManager.getInstance().getLanguage());
//                ResultContainer<LoginResultData> result = serviceClient.getUserProfile();
//                if(result!=null && result.success){
//                    Log.d("chenhongDB","updateUserInfo");
//                    UserCenterManager.getInstance().updateUserInfo(result.data);
//                }
//            });
//            MacaoDatabaseUtil.upgradeTable(database, connectionSource, LoginResultData.class, MacaoDatabaseUtil.OPERATION_TYPE.DELETE);
        }
    }
//
//        try {
//            // 删除旧版User表，忽略错误
////            TableUtils.dropTable(connectionSource, User.class, true);
//            TableUtils.dropTable(connectionSource, LoginResultData.class, true);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            // 从新创建User表
////            TableUtils.createTableIfNotExists(connectionSource, User.class);
//            TableUtils.createTableIfNotExists(connectionSource, LoginResultData.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}