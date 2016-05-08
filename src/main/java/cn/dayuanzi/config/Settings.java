package cn.dayuanzi.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.dayuanzi.exception.ConfigException;
import cn.dayuanzi.util.YardUtils;

/**
 * 
 * @author : leihc
 * @date : 2015年4月13日 下午2:46:21
 * @version : 1.0
 */
public class Settings implements IConfig {

	private Logger logger = LoggerFactory.getLogger(Settings.class);
	
	/**
	 * 当前消息推送到APNS服务器，是沙箱模式（测试）
	 */
	public static boolean APNS_SANDBOX_MODE;
	/**
	 * APNS服务器证书密码
	 */
	public static String APNS_P12_PASSWORD;
	public static String APNS_P12_FILENAME;
	public static String APP_HOME_URL;
	/**
	 * 图片目录
	 */
	public static File IMAGES_DIRETORY;
	/**
	 * 商店图片
	 */
	public static File SHOP_IMAGE_DIRE;
	/**
	 * 活动的图片目录
	 */
	public static File ACTIVITY_IMAGE_DIRE;
	
	/**
	 * @see cn.dayuanzi.config.IConfig#init()
	 */
	@Override
	public void init() {
		
		try{
			Properties config = new Properties();
			File file = YardUtils.getResourcesFile("settings.properties");
			config.load(new InputStreamReader(new FileInputStream(file)));
			APNS_P12_PASSWORD = config.getProperty("APNS_P12_PASSWORD");
			APNS_P12_FILENAME = config.getProperty("APNS_P12_FILENAME");
			APNS_SANDBOX_MODE = Boolean.parseBoolean(config.getProperty("APNS_SANDBOX_MODE"));
			APP_HOME_URL = config.getProperty("APP_HOME_URL");
			String imagesDire = config.getProperty("IMAGES_DIRETORY");
			if(StringUtils.isBlank(imagesDire)){
				throw new ConfigException("系统的图片目录未设置。");
			}
			IMAGES_DIRETORY = new File(imagesDire);
			if(!IMAGES_DIRETORY.exists() || !IMAGES_DIRETORY.isDirectory()){
				IMAGES_DIRETORY.mkdirs();
			}
			
			SHOP_IMAGE_DIRE = new File(IMAGES_DIRETORY,"ShopImage");
			if(!SHOP_IMAGE_DIRE.exists()||!SHOP_IMAGE_DIRE.isDirectory()){
				SHOP_IMAGE_DIRE.mkdir();
			}
			logger.info("商店图片保存目录："+SHOP_IMAGE_DIRE.getAbsolutePath());
			
			ACTIVITY_IMAGE_DIRE = new File(IMAGES_DIRETORY,"ActivityImage");
			if(!ACTIVITY_IMAGE_DIRE.exists()||!ACTIVITY_IMAGE_DIRE.isDirectory()){
				ACTIVITY_IMAGE_DIRE.mkdir();
			}
			logger.info("活动图片保存目录："+ACTIVITY_IMAGE_DIRE.getAbsolutePath());

		}catch(Exception ex){
			ex.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * @see cn.dayuanzi.config.IConfig#reload()
	 */
	@Override
	public void reload() {
		// TODO Auto-generated method stub
		this.init();
	}

}
