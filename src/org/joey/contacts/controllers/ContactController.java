package org.joey.contacts.controllers;



import org.joey.contacts.entities.Address;
import org.joey.contacts.entities.Contact;
import org.joey.contacts.repositories.CompanyRepository;
import org.joey.contacts.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Servlet implementation class ContactServelet
 */
@Controller
public class ContactController {

	@Autowired
    private ContactRepository contactRepository;   
	
	@Autowired
	private CompanyRepository companyRepository;
    
	@RequestMapping(value="/contacts",method=RequestMethod.GET)
	public String getContactList(Model model){
		model.addAttribute("contacts",contactRepository.findAll());
		return "contact/list";
	}
	
}
