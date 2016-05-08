package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.dayuanzi.pojo.Role;
import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 管理者用户表 
 * @author : leihc
 * @date : 2015年5月11日
 * @version : 1.0
 */
@Entity
@Table(name = "t_manager_user")
public class ManagerUser extends VersionPersistentSupport {

	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户角色
	 */
	private Role role;
	
	/**
	 * 社区CEO或者管理员所属社区
	 */
	private long courtyardId;
	/**
	 * 是否被禁止
	 */
	private boolean banning;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role.ordinal();
	}
	public void setRole(int role) {
		this.role = Role.values()[role];
	}
	public long getCourtyardId() {
		return courtyardId;
	}
	public void setCourtyardId(long courtyardId) {
		this.courtyardId = courtyardId;
	}
	public boolean isBanning() {
		return banning;
	}
	public void setBanning(boolean banning) {
		this.banning = banning;
	}
	
}
