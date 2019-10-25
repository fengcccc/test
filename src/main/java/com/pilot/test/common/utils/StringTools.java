package com.pilot.test.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class StringTools {

    /**
     * 生成6位随机字符
     */
    public static String getStringRamdom(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
            val += (char) (random.nextInt(26) + temp);
        }
        return val;
    }

    /**
     * 时间差
     */
    public static long difTime(String stime, String etime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date sdate = null;
        Date eDate = null;
        try {
            sdate = df.parse(stime);
            eDate = df.parse(etime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long betweendays = (long) ((eDate.getTime() - sdate.getTime()) / (1000 * 60 * 60 * 24) + 0.5);//天数间隔
        System.out.println(betweendays);
        return betweendays;
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        String stime = "2019-09-19";
        String etime = "2019-09-23";
        int result = stime.compareTo(etime);
        System.out.println(result);
    }

}
