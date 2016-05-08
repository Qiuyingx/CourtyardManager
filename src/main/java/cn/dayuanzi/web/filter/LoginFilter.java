package cn.dayuanzi.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import cn.dayuanzi.exception.GeneralLogicException;
import cn.dayuanzi.model.ManagerUser;
import cn.dayuanzi.pojo.UserSession;
import cn.dayuanzi.resp.LoginResp;
import cn.dayuanzi.resp.Resp;
import cn.dayuanzi.service.ServiceRegistry;
import cn.dayuanzi.util.JsonUtils;

/**
 * 
 * @author : lhc
 * @date : 2015年4月10日 下午3:04:56
 * @version : 1.0
 */
public class LoginFilter implements Filter{

	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		
	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		Resp respdata = null;
		try{
			UserSession userSession = (UserSession)session.getAttribute("USERSESSION");
//			if(userSession!=null){
//				return;
//			}
			if(StringUtils.isBlank(userName)){
				throw new GeneralLogicException("用户名不能为空");
			}
			if(StringUtils.isBlank(password)){
				throw new GeneralLogicException("密码不能为空");
			}
			ManagerUser managerUser = ServiceRegistry.getManagerUserService().findUserByName(userName);
			if(managerUser == null){
				throw new GeneralLogicException("用户不存在");
			}
			if(managerUser.isBanning()){
				throw new GeneralLogicException("您已经被禁止登录");
			}
			if(!managerUser.getPassword().equals(DigestUtils.md5Hex(password))){
				throw new GeneralLogicException("密码不正确");
			}
			userSession = new UserSession(managerUser);
			session.setAttribute("USERSESSION", userSession);
			respdata = new LoginResp(managerUser);
		}catch(Exception ex){
			respdata = new Resp(Resp.CODE_ERR_LOGIN, ex.getMessage());
			ex.printStackTrace();
		}
		String json = JsonUtils.toJson(respdata);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(json);
		response.flushBuffer();
	}

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}
