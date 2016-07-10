 package org.joey.contacts.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joey.contacts.entities.Contact;
import org.joey.contacts.repositories.ContactRepository;
	
	
/**
 * Servlet implementation class ContactListServlet
 */
@WebServlet("/contacts")
public class ContactListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ContactRepository contactRepository=new ContactRepository();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Contact> contacts;
		try {
			contacts = contactRepository.findAll();
			request.setAttribute("contacts", contacts);
		
			request.getRequestDispatcher("/jsp/contactList.jsp").forward(request, response);
			}catch (SQLException e) {
				throw new ServletException(e);
	}
	}

}
