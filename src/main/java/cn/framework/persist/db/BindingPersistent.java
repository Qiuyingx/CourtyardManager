package cn.framework.persist.db;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * 主键ID不用自动生成，带乐观锁控制
 * 
 * @author : lhc
 * @date : 2015年4月10日 下午5:10:25
 * @version : 1.0
 */
@MappedSuperclass
public class BindingPersistent implements Persistent{
	/**
	 * 
	 */
	protected Long id;
	/**
	 * 
	 */
	private int version;

	@Id
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Version
	@Column(columnDefinition = "int default 0")
	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
