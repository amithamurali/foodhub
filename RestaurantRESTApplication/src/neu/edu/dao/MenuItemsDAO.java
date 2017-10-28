package neu.edu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.entity.MenuItems;
import neu.edu.entity.Restaurant;
import neu.edu.entity.RestaurantOrder;
import neu.edu.entity.ReviewReply;
import neu.edu.entity.User;

@Service
public class MenuItemsDAO {
	
	public MenuItemsDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<MenuItems> GetMenuItemsForSpecificRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Session session = sessionFactory.openSession();
 		Query query = session.createQuery("from MenuItems where restaurant.restaurantId=:rId");
		query.setString("rId", String.valueOf(restaurantId));

		List<MenuItems> menuItems = (List<MenuItems>) query.list();
		if (menuItems.size() > 0) {
			return menuItems;
		}
		try {
			session.close();
		} catch (Exception ex) {
			
		}
		return null;
	}
	
	@Transactional
	public boolean CreateMenuItem(MenuItems menuItem) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(menuItem);
		return true;

	}

		
	@Transactional
	public boolean UpdateMenuItem(MenuItems menuItem) {
		
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(menuItem);
		
		return true;
	}
	
	@Transactional
	public boolean DeleteMenuItem(MenuItems menuItem) {
		
			Session session = sessionFactory.getCurrentSession();
			session.delete(menuItem);
		
		return true;
	}

}