package neu.edu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import neu.edu.entity.User;
import neu.edu.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDAO {
	
	
	public UserDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public User validateUser(String username, String password) {
		// TODO Auto-generated method stub
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from User "
										+ "where userName=:un "
										+ "and password=:pass");
		query.setString("un", username);
		query.setString("pass", password);

		List<User> userAccounts = (List<User>) query.list();
		if (userAccounts.size() > 0) {
			return userAccounts.get(0);
		}
		try {
			session.close();
		} catch (Exception ex) {
			
		}
		return null;
	}
	
	
	public User isUserNameInUse(String username) {
		// TODO Auto-generated method stub
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from User "
										+ "where userName=:un");
		query.setString("un", username);
		
		List<User> userAccounts = (List<User>) query.list();
		if (userAccounts.size() > 0) {
			return userAccounts.get(0);
		}
		try {
			session.close();
		} catch (Exception ex) {
			
		}
		return null;
	}
	

	public List<User> listUser() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from User");
		

		List<User> userAccounts = (List<User>) query.list();
		
		try {
			session.close();
		} catch (Exception ex) {
			return null;
		}
		return userAccounts;
	}



	public User GetUser(int userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from User where userId=:uId");
		query.setString("uId", String.valueOf(userId));

		List<User> userAccounts = (List<User>) query.list();
		if (userAccounts.size() > 0) {
			return userAccounts.get(0);
		}
		try {
			session.close();
		} catch (Exception ex) {
			
		}
		return null;
	}
	
@Transactional
	public boolean CreateNewUser(User userAccount) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(userAccount);
		
		return true;

	}

	@Transactional
	public boolean deleteUser(User userAccount) {
		// TODO Auto-generated method stub
		
	    Session session = sessionFactory.getCurrentSession();
		session.delete(userAccount);
			
		return true;
	}

	@Transactional
	public boolean updateUser(User userAccount) {
	
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(userAccount);
	
		return true;
	}


}
