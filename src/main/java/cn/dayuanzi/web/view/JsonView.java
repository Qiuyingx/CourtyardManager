package cn.dayuanzi.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import cn.dayuanzi.resp.Resp;
import cn.dayuanzi.util.JsonUtils;

public class JsonView extends AbstractView {

	private final Logger logger = LoggerFactory.getLogger(JsonView.class);

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		Resp resp = (Resp) model.get("model");
		String json = JsonUtils.toJson(resp);
		this.logger.info(request.getRequestURL().toString()+(request.getQueryString()==null?"":"?"+request.getQueryString()));
		this.logger.info(json);
		response.getWriter().println(json);
		response.flushBuffer();
	}

}
