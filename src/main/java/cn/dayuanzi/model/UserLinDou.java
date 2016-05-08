package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.BindingPersistent;

/**
 * 用户的邻豆
 * 
 * @author : leihc
 * @date : 2015年6月5日
 * @version : 1.0
 */
@Entity
@Table(name = "t_user_lindou")
public class UserLinDou extends BindingPersistent {

	private int amount;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void used(int cost){
		this.amount-=cost;
		if(this.amount < 0){
			this.amount = 0;
		}
	}
}
