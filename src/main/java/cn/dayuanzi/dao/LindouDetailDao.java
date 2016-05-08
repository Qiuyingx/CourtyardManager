package cn.dayuanzi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.LindouDetail;

/**
 * 
 * @author : leihc
 * @date : 2015年6月7日
 * @version : 1.0
 */
@Repository
public class LindouDetailDao extends BaseDao<Long, LindouDetail> {

	/**
	 * 查找指定用户邻豆详细获取列表
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LindouDetail> findDetails(long userId){
		Criteria c = this.createCriteria(Restrictions.eq("user_id", userId));
		c.addOrder(Order.desc("create_time"));
		return c.list();
	}
}
