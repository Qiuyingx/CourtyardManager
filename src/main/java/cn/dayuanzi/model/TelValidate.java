package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/**
 * 手机验证信息表
 * 
 * @author : leihc
 * @date : 2015年4月15日 上午11:53:00
 * @version : 1.0
 */
@Entity
@Table(name = "t_tel_validate")
public class TelValidate extends PersistentSupport {

	/**
	 * 验证手机号
	 */
	private String tel;
	/**
	 * 验证时间
	 */
	private long create_time;
	
	public TelValidate(){
		
	}
	
	public TelValidate(String tel){
		this.tel = tel;
		this.create_time = System.currentTimeMillis();
	}
	
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the create_time
	 */
	public long getCreate_time() {
		return create_time;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	
	
}
