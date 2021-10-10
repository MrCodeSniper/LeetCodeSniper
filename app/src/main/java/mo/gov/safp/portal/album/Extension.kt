package mo.gov.safp.portal.album

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.provider.MediaStore
import androidx.core.app.ActivityCompat.startActivityForResult




/**
 * @author CH
 * @date 2021/10/9
 */

fun Activity.selectMultipleImage(requestCode: Int){
    val intent = Intent()
    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
    intent.type = MIMEType.TYPE_IMAGE
    intent.action = Intent.ACTION_PICK
    intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI //直接打开系统相册  不设置会有选择相册一步（例：系统相册、QQ浏览器相册）
    this.startActivityForResult((Intent.createChooser(intent,"Select Picture")), requestCode)
}

fun Activity.select(requestCode: Int){
    val intentFromGallery = Intent()
    //4.4及以上
    intentFromGallery.action = Intent.ACTION_PICK
    intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
    startActivityForResult(intentFromGallery, requestCode)
}