package cn.dayuanzi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author : lhc
 * @date : 2015年4月10日 下午5:47:44
 * @version : 1.0
 */
public class LogUtil {
	/**
	 * 错误日志
	 */
	private static final Logger exceptionLog = LoggerFactory.getLogger("dayuanzi.exceptionLog");
	
	/**
	 * 记录错误日志
	 * 
	 * @param message
	 */
	public static void logException(String message, Exception ex){
		exceptionLog.debug(message, ex);
	}
}
