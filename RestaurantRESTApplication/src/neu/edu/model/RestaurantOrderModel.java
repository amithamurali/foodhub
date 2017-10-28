package neu.edu.model;

import java.util.Date;
import neu.edu.entity.MenuItems;
import neu.edu.entity.Restaurant;
import neu.edu.entity.User;

public class RestaurantOrderModel {

	private Integer orderId;
	private MenuItems menuItemsByMenuItem2;
	private MenuItems menuItemsByMenuItem3;
	private MenuItems menuItemsByMenuItem1;
	private Restaurant restaurant;
	private User user;
	private String amount;
	private Date orderDate;
	private String status;
	private String paymentType;
	
	
	
	public MenuItems getMenuItemsByMenuItem2() {
		return menuItemsByMenuItem2;
	}
	public void setMenuItemsByMenuItem2(MenuItems menuItemsByMenuItem2) {
		this.menuItemsByMenuItem2 = menuItemsByMenuItem2;
	}
	public MenuItems getMenuItemsByMenuItem3() {
		return menuItemsByMenuItem3;
	}
	public void setMenuItemsByMenuItem3(MenuItems menuItemsByMenuItem3) {
		this.menuItemsByMenuItem3 = menuItemsByMenuItem3;
	}
	public MenuItems getMenuItemsByMenuItem1() {
		return menuItemsByMenuItem1;
	}
	public void setMenuItemsByMenuItem1(MenuItems menuItemsByMenuItem1) {
		this.menuItemsByMenuItem1 = menuItemsByMenuItem1;
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
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	
	
}
