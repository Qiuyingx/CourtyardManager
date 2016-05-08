package cn.dayuanzi.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.dayuanzi.config.LevelUpConfig;
import cn.dayuanzi.config.LevelUpInfo;
import cn.dayuanzi.service.ServiceRegistry;
import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 用户表
 * 
 * @author : lhc
 * @date : 2015年4月10日 下午5:05:17
 * @version : 1.0
 */
@Entity
@Table(name = "t_user")
public class User extends VersionPersistentSupport{
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 性别 0 不知 1 男 2 女
	 */
	private int gender;
	/**
	 * 生日
	 */
	private Timestamp birthday;
	/**
	 * 职业ID
	 */
	private int careerId;
	/**
	 * 当前所在院子ID
	 */
	private long courtyard_id;
	/**
	 * 所住社区（注册时选择社区，验证后为验证社区）
	 */
	private long houseowner_id;
	/**
	 * false 表示未提交社区验证 true 表示已提交验证
	 */
//	private boolean submitValidate;
	/**
	 * 注册时间
	 */
	private long register_time;
	/**
	 * 最后登录时间
	 */
	private long last_login_time;
	/**
	 * 签名
	 */
	private String signature;
	/**
	 * 头像
	 */
	private String head_icon;
	/**
	 * 证件号码
	 */
//	private String card_id;
	/**
	 * 注册平台 1 android 2 ios
	 */
	private int platform;
	/**
	 * 邀请码
	 */
	private long inviteCode;
	/**
	 * 最后一次登陆平台 1 android 2 ios
	 */
	private int last_login_platform;
	/**
	 * 最后一次登陆时的设备token
	 */
	private String last_login_token;
	/**
	 * 设备token,注册用户时的
	 */
	private String token;
	/**
	 * 第三方代号0 表示本平台 1 表示QQ平台 2 表示微信 3 表示微博
	 */
	private int externalType;
	/**
	 * 第三方返回的代号
	 */
	private String openId;
	/**
	 * 等级
	 */
	private int level=1;
	/**
	 * 经验
	 */
	private int exp;
	/**
	 * 封禁时间
	 */
	private long banningTime;
	
	public User(){
		
	}
	
	public User(String tel, String password){
		this.tel = tel;
		this.password = password;
		this.register_time = System.currentTimeMillis();
	}
	
	public User(int externalType ,String openId){
//		this.tel = tel;
		this.externalType = externalType;
		this.openId = openId;
		this.register_time = System.currentTimeMillis();
	}
	
	/**
	 * 添加经验，可以触发升级
	 * @param exp
	 * @return 触发升级 true 未升级 false
	 */
	public boolean addExp(int exp){
		if(exp<=0){
			return false;
		}
		boolean isLevelUp = false;
		LevelUpInfo info = LevelUpConfig.getDatas().get(this.level+1);
		int finalExp = this.exp+exp;
		while(finalExp >= info.getExp()){
			this.level++;
//			finalExp = finalExp - info.getExp();
			isLevelUp = true;
			// 添加邻豆
			if(info.getLindou() > 0){
				ServiceRegistry.getUserService().addLindou(this.id, info.getLindou(), "升级到"+this.level);
			}
			info = LevelUpConfig.getDatas().get(this.level+1);
			if(info==null){
				finalExp = LevelUpConfig.getDatas().get(level).getExp();
				break;
			}
		}
		this.exp = finalExp;
		
		return isLevelUp;
	}
	
	/**
	 * 判断用户是否被禁止登录
	 * @return
	 */
	@Transient
	public boolean isBanned(){
		return this.banningTime > 0;
	}
	
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the tel
	 */
	@Column(nullable = true)
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
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
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}
	/**
	 * @return the register_time
	 */
	public long getRegister_time() {
		return register_time;
	}
	/**
	 * @param register_time the register_time to set
	 */
	public void setRegister_time(long register_time) {
		this.register_time = register_time;
	}
	/**
	 * @return the last_login_time
	 */
	public long getLast_login_time() {
		return last_login_time;
	}
	/**
	 * @param last_login_time the last_login_time to set
	 */
	public void setLast_login_time(long last_login_time) {
		this.last_login_time = last_login_time;
	}
	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}
	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	/**
	 * @return the head_icon
	 */
	public String getHead_icon() {
		return head_icon;
	}

	/**
	 * @param head_icon the head_icon to set
	 */
	public void setHead_icon(String head_icon) {
		this.head_icon = head_icon;
	}


	public long getHouseowner_id() {
		return houseowner_id;
	}

	public void setHouseowner_id(long houseowner_id) {
		this.houseowner_id = houseowner_id;
	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
		this.last_login_platform = platform;
	}

	public long getCourtyard_id() {
		return courtyard_id;
	}

	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	
	public int getCareerId() {
		return careerId;
	}

	public void setCareerId(int careerId) {
		this.careerId = careerId;
	}

	public long getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(long inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExternalType() {
		return externalType;
	}

	public void setExternalType(int externalType) {
		this.externalType = externalType;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getLast_login_platform() {
		return last_login_platform;
	}

	public void setLast_login_platform(int last_login_platform) {
		this.last_login_platform = last_login_platform;
	}

	public String getLast_login_token() {
		return last_login_token;
	}

	public void setLast_login_token(String last_login_token) {
		this.last_login_token = last_login_token;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public long getBanningTime() {
		return banningTime;
	}

	public void setBanningTime(long banningTime) {
		this.banningTime = banningTime;
	}

}
