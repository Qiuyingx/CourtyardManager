package cn.dayuanzi.config;

/**
 * 
 * @author : leihc
 * @date : 2015年6月5日
 * @version : 1.0
 */
public class LevelUpInfo {

	/**
	 * 等级
	 */
	private int level;
	/**
	 * 该等级需要的经验
	 */
	private int exp;
	/**
	 * 到该等级可以得到的邻豆
	 */
	private int lindou;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getLindou() {
		return lindou;
	}
	public void setLindou(int lindou) {
		this.lindou = lindou;
	}
	
	
}
