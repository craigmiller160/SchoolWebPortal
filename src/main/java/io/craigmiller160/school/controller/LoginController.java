package io.craigmiller160.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.craigmiller160.school.domain.PasswordReset;

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

	@RequestMapping (value="/login", method=RequestMethod.GET)
	public String login(Model model,
			@RequestParam (value="error", required=false) String error){
		model.addAttribute("pageName", "login");
		if(error != null){
			model.addAttribute("error", new Object());
		}
		
		return "login";
	}
	
	//TODO this will need to be redone as part of a larger logout revamp
	@RequestMapping (value="/logout-placeholder")
	public String logout(){
		return "logout-placeholder";
	}
	
	@RequestMapping (value="login/forgot", method=RequestMethod.GET)
	public String forgotPassword(Model model){
		model.addAttribute("pageName", "login");
		
		return "forgot-password";
	}
	
	@RequestMapping (value="login/forgot", method=RequestMethod.POST)
	public String resetPassword(Model model, 
			@RequestParam (value="username", required=false) String username){
		if(username != null){
			//TODO get user's email, send email to account with reset code
			model.addAttribute("codeSent", new Object());
			//TODO need a different value sent to the view if the username is invalid
		}
		
		model.addAttribute("pageName", "login");
		
		return "forgot-password";
	}
	
	@RequestMapping (value="login/forgot", method=RequestMethod.PUT)
	public String resetPassword(Model model, PasswordReset passReset){
		//TODO use passReset values to perform the reset.
		//Return a success/fail model value for displaying to the user
		
		model.addAttribute("pageName", "login");
		
		return "forgot-password";
	}
	
}
