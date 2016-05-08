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


public class StringList implements org.hibernate.usertype.UserType{
	
	public int[] sqlTypes() {
		return new int[]{Types.CHAR};
	}

	@SuppressWarnings("rawtypes")
	public Class returnedClass() {
		return List.class;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		return ( x == y ) || ( x != null && x.equals( y ) );
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@SuppressWarnings("unchecked")
	public Object deepCopy(Object value) throws HibernateException {
		if(value==null){
			return new ArrayList<String>();
		}
		return new ArrayList<String>((List<String>)value);
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

	/**
	 * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[], org.hibernate.engine.spi.SessionImplementor, java.lang.Object)
	 */
	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,SessionImplementor session, Object owner)throws HibernateException, SQLException {
		String value = rs.getString(names[0]);
		return TypeUtils.stringToList(String.class, value);
	}

	/**
	 * @see org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int, org.hibernate.engine.spi.SessionImplementor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		if ( value != null ) {
			List<String> list = (List<String>) value;
			st.setString(index,TypeUtils.listToString(list));
		}
		else {
			st.setNull( index, sqlTypes()[0] );
		}		
	}
}
