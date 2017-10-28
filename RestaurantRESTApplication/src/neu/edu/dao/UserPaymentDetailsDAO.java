package neu.edu.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import neu.edu.entity.User;
import neu.edu.entity.UserPaymentDetails;

@Service
public class UserPaymentDetailsDAO {
	
public UserPaymentDetailsDAO(){
	
}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<UserPaymentDetails> GetUserPaymentDetailsForSpecificUser(int userId) {
		// TODO Auto-generated method stub
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Session session = sessionFactory.openSession();
 		Query query = session.createQuery("from UserPaymentDetails where user.userId=:uId");
		query.setString("uId", String.valueOf(userId));

		List<UserPaymentDetails> userPaymentDetails = (List<UserPaymentDetails>) query.list();
		if (userPaymentDetails.size() > 0) {
			return userPaymentDetails;
		}
		try {
			session.close();
		} catch (Exception ex) {
			
		}
		return null;
	}
	
	@Transactional
	public boolean CreateUserPaymentDetails(UserPaymentDetails userPaymentDetails) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(userPaymentDetails);
		return true;

	}

		
	@Transactional
	public boolean UpdateUserPaymentDetails(UserPaymentDetails userPaymentDetails) {
		
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(userPaymentDetails);
		
		return true;
	}


}
