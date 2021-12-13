package mo.gov.safp.portal.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.alipay.android.phone.mobilesdk.apm.storage.Lists;

import java.util.ArrayList;
import java.util.List;

public class JustifyTextView extends androidx.appcompat.widget.AppCompatTextView {
    public JustifyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        TextPaint mPaint = getPaint();
        Paint.FontMetrics fm = mPaint.getFontMetrics();

        float baseline = fm.descent - fm.ascent;
        float x = 0;
        //由于系统基于字体的底部来绘制文本，所有需要加上字体的高度。
        float y =  baseline;
        String txt = this.getText().toString();
        //文本自动换行
        String[] texts = autoSplit(txt, mPaint, getWidth() - 5);
//        Log.d( Arrays.toString(texts));
        for(String text : texts) {
            //坐标以控件左上角为原点
            canvas.drawText(text, x, y, mPaint);
            //添加字体行间距
            y += baseline + fm.leading;
        }

    }
    private boolean isFirstLineOfParagraph(int lineStart, String line) {
        return line.length() > 3 && line.charAt(0) == ' ' && line.charAt(1) == ' ';
    }
    /**
     * 分割文本
     * @param content 文本内容
     * @param p  画笔，用来根据字体测量文本的宽度
     * @param width 最大的可显示像素（一般为控件的宽度）
     * @return 一个字符串数组，保存每行的文本
     */
    private String[] autoSplit(String content, Paint p, float width) {
        int length = content.length();
        float textWidth = p.measureText(content);
        if(textWidth <= width) {
            return new String[]{content};
        }
        List<String> result = new ArrayList<>();
        int start = 0, end = 1;

        while(start < length) {
            if(p.measureText(content, start, end) >= width) {
                //文本宽度超出控件宽度时
                result.add(content.substring(start, end));
                start = end;
            }
            if(end == length) {
                //不足一行的文本
                result.add(content.substring(start, end));
                break;
            }
            end += 1;
        }
        return result.toArray(new String[0]);
    }
    private boolean needScale(String line) {
        if (line.length() == 0) {
            return false;
        } else {
            return line.charAt(line.length() - 1) != '\n';
        }
    }
}