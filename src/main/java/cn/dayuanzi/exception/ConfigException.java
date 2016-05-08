package cn.dayuanzi.exception;

/**
 * 
 * @author : leihc
 * @date : 2015年4月15日 下午7:47:59
 * @version : 1.0
 */
public class ConfigException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7913298412643366406L;

	public ConfigException() {
        super();
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(Throwable cause) {
        super(cause);
    }
	
}
