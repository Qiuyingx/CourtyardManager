package cn.dayuanzi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

import cn.dayuanzi.exception.GeneralLogicException;
import cn.dayuanzi.exception.LoginExpiredException;
import cn.dayuanzi.resp.Resp;
import cn.dayuanzi.util.LogUtil;


/**
 * 统一的异常处理器
 * 
 * @author : lhc
 * @date : 2015年4月9日 上午11:42:31
 * @version : 1.0
 */
@Component("handlerExceptionResolver")
public class LogicExceptionResolver extends HandlerExceptionResolverComposite {

	/**
	 * @see org.springframework.web.servlet.handler.HandlerExceptionResolverComposite#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) {
		if (ex instanceof GeneralLogicException) {
			ModelAndView mav = new ModelAndView("jsonView");
			mav.addObject("model", new Resp(((GeneralLogicException)ex).getErrorCode(), ex.getMessage()));
			return mav;
		}
		else if (ex instanceof LoginExpiredException) {
			ModelAndView mav = new ModelAndView("jsonView");
			mav.addObject("model", new Resp(Resp.CODE_ERR_LOGIN, "登录信息已过期，请重新登录"));
			return mav;
		}
		else {
			StringBuilder buf = new StringBuilder();
			buf.append(request.getRequestURI());
			if (request.getQueryString() != null)
				buf.append("?").append(request.getQueryString());
			LogUtil.logException(buf.toString(), ex);
			ex.printStackTrace();
			ModelAndView mav = new ModelAndView("jsonView");
			mav.addObject("model", new Resp(Resp.CODE_ERR_SYS, "读取数据失败，请稍后重试。"));
			return mav;
		}
	}
}
