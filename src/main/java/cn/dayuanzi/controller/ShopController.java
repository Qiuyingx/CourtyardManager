package cn.dayuanzi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.dayuanzi.exception.GeneralLogicException;
import cn.dayuanzi.pojo.Role;
import cn.dayuanzi.pojo.UserSession;
import cn.dayuanzi.resp.Resp;
import cn.dayuanzi.service.ShopService;

/**
 * 
 * @author : leihc
 * @date : 2015年6月11日
 * @version : 1.0
 */
@Controller
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value = "findOrders.do")
	public ModelAndView findOrders(long courtyardId,int page, int rows){
		UserSession userSession = UserSession.get();
		if(userSession.getRole()!=Role.ROOT){
			throw new GeneralLogicException("不能查看订单");
		}
		Resp resp = shopService.findOrders(courtyardId,page,rows);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	
	@RequestMapping(value = "addGoods.do")
	public ModelAndView addGoods(HttpServletRequest request,long goodsId,int allCourtyard,String goodsName,int price,int stockLimit,String imageUrl,String imagesUrl,String remark,int status){
		String[]  courtyardIds=request.getParameterValues("courtyardIds"); 
		if(courtyardIds==null||courtyardIds.length==0){
			throw new GeneralLogicException("请选择社区");
		}
		List<Long> courtyards = new ArrayList<Long>();
		for(String courtyardId : courtyardIds){
			courtyards.add(Long.parseLong(courtyardId));
		}
		UserSession userSession = UserSession.get();
		if(userSession.getRole()!=Role.ROOT){
			throw new GeneralLogicException("不能添加商品");
		}
		if(StringUtils.isBlank(goodsName)){
			throw new GeneralLogicException("商品名不能为空");
		}
		if(price < 0){
			throw new GeneralLogicException("单价不能小于零");
		}
		if(status!=0&&status!=1){
			throw new GeneralLogicException("参数错误");
		}
		if(StringUtils.isNotBlank(remark)&&remark.length()>500){
			throw new GeneralLogicException("对不起，商品描述不能超过500个字符");
		}
		this.shopService.addGoods(goodsId,allCourtyard,courtyards,goodsName, price, stockLimit, imageUrl, imagesUrl,remark, status);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", new Resp());
		return mav;
	}
	
	/**
	 * 改变商品状态
	 * @param goodsId
	 * @param isUp true 上架  false 下架
	 * @return
	 */
	@RequestMapping(value = "changeGoodsStatus.do")
	public ModelAndView changeGoodsStatus(long goodsId,boolean isUp){
		UserSession userSession = UserSession.get();
		if(userSession.getRole()!=Role.ROOT){
			throw new GeneralLogicException("不能查看订单");
		}
		shopService.removeGoods(goodsId, isUp?0:1);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", new Resp());
		return mav;
	}
	
	@RequestMapping(value = "showGoodsInfo.do")
	public ModelAndView showGoodsInfo(long goodsId){
		UserSession userSession = UserSession.get();
		if(userSession.getRole()!=Role.ROOT){
			throw new GeneralLogicException("不能查看订单");
		}
		Resp resp = shopService.showGoodsInfo(goodsId);
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
	
	@RequestMapping(value = "getAllGoods.do")
	public ModelAndView getAllGoods(){
		UserSession userSession = UserSession.get();
		if(userSession.getRole()!=Role.ROOT){
			throw new GeneralLogicException("不能查看商品");
		}
		Resp resp = shopService.getAllGoods();
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("model", resp);
		return mav;
	}
}
