package mo.gov.safp.portal.album;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import java.util.ArrayList;

import mo.gov.safp.portal.R;
import mo.gov.safp.portal.sqlite.LoginResultData;
import mo.gov.safp.portal.sqlite.MacaoOrmLiteSqliteOpenHelper;
import mo.gov.safp.portal.sqlite.UserCenterManager;

public class AlblumActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alblum);
        refreshLogin();
    }

    public void mutipleAlblumPic(View view) {
//        openAlbum();
    }

    //启动相册的方法
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri clipData = data.getData();
        if(clipData!=null){
            Glide.with(this)
                    .asBitmap()
                    .load(clipData)
                    .skipMemoryCache(true)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull @NotNull Bitmap resource, @Nullable @org.jetbrains.annotations.Nullable Transition<? super Bitmap> transition) {
                            int maxWidth = resource.getWidth();
                            int maxHeight = resource.getHeight();
                            float ratio = (float) maxHeight/(float) maxWidth;
                            long currentTime = System.currentTimeMillis();
                            String imagePath = "gallery_%s_%s.jpg";
                            File cropImage = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), String.format(imagePath, currentTime, "cropImage"));
                            UCrop.Options options = new UCrop.Options();
                            options.setFreeStyleCropEnabled(true);
                            options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
                            UCrop.of(clipData, Uri.fromFile(cropImage)).withAspectRatio(1, ratio).withOptions(options).start(AlblumActivity.this);
                        }
                    });


        }
    }

    public void login(View view) {
        LoginResultData loginResultData = new LoginResultData();
        loginResultData.euid = "123";
        loginResultData.token = "abcd";
        loginResultData.username = "username";
        UserCenterManager.getInstance().saveLoginUserData(loginResultData);
        refreshLogin();
    }

    public void refreshLogin(){
        TextView tv = findViewById(R.id.tv_unlogin);
        tv.setText(UserCenterManager.getInstance().isLogin()?"登录":"未登录");
    }


}