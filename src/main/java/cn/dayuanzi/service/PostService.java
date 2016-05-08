package cn.dayuanzi.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



















import cn.dayuanzi.config.InvitationConfig;
import cn.dayuanzi.dao.ActivityInfoDao;
import cn.dayuanzi.dao.ActivityLaudDao;
import cn.dayuanzi.dao.ActivityReplyDao;
import cn.dayuanzi.dao.ActivitySignUpDao;
import cn.dayuanzi.dao.CourtYardDao;
import cn.dayuanzi.dao.CourtyardOfActivityDao;
import cn.dayuanzi.dao.InvitationDao;
import cn.dayuanzi.dao.InvitationDiscussGroupDao;
import cn.dayuanzi.dao.InvitationRemovedDao;
import cn.dayuanzi.dao.ManagerUserDao;
import cn.dayuanzi.dao.NoticeDao;
import cn.dayuanzi.dao.PostLaudDao;
import cn.dayuanzi.dao.PostReplyDao;
import cn.dayuanzi.dao.UserDao;
import cn.dayuanzi.dao.UserFeedbackDao;
import cn.dayuanzi.dao.UserPostDao;
import cn.dayuanzi.dao.UserPostRemovedDao;
import cn.dayuanzi.dao.UserReportDao;
import cn.dayuanzi.dao.UserSettingDao;
import cn.dayuanzi.dao.ValidateUserDao;
import cn.dayuanzi.exception.GeneralLogicException;
import cn.dayuanzi.model.ActivityInfo;
import cn.dayuanzi.model.ActivitySignUp;
import cn.dayuanzi.model.CourtYard;
import cn.dayuanzi.model.CourtyardOfActivity;
import cn.dayuanzi.model.Invitation;
import cn.dayuanzi.model.InvitationRemoved;
import cn.dayuanzi.model.ManagerUser;
import cn.dayuanzi.model.Notice;
import cn.dayuanzi.model.User;
import cn.dayuanzi.model.UserFeedback;
import cn.dayuanzi.model.UserPost;
import cn.dayuanzi.model.UserPostRemoved;
import cn.dayuanzi.model.UserReport;
import cn.dayuanzi.model.UserSetting;
import cn.dayuanzi.pojo.ContentType;
import cn.dayuanzi.pojo.NoticeType;
import cn.dayuanzi.pojo.PlatForm;
import cn.dayuanzi.pojo.Role;
import cn.dayuanzi.pojo.UserSession;
import cn.dayuanzi.resp.ActivityDto;
import cn.dayuanzi.resp.ActivitySignDto;
import cn.dayuanzi.resp.CourtyardDto;
import cn.dayuanzi.resp.DataResp;
import cn.dayuanzi.resp.InvitationDto;
import cn.dayuanzi.resp.ReportDto;
import cn.dayuanzi.resp.Resp;
import cn.dayuanzi.resp.UserFeedbackDto;
import cn.dayuanzi.resp.UserPostDto;
import cn.dayuanzi.util.ApnsUtil;
import cn.dayuanzi.util.DateTimeUtil;

/**
 * 
 * @author : leihc
 * @date : 2015年5月20日
 * @version : 1.0
 */
@Service
public class PostService {

	@Autowired
	private UserPostDao userPostDao;
	
	@Autowired
	private InvitationDao invitationDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PostLaudDao postLaudDao;
	@Autowired
	private PostReplyDao postReplyDao;
	@Autowired
	private ValidateUserDao validateUserDao;
	@Autowired
	private CourtYardDao courtyardDao;
	@Autowired
	private UserReportDao userReportDao;
	@Autowired
	private UserPostRemovedDao userPostRemovedDao;
	@Autowired
	private InvitationRemovedDao invitationRemovedDao;
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private InvitationDiscussGroupDao invitationDiscussGroupDao;
	@Autowired
	private ActivityInfoDao activityInfoDao;
	@Autowired
	private UserFeedbackDao userFeedBackDao;
	@Autowired
	private UserSettingDao userSettingDao;
	@Autowired
	private ManagerUserDao managerUserDao;
	@Autowired
	private ActivitySignUpDao activitySignUpDao;
	@Autowired
	private CourtyardOfActivityDao courtyardOfActivityDao;
	@Autowired
	private ActivityLaudDao  activityLaudDao;
	@Autowired
	private ActivityReplyDao activityReplyDao;
	
