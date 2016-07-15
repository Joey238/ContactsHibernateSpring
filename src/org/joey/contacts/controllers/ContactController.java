package org.joey.contacts.controllers;


import org.joey.contacts.entities.Address;
import org.joey.contacts.entities.Contact;
import org.joey.contacts.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.fabric.Response;

/**
 * Servlet implementation class ContactServelet
 */
@Controller
public class ContactController {

	@Autowired
    private ContactRepository contactRepository;   
    
	@RequestMapping(value="/contacts",method=RequestMethod.GET)
	public String getContactList(Model model){
		model.addAttribute(contactRepository.findAll());
		return "contact/list";
	}
	
	@RequestMapping(value="/contact",params="add", method=RequestMethod.GET)
	public String getAddContact(){
		return "contact/add";
	}
	
	@RequestMapping(value="/contact",params="edit",method=RequestMethod.GET)
	public String getEditContact(@RequestParam long id,Model model){
		model.addAttribute("contact",contactRepository.findOne(id));
		return "contact/edit";
	}
	
	@RequestMapping(value="/contact",method=RequestMethod.GET)
	public String getViewContact(@RequestParam long id, Model model){
		model.addAttribute("contact",contactRepository.findOne(id));
		return "contact/view";
		
	}

	@RequestMapping(value="/contact",params="add",method=RequestMethod.POST)
	public String postAddContact(@RequestParam String name,@RequestParam String street,  @RequestParam String city, @RequestParam String state, @RequestParam String zip){
			//create new contact and address from for parameters, and persist
			Address address=new Address(street,city,state,zip);
			Contact contact=new Contact(name,address);
			contact=contactRepository.save(contact);
			
			//redirect to contact view page
			return "redirect:contact?id="+contact.getId();
	}	
	
	@RequestMapping(value="/contact",params="edit",method=RequestMethod.POST)
	public String postEditonContac(@RequestParam long id, @RequestParam String name,@RequestParam String street,  @RequestParam String city, @RequestParam String state, @RequestParam String zip){
			//lookingup existing contact and address, edit field and persist
		Contact contact=contactRepository.findOne(id);
		Address address=contact.getAddress();
		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		address.setZip(zip);
		contact.setName(name);
		contactRepository.save(contact);
		return "redirect:contact?id="+contact.getId();
		
	}
	
	@RequestMapping(value="/contact",params="delete",method=RequestMethod.POST)
	public String postDeleteContact(@RequestParam long id){
		Contact contact=contactRepository.findOne(id);
		contactRepository.delete(contact);
		return "redirect:contacts";
		
	}
	


}
