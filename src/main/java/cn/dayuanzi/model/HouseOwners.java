package cn.dayuanzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.framework.persist.db.VersionPersistentSupport;

/**
 * 业主表，保存各个院子的业主信息
 * 
 * @author : leihc
 * @date : 2015年4月15日 上午11:10:35
 * @version : 1.0
 */
@Entity
@Table(name = "t_houseowners")
public class HouseOwners extends VersionPersistentSupport {

	/**
	 * 院子ID
	 */
	private long courtyard_id;
	/**
	 * 期数
	 */
	private int term_id;
	/**
	 * 楼栋号
	 */
	private int building_id;
	/**
	 * 单元
	 */
	private int unit_id;
	/**
	 * 房号
	 */
	private int house_id;
	/**
	 * 期数描述
	 */
	private String term;
	/**
	 * 楼栋名称（显示）
	 */
	private String building;
	/**
	 * 单元
	 */
	private String unit;
	/**
	 * 房名称（显示）
	 */
	private String house;
	
	/**
	 * 业主名字
	 */
	private String owner_name;
	
	/**
	 * 证件号码
	 */
	private String card_id;
	
	/**
	 * 联系电话
	 */
	private String tel;
	
	public HouseOwners(){
		
	}
	
	public HouseOwners(long courtyard_id, int term_id, int building_id, int unit_id, int house_id){
		this.courtyard_id = courtyard_id;
		this.term_id = term_id;
		this.building_id = building_id;
		this.unit_id = unit_id;
		this.house_id = house_id;
	}
	

	public long getCourtyard_id() {
		return courtyard_id;
	}

	public void setCourtyard_id(long courtyard_id) {
		this.courtyard_id = courtyard_id;
	}

	/**
	 * @return the owner_name
	 */
	public String getOwner_name() {
		return owner_name;
	}

	/**
	 * @param owner_name the owner_name to set
	 */
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	/**
	 * @return the card_id
	 */
	public String getCard_id() {
		return card_id;
	}

	/**
	 * @param card_id the card_id to set
	 */
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the building
	 */
	public String getBuilding() {
		return building;
	}

	/**
	 * @param building the building to set
	 */
	public void setBuilding(String building) {
		this.building = building;
	}


	public int getBuilding_id() {
		return building_id;
	}

	public void setBuilding_id(int building_id) {
		this.building_id = building_id;
	}

	public int getHouse_id() {
		return house_id;
	}

	public void setHouse_id(int house_id) {
		this.house_id = house_id;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public int getTerm_id() {
		return term_id;
	}

	public void setTerm_id(int term_id) {
		this.term_id = term_id;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public int getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
