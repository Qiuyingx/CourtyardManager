package cn.dayuanzi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import cn.framework.persist.db.PersistentSupport;

/**
 * 邀约表
 * 
 * @author : leihc
 * @date : 2015年4月19日 下午3:23:02
 * @version : 1.0
 */
@Entity
@Table(name = "t_invitation_removed")
public class InvitationRemoved extends PersistentSupport {

	/**
	 * 院子ID
	 */
	private long courtyard_id;
	/**
	 * 发邀约的用户ID
	 */
	private long user_id;
	/**
	 * 邀约类型(篮球，足球等等)，和用户注册时可选择的兴趣一致，具体见insterests.xml
	 */
	private int invitation_type;
	
	/**
	 * 活动时间
	 */
	private long activity_time;
	
	/**
	 * 活动地点
	 */
	private String activity_place;
	/**
	 * 内容说明
	 */
	private String content;
	/**
	 * 发布的图片
	 */
	private List<String> image_names;
	
	/**
	 * 创建时间
	 */
	private long create_time;
	/**
	 *  创建讨论组时环信返回的组ID
	 */
	private String group_id;
	/**
	 * 讨论组名称
	 */
	private String group_name;

	public InvitationRemoved(){
		
	}
	
	public InvitationRemoved(Invitation invitation){
		this.id = invitation.getId();
		this.courtyard_id = invitation.getCourtyard_id();
		this.user_id = invitation.getUser_id();
		this.invitation_type = invitation.getInvitation_type();
		this.activity_time = invitation.getActivity_time();
		this.activity_place = invitation.getActivity_place();
		this.content = invitation.getContent();
		this.image_names = invitation.getImage_names();
		this.create_time = invitation.getCreate_time();
		this.group_id = invitation.getGroup_id();
		this.group_name = invitation.getGroup_name();
	}
	
	/**
	 * @return the invitation_type
	 */
	public int getInvitation_type() {
		return invitation_type;
	}

	/**
	 * @param invitation_type the invitation_type to set
	 */
	public void setInvitation_type(int invitation_type) {
		this.invitation_type = invitation_type;
	}

	/**
	 * @return the activity_time
	 */
	public long getActivity_time() {
		return activity_time;
	}

	/**
	 * @param activity_time the activity_time to set
	 */
	public void setActivity_time(long activity_time) {
		this.activity_time = activity_time;
	}

	/**
	 * @return the activity_place
	 */
	public String getActivity_place() {
		return activity_place;
	}

	/**
	 * @param activity_place the activity_place to set
	 */
	public void setActivity_place(String activity_place) {
		this.activity_place = activity_place;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public long getCourtyard_id() {
		return courtyard_id;
	}

	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Type(type = "cn.framework.persist.db.type.StringList")
	@Column(nullable = true, columnDefinition = "varchar(256) default null")
	public List<String> getImage_names() {
		return image_names;
	}

	public void setImage_names(List<String> image_names) {
		this.image_names = image_names;
	}

	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	
	
}
