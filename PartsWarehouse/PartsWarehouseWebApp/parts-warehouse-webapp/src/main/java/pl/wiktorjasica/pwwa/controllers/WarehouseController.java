package pl.wiktorjasica.pwwa.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.wiktorjasica.pwwa.model.Key;
import pl.wiktorjasica.pwwa.model.Part;
import pl.wiktorjasica.pwwa.model.Warehouse;
import pl.wiktorjasica.pwwa.services.PartService;
import pl.wiktorjasica.pwwa.services.UserService;
import pl.wiktorjasica.pwwa.services.WarehouseService;

@Controller
public class WarehouseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private PartService partService;
	
		
	/**
	 * This method creates Part object with parameters from form in jsp view.
	 * 
	 * @return
	 * 			Part object bind with specified form in jsp view
	 */
	@ModelAttribute("part")
	public Part constructPart() {
		return new Part();
	}
	
	/**
	 * This method creates Part object with parameters from "editedPart" form in jsp view.
	 * 
	 * @return
	 * 		Part object bind with "editedPart" form in jsp view
	 */
	@ModelAttribute("editedPart")
	public Part editPart() {
		return new Part();
	}
	
	/**
	 * This method creates Key object with parameter from "Search" form.
	 * 
	 * @return Key object bind with "Search" form in jsp view
	 */
	@ModelAttribute("key")
	public Key searchKey() {
		return new Key();
	}
	
	
	/**
	 * This method creates Warehouse object with parameters from "New Warehouse" form in jsp view
	 * 
	 * @return
	 * 			Warehouse object bind with "New Warehouse" form in jsp view
	 */
	@ModelAttribute("warehouse")
	public Warehouse constructWarehouse() {
		return new Warehouse();
	}
	
	
	
	/**
	 * This method displays warehouses for logged-in user
	 * 
	 * @param model
	 * 			Model object that is sent to "my-warehouse" jsp view
	 * @param principal
	 * 			Object with data about logged in user
	 * @return
	 * 		Name of jsp view
	 */
	@RequestMapping("/my-warehouses")
	public String userWarehouses(Model model, Principal principal) {
		String name = principal.getName();
		List<Warehouse> warehouses = userService.findUserWithWarehousesByName(name);
		model.addAttribute("userName", name);
		model.addAttribute("warehousesList", warehouses);
		return "my-warehouses";
	}
	
	/**
	 * This method adds warehouse for logged in user 
	 * 
	 * @param warehouse
	 * 				Warehouse object bind with "New Warehouse" form 
	 * @param principal
	 * 				Object with data about logged in user
	 * @return
	 * 		Redirected url address
	 */
	@RequestMapping(value="/my-warehouses", method=RequestMethod.POST)
	public String doAddWarehouse(Model model, @Valid @ModelAttribute("warehouse") Warehouse warehouse, BindingResult resoult, Principal principal){
		if(resoult.hasErrors()) {
			return userWarehouses(model, principal);
		}
		String name = principal.getName();
		warehouseService.createWarehouse(warehouse,name);
		return "redirect:/my-warehouses.html";
	}
	
	/**
	 * This method removes warehouse with specific id
	 * 
	 * @param id
	 * 		warehouse's id taken from url address
	 * @return
	 * 		Redirected url address
	 */
	@RequestMapping("/my-warehouses/delete/{id}")
	public String doDeleteWarehouse(@PathVariable Long id) {
		Warehouse warehouse = warehouseService.getById(id);
		warehouseService.delete(warehouse);
		return"redirect:/my-warehouses.html";
	}
	
	// Sort methods
	
	/**
	 * This method displays warehouse sorted by parts name.
	 * 
	 * @param model
	 * 			Model object that is sent to "user-parts-warehouse" jsp view
	 * @param id
	 * 		warehouse's id taken from url address
	 * @return Name of jsp view
	 */
	@RequestMapping("/user-parts-warehouse/sort-by-name/{id}")
	public String doSortWarehouseByPartName(Model model,@PathVariable Long id) {
		Warehouse warehouse = warehouseService.findWarehouseWithParts(id);
		warehouse.setParts(partService.sortPartsByName(partService.findPartsForWarehouse(warehouse)));
		model.addAttribute("warehouse", warehouse);
		return "user-parts-warehouse";
	}
	
	/**
	 * This method displays warehouse sorted by parts manufacturer.	
	 * 
	 * @param model
	 * 			Model object that is sent to "user-parts-warehouse" jsp view
	 * @param id
	 * 		warehouse's id taken from url address
	 * @return Name of jsp view
	 */
	@RequestMapping("/user-parts-warehouse/sort-by-manufacturer/{id}")
	public String doSortWarehouseByPartManufacturer(Model model, @PathVariable Long id) {
		Warehouse warehouse = warehouseService.findWarehouseWithParts(id);
		warehouse.setParts(partService.sortPartsByManufacturer(partService.findPartsForWarehouse(warehouse)));
		model.addAttribute("warehouse", warehouse);
		return "user-parts-warehouse";
	}
	
	/**
	 * This method displays warehouse sorted by parts price.
	 * 
	 * @param model
	 * 			Model object that is sent to "user-parts-warehouse" jsp view
	 * @param id
	 * 		warehouse's id taken from url address
	 * @return Name of jsp view
	 */
	@RequestMapping("/user-parts-warehouse/sort-by-price/{id}")
	public String doSortWarehouseByPartPrice(Model model, @PathVariable Long id) {
		Warehouse warehouse = warehouseService.findWarehouseWithParts(id);
		warehouse.setParts(partService.sortPartsByPrice(partService.findPartsForWarehouse(warehouse)));
		model.addAttribute("warehouse", warehouse);
		return "user-parts-warehouse";
	}
	
	/**
	 * This method displays warehouse sorted by parts quantity.
	 * 
	 * @param model
	 * 			Model object that is sent to "user-parts-warehouse" jsp view
	 * @param id
	 * 		warehouse's id taken from url address
	 * @return Name of jsp view
	 */
	@RequestMapping("/user-parts-warehouse/sort-by-quantity/{id}")
	public String doSortWarehouseByPartQuantity(Model model, @PathVariable Long id) {
		Warehouse warehouse = warehouseService.findWarehouseWithParts(id);
		warehouse.setParts(partService.sortPartsByQuantity(partService.findPartsForWarehouse(warehouse)));
		model.addAttribute("warehouse", warehouse);
		return "user-parts-warehouse";
	}
	
	/**
	 * This method displays warehouse with parts found by keyword.
	 * 
	 * @param model
	 * 			Model object that is sent to "user-parts-warehouse" jsp view
	 * 			
	 * @param key
	 * 			keyword for searching in warehouse
	 * @param resoult
	 * 			object informing if data in form is valid
	 * @param id
	 * 			warehouse's id taken from url address
	 * @return Name of jsp view
	 */
	@RequestMapping(value="/user-parts-warehouse/{id}/search", method=RequestMethod.POST)
	public String doSearchInWarehouse(Model model, @Valid @ModelAttribute("key") Key key, BindingResult resoult, @PathVariable Long id) {
		if(resoult.hasErrors()) {
			model.addAttribute("warehouse", warehouseService.findWarehouseWithParts(id));
			return "user-parts-warehouse";
		}
		String keyword = key.getKey().toLowerCase();
		Warehouse warehouse = warehouseService.findWarehouseWithParts(id);
		warehouse.setParts(partService.findPartsByKey(warehouse, keyword));
		model.addAttribute("warehouse", warehouse);
		return "user-parts-warehouse";
	}
	
}
