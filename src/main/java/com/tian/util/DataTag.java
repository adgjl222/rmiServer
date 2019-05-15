package com.tian.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * 自定义jsp时间标签
 */
public class DataTag extends TagSupport {

    private String value;

    @Override
    public int doStartTag() throws JspException {
        String vv = "" + value;
        try {
            //.trim函数：取出空格 Long.valueOf():将参数转为long的包装类Long
            long time = Long.valueOf(vv.trim());
            //Calendar.getInstance() 可以设置特定的年月日和时区等
            Calendar c = Calendar.getInstance();
            //从给定的long值设置日历的当前时间毫秒数
            c.setTimeInMillis(time);
            //设置时间格式 24小时制 HH:mm:ss   12小时制 hh:mm:ss

            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            //给定String类型变量s 值为根据给定long值设置的时间毫秒数 转换为的 需要的时间格式
            String s = dateformat.format(c.getTime());
            //pageContext：表示的是一个jsp页面的上下文，而且功能强大，几乎可以操作各种内置对象
            //pageContext.getOut() :该方法返回一个JspWriter类的实例对象，也就是JSP内置对象--out对象，可以往客户端写入输入流。
            pageContext.getOut().write(s);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //doStartTag()方法是遇到标签开始时执行 其合法的返回值是EVAL_BODY_INCLUDE与SKIP_BODY,前者表示将显示标签间的内容，后者表示不显示标签间的内容
        return super.doStartTag();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        long time = Long.valueOf(a);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        SimpleDateFormat dateformat =new SimpleDateFormat("MM-dd HH:mm");
        String s = dateformat.format(c.getTime());
        System.out.println(s);
    }


}
