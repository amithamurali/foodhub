package neu.edu.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.dao.RestaurantDAO;
import neu.edu.dao.UserDAO;
import neu.edu.entity.Restaurant;
import neu.edu.entity.User;
import neu.edu.model.RestaurantModel;

@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantDAO restaurantDao;
	@Autowired
	private UserDAO userDao;
	
	
	public List<RestaurantModel> GetAllRestaurant(){

		List<Restaurant> restaurants = restaurantDao.GetAllRestaurants();
		List<RestaurantModel> restaurantList = null;
		if (restaurants != null) {
			restaurantList = new ArrayList<>();
			for (Restaurant restaurant : restaurants) {
				RestaurantModel restaurantModel = new RestaurantModel();
				
				restaurantModel.setRestaurantId(restaurant.getRestaurantId());
				restaurantModel.setRestaurantName(restaurant.getRestaurantName());
				restaurantModel.setLocation(restaurant.getLocation());
				restaurantModel.setCuisuine(restaurant.getCuisuine());
				restaurantModel.setPhoneNumber(restaurant.getPhoneNumber());
				restaurantModel.setPrice(restaurant.getPrice());
				restaurantModel.setStatus(restaurant.getStatus());
				
				User user = new User();
				if(restaurant.getUser() != null)
				{
				user.setUserId(restaurant.getUser().getUserId());	
				}
				restaurantModel.setUser(user);
								
				restaurantList.add(restaurantModel);
			}
		}
		return restaurantList;
		
	}

	
	public RestaurantModel GetResturantDetailsBasedOnId(int restaurantId){

		Restaurant restaurantdb = restaurantDao.GetResturantDetailsBasedOnId(restaurantId);
		
				RestaurantModel restaurantModel = new RestaurantModel();
				
				restaurantModel.setRestaurantId(restaurantdb.getRestaurantId());
				restaurantModel.setRestaurantName(restaurantdb.getRestaurantName());
				restaurantModel.setLocation(restaurantdb.getLocation());
				restaurantModel.setCuisuine(restaurantdb.getCuisuine());
				restaurantModel.setPhoneNumber(restaurantdb.getPhoneNumber());
				restaurantModel.setPrice(restaurantdb.getPrice());
				restaurantModel.setStatus(restaurantdb.getStatus());
				User user = new User();
				if(restaurantdb.getUser() != null)
				{
				user.setUserId(restaurantdb.getUser().getUserId());
				}
				restaurantModel.setUser(user);				
				return restaurantModel;
		
	}
	public List<RestaurantModel> GetAllRestaurantForSpecificUser(int userId){

		List<Restaurant> restaurants = restaurantDao.GetResturantsForSpecificUser(userId);
		List<RestaurantModel> restaurantList = null;
		if (restaurants != null) {
			restaurantList = new ArrayList<>();
			for (Restaurant restaurant : restaurants) {
				RestaurantModel restaurantModel = new RestaurantModel();
				
				restaurantModel.setRestaurantId(restaurant.getRestaurantId());
				restaurantModel.setRestaurantName(restaurant.getRestaurantName());
				restaurantModel.setLocation(restaurant.getLocation());
				restaurantModel.setCuisuine(restaurant.getCuisuine());
				restaurantModel.setPhoneNumber(restaurant.getPhoneNumber());
				restaurantModel.setPrice(restaurant.getPrice());
				restaurantModel.setStatus(restaurant.getStatus());
				User user = new User();
				if(restaurant.getUser() != null)
				{
				user.setUserId(restaurant.getUser().getUserId());
				}
				restaurantModel.setUser(user);				
				restaurantList.add(restaurantModel);
			}
		}
		return restaurantList;
		
	}

	public boolean CreateRestaurant(RestaurantModel restaurant,int userId){
	
		Restaurant restaurantdb = new Restaurant();
			
		restaurantdb.setRestaurantId(restaurant.getRestaurantId());
		restaurantdb.setRestaurantName(restaurant.getRestaurantName());
		restaurantdb.setLocation(restaurant.getLocation());
		restaurantdb.setCuisuine(restaurant.getCuisuine());
		restaurantdb.setPhoneNumber(restaurant.getPhoneNumber());
		restaurantdb.setPrice(restaurant.getPrice());
		restaurantdb.setStatus(restaurant.getStatus());
		restaurantdb.setIsDisabled("false");
		
		User user = userDao.GetUser(userId);
		if(user != null)
		{
		user.getRestaurants().add(restaurantdb);
		restaurantdb.setUser(user);
		}
		
		return restaurantDao.CreateRestaurant(restaurantdb);
		
	}

	public boolean UpdateRestaurant(RestaurantModel restaurant,int userId,int restaurantid){
		
		Restaurant restaurantdb = new Restaurant();
			
		if(restaurantid > 0)
		{
		restaurantdb.setRestaurantId(restaurantid);
		}
		restaurantdb.setRestaurantName(restaurant.getRestaurantName());
		restaurantdb.setLocation(restaurant.getLocation());
		restaurantdb.setCuisuine(restaurant.getCuisuine());
		restaurantdb.setPhoneNumber(restaurant.getPhoneNumber());
		restaurantdb.setPrice(restaurant.getPrice());
		restaurantdb.setStatus(restaurant.getStatus());
		restaurantdb.setIsDisabled("false");
		
		User user = userDao.GetUser(userId);
		if(user != null)
		{
		user.getRestaurants().add(restaurantdb);
		restaurantdb.setUser(user);
		}
		
		return restaurantDao.UpdateRestaurant(restaurantdb);
		
	}
	
public boolean DisableRestaurant(RestaurantModel restaurant,int userId,int restaurantid){
		
		Restaurant restaurantdb = new Restaurant();
			
		if(restaurantid > 0)
		{
		restaurantdb.setRestaurantId(restaurantid);
		}
		restaurantdb.setRestaurantName(restaurant.getRestaurantName());
		restaurantdb.setLocation(restaurant.getLocation());
		restaurantdb.setCuisuine(restaurant.getCuisuine());
		restaurantdb.setPhoneNumber(restaurant.getPhoneNumber());
		restaurantdb.setPrice(restaurant.getPrice());
		restaurantdb.setStatus(restaurant.getStatus());
		restaurantdb.setIsDisabled("true");
		
		User user = userDao.GetUser(userId);
		if(user != null)
		{
		user.getRestaurants().add(restaurantdb);
		restaurantdb.setUser(user);
		}
		
		return restaurantDao.UpdateRestaurant(restaurantdb);
		
	}
}
