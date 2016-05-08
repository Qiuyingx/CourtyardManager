package cn.dayuanzi.exception;

/**
 * 
 * @author : lhc
 * @date : 2015年4月9日 上午11:34:26
 * @version : 1.0
 */
public class LoginExpiredException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4686550072602162777L;

	
	public LoginExpiredException() {
		super();
	}

	public LoginExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginExpiredException(String message) {
		super(message);
	}

	public LoginExpiredException(Throwable cause) {
		super(cause);
	}
}
