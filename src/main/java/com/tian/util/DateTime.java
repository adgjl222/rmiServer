package com.tian.util;

import java.text.SimpleDateFormat;
import java.util.Random;

public class DateTime {

    public static String getDateTime(){

        Random random = new Random();//生成伪随机数流 最多可提供32个伪随机生成的位

        //时间格式为 年-月-日
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //小时的毫秒数 生成0-24区间的数
        long h = 3600000 * random.nextInt(24);

        //天的毫秒数 小时的毫秒数数*0-60区间的数
        long day = h * random.nextInt(60);

        //获得系统时间，单位为毫秒加上生成的经计算后的小时的毫秒数和天数的毫秒数
        long ts = System.currentTimeMillis() + h + day;

        //将时间转换为指定格式
        String dateTime = simpleDateFormat.format(ts);
        System.out.println(dateTime.length());


        return dateTime;
    }

    public static Long getTime() {
        long dateTime = System.currentTimeMillis();
        return dateTime;
    }
}



