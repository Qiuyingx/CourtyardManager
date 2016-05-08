package cn.dayuanzi.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

/**
 * 
 * @author : leihc
 * @date : 2015年5月8日
 * @version : 1.0
 */
public class DateTimeUtil {

	private static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm";
	
	private static String[] patterns = new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm","yyyy-MM-dd"};
	
	private static Map<String, SimpleDateFormat> formats = new HashMap<String, SimpleDateFormat>();
	
	static{
		for(String pattern : patterns){
			formats.put(pattern, new SimpleDateFormat(pattern) );
		}
	}
	/**
	 * 返回格式化时间，默认格式
	 * @param dateTime
	 * @return
	 */
	public static String formatDateTime(Object dateTime){
		return formatDateTime(dateTime, DEFAULT_PATTERN);
	}
	
	/**
	 * 返回格式化日期时间
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String formatDateTime(Object dateTime, String pattern){
		return formats.get(pattern).format(dateTime);
	}
	
	/**
	 * 获取毫秒数
	 * 
	 * @param dateTime
	 * @return
	 */
	public static long getTime(String dateTime,String pattern){
		long result = 0;
		try {
			result = formats.get(pattern).parse(dateTime).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Date parse(String dateTime, String pattern){
		Date result = null;
		try {
			result = formats.get(pattern).parse(dateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 判断是不是今天
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date){
		if(date==null) return false;
		return DateUtils.isSameDay(new Date(), date);
	}
	/**
	 * 多少分钟前发帖
	 * @param dateTime
	 * @return
	 */
	public static String getDisplay(long dateTime){
		Date date = new Date(dateTime);
		if(!isToday(date)){
			return formatDateTime(date,"yyyy-MM-dd HH:mm");
		}
		long time = System.currentTimeMillis() - dateTime;
		long mm = time / 60 / 1000;
		long ss= time/1000;
		
		if(mm < 60){
			if(mm<1){
				return ss+"秒前";
			}
			return mm+"分钟前";
		}
		return (mm/60)+"小时前";
	}
	
	public static String getAgeData(Timestamp birthday) {
		 	if(birthday==null){
		 		return "";
		 	}
			Calendar cal = Calendar.getInstance();

	        if (cal.before(birthday)) {
	            throw new IllegalArgumentException(
	                "The birthDay is before Now.It's unbelievable!");
	        }

	        int yearNow = cal.get(Calendar.YEAR);
	        int monthNow = cal.get(Calendar.MONTH)+1;
	        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
	       
	        cal.setTime(birthday);
	        int yearBirth = cal.get(Calendar.YEAR);
	        int monthBirth = cal.get(Calendar.MONTH);
	        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
	        int age = yearNow - yearBirth;
	        if (monthNow <= monthBirth) {
	            if (monthNow == monthBirth) {
	                if (dayOfMonthNow < dayOfMonthBirth) {
	                    age--;
	                }
	            } else {
	                age--;
	            }
	        }
	        return age +"";
	    
	}
	
	public static void main(String[] args) {
		System.out.println(getDisplay(System.currentTimeMillis()-2*60*1000));
		
	}

	
}
