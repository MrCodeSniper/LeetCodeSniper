package mo.gov.safp.portal.web;

import com.alipay.mobile.nebula.provider.H5ResProvider;

import java.io.InputStream;

/**
 * @author CH
 * @date 2021/10/20
 */
public class MpaasResourceProvider implements H5ResProvider {

    @Override
    public InputStream getResource(String url) {
        return MpaasWebExtensionKt.getUrlInputStream(url);
    }

    @Override
    public boolean contains(String s) {
        return false;
    }
}
