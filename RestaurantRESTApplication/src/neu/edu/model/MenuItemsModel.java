package neu.edu.model;

import neu.edu.entity.Restaurant;

public class MenuItemsModel {
	
	private Integer menuItemsId;
	private Restaurant restaurant;
	private String itemName;
	private String description;
	private String price;
	private String category;
	
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public Integer getMenuItemsId() {
		return menuItemsId;
	}
	public void setMenuItemsId(Integer menuItemsId) {
		this.menuItemsId = menuItemsId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
