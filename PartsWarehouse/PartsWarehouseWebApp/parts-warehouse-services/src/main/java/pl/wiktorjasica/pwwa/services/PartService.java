package pl.wiktorjasica.pwwa.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import pl.wiktorjasica.pwwa.dao.PartDao;
import pl.wiktorjasica.pwwa.dao.WarehouseDao;
import pl.wiktorjasica.pwwa.model.Part;
import pl.wiktorjasica.pwwa.model.Warehouse;

/**
 * This service implements functions connected with Part.
 *
 */
@Service
@Transactional
public class PartService extends AbstractService<Long, Part>{

	@Autowired
	private PartDao partDao;
	
	@Autowired
	private WarehouseDao warehouseDao;
	
		
	/**
	 *  This method finds parts for given warehouse.
	 *  
	 * @param warehouse
	 * 				warehouse which parts we want to get
	 * 
	 * @return List of parts for given warehouse 
	 */
	public List<Part> findPartsForWarehouse(Warehouse warehouse){
		return partDao.findByWarehouse(warehouse);
	}
	
	
	/**
	 * This method sets part with parameters written in form to specific warehouse and saves it to part repository
	 * 
	 * @param part
	 * 		Part object bind with "Add Part" form
	 * @param id
	 * 		id of a specific warehouse
	 * @return
	 * 		Part object added to repository
	 */
	public Part savePart(Part part, Long id) {
		Warehouse warehouse = warehouseDao.findOne(id);
		part.setWarehouse(warehouse);	
		return super.create(part);
	}
	
	/**
	 *  This method compares part from completed "Add Form" with others in specific warehouse.
	 *  If one of them has 3 parameters (name, manufacturer, price) equals to the sent one, 
	 *  adds only quantity to not duplicate parts in the warehouse. 
	 * 
	 * @param part
	 * 			part object bind to "Add Form"
	 * @param id
	 * 			id of a warehouse in which we want to compare parts 
	 * @return indicator of comparison
	 */
	public boolean compareParts(Part part, Long id) {
		Warehouse warehouse = warehouseDao.findOne(id);
		List<Part> parts = findPartsForWarehouse(warehouse);
		for(Part warehousePart :parts) {
			if(warehousePart.getName().equals(part.getName()) && warehousePart.getManufacturer().equals(part.getManufacturer()) 
					&& Double.valueOf(warehousePart.getPrice()).equals(part.getPrice())){
				
				int quantity1 = warehousePart.getQuantity();
				int quantity2 = part.getQuantity();
				Part daoPart = getById(warehousePart.getId());
				daoPart.setQuantity(quantity1+quantity2);
				savePart(daoPart, id);
				return true;
			}
		}
		return false;
	}	
	
	/**
	 * 	This method compares part from completed "Edit Form" with others in specific warehouse.
	 *  If one of them has 3 parameters (name, manufacturer, price) equals to the sent one, 
	 *  adds only quantity to not duplicate parts in the warehouse.
	 * 
	 * @param id
	 * 			id of a warehouse in which we want to compare parts 
	 * @return	indicator of comparison
	 */
	public boolean editCompareParts(Long id) {
		Warehouse warehouse = warehouseDao.findOne(id);
		List<Part> parts = findPartsForWarehouse(warehouse);
		for(int i=0;i<parts.size();i++) {
			for(int j=0;j<parts.size();j++) {
			if(parts.get(i).getName().equals(parts.get(j).getName()) && parts.get(i).getManufacturer().equals(parts.get(j).getManufacturer()) 
					&& Double.valueOf(parts.get(i).getPrice()).equals(parts.get(j).getPrice()) && (!parts.get(i).equals(parts.get(j)))) {
				int quantity1 = parts.get(i).getQuantity();
				int quantity2 = parts.get(j).getQuantity();
				Part daoPart = getById(parts.get(i).getId());
				daoPart.setQuantity(quantity1+quantity2);
				delete(parts.get(j));
				savePart(daoPart, id);
				return true;
				}
			}
		}
		return false;
}
	
	/**
	 * This method edits part in specific warehouse.
	 * 
	 * @param editedPart
	 * 				Part object bind to "Edit Form" with changed parameters
	 * @param id2
	 * 			id of part to edit
	 * @return Edited Part object
	 */
	public Part editPartInWarehouse(Part editedPart, Long id2){
		Part part = getById(id2);
		part.setName(editedPart.getName());
		part.setManufacturer(editedPart.getManufacturer());
		part.setPrice(editedPart.getPrice());
		part.setQuantity(editedPart.getQuantity());
		return part;
	}

	/**
	 * This method deletes specific part.
	 * You can delete part only if you are loged-in.
	 * 
	 * @param part 
	 * 			Part object to delete 
	 */
	@PreAuthorize("#part.warehouse.user.name == authentication.name")	
	public void delete(@P("part")Part part) {
		partDao.delete(part);
	}
	
	
	/**
	 * This method sorts list of parts by their name in ascending order
	 * 
	 * @param partsList
	 * 			List of parts to sort
	 * @return Sorted list of parts
	 */
	public List<Part> sortPartsByName(List<Part> partsList ){
		partsList.sort(new Comparator<Part>()  {

			@Override
			public int compare(Part o1, Part o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return partsList;
	}
	
	/**
	 * This method sorts list of parts by their manufacturer in ascending order
	 * 
	 * @param partsList
	 * 			List of parts to sort
	 * @return Sorted list of parts
	 */
	public List<Part> sortPartsByManufacturer(List<Part> partsList ){
		
		partsList.sort(new Comparator<Part>()  {

			@Override
			public int compare(Part o1, Part o2) {
				return o1.getManufacturer().compareTo(o2.getManufacturer());
			}
		});
		return partsList;
	}
	
	/**
	 * This method sorts list of parts by their price in ascending order
	 * 
	 * @param partsList
	 * 			List of parts to sort
	 * @return Sorted list of parts
	 */
	public List<Part> sortPartsByPrice(List<Part> partsList ){
		
		partsList.sort(new Comparator<Part>()  {

			@Override
			public int compare(Part o1, Part o2) {
				
				return Double.valueOf(o1.getPrice()).compareTo(o2.getPrice());
			}
		});
		return partsList;
	}
	
	
	/**
	 * This method sorts list of parts by their quantity in ascending order
	 * 
	 * @param partsList
	 * 			List of parts to sort
	 * @return Sorted list of parts
	 */
	public List<Part> sortPartsByQuantity(List<Part> partsList ){
		partsList.sort(new Comparator<Part>()  {

			@Override
			public int compare(Part o1, Part o2) {
				return Integer.valueOf(o1.getQuantity()).compareTo(o2.getQuantity());
			}
		});
		return partsList;
	}
	
	/**
	 * This method finds parts in warehouse by given keyword.
	 * 
	 * @param warehouse
	 * 			warehouse in which we search parts
	 * @param key
	 * 			keyword for searching  
	 * 
	 * @return List of parts fulfilling search condition
	 */
	public List<Part> findPartsByKey (Warehouse warehouse, String key){
		List<Part> partsList = partDao.findByWarehouse(warehouse);
		List<Part> tempList = new ArrayList<Part>();
		for (Part part : partsList) {
			if(part.getName().toLowerCase().contains(key) || part.getManufacturer().toLowerCase().contains(key)) {
				tempList.add(part);
			}
		}
		return tempList;
	}
	
	/**
	 * This method returns repository for the Part type of object (it's required for  abstract class).
	 * 
	 */
	@Override
	protected CrudRepository<Part, Long> getRepository() {
		return partDao;
	}
}
