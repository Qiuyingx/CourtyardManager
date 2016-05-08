package cn.framework.persist.db.type;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

/**
 * 自定义hibernate数据对象List
 * 
 * @author : lhc
 * @date : 2015年4月10日 下午5:28:49
 * @version : 1.0
 */
public class LongList implements org.hibernate.usertype.UserType, Serializable {

	private static final long serialVersionUID = -141671036812517797L;

	private static final int[] TYPES = new int[] { Types.VARCHAR };

	public int[] sqlTypes() {
		return TYPES;
	}

	@SuppressWarnings("rawtypes")
	public Class<List> returnedClass() {
		return List.class;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		return (x == y) || (x != null && x.equals(y));
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
		String value = rs.getString(names[0]);
		return TypeUtils.stringToList(Long.class, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		if (value != null) {
			List<Long> list = (List<Long>) value;
			if (list.isEmpty())
				st.setNull(index, sqlTypes()[0]);
			else
				st.setString(index, TypeUtils.listToString(list));
		} else {
			st.setNull(index, sqlTypes()[0]);
		}
	}

	@SuppressWarnings("unchecked")
	public Object deepCopy(Object value) throws HibernateException {
		return new ArrayList<Long>((List<Long>) value);
	}

	public boolean isMutable() {
		return false;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

}
