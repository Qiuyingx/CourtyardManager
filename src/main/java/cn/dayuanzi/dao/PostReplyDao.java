package cn.dayuanzi.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.PostReply;

/**
 * 
 * @author : leihc
 * @date : 2015年4月21日 上午11:45:27
 * @version : 1.0
 */
@Repository
public class PostReplyDao extends BaseDao<Long, PostReply> {

	/**
	 * 获取回复指定评论的用户ID
	 * @param replyId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getReplyer(long replyId){
		Criteria criteria = this.createCriteria(Restrictions.eq("reply_id", replyId));
		ProjectionList list = Projections.projectionList();
		list.add(Projections.property("replyer_id"));
		criteria.setProjection(list);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PostReply> findPostReplys(String orderByProperty, boolean isAsc, final Criterion... criterions){
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
