package cn.dayuanzi.resp;

import cn.dayuanzi.model.UserPost;
import cn.dayuanzi.util.DateTimeUtil;

/**
 * 
 * @author : leihc
 * @date : 2015年5月20日
 * @version : 1.0
 */
public class UserPostDto {

	
	private long id;
	
	private String courtyardName;
	
	private String title;
	
	private String nickname;
	
	private String createTime;
	
	private int replyAmount;
	
	private int laudAmount;
	
	private String content;
	
	public UserPostDto(UserPost userPost){
		this.id = userPost.getId();
		this.title = userPost.getTitle();
		this.createTime = DateTimeUtil.formatDateTime(userPost.getCreate_time(), "yyyy-MM-dd HH:mm:ss");
		this.content = userPost.getContent();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getReplyAmount() {
		return replyAmount;
	}

	public void setReplyAmount(int replyAmount) {
		this.replyAmount = replyAmount;
	}

	public int getLaudAmount() {
		return laudAmount;
	}

	public void setLaudAmount(int laudAmount) {
		this.laudAmount = laudAmount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCourtyardName() {
		return courtyardName;
	}

	public void setCourtyardName(String courtyardName) {
		this.courtyardName = courtyardName;
	}
	
	
}
