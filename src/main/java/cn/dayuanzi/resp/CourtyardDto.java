package cn.dayuanzi.resp;

import cn.dayuanzi.model.CourtYard;

/**
 * 
 * @author : leihc
 * @date : 2015年5月19日
 * @version : 1.0
 */
public class CourtyardDto {

	/**
	 * 社区ID
	 */
	private long id;
	/**
	 * 社区名称
	 */
	private String name;
	
	public CourtyardDto(CourtYard courtyard){
		this.id = courtyard.getId();
		this.name = courtyard.getCourtyard_name();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
