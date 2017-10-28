package neu.edu.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.dao.RestaurantDAO;
import neu.edu.dao.ReviewDAO;
import neu.edu.dao.ReviewReplyDAO;
import neu.edu.dao.UserDAO;
import neu.edu.entity.Restaurant;
import neu.edu.entity.Review;
import neu.edu.entity.ReviewReply;
import neu.edu.entity.User;
import neu.edu.model.ReviewAndReply;
import neu.edu.model.ReviewModel;
import neu.edu.model.ReviewReplyModel;

@Service
public class ReviewReplyService {
	
	
	@Autowired
	private ReviewReplyDAO reviewReplyDao;
	
	@Autowired
	private RestaurantDAO restaurantDao;
	
	@Autowired
	private ReviewDAO reviewDao;
	
	@Autowired
	private UserDAO userDao;
	
	public List<ReviewReplyModel> GetAllReviewReplyForSpecificReview(int reviewId){

		List<ReviewReply> reviewReplydb = reviewReplyDao.GetReviewRepliesForSpecificReview(reviewId);
		List<ReviewReplyModel> reviewReplyList = null;
		if (reviewReplydb != null) {
			reviewReplyList = new ArrayList<>();
			for (ReviewReply reviewReply : reviewReplydb) {
				ReviewReplyModel reviewReplyModel = new ReviewReplyModel();
				
				reviewReplyModel.setReplyId(reviewReply.getReplyId());	
				User user = new User();
				if(reviewReply.getUser() != null)
				{
				user.setUserId(reviewReply.getUser().getUserId());	
				user.setFullName(reviewReply.getUser().getFullName());
				}
				reviewReplyModel.setUser(user);
				
				Restaurant restaurant = new Restaurant();
				if(reviewReply.getRestaurant() != null)
				{
				restaurant.setRestaurantId(reviewReply.getRestaurant().getRestaurantId());
				}
				reviewReplyModel.setRestaurant(restaurant);
			
				reviewReplyModel.setComments(reviewReply.getComments());
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(reviewReply.getReviewDate() != null)
				{
				reviewReplyModel.setReviewDate(sdf.format(reviewReply.getReviewDate()));
				}
				//reviewReplyModel.setReviewDate(String.valueOf(reviewReply.getReviewDate()));
						
				reviewReplyList.add(reviewReplyModel);
			}
		}
		return reviewReplyList;
		
	}
	
	public List<ReviewAndReply> GetReviewRepliesForSpecificRestaurant(int restaurantId){

		List<ReviewAndReply> reviewAndReplyList = new ArrayList<>();
		
		List<Review> reviewsdb = reviewDao.GetReviewForSpecificRestaurant(restaurantId);
		for(Review rw : reviewsdb)
		{
			//Setting properties for review model
			ReviewAndReply reviewAndReply = new ReviewAndReply();
			reviewAndReply.setReviewId(rw.getReviewId());
			User user = new User();
			if(rw.getUser() != null)
			{
			user.setUserId(rw.getUser().getUserId());
			user.setFullName(rw.getUser().getFullName());
			}
			reviewAndReply.setUser(user);
			
			Restaurant restaurant = new Restaurant();
			if(rw.getRestaurant() != null)
			{
			restaurant.setRestaurantId(rw.getRestaurant().getRestaurantId());
			}
			reviewAndReply.setRestaurant(restaurant);
		
			
			reviewAndReply.setComments(rw.getComments());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(rw.getReviewDate() != null)
			{
			reviewAndReply.setReviewDate(sdf.format(rw.getReviewDate()));
			}
			
			if(rw.getReviewId() != null)
			{
				//Get all the replies for this specific review
				List<ReviewReply> reviewReplydb = reviewReplyDao.GetReviewRepliesForSpecificReview(rw.getReviewId());
				if(reviewReplydb != null)
				{
				//List<ReviewReplyModel> reviewReplyList = new ArrayList<>();
					
				for(ReviewReply rwreply : reviewReplydb)
				{
					//Set all the properties for the reply model
					ReviewReplyModel reviewReplyModel = new ReviewReplyModel();
					reviewReplyModel.setReplyId(rwreply.getReplyId());	
					User userobject = new User();
					if(rwreply.getUser() != null)
					{
						userobject.setUserId(rwreply.getUser().getUserId());	
					}
					reviewReplyModel.setUser(userobject);
					
					Restaurant restaurantObject = new Restaurant();
					if(rwreply.getRestaurant() != null)
					{
						restaurantObject.setRestaurantId(rwreply.getRestaurant().getRestaurantId());
					}
					reviewReplyModel.setRestaurant(restaurantObject);
				
					reviewReplyModel.setComments(rwreply.getComments());
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
					if(rwreply.getReviewDate() != null)
					{
					reviewReplyModel.setReviewDate(sdf1.format(rwreply.getReviewDate()));
					}
					
					reviewAndReply.getReviewReply().add(reviewReplyModel);
				}
				}
			}
			
			reviewAndReplyList.add(reviewAndReply);
		}
		
	return reviewAndReplyList;
		
	}
	

