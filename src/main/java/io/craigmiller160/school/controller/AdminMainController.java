package io.craigmiller160.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminMainController {

	@RequestMapping (value="/admin")
	public String showPage(){
		return "admin-main";
	}
	
}
