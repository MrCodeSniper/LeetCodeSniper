package mo.gov.safp.portal.web;

import android.os.Bundle;

import com.alipay.mobile.h5container.api.H5Page;
import com.alipay.mobile.nebula.appcenter.res.H5ResourceManager;
import com.alipay.mobile.nebula.provider.MPH5ErrorPageView;
import com.alipay.mobile.nebula.webview.APWebView;
import com.alipay.mobile.quinox.LauncherApplication;

import mo.gov.safp.portal.R;
import mo.gov.safp.portal.base.CustomApplication;

/**
 * @author CH
 * @date 2021/12/10
 */
public class H5ErrorPageViewImpl implements MPH5ErrorPageView {
    @Override
    public boolean enableShowErrorPage(H5Page h5Page, APWebView apWebView, String s, int i, String s1, String s2, Bundle bundle, Object o) {
        return true;
    }

    @Override
    public void errorPageCallback(H5Page h5Page, APWebView apWebView, String s, int i, String s1, String s2, Bundle bundle, Object o) {
        String raw = H5ResourceManager.readRawFromResource(R.raw.networkprompt, CustomApplication.getInstance().getResources());
        apWebView.loadDataWithBaseURL(s,raw,"text/html","utf-8",s);

    }
}
