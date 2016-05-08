package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/**
 * 活动报名表
 * 
 * @author : leihc
 * @date : 2015年5月26日
 * @version : 1.0
 */
@Entity
@Table(name = "t_activity_signup")
public class ActivitySignUp extends PersistentSupport {
	/**
	 * 报名者ID
	 */
	private long user_id;
	/**
	 * 活动ID
	 */
	private long activity_id;
	/**
	 * 报名用户姓名
	 */
	private String name;
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * 备注
	 */
	private String content;
	/**
	 * 报名时间
	 */
	private long create_time;
	
	public ActivitySignUp(){
		
	}
	
	public ActivitySignUp(long user_id, long activity_id,String name,String tel,String content){
		this.user_id = user_id;
		this.activity_id = activity_id;
		this.create_time = System.currentTimeMillis();
		this.content = content;
		this.name = name;
		this.tel = tel ;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	public long getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}
	
}
