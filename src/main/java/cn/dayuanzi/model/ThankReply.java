package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/**
 * 感谢评论
 * 
 * @author : leihc
 * @date : 2015年6月9日
 * @version : 1.0
 */
@Entity
@Table(name = "t_thank_reply")
public class ThankReply extends PersistentSupport {

	/**
	 * 操作感谢的用户ID
	 */
	private long user_id;
	/**
	 * 感谢的评论ID
	 */
	private long reply_id;
	/**
	 * 发评论的用户ID
	 */
	private long replyer_id;
	/**
	 * 评论内容类型
	 */
	private int content_type;
	/**
	 * 感谢的时间
	 */
	private long create_time;
	
	public ThankReply(){
		
	}
	
	public ThankReply(long userId, PostReply postReply){
		this.user_id = userId;
		this.reply_id = postReply.getId();
		this.replyer_id = postReply.getReplyer_id();
		this.content_type = postReply.getContent_type();
		this.create_time = System.currentTimeMillis();
	}
	
	public long getReply_id() {
		return reply_id;
	}
	public void setReply_id(long reply_id) {
		this.reply_id = reply_id;
	}
	public long getReplyer_id() {
		return replyer_id;
	}
	public void setReplyer_id(long replyer_id) {
		this.replyer_id = replyer_id;
	}
	public int getContent_type() {
		return content_type;
	}
	public void setContent_type(int content_type) {
		this.content_type = content_type;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	
	
}
