package neu.edu.model;

import java.util.Date;
import neu.edu.entity.Restaurant;
import neu.edu.entity.User;

public class ReviewModel {

	private Integer reviewId;
	private Restaurant restaurant;
	private User user;
	private String comments;
	private String reviewDate;
	public Integer getReviewId() {
		return reviewId;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	

	
}
