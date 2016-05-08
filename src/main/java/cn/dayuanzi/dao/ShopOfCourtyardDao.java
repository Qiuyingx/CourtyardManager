package cn.dayuanzi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.ShopOfCourtyard;

/**
 * 
 * @author : leihc
 * @date : 2015年6月23日
 * @version : 1.0
 */
@Repository
public class ShopOfCourtyardDao extends BaseDao<Long, ShopOfCourtyard> {

	/**
	 * 查询商品适用的所有社区
	 * @param goodsId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Long> findCourtyardIdsOfGoods(long goodsId){
		Criteria criteria = this.createCriteria(Restrictions.eq("goods_id", goodsId));
		ProjectionList list = Projections.projectionList();
		list.add(Projections.property("courtyard_id"));
		criteria.setProjection(list);
		return criteria.list();
	}
	/**
	 * 查询社区下所有的商品
	 * @param courtyard_id
	 * @param current_page
	 * @param max_num
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Long> findGoodsForCourtyardId(long courtyard_id){
		Criteria criteria = this.createCriteria(Restrictions.or(Restrictions.eq("courtyard_id", courtyard_id), Restrictions.eq("courtyard_id", 0l)));
		ProjectionList list = Projections.projectionList();
		list.add(Projections.property("goods_id"));
		criteria.setProjection(list);
		return criteria.list();
	}
	/**
	 * 删除商品与社区的对应关系
	 * @param goods_id
	 */
	public void removeCourtyardOfGoods(long goods_id){
		String hql = "delete from ShopOfCourtyard where goods_id = ?";
		this.batchExecute(hql, goods_id);
	}
}
