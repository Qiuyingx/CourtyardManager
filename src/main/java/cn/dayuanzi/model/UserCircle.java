package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 用户圈子
 * 
 * @author : leihc
 * @date : 2015年4月15日 上午10:25:45
 * @version : 1.0
 */
@Entity
@Table(name = "t_user_circle")
public class UserCircle extends VersionPersistentSupport {

	/**
	 * 用户ID
	 */
	private long user_id;
	/**
	 * 圈子ID
	 */
	private int circle_id;
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
	 * @return the circle_id
	 */
	public int getCircle_id() {
		return circle_id;
	}
	/**
	 * @param circle_id the circle_id to set
	 */
	public void setCircle_id(int circle_id) {
		this.circle_id = circle_id;
	}
	
	
}
