package org.joey.contacts.controllers;



import org.joey.contacts.entities.Address;
import org.joey.contacts.entities.Person;
import org.joey.contacts.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Servlet implementation class PersonServelet
 */
@Controller
public class PersonController {

	@Autowired
    private PersonRepository personRepository;   

	
	@RequestMapping(value="/person",params="add", method=RequestMethod.GET)
	public String getAddPerson(){
		return "person/add";
	}
	
	@RequestMapping(value="/person",params="edit", method=RequestMethod.GET)
	public String getEditPerson(@RequestParam("edit") long id,Model model){
		model.addAttribute("person",personRepository.findOne(id));
		return "person/edit";
	}
	
	@RequestMapping(value="/person",params="id",method=RequestMethod.GET)
	public String getViewPerson(@RequestParam long id, Model model){
		model.addAttribute("person",personRepository.findOne(id));
		return "person/view";
		
	}

	@RequestMapping(value="/person",params="add",method=RequestMethod.POST)
	public String postAddPerson(@RequestParam String name,@RequestParam String street,  @RequestParam String city, @RequestParam String state, @RequestParam String zip){
			//create new person and address from for parameters, and persist
			Address address=new Address(street,city,state,zip);
			Person person=new Person(name,address);//new Person(name,address);
			person=personRepository.save(person);
			
			//redirect to person view page
			return "redirect:person?id="+person.getId();
	}	
	
	@RequestMapping(value="/person",params="edit",method=RequestMethod.POST)
	public String postEditonPerson(@RequestParam long id, @RequestParam String name,@RequestParam String street,  @RequestParam String city, @RequestParam String state, @RequestParam String zip){
			//lookingup existing person and address, edit field and persist
		Person person=personRepository.findOne(id);
		Address address=person.getAddress();  
		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		address.setZip(zip);
		person.setName(name);
		
		personRepository.save(person);
		return "redirect:person?id="+person.getId();
		
	}
	
	@RequestMapping(value="/person",params="delete",method=RequestMethod.POST)
	public String postDeletePerson(@RequestParam long id){
		personRepository.delete(id);
		return "redirect:contacts";	
	}
}
