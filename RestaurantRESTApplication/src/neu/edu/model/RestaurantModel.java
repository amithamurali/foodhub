package neu.edu.model;

import java.util.HashSet;
import java.util.Set;

import neu.edu.entity.User;

public class RestaurantModel {
	
	
	private User user;
	private Integer restaurantId;
	private String restaurantName;
	private String location;
	private String cuisuine;
	private String phoneNumber;
	private String price;
	private String status;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCuisuine() {
		return cuisuine;
	}
	public void setCuisuine(String cuisuine) {
		this.cuisuine = cuisuine;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
