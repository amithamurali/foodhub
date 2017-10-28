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
public class ReviewReplyDAO {
	
	public ReviewReplyDAO()
	{
		
	}
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<ReviewReply> GetReviewRepliesForSpecificReview(int reviewId) {
		// TODO Auto-generated method stub
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Session session = sessionFactory.openSession();
 		Query query = session.createQuery("from ReviewReply where review.reviewId=:rId");
		query.setString("rId", String.valueOf(reviewId));

		List<ReviewReply> reviewReplies = (List<ReviewReply>) query.list();
		if (reviewReplies.size() > 0) {
			return reviewReplies;
		}
		try {
			session.close();
		} catch (Exception ex) {
			
		}
		return null;
	}
	
	@Transactional
	public boolean CreateReviewReply(ReviewReply reviewReply) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(reviewReply);
		return true;

	}

		
	@Transactional
	public boolean UpdateReviewReply(ReviewReply reviewReply) {
		
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(reviewReply);
		
		return true;
	}

	@Transactional
	public boolean DeleteReviewReply(ReviewReply reviewReply) {
		
			Session session = sessionFactory.getCurrentSession();
			session.delete(reviewReply);
		
		return true;
	}

}
