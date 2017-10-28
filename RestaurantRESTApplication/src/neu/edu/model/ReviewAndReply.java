package neu.edu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import neu.edu.entity.Restaurant;
import neu.edu.entity.User;

public class ReviewAndReply {
	
	private Integer reviewId;
	private Restaurant restaurant;
	private List<ReviewReplyModel> reviewReply;
	private User user;
	private String comments;
	private String reviewDate;
	
	public ReviewAndReply()
	{
		reviewReply = new ArrayList<>();
	}
	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public List<ReviewReplyModel> getReviewReply() {
		return reviewReply;
	}
	public void setReviewReply(List<ReviewReplyModel> reviewReply) {
		this.reviewReply = reviewReply;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
