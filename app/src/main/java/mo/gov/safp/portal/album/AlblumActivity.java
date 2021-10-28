package mo.gov.safp.portal.album;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mo.gov.safp.portal.R;
import mo.gov.safp.portal.sqlite.LoginResultData;
import mo.gov.safp.portal.sqlite.MacaoOrmLiteSqliteOpenHelper;
import mo.gov.safp.portal.sqlite.UserCenterManager;
import mo.gov.safp.portal.utils.MogovFileHelperKt;
import mo.gov.safp.portal.web.MpaasWebAcitvity;

public class AlblumActivity extends AppCompatActivity {

    private Uri mUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alblum);
        refreshLogin();
    }

    public void startCamera(){
//        long currentTime = System.currentTimeMillis();
//        String imagePath = "gallery_%s_%s.jpg";
//        File outputImage = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//                , String.format(imagePath, currentTime, "image"));
//        if (outputImage.exists()) {
//            outputImage.delete();
//        }
//        mUri = MogovFileHelperKt.getUriFromFile(outputImage, this);
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
//        startActivityForResult(intent, 100);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
// 指定拍摄的图片的存储的路径
        File imgFilePath = getExternalCacheDir();
        if (!imgFilePath .exists())
        {
            imgFilePath .mkdirs();
        }
        // 图片名
        File file = new File(imgFilePath, System.currentTimeMillis() + ".jpg");
        mUri = MogovFileHelperKt.getUriFromFile(file, this);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(intent, 100);
    }


    public void mutipleAlblumPic(View view) {
//        openAlbum();
//        LoginResultData.EntitiesBean.EntityTypeBean entityType = UserCenterManager.getInstance().getLoginResult().entities.get(0).entityType;
//        Log.d("chenhong","1111");
//        MpaasWebAcitvity.Companion.start(this);
        startCamera();
    }

    //启动相册的方法
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(intent,2);
    }

    // 将Uri转换成Bitmap，从而判断用户有没有进行拍照（因为在打开相机时就默认创建了文件路径）
    public static Bitmap getBitmapFromUri(Uri uri, Context mContext)
    {
        Bitmap bitmap = null;
        try
        {
            // 读取uri所在的图片
            bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Bitmap bitmap = getBitmapFromUri(mUri,this);
            Log.d("chenhong",requestCode+","+resultCode+"," + bitmap);
        }else{
//            Uri clipData = data.getData();
//            if(clipData!=null){
//                Glide.with(this)
//                        .asBitmap()
//                        .load(clipData)
//                        .skipMemoryCache(true)
//                        .into(new SimpleTarget<Bitmap>() {
//                            @Override
//                            public void onResourceReady(@NonNull @NotNull Bitmap resource, @Nullable @org.jetbrains.annotations.Nullable Transition<? super Bitmap> transition) {
//                                int maxWidth = resource.getWidth();
//                                int maxHeight = resource.getHeight();
//                                float ratio = (float) maxHeight/(float) maxWidth;
//                                long currentTime = System.currentTimeMillis();
//                                String imagePath = "gallery_%s_%s.jpg";
//                                File cropImage = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), String.format(imagePath, currentTime, "cropImage"));
//                                UCrop.Options options = new UCrop.Options();
//                                options.setFreeStyleCropEnabled(true);
//                                options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
//                                UCrop.of(clipData, Uri.fromFile(cropImage)).withAspectRatio(1, ratio).withOptions(options).start(AlblumActivity.this);
//                            }
//                        });
//
//
//            }
        }
    }

    public void login(View view) {
        LoginResultData loginResultData = new LoginResultData();
        loginResultData.euid = "123";
        loginResultData.token = "abcd";
        loginResultData.username = "username";
        ArrayList<LoginResultData.EntitiesBean> entitiesBeans = new ArrayList<>();
        LoginResultData.EntitiesBean bean = new LoginResultData.EntitiesBean();
        bean.address = "1231231";
        bean.entityType = new LoginResultData.EntitiesBean.EntityTypeBean();
        entitiesBeans.add(bean);
        loginResultData.entities = entitiesBeans;
        UserCenterManager.getInstance().saveLoginUserData(loginResultData);
        refreshLogin();
    }

    public void refreshLogin(){
        TextView tv = findViewById(R.id.tv_unlogin);
        tv.setText(UserCenterManager.getInstance().isLogin()?"登录":"未登录");
    }


}