/**
 * 
 */
package cn.dayuanzi.dao;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.ActivityReply;

/** 
 * @ClassName: Activity 
 * @Description: TODO
 * @author qiuyingxiang
 * @date 2015年6月8日 下午7:11:08 
 *  
 */
@Repository
public class ActivityReplyDao extends BaseDao<Long, ActivityReply>{

	/**
	 * 查询评论
	 */
	@SuppressWarnings("unchecked")
	public List<ActivityReply> findActivityReply(String orderByProperty, boolean isAsc, final Criterion... criterions){
		Criteria c = createCriteria(criterions);
		if(StringUtils.isNotBlank(orderByProperty)){
			if (isAsc) {
				c.addOrder(Order.asc(orderByProperty));
			} else {
				c.addOrder(Order.desc(orderByProperty));
			}
		}
		return c.list();
	}
	
	
}
