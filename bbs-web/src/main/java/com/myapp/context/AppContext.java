package com.myapp.context;

import com.myapp.object.CurrentUser;

public interface AppContext
{

	public CurrentUser getUser();

	public void setUser(CurrentUser user);
}