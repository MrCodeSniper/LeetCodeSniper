package mo.gov.safp.portal.soft;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import mo.gov.safp.portal.R;
import mo.gov.safp.portal.utils.AndroidBug5497Workaround;

/**
 * @author CH
 * @date 2021/10/6
 */
public class LoginUnScrollActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_unscorll);
        findViewById(R.id.tv_close_login_page).setOnClickListener(v -> finish());
    }
}
