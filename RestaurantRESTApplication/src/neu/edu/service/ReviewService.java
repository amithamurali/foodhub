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
import neu.edu.dao.UserDAO;
import neu.edu.entity.Restaurant;
import neu.edu.entity.Review;
import neu.edu.entity.User;
import neu.edu.model.RestaurantModel;
import neu.edu.model.ReviewModel;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewDAO reviewDao;
	@Autowired
	private RestaurantDAO restaurantDao;
	@Autowired
	private UserDAO userDao;
	
	public List<ReviewModel> GetAllReviewForSpecificRestaurant(int restaurantId){

		List<Review> reviews = reviewDao.GetReviewForSpecificRestaurant(restaurantId);
		List<ReviewModel> reviewList = null;
		if (reviews != null) {
			reviewList = new ArrayList<>();
			for (Review review : reviews) {
				ReviewModel reviewModel = new ReviewModel();
				
				reviewModel.setReviewId(review.getReviewId());
				User user = new User();
				if(review.getUser() != null)
				{
				user.setUserId(review.getUser().getUserId());
				user.setFullName(review.getUser().getFullName());
				}
				reviewModel.setUser(user);
				
				Restaurant restaurant = new Restaurant();
				if(review.getRestaurant() != null)
				{
				restaurant.setRestaurantId(review.getRestaurant().getRestaurantId());
				}
				reviewModel.setRestaurant(restaurant);
			
				
				reviewModel.setComments(review.getComments());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
if(review.getReviewDate() != null)
{
				reviewModel.setReviewDate(sdf.format(review.getReviewDate()));
}
						
				reviewList.add(reviewModel);
			}
		}
		return reviewList;
		
	}

	public boolean CreateReview(int restaurantId,int userId,Review review){
	
		Review reviewdb = new Review();
	
		reviewdb.setReviewId(review.getReviewId());
		reviewdb.setRestaurant(review.getRestaurant());
		reviewdb.setUser(review.getUser());
		reviewdb.setComments(review.getComments());
		Date today = Calendar.getInstance().getTime();
		reviewdb.setReviewDate(today);
		
		Restaurant restaurant = restaurantDao.GetRestaurant(restaurantId);
		User user = userDao.GetUser(userId);
		
		if(restaurant != null && user != null)
		{
			user.getReviews().add(reviewdb);
			restaurant.getReviews().add(reviewdb);
			reviewdb.setRestaurant(restaurant);
			reviewdb.setUser(user);
		}
		
		return reviewDao.CreateReview(reviewdb);
		
	}

	public boolean DeleteReview(int restaurantId,int userId,int reviewId, Review review){
		
		Review reviewdb = new Review();
	
		if(reviewId > 0)
		{
		reviewdb.setReviewId(reviewId);
		}
		reviewdb.setRestaurant(review.getRestaurant());
		reviewdb.setUser(review.getUser());
		reviewdb.setComments(review.getComments());
		reviewdb.setReviewDate(review.getReviewDate());
		
		Restaurant restaurant = restaurantDao.GetRestaurant(restaurantId);
		User user = userDao.GetUser(userId);
		
		if(restaurant != null && user != null)
		{
			user.getReviews().add(reviewdb);
			restaurant.getReviews().add(reviewdb);
			reviewdb.setRestaurant(restaurant);
			reviewdb.setUser(user);
		}
		
		return reviewDao.DeleteReview(reviewdb);
		
	}
}
