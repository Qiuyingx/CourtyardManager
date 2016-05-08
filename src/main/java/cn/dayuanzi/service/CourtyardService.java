package cn.dayuanzi.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dayuanzi.dao.CourtYardDao;
import cn.dayuanzi.model.CourtYard;
import cn.dayuanzi.resp.CourtyardDto;
import cn.dayuanzi.resp.DataResp;

/**
 * 
 * @author : leihc
 * @date : 2015年5月19日
 * @version : 1.0
 */
@Service
public class CourtyardService {

	@Autowired
	private CourtYardDao courtyardDao;
	
	@Transactional(readOnly = true)
	public DataResp loadCourtyard(int cityId){
		List<CourtYard> courtyards = this.courtyardDao.find(Restrictions.eq("city_id", cityId));
		DataResp resp = new DataResp();
		for(CourtYard courtyard : courtyards){
			resp.getRows().add(new CourtyardDto(courtyard));
		}
		return resp;
	}
}
