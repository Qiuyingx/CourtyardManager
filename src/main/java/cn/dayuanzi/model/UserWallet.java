package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.BindingPersistent;

/**
 * 用户钱包
 * 
 * @author : leihc
 * @date : 2015年4月29日
 * @version : 1.0
 */
@Entity
@Table(name = "t_user_wallet")
public class UserWallet extends BindingPersistent {
	/**
	 * 红包类型
	 */
	private int type;
	/**
	 * 红包面额
	 */
	private int amount;
	/**
	 * 获取红包的来源
	 */
	private int source;
	/**
	 * 获取时间
	 */
	private long gain_time;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public long getGain_time() {
		return gain_time;
	}
	public void setGain_time(long gain_time) {
		this.gain_time = gain_time;
	}
	
	
}
