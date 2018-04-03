package pl.wiktorjasica.pwwa.controllers;


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
import pl.wiktorjasica.pwwa.services.PartService;
import pl.wiktorjasica.pwwa.services.WarehouseService;

@Controller
public class PartController {
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private PartService partService;
	
	
	
	/**
	 * This method creates Part object with parameters from form in jsp view
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
	 * This method displays specific warehouse 
	 * 
	 * @param model
	 * 			Model object that is sent to the "user-parts-warehouse" jsp view
	 * @param id
	 * 			"id" parameter of a specific warehouse taken from url address 
	 * @return
	 * 			name od jsp view
	 */
	@RequestMapping("/user-parts-warehouse/{id}")
	public String findWarehouseWithParts(Model model, @PathVariable Long id) {
		model.addAttribute("warehouse", warehouseService.findWarehouseWithParts(id));				
		return "user-parts-warehouse";
	}
	
	/**
	 * This method adds part to specific warehouse
	 * 
	 * @param part
	 * 			Part object bind with "Add Part" form
	 * @param id
	 * 			"id" parameter of a specific warehouse taken from url address
	 * @return
	 * 		Redirected url address
	 */
	@RequestMapping(value="/user-parts-warehouse/add/{id}", method=RequestMethod.POST)
	public String doAddPart(Model model, @Valid @ModelAttribute("part") Part part, BindingResult resoult, @PathVariable Long id) {
		if(resoult.hasErrors()) {
			return findWarehouseWithParts(model, id);
		}
		boolean score = partService.compareParts(part, id);
		if(score==false) {
			partService.savePart(part, id);
		}
		return"redirect:/user-parts-warehouse/{id}";
	}
	
	/**
	 * This method removes specific part from specific warehouse
	 * 
	 * @param id1
	 * 			"id1" parameter of a specific warehouse taken from url address
	 * @param id2
	 * 			"id2" parameter of a specific part to remove taken from url address
	 * @return
	 * 		Redirected url address
	 */
	@RequestMapping(value="/user-parts-warehouse/edit-delete/{id1}/part/{id2}/delete")
	public String doDeletePart(@PathVariable Long id1, @PathVariable Long id2) {
		Part part = partService.getById(id2);
		partService.delete(part);
		return"redirect:/user-parts-warehouse/{id1}";
	}
	
	/**
	 * This method displays form to edit part in warehouse
	 * 
	 *  			
	 * @param editedPart
	 * 			Part object bind with "editedPart" form in jsp view
	 * @param id1
	 * 			"id1" parameter of a specific warehouse taken from url address
	 * @param id2
	 * 			"id2" parameter of a specific part to edit taken from url address
	 * 			
	 * @return Redirected url address
	 */
	@RequestMapping(value="/user-parts-warehouse/edit-delete/{id1}/part/{id2}/edit", method=RequestMethod.POST)
	public String doEditPart(Model model,@Valid @ModelAttribute ("editedPart") Part editedPart, BindingResult resoult, @PathVariable Long id1, @PathVariable Long id2) {
		
		Part part = partService.editPartInWarehouse(editedPart, id2);
		partService.savePart(part, id1);
		partService.editCompareParts(id1);
		return"redirect:/user-parts-warehouse/{id1}";
	}
		
}
