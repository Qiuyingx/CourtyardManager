package cn.dayuanzi.resp;

import cn.dayuanzi.model.ManagerUser;

/**
 * 
 * @author : leihc
 * @date : 2015年6月2日
 * @version : 1.0
 */
public class LoginResp extends Resp {

	private int role;
	
	public LoginResp(ManagerUser managerUser){
		this.role = managerUser.getRole();
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	
}
