package cn.dayuanzi.config;

/**
 * 
 * @author : leihc
 * @date : 2015年5月7日
 * @version : 1.0
 */
public class Interest {

	/**
	 * 兴趣代号
	 */
	private int id;
	/**
	 * 兴趣
	 */
	private String interest;
	
	public Interest(int id, String interest){
		this.id = id;
		this.interest = interest;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	
	
}
