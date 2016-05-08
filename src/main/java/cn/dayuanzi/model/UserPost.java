package cn.dayuanzi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import cn.dayuanzi.pojo.ContentType;
import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 用户帖子
 *  
 * @author : leihc
 * @date : 2015年4月15日 上午10:32:57
 * @version : 1.0
 */
@Entity
@Table(name = "t_post")
public class UserPost extends VersionPersistentSupport {
	
	/**
	 * 院子ID
	 */
	protected long courtyard_id;
	
	/**
	 * 发帖人ID
	 */
	protected long user_id;
	
	/**
	 * 标题
	 */
	protected String title;
	
	/**
	 * 内容
	 */
	protected String content;
	
	/**
	 * 发布的图片保存本地路径
	 */
	protected List<String> image_names;
	
	/**
	 * 创建时间
	 */
	protected long create_time;
	/**
	 * 邀约(1),邻居帮帮(2),分享(3),活动(4);
	 */
	private ContentType content_type;
	/**
	 * 邻居帮帮的标签
	 */
	private int tag;
	/**
	 * 是否是介绍自己
	 */
	private boolean myself;
	/**
	 * 邻居帮帮是否是紧急求助
	 */
	private int priority;
	/**
	 * 帮帮悬赏额
	 */
	private int reward;
	/**
	 * 帮帮采纳的回复ID
	 */
	private long acceptId;

	public UserPost(){
		
	}
	
	public UserPost(long courtyardId, long userId, String title, String content){
		this.courtyard_id = courtyardId;
		this.user_id = userId;
		this.title = title;
		this.content = content;
		this.create_time = System.currentTimeMillis();
	}
	
	public UserPost(long courtyardId, long userId, int tag,String content){
		this.courtyard_id = courtyardId;
		this.user_id = userId;
		this.tag = tag;
		this.content = content;
		this.create_time = System.currentTimeMillis();
	}

	

	public boolean isMyself() {
		return myself;
	}

	public void setMyself(boolean myself) {
		this.myself = myself;
	}

	public long getCourtyard_id() {
		return courtyard_id;
	}

	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}

	/**
	 * @return the user_id
	 */
	public long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the image_names
	 */
	@Type(type = "cn.framework.persist.db.type.StringList")
	@Column(nullable = true, columnDefinition = "varchar(256) default null")
	public List<String> getImage_names() {
		return image_names;
	}

	/**
	 * @param image_names the image_names to set
	 */
	public void setImage_names(List<String> image_names) {
		this.image_names = image_names;
	}

	/**
	 * @return the create_time
	 */
	public long getCreate_time() {
		return create_time;
	}

	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	public int getContent_type() {
		return content_type.getValue();
	}

	public void setContent_type(int content_type) {
		this.content_type = ContentType.values()[content_type-1];
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public long getAcceptId() {
		return acceptId;
	}

	public void setAcceptId(long acceptId) {
		this.acceptId = acceptId;
	}
	
}
