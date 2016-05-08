package cn.dayuanzi.resp;

import cn.dayuanzi.model.ActivitySignUp;
import cn.dayuanzi.util.DateTimeUtil;

/**
 * 活动报名数据
 * 
 * @author : leihc
 * @date : 2015年6月16日
 * @version : 1.0
 */
public class ActivitySignDto {

	/**
	 * 报名者所在社区
	 */
	private String courtyardName;
	/**
	 * 报名人姓名
	 */
	private String userName;
	/**
	 * 报名联系电话
	 */
	private String tel;
	/**
	 * 备注
	 */
	private String content;
	/**
	 * 报名时间
	 */
	private String signTime;
	
	public ActivitySignDto(ActivitySignUp signUp){
		this.userName = signUp.getName();
		this.tel = signUp.getTel();
		this.content = signUp.getContent();
		this.signTime = DateTimeUtil.formatDateTime(signUp.getCreate_time());
	}
	
	public String getCourtyardName() {
		return courtyardName;
	}
	public void setCourtyardName(String courtyardName) {
		this.courtyardName = courtyardName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSignTime() {
		return signTime;
	}
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	
	
}
