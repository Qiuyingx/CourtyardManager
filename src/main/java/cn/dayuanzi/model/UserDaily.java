package cn.dayuanzi.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Table;


import cn.framework.persist.db.BindingPersistent;

/**
 * 用户每日数据、
 * 
 * @author : leihc
 * @date : 2015年6月7日
 * @version : 1.0
 */
@Entity
@Table(name = "t_user_daily")
public class UserDaily extends BindingPersistent {

	/**
	 * 今年的第多少天
	 */
	private int day_of_year;
	/**
	 * 最后一次签到时间
	 */
	private long last_check_time;
	/**
	 * 签到持续天数
	 */
	private int check_continue_days;
	/**
	 * 每日话题类发帖得经验次数
	 */
	private int send_post_count;
	/**
	 * 每日发邀约得经验次数
	 */
	private int send_invitation_count;
	/**
	 * 每日发帮帮得经验次数
	 */
	private int send_help_count;
	/**
	 * 每日帮帮评论得经验次数
	 */
	private int help_reply_count;
	/**
	 * 每日被感谢得经验次数
	 */
	private int be_thank_count;
	/**
	 * 每日意见被采纳次数
	 */
	private int accepted_count;
	
	
	public int getDay_of_year() {
		return day_of_year;
	}
	public void setDay_of_year(int day_of_year) {
		this.day_of_year = day_of_year;
	}
	
	public long getLast_check_time() {
		return last_check_time;
	}
	public void setLast_check_time(long last_check_time) {
		this.last_check_time = last_check_time;
	}
	public int getCheck_continue_days() {
		return check_continue_days;
	}
	public void setCheck_continue_days(int check_continue_days) {
		this.check_continue_days = check_continue_days;
	}
	public int getSend_post_count() {
		return send_post_count;
	}
	public void setSend_post_count(int send_post_count) {
		this.send_post_count = send_post_count;
	}
	
	public int getSend_invitation_count() {
		return send_invitation_count;
	}
	public void setSend_invitation_count(int send_invitation_count) {
		this.send_invitation_count = send_invitation_count;
	}
	public int getSend_help_count() {
		return send_help_count;
	}
	public void setSend_help_count(int send_help_count) {
		this.send_help_count = send_help_count;
	}
	
	public int getHelp_reply_count() {
		return help_reply_count;
	}
	public void setHelp_reply_count(int help_reply_count) {
		this.help_reply_count = help_reply_count;
	}
	public int getBe_thank_count() {
		return be_thank_count;
	}
	public void setBe_thank_count(int be_thank_count) {
		this.be_thank_count = be_thank_count;
	}
	
	public int getAccepted_count() {
		return accepted_count;
	}
	public void setAccepted_count(int accepted_count) {
		this.accepted_count = accepted_count;
	}
	/**
	 * 更新每日数据
	 * 如果增加每日记录数据需要添加于此更新
	 */
	public void doUpdate(){
		Calendar calendar = Calendar.getInstance();
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		if(dayOfYear!=this.day_of_year){
			this.day_of_year = dayOfYear;
			this.send_post_count = 0;
			this.send_help_count = 0;
			this.send_invitation_count = 0;
			this.help_reply_count = 0;
			this.be_thank_count = 0;
			this.accepted_count = 0;
		}
	}
	
	
}
