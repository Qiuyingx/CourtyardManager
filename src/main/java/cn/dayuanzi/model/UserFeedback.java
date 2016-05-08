package cn.dayuanzi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;







import org.hibernate.annotations.Type;

import cn.framework.persist.db.PersistentSupport;


/**
 * 用户反馈
 * @author qiuyingxiang
 *
 */

@Entity
@Table(name = "t_user_feedback")
public class UserFeedback extends PersistentSupport{
	/**
	 * 用户ID
	 */
	private long user_id;

	/**
	 * 院子ID
	 */
	private long courtyard_id;
	/**
	 * 意见反馈内容
	 */
	private String content_back;
	/**
	 * 用户电话
	 */
	private String user_tel;
	/**
	 * 创建反馈时间
	 */
	private long create_time;
	/**
	 *意见反馈 
	 */
	private List<String>  feedBackImages;
	
	public UserFeedback(){
	
	}
	public UserFeedback(long userId,String tel,Long courtyardId,String content){
		this.user_id = userId;
		this.user_tel = tel;
		this.courtyard_id = courtyardId;
		this.content_back = content;
		this.create_time = System.currentTimeMillis();
		
	}
	

	@Type(type = "cn.framework.persist.db.type.StringList")
	@Column(nullable = true, columnDefinition = "varchar(256) default null")
	public List<String> getFeedBackImages() {
		return feedBackImages;
	}
	public void setFeedBackImages(List<String> feedBackImages) {
		this.feedBackImages = feedBackImages;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getCourtyard_id() {
		return courtyard_id;
	}
	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}
	public String getContent_back() {
		return content_back;
	}
	public void setContent_back(String content_back) {
		this.content_back = content_back;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	
	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	
	

}
