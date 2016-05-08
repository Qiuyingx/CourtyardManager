package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/**
 * 
 * 用户收藏
 * @author : leihc
 * @date : 2015年4月27日
 * @version : 1.0
 */
@Entity
@Table(name = "t_user_collect")
public class UserCollect extends PersistentSupport {

	/**
	 * 用户ID
	 */
	private long user_id;
	/**
	 * 院子ID
	 */
	private long courtyard_id;
	/**
	 * 收藏类型 1 邀约 2 互助 &分享
	 */
	private int collect_type;
	/**
	 * 收藏对象ID
	 */
	private long target_id;
	/**
	 * 收藏时间
	 */
	private long collect_time;
	
	public UserCollect(){
		
	}
	
	public UserCollect(long courtyard_id, long user_id, int collect_type, long target_id){
		this.user_id = user_id;
		this.collect_type = collect_type;
		this.target_id = target_id;
		this.courtyard_id = courtyard_id;
		this.collect_time = System.currentTimeMillis();
	}
	
	public int getCollect_type() {
		return collect_type;
	}
	public void setCollect_type(int collect_type) {
		this.collect_type = collect_type;
	}
	public long getTarget_id() {
		return target_id;
	}
	public void setTarget_id(long target_id) {
		this.target_id = target_id;
	}
	public long getCollect_time() {
		return collect_time;
	}
	public void setCollect_time(long collect_time) {
		this.collect_time = collect_time;
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
