package mo.gov.safp.portal.album;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MIMEType {
    public static final String TYPE_ALL = "*/*";
    public static final String TYPE_IMAGE = "image/*";
    public static final String TYPE_PDF = "application/pdf";
    public static final String TYPE_TEXT = "text/plain";
    public static final String TYPE_AUDIO = "audio/*";
    public static final String TYPE_VIDEO = "video/*";
    public static final String TYPE_CHM = "application/x-chm";
    public static final String TYPE_APK = "application/vnd.android.package-archive";
    public static final String TYPE_PPT = "application/vnd.ms-powerpoint";
    public static final String TYPE_EXCEL = "application/vnd.ms-excel";
    public static final String TYPE_WORD = "application/msword";
    public static final String TYPE_PKG = "application/x-wav";
    public static final String TYPE_RTF = "application/rtf";

    @StringDef({TYPE_IMAGE, TYPE_PDF, TYPE_TEXT, TYPE_AUDIO, TYPE_VIDEO, TYPE_CHM, TYPE_APK, TYPE_PPT, TYPE_EXCEL, TYPE_WORD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }
}
