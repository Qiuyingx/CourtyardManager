/**
 * 
 */
package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/** 
 * @ClassName: ActivityLaud 
 * @Description: 活动点赞
 * @author qiuyingxiang
 * @date 2015年6月5日 下午2:27:34 
 *  
 */

@Entity
@Table(name = "t_activity_laud")
public class ActivityLaud extends PersistentSupport {

	/**
	 * 活动ID
	 */
	private long activity_id;
	/**
	 * 点赞用户ID
	 */
	private long user_id;
	
	/**
	 * 院子ID
	 */
	private long courtyard_id;
	/**
	 * 点赞时间
	 */
	private long create_time;
	
	
	public  ActivityLaud (){
		
	}
	public ActivityLaud(long userId,long courtyardId,long activityId){
		this.activity_id =  activityId;
		this.courtyard_id = courtyardId;
		this.create_time = System.currentTimeMillis();
		this.user_id = userId;
	}
	
	public long getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
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
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	
	
}
