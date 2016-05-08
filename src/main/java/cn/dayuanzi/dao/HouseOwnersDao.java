package cn.dayuanzi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.HouseOwners;

/**
 * 
 * @author : leihc
 * @date : 2015年4月19日 上午10:59:08
 * @version : 1.0
 */
@Repository
public class HouseOwnersDao extends BaseDao<Long, HouseOwners> {

	/**
	 * 根据院子ID，证件ID查找业主信息，同一院子业主可能拥有多套房
	 * 
	 * @param yardId
	 * @param cardId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HouseOwners> findHouseOwnerByCardId(long yardId, String cardId){
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("courtyard_id", yardId));
		criteria.add(Restrictions.eq("card_id", cardId));
		return criteria.list();
	}
	
	/**
	 * 根据院子ID和房号查询业主信息
	 * 
	 * @param yardId
	 * @param house_id
	 * @return
	 */
	public HouseOwners findHouseOwnerByHouseId(long courtyardId, int term, int building, int unit, int house_id){
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("courtyard_id", courtyardId));
		criteria.add(Restrictions.eq("term_id", term));
		criteria.add(Restrictions.eq("building_id", building));
		criteria.add(Restrictions.eq("unit_id", unit));
		criteria.add(Restrictions.eq("house_id", house_id));
		return (HouseOwners)criteria.uniqueResult();
	}
}
