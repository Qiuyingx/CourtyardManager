package cn.dayuanzi.resp;

/**
 * 匹配的兴趣相近的人
 * @author : leihc
 * @date : 2015年5月26日
 * @version : 1.0
 */
public class MatchInterestDto {

	private String nickname;
	
	private String tel;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
