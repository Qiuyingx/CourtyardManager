package cn.dayuanzi.pojo;

import cn.dayuanzi.model.ManagerUser;



/**
 *
 * @author : lhc
 * @date : 2015年4月9日 上午11:07:23
 * @version : 1.0
 */
public class UserSession {
	
	private static ThreadLocal<UserSession> local = new ThreadLocal<UserSession>();
	
	public static void set(UserSession sessionUser){
		local.set(sessionUser);
	}
	
	public static UserSession get(){
		return local.get();
	}
	
	public static void clear(){
		local.remove();
	}
	
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户角色
	 */
	private Role role;
	/**
	 * 社区管理员或者CEO所在社区
	 */
	private long courtyardId;
	
	public UserSession(ManagerUser managerUser){
		this.userId = managerUser.getId();
		this.userName = managerUser.getUserName();
		this.role = Role.values()[managerUser.getRole()];
		this.courtyardId = managerUser.getCourtyardId();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getCourtyardId() {
		return courtyardId;
	}

	public void setCourtyardId(long courtyardId) {
		this.courtyardId = courtyardId;
	}
	

	
	
	
}
