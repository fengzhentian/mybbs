package com.myapp.common.abandon;

public enum MenuObjType
{
	LINKBUTTON("linkbutton"), MENUBUTTON("menubutton"), SUBBUTTON("subbutton"), MENUSEP(
			"menusep");

	private String type;

	private MenuObjType(String type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return type;
	}
}