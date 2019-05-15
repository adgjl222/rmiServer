package com.tian.util;

import java.util.Random;

public class DataUtils {



    /**
     * 随机 n位全部为数字的字符串
     * @param n
     * @return
     */
    public static String getNumber(int n) {
        String sources = "0123456789";
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < n; j++) {
            flag.append(sources.charAt(rand.nextInt(9)) + "");
        }
        return flag.toString();
    }
    /**
     * 随机返回五位数
     * @return
     */
    public static int getIntNumber(){
        Random random = new Random();

        int num = random.nextInt(99999);
        if (num < 10000){
            num += 10000;
        }
        return  num;
    }


    public static String getMsgStatus() {

        String msgStatus = null;
        Random random = new Random();
        String[] strs = {"true", "false"};
        int num = random.nextInt(strs.length);
        msgStatus  = strs[num];
        return  msgStatus;
    }
}
