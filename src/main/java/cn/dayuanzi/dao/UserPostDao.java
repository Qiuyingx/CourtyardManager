package cn.dayuanzi.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.UserPost;

/**
 * 
 * @author : leihc
 * @date : 2015年4月16日 下午9:09:53
 * @version : 1.0
 */
@Repository
public class UserPostDao extends BaseDao<Long, UserPost> {
	
	/**
	 * 查询最新帖子
	 * 
	 * @param userId
	 * @param courtyardId
	 * @return
	 */
	public UserPost getLatestUserPost(long userId, long courtyardId){
		Criteria criteria = this.createCriteria(Restrictions.eq("courtyard_id", courtyardId),Restrictions.eq("user_id", userId));
		ProjectionList list = Projections.projectionList();
		list.add(Projections.max("id"));
		criteria.setProjection(list);
		Long maxUserPostId = (Long)criteria.uniqueResult();
		if(maxUserPostId!=null){
			return this.get(maxUserPostId);
		}
		// id = (select max)
		return null;
	}
}
