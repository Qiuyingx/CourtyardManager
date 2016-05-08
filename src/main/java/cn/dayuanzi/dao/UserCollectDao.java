package cn.dayuanzi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.UserCollect;

/**
 * 
 * @author : leihc
 * @date : 2015年4月27日
 * @version : 1.0
 */
@Repository
public class UserCollectDao extends BaseDao<Long, UserCollect> {

	/**
	 * 根据收藏类型，按收藏时间排序查询收藏的帖子ID
	 * 
	 * @param courtyard_id
	 * @param collect_type
	 * @param current_page
	 * @param max_num
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getCollectTargetIds(long courtyard_id, int collect_type, int current_page, int max_num){
		Criteria criteria = this.createCriteria(Restrictions.eq("courtyard_id", courtyard_id),Restrictions.eq("collect_type", collect_type));
		if(current_page<=0){
			current_page = 1;
		}
		if(max_num > 1000){
			max_num = 1000;
		}else if(max_num <= 0){
			max_num = 1;
		}
		criteria.setFirstResult(((current_page-1)) * max_num);
		criteria.setMaxResults(max_num);
		criteria.addOrder(Order.desc("collect_time"));
		ProjectionList list = Projections.projectionList();
		list.add(Projections.property("target_id"));
		criteria.setProjection(list);
		return criteria.list();
	}
}
