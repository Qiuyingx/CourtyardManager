package cn.dayuanzi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * 系统启动时需要执行的操作
 * 
 * @author : leihc
 * @date : 2015年4月13日 下午3:32:52
 * @version : 1.0
 */
public class DoStarting implements InitializingBean {

	
	private static final Logger logger = LoggerFactory.getLogger(DoStarting.class);
	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("开始初始化配置文件");
		YardUtils.initAllConfigs();
	}

}
