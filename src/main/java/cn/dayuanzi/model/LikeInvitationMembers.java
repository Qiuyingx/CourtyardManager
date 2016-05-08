package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/**
 * 保存对邀约感兴趣的信息
 * 
 * @author : leihc
 * @date : 2015年4月20日 下午5:57:05
 * @version : 1.0
 */
@Entity
@Table(name = "t_like_invitation")
public class LikeInvitationMembers extends PersistentSupport{

	/**
	 * 邀约ID
	 */
	private long invitation_id;
	/**
	 * 发邀约人的ID
	 */
	private long invitation_sender_id;
	/**
	 * 院子ID
	 */
	private long courtyard_id;
	
	/**
	 * 感兴趣的人的ID
	 */
	private long user_id;
	
	/**
	 * 操作时间
	 */
	private long create_time;
	
	public LikeInvitationMembers(){
		
	}
	
	public LikeInvitationMembers(long courtyard_id, long user_id, long invitation_id,long sender_id){
		this.courtyard_id = courtyard_id;
		this.user_id = user_id;
		this.invitation_id = invitation_id;
		this.invitation_sender_id = sender_id;
		this.create_time = System.currentTimeMillis();
	}

	/**
	 * @return the invitation_id
	 */
	public long getInvitation_id() {
		return invitation_id;
	}

	/**
	 * @param invitation_id the invitation_id to set
	 */
	public void setInvitation_id(long invitation_id) {
		this.invitation_id = invitation_id;
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

	public long getCourtyard_id() {
		return courtyard_id;
	}

	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}

	public long getInvitation_sender_id() {
		return invitation_sender_id;
	}

	public void setInvitation_sender_id(long invitation_sender_id) {
		this.invitation_sender_id = invitation_sender_id;
	}
	
	
}
