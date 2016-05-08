package cn.dayuanzi.dao;

import java.util.List;

import cn.dayuanzi.model.CourtYard;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author : leihc
 * @date : 2015年4月24日 下午6:21:32
 * @version : 1.0
 */
@Repository
public class CourtYardDao extends BaseDao<Long, CourtYard> {

	@SuppressWarnings("unchecked")
	public List<Long> findCourtyardIds(int cityId){
		Criteria criteria = this.createCriteria(Restrictions.eq("city_id", cityId));
		ProjectionList list = Projections.projectionList();
		list.add(Projections.property("id"));
		criteria.setProjection(list);
		return criteria.list();
	}
}
