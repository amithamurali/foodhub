package neu.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.dao.MenuItemsDAO;
import neu.edu.dao.RestaurantDAO;
import neu.edu.entity.MenuItems;
import neu.edu.entity.Restaurant;
import neu.edu.entity.User;
import neu.edu.model.MenuItemsModel;
import neu.edu.model.RestaurantModel;

@Service
public class MenuItemsService {
	
	@Autowired
	private MenuItemsDAO menuItemsDAO;
	@Autowired
	private RestaurantDAO restaurantDao;
	
	public List<MenuItemsModel> GetAllMenuItemsForSpecificRestaurant(int restaurantId){

		List<MenuItems> menuItemsDB = menuItemsDAO.GetMenuItemsForSpecificRestaurant(restaurantId);
		List<MenuItemsModel> menuItemList = null;
		if (menuItemsDB != null) {
			menuItemList = new ArrayList<>();
			for (MenuItems menuItem : menuItemsDB) {
				MenuItemsModel menuItemModel = new MenuItemsModel();
						
				menuItemModel.setMenuItemsId(menuItem.getMenuItemsId());
			    Restaurant restaurant = new Restaurant();
			    if(menuItem.getRestaurant() != null)
			    {
			    restaurant.setRestaurantId(menuItem.getRestaurant().getRestaurantId());
			    }
				menuItemModel.setRestaurant(restaurant);
				menuItemModel.setItemName(menuItem.getItemName());
				menuItemModel.setCategory(menuItem.getCategory());
				menuItemModel.setDescription(menuItem.getDescription());
				menuItemModel.setPrice(menuItem.getPrice());
			
				menuItemList.add(menuItemModel);
			}
		}
		return menuItemList;
		
	}
	
	public boolean CreateMenuItem(int restaurantId, MenuItemsModel menuItem){
	
		MenuItems menuItemdb = new MenuItems();
			
		menuItemdb.setMenuItemsId(menuItem.getMenuItemsId());
		menuItemdb.setRestaurant(menuItem.getRestaurant());
		menuItemdb.setItemName(menuItem.getItemName());
		menuItemdb.setCategory(menuItem.getCategory());
		menuItemdb.setDescription(menuItem.getDescription());
		menuItemdb.setPrice(menuItem.getPrice());
		
		Restaurant restaurant = restaurantDao.GetRestaurant(restaurantId);
				
		if(restaurant != null)
		{
			
			restaurant.getMenuItemses().add(menuItemdb);
			menuItemdb.setRestaurant(restaurant);
			
		}
		
		
		return menuItemsDAO.CreateMenuItem(menuItemdb);
		
	}

	public boolean DeleteMenuItem(int restaurantId,int menuItemId, MenuItemsModel menuItem){
		
		MenuItems menuItemdb = new MenuItems();
			
		if(menuItemId > 0)
		{
		menuItemdb.setMenuItemsId(menuItemId);
		}
		menuItemdb.setRestaurant(menuItem.getRestaurant());
		menuItemdb.setItemName(menuItem.getItemName());
		menuItemdb.setCategory(menuItem.getCategory());
		menuItemdb.setDescription(menuItem.getDescription());
		menuItemdb.setPrice(menuItem.getPrice());
		
		Restaurant restaurant = restaurantDao.GetRestaurant(restaurantId);
				
		if(restaurant != null)
		{
			
			restaurant.getMenuItemses().add(menuItemdb);
			menuItemdb.setRestaurant(restaurant);
			
		}
		
		
		return menuItemsDAO.DeleteMenuItem(menuItemdb);
		
	}

}
