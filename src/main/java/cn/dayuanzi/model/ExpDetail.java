/**
 * 
 */
package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/** 
 * @ClassName: EXPDetail 
 * @Description: TODO
 * @author qiuyingxiang
 * @date 2015年6月13日 下午3:01:33 
 *  
 */
@Entity
@Table(name = "t_exp_detail")
public class ExpDetail extends PersistentSupport {
	/**
	 * 用户ID
	 */
	private long user_id;
	/**
	 * 变化数量 正数为增加 
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
	
	public ExpDetail(){
		
	}
	
	public ExpDetail(long userId,int change,String remark){
		this.user_id = userId;
		this.create_time =  System.currentTimeMillis();
		this.remark = remark;
		this.changeAmount = change ;
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
