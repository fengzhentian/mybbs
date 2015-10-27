package com.myapp.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 自定义拦截器，拦截符合条件url的请求 身份验证拦截
 * (是否有登录用户信息)
 */
public class IdentityInterceptor extends HandlerInterceptorAdapter
{
	// 加载日志
	static Logger log = Logger.getLogger(IdentityInterceptor.class);
	// // 读取ssoToken参数
	// private static final String strToken =
	// BaseUtils.getConfigValue("ssoKey");

	public final static String SEESION_USER = "seesion_user";
	// 不拦截的url
	private List<String> uncheckUrls = null;

	public List<String> getUncheckUrls()
	{
		return uncheckUrls;
	}

	public void setUncheckUrls(List<String> uncheckUrls)
	{
		this.uncheckUrls = uncheckUrls;
	}

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception
	{

		log.debug("==============执行顺序: 1、preHandle================");
		// 获取当前请求的url
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String queryString = request.getQueryString();

		/***** 测试用*******/
		String url = requestUri.substring(contextPath.length());
		log.debug("requestUri:" + requestUri);
		log.debug("contextPath:" + contextPath);
		log.debug("url:" + url);

		Validator validator = Validator.getInstance();
		// 注入当前session
		// validator.init(request);
		// 默认check
		boolean check = true;
		// 遍历放行的url
		if (this.uncheckUrls != null)
		{
			for (String uncheckUri : this.uncheckUrls)
			{
				String uncheckQuery = null;
				String uri = uncheckUri;
				// 拦截可细致到参数
				if (uncheckUri.indexOf("?") >= 0)
				{
					uri = uncheckUri.substring(0, uncheckUri.indexOf("?"));
					uncheckQuery = uncheckUri
							.substring(uncheckUri.indexOf("?") + 1);
					check = !(requestUri.startsWith(uri) && queryString
							.indexOf(uncheckQuery) >= 0);
				}
				else
				{
					check = !(requestUri.startsWith(uri));
				}
			}
		}
		if (check)
		{
			// String strResponse = request.getParameter(strToken);

			// if (strResponse != null)
			// {
			// // 如果服务器端通过认证后，会返回后执行改操作，然后写入cookie
			// SSOResponse ssoResp = new SSOResponse(strResponse);
			// TicketManager tm = ssoResp.CreatePSOTicket();
			// if (tm == null)
			// {
			// PSORequest psoRequest = new PSORequest(request);
			// String requeststr = psoRequest.CreateHash();
			//
			// String keeperUrl = BaseUtils.getConfigValue("keeperUrl");
			// keeperUrl = keeperUrl + "?" + strToken + "="
			// + URLEncoder.encode(requeststr, "UTF-8");
			// response.sendRedirect(keeperUrl);
			// }
			// else
			// {
			// String domainName = BaseUtils.getConfigValue("domain");
			// tm.SaveTicket(response, domainName);
			//
			// Iterator iterator = request.getParameterMap().entrySet()
			// .iterator();
			// StringBuffer param = new StringBuffer();
			// int i = 0;
			// while (iterator.hasNext())
			// {
			// Entry entry = (Entry) iterator.next();
			// if (entry.getKey().equals(strToken))
			// continue;
			// else
			// {
			// i++;
			// if (i == 1)
			// param.append("?").append(entry.getKey())
			// .append("=");
			// else
			// param.append("&").append(entry.getKey())
			// .append("=");
			//
			// if (entry.getValue() instanceof String[])
			// {
			// param.append(((String[]) entry.getValue())[0]);
			// }
			// else
			// {
			// param.append(entry.getValue());
			// }
			// }
			// }
			// response.sendRedirect(requestUri + param.toString());
			// return false;
			// }
			// }
			// else
			// {
			// TicketManager tm = new TicketManager();
			// if (!tm.LoadTicket(request))
			// {
			// PSORequest psoRequest = new PSORequest(request);
			// String requeststr = psoRequest.CreateHash();
			//
			// String keeperUrl = BaseUtils.getConfigValue("keeperUrl");
			// keeperUrl = keeperUrl + "?" + strToken + "="
			// + URLEncoder.encode(requeststr, "UTF-8");
			// response.sendRedirect(keeperUrl);
			// return false;
			// }
			// }

			// 需要用户身份验证
			/**boolean isValidate = validator.validate();
			if (isValidate)
			{
				// 验证通过
				try
				{
					validator.confirm();
					return true;
				}
				catch (Exception ex)
				{
					request.getRequestDispatcher("/WEB-INF/view/login.jsp")
							.forward(request, response);
					return false;
				}
			}
			else
			{
				// 身份验证失败
				validator.cancel();
				String ssoUrl= "http://192.168.0.29:8088/Keeper.aspx";
				
				request.getRequestDispatcher("/WEB-INF/view/login.jsp")
						.forward(request, response);
			}**/
			validator.SetUserTicket(request);
			return true;
		}
		else
		{
			return true;
		}
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
		log.debug("==============执行顺序: 2、postHandle================");
		if (modelAndView != null)
		{ // 加入当前时间
			// modelAndView.addObject("var", "测试postHandle");
		}
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception
	{
		log.debug("==============执行顺序: 3、afterCompletion================");
	}
}
