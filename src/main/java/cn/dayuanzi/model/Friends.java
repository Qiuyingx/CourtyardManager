package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/**
 * 好友关系表
 * 
 * @author : leihc
 * @date : 2015年4月15日 上午11:07:23
 * @version : 1.0
 */
@Entity
@Table(name = "t_friends")
public class Friends extends PersistentSupport {
	
	/**
	 * 院子ID
	 */
	private long courtyard_id;

	/**
	 * 好友ID，请求方
	 */
	private long user_id;

	/**
	 * 用户ID，被请求方
	 */
	
	private long friend_id;
	/**
	 * 请求时间
	 */
	private long request_time;
	/**
	 * 处理时间
	 */
	private long operate_time;
	/**
	 * 是否接受     0不是好友    1 请求状态    2 同意好友
	 */
	
	private int accept;
	/**
	 * 验证内容
	 * 
	 */
	private String content; 
	
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(long friend_id) {
		this.friend_id = friend_id;
	}
	public long getRequest_time() {
		return request_time;
	}
	public void setRequest_time(long request_time) {
		this.request_time = request_time;
	}
	public long getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(long operate_time) {
		this.operate_time = operate_time;
	}
	public int getAccept() {
		return accept;
	}
	public void setAccept(int accept) {
		this.accept = accept;
	}
	public long getCourtyard_id() {
		return courtyard_id;
	}
	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}
	
	
	
}
