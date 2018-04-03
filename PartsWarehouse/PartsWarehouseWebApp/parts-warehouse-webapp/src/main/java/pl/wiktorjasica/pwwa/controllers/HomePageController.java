package pl.wiktorjasica.pwwa.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {

	/**
	 *This method displays HomePage 
	 *
	 */
	@RequestMapping("/")
	public String showHomePage(Model model, Principal principal) {
		if(principal==null) {
			return "home-page";
		}
		String name = principal.getName();
		model.addAttribute("userName", name);		
		return "home-page";
	}
	
}