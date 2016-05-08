package cn.dayuanzi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.UserInterest;

/**
 * 
 * @author : leihc
 * @date : 2015年4月16日 下午6:30:41
 * @version : 1.0
 */
@Repository
public class UserInterestDao extends BaseDao<Long, UserInterest> {

	@SuppressWarnings("unchecked")
	public List<Integer> findInterests(long user_id){
		Criteria criteria = this.createCriteria(Restrictions.eq("user_id", user_id));
		ProjectionList list = Projections.projectionList();
		list.add(Projections.property("interest_id"));
		criteria.setProjection(list);
		return criteria.list();
	}
	
	public void removeInterests(long user_id){
		String hql = "delete from UserInterest where user_id = ?";
		this.batchExecute(hql, user_id);
	}
}
