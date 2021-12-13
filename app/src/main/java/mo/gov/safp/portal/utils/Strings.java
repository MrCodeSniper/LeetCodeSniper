package mo.gov.safp.portal.utils;

import android.text.TextUtils;

/**
 * @author CH
 * @date 2021/9/23
 */
public class Strings {

    public static boolean isNullOrEmpty(String str){
        return (TextUtils.isEmpty(str));
    }

    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                // 全角空格为12288，半角空格为32
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                // 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

}
