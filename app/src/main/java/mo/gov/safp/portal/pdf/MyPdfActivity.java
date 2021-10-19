package mo.gov.safp.portal.pdf;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;


import java.io.File;

import mo.gov.safp.portal.R;

import static android.os.Environment.DIRECTORY_DOWNLOADS;


/**
 * PDF预览
 *
 * @author ch
 */
public class MyPdfActivity extends AppCompatActivity {

    private static final String TAG = "MyPdfActivity";
    private PDFView mPdfView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pdf);
        mPdfView = (PDFView) findViewById(R.id.pdfView);
        String path = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getAbsolutePath();
        String filePath = path +"/safp-proj-095-3019.pdf";
        loadPdf(filePath);
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
