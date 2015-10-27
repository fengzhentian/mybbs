package com.myapp.base;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController
{
	@Autowired
	private HttpServletRequest request;

	/** 
	 * 记录日志
	 */
	protected Logger log = Logger.getLogger(this.getClass());

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
	}

	// 获取访问ip地址
	public String getClientIp()
	{
		try
		{
			return InetAddress.getLocalHost().getHostAddress();
		}
		catch (Exception ex)
		{
			return "";
		}
	}

	// /**
	// * 获取当前登录用户信息
	// *
	// * @return
	// */
	// @ModelAttribute("contextUser")
	// public CurrentUser getContextUser()
	// {
	// AppContext appContext = AppContextHolder.getContext();
	// if (appContext != null)
	// {
	// return appContext.getUser();
	// }
	// return null;
	// }

	/**
	 * 用户所在的OU
	 * @param model
	 */
	@ModelAttribute("OU")
	public String getSystemOU()
	{
		return "";
		// return BaseUtils.getConfigValue("ouname");
	}
}