package neu.edu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.entity.Restaurant;
import neu.edu.entity.RestaurantOrder;
import neu.edu.entity.User;

@Service
public class RestaurantOrderDAO {
	
	public RestaurantOrderDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<RestaurantOrder> GetResturantsOrderForSpecificUser(int userId) {
		// TODO Auto-generated method stub
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Session session = sessionFactory.openSession();
 		Query query = session.createQuery("from RestaurantOrder where userId=:uId");
		query.setString("uId", String.valueOf(userId));

		List<RestaurantOrder> restaurantOrders = (List<RestaurantOrder>) query.list();
		if (restaurantOrders.size() > 0) {
			return restaurantOrders;
		}
		try {
			session.close();
		} catch (Exception ex) {
			
		}
		return null;
	}
	
	@Transactional
	public boolean CreateRestaurantOrder(RestaurantOrder restaurantOrder) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(restaurantOrder);
		return true;

	}

		
	@Transactional
	public boolean UpdateRestaurantOrder(RestaurantOrder restaurantOrder) {
		
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(restaurantOrder);
		
		return true;
	}
	
	public List<RestaurantOrder> GetAllRestaurantOrdersForaRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from RestaurantOrder where restaurantId=:rId");
		query.setString("rId", String.valueOf(restaurantId));

		List<RestaurantOrder> restaurantOrders = (List<RestaurantOrder>) query.list();
		if (restaurantOrders.size() > 0) {
			return restaurantOrders;
		}
		try {
			session.close();
		} catch (Exception ex) {
			
		}
		return null;

	}
}