	@Transactional(readOnly = true)
	public UserPost findUserPostById(long postId){
		return this.userPostDao.get(postId);
	}
	
	@SuppressWarnings("unchecked")
	private List<Long> findUser(List<Long> courtyardIds, long courtyardId, String nickname, String tel){
		if(courtyardId>0){
			CourtYard courtyard = this.courtyardDao.get(courtyardId);
			if(courtyard==null)
				throw new GeneralLogicException("社区不存在");
		}
		// 查询社区验证过的用户
		Criteria criteria = null;
		if(courtyardId>0){
			criteria = validateUserDao.createCriteria(Restrictions.eq("courtyard_id", courtyardId),Restrictions.eq("validate_status", 1));
		}else{
			criteria = validateUserDao.createCriteria(Restrictions.in("courtyard_id", courtyardIds),Restrictions.eq("validate_status", 1));
		}
		ProjectionList plist = Projections.projectionList();
		plist.add(Projections.property("user_id"));
		criteria.setProjection(plist);
		
		List<Long> userAtCourtyard=criteria.list();
		if(userAtCourtyard.isEmpty()){
			return null;
		}
		// 如果输入了昵称或者手机号码，查询相应的用户
		Disjunction disjunction = null;
		if(StringUtils.isNotBlank(tel)){
			disjunction =  Restrictions.disjunction();
			disjunction.add(Restrictions.like("tel", "%"+tel.trim()+"%"));
		}
		if(StringUtils.isNotBlank(nickname)){
			if(disjunction==null){
				disjunction =  Restrictions.disjunction();
			}
			disjunction.add(Restrictions.like("nickname", "%"+nickname.trim()+"%"));
		}
		
		Criteria userCriteria = this.userDao.createCriteria(Restrictions.in("id", userAtCourtyard));
		if(disjunction!=null){
			userCriteria.add(disjunction);
		}
		ProjectionList list = Projections.projectionList();
		list.add(Projections.property("id"));
		userCriteria.setProjection(list);
		return userCriteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public DataResp findInvition(int cityId, long courtyardId, String nickname, String tel,int page, int rows){
		
		List<Long> courtyardIds = this.courtyardDao.findCourtyardIds(cityId);
		if (courtyardIds.isEmpty()) {
			throw new GeneralLogicException("你选择的城市还没社区");
		}
		if(courtyardId > 0 && !courtyardIds.contains(courtyardId)){
			throw new GeneralLogicException("选择的城市里没有这个社区");
		}
		
		DataResp resp = new DataResp();
		List<Long> userIds = this.findUser(courtyardIds,courtyardId, nickname, tel);
		// 如果输入了手机或者昵称，但没有查到有相应的用户，就不用继续查询了
		if(userIds==null||userIds.isEmpty()){
			return resp;
		}
		Criteria criteria = this.invitationDao.createCriteria();
		Criteria c_count = this.invitationDao.createCriteria();
		if(courtyardId>0){
			criteria.add(Restrictions.eq("courtyard_id", courtyardId));
			c_count.add(Restrictions.eq("courtyard_id", courtyardId));
		}else{
			criteria.add(Restrictions.in("courtyard_id", courtyardIds));
			c_count.add(Restrictions.in("courtyard_id", courtyardIds));
		}
		criteria.add(Restrictions.in("user_id", userIds));
		c_count.add(Restrictions.in("user_id", userIds));
		criteria.addOrder(Order.desc("create_time"));
		criteria.setMaxResults(rows);
		criteria.setFirstResult((page - 1) * rows);
		List<Invitation> invitations = criteria.list();
		c_count.setProjection(Projections.rowCount());
		long total = (long)c_count.uniqueResult();
		resp.setTotal((int)total);
		CourtYard courtyard = null;
		if(courtyardId>0){
			courtyard = this.courtyardDao.get(courtyardId);
		}
		for(Invitation invitation : invitations){
			InvitationDto dto = new InvitationDto();
			dto.setActivityType(InvitationConfig.getInterests().get(invitation.getInvitation_type()).getInvitation());
			dto.setActivityTime(DateTimeUtil.formatDateTime(invitation.getActivity_time()));
			dto.setActivityPlace(invitation.getActivity_place());
			dto.setContent(invitation.getContent());
			if(courtyard!=null){
				dto.setCourtyardName(courtyard.getCourtyard_name());
			}else{
				CourtYard c = this.courtyardDao.get(invitation.getCourtyard_id());
				dto.setCourtyardName(c.getCourtyard_name());
			}
			User sender = this.userDao.get(invitation.getUser_id());
			dto.setUserName(sender.getNickname());
//			List<InvitationDiscussGroupMember> members = invitationDiscussGroupDao.find(Restrictions.eq("invitation_id", invitation.getId()));
//			dto.setGroupMemberAmounts(members.size());
			resp.getRows().add(dto);
		}
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public DataResp findPost(int cityId, long courtyardId, String nickname, String tel, int contentType,int page, int rows){
		
		List<Long> courtyardIds = this.courtyardDao.findCourtyardIds(cityId);
		if (courtyardIds.isEmpty()) {
			throw new GeneralLogicException("你选择的城市还没社区");
		}
		if(courtyardId > 0 && !courtyardIds.contains(courtyardId)){
			throw new GeneralLogicException("选择的城市里没有这个社区");
		}
		DataResp resp = new DataResp();
		List<Long> userIds = this.findUser(courtyardIds, courtyardId, nickname, tel);
		// 但没有查到有相应的用户，就不用继续查询了
		if(userIds==null||userIds.isEmpty()){
			return resp;
		}
		
		Criteria criteria = this.userPostDao.createCriteria();
		Criteria c_count = this.userPostDao.createCriteria();
		if(courtyardId > 0){
			criteria.add(Restrictions.eq("courtyard_id", courtyardId));
			c_count.add(Restrictions.eq("courtyard_id", courtyardId));
		}else{
			criteria.add(Restrictions.in("courtyard_id", courtyardIds));
			c_count.add(Restrictions.in("courtyard_id", courtyardIds));
		}
		if(!userIds.isEmpty()){
			criteria.add(Restrictions.in("user_id", userIds));
			c_count.add(Restrictions.in("user_id", userIds));
		}
		criteria.add(Restrictions.eq("content_type", contentType));
		c_count.add(Restrictions.eq("content_type", contentType));
		criteria.addOrder(Order.desc("create_time"));
		criteria.setMaxResults(rows);
		criteria.setFirstResult((page - 1) * rows);
		List<UserPost> userPosts = criteria.list();
		c_count.setProjection(Projections.rowCount());
		long total = (long)c_count.uniqueResult();
		resp.setTotal((int)total);
		CourtYard courtyard = null;
		if(courtyardId>0){
			courtyard = this.courtyardDao.get(courtyardId);
		}
		for(UserPost userPost : userPosts){
			UserPostDto dto = new UserPostDto(userPost);
			if(courtyard!=null){
				dto.setCourtyardName(courtyard.getCourtyard_name());
			}else{
				CourtYard c = this.courtyardDao.get(userPost.getCourtyard_id());
				dto.setCourtyardName(c.getCourtyard_name());
			}
			User sender = userDao.get(userPost.getUser_id());
			dto.setNickname(sender.getNickname());
			dto.setReplyAmount((int)this.postReplyDao.count(Restrictions.eq("post_id", userPost.getId()),Restrictions.eq("content_type", userPost.getContent_type())));
			dto.setLaudAmount((int)this.postLaudDao.count("post_id", userPost.getId()));
			resp.getRows().add(dto);
		}
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public DataResp findReportPost(int cityId, long courtyardId, String nickname, String tel, int contentType, int page, int rows,boolean handled){
		
		List<Long> courtyardIds = this.courtyardDao.findCourtyardIds(cityId);
		if (courtyardIds.isEmpty()) {
			throw new GeneralLogicException("你选择的城市还没社区");
		}
		if(courtyardId > 0 && !courtyardIds.contains(courtyardId)){
			throw new GeneralLogicException("选择的城市里没有这个社区");
		}
		
		DataResp resp = new DataResp();
		List<Long> userIds = this.findUser(courtyardIds,courtyardId, nickname, tel);
		// 如果输入了手机或者昵称，但没有查到有相应的用户，就不用继续查询了
		if(userIds==null||userIds.isEmpty()){
			return resp;
		}
		
		Criteria criteria = this.userReportDao.createCriteria();
		Criteria c_count = this.userReportDao.createCriteria();
		if(courtyardId>0){
			criteria.add(Restrictions.eq("courtyard_id", courtyardId));
			c_count.add(Restrictions.eq("courtyard_id", courtyardId));
		}else{
			criteria.add(Restrictions.in("courtyard_id", courtyardIds));
			c_count.add(Restrictions.in("courtyard_id", courtyardIds));
		}
		criteria.add(Restrictions.eq("content_type", contentType));
		criteria.add(Restrictions.eq("status", handled?0:1));
		c_count.add(Restrictions.eq("content_type", contentType));
		c_count.add(Restrictions.eq("status", handled?0:1));
		if( !userIds.isEmpty()){
			criteria.add(Restrictions.in("user_id", userIds));
			c_count.add(Restrictions.in("user_id", userIds));
		}
		criteria.addOrder(Order.desc("create_time"));
		criteria.setMaxResults(rows);
		criteria.setFirstResult((page - 1) * rows);
		c_count.setProjection(Projections.rowCount());
		long total = (long)c_count.uniqueResult();
		resp.setTotal((int)total);
		
		CourtYard courtyard = null;
		if(courtyardId>0){
			courtyard = this.courtyardDao.get(courtyardId);
		}
		List<UserReport> userReports = criteria.list();
		for(UserReport userReport : userReports){
			ReportDto dto = new ReportDto(userReport);
			if(userReport.getStatus()==0){
				UserPost userPost = this.userPostDao.get(userReport.getTarget_id());
				dto.setTitle(userPost.getTitle());
				User sender = this.userDao.get(userPost.getUser_id());
				dto.setSender(sender.getNickname());
				dto.setContent(userPost.getContent());
				dto.setReplyAmount((int)this.postReplyDao.count(Restrictions.eq("post_id", userPost.getId()),Restrictions.eq("content_type", userPost.getContent_type())));
				dto.setLaudAmount((int)this.postLaudDao.count("post_id", userPost.getId()));
				dto.setCreateTime(DateTimeUtil.formatDateTime(userPost.getCreate_time()));
			}else if(userReport.getStatus()==1){
				UserPostRemoved r = this.userPostRemovedDao.get(userReport.getTarget_id());
				dto.setTitle(r.getTitle());
				User sender = this.userDao.get(r.getUser_id());
				dto.setSender(sender.getNickname());
				dto.setContent(r.getContent());
				dto.setReplyAmount((int)this.postReplyDao.count(Restrictions.eq("post_id", r.getId()),Restrictions.eq("content_type", r.getContent_type())));
				dto.setLaudAmount((int)this.postLaudDao.count("post_id", r.getId()));
				dto.setCreateTime(DateTimeUtil.formatDateTime(r.getCreate_time()));
			}
			if(courtyard!=null){
				dto.setCourtyardName(courtyard.getCourtyard_name());
			}else{
				CourtYard c = this.courtyardDao.get(userReport.getCourtyard_id());
				dto.setCourtyardName(c.getCourtyard_name());
			}
			resp.getRows().add(dto);
		}
		
		return resp;
	}
	
	@Transactional(readOnly = false)
	public void removeReportPost(long managerId, long reportId){
		UserReport userReport = userReportDao.get(reportId);
		if(userReport == null){
			throw new GeneralLogicException("举报不存在");
		}
		ManagerUser managerUser = this.managerUserDao.get(managerId);
		if(managerUser.getRole()==Role.COURTYARD_CEO.ordinal()){
			throw new GeneralLogicException("你无权处理");
		}
		else if(managerUser.getRole()==Role.COURTYARD_ROOT.ordinal()&&managerUser.getCourtyardId()!=userReport.getCourtyard_id()){
			throw new GeneralLogicException("你无权处理");
		}
		if(userReport.getStatus() > 0){
			throw new GeneralLogicException("举报已处理");
		}
		if(userReport.getContent_type()==ContentType.分享.getValue() || userReport.getContent_type()==ContentType.邻居帮帮.getValue()){
			UserPost userPost = this.userPostDao.get(userReport.getTarget_id());
			if(userPost==null){
				throw new GeneralLogicException("帖子已经被删除了");
			}
			this.userPostDao.delete(userPost);
			userPostRemovedDao.save(new UserPostRemoved(userPost));
		}else{
			Invitation invitation = this.invitationDao.get(userReport.getTarget_id());
			if(invitation==null){
				throw new GeneralLogicException("邀约已经被删除了");
			}
			this.invitationDao.delete(invitation);
			invitationRemovedDao.save(new InvitationRemoved(invitation));
		}
		userReport.setStatus(1);
		this.userReportDao.save(userReport);
		// 记录管理人员的操作记录
	}
	@Transactional(readOnly = false)
	public void sendHelpNoticeForCourtyard(long managerId, long postId){
		UserPost userPost = this.userPostDao.get(postId);
		if(userPost==null || userPost.getContent_type()!=ContentType.邻居帮帮.getValue()){
			throw new GeneralLogicException("没有这条帮帮");
		}
		if(userPost.getPriority()<=0){
			throw new GeneralLogicException("非紧急求助");
		}
		Notice notice = new Notice(NoticeType.紧急通知.ordinal(),userPost.getCourtyard_id(),userPost.getContent());
		this.noticeDao.save(notice);
		List<Long> userAtCourtyard = this.validateUserDao.findUserId(userPost.getCourtyard_id());
		List<User> users = this.userDao.find(Restrictions.eq("courtyard_id", userPost.getCourtyard_id()),Restrictions.eq("last_login_platform", PlatForm.IOS.ordinal()),Restrictions.in("id", userAtCourtyard));
		for(User user : users){
			// 推送给IOS用户
			UserSetting userSetting = userSettingDao.get(user.getId());
			if(userSetting==null||userSetting.isSystem()){
				ApnsUtil.getInstance().send(user, "紧急通知:"+userPost.getContent());
			}
		}
	}

	/**
	 * @param managerId
	 * @param courtyardId
	 * @param content
	 * @return
	 */
	@Transactional(readOnly = false)
	public void sendNotice(long managerId, String content) {
		long courtyardId = UserSession.get().getCourtyardId();
		CourtYard courtyard = this.courtyardDao.get(UserSession.get().getCourtyardId());
		if(courtyard==null){
			throw new GeneralLogicException("只有社区管理员可以发布公告");
		}
		Notice notice = new Notice(NoticeType.社区公告.ordinal(),courtyardId,content);
		this.noticeDao.save(notice);
		List<Long> userAtCourtyard = this.validateUserDao.findUserId(courtyardId);
		List<User> users = this.userDao.find(Restrictions.eq("courtyard_id", courtyardId),Restrictions.eq("last_login_platform", PlatForm.IOS.ordinal()),Restrictions.in("id", userAtCourtyard));
		for(User user : users){
			// 推送给IOS用户
			UserSetting userSetting = userSettingDao.get(user.getId());
			if(userSetting==null||userSetting.isSystem()){
				ApnsUtil.getInstance().send(user, "社区公告:"+content);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void publishActivity(long activityId,int allCourtyard,List<Long> courtyardIds,String title, int sign, int peoplesLimit,String bannerUrl,String content, Timestamp startTime, Timestamp endTime){
		UserSession userSession = UserSession.get();
		if(StringUtils.isNotBlank(bannerUrl)&&bannerUrl.startsWith("/")){
			bannerUrl = bannerUrl.substring(1);
		}
		if(allCourtyard!=0){
			List<CourtYard> courtyards = this.courtyardDao.get(courtyardIds);
			if(courtyards.size()!=courtyardIds.size()){
				throw new GeneralLogicException("参数错误");
			}
		}
		ActivityInfo info = null;
		if(activityId==0){
			info = new ActivityInfo(0l, title, content, startTime, endTime, userSession.getUserId());
		}else{
			info = this.activityInfoDao.get(activityId);
			if(info==null){
				throw new GeneralLogicException("没有这个活动");
			}
			courtyardOfActivityDao.removeCourtyardOfAcitivty(activityId);
			info.setActivity_title(title);
			info.setContent(content);
			info.setStartTime(startTime);
			info.setEndTime(endTime);
			
		}
		info.setSignDisable(sign==1);
		if(!info.isSignDisable()){
			info.setPeoplesLimit(peoplesLimit);
		}
		info.setImage(bannerUrl);
		this.activityInfoDao.saveOrUpdate(info);
		// 所有社区
		if(allCourtyard==0){
			courtyardOfActivityDao.save(new CourtyardOfActivity(0l, info.getId()));
		}else{
			for(long courtyardId : courtyardIds){
				courtyardOfActivityDao.save(new CourtyardOfActivity(courtyardId, info.getId()));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Resp getActivitys(long courtyardId){
		Resp resp  = new Resp();
		Criteria c = this.activityInfoDao.createCriteria();
//		String courtyardName = null;
//		if(courtyardId > 0){
//			c.add(Restrictions.eq("courtyard_id", courtyardId));
//			CourtYard courtyard = this.courtyardDao.get(courtyardId);
//			courtyardName = courtyard.getCourtyard_name();
//		}
//		if(courtyardName == null){
//			courtyardName = "所有社区";
//		}
		List<ActivityInfo> infos = c.list();
		for(ActivityInfo info : infos){
			ActivityDto dto = new ActivityDto(info);
			dto.setManagerName(this.managerUserDao.get(info.getManagerId()).getUserName());
			dto.setLaudAmount((int)this.activityLaudDao.count("activity_id",info.getId()));
			dto.setReplyAmount((int)this.activityReplyDao.count(Restrictions.eq("activity_id", info.getId())));
			int joinedAmount = (int)activitySignUpDao.count(Restrictions.eq("activity_id", info.getId()));
			dto.setJoinedAmount(joinedAmount);
			List<Long> courtyardIds = courtyardOfActivityDao.findCourtyardIdsOfActivity(info.getId());
			if(courtyardIds.size()==1&&courtyardIds.get(0)==0){
				dto.setToAllCourtyard(true);
			}else{
				if(courtyardIds.size()>0){
					List<CourtYard> courtyards = this.courtyardDao.get(courtyardIds);
					for(CourtYard courtyard : courtyards){
						dto.getCourtyards().add(new CourtyardDto(courtyard));
					}
				}
			}
			resp.getRows().add(dto);
		}
		return resp;
	}
	
	@Transactional(readOnly = true)
	public Resp findSignUpForActivity(long activityId){
		List<ActivitySignUp> signUps = activitySignUpDao.findBy("activity_id", activityId);
		DataResp resp = new DataResp();
		for(ActivitySignUp signUp : signUps){
			ActivitySignDto dto = new ActivitySignDto(signUp);
			User user = this.userDao.get(signUp.getUser_id());
			CourtYard yard = this.courtyardDao.get(user.getCourtyard_id());
			if(yard!=null){
				dto.setCourtyardName(yard.getCourtyard_name());
			}else{
				dto.setCourtyardName("未知");
			}
			resp.getRows().add(dto);
		}
		return resp;
	}
	
	@Transactional(readOnly = false)
	public void deleteActivity(long activityId){
		this.activityInfoDao.delete(activityId);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public DataResp findFeedback(long courtyardId, String nickname, String tel,int page, int rows){
		
		DataResp resp = new DataResp();
//			List<Long> userIds = this.findUser(courtyardId, nickname, tel);
//			// 如果输入了手机或者昵称，但没有查到有相应的用户，就不用继续查询了
//			if(userIds==null||userIds.isEmpty()){
//				return resp;
//			}
		
		Criteria criteria = this.userFeedBackDao.createCriteria();
		if(courtyardId>0){
			criteria.add(Restrictions.eq("courtyard_id", courtyardId));
		}
//			if(!userIds.isEmpty()){
//				criteria.add(Restrictions.in("user_id", userIds));
//			}
		criteria.addOrder(Order.desc("create_time"));
		criteria.setMaxResults(rows);
		criteria.setFirstResult((page - 1) * rows);
		List<UserFeedback> feedbacks = criteria.list();
		resp.setTotal(page*rows+1);
		CourtYard courtyard = null;
		if(courtyardId > 0){
			courtyard = this.courtyardDao.get(courtyardId);
		}
		for(UserFeedback feedback : feedbacks){
			UserFeedbackDto dto = new UserFeedbackDto(feedback);
			User sender = userDao.get(feedback.getUser_id());
			dto.setNickname(sender.getNickname());
			if(courtyardId > 0 && courtyard!=null){
				dto.setCourtyardName(courtyard.getCourtyard_name());
			}else{
				CourtYard c = this.courtyardDao.get(feedback.getCourtyard_id());
				if(c!=null){
					dto.setCourtyardName(c.getCourtyard_name());
				}
			}
			resp.getRows().add(dto);
		}
		return resp;
	}
}
