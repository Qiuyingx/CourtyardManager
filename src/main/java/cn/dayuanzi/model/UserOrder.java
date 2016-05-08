package cn.dayuanzi.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 
 * @author : leihc
 * @date : 2015年6月7日
 * @version : 1.0
 */
@Entity
@Table(name = "t_user_order")
public class UserOrder extends VersionPersistentSupport {

	/**
	 * 用户ID
	 */
	private long user_id;
	/**
	 * 收货人
	 */
	private String userName;
	/**
	 * 联系电话
	 */
	private String tel;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 社区ID
	 */
	private long courtyard_id;
	/**
	 * 订单ID
	 */
	private String order_id;
	/**
	 * 商品ID
	 */
	private long goods_id;
	/**
	 * 购买数量
	 */
	private int amount;
	/**
	 * 购买时间
	 */
	private long create_time;
	
	public UserOrder(){
		this.order_id = UUID.randomUUID().toString().replaceAll("-", "");
		this.create_time = System.currentTimeMillis();
	}
	
	public UserOrder(long userId,long courtyardId,String userName,String tel,String address,long goods_id,int amount){
		this.user_id = userId;
		this.courtyard_id = courtyardId;
		this.order_id = UUID.randomUUID().toString().replaceAll("-", "");
		this.goods_id = goods_id;
		this.amount = amount;
		this.userName = userName;
		this.tel = tel;
		this.address = address;
		this.create_time = System.currentTimeMillis();
	}
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	public long getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(long goods_id) {
		this.goods_id = goods_id;
	}

	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getCourtyard_id() {
		return courtyard_id;
	}

	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
