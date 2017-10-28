package neu.edu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.entity.Restaurant;
import neu.edu.entity.Review;
import neu.edu.entity.ReviewReply;
import neu.edu.entity.User;

@Service
public class ReviewDAO {
	
	public ReviewDAO()
	{
		
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Review> GetReviewForSpecificRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Session session = sessionFactory.openSession();
 		Query query = session.createQuery("from Review where restaurant.restaurantId=:rId");
		query.setString("rId", String.valueOf(restaurantId));

		List<Review> reviews = (List<Review>) query.list();
		if (reviews.size() > 0) {
			return reviews;
		}
		try {
			session.close();
		} catch (Exception ex) {
			
		}
		return null;
	}
	
	@Transactional
	public boolean CreateReview(Review review) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(review);
		return true;

	}

		
	@Transactional
	public boolean UpdateReview(Review review) {
		
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(review);
		
		return true;
	}

	@Transactional
	public boolean DeleteReview(Review review) {
		
			Session session = sessionFactory.getCurrentSession();
			session.delete(review);
		
		return true;
	}

	public Review GetReview(int reviewId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Review where reviewId=:rId");
		query.setString("rId", String.valueOf(reviewId));

		List<Review> reviews = (List<Review>) query.list();
		if (reviews.size() > 0) {
			return reviews.get(0);
		}
		try {
			session.close();
		} catch (Exception ex) {
			
		}
		return null;
	}

}
