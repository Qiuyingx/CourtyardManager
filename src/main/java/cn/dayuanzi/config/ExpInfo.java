package cn.dayuanzi.config;

/**
 * 
 * @author : leihc
 * @date : 2015年6月8日
 * @version : 1.0
 */
public class ExpInfo {
	
	/**
	 * id
	 */
	private int id;
	/**
	 * 图标图片
	 */
	private String  image ;
	/**
	 * 每次可以获得经验
	 */
	private int exp;
	
	/**
	 * 每天限制次数
	 */
	private int limitDaily;
	/**
	 * 描述
	 */
	private String remark;
	/**
	 * 
	 * 上限描述
	 */
	private String dec ;
	
	
	public String getDec() {
		return dec;
	}
	public void setDec(String dec) {
		this.dec = dec;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getLimitDaily() {
		return limitDaily;
	}
	public void setLimitDaily(int limitDaily) {
		this.limitDaily = limitDaily;
	}
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
	
	
}
