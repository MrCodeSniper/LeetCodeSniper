package mo.gov.safp.portal.soft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import mo.gov.safp.portal.WindowSoftInputActivity;
import mo.gov.safp.portal.R;

/**
 * @author CH
 * @date 2021/10/6
 */
public class LoginScrollAdjustResizeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.tv_close_login_page).setOnClickListener(v -> finish());
        findViewById(R.id.btn_start_login).setOnClickListener(v -> startActivity(new Intent(LoginScrollAdjustResizeActivity.this, WindowSoftInputActivity.class)));
    }
}
