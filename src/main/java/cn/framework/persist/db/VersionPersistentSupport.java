package cn.framework.persist.db;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * 主键自动生成，带乐观锁
 * 
 * @author : lhc
 * @date : 2015年4月10日 下午5:12:19
 * @version : 1.0
 */
@MappedSuperclass
public class VersionPersistentSupport extends PersistentSupport{
	/**
	 * 版本
	 */
	private int version;
	
	@Version
	@Column(columnDefinition = "int default 0")
	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
