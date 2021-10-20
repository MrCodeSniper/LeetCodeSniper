package mo.gov.safp.portal;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import mo.gov.safp.portal.utils.RSAUtil;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TEST_CH";

    private FusedLocationProviderClient fusedLocationClient;

    private LocationRequest locationRequest;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initText(intent);
    }

    private void initText(Intent intent) {
        String content = intent.getStringExtra("passData");
        if (TextUtils.isEmpty(content)) {
            content = "";
        }
        Log.d(TAG, "content:" + content);
        TextView tvSecret = findViewById(R.id.tv_secret);
        tvSecret.setText("當前密文:\n" + content);
        TextView tvContent = findViewById(R.id.tv_content);
        try {
            byte[] decodeContent = Base64.decode(content, Base64.DEFAULT);
            byte[] str1 = RSAUtil.decryptByPrivateKey(decodeContent, RSAUtil.PRIVATE_KEY_NAME);
            tvContent.setText("解密後:\n" + new String(str1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvSecret = findViewById(R.id.tv_secret);
        TextView tvContent = findViewById(R.id.tv_content);
        initText(getIntent());
        EditText etLink = findViewById(R.id.link);
        EditText et = findViewById(R.id.input);
        findViewById(R.id.btn_route).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName comp = new ComponentName("mo.gov.safp.portal", "com.alibaba.yihutong.common.nav.MogovCommunicateActivity");
                intent.setComponent(comp);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("url", etLink.getText().toString().trim());
                intent.putExtra("packageName", "mo.gov.safp.portalch");
                intent.putExtra("activityName", "mo.gov.safp.portal.MainActivity");
                intent.setAction("android.intent.action.VIEW");
                startActivity(intent);
            }
        });
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        findViewById(R.id.btn_decrept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String encodeContent2 = "VLzCEsXcJSuRoJehSLaytir7napFPw7NhV0R5M2lPhbISXIsuQfRYV2DKW4GhQcvPCpw+fVCZs37\n" +
                        "sr6RnLJI6r+N/aZ0gexYsFmSOR8PdWhQZ9NBlirdcJ9q6/86xv4zPUbnOQRF0h1FqlRobs9vBiR/\n" +
                        "xU5gxSIy+RnA6NkCZJKXsnzZi+yF15Htjyynsg7/VNf+8ZHFGfJw/8udv0FAXkVTbB4akQCBELqu\n" +
                        "iO6TdLshgIdeSHINmeR5h/HX6D+wFqWKqPR+yOyQkTteCtoMqKRjYBh+VSYnYeHHsHt6SddG9iID\n" +
                        "1CwR75yDl/ttrngq1prqdlKWCQGdlROhYJb/kw==";
                try {
                    byte[] decodeBytes = Base64.decode(encodeContent2, 0);
                    byte[] decryptedBytes = RSAUtil.decryptByPrivateKey(decodeBytes, RSAUtil.PRIVATE_KEY_NAME);

                    tvSecret.setText("當前密文:\n" + encodeContent2);
                    tvContent.setText("解密後:\n" + new String(decryptedBytes));
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Update UI with location data
                    // ...
                }
            }
        };
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Log.d("chenhong111", "onSuccess");
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            Log.d("chenhong111", "success");
                        }
                    }
                });

        startLocationUpdates();
    }

    private LocationCallback locationCallback;

    @Override
    protected void onResume() {
        super.onResume();
//        if (requestingLocationUpdates) {
//            startLocationUpdates();
//        }
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }
}