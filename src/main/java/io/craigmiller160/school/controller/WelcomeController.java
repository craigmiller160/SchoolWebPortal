package io.craigmiller160.school.controller;

import org.springframework.stereotype.Controller;
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
	 * parameter is not null, then it returns the
	 * login screen. 
	 * 
	 * @param loginButton a parameter that is not null only when
	 * the user has selected the login button on the screen.
	 * @return either the welcome page or the login page, depending
	 * on the request parameter.
	 */
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String welcome(@RequestParam (required=false) String loginButton){
		if(loginButton != null){
			return "login";
		}
		return "welcome";
	}
	
}
