package cn.dayuanzi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.CourtyardOfActivity;

/**
 * 
 * @author : leihc
 * @date : 2015年6月17日
 * @version : 1.0
 */
@Repository
public class CourtyardOfActivityDao extends BaseDao<Long, CourtyardOfActivity> {

	/**
	 * 查找指定活动的社区
	 * 
	 * @param activityId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Long> findCourtyardIdsOfActivity(long activityId){
		Criteria criteria = this.createCriteria(Restrictions.eq("activity_id", activityId));
		ProjectionList list = Projections.projectionList();
		list.add(Projections.property("courtyard_id"));
		criteria.setProjection(list);
		return criteria.list();
	}
	
	/**
	 * 查找该社区所有活动
	 * @param courtyard_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Long> findActivitysForCourtyardId(long courtyard_id){
		Criteria criteria = this.createCriteria(Restrictions.or(Restrictions.eq("courtyard_id", courtyard_id), Restrictions.eq("courtyard_id", 0l)));
		ProjectionList list = Projections.projectionList();
		list.add(Projections.property("activity_id"));
		criteria.setProjection(list);
		return criteria.list();
	}
	
	/**
	 * 删除该活动社区的关系
	 * 
	 * @param activity_id
	 */
	public void removeCourtyardOfAcitivty(long activity_id){
		String hql = "delete from CourtyardOfActivity where activity_id = ?";
		this.batchExecute(hql, activity_id);
	}
}
