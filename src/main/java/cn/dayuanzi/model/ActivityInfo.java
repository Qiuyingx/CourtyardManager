package cn.dayuanzi.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 活动信息表
 * @author : leihc
 * @date : 2015年5月26日
 * @version : 1.0
 */
@Entity
@Table(name = "t_activity_info")
public class ActivityInfo extends VersionPersistentSupport{

	/**
	 * 活动社区ID
	 */
	private long courtyard_id;
	/**
	 * 活动标题
	 */
	private String activity_title;
	/**
	 * 活动有效期的起始时间
	 */
	private Timestamp startTime;
	/**
	 * 活动有效期的截止时间
	 */
	private Timestamp endTime;
	/**
	 * banner图片地址
	 */
	private String image; 
	/**
	 * 活动内容
	 */
	private String content;
	/**
	 * 活动发布时间
	 */
	private long createTime;
	/**
	 * 不能报名
	 */
	private boolean signDisable;
	/**
	 * 人数限制
	 */
	private int peoplesLimit;
	/**
	 * 发布的管理员ID
	 */
	private long managerId;
	
	public ActivityInfo(){
		
	}
	
	public ActivityInfo(long courtyardId, String activityTitle,String content, Timestamp startTime, Timestamp endTime, long managerId){
		this.courtyard_id = courtyardId;
		this.activity_title = activityTitle;
		this.content = content;
		this.startTime = startTime;
		this.endTime = endTime;
		this.managerId = managerId;
		this.createTime = System.currentTimeMillis();
	}
	
	
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getCourtyard_id() {
		return courtyard_id;
	}
	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}
	public String getActivity_title() {
		return activity_title;
	}
	public void setActivity_title(String activity_title) {
		this.activity_title = activity_title;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getManagerId() {
		return managerId;
	}
	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	public boolean isSignDisable() {
		return signDisable;
	}

	public void setSignDisable(boolean signDisable) {
		this.signDisable = signDisable;
	}

	public int getPeoplesLimit() {
		return peoplesLimit;
	}

	public void setPeoplesLimit(int peoplesLimit) {
		this.peoplesLimit = peoplesLimit;
	}
	
}
