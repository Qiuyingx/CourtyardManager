package cn.dayuanzi.config;

/**
 * 
 * @author : leihc
 * @date : 2015年6月8日
 * @version : 1.0
 */
public class LindouInfo {
	/**
	 * id
	 */
	private int id;
	/**
	 * 图标图片
	 */
	private String image ;
	/**
	 * 每次可以获得邻豆
	 */
	private int lindou;
	/**
	 * 每天限制次数
	 */
//	private int limit;
	/**
	 * 描述
	 */
	private String remark;
	/**
	 * 上限描述
	 */
	private String dec;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getLindou() {
		return lindou;
	}
	public void setLindou(int lindou) {
		this.lindou = lindou;
	}
	//	public int getLimit() {
//		return limit;
//	}
//	public void setLimit(int limit) {
//		this.limit = limit;
//	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDec() {
		return dec;
	}
	public void setDec(String dec) {
		this.dec = dec;
	}
	
	
	
}
