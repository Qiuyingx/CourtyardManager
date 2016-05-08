/**
 * 
 */
package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.BindingPersistent;


/** 
 * @ClassName: UserSet 
 * @Description: TODO
 * @author qiuyingxiang
 * @date 2015年5月27日 上午10:02:33 
 *  
 */
@Entity
@Table(name = "t_user_setting")
public class UserSetting extends BindingPersistent {


	/**
	 * 用户评论提醒
	 */
	private boolean reply ;
	/**
	 * 用户点赞提醒
	 */
	private boolean laud ;
	/**
	 * 用户私信提醒
	 */
	private boolean message;
	/**
	 * 用户系统提醒
	 */
	private  boolean system ;
	
	public  UserSetting (){
		
	}
	
	public  UserSetting (long userId,boolean reply,boolean laud,boolean message,boolean system){
		this.id = userId;
		this.system =  system ;
		this.message = message;
		this.laud = laud;
		this.reply = reply;
	}
	
	public boolean isReply() {
		return reply;
	}
	public void setReply(boolean reply) {
		this.reply = reply;
	}
	public boolean isLaud() {
		return laud;
	}
	public void setLaud(boolean laud) {
		this.laud = laud;
	}
	public boolean isMessage() {
		return message;
	}
	public void setMessage(boolean message) {
		this.message = message;
	}
	public boolean isSystem() {
		return system;
	}
	public void setSystem(boolean system) {
		this.system = system;
	}
	
	
	
}
