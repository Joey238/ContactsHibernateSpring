package org.joey.contacts.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joey.contacts.entities.Address;
import org.joey.contacts.entities.Contact;
import org.joey.contacts.repositories.AddressRepository;
import org.joey.contacts.repositories.ContactRepository;

import com.mysql.fabric.Response;

/**
 * Servlet implementation class ContactServelet
 */
@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AddressRepository addressRepository=new AddressRepository();
    private final ContactRepository contactRepository=new ContactRepository();   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String add=request.getParameter("add");
		 	// delete try-catch
			 if(add!=null){
				 request.getRequestDispatcher("jsp/addContact.jsp").forward(request, response);
			 }else{ 
				 //get contact id from request parameter, and populate model with the contact and address objects. 
				 long id=Long.parseLong(request.getParameter("id"));
				 Contact contact=contactRepository.find(id);
				 
				 Address address=addressRepository.find(contact.getAddressId());
				 System.out.println("do get: address id: ***********"+ contact.getAddressId()+" address id: "+ address.getId());

				 request.setAttribute("contact", contact);
				 request.setAttribute("address",address);				 
			
				 //dispatch either to the edit page or to the view page
				 if(request.getParameter("edit")!=null){
					 request.getRequestDispatcher("jsp/editContact.jsp").forward(request, response);
				 }else{
				  request.getRequestDispatcher("jsp/viewContact.jsp").forward(request, response);
				 } 
			 }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//add a new contact
			if(request.getParameter("add")!=null){
				//create new contact and address from form parameter, and persist
				Address address=new Address(request.getParameter("street"),request.getParameter("city"),request.getParameter("state"),request.getParameter("zip"));
					//chage create to save
					address=(Address)addressRepository.save(address);
					Contact contact=new Contact(request.getParameter("name"),address.getId());
					contact=(Contact) contactRepository.save(contact);
					System.out.println("contact id: "+ contact.getId());
					
					//redirect to contact view list page
					response.sendRedirect("contact?id="+ contact.getId());
					
			}else if(request.getParameter("edit")!=null){
				//look up existing contact and address, edit fields and persist
				long id=Long.parseLong(request.getParameter("id"));
				
				 Contact contact=contactRepository.find(id);
				 Address address=addressRepository.find(contact.getAddressId());
					 contact.setName(request.getParameter("name"));
					 address.setStreet(request.getParameter("street"));
					 address.setCity(request.getParameter("city"));
					 address.setState(request.getParameter("state"));
					 address.setZip(request.getParameter("zip"));
					 address.setId(contact.getAddressId());
					 
					 //change update to save
					 addressRepository.save(address);
					 contactRepository.save(contact);
					 
					 //redirect to contact view page
					 response.sendRedirect("contact?id="+ contact.getId());
			}else if(request.getParameter("delete")!=null){
					
					//look up existing contact and address, and delete
					long id =Long.parseLong(request.getParameter("id"));
					Contact contact=contactRepository.find(id);
					Address address=addressRepository.find(contact.getAddressId());
					
					System.out.println("contact id*******: "+ contact.getId()+ " ****address id****:"+ address.getId());
					
					addressRepository.delete(address);
					contactRepository.delete(contact);	
					
					//redirect to contact contact list view page
					response.sendRedirect("contacts");
			}else{
				super.doPost(request, response);
				
			}
	}

}
