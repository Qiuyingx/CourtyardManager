package cn.dayuanzi.resp;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author : lhc
 * @date : 2015年4月8日 上午10:37:03
 * @Description :
 *
 */
public class Resp {

	protected final transient Logger logger = LoggerFactory.getLogger(Resp.class);
	
	public static final int CODE_SUCCESS = 0;
	public static final int CODE_ERR_LOGIN = 10;
	public static final int CODE_ERR_SERVICE = 20;
	public static final int CODE_ERR_SYS = 99;
	
	/**
	 * 状态码
	 */
	private int errorCode = 0;
	
	/**
	 * 返回数据
	 */
	private String msg = "";
	
	protected List<Object> rows = new ArrayList<Object>();

	protected int total;
	
	public Resp(){
		
	}
	
	public Resp(int errorCode, String msg){
		this.errorCode = errorCode;
		this.msg = msg;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
