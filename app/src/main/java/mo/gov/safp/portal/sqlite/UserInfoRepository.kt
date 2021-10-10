package mo.gov.safp.portal.sqlite

import android.content.Context
import android.database.SQLException


/**
 * 用户信息存储
 */
class UserInfoRepository(context: Context) {

    private var mDbHelper: MacaoOrmLiteSqliteOpenHelper = MacaoOrmLiteSqliteOpenHelper(context)

    /**
     * 插入用户信息
     * @param user 用户信息
     */
    fun insertUser(user: LoginResultData?): Int {
        if (null == user) {
            return 0
        }
        try {
            return mDbHelper.getDao(LoginResultData::class.java).create(user)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 删除用户信息
     * @param user 用户信息
     */
    fun deleteUser(user: LoginResultData): Int {
        try {
            return mDbHelper.getDao(LoginResultData::class.java).delete(user)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 删除用户信息
     */
    fun deleteAll(): Int {
        try {
            return mDbHelper.getDao(LoginResultData::class.java).deleteBuilder().delete()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 删除子用户信息
     */
    fun deleteAllChild(): Int {
        try {
            val dao = mDbHelper.getDao(LoginResultData::class.java)
            val list = dao.queryForEq(COLUMN_IS_CHILD, true)
            return dao.delete(list)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 条件删除
     */
    fun deleteById(id: String): Int {
        try {
            val dao = mDbHelper.getDao(LoginResultData::class.java)
            val deleteBuilder = dao.deleteBuilder()
            deleteBuilder.where().eq(COLUMN_EUID, id)
            return deleteBuilder.delete()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 条件删除多个
     */
    fun deleteByIds(euids: List<String>): Int {
        try {
            val dao = mDbHelper.getDao(LoginResultData::class.java)
            val deleteBuilder = dao.deleteBuilder()
            deleteBuilder.where().`in`(COLUMN_EUID, euids)
            return deleteBuilder.delete()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 更新用户信息
     * @param user 用户信息
     */
    fun updateUser(user: LoginResultData): Int {
        try {
            return mDbHelper.getDao(LoginResultData::class.java).update(user)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 查询所有数据
     */
    fun queryAll(): MutableList<LoginResultData>? {
        try {
            return mDbHelper.getDao(LoginResultData::class.java).queryBuilder()
                    .query()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 根据id查询
     */
    fun queryById(id: String): LoginResultData? {
        try {
            val queryBuilder = mDbHelper.getDao(LoginResultData::class.java).queryBuilder()
            return queryBuilder.where()
                    .eq(COLUMN_EUID, id)
                    .queryForFirst()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }

    fun queryMainUser(): LoginResultData? {
        try {
            val queryBuilder = mDbHelper.getDao(LoginResultData::class.java).queryBuilder()
            return queryBuilder.where()
                    .eq(COLUMN_IS_CHILD, false)
                    .queryForFirst()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }

    fun queryCurrentUser(): LoginResultData? {
        try {
            val queryBuilder = mDbHelper.getDao(LoginResultData::class.java).queryBuilder()
            return queryBuilder.where()
                    .eq(COLUMN_IS_CURRENT, true)
                    .queryForFirst()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }

    fun queryAllChild(): MutableList<LoginResultData>? {
        try {
            val dao = mDbHelper.getDao(LoginResultData::class.java)
            return dao.queryForEq(COLUMN_IS_CHILD, true)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }

    companion object {
        const val COLUMN_EUID = "euid"
        const val COLUMN_IS_CHILD = "isChild"
        const val COLUMN_IS_CURRENT = "isCurrent"
    }

}