package pl.wiktorjasica.pwwa.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.wiktorjasica.pwwa.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * This method displays all users that are registered.
	 * 
	 * @param model
	 *            Model object that is sent to the "users" jsp view
	 * @return Name of jsp view
	 */
	@RequestMapping("/users")
	public String showUsers(Model model) {
		model.addAttribute("users", userService.findUsersWithWarehouses()); 
		return "users";
	}

	/**
	 * This method delete specific user
	 * 
	 * @param id
	 *         user's id taken from url address
	 * @return Redirected url address
	 */
	@RequestMapping("/user/delete/{id}")
	public String doDeleteUser(@PathVariable Long id) {
		userService.delete(id);
		return "redirect:/users.html";
	}

}
