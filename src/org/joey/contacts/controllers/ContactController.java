package org.joey.contacts.controllers;


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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//add a new contact
			if(request.getParameter("add")!=null){
				//create new contact and address from form parameter, and persist
				Address address=new Address(request.getParameter("street"),request.getParameter("city"),request.getParameter("state"),request.getParameter("zip"));
					
				//change create to save	
/*//	delete		address=addressRepository.save(address); add:
				@OneToOne(cascade=CascadeType.ALL)//
				private Address address;*/	
				
				Contact contact=new Contact(request.getParameter("name"), address);
					contact=contactRepository.save(contact);
					
					System.out.println("contact id: "+ contact.getId());
					
					//redirect to contact view list page
					response.sendRedirect("contact?id="+ contact.getId());
					
			}else if(request.getParameter("edit")!=null){
				//look up existing contact and address, edit fields and persist
				long id=Long.parseLong(request.getParameter("id"));
				
				 Contact contact=contactRepository.find(id);
				 Address address=contact.getAddress();
				 
					 contact.setName(request.getParameter("name"));
					 address.setStreet(request.getParameter("street"));
					 address.setCity(request.getParameter("city"));
					 address.setState(request.getParameter("state"));
					 address.setZip(request.getParameter("zip"));
//					 address.setId(contact.getAddress());
					 
					 //change update to save
					 addressRepository.save(address);
					 contactRepository.save(contact);
					 
					 //redirect to contact view page
					 response.sendRedirect("contact?id="+ contact.getId());
			}else if(request.getParameter("delete")!=null){
					
					//look up existing contact, and delete
					long id =Long.parseLong(request.getParameter("id"));
					Contact contact=contactRepository.find(id);
					 					
					contactRepository.delete(contact);	
					//delete :  addressRepository.delete(address);
					
					//redirect to contact contact list view page
					response.sendRedirect("contacts");
			}else{
				super.doPost(request, response);
				
			}
	}

}
