package cn.dayuanzi.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author : lhc
 * @date : 2015年4月10日 下午3:23:53
 * @version : 1.0
 */
public class BenchFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(BenchFilter.class);
	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
		long startTime = System.currentTimeMillis();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(false);
		String sessionId = null;
		if(session!=null){
			sessionId = session.getId();
		}
		chain.doFilter(req, resp);
		long elapse = System.currentTimeMillis() - startTime;
		StringBuffer buffer = new StringBuffer();
		buffer.append(elapse).append(" ").append(request.getRequestURI());
		if (request.getQueryString() != null) buffer.append("?").append(request.getQueryString());
		buffer.append(" ").append(sessionId);
		logger.debug(buffer.toString());
	}

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
