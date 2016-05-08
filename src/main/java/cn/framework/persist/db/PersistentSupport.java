package cn.framework.persist.db;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 主键自动生成，不带乐观锁
 * 
 * @author : lhc
 * @date : 2015年4月10日 下午5:11:37
 * @version : 1.0
 */
@MappedSuperclass
public abstract class PersistentSupport implements Persistent{
	/**
	 * 
	 */
	protected Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PersistentSupport other = (PersistentSupport) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}