package pl.wiktorjasica.pwwa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	/**
	 * This method displays login form
	 * 
	 * @return	Name of user login page
	 */
	@RequestMapping("/login")
	public String login() {
		return"login";
	}

	/**
	 * This method logg-in userRedirected url address
	 * 
	 * @param redirectAttribute
	 * 			Object keeps redirect url address attribute 
	 * @return 
	 */
	@RequestMapping(value="/j_spring_security_check",method=RequestMethod.POST)
	public String doLogin(RedirectAttributes redirectAttribute) {
		System.out.println("logged_in");
		redirectAttribute.addFlashAttribute("success", true);
		return "redirect:/";
	}
}
