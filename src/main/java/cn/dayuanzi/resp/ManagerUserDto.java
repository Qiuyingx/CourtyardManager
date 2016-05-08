package cn.dayuanzi.resp;

/**
 * 
 * @author : leihc
 * @date : 2015年5月28日
 * @version : 1.0
 */
public class ManagerUserDto {

	private long id;
	private String userName;
	private String role;
	private String courtyard;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCourtyard() {
		return courtyard;
	}
	public void setCourtyard(String courtyard) {
		this.courtyard = courtyard;
	}
	
}
