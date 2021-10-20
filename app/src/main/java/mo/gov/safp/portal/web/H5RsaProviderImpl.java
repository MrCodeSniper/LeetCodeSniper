package mo.gov.safp.portal.web;

import com.alipay.mobile.nebula.provider.H5PublicRsaProvider;

/**
 * 配置离线包验签公钥
 * 生成私钥:
 * openssl genrsa -out private_key.pem 2048
 * 生成公钥：
 * openssl rsa -in private_key.pem -outform PEM -pubout -out public.pem
 * 此处拷贝public.pem内不含头尾的内容，且需要去掉\n
 */
public class H5RsaProviderImpl implements H5PublicRsaProvider {

    public static final String OFFLINE_NEBULA_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5cRPyq67W+Trg8cM35jI" +
            "I3NJxvy+N5DjI8oT5n/msxzB6r7xZqLGWfdVVt58hTSTnvKAuOxwLj63ceAudumj" +
            "fC8tW2ydvxsn1KkLPkHhuVBz4ywKXzae5yKY9rd1NmJ0mye4yDReP/EBQ+7FH1Mi" +
            "7m9HocRqtT77VZMOPvNkuV4rJPPyE5xed7/usLF5oo/YFtHPPNHIaO7xlNC+AWuC" +
            "bWODI/nKaEyWtIdKEzcHsNn7c49Gsz/mnJXIo959sDq/r+6E0pANM88E3IamplC6" +
            "iNU+DdOR2fVY/MqjN9TTql4MtBYZ5SRRPgG63XK9sbaV73R9aNCsDmFP6h6llLKI" +
            "XwIDAQAB";

    @Override
    public String getPublicRsa() {
        return OFFLINE_NEBULA_PUBLIC_KEY;
    }
}