package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/**
 * 帖子点赞表，只包含话题和帮帮
 * 
 * @author : leihc
 * @date : 2015年4月15日 上午11:18:25
 * @version : 1.0
 */
@Entity
@Table(name = "t_post_laud")
public class PostLaud extends PersistentSupport {

	/**
	 * 帖子ID
	 */
	private long post_id;
	/**
	 * 发帖人ID
	 */
	private long post_sender_id;
	/**
	 * 帖子类型
	 */
//	private int content_type;
	/**
	 * 点赞用户ID
	 */
	private long user_id;
	
	/**
	 * 院子ID
	 */
	private long courtyard_id;
	/**
	 * 点赞时间
	 */
	private long create_time;
	
	public PostLaud(){
		
	}
	
	public PostLaud(long post_id, long post_sender_id, long user_id, long courtyard_id){
		this.post_id = post_id;
		this.post_sender_id = post_sender_id;
		this.user_id = user_id;
		this.courtyard_id = courtyard_id;
		this.create_time = System.currentTimeMillis();
	}
	
	
	public long getCourtyard_id() {
		return courtyard_id;
	}
	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}
	/**
	 * @return the post_sender_id
	 */
	public long getPost_sender_id() {
		return post_sender_id;
	}
	/**
	 * @param post_sender_id the post_sender_id to set
	 */
	public void setPost_sender_id(long post_sender_id) {
		this.post_sender_id = post_sender_id;
	}
	/**
	 * @return the post_id
	 */
	public long getPost_id() {
		return post_id;
	}
	/**
	 * @param post_id the post_id to set
	 */
	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}
	/**
	 * @return the user_id
	 */
	public long getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(long user_id) {
		this.user_id = user_id;
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
	
	
}
