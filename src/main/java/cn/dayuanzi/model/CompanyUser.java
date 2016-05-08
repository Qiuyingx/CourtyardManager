package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 物业公司用户，物业公司员工
 *  
 * @author : leihc
 * @date : 2015年4月15日 上午10:58:36
 * @version : 1.0
 */
@Entity
@Table(name = "t_company_user")
public class CompanyUser extends VersionPersistentSupport {
	
	/**
	 * 用户名
	 */
	private String user_name;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 物业公司ID
	 */
	private long company_id;

	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the company_id
	 */
	public long getCompany_id() {
		return company_id;
	}

	/**
	 * @param company_id the company_id to set
	 */
	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}
	
	
}
