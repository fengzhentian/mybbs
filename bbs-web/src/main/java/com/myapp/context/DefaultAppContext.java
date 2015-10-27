package com.myapp.context;

import com.myapp.object.CurrentUser;

public class DefaultAppContext implements AppContext
{
	/**
	 * 登录用户信息
	 */
	private CurrentUser user = null;

	public DefaultAppContext()
	{
	}

	public DefaultAppContext(CurrentUser user)
	{
		this.user = user;
	}

	public CurrentUser getUser()
	{
		return this.user;
	}

	public void setUser(CurrentUser user)
	{
		this.user = user;
	}
}