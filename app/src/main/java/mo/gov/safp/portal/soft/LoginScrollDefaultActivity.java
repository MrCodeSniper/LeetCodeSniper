package mo.gov.safp.portal.soft;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import mo.gov.safp.portal.LauncherActivity;
import mo.gov.safp.portal.R;
import mo.gov.safp.portal.utils.AndroidBug5497Workaround;

/**
 * @author CH
 * @date 2021/10/6
 */
public class LoginScrollDefaultActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View decorView = getWindow().getDecorView();
//        //让应用主题内容占用系统状态栏的空间,注意:下面两个参数必须一起使用 stable 牢固的
//        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//        decorView.setSystemUiVisibility(option);
//        //设置状态栏颜色为透明
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_start_login).setOnClickListener(v -> startActivity(new Intent(LoginScrollDefaultActivity.this, LauncherActivity.class)));
        findViewById(R.id.tv_close_login_page).setOnClickListener(v -> finish());
//        AndroidBug5497Workaround.assistActivity(findViewById(android.R.id.content));
    }
}
