package cn.dayuanzi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 
 * @author : leihc
 * @date : 2015年6月11日
 * @version : 1.0
 */
@Entity
@Table(name = "t_shop")
public class Shop extends VersionPersistentSupport {

	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 商品价格
	 */
	private int price;
	/**
	 * 库存限制
	 */
	private int stockLimit;
	/**
	 * 图片列表
	 */
	private List<String>  listImage;
	
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Type(type = "cn.framework.persist.db.type.StringList")
	@Column(nullable = true, columnDefinition = "varchar(256) default null")
	public List<String> getListImage() {
		return listImage;
	}
	public void setListImage(List<String> listImage) {
		this.listImage = listImage;
	}
	
	
	
	
	
}
