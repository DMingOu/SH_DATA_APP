package com.qg.sh_data_app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static boolean compare(String start, String end) throws ParseException {
        // 如果想比较日期则写成"yyyy-MM-dd"就可以了
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 注意时间格式为24小时
        // 将字符串形式的时间转化为Date类型的时间
        Date a = sdf.parse(start);
        Date b = sdf.parse(end);
        if (a.getTime() - b.getTime() < 0) {
            return true;
        } else {
            return false;
        }
    }
    public static String getNextTime(){
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.setTime(new Date());
        tomorrow.add(Calendar.DAY_OF_MONTH,1);
        return new SimpleDateFormat("yyyy-MM-dd").format(tomorrow.getTime());
    }
}
