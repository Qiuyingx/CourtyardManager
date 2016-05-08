/**
 * 
 */
package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/** 
 * @ClassName: ActivityReply 
 * @Description: TODO
 * @author qiuyingxiang
 * @date 2015年6月5日 下午2:40:33 
 *  
 */

@Entity
@Table(name = "t_activity_reply")
public class ActivityReply extends PersistentSupport{

	/**
	 * 活动ID
	 */
	private long activity_id;
	
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
	
	public ActivityReply(){
		
	}
	
	public ActivityReply(User replyer, long activityId, String content){
		this.activity_id = activityId;
		this.courtyard_id = replyer.getCourtyard_id();
		this.replyer_id = replyer.getId();
		this.content = content;
		this.create_time = System.currentTimeMillis();
		
	}

	

	public long getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}

	public long getCourtyard_id() {
		return courtyard_id;
	}

	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
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

	public long getAt_targetId() {
		return at_targetId;
	}

	public void setAt_targetId(long at_targetId) {
		this.at_targetId = at_targetId;
	}

	public long getReply_id() {
		return reply_id;
	}

	public void setReply_id(long reply_id) {
		this.reply_id = reply_id;
	}

	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	
	
}
