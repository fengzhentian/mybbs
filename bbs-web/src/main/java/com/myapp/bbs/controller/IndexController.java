package com.myapp.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class IndexController
{
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String loadIndex(Model model)
	{
		return "index";
	}
}
