package io.craigmiller160.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String welcome(@RequestParam (required=false) String loginButton){
		if(loginButton != null){
			return "login";
		}
		return "welcome";
	}
	
}
