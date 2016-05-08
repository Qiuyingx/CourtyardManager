package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.VersionPersistentSupport;

@Entity
@Table(name = "t_version")
public class VersionChecke extends VersionPersistentSupport{
	
	/**
	 * 版本Id
	 */
	private int  appVersionid;
	/**
	 * 版本号 
	 */
	private String appVersion ;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 版本更新内容
	 */
	private String content;
	
	public VersionChecke(){
		
	}
	
	public int getAppVersionid() {
		return appVersionid;
	}

	public void setAppVersionid(int appVersionid) {
		this.appVersionid = appVersionid;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
