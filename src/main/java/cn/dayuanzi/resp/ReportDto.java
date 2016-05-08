package cn.dayuanzi.resp;

import cn.dayuanzi.model.User;
import cn.dayuanzi.model.UserReport;
import cn.dayuanzi.service.ServiceRegistry;
import cn.dayuanzi.util.DateTimeUtil;

/**
 * 
 * @author : leihc
 * @date : 2015年5月22日
 * @version : 1.0
 */
public class ReportDto {

	/**
	 * 举报ID
	 */
	private long id;
	/**
	 * 处理状态,0 举报，1已处理
	 */
	private int status;
	/**
	 * 社区名称
	 */
	private String courtyardName;
	/**
	 * 举报者
	 */
	private String reporter;
	/**
	 * 举报时间
	 */
	private String reportTime;
	/**
	 * 举报的帖子标题
	 */
	private String title;
	/**
	 * 帖子发帖者
	 */
	private String sender;
	/**
	 * 帖子内容
	 */
	private String content;
	/**
	 * 回复数量
	 */
	private int replyAmount;
	/**
	 * 点赞数量
	 */
	private int laudAmount;
	/**
	 * 发帖时间
	 */
	private String createTime;
	
	public ReportDto(UserReport userReport){
		this.id = userReport.getId();
		this.status = userReport.getStatus();
		User reporter = ServiceRegistry.getUserService().findUserById(userReport.getUser_id());
		this.reporter = reporter.getNickname();
		this.reportTime = DateTimeUtil.formatDateTime(userReport.getCreate_time());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCourtyardName() {
		return courtyardName;
	}

	public void setCourtyardName(String courtyardName) {
		this.courtyardName = courtyardName;
	}

	
}
