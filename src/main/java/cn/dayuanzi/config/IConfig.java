package cn.dayuanzi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author : leihc
 * @date : 2015年4月13日 上午9:10:50
 * @version : 1.0
 */
public interface IConfig {

	final static Logger logger = LoggerFactory.getLogger(IConfig.class);
	
	/**
	 * 配置文件初始化
	 */
	public void init();
	
	/**
	 * 重新加载
	 */
	public void reload();
}
