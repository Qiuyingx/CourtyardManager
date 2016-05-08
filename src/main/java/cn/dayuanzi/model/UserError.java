package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;


/**
 * 用户认证报错
 * @author qiuyingxiang
 *
 */

@Entity
@Table(name = "t_user_error")
public class UserError extends PersistentSupport{

	/**
	 * 业主账号id
	 */
	private long user_id;
	/**
	 * 业主报错地址（社区，期数，楼栋，单元，房间号）
	 */
	private String address;
	/**
	 * 业主姓名
	 */
	private String user_name;
	/**
	 * 业主身份证号码
	 */
	private String user_code;
	/**
	 * 业主手机号
	 */
	private String user_tel;
	/**
	 * 业主报错说明  
	 */
	private String user_content;
	/**
	 * 报错时间时
	 */
	private long  create_time;
	
	public UserError(){
		
	}
	
	public UserError(long userId, String name,String code,String address,String tel,String content){
		this.user_id = userId;
		this.address = address;
		this.user_name = name;
		this.user_code = code; 
		this.user_tel = tel;
		this.user_content = content;
		this.create_time = System.currentTimeMillis();
	}
	
	
	public long getCreate_time() {
		return create_time;
	}


	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getUser_content() {
		return user_content;
	}

	public void setUser_content(String user_content) {
		this.user_content = user_content;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
