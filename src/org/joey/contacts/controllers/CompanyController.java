package org.joey.contacts.controllers;

import org.joey.contacts.entities.Company;
import org.joey.contacts.entities.Person;
import org.joey.contacts.repositories.CompanyRepository;
import org.joey.contacts.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Servlet implementation class CompanyServelet
 */
@Controller
public class CompanyController {

	@Autowired
    private CompanyRepository companyRepository;   
	
	@Autowired
	private OfficeRepository officeRepository;

	
	@RequestMapping(value="/company",params="add", method=RequestMethod.GET)
	public String getAddCompany(){
		return "company/add";
	}
	
	@RequestMapping(value="/company",params="edit", method=RequestMethod.GET)
	public String getEditCompany(@RequestParam long id, Model model){
		model.addAttribute("company",companyRepository.findOne(id));
		return "company/edit";
	}
	
	@RequestMapping(value="/company",method=RequestMethod.GET)
	public String getViewCompany(@RequestParam long id, Model model){
				model.addAttribute("company",companyRepository.findOne(id));
		return "company/view";
		
	}

	@RequestMapping(value="/company",params="add",method=RequestMethod.POST)
	public String postAddCompany(@RequestParam String name){
			//create new company and address from for parameters, and persist
		
			Company company=new Company(name,null);//new Company(name,address);
			company=companyRepository.save(company);
			
			//redirect to company view page
			return "redirect:company?id="+company.getId();
	}	
	
	@RequestMapping(value="/company",params="edit",method=RequestMethod.POST)
	public String postEditonCompany(@RequestParam long id, @RequestParam String name){
			//lookingup existing company and address, edit field and persist
		Company company=companyRepository.findOne(id);
	
		companyRepository.save(company);
		return "redirect:company?id="+company.getId();
		
	}
	
	@RequestMapping(value="/company",params="delete",method=RequestMethod.POST)
	public String postDeleteCompany(@RequestParam long id){
		officeRepository.delete(companyRepository.findOne(id).getOffices());
		companyRepository.delete(id);
		return "redirect:contacts";	
	}
}
