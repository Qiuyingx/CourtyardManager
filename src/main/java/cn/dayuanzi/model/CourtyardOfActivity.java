package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/**
 * 
 * @author : leihc
 * @date : 2015年6月17日
 * @version : 1.0
 */
@Entity
@Table(name = "t_couryard_of_activity")
public class CourtyardOfActivity extends PersistentSupport {

	/**
	 * 社区ID,为0时表示所有社区
	 */
	private long courtyard_id;
	/**
	 * 活动ID
	 */
	private long activity_id;
	
	public CourtyardOfActivity(){
		
	}
	
	public CourtyardOfActivity(long courtyard_id, long activity_id){
		this.courtyard_id = courtyard_id;
		this.activity_id = activity_id;
	}
	
	public long getCourtyard_id() {
		return courtyard_id;
	}
	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}
	public long getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}
	
	
}
