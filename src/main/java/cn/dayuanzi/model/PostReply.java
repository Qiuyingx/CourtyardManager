package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.dayuanzi.pojo.ContentType;
import cn.framework.persist.db.PersistentSupport;

/**
 * 评论表，邀约，帮帮，话题评论都在这个表
 * 
 * @author : leihc
 * @date : 2015年4月15日 上午11:21:04
 * @version : 1.0
 */
@Entity
@Table(name = "t_post_reply")
public class PostReply extends PersistentSupport {

	/**
	 * 帖子的类型{@link ContentType}
	 */
	private int content_type;
	/**
	 * 帖子ID
	 */
	private long post_id;
	/**
	 * 发帖子的用户ID（冗余，但便于消息提示）
	 */
	private long post_sender_id;
	/**
	 * 院子ID
	 */
	private long courtyard_id;
	/**
	 * 评论用户ID
	 */
	private long replyer_id;
	
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 回复@对象的ID
	 */
	private long at_targetId;
	
	/**
	 * 回复的评论ID
	 */
	private long reply_id;
	
	/**
	 * 评论时间
	 */
	private long create_time;
	/**
	 * 回复帮帮被采纳了
	 */
	private boolean accepted;
	
	public PostReply(){
		
	}
	
	public PostReply(User replyer, long post_id, int content_type, String content){
		this.post_id = post_id;
		this.content_type = content_type;
		this.courtyard_id = replyer.getCourtyard_id();
		this.replyer_id = replyer.getId();
		this.content = content;
		this.create_time = System.currentTimeMillis();
	}
	
	
	public long getPost_id() {
		return post_id;
	}
	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}
	public long getReplyer_id() {
		return replyer_id;
	}
	public void setReplyer_id(long replyer_id) {
		this.replyer_id = replyer_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	public long getAt_targetId() {
		return at_targetId;
	}

	public void setAt_targetId(long at_targetId) {
		this.at_targetId = at_targetId;
	}
	
	/**
	 * 回复给指定对象
	 * 
	 * @return
	 */
	public boolean hasAtTarget(){
		return this.at_targetId>0;
	}

	public long getPost_sender_id() {
		return post_sender_id;
	}

	public void setPost_sender_id(long post_sender_id) {
		this.post_sender_id = post_sender_id;
	}

	public long getCourtyard_id() {
		return courtyard_id;
	}

	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}

	public long getReply_id() {
		return reply_id;
	}

	public void setReply_id(long reply_id) {
		this.reply_id = reply_id;
	}

	public int getContent_type() {
		return content_type;
	}

	public void setContent_type(int content_type) {
		this.content_type = content_type;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
	
}
