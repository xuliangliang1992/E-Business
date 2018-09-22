package com.jinlong.ebusiness.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * @aucthor xll
 * @date 2018/9/23 0023
 */
public class TextUtil {

    /**
     * 设置部分字体颜色
     * @param content
     * @param bgStart
     * @param bgEnd
     * @param bgColor
     * @param fontStart
     * @param fontEnd
     * @param fontColor
     * @param textView
     */
    public static void setPartOfFontColor(String content, int bgStart, int bgEnd, int bgColor,
                                                 int fontStart, int fontEnd, int fontColor,
                                                 TextView textView) {
      /*  String str="设置TextView部分字体颜色以及背景颜色!";
        int bstart=str.indexOf("背景");
        int bend=bstart+"背景".length();
        int fstart=str.indexOf("字体颜色");
        int fend=fstart+"字体颜色".length();*/
        SpannableStringBuilder style = new SpannableStringBuilder(content);
        style.setSpan(new BackgroundColorSpan(bgColor), bgStart, bgEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(fontColor), fontStart, fontEnd, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(fontColor), fontStart, fontEnd, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(style);

    }


  /*  AbsoluteSizeSpan(int size) ---- 设置字体大小，参数是绝对数值，相当于Word中的字体大小
    RelativeSizeSpan(float proportion) ---- 设置字体大小，参数是相对于默认字体大小的倍数，比如默认字体大小是x, 那么设置后的字体大小就是x*proportion，这个用起来比较灵活，proportion>1就是放大(zoom in), proportion<1就是缩小(zoom out)
    ScaleXSpan(float proportion) ---- 缩放字体，与上面的类似，默认为1,设置后就是原来的乘以proportion，大于1时放大(zoon in)，小于时缩小(zoom out)
    BackgroundColorSpan(int color) ----背景着色，参数是颜色数值，可以直接使用android.graphics.Color里面定义的常量，或是用Color.rgb(int, int, int)
    ForegroundColorSpan(int color) ----前景着色，也就是字的着色，参数与背景着色一致TypefaceSpan(String family) ----字体，参数是字体的名字比如“sans", "sans-serif"等
    StyleSpan(Typeface style) -----字体风格，比如粗体，斜体，参数是android.graphics.Typeface里面定义的常量，如Typeface.BOLD，Typeface.ITALIC等等。StrikethroughSpan----如果设置了此风格，会有一条线从中间穿过所有的字，就像被划掉一样
*/

}
