package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 从环信获取的管理员token
 * 
 * @author : leihc
 * @date : 2015年4月26日 上午11:35:04
 * @version : 1.0
 */
@Entity
@Table(name = "t_im_token")
public class ImToken {

	private long id;
	/**
	 * 
	 */
	private String access_token;
	/**
	 * 获取时间
	 */
	private long expires_in;
	/**
	 * 返回的应用的UUID
	 */
	private String application;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public long getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	
	@Transient
	public boolean isExpired(){
		return System.currentTimeMillis() > this.expires_in*1000;
	}
}
