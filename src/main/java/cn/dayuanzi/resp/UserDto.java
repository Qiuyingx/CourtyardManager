package cn.dayuanzi.resp;

import org.apache.commons.lang.StringUtils;

import cn.dayuanzi.config.CareerConfig;
import cn.dayuanzi.model.User;
import cn.dayuanzi.util.DateTimeUtil;

/**
 * 
 * @author : leihc
 * @date : 2015年5月26日
 * @version : 1.0
 */
public class UserDto {

	private long id;
	private String tel;
	private String nickname;
	private int level;
	private int exp;
	private int lindou;
	private String instrests;
	private String cereer;
	private String gender;
	private String age;
	private String validateStatus;
	private String registerTime;
	private boolean review;
	private String courtyardName;
	
	public UserDto(User user){
		this.id = user.getId();
		this.tel = StringUtils.isBlank(user.getTel())?"无":user.getTel();
		this.nickname = user.getNickname();
		if(CareerConfig.getCareers().get(user.getCareerId())!=null)
		this.cereer = CareerConfig.getCareers().get(user.getCareerId()).getIndustry();
		this.gender = user.getGender()==1?"男":"女";
		this.age = DateTimeUtil.getAgeData(user.getBirthday());
		this.registerTime = DateTimeUtil.formatDateTime(user.getRegister_time());
		this.level = user.getLevel();
		this.exp = user.getExp();
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getInstrests() {
		return instrests;
	}
	public void setInstrests(String instrests) {
		this.instrests = instrests;
	}
	public String getCereer() {
		return cereer;
	}
	public void setCereer(String cereer) {
		this.cereer = cereer;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getValidateStatus() {
		return validateStatus;
	}

	public void setValidateStatus(String validateStatus) {
		this.validateStatus = validateStatus;
	}

	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isReview() {
		return review;
	}

	public void setReview(boolean review) {
		this.review = review;
	}

	public String getCourtyardName() {
		return courtyardName;
	}

	public void setCourtyardName(String courtyardName) {
		this.courtyardName = courtyardName;
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

	public int getLindou() {
		return lindou;
	}

	public void setLindou(int lindou) {
		this.lindou = lindou;
	}

	
}
