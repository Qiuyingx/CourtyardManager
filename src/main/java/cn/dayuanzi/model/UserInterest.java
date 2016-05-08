package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 用户兴趣表
 *  
 * @author : leihc
 * @date : 2015年4月16日 上午11:43:43
 * @version : 1.0
 */
@Entity
@Table(name = "t_user_interest")
public class UserInterest extends VersionPersistentSupport {

	/**
	 * 用户ID
	 */
	private long user_id;
	
	/**
	 * 兴趣ID
	 */
	private int interest_id;

	public UserInterest(){
		
	}
	
	public UserInterest(long user_id, int interest_id){
		this.user_id = user_id;
		this.interest_id = interest_id;
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
	 * @return the interest_id
	 */
	public int getInterest_id() {
		return interest_id;
	}

	/**
	 * @param interest_id the interest_id to set
	 */
	public void setInterest_id(int interest_id) {
		this.interest_id = interest_id;
	}
	
}
