package pl.wiktorjasica.pwwa.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.wiktorjasica.pwwa.model.User;
import pl.wiktorjasica.pwwa.services.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * This method creates User object with parameters from form in jsp view
	 * 
	 * @return
	 * 			User object bind with "Registration" form in jsp view
	 */
	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}
	
	/**
	 * This method displays registration form
	 * 
	 * @return Name of jsp view
	 * 			
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String registerUser() {
		return "user-register";
	}
	
	/**
	 * This method creates new user with parameters from registration form
	 * 
	 * @param user
	 * 			User object bind with Registration form 
	 * @param resoult
	 * 			object informing if data in form is valid
	 * @param redirectAttribute
	 * 			Object keeps redirect url address attribute
	 * @return Redirected url address
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult resoult, RedirectAttributes redirectAttribute) {
		if(resoult.hasErrors()) {
			return"user-register";
		}
		userService.create(user);
		redirectAttribute.addFlashAttribute("success", true);
		return "redirect:/register.html"; 
	}

}
