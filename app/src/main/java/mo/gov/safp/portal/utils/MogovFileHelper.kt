package mo.gov.safp.portal.utils

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.StatFs
import androidx.core.content.FileProvider
import mo.gov.safp.portal.base.CustomApplication
import java.io.File
import java.math.BigInteger

/**
 * @ClassName MogovFileHelper
 * @Description 文件相关工具
 * @Author CH
 * @Date 4/27/21 10:46 AM
 */

val FILE_AUTHORITIES: String =
    AppInfoUtils.getPackageName(CustomApplication.getInstance().applicationContext).toString() + ".common.fileprovider"


fun File.getUriFromFile(context: Context): Uri {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        FileProvider.getUriForFile(context, FILE_AUTHORITIES, this)
    } else {
        //小于android 版本7.0（24）的场合
        Uri.fromFile(this)
    }
}

fun getFreeDiskStorageSync(): Double {
    return try {
        val rootDir = StatFs(Environment.getRootDirectory().absolutePath)
        val dataDir = StatFs(Environment.getDataDirectory().absolutePath)
        val intApiDeprecated = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2
        val rootAvailableBlocks: Long = getTotalAvailableBlocks(rootDir, intApiDeprecated)
        val rootBlockSize: Long = getBlockSize(rootDir, intApiDeprecated)
        val rootFree: Double =
            BigInteger.valueOf(rootAvailableBlocks).multiply(BigInteger.valueOf(rootBlockSize))
                .toDouble()
        val dataAvailableBlocks: Long = getTotalAvailableBlocks(dataDir, intApiDeprecated)
        val dataBlockSize: Long = getBlockSize(dataDir, intApiDeprecated)
        val dataFree: Double =
            BigInteger.valueOf(dataAvailableBlocks).multiply(BigInteger.valueOf(dataBlockSize))
                .toDouble()
        rootFree + dataFree
    } catch (e: Exception) {
        (-1).toDouble()
    }
}

fun getTotalAvailableBlocks(dir: StatFs, intApiDeprecated: Boolean): Long {
    return if (intApiDeprecated) dir.availableBlocksLong else dir.availableBlocks.toLong()
}

fun getBlockSize(dir: StatFs, intApiDeprecated: Boolean): Long {
    return if (intApiDeprecated) dir.blockSizeLong else dir.blockSize.toLong()
}


fun deleteFile(var0: File): Boolean {
    return if (!var0.exists()) {
        true
    } else {
        if (var0.isDirectory) {
            var var1: Array<File>
            val var2 = var0.listFiles().also { var1 = it }.size
            for (var3 in 0 until var2) {
                deleteFile(var1[var3])
            }
        }
        var0.delete()
    }
}