	public boolean CreateReviewReply(int restaurantId,int userId,int reviewId,ReviewReplyModel reviewReply){
	
		ReviewReply reviewReplydb = new ReviewReply();
	
		reviewReplydb.setReplyId(reviewReply.getReplyId());
		reviewReplydb.setRestaurant(reviewReply.getRestaurant());
		reviewReplydb.setUser(reviewReply.getUser());
		reviewReplydb.setComments(reviewReply.getComments());
		Date today = Calendar.getInstance().getTime();
		reviewReplydb.setReviewDate(today);
		
		Restaurant restaurant = restaurantDao.GetRestaurant(restaurantId);
		User user = userDao.GetUser(userId);
		Review review = reviewDao.GetReview(reviewId);
		
		if(restaurant != null)
		{
			restaurant.getReviewReplies().add(reviewReplydb);		
			reviewReplydb.setRestaurant(restaurant);
			
		}
		if(user != null)
		{
			user.getReviewReplies().add(reviewReplydb);
			reviewReplydb.setUser(user);
		}
		if(review != null)
		{
			review.getReviewReplies().add(reviewReplydb);
			reviewReplydb.setReview(review);
		}
		
		
		return reviewReplyDao.CreateReviewReply(reviewReplydb);
		
	}	
	
	public boolean DeleteReviewReply(int restaurantId,int userId,int reviewId,int replyId, ReviewReplyModel reviewReply){
		
		ReviewReply reviewReplydb = new ReviewReply();
	
		if(replyId > 0)
		{
		reviewReplydb.setReplyId(replyId);
		}
		reviewReplydb.setRestaurant(reviewReply.getRestaurant());
		reviewReplydb.setUser(reviewReply.getUser());
		reviewReplydb.setComments(reviewReply.getComments());
	//	reviewReplydb.setReviewDate(reviewReply.getReviewDate());
		
		Restaurant restaurant = restaurantDao.GetRestaurant(restaurantId);
		User user = userDao.GetUser(userId);
		Review review = reviewDao.GetReview(reviewId);
		
		if(restaurant != null)
		{
			restaurant.getReviewReplies().add(reviewReplydb);		
			reviewReplydb.setRestaurant(restaurant);
			
		}
		if(user != null)
		{
			user.getReviewReplies().add(reviewReplydb);
			reviewReplydb.setUser(user);
		}
		if(review != null)
		{
			review.getReviewReplies().add(reviewReplydb);
			reviewReplydb.setReview(review);
		}
		
		
		return reviewReplyDao.DeleteReviewReply(reviewReplydb);
		
	}	

	
public boolean UpdateReviewReply(int restaurantId,int userId,int reviewId,int replyId, ReviewReplyModel reviewReply){
		
		ReviewReply reviewReplydb = new ReviewReply();
	
		reviewReplydb.setReplyId(replyId);
		reviewReplydb.setRestaurant(reviewReply.getRestaurant());
		reviewReplydb.setUser(reviewReply.getUser());
		reviewReplydb.setComments(reviewReply.getComments());
	//	reviewReplydb.setReviewDate(reviewReply.getReviewDate());
		
		Restaurant restaurant = restaurantDao.GetRestaurant(restaurantId);
		User user = userDao.GetUser(userId);
		Review review = reviewDao.GetReview(reviewId);
		
		if(restaurant != null)
		{
			restaurant.getReviewReplies().add(reviewReplydb);		
			reviewReplydb.setRestaurant(restaurant);
			
		}
		if(user != null)
		{
			user.getReviewReplies().add(reviewReplydb);
			reviewReplydb.setUser(user);
		}
		if(review != null)
		{
			review.getReviewReplies().add(reviewReplydb);
			reviewReplydb.setReview(review);
		}
		
		
		return reviewReplyDao.UpdateReviewReply(reviewReplydb);
		
	}	


}
