package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.PersistentSupport;

/**
 * 物业公司信息表
 * 
 * @author : leihc
 * @date : 2015年5月4日
 * @version : 1.0
 */
@Entity
@Table(name = "t_property_company")
public class PropertyCompany extends PersistentSupport {

	/**
	 * 公司名称
	 */
	private String company_name;
	/**
	 * 公司的支付宝账号
	 */
	private String alipay_acount;
	
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getAlipay_acount() {
		return alipay_acount;
	}
	public void setAlipay_acount(String alipay_acount) {
		this.alipay_acount = alipay_acount;
	}
	
	
}
