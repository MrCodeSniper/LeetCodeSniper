package mo.gov.safp.portal;

import android.os.Bundle;
import android.util.Log;

import com.alipay.mobile.h5container.api.H5Bundle;
import com.alipay.mobile.h5container.api.H5Param;
import com.alipay.mobile.nebula.webview.APWebView;
import com.alipay.mobile.nebulacore.core.H5PageImpl;
import com.alipay.mobile.nebulacore.web.H5WebViewClient;

import java.util.HashMap;
import java.util.Map;

/**
 * author : CH
 * time   : 7/8/21
 * desc   : TODO
 */
public class CustomWebViewClient extends H5WebViewClient {

    public CustomWebViewClient(H5PageImpl page) {
        super(page);
    }

    @Override
    public boolean shouldOverrideUrlLoading(APWebView view, String url) {
        Map<String,String > param = new HashMap<>();
        param.put(H5Param.APP_ID, "11111111");
        param.put(H5Param.LONG_URL, "/index.html#/login");
        Log.d("chenhong",url);
        view.loadUrl("",param);
        return true;
    }
}
