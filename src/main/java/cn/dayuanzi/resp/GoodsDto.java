package cn.dayuanzi.resp;

import java.util.ArrayList;
import java.util.List;

import cn.dayuanzi.model.Shop;

/**
 * 
 * @author : leihc
 * @date : 2015年6月11日
 * @version : 1.0
 */
public class GoodsDto {

	private long id;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 商品价格
	 */
	private int price;
	/**
	 * 库存限制，-1表示无限制
	 */
	private int stockLimit;
	/**
	 * 图片
	 */
	private String image;
	/**
	 * 商品描述
	 */
	private String remark;
	
	/**
	 * 商品状态，0正常 1下架
	 */
	private int status;
	/**
	 * 是否面向所有社区
	 */
	private boolean toAllCourtyard;
	/**
	 * 面向的社区
	 */
	private List<CourtyardDto> courtyards=new ArrayList<CourtyardDto>();
	
	public GoodsDto(Shop shop){
		this.id = shop.getId();
		this.goodsName = shop.getGoodsName();
		this.price = shop.getPrice();
		this.stockLimit = shop.getStockLimit();
		this.remark = shop.getRemark();
		this.status = shop.getStatus();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockLimit() {
		return stockLimit;
	}

	public void setStockLimit(int stockLimit) {
		this.stockLimit = stockLimit;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isToAllCourtyard() {
		return toAllCourtyard;
	}

	public void setToAllCourtyard(boolean toAllCourtyard) {
		this.toAllCourtyard = toAllCourtyard;
	}

	public List<CourtyardDto> getCourtyards() {
		return courtyards;
	}

	public void setCourtyards(List<CourtyardDto> courtyards) {
		this.courtyards = courtyards;
	}
	
}
