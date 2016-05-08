package cn.dayuanzi.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.ImToken;

/**
 * 
 * @author : leihc
 * @date : 2015年4月26日 下午3:18:24
 * @version : 1.0
 */
@Repository
public class ImTokenDao extends BaseDao<Long, ImToken> {

	
	public ImToken getLatestImToken(){
		Criteria c = createCriteria();
		c.addOrder(Order.desc("id"));
		c.setMaxResults(1);
		return (ImToken)c.uniqueResult();
	}
}
