package io.craigmiller160.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping (value="/admin")
public class AdminMainController {

	@RequestMapping (method=RequestMethod.GET)
	public String showMainPage(){
		return "admin-main";
	}
	
	@RequestMapping (value="/logout", method=RequestMethod.GET)
	public String logout(){
		return "redirect:/welcome.html";
	}
	
}
