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

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsReaderView;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;

import org.jetbrains.annotations.NotNull;

import mo.gov.safp.portal.R;

public class FilePreviewFragment extends Fragment implements ReaderCallback {

    private TbsReaderView mTbsReaderView;

    private String mPreviewFilePath = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBundle();
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_preview_file, null, false);
        RelativeLayout rootRl = view.findViewById(R.id.rl_root);
        if (getActivity() != null) {
            mTbsReaderView = new TbsReaderView(getActivity(), this);
            rootRl.addView(mTbsReaderView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        displayFile();
        return view;
    }

    private void initBundle() {
        if (getArguments() != null) {
            mPreviewFilePath = getArguments().getString(RouteConstant.IntentParam.LOCAL_FILE_PATH);
        }
    }

    private void displayFile() {
        if(!TextUtils.isEmpty(mPreviewFilePath)){
            Bundle bundle = new Bundle();
            bundle.putString("filePath", mPreviewFilePath);
            bundle.putString("tempPath", getActivity().getApplicationContext().getCacheDir().toString());
            QbSdk.clearAllWebViewCache(getActivity(), true);
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
    public void onDestroyView() {
        super.onDestroyView();
        if (mTbsReaderView != null) {
            mTbsReaderView.onStop();
        }
    }
}
