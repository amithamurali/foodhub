package neu.edu.dao;

import java.util.List;
import neu.edu.entity.Restaurant;
import neu.edu.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantDAO {
	
	public RestaurantDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Restaurant> GetResturantsForSpecificUser(int userId) {
		// TODO Auto-generated method stub
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Session session = sessionFactory.openSession();
 		Query query = session.createQuery("from Restaurant where user.userId=:uId and isDisabled=:Disabled");
	    query.setString("Disabled", "false");				
		query.setString("uId", String.valueOf(userId));

		List<Restaurant> restaurants = (List<Restaurant>) query.list();
		if (restaurants.size() > 0) {
			return restaurants;
		}
		try {
			session.close();
		} catch (Exception ex) {
			
		}
		return null;
	}
	
	public Restaurant GetResturantDetailsBasedOnId(int restaurantId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
 		Query query = session.createQuery("from Restaurant where restaurantId=:rId");
		query.setString("rId", String.valueOf(restaurantId));

		List<Restaurant> restaurants = (List<Restaurant>) query.list();
		if (restaurants.size() > 0) {
			return restaurants.get(0);
		}
		try {
			session.close();
		} catch (Exception ex) {
			
		}
		return null;
	}
	
	public Restaurant GetRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Restaurant where restaurantId=:rId");
		query.setString("rId", String.valueOf(restaurantId));

		List<Restaurant> restaurants = (List<Restaurant>) query.list();
		if (restaurants.size() > 0) {
			return restaurants.get(0);
		}
		try {
			session.close();
		} catch (Exception ex) {
			
		}
		return null;
	}

	
	@Transactional
	public boolean CreateRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(restaurant);
		return true;

	}

		
	@Transactional
	public boolean UpdateRestaurant(Restaurant restaurant) {
		
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(restaurant);
		
		return true;
	}
	
	
	
	public List<Restaurant> GetAllRestaurants() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Restaurant where isDisabled=:disabled");
		query.setString("disabled", "false");
		

		List<Restaurant> restaurants = (List<Restaurant>) query.list();
		
		try {
			session.close();
		} catch (Exception ex) {
			return null;
		}
		return restaurants;
	}


}
