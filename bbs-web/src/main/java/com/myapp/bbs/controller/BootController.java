package com.myapp.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("boot")
public class BootController
{
	private static final String JSP_PATH = "boot";

	@RequestMapping(value = "demo", method = RequestMethod.GET)
	public String loadIndex(Model model)
	{
		return JSP_PATH + "/demo";
	}

}
