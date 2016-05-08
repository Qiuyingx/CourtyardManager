package cn.dayuanzi.exception;

import cn.dayuanzi.resp.Resp;


/**
 * 
 * @author : lhc
 * @date : 2015年4月9日 上午11:40:23
 * @version : 1.0
 */
public class GeneralLogicException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1692619579197893878L;

	private int errorCode = Resp.CODE_ERR_SERVICE;

	public GeneralLogicException() {
		super();
	}

	public GeneralLogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public GeneralLogicException(String message) {
		super(message);
	}
	
	public GeneralLogicException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public GeneralLogicException(Throwable cause) {
		super(cause);
	}
	
	public int getErrorCode() {
		return this.errorCode;
	}
	
}
