package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/**
 * 商品对应的社区
 * @author : leihc
 * @date : 2015年6月23日
 * @version : 1.0
 */
@Entity
@Table(name = "t_shop_courtyard")
public class ShopOfCourtyard extends PersistentSupport {

	/**
	 * 社区ID
	 */
	private long courtyard_id;
	/**
	 * 商品ID
	 */
	private long goods_id;
	
	public ShopOfCourtyard(){
		
	}
	
	public ShopOfCourtyard(long courtyard_id,long goods_id){
		this.courtyard_id = courtyard_id;
		this.goods_id = goods_id;
	}
	
	public long getCourtyard_id() {
		return courtyard_id;
	}
	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}
	public long getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(long goods_id) {
		this.goods_id = goods_id;
	}
	
	
}
