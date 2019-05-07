package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author  yue
 * @date 2019.4.12
 */
public class TimeUtils {

    /**
     * 获取当前时间
     * @param formatter "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static  String getNowFomatter(String formatter){
        SimpleDateFormat df = new SimpleDateFormat(formatter);
        // 输出字符串
        System.out.println(df.format(new Date()));
        return df.format(new Date());
    }

    /**
     * 将时间戳取小时分钟
     * @param unixTime
     * @return
     */
    public static String getHourMin(String unixTime){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date(Long.parseLong(unixTime));
            String strDate =simpleDateFormat.format(date);
            String targetTime = strDate.substring(strDate.lastIndexOf(" ")+1);
            return targetTime;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 将时间戳取日期
     * @param unixTime
     * @return
     */
    public static String getYearDay(String unixTime){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date(Long.parseLong(unixTime));
            String strDate =simpleDateFormat.format(date);
            String targetTime = strDate.substring(0,strDate.lastIndexOf(" "));
            return targetTime;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
