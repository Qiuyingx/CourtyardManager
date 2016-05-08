package cn.dayuanzi.resp;

import cn.dayuanzi.config.Settings;
import cn.dayuanzi.model.ValidateUser;
import cn.dayuanzi.util.DateTimeUtil;

/**
 * 
 * @author : leihc
 * @date : 2015年6月1日
 * @version : 1.0
 */
public class ValidateInfoResp extends Resp {

	private String courtyardName;
	private int validateType;
	private String validateTypeDescription;
	private String submitTime;
	private String append;
	private boolean review;
	
	public ValidateInfoResp(ValidateUser validateUser,String courtyardName){
		this.courtyardName = courtyardName;
		if(validateUser.getValidate_type()==0){
			validateTypeDescription = "手机验证";
		}
		else if(validateUser.getValidate_type()==1){
			validateTypeDescription = "图片验证";
			review = validateUser.getValidate_status()==0;
		}else if(validateUser.getValidate_type()==2){
			validateTypeDescription = "code码验证";
		}else if(validateUser.getValidate_type()==3){
			validateTypeDescription = "邀请码验证";
		}else{
			validateTypeDescription = "未知";
		}
		validateType = validateUser.getValidate_type();
		submitTime = DateTimeUtil.formatDateTime(validateUser.getCreate_time());
		if(validateUser.getValidate_type()==1){
			append = Settings.APP_HOME_URL+validateUser.getAppend();
		}else{
			append = "";
		}
		
	}
	
	public String getCourtyardName() {
		return courtyardName;
	}
	public void setCourtyardName(String courtyardName) {
		this.courtyardName = courtyardName;
	}
	
	public int getValidateType() {
		return validateType;
	}

	public void setValidateType(int validateType) {
		this.validateType = validateType;
	}

	public String getValidateTypeDescription() {
		return validateTypeDescription;
	}

	public void setValidateTypeDescription(String validateTypeDescription) {
		this.validateTypeDescription = validateTypeDescription;
	}

	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getAppend() {
		return append;
	}
	public void setAppend(String append) {
		this.append = append;
	}
	public boolean isReview() {
		return review;
	}
	public void setReview(boolean review) {
		this.review = review;
	}
	
}
