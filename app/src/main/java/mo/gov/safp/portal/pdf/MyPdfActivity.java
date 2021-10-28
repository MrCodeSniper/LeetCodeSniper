package mo.gov.safp.portal.pdf;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.demon.js_pdf.view.DWebView;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.gyf.immersionbar.ImmersionBar;


import java.io.File;

import mo.gov.safp.portal.R;
import mo.gov.safp.portal.nav.NavigationHelper;

import static android.os.Environment.DIRECTORY_DOWNLOADS;


/**
 * PDF预览
 *
 * @author ch
 */
public class MyPdfActivity extends AppCompatActivity {

    private static final String TAG = "MyPdfActivity";
    private PDFView mPdfView;
    private WebView mWebView;

    public static void start(Context context){
        context.startActivity(new Intent(context,MyPdfActivity.class));
    }

    public void initStatusBar(){
        ImmersionBar.with(this).statusBarColor(android.R.color.transparent)
                .fitsSystemWindows(false)
                .statusBarDarkFont(false)
                .init();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        NavigationHelper.onCreate(this);
        initStatusBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pdf);
        mPdfView = (PDFView) findViewById(R.id.pdfView);
        String path = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getAbsolutePath();
        String filePath = path +"/a.pdf";
        String filePath2 = path +"/safp-proj-095-3019.pdf";
        String file = "file:///android_asset/demo.pdf";
//        loadPdf(filePath);
        mWebView = findViewById(R.id.jsWebView);
        WebSettings webSettings =mWebView.getSettings();
        //支持缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        //自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        //允许js 并读取文件
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        display(filePath2);
    }


    void display(String url) {
        mWebView.loadUrl("file:///android_asset/viewer.html?file=" + url);
    }


    private void loadPdf(String pdfPath) {
        try {
            File file = new File(pdfPath);
            int pageNum = 0;
            mPdfView.fromFile(file)
                    .defaultPage(pageNum)
                    //是否允许翻页，默认是允许翻页
                    .enableSwipe(true)
                    //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
//                    .swipeHorizontal(false)
                    // 渲染风格（就像注释，颜色或表单）
//                    .enableAnnotationRendering(true)
                    .enableAntialiasing(true)
                    .onLoad(nbPages -> {})
                    .scrollHandle(new DefaultScrollHandle(this))
                    .onPageError((page, t) -> {})
                    .load();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }

}
