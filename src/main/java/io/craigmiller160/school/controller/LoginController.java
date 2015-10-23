package io.craigmiller160.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showPage(){
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam (required=false) String admin,
			@RequestParam (required=false) String student){
		if(admin != null && student == null){
			return "redirect:admin.html";
		}
		else if(student != null && admin == null){
			System.out.println("Student");
		}
		
		return null;
	}
	
}
