package cn.dayuanzi.resp;


/**
 * 
 * @author : leihc
 * @date : 2015年5月26日
 * @version : 1.0
 */
public class InvitationDto {

	private String courtyardName;
	/**
	 * 活动类型
	 */
	private String activityType;
	/**
	 * 活动时间
	 */
	private String activityTime;
	/**
	 * 活动地点
	 */
	private String activityPlace;
	/**
	 * 活动内容
	 */
	private String content;
	/**
	 * 发布活动人的昵称
	 */
	private String userName;
	/**
	 * 讨论组人数
	 */
//	private int groupMemberAmounts;
	/**
	 * 匹配相同兴趣的人
	 */
//	private List<MatchInterestDto> matchsInterests;
	
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}
	public String getActivityPlace() {
		return activityPlace;
	}
	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
//	public int getGroupMemberAmounts() {
//		return groupMemberAmounts;
//	}
//	public void setGroupMemberAmounts(int groupMemberAmounts) {
//		this.groupMemberAmounts = groupMemberAmounts;
//	}
//	public List<MatchInterestDto> getMatchsInterests() {
//		return matchsInterests;
//	}
//	public void setMatchsInterests(List<MatchInterestDto> matchsInterests) {
//		this.matchsInterests = matchsInterests;
//	}
	public String getCourtyardName() {
		return courtyardName;
	}
	public void setCourtyardName(String courtyardName) {
		this.courtyardName = courtyardName;
	}
	
	
}
