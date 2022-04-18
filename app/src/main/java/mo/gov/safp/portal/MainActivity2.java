package mo.gov.safp.portal;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mpaas.nebula.adapter.api.MPNebula;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_softtoken);
    }

    public void nebulaStart(View view) {
        MPNebula.startUrl("https://ss-ehr-web01.ssm.gov.mo/infrs/home");
    }

}