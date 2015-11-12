package io.craigmiller160.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The controller for handling the login page of this
 * application. It displays the login page, and 
 * handles the process of authenticating the
 * login process and redirecting to the appropriate
 * portal based on access level.
 * <p>
 * <b>THREAD SAFETY:</b> Because this controller will be used
 * in a servlet environment, it is designed to be completely 
 * thread safe.
 * 
 * @author craig
 * @version 1.0
 */
@Controller
public class LoginController {

	//TODO login authentication is still needed, and will be integrated
	//before development completion.
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showPage(){
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam (required=false) String admin,
			@RequestParam (required=false) String student){
		if(admin != null && student == null){
			return "redirect:/admin.html";
		}
		else if(student != null && admin == null){
			System.out.println("Student");
		}
		
		//TODO probably should be a better option for this besides
		//return null
		return null;
	}
	
	//TODO will probably need to move this method to another controller,
	//as this one will likely end up deprecated
	
	@RequestMapping (value="/logout-placeholder")
	public String logout(){
		return "logout-placeholder";
	}
	
}
