package cn.dayuanzi.util;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.dayuanzi.config.Settings;
import cn.dayuanzi.model.User;
import cn.dayuanzi.pojo.PlatForm;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.ApnsServiceBuilder;

/**
 * 
 * @author : leihc
 * @date : 2015年5月25日
 * @version : 1.0
 */
public class ApnsUtil {

	private Logger logger = LoggerFactory.getLogger(ApnsUtil.class);
	
	private static final ApnsUtil instance = new ApnsUtil();
	
	private ApnsService apnsService;
	
	
	private ApnsUtil(){
		File p12 = YardUtils.getResourcesFile(Settings.APNS_P12_FILENAME);
		ApnsServiceBuilder serviceBuilder = APNS.newService().withCert(p12.getPath(), Settings.APNS_P12_PASSWORD);
		serviceBuilder.withAppleDestination(Settings.APNS_SANDBOX_MODE);
		serviceBuilder.asQueued();
		apnsService = serviceBuilder.build();
	}

	public static ApnsUtil getInstance() {
		return instance;
	}

	public ApnsService getApnsService() {
		return apnsService;
	}
	
	public void send(User target, String content){
		if(target.getLast_login_platform()==PlatForm.IOS.ordinal()&&StringUtils.isNotBlank(target.getLast_login_token())){
			String payload = APNS.newPayload().alertBody(content).build();
			apnsService.push(target.getLast_login_token(), payload);
			logger.info("IOS推送消息，昵称："+target.getNickname()+" token:"+target.getLast_login_token());
		}
	}
}
