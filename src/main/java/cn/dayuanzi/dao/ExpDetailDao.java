/**
 * 
 */
package cn.dayuanzi.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.ExpDetail;


/** 
 * @ClassName: ExpDetailDao 
 * @Description: TODO
 * @author qiuyingxiang
 * @date 2015年6月13日 下午4:20:47 
 *  
 */
@Repository
public class ExpDetailDao extends BaseDao<Long, ExpDetail>{

	
	
	/**
	 * 查找指定用户经验详细获取列表
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExpDetail> findDetails(long userId){
		Criteria c = this.createCriteria(Restrictions.eq("user_id", userId));
		c.addOrder(Order.desc("create_time"));
		return c.list();
	}
}
