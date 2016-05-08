package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/**
 * 
 * 邀约讨论组成员
 * 
 * @author : leihc
 * @date : 2015年4月24日 下午3:33:51
 * @version : 1.0
 */
@Entity
@Table(name = "t_discuss_group_member")
public class InvitationDiscussGroupMember extends PersistentSupport {

	/**
	 * 邀约的ID
	 */
	private long invitation_id;
	/**
	 * 加入这条邀约讨论组的用户ID
	 */
	private long user_id;
	
	/**
	 * 所在院子
	 */
	private long courtyard_id;
	
	public InvitationDiscussGroupMember(){
		
	}
	
	public InvitationDiscussGroupMember(long invitantionId,long userId,long courtyardId){
		this.invitation_id = invitantionId;
		this.user_id = userId;
		this.courtyard_id = courtyardId;
	}
	
	public long getInvitation_id() {
		return invitation_id;
	}
	public void setInvitation_id(long invitation_id) {
		this.invitation_id = invitation_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getCourtyard_id() {
		return courtyard_id;
	}
	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}
	
	
}
