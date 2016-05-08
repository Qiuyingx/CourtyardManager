package cn.dayuanzi.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dayuanzi.dao.CourtYardDao;
import cn.dayuanzi.dao.ShopDao;
import cn.dayuanzi.dao.ShopOfCourtyardDao;
import cn.dayuanzi.dao.UserDao;
import cn.dayuanzi.dao.UserOrderDao;
import cn.dayuanzi.exception.GeneralLogicException;
import cn.dayuanzi.model.CourtYard;
import cn.dayuanzi.model.Shop;
import cn.dayuanzi.model.ShopOfCourtyard;
import cn.dayuanzi.model.User;
import cn.dayuanzi.model.UserOrder;
import cn.dayuanzi.resp.CourtyardDto;
import cn.dayuanzi.resp.GoodsDto;
import cn.dayuanzi.resp.GoodsResp;
import cn.dayuanzi.resp.OrderDto;
import cn.dayuanzi.resp.Resp;
import cn.dayuanzi.util.DateTimeUtil;

/**
 * 
 * @author : leihc
 * @date : 2015年6月11日
 * @version : 1.0
 */
@Service
public class ShopService {

	@Autowired
	private ShopDao shopDao;
	@Autowired
	private CourtYardDao courtyardDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserOrderDao userOrderDao;
	@Autowired
	private ShopOfCourtyardDao shopOfCourtyardDao;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Resp findOrders(long courtyardId,int page, int rows){
		CourtYard courtyard = this.courtyardDao.get(courtyardId);
		Criteria c  = this.userOrderDao.createCriteria();
		Criteria c_count  = this.userOrderDao.createCriteria();
		if(courtyardId > 0 ){
			c.add(Restrictions.eq("courtyard_id", courtyardId));
			c_count.add(Restrictions.eq("courtyard_id", courtyardId));
		}
		c_count.setProjection(Projections.rowCount());
		long total = (long)c_count.uniqueResult();
		
		c.addOrder(Order.desc("create_time"));
		c.setMaxResults(rows);
		c.setFirstResult((page - 1) * rows);
		List<UserOrder> orders = c.list();
		
		Resp resp = new Resp();
		resp.setTotal((int)total);
		for(UserOrder order : orders){
			OrderDto dto = new OrderDto();
			dto.setOrderId(order.getOrder_id());
			if(courtyard!=null){
				dto.setCourtyardName(courtyard.getCourtyard_name());
			}else{
				CourtYard yard = this.courtyardDao.get(order.getCourtyard_id());
				if(yard!=null){
					dto.setCourtyardName(yard.getCourtyard_name());
				}else{
					dto.setCourtyardName("未知");
				}
			}
			User user = this.userDao.get(order.getUser_id());
			dto.setNickname(user.getNickname());
			Shop goods = this.shopDao.get(order.getGoods_id());
			dto.setGoodsName(goods.getGoodsName());
			dto.setPrice(goods.getPrice());
			dto.setAmount(order.getAmount());
			dto.setTel(order.getTel());
			dto.setAddress(order.getAddress());
			dto.setCreateTime(DateTimeUtil.formatDateTime(order.getCreate_time()));
			resp.getRows().add(dto);
		}
		return resp;
	}
	
	@Transactional
	public void addGoods(long goodsId,int allCourtyard,List<Long> courtyardIds,String goodsName,int price,int stockLimit,String imageUrl,String imagesUrl,String remark,int status){
		Shop goods = this.shopDao.get(goodsId);
		if(goods==null){
			goods = new Shop();
		}else{
			shopOfCourtyardDao.removeCourtyardOfGoods(goods.getId());
		}
		if(allCourtyard!=0){
			List<CourtYard> courtyards = this.courtyardDao.get(courtyardIds);
			if(courtyards.size()!=courtyardIds.size()){
				throw new GeneralLogicException("参数错误");
			}
		}
		if(StringUtils.isNotBlank(imageUrl)&&imageUrl.startsWith("/")){
			imageUrl = imageUrl.substring(1);
		}
		goods.setGoodsName(goodsName);
		goods.setPrice(price);
		goods.setRemark(remark);
		goods.setStockLimit(stockLimit);
		goods.setImage(imageUrl);
		goods.setStatus(status);
		if(StringUtils.isNotBlank(imagesUrl)){
			String[] tokens = imagesUrl.split("\\|");
			List<String> uris = new ArrayList<String>();
			for(String token : tokens){
				uris.add(token);
			}
			goods.setListImage(uris);
		}
		if(allCourtyard==0){
			shopOfCourtyardDao.save(new ShopOfCourtyard(0l,goods.getId()));
		}else{
			for(long courtyardId : courtyardIds){
				shopOfCourtyardDao.save(new ShopOfCourtyard(courtyardId, goods.getId()));
			}
		}
		this.shopDao.save(goods);
	}
	
	@Transactional
	public void modifyGoods(long goodsId,String goodsName,int price,int stockLimit,String image,String remark,int status){
		Shop goods = this.shopDao.get(goodsId);
		if(goods == null){
			throw new GeneralLogicException("没有这个商品");
		}
		goods.setGoodsName(goodsName);
		goods.setPrice(price);
		goods.setRemark(remark);
		goods.setStockLimit(stockLimit);
		goods.setImage(image);
		goods.setStatus(status);
		this.shopDao.save(goods);
	}
	
	@Transactional
	public void removeGoods(long goodsId,int status){
		Shop goods = this.shopDao.get(goodsId);
		if(goods == null){
			throw new GeneralLogicException("没有这个商品");
		}
		goods.setStatus(status);
		this.shopDao.saveOrUpdate(goods);
	}
	
	@Transactional(readOnly = true)
	public Resp getAllGoods(){
		List<Shop> goods = this.shopDao.getAll();
		Resp resp = new Resp();
		resp.setTotal(goods.size());
		for(Shop shop:goods){
			GoodsDto dto = new GoodsDto(shop);
			List<Long> courtyardIds = shopOfCourtyardDao.findCourtyardIdsOfGoods(shop.getId());
			if(courtyardIds.size()==1&&courtyardIds.get(0)==0l){
				dto.setToAllCourtyard(true);
			}else{
				if(courtyardIds.size()>0){
					List<CourtYard> courtyards = this.courtyardDao.get(courtyardIds);
					for(CourtYard courtyard : courtyards){
						dto.getCourtyards().add(new CourtyardDto(courtyard));
					}
				}
			}
			resp.getRows().add(dto);
		}
		return resp;
	}
	
	@Transactional(readOnly = true)
	public Resp showGoodsInfo(long goodsId){
		Shop goods = this.shopDao.get(goodsId);
		if(goods == null){
			throw new GeneralLogicException("没有这个商品");
		}
		GoodsResp resp = new GoodsResp(goods);
		return resp;
	}
}
