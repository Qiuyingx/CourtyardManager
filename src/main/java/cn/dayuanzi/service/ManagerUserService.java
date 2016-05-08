package cn.dayuanzi.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.dayuanzi.dao.CourtYardDao;
import cn.dayuanzi.dao.ManagerUserDao;
import cn.dayuanzi.exception.GeneralLogicException;
import cn.dayuanzi.model.CourtYard;
import cn.dayuanzi.model.ManagerUser;
import cn.dayuanzi.pojo.Role;
import cn.dayuanzi.resp.DataResp;
import cn.dayuanzi.resp.ManagerUserDto;

/**
 * 
 *
 * @author : leihc
 * @date : 2015年5月17日
 * 
 */
@Service
public class ManagerUserService {

	@Autowired
	private ManagerUserDao managerUserDao;
	@Autowired
	private CourtYardDao courtyardDao;
	
	
	@Transactional(readOnly = true)
	public ManagerUser findUserByName(String userName){
		Assert.notNull(userName, "userName不能为空");
		return this.managerUserDao.findUniqueBy("userName", userName);
	}
	@Transactional
	public void addManagerUser(String userName,String password, Role role, Long courtyardId){
		if(role!=Role.ROOT){
			CourtYard courtyard = this.courtyardDao.get(courtyardId);
			if(courtyard==null){
				throw new GeneralLogicException("请选择社区");
			}
		}
		if(findUserByName(userName.trim())!=null){
			throw new GeneralLogicException("用户名已被使用了");
		}
		ManagerUser managerUser = new ManagerUser();
		managerUser.setUserName(userName.trim());
		managerUser.setPassword(DigestUtils.md5Hex(password));
		managerUser.setRole(role.ordinal());
		if(courtyardId!=null){
			managerUser.setCourtyardId(courtyardId);
		}
		this.managerUserDao.save(managerUser);
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public DataResp findManagerUsers(Long courtyardId,String userName){
		Criteria c = this.managerUserDao.createCriteria();
		if(courtyardId!=null&&courtyardId>0){
			CourtYard courtyard = this.courtyardDao.get(courtyardId);
			if(courtyard==null){
				throw new GeneralLogicException("选择的社区不存在");
			}
			c.add(Restrictions.eq("courtyardId", courtyardId));
		}
		if(StringUtils.isNotBlank(userName)){
			c.add(Restrictions.like("userName", "%"+userName.trim()+"%"));
		}
		List<ManagerUser> managerUsers = c.list();
		DataResp resp = new DataResp();
		for(ManagerUser managerUser : managerUsers){
			ManagerUserDto dto = new ManagerUserDto();
			dto.setId(managerUser.getId());
			dto.setUserName(managerUser.getUserName());
			if(managerUser.getRole()!=Role.ROOT.ordinal()){
				CourtYard courtyard = this.courtyardDao.get(managerUser.getCourtyardId());
				if(courtyard!=null){
					dto.setCourtyard(courtyard.getCourtyard_name());
				}else{
					dto.setCourtyard("无");
				}
			}
			dto.setRole(Role.values()[managerUser.getRole()].getDescription());
			resp.getRows().add(dto);
		}
		return resp;
	}
	
	@Transactional
	public void modifyPw(long managerId,String oldLoginPass,String newLoginPass ){
		ManagerUser user = managerUserDao.get(managerId);
		if(!user.getPassword().equals(DigestUtils.md5Hex(oldLoginPass))){
			throw new GeneralLogicException("你输入的老密码不正确");
		}
		user.setPassword(DigestUtils.md5Hex(newLoginPass));
		this.managerUserDao.saveOrUpdate(user);
	}
}
