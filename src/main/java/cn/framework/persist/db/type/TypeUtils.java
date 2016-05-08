package cn.framework.persist.db.type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * 自定义hibernate数据对象工具
 * 
 * @author : lhc
 * @date : 2015年4月10日 下午5:29:00
 * @version : 1.0
 */
public class TypeUtils {
	private static final String SEPARATOR = "\\|";

	public static List<Long> stringToLongList(String value) {
		if (StringUtils.isBlank(value))
			return new ArrayList<Long>();

		String[] tokens = value.split(SEPARATOR);
		List<Long> list = new ArrayList<Long>(tokens.length);
		for (String token : tokens) {
			try {
				if(StringUtils.isBlank(token)){
					continue;
				}
				list.add(Long.parseLong(token));
			} catch (Exception e) {
				
			}
		}
		return list;
	}

	public static <T> List<T> stringToList(Class<T> type, String value) {
		if (StringUtils.isBlank(value))
			return new ArrayList<T>();

		String[] tokens = value.split(SEPARATOR);
		List<T> list = new ArrayList<T>(tokens.length);
		for (String token : tokens) {
			try {
				if(StringUtils.isBlank(token)){
					continue;
				}
				list.add(toType(type, token));
			} catch (Exception e) {
				
			}
		}
		return list;
	}

	public static <T> Set<T> stringToSet(Class<T> type, String value) {
		if (StringUtils.isBlank(value))
			return new HashSet<T>();

		String[] tokens = value.split(SEPARATOR);
		Set<T> resultSet = new HashSet<T>(tokens.length);
		for (String token : tokens) {
			try {
				if(StringUtils.isBlank(token)){
					continue;
				}
				resultSet.add(toType(type, token));
			} catch (Exception e) {
				
			}
		}
		return resultSet;
	}

	@SuppressWarnings("unchecked")
	private static <T> T toType(Class<T> type, String value) {
		if (String.class.isAssignableFrom(type))
			return (T) value;
		else if (Long.class.isAssignableFrom(type))
			return (T) new Long(value);
		else if (Integer.class.isAssignableFrom(type))
			return (T) new Integer(value);
		throw new IllegalArgumentException("unable to parse to type:" + type);
	}

	public static String listToString(List<?> list) {
		StringBuilder buffer = new StringBuilder();
		for (Object obj : list) {
			buffer.append(String.valueOf(obj)).append("|");
		}
		return buffer.toString();
	}

	public static String setToString(Collection<?> list) {
		StringBuilder buffer = new StringBuilder();
		for (Object obj : list) {
			buffer.append(String.valueOf(obj)).append("|");
		}
		return buffer.toString();
	}
}
