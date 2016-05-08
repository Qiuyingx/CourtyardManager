package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/**
 * 邻豆明细表
 * 
 * @author : leihc
 * @date : 2015年6月7日
 * @version : 1.0
 */
@Entity
@Table(name = "t_lindou_detail")
public class LindouDetail extends PersistentSupport {

	/**
	 * 用户ID
	 */
	private long user_id;
	/**
	 * 变化数量 正数为增加，负数为减少
	 */
	private int changeAmount;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 生成时间
	 */
	private long create_time;
	
	public LindouDetail(){
		
	}
	
	public LindouDetail(long user_id, int change,String remark){
		this.user_id = user_id;
		this.changeAmount = change;
		this.remark = remark;
		this.create_time = System.currentTimeMillis();
	}
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	public int getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(int changeAmount) {
		this.changeAmount = changeAmount;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	
	
}
