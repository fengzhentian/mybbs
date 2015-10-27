package com.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.myapp.context.AppContext;
import com.myapp.context.AppContextHolder;
import com.myapp.context.DefaultAppContext;
import com.myapp.object.CurrentUser;

public class Validator
{
//	/**
//	 *  log4j日志
//	 */
//	private static final Logger log = Logger.getLogger(Validator.class);

	private static ThreadLocal<Validator> validatorHolder = new ThreadLocal<Validator>()
	{

		protected Validator initialValue()
		{
			return new Validator();
		}

	};
	// 当前请求的session
	private HttpSession session = null;

	// 当前的请求
	private HttpServletRequest request = null;

	// 当前登录系统的用户信息
	private CurrentUser user = null;

	private Validator()
	{
	}

	public static Validator getInstance()
	{
		return validatorHolder.get();
	}

	public void init(HttpServletRequest httpRequest)
	{
		this.request = httpRequest;
		this.session = request.getSession();
	}

	/*
	 * 将用户填充到Context
	 */
	public void confirm()
	{
		if (this.user == null)
		{
			throw new IllegalArgumentException("authentication is null.");
		}
		AppContext context = AppContextHolder.getContext();
		if (context == null)
		{
			context = new DefaultAppContext(user);
			AppContextHolder.setContext(context);
		}
		else
		{
			context.setUser(user);
		}
		// 将用户信息记录到log4j中MDC中
		MDC.put("uguid", user.getUserGuid());
		MDC.put("uname", user.getUserName());
		MDC.put("uip", user.getClientIp());
	}

	/**
	 * 将凭证身份加入到session
	 * @param httpRequest
	 */
	public void SetUserTicket(HttpServletRequest httpRequest)
	{
		try
		{
			if (httpRequest.getSession().getAttribute(
					IdentityInterceptor.SEESION_USER) == null)
			{
				// TicketManager ticket = new TicketManager();
				// if (ticket.LoadTicket(httpRequest))
				// {
				// log.debug(ticket.getADGUID());
				// // 记录登录用户的信息
				// user = new CurrentUser();
				// user.setUserName(ticket.getUserName());
				// user.setAccount(ticket.getUserID());
				// user.setUserGuid(ticket.getADGUID());
				// user.setUserPath(new ADUserHelper().getADUserByAccount(
				// ticket.getUserID()).getFullName());
				// String clientIP = InetAddress.getLocalHost()
				// .getHostAddress();
				// user.setClientIp(clientIP);
				// user.setOrigUserName(ticket.getOrigName());
				// user.setOrigUserGuid(ticket.getOrigGUID());
				// httpRequest.getSession().setAttribute(
				// IdentityInterceptor.SEESION_USER, user);
				// }
			}
			else
			{
				this.user = (CurrentUser) httpRequest.getSession()
						.getAttribute(IdentityInterceptor.SEESION_USER);
			}

			AppContext context = AppContextHolder.getContext();
			if (context == null)
			{
				context = new DefaultAppContext(user);
				AppContextHolder.setContext(context);
			}
			else
			{
				context.setUser(this.user);
			}
			// 将用户信息记录到log4j中MDC中
			MDC.put("uguid", user.getUserGuid());
			MDC.put("uname", user.getUserName());
			MDC.put("uip", user.getClientIp());
		}
		catch (Exception ex)
		{
			// LogProxy.WriteLogError(log, "验证用户身份", ex.toString(),
			// IdentityInterceptor.SEESION_USER);
		}
	}

	/**
	 * 清除session
	 */
	public void cancel()
	{
		this.session = null;
		this.user = null;
		AppContext context = AppContextHolder.getContext();
		if (context != null)
		{
			context.setUser(null);
		}
	}
}