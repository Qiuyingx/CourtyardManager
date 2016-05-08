package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 比对院子业主表通过验证的用户信息
 * 
 * @author : leihc
 * @date : 2015年4月15日 上午11:24:32
 * @version : 1.0
 */
@Entity
@Table(name = "t_validate_user")
public class ValidateUser extends VersionPersistentSupport {

	/**
	 * 用户ID
	 */
	private long user_id;
	/**
	 * 业主ID，关联社主表信息
	 */
//	private long houseowner_id;
	/**
	 * 院子ID
	 */
	private long courtyard_id;
	/**
	 * 验证方式 0 有合作的手机验证 1 无合作的上传图片 2 无合作的code礼包 3 邀请码验证
	 */
	private int validate_type;
	/**
	 * 验证状态 0 请求状态  1 审核通过 2 审核不通过
	 */
	private int validate_status;
	/**
	 * 验证通过
	 */
//	private boolean passed;
	/**
	 * 图片验证方式图片的保存路径或者code验证时生成的码
	 */
	private String append;
	/**
	 * 验证时间
	 */
	private long create_time;
	
	
	public ValidateUser(){
		
	}
	
	public ValidateUser(long user_id, long courtyard_id){
		this.user_id = user_id;
		this.courtyard_id = courtyard_id;
		this.create_time = System.currentTimeMillis();
	}
	
	
	/**
	 * @return the user_id
	 */
	public long getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(long user_id) {
		this.user_id = user_id;
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

	public long getCourtyard_id() {
		return courtyard_id;
	}

	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}

	public int getValidate_type() {
		return validate_type;
	}

	public void setValidate_type(int validate_type) {
		this.validate_type = validate_type;
	}

//	public boolean isPassed() {
//		return passed;
//	}
//
//	public void setPassed(boolean passed) {
//		this.passed = passed;
//	}

	public String getAppend() {
		return append;
	}

	public void setAppend(String append) {
		this.append = append;
	}

	public int getValidate_status() {
		return validate_status;
	}

	public void setValidate_status(int validate_status) {
		this.validate_status = validate_status;
	}

	
}
