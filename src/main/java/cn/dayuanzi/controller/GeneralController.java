package cn.dayuanzi.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.dayuanzi.exception.GeneralLogicException;
import cn.dayuanzi.pojo.Role;
import cn.dayuanzi.pojo.UserSession;
import cn.dayuanzi.resp.DataResp;
import cn.dayuanzi.resp.Resp;
import cn.dayuanzi.resp.RoleDto;
import cn.dayuanzi.service.CourtyardService;
import cn.dayuanzi.service.ManagerUserService;
import cn.dayuanzi.service.PostService;
import cn.dayuanzi.service.UserService;
import cn.dayuanzi.util.DateTimeUtil;

/**
 * 
 * @author : leihc
 * @date : 2015年5月11日
 * @version : 1.0
 */
@Controller
public class GeneralController {

	@Autowired
	private CourtyardService courtyardService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private ManagerUserService managerUserService;
	
	
	
	/**
	 * 添加管理员用户
	 * @param userName
	 * @param password
	 * @param roleId
	 * @param courtyardId
	 * @return
	 */
	@RequestMapping(value = "addManagerUser.do")
	public ModelAndView addManagerUser(String userName,String password,int roleId,@RequestParam(required=false)Long courtyardId){
		Role role = Role.values()[roleId];
		if(role!=Role.ROOT && courtyardId==null){
			throw new GeneralLogicException("请选择社区");
		}
		UserSession userSession = UserSession.get();
		if(userSession.getRole()==Role.COURTYARD_CEO){
			throw new GeneralLogicException("您无权添加用户");
		}
		if(userSession.getRole()==Role.COURTYARD_ROOT&&userSession.getCourtyardId()!=courtyardId){
			throw new GeneralLogicException("您无权添加这个社区的管理员");
		}
		if(StringUtils.isBlank(userName)){
			throw new GeneralLogicException("用户名不能为空");
		}
		if(StringUtils.isBlank(password)){
			throw new GeneralLogicException("密码不能为空");
		}
		managerUserService.addManagerUser(userName, password, role, courtyardId);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", new Resp());
		return mav;
	}
	/**
	 * 修改自己密码
	 * 
	 * @param oldLoginPass
	 * @param newLoginPass
	 * @return
	 */
	@RequestMapping(value = "modifyPassword.do")
	public ModelAndView modifyPassword(String oldLoginPass,String newLoginPass){
		if(StringUtils.isBlank(oldLoginPass)){
			throw new GeneralLogicException("请输入原来的密码");
		}
		if(StringUtils.isBlank(newLoginPass)){
			throw new GeneralLogicException("请输入新密码");
		}
		UserSession userSession = UserSession.get();
		this.managerUserService.modifyPw(userSession.getUserId(), oldLoginPass, newLoginPass);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", new Resp());
		return mav;
	}
	
	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "logout.do")
	public ModelAndView logout(HttpSession session){
		session.invalidate();
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", new Resp());
		return mav;
	}
	
	/**
	 * 加载角色设置
	 * @return
	 */
	@RequestMapping(value = "loadRole.do")
	public ModelAndView loadRole(){
		DataResp resp = new DataResp();
		for(Role role : Role.values()){
			resp.getRows().add(new RoleDto(role));
		}
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	
	/**
	 * 查询管理员用户
	 * @param courtyardId
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "findManagerUsers.do")
	public ModelAndView findManagerUsers(@RequestParam(required=false)Long courtyardId, @RequestParam(required=false)String userName){
		UserSession userSession = UserSession.get();
		if(userSession.getRole()==Role.COURTYARD_ROOT){
			courtyardId = userSession.getCourtyardId();
		}else if(userSession.getRole()==Role.COURTYARD_CEO){
			throw new GeneralLogicException("您无权查看");
		}
		DataResp resp = this.managerUserService.findManagerUsers(courtyardId, userName);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	
	/**
	 * 加载指定城市的社区
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadCourtyard.do")
	public ModelAndView loadCourtyard(int cityId){
		DataResp resp = courtyardService.loadCourtyard(cityId);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	/**
	 * 查找指定社区的帖子
	 * 
	 * @param courtyardId
	 * @param nickname
	 * @param tel
	 * @param contentType
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "findPost.do")
	public ModelAndView findPost(int cityId, long courtyardId, @RequestParam(value="nickname",required=false) String nickname, @RequestParam(value="tel", required=false) String tel, int contentType, int page, int rows){
		if(cityId<=0){
			throw new GeneralLogicException("请选择城市");
		}
		if(contentType!=2&&contentType!=3 || courtyardId<0){
			throw new GeneralLogicException("参数错误");
		}
		UserSession userSession = UserSession.get();
		if(userSession.getRole()!=Role.ROOT && userSession.getCourtyardId()!=courtyardId){
			throw new GeneralLogicException("选择社区并且只能查看你社区的内容");
		}
		DataResp resp = postService.findPost(cityId, courtyardId, nickname, tel, contentType, page, rows);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	
	/**
	 * 查看指定社区的举报
	 * @param courtyardId
	 * @param nickname
	 * @param tel
	 * @param contentType
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "findReportPost.do")
	public ModelAndView findReportPost(int cityId, long courtyardId, @RequestParam(value="nickname",required=false) String nickname, @RequestParam(value="tel", required=false) String tel, int contentType, int page, int rows,boolean handled){
		if(cityId<=0){
			throw new GeneralLogicException("请选择城市");
		}
		if(contentType!=2&&contentType!=3 || courtyardId<0){
			throw new GeneralLogicException("参数错误");
		}
		DataResp resp = postService.findReportPost(cityId, courtyardId, nickname, tel, contentType, page, rows,handled);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	/**
	 * 删除被举报的帖子
	 * @param reportId
	 * @return
	 */
	@RequestMapping(value = "removeReportPost.do")
	public ModelAndView removeReportPost(long reportId){
		UserSession userSession = UserSession.get();
		postService.removeReportPost(userSession.getUserId(), reportId);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", new Resp());
		return mav;
	}
	/**
	 * 发送求助通知给社区用户
	 * @param postId
	 * @return
	 */
	@RequestMapping(value = "sendHelpNotice.do")
	public ModelAndView sendHelpNotice(long postId){
		UserSession userSession = UserSession.get();
		postService.sendHelpNoticeForCourtyard(userSession.getUserId(), postId);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", new Resp());
		return mav;
	}
	
	/**
	 * 查找指定社区邀约
	 * 
	 * @param courtyardId
	 * @param nickname
	 * @param tel
	 * @param contentType
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "findInvitation.do")
	public ModelAndView findInvitation(int cityId, long courtyardId, @RequestParam(value="nickname",required=false) String nickname, @RequestParam(value="tel", required=false) String tel, int page, int rows){
		if(cityId<=0){
			throw new GeneralLogicException("请选择城市");
		}
		if(courtyardId<0){
			throw new GeneralLogicException("参数错误");
		}
		DataResp resp = postService.findInvition(cityId,courtyardId, nickname, tel, page, rows);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	/**
	 * 发布公告
	 * @param courtyardId
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "sendNotice.do")
	public ModelAndView sendNotice(String content){
		UserSession userSession = UserSession.get();
//		if (userSession.getRole() == Role.COURTYARD_CEO) {
//			throw new GeneralLogicException("您不能发布公告");
//		}
		if(StringUtils.isBlank(content)){
			throw new GeneralLogicException("公告内容不能为空");
		}
		postService.sendNotice(userSession.getUserId(), content);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", new Resp());
		return mav;
	}
	
	/**
	 * 查找用户
	 * @param courtyardId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "findUsers.do")
	public ModelAndView findUsers(long courtyardId,String startTime, String endTime, int page, int rows){

		UserSession userSession = UserSession.get();
		if(userSession.getRole()==Role.COURTYARD_ROOT && userSession.getCourtyardId()!=courtyardId){
			throw new GeneralLogicException("不能查看这个社区的用户");
		}
		long start = 0,end=0;
		if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
			start = DateTimeUtil.getTime(startTime, "yyyy-MM-dd HH:mm");
			end = DateTimeUtil.getTime(endTime, "yyyy-MM-dd HH:mm");
		}
		DataResp resp = userService.findUsers(courtyardId,start, end, page, rows);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	
	@RequestMapping(value = "findTargetUsers.do")
	public ModelAndView findTargetUsers(long courtyardId,String nickName,int page, int rows){

		UserSession userSession = UserSession.get();
		if(userSession.getRole()==Role.COURTYARD_ROOT && userSession.getCourtyardId()!=courtyardId){
			throw new GeneralLogicException("不能查看这个社区的用户");
		}
		if(courtyardId==0&&StringUtils.isBlank(nickName)){
			throw new GeneralLogicException("请选择社区或者输入用户昵称");
		}
		DataResp resp = userService.findTargetUsers(courtyardId,nickName, page, rows);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	
	/**
	 * 查找用户的验证信息
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "showValidateInfo.do")
	public ModelAndView showValidateInfo(long userId){
		UserSession userSession = UserSession.get();
		if (userSession.getRole() == Role.COURTYARD_CEO) {
			throw new GeneralLogicException("您无法查看");
		}
		Resp resp = userService.findValidateInfo(userId);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	/**
	 * 指定用户审核通过
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "passValidate.do")
	public ModelAndView passValidate(long userId,boolean passed){
		UserSession userSession = UserSession.get();
		if (userSession.getRole() == Role.COURTYARD_CEO) {
			throw new GeneralLogicException("您无权审核");
		}
		userService.passValidate(userId,passed);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", new Resp());
		return mav;
	}
	
	@RequestMapping(value = "findFeedback.do")
	public ModelAndView findFeedback(long courtyardId, @RequestParam(value="nickname",required=false) String nickname, @RequestParam(value="tel", required=false) String tel, int page, int rows){
		UserSession userSession = UserSession.get();
		if (userSession.getRole() != Role.ROOT) {
			throw new GeneralLogicException("您无权查看");
		}
		Resp resp = this.postService.findFeedback(courtyardId, nickname, tel, page, rows);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	
	/**
	 * 发布活动
	 * @param title
	 * @param content
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "publishActivity.do")
	public ModelAndView publishActivity(HttpServletRequest request,long activityId,int allCourtyard,String title, int sign, @RequestParam(value="peoplesLimit",required=false)Integer peoplesLimit,String bannerUrl,String content, String startTime, String endTime){
		
		String[]  courtyardIds=request.getParameterValues("courtyardIds"); 
		if(courtyardIds==null||courtyardIds.length==0){
			throw new GeneralLogicException("请选择社区");
		}
		List<Long> courtyards = new ArrayList<Long>();
		for(String courtyardId : courtyardIds){
			courtyards.add(Long.parseLong(courtyardId));
		}
		UserSession userSession = UserSession.get();
		if (userSession.getRole() == Role.COURTYARD_CEO) {
			throw new GeneralLogicException("您不能发布活动");
		}
		if(userSession.getRole() == Role.COURTYARD_ROOT&&(courtyards.size()>1||!courtyards.contains(userSession.getCourtyardId()))){
			throw new GeneralLogicException("您只能在本社区发布活动");
		}
		
		if (StringUtils.isBlank(title)) {
			throw new GeneralLogicException("标题不能为空");
		}
		if (StringUtils.isBlank(content)) {
			throw new GeneralLogicException("内容不能为空");
		}
		if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
			throw new GeneralLogicException("请输入活动的有效时间");
		}
		if(sign!=0&&sign!=1 || (peoplesLimit!=null&&peoplesLimit<0)){
			throw new GeneralLogicException("参数错误");
		}
		long start = DateTimeUtil.getTime(startTime, "yyyy-MM-dd HH:mm");
		long end = DateTimeUtil.getTime(endTime, "yyyy-MM-dd HH:mm");
		if (start == 0 || end == 0) {
			throw new GeneralLogicException("请输入正确的活动的有效时间");
		}
		if(peoplesLimit==null){
			peoplesLimit=0;
		}
		this.postService.publishActivity(activityId,allCourtyard,courtyards,title, sign, peoplesLimit,bannerUrl,content, new Timestamp(start), new Timestamp(end));
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", new Resp());
		return mav;
	}
	/**
	 * 查看所有活动
	 * @param courtyardId
	 * @return
	 */
	@RequestMapping(value = "findActivitys.do")
	public ModelAndView findActivitys(Long courtyardId){
		UserSession userSession = UserSession.get();
		if (userSession.getRole() != Role.ROOT) {
			throw new GeneralLogicException("您无权查看");
		}
		Resp resp = this.postService.getActivitys(0);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	
	@RequestMapping(value = "findActivitySignUp.do")
	public ModelAndView findActivitySignUp(long activityId){
		UserSession userSession = UserSession.get();
		if (userSession.getRole() != Role.ROOT) {
			throw new GeneralLogicException("您无权查看");
		}
		Resp resp = this.postService.findSignUpForActivity(activityId);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	
	@RequestMapping(value = "deleteActivity.do")
	public ModelAndView deleteActivity(long activityId){
		UserSession userSession = UserSession.get();
		if (userSession.getRole() != Role.ROOT) {
			throw new GeneralLogicException("您无权删除活动");
		}
		this.postService.deleteActivity(activityId);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", new Resp());
		return mav;
	}
	
	/**
	 * 发经验邻豆
	 * @param userId
	 * @param addType
	 * @param amount
	 * @return
	 */
	@RequestMapping(value = "addLindouExp.do")
	public ModelAndView addLindouExp(long userId,int addType,int amount){
		UserSession userSession = UserSession.get();
		if (userSession.getRole() != Role.ROOT) {
			throw new GeneralLogicException("您无权发放");
		}
		if(addType!=1&&addType!=2){
			throw new GeneralLogicException("参数错误");
		}
		this.userService.addLindouExp(userId, addType, amount);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", new Resp());
		return mav;
	}
	
}
