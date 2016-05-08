package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 记录最后读取的消息
 * 
 * @author : leihc
 * @date : 2015年4月22日 上午9:14:13
 * @version : 1.0
 */
@Entity
@Table(name = "t_message_check")
public class MessageStatus extends VersionPersistentSupport {

	/**
	 * 用户ID
	 */
	private long user_id;
	/**
	 * 院子ID
	 */
	private long courtyard_id;
	/**
	 * 最后一条评论的ID
	 */
	private long last_readed_reply_id;
	/**
	 * 最后一条点赞的ID
	 */
	private long last_readed_laud_id;
	/**
	 * 最后一条邀约点赞的ID
	 */
	private long last_readed_invitation_like_id;
	/**
	 * 最后的好友请求的ID
	 */
	private long last_friend_request;
	/**
	 * 最后一条通知的ID
	 */
	private long last_notice;
	/**
	 * 最后一条邀约的ID
	 */
	private long last_invitation;
	/**
	 * 最后一条评论感谢
	 */
	private long last_think_reply;
	
	public MessageStatus(){
		
	}
	
	public MessageStatus(long user_id, long courtyard_id){
		this.user_id = user_id;
		this.courtyard_id = courtyard_id;
	}
	
	
	
	public long getLast_think_reply() {
		return last_think_reply;
	}

	public void setLast_think_reply(long last_think_reply) {
		this.last_think_reply = last_think_reply;
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
	
	public long getCourtyard_id() {
		return courtyard_id;
	}
	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}
	/**
	 * @return the last_readed_reply_id
	 */
	public long getLast_readed_reply_id() {
		return last_readed_reply_id;
	}
	/**
	 * @param last_readed_reply_id the last_readed_reply_id to set
	 */
	public void setLast_readed_reply_id(long last_readed_reply_id) {
		this.last_readed_reply_id = last_readed_reply_id;
	}
	/**
	 * @return the last_readed_laud_id
	 */
	public long getLast_readed_laud_id() {
		return last_readed_laud_id;
	}
	/**
	 * @param last_readed_laud_id the last_readed_laud_id to set
	 */
	public void setLast_readed_laud_id(long last_readed_laud_id) {
		this.last_readed_laud_id = last_readed_laud_id;
	}
	/**
	 * @return the last_friend_request
	 */
	public long getLast_friend_request() {
		return last_friend_request;
	}
	/**
	 * @param last_friend_request the last_friend_request to set
	 */
	public void setLast_friend_request(long last_friend_request) {
		this.last_friend_request = last_friend_request;
	}
	/**
	 * @return the last_notice
	 */
	public long getLast_notice() {
		return last_notice;
	}
	/**
	 * @param last_notice the last_notice to set
	 */
	public void setLast_notice(long last_notice) {
		this.last_notice = last_notice;
	}
	/**
	 * @return the last_invitation
	 */
	public long getLast_invitation() {
		return last_invitation;
	}
	/**
	 * @param last_invitation the last_invitation to set
	 */
	public void setLast_invitation(long last_invitation) {
		this.last_invitation = last_invitation;
	}

	public long getLast_readed_invitation_like_id() {
		return last_readed_invitation_like_id;
	}

	public void setLast_readed_invitation_like_id(
			long last_readed_invitation_like_id) {
		this.last_readed_invitation_like_id = last_readed_invitation_like_id;
	}
	
	
}
