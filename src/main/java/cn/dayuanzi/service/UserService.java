package cn.dayuanzi.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dayuanzi.config.ExpInfo;
import cn.dayuanzi.config.InterestConf;
import cn.dayuanzi.config.LindouInfo;
import cn.dayuanzi.dao.CourtYardDao;
import cn.dayuanzi.dao.ExpDetailDao;
import cn.dayuanzi.dao.LindouDetailDao;
import cn.dayuanzi.dao.NoticeDao;
import cn.dayuanzi.dao.UserDao;
import cn.dayuanzi.dao.UserInterestDao;
import cn.dayuanzi.dao.UserLinDouDao;
import cn.dayuanzi.dao.UserSettingDao;
import cn.dayuanzi.dao.ValidateUserDao;
import cn.dayuanzi.exception.GeneralLogicException;
import cn.dayuanzi.model.CourtYard;
import cn.dayuanzi.model.ExpDetail;
import cn.dayuanzi.model.LindouDetail;
import cn.dayuanzi.model.Notice;
import cn.dayuanzi.model.User;
import cn.dayuanzi.model.UserLinDou;
import cn.dayuanzi.model.UserSetting;
import cn.dayuanzi.model.ValidateUser;
import cn.dayuanzi.pojo.NoticeType;
import cn.dayuanzi.pojo.Role;
import cn.dayuanzi.pojo.ThingsAdder;
import cn.dayuanzi.pojo.UserSession;
import cn.dayuanzi.resp.DataResp;
import cn.dayuanzi.resp.UserDto;
import cn.dayuanzi.resp.ValidateInfoResp;
import cn.dayuanzi.util.ApnsUtil;

