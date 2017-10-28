package neu.edu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.dao.MenuItemsDAO;
import neu.edu.dao.RestaurantDAO;
import neu.edu.dao.RestaurantOrderDAO;
import neu.edu.dao.UserDAO;
import neu.edu.entity.MenuItems;
import neu.edu.entity.Restaurant;
import neu.edu.entity.RestaurantOrder;
import neu.edu.entity.User;
import neu.edu.model.MenuItemsModel;
import neu.edu.model.RestaurantOrderModel;

@Service
public class RestaurantOrderService {
	
	
	@Autowired
	private RestaurantOrderDAO restaurantOrderDAO;
	@Autowired
	private RestaurantDAO restaurantDao;
	@Autowired
	private UserDAO userDao;
	
	
	public List<RestaurantOrderModel> GetResturantsOrderForSpecificUser(int userId){

		List<RestaurantOrder> restaurantOrderDB = restaurantOrderDAO.GetResturantsOrderForSpecificUser(userId);
		List<RestaurantOrderModel> restaurantOrderList = null;
		if (restaurantOrderDB != null) {
			restaurantOrderList = new ArrayList<>();
			for (RestaurantOrder restaurantOrder : restaurantOrderDB) {
				RestaurantOrderModel restaurantOrderModel = new RestaurantOrderModel();		
				
				restaurantOrderModel.setOrderId(restaurantOrder.getOrderId());
				User user = new User();
				if(restaurantOrder.getUser() != null)
				{
				user.setUserId(restaurantOrder.getUser().getUserId());
				}
				restaurantOrderModel.setUser(user);
				
				Restaurant restaurant = new Restaurant();
				if(restaurantOrder.getRestaurant() != null)
				{
				restaurant.setRestaurantId(restaurantOrder.getRestaurant().getRestaurantId());
				}
				restaurantOrderModel.setRestaurant(restaurant);
			
				restaurantOrderModel.setAmount(restaurantOrder.getAmount());
				restaurantOrderModel.setOrderDate(restaurantOrder.getOrderDate());
				restaurantOrderModel.setStatus(restaurantOrder.getStatus());
				restaurantOrderModel.setPaymentType(restaurantOrder.getPaymentType());
				restaurantOrderList.add(restaurantOrderModel);
			}
		}
		return restaurantOrderList;
		
	}
	
	public List<RestaurantOrderModel> GetAllMenuItemsForSpecificRestaurant(int restaurantId){

		List<RestaurantOrder> restaurantOrderDB = restaurantOrderDAO.GetAllRestaurantOrdersForaRestaurant(restaurantId);
		List<RestaurantOrderModel> restaurantOrderList = null;
		if (restaurantOrderDB != null) {
			restaurantOrderList = new ArrayList<>();
			for (RestaurantOrder restaurantOrder : restaurantOrderDB) {
				RestaurantOrderModel restaurantOrderModel = new RestaurantOrderModel();		
				
				restaurantOrderModel.setOrderId(restaurantOrder.getOrderId());
				restaurantOrderModel.setRestaurant(restaurantOrder.getRestaurant());
				restaurantOrderModel.setUser(restaurantOrder.getUser());
				restaurantOrderModel.setAmount(restaurantOrder.getAmount());
				restaurantOrderModel.setOrderDate(restaurantOrder.getOrderDate());
				restaurantOrderModel.setStatus(restaurantOrder.getStatus());
				restaurantOrderModel.setPaymentType(restaurantOrder.getPaymentType());
				restaurantOrderList.add(restaurantOrderModel);
			}
		}
		return restaurantOrderList;
		
	}
	public boolean CreateRestaurantOrder(int restaurantId, int userId, RestaurantOrderModel restaurantOrder){
	
		RestaurantOrder restaurantOrderDB = new RestaurantOrder();
			
		restaurantOrderDB.setOrderId(restaurantOrder.getOrderId());
		restaurantOrderDB.setRestaurant(restaurantOrder.getRestaurant());
		restaurantOrderDB.setUser(restaurantOrder.getUser());
		restaurantOrderDB.setAmount(restaurantOrder.getAmount());
		restaurantOrderDB.setOrderDate(restaurantOrder.getOrderDate());
		restaurantOrderDB.setStatus(restaurantOrder.getStatus());
		restaurantOrderDB.setPaymentType(restaurantOrder.getPaymentType());
		
		
		Restaurant restaurant = restaurantDao.GetRestaurant(restaurantId);
		User user = userDao.GetUser(userId);
		
		if(restaurant != null && user != null)
		{
			user.getRestaurantOrders().add(restaurantOrderDB);
			restaurant.getRestaurantOrders().add(restaurantOrderDB);
			restaurantOrderDB.setRestaurant(restaurant);
			restaurantOrderDB.setUser(user);
		}
		
		return restaurantOrderDAO.CreateRestaurantOrder(restaurantOrderDB);
		
	}



}
