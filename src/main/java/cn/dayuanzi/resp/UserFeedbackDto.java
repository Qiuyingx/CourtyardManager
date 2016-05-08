package cn.dayuanzi.resp;

import java.util.ArrayList;
import java.util.List;

import cn.dayuanzi.config.Settings;
import cn.dayuanzi.model.UserFeedback;
import cn.dayuanzi.util.DateTimeUtil;

/**
 * 
 * @author : leihc
 * @date : 2015年6月2日
 * @version : 1.0
 */
public class UserFeedbackDto {

	private String nickname;
	private String tel;
	private String content;
	private String createTime;
	private String courtyardName;
	private List<String>  feedBackImages;
	
	public UserFeedbackDto(UserFeedback userFeedback){
		this.tel = userFeedback.getUser_tel();
		this.content = userFeedback.getContent_back();
		this.createTime = DateTimeUtil.formatDateTime(userFeedback.getCreate_time());
		this.feedBackImages = new ArrayList<String>();
		for(String img : userFeedback.getFeedBackImages()){
			feedBackImages.add(Settings.APP_HOME_URL+img);
		}
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCourtyardName() {
		return courtyardName;
	}

	public void setCourtyardName(String courtyardName) {
		this.courtyardName = courtyardName;
	}

	public List<String> getFeedBackImages() {
		return feedBackImages;
	}

	public void setFeedBackImages(List<String> feedBackImages) {
		this.feedBackImages = feedBackImages;
	}
	
	
}
