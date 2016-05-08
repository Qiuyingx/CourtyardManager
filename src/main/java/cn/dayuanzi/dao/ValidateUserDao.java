package cn.dayuanzi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.ValidateUser;

/**
 * 
 * @author : leihc
 * @date : 2015年4月19日 下午12:25:59
 * @version : 1.0
 */
@Repository
public class ValidateUserDao extends BaseDao<Long, ValidateUser> {

	public ValidateUser findValidateUser(long userId, long courtyardId){
		return this.findUnique(Restrictions.eq("user_id", userId),Restrictions.eq("courtyard_id", courtyardId));
	}
	/**
	 * 查找属于这个社区的用户ID
	 * @param courtyardId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Long> findUserId(long courtyardId){
		Criteria criteria = this.createCriteria(Restrictions.eq("courtyard_id", courtyardId),Restrictions.eq("validate_status", 1));
		ProjectionList list = Projections.projectionList();
		list.add(Projections.property("user_id"));
		criteria.setProjection(list);
		return criteria.list();
	}
}
