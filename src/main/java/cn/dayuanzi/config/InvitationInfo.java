/**
 * 
 */
package cn.dayuanzi.config;

/** 
 * @ClassName: invitation 
 * @Description: TODO
 * @author qiuyingxiang
 * @date 2015年6月2日 下午11:01:00 
 *  
 */

public class InvitationInfo {
	
	/**
	 * 邀约id
	 */
	
	private int id;
	
	/**
	 * 邀约内容
	 */
	private String invitation;
	
	public InvitationInfo(int id, String invitation){
		this.id = id;
		this.invitation = invitation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInvitation() {
		return invitation;
	}

	public void setInvitation(String invitation) {
		this.invitation = invitation;
	}
	
}
