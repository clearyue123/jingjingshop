package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:yue
 * @desc:时间工具类
 * @date:2019.6.10
 */
public class DateUtils {
    
	public static String getDateStrFromTimeStamp(Long dateTime){
		try{
			Date date = new Date(dateTime);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = df.format(date);
			return dateStr;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	public static String getDateStrFromDate(Date date){
		try{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = df.format(date);
			return dateStr;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	public static void main(String[] args) {
		String testDateStr = DateUtils.getDateStrFromDate(new Date());
		System.out.println("时间："+testDateStr);
	}
}
