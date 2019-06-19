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
    
	/**
	 * 返回特定df日期
	 * @param dateFormat
	 * @param date
	 * @return
	 */
	public static String getDateStrFromDf(String dateFormat,Date date){
		try{
			DateFormat df = new SimpleDateFormat(dateFormat);
			String dateStr = df.format(date);
			return dateStr;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
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
		String testDateStr = DateUtils.getDateStrFromDf("yyyyMMdd", new Date());
		System.out.println("时间："+testDateStr);
	}
}
