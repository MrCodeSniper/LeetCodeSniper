package mo.gov.safp.portal.softtoken.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.greenrobot.greendao.database.Database;



public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.d("MySQLiteOpenHelper", "onUpgrade oldVersion = " + oldVersion + " && newVersion = " + newVersion);

        // newVersion = 14 更新內容：AppGroupInfo、AppVersionInfo、MetaReactInfo
        if(oldVersion == 13){ //從版本13升級到14
//            MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
//                @Override
//                public void onCreateAllTables(Database db, boolean ifNotExists) {
//                    DaoMaster.createAllTables(db, ifNotExists);
//                }
//                @Override
//                public void onDropAllTables(Database db, boolean ifExists) {
//                    DaoMaster.dropAllTables(db, ifExists);
//                }
//            }, AppGroupInfoDao.class, AppVersionInfoDao.class);
        }else if(oldVersion == 14){ //從版本13升級到14
//            MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
//                @Override
//                public void onCreateAllTables(Database db, boolean ifNotExists) {
//                    DaoMaster.createAllTables(db, ifNotExists);
//                }
//                @Override
//                public void onDropAllTables(Database db, boolean ifExists) {
//                    DaoMaster.dropAllTables(db, ifExists);
//                }
//            }, AppVersionInfoDao.class);
        }
    }
}
