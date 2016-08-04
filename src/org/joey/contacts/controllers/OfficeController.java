package org.joey.contacts.controllers;



import org.joey.contacts.entities.Address;
import org.joey.contacts.entities.Company;
import org.joey.contacts.entities.Office;
import org.joey.contacts.repositories.CompanyRepository;
import org.joey.contacts.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Servlet implementation class OfficeServelet
 */
@Controller
public class OfficeController {

	@Autowired
    private OfficeRepository officeRepository;   
	
	@Autowired
	private CompanyRepository companyRepository;

	//check href on company/view.jsp
	@RequestMapping(value="/office",params="add", method=RequestMethod.GET)
	public String getAddOffice(@RequestParam("company_id") long companyId, Model model){
			model.addAttribute("company",companyRepository.findOne(companyId));
		return "office/add";
	}
	
	@RequestMapping(value="/office",params="edit", method=RequestMethod.GET)
	public String getEditOffice(@RequestParam long id,Model model){
		model.addAttribute("office",officeRepository.findOne(id));
		return "office/edit";
	}
	
	@RequestMapping(value="/office",method=RequestMethod.GET)
	public String getViewOffice(@RequestParam long id, Model model){
		model.addAttribute("office",officeRepository.findOne(id));
		return "office/view";
		
	}

	@RequestMapping(value="/office",params="add",method=RequestMethod.POST)
	public String postAddOffice(@RequestParam("company_id") long companyId, 
							String name,@RequestParam String street,  @RequestParam String city,
							@RequestParam String state, @RequestParam String zip){
			//create new office and address from for parameters, and persist
			Address address=new Address(street,city,state,zip);
			Company company=companyRepository.findOne(companyId);
			Office office=new Office(name,address,company);//new Office(name,address,company);
			office=officeRepository.save(office);
			
			//redirect to office view page
			return "redirect:office?id="+office.getId();
	}	
	
	@RequestMapping(value="/office",params="edit",method=RequestMethod.POST)
	public String postEditonOffice(@RequestParam long id, @RequestParam String name,@RequestParam String street,  @RequestParam String city, @RequestParam String state, @RequestParam String zip){
			//lookingup existing office and address, edit field and persist
		Office office=officeRepository.findOne(id);
		Address address=office.getAddress();  
		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		address.setZip(zip);
		office.setName(name);
		
		officeRepository.save(office);
		return "redirect:office?id="+office.getId();
		
	}
	
	@RequestMapping(value="/office",params="delete",method=RequestMethod.POST)
	public String postDeleteOffice(@RequestParam long id){
		Office office=officeRepository.findOne(id);
		officeRepository.delete(office);
		return "redirect:"+office.getCompany().getUrl();
	}
}