/**
 * 
 * @author : leihc
 * @date : 2015年5月22日
 * @version : 1.0
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private CourtYardDao courtyardDao;
	@Autowired
	private ValidateUserDao validateUserDao;
	@Autowired
	private UserInterestDao userInterestDao;
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private UserSettingDao userSettingDao;
	@Autowired
	private UserLinDouDao userLindouDao;
	@Autowired
	private LindouDetailDao lindouDetailDao;
	@Autowired
	private UserLinDouDao userLinDouDao;
	@Autowired
	private ExpDetailDao expDetailDao;
	
	@Transactional(readOnly = true)
	public User findUserById(long userId){
		return this.userDao.get(userId);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public DataResp findUsers(long courtyardId,long start, long end, int page, int rows){
		CourtYard courtyard = this.courtyardDao.get(courtyardId);
//		if(courtyard==null){
//			throw new GeneralLogicException("请选择社区");
//		}
		Criteria criteria = this.userDao.createCriteria();
		Criteria criteriaCount = this.userDao.createCriteria();
		if(courtyardId > 0){
			criteria.add(Restrictions.eq("courtyard_id", courtyardId));
			criteriaCount.add(Restrictions.eq("courtyard_id", courtyardId));
		}
		if(start!=0 && end!=0 && start<=end){
			criteria.add(Restrictions.gt("register_time", start));
			criteria.add(Restrictions.lt("register_time", end));
			criteriaCount.add(Restrictions.gt("register_time", start));
			criteriaCount.add(Restrictions.lt("register_time", end));
		}
		criteriaCount.setProjection(Projections.rowCount());
		long total = (long)criteriaCount.uniqueResult();
		
		criteria.addOrder(Order.desc("register_time"));
		criteria.setMaxResults(rows);
		criteria.setFirstResult((page - 1) * rows);
		List<User> users = criteria.list();
		
		DataResp resp = new DataResp();
		resp.setTotal((int)total);
		for(User user : users){
			UserDto dto = new UserDto(user);
			if(courtyard!=null){
				dto.setCourtyardName(courtyard.getCourtyard_name());
			}else{
				CourtYard yard = this.courtyardDao.get(user.getCourtyard_id());
				if(yard!=null){
					dto.setCourtyardName(yard.getCourtyard_name());
				}else{
					dto.setCourtyardName("未知");
				}
			}
			ValidateUser validateUser = validateUserDao.findValidateUser(user.getId(), user.getCourtyard_id());
			if(validateUser==null){
				dto.setValidateStatus("未申请验证");
			}else{
				if(validateUser.getValidate_status()==1){
					dto.setValidateStatus("已验证");
				}else if(validateUser.getValidate_status()==2){
					dto.setValidateStatus("未通过审核");
				}else{
					dto.setValidateStatus("待审核");
				}
			}
			List<Integer> interestIds = userInterestDao.findInterests(user.getId());
			if(!interestIds.isEmpty()){
				StringBuilder b = new StringBuilder();
				for(Integer interestId : interestIds){
					if(InterestConf.getInterests().get(interestId)!=null)
					b.append(InterestConf.getInterests().get(interestId).getInterest()).append("/");
				}
				dto.setInstrests(b.toString());
			}
			UserLinDou userLindou = userLindouDao.get(user.getId());
			if(userLindou!=null){
				dto.setLindou(userLindou.getAmount());
			}
			resp.getRows().add(dto);
		}
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public DataResp findTargetUsers(long courtyardId,String nickName,int page, int rows){
		CourtYard courtyard = this.courtyardDao.get(courtyardId);
//		if(courtyard==null){
//			throw new GeneralLogicException("请选择社区");
//		}
		Criteria criteria = this.userDao.createCriteria();
		Criteria criteriaCount = this.userDao.createCriteria();
		if(courtyardId > 0){
			criteria.add(Restrictions.eq("courtyard_id", courtyardId));
			criteriaCount.add(Restrictions.eq("courtyard_id", courtyardId));
			if(StringUtils.isNotBlank(nickName)){
				criteria.add(Restrictions.like("nickname", "%"+nickName.trim()+"%"));
				criteriaCount.add(Restrictions.like("nickname", "%"+nickName.trim()+"%"));
			}
		}else{
			criteria.add(Restrictions.like("nickname", "%"+nickName.trim()+"%"));
			criteriaCount.add(Restrictions.like("nickname", "%"+nickName.trim()+"%"));
		}
		
		criteriaCount.setProjection(Projections.rowCount());
		long total = (long)criteriaCount.uniqueResult();
		
		criteria.addOrder(Order.desc("register_time"));
		criteria.setMaxResults(rows);
		criteria.setFirstResult((page - 1) * rows);
		List<User> users = criteria.list();
		
		DataResp resp = new DataResp();
		resp.setTotal((int)total);
		for(User user : users){
			UserDto dto = new UserDto(user);
			if(courtyard!=null){
				dto.setCourtyardName(courtyard.getCourtyard_name());
			}else{
				CourtYard yard = this.courtyardDao.get(user.getCourtyard_id());
				if(yard!=null){
					dto.setCourtyardName(yard.getCourtyard_name());
				}else{
					dto.setCourtyardName("未知");
				}
			}
			ValidateUser validateUser = validateUserDao.findValidateUser(user.getId(), user.getCourtyard_id());
			if(validateUser==null){
				dto.setValidateStatus("未申请验证");
			}else{
				if(validateUser.getValidate_status()==1){
					dto.setValidateStatus("已验证");
				}else if(validateUser.getValidate_status()==2){
					dto.setValidateStatus("未通过审核");
				}else{
					dto.setValidateStatus("待审核");
				}
			}
			List<Integer> interestIds = userInterestDao.findInterests(user.getId());
			if(!interestIds.isEmpty()){
				StringBuilder b = new StringBuilder();
				for(Integer interestId : interestIds){
					if(InterestConf.getInterests().get(interestId)!=null)
					b.append(InterestConf.getInterests().get(interestId).getInterest()).append("/");
				}
				dto.setInstrests(b.toString());
			}
			UserLinDou userLindou = userLindouDao.get(user.getId());
			if(userLindou!=null){
				dto.setLindou(userLindou.getAmount());
			}
			resp.getRows().add(dto);
		}
		return resp;
	}
	
	@Transactional(readOnly=true)
	public ValidateInfoResp findValidateInfo( long userId){
		UserSession userSession = UserSession.get();
		User user = this.userDao.get(userId);
		if(userSession.getRole()!=Role.ROOT&&userSession.getCourtyardId()!=user.getCourtyard_id()){
			throw new GeneralLogicException("您无法查看");
		}
		CourtYard courtyard = this.courtyardDao.get(user.getCourtyard_id());
		ValidateUser validateUser = validateUserDao.findValidateUser(user.getId(), user.getCourtyard_id());
		if(validateUser==null){
			throw new GeneralLogicException("用户还未提交验证请求");
		}
		ValidateInfoResp resp = new ValidateInfoResp(validateUser,courtyard.getCourtyard_name());
		return resp;
	}
	
	@Transactional(readOnly=false)
	public void passValidate(long userId,boolean passed){
		UserSession userSession = UserSession.get();
		User user = this.userDao.get(userId);
		if(userSession.getRole()!=Role.ROOT&&userSession.getCourtyardId()!=user.getCourtyard_id()){
			throw new GeneralLogicException("您无权审核");
		}
//		CourtYard courtyard = this.courtyardDao.get(user.getCourtyard_id());
		ValidateUser validateUser = validateUserDao.findValidateUser(user.getId(), user.getCourtyard_id());
		if(validateUser.getValidate_status()!=0){
			throw new GeneralLogicException("用户的验证申请已处理过了");
		}
		String content = "";
		if(passed){
			validateUser.setValidate_status(1);
			content = "恭喜你，住址验证成功";
			if(user.getInviteCode()>0){
				User inviter = this.userDao.get(user.getInviteCode());
				boolean isNeighbor = inviter.getCourtyard_id()==user.getCourtyard_id();
				LindouInfo info = isNeighbor?ThingsAdder.邀请邻居.getLindouInfo():ThingsAdder.邀请非邻居.getLindouInfo();
				ServiceRegistry.getUserService().addLindou(inviter.getId(), info.getLindou(), info.getRemark());
				ExpInfo expInfo = isNeighbor?ThingsAdder.邀请邻居.getExpInfo():ThingsAdder.邀请非邻居.getExpInfo();
				inviter.addExp(expInfo.getExp());
				ExpDetail exps = new ExpDetail(inviter.getId(),expInfo.getExp(),expInfo.getRemark());
				this.expDetailDao.save(exps);
			}
			
			LindouInfo info = ThingsAdder.社区认证.getLindouInfo();
			ServiceRegistry.getUserService().addLindou(user.getId(), info.getLindou(), info.getRemark());
			ExpInfo expInfo = ThingsAdder.社区认证.getExpInfo();
			user.addExp(expInfo.getExp());
			ExpDetail exp = new ExpDetail(user.getId(),expInfo.getExp(),expInfo.getRemark());
			this.expDetailDao.save(exp);
		}else{
			validateUser.setValidate_status(2);
			content = "对不起，你上传的照片与你提交的住址信息不符合，请重新提交";
		}
		this.validateUserDao.saveOrUpdate(validateUser);
		Notice notice = new Notice(NoticeType.系统通知.ordinal(),user.getCourtyard_id(),user.getId(),content);
		this.noticeDao.save(notice);
		UserSetting userSetting = userSettingDao.get(user.getId());
		if(userSetting==null || userSetting.isSystem()){
			ApnsUtil.getInstance().send(user, content);
		}
	}
	
	@Transactional
	public void addLindou(long userId, int amount, String from) {
		if(amount <=0 ){
			return;
		}
		UserLinDou userLinDou = getUserLinDou(userId);
		userLinDou.setAmount(userLinDou.getAmount()+amount);
		this.lindouDetailDao.save(new LindouDetail(userId, amount, from));
	}
	
	@Transactional
	public UserLinDou getUserLinDou(long userId) {
		UserLinDou lindou = this.userLinDouDao.get(userId);
		if(lindou==null){
			lindou = new UserLinDou();
			lindou.setId(userId);
			this.userLinDouDao.save(lindou);
		}
		return lindou;
	}
	
	@Transactional
	public void addLindouExp(long userId, int addType, int amount){
		User user  =this.userDao.get(userId);
		if(user==null){
			throw new GeneralLogicException("没有这个用户");
		}
		if(addType==1){
			this.addLindou(userId, amount, "后台发放");
		}else{
			user.addExp(amount);
		}
	}
}
