package cn.dayuanzi.resp;

import java.util.ArrayList;
import java.util.List;

import cn.dayuanzi.model.ActivityInfo;
import cn.dayuanzi.util.DateTimeUtil;


/**
 * 
 * @author : leihc
 * @date : 2015年6月12日
 * @version : 1.0
 */
public class ActivityDto {

	private long id;
	/**
	 * 活动社区ID
	 */
	private String courtyardName;
	/**
	 * 活动标题
	 */
	private String activityTitle;
	/**
	 * 活动有效期的起始时间
	 */
	private String startTime;
	/**
	 * 活动有效期的截止时间
	 */
	private String endTime;
	/**
	 * banner图片
	 */
	private String image; 
	/**
	 * 活动内容
	 */
	private String content;
	/**
	 * 活动发布时间
	 */
	private String createTime;
	/**
	 * 发布者
	 */
	private String managerName;
	/**
	 * 不能报名
	 */
	private boolean signDisable;
	/**
	 * 人数限制
	 */
	private int peoplesLimit;
	/**
	 * 报名人数
	 */
	private int joinedAmount;
	/**
	 * 回复数
	 */
	private int replyAmount;
	/**
	 * 点赞数
	 */
	private int laudAmount;
	/**
	 * 删除列
	 */
	private String removeActivity="";
	/**
	 * 是否面向所有社区
	 */
	private boolean toAllCourtyard;
	/**
	 * 面向的社区
	 */
	private List<CourtyardDto> courtyards=new ArrayList<CourtyardDto>();
	
	public ActivityDto(ActivityInfo info){
		this.id = info.getId();
		this.activityTitle = info.getActivity_title();
		this.startTime = DateTimeUtil.formatDateTime(info.getStartTime());
		this.endTime = DateTimeUtil.formatDateTime(info.getEndTime());
		this.image = info.getImage();
		this.content = info.getContent();
		this.createTime = DateTimeUtil.formatDateTime(info.getCreateTime());
		this.signDisable = info.isSignDisable();
		this.peoplesLimit = info.getPeoplesLimit();
	}
	
	public boolean isSignDisable() {
		return signDisable;
	}

	public void setSignDisable(boolean signDisable) {
		this.signDisable = signDisable;
	}

	public int getPeoplesLimit() {
		return peoplesLimit;
	}

	public void setPeoplesLimit(int peoplesLimit) {
		this.peoplesLimit = peoplesLimit;
	}

	public String getCourtyardName() {
		return courtyardName;
	}
	public void setCourtyardName(String courtyardName) {
		this.courtyardName = courtyardName;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public int getJoinedAmount() {
		return joinedAmount;
	}

	public void setJoinedAmount(int joinedAmount) {
		this.joinedAmount = joinedAmount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRemoveActivity() {
		return removeActivity;
	}

	public void setRemoveActivity(String removeActivity) {
		this.removeActivity = removeActivity;
	}

	public boolean isToAllCourtyard() {
		return toAllCourtyard;
	}

	public void setToAllCourtyard(boolean toAllCourtyard) {
		this.toAllCourtyard = toAllCourtyard;
	}

	public List<CourtyardDto> getCourtyards() {
		return courtyards;
	}

	public void setCourtyards(List<CourtyardDto> courtyards) {
		this.courtyards = courtyards;
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
	
	
}
