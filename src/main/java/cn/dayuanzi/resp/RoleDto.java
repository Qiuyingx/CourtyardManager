package cn.dayuanzi.resp;

import cn.dayuanzi.pojo.Role;

/**
 * 
 * @author : leihc
 * @date : 2015年5月28日
 * @version : 1.0
 */
public class RoleDto {

	private int id;
	private String description;
	
	public RoleDto(Role role){
		this.id = role.ordinal();
		this.description = role.getDescription();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
