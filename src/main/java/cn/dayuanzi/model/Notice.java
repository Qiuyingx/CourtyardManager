package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 通知信息表
 * 
 * @author : leihc
 * @date : 2015年4月15日 上午11:15:22
 * @version : 1.0
 */
@Entity
@Table(name = "t_notice")
public class Notice extends VersionPersistentSupport {

	/**
	 * 通知类型 0 系统通知 1 社区公告 2 紧急通知
	 */
	private int notice_type;
	
	/**
	 * 院子ID,为0表示所有社区
	 */
	private long courtyard_id;
	/**
	 * 发给指定用户的通知，为0表示所有用户
	 */
	private long user_id;
	/**
	 * 通知内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private long create_time;
	
	
	public Notice(){
		
	}
	
	public Notice(int notice_type, long courtyard_id, String content){
		this.notice_type = notice_type;
		this.courtyard_id = courtyard_id;
		this.content = content;
		this.create_time = System.currentTimeMillis();
	}
	
	public Notice(int notice_type, long courtyard_id, long user_id, String content){
		this(notice_type, courtyard_id, content);
		this.user_id = user_id;
	}
	
	/**
	 * @return the notice_type
	 */
	public int getNotice_type() {
		return notice_type;
	}
	/**
	 * @param notice_type the notice_type to set
	 */
	public void setNotice_type(int notice_type) {
		this.notice_type = notice_type;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the create_time
	 */
	public long getCreate_time() {
		return create_time;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
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
	
	
	
}
