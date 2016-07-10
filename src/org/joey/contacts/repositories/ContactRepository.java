package org.joey.contacts.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.joey.contacts.entities.Contact;

public class ContactRepository {
	private final DataSource ds;
	
	public ContactRepository(){
		try {
			Context context=new InitialContext();
			try{
				ds=(DataSource) context.lookup("java:comp/env/jdbc/contactsdb");
			}finally{
				context.close();
			}
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void init() throws SQLException{
		Connection connection=ds.getConnection();
		try {
			 Statement statement=connection.createStatement();
			  	try {
					String sql="CREATE TABLE contact(id int NOT NULL AUTO_INCREMENT,"
							+ "name varchar(50),"
							+ "addressId int,"
							+ "PRIMARY KEY (id),"
							+ "FOREIGN KEY (addressId) REFERENCES address(id))";
				    statement.execute(sql);
				} finally {
					statement.close();
				}
		
		}finally{
			connection.close();
		}
	}
	
	public List<Contact> findAll() throws SQLException {
		Connection connection =ds.getConnection();
		try{
		Statement statement=connection.createStatement();
			try{
				String sql="SELECT * FROM contact;";
				
				List<Contact> list=new ArrayList<Contact>();
				ResultSet results=statement.executeQuery(sql);
				try{
					while(results.next()){
						list.add(unmarchal(results));
					}
					return list;
				}finally{
					results.close();
				}
			}finally{
				statement.close();
			}
		}finally{
			connection.close();
		}		
	}
	
	public Contact find(long id) throws SQLException{
		Connection connection=ds.getConnection();
		String sql="SELECT id, name,addressId FROM contact where id= "+id;
		try{
		Statement statement=connection.createStatement();
			try{
			ResultSet results=statement.executeQuery(sql);
				try{
				if(results.next()){
					return unmarchal(results);
				}else{
					return null;
				}
				}finally{
					results.close();
				}
			}finally{
				statement.close();
			}
		}finally{
			connection.close();
		}
	}
	
	public void create(Contact contact) throws SQLException{
		Connection connection=ds.getConnection();
		try{
			PreparedStatement pstmt=null;
			try{
				String sql="insert into contact(name,addressId) values(?,?)";
				pstmt=connection.prepareStatement(sql);
				
				 pstmt.setString(1, contact.getName());
				 pstmt.setLong(2, contact.getAddressId());

				 pstmt.executeUpdate();
					 System.out.println("insert an contact successfully");
			}finally{
				pstmt.close();
			}
		}finally{
			connection.close();
		}
	}

	private Contact unmarchal(ResultSet results) throws SQLException {
		Contact contact=new Contact();
		contact.setId(results.getLong("id"));
		contact.setName(results.getString("name"));
		contact.setAddressId(results.getLong("addressId"));
		return contact;
	}
	
	public void update(Contact contact) throws SQLException{
		Connection connection=ds.getConnection();
		try{
			PreparedStatement pstmt=null;
			String sql="update contact set name=? where id=?";
			try{
				pstmt=connection.prepareStatement(sql);
				pstmt.setString(1,contact.getName());
				pstmt.setLong(2, contact.getId());
	
				// execute update SQL stetement
				pstmt.executeUpdate();
			}finally{
				pstmt.close();
			}
		}finally{
			connection.close();
		}
	}
	
	public void delete(Contact contact) throws SQLException{
		Connection connection=ds.getConnection();
		try{
			Statement statement=connection.createStatement();
			String sql="delete from contact where id = "+ contact.getId();
			statement.executeUpdate(sql);
		}finally{
			connection.close();
		}
	}
	
}
