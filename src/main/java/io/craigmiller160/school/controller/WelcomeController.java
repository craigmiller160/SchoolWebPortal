package io.craigmiller160.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class for the welcome page of this application.
 * This page simply serves to display a welcome screen with
 * the option of logging into the Web Portal, and this controller
 * returns either that welcome page or the login page depending
 * on user input.
 * <p>
 * <b>THREAD SAFETY:</b> Because this controller will be used
 * in a servlet environment, it is designed to be completely 
 * thread safe.
 * 
 * @author craig
 * @version 1.0
 */
@Controller
public class WelcomeController {

	/**
	 * Access to the welcome page is controlled via
	 * this method. By default it simply returns the
	 * welcome screen, but if the <tt>loginButton</tt>
	 * parameter is not null, then it redirects to the
	 * login screen. 
	 * 
	 * @param loginButton a parameter that is not null only when
	 * the user has selected the login button on the screen.
	 * @return either the welcome page or the login page, depending
	 * on the request parameter.
	 */
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String welcome(Model model,
			@RequestParam (required=false) String loginButton){
		model.addAttribute("headerTitle", "welcome.header.title");
		model.addAttribute("pageName", "welcome");
		
		//TODO currently, loginButton is not being used at all
		//in this page. It may be restored when doing the login
		//operation though.
		if(loginButton != null){
			return "redirect:login.html";
		}
		return "welcome";
	}
	
}
