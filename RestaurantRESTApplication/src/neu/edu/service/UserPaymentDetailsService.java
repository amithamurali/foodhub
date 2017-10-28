package neu.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.dao.RestaurantDAO;
import neu.edu.dao.UserDAO;
import neu.edu.dao.UserPaymentDetailsDAO;
import neu.edu.entity.Restaurant;
import neu.edu.entity.User;
import neu.edu.entity.UserPaymentDetails;
import neu.edu.model.RestaurantModel;
import neu.edu.model.UserPaymentDetailsModel;

@Service
public class UserPaymentDetailsService {
	

	@Autowired
	private UserPaymentDetailsDAO userPaymentDetailsDao;
	@Autowired
	private UserDAO userDao;

	public List<UserPaymentDetailsModel> GetUserPaymentDetailsForSpecificUser(int userId){

		List<UserPaymentDetails> userPaymentDetails = userPaymentDetailsDao.GetUserPaymentDetailsForSpecificUser(userId);
		List<UserPaymentDetailsModel> UserPaymentDetailsList = null;
		if (userPaymentDetails != null) {
			UserPaymentDetailsList = new ArrayList<>();
			for (UserPaymentDetails userPaymentDetail : userPaymentDetails) {
				UserPaymentDetailsModel userPaymentDetailsModel = new UserPaymentDetailsModel();
				
				userPaymentDetailsModel.setUserPaymentDetailsId(userPaymentDetail.getUserPaymentDetailsId());
				
				User user = new User();
				if(userPaymentDetail.getUser() != null)
				{
				user.setUserId(userPaymentDetail.getUser().getUserId());	
				}
			    userPaymentDetailsModel.setUser(user);
				userPaymentDetailsModel.setCardNumber(userPaymentDetail.getCardNumber());
				userPaymentDetailsModel.setCvv(userPaymentDetail.getCvv());
				userPaymentDetailsModel.setExpireDate(userPaymentDetail.getExpireDate());
				userPaymentDetailsModel.setCardType(userPaymentDetail.getCardType());
				UserPaymentDetailsList.add(userPaymentDetailsModel);
			}
		}
		return UserPaymentDetailsList;
		
	}

	public boolean CreateUserPaymentDetails(UserPaymentDetailsModel userPaymentDetail,int userId){
	
		UserPaymentDetails userPaymentDetailsdb = new UserPaymentDetails();
			
		userPaymentDetailsdb.setUserPaymentDetailsId(userPaymentDetail.getUserPaymentDetailsId());
		userPaymentDetailsdb.setUser(userPaymentDetail.getUser());
		userPaymentDetailsdb.setCardNumber(userPaymentDetail.getCardNumber());
		userPaymentDetailsdb.setCvv(userPaymentDetail.getCvv());
		userPaymentDetailsdb.setExpireDate(userPaymentDetail.getExpireDate());
		userPaymentDetailsdb.setCardType(userPaymentDetail.getCardType());
		
		User user = userDao.GetUser(userId);
		if(user != null)
		{
		user.getUserPaymentDetailses().add(userPaymentDetailsdb);
		userPaymentDetailsdb.setUser(user);
		}
		
		return userPaymentDetailsDao.CreateUserPaymentDetails(userPaymentDetailsdb);
		
	}


}
