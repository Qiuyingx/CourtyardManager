package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 用户举报表
 * 
 * @author : leihc
 * @date : 2015年5月21日
 * @version : 1.0
 */
@Entity
@Table(name = "t_user_report")
public class UserReport extends VersionPersistentSupport {

	/**
	 * 举报者
	 */
	private long user_id;
	/**
	 * 举报的内容类型
	 */
	private int content_type;
	/**
	 * 举报类型
	 */
	private int  report_type;
	/**
	 * 举报内容
	 */
	private String content;
	/**
	 * 举报的内容数据ID
	 */
	private long target_id;
	/**
	 * 帖子所属社区
	 */
	private long courtyard_id;
	/**
	 * 举报时间
	 */
	private long create_time;
	/**
	 * 状态 0 举报 1 处理并删除
	 */
	private int status;
	
	public UserReport(){
		
	}
	
	public UserReport(long user_id, int content_type, long target_id, long courtyard_id,int report_type, String content){
		this.user_id =  user_id;
		this.content_type = content_type;
		this.target_id = target_id;
		this.courtyard_id = courtyard_id;
		this.report_type = report_type;
		this.content = content;
		this.create_time = System.currentTimeMillis();
	}
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReport_type() {
		return report_type;
	}
	public void setReport_type(int report_type) {
		this.report_type = report_type;
	}

	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	public int getContent_type() {
		return content_type;
	}

	public void setContent_type(int content_type) {
		this.content_type = content_type;
	}

	public long getTarget_id() {
		return target_id;
	}
	public void setTarget_id(long target_id) {
		this.target_id = target_id;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public long getCourtyard_id() {
		return courtyard_id;
	}

	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}
	
	
}
