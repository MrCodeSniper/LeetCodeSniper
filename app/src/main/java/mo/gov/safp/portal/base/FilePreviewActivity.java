package mo.gov.safp.portal.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsReaderView;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;

import org.jetbrains.annotations.NotNull;

import mo.gov.safp.portal.R;

public class FilePreviewActivity extends FragmentActivity implements ReaderCallback {

    private TbsReaderView mTbsReaderView;

    private String mPreviewFilePath = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBundle();
        setContentView(R.layout.activity_preview_file);
        RelativeLayout rootRl = findViewById(R.id.rl_root);
        mTbsReaderView = new TbsReaderView(this, this);
        rootRl.addView(mTbsReaderView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        displayFile();
    }


    private void initBundle() {
        if (getIntent() != null) {
            mPreviewFilePath = getIntent().getStringExtra(RouteConstant.IntentParam.LOCAL_FILE_PATH);
        }
    }

    private void displayFile() {
        if(!TextUtils.isEmpty(mPreviewFilePath)){
            Bundle bundle = new Bundle();
            bundle.putString("filePath", mPreviewFilePath);
            bundle.putString("tempPath", getApplicationContext().getCacheDir().toString());
            QbSdk.clearAllWebViewCache(this, true);
            boolean result = mTbsReaderView.preOpen(parseFormat(mPreviewFilePath), false);
            if (result) {
                mTbsReaderView.openFile(bundle);
            }
        }
    }

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTbsReaderView != null) {
            mTbsReaderView.onStop();
        }
    }
}
