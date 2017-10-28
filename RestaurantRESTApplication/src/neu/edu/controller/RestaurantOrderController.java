package neu.edu.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.model.MenuItemsModel;
import neu.edu.model.RestaurantOrderModel;
import neu.edu.service.MenuItemsService;
import neu.edu.service.RestaurantOrderService;

@Path("/restaurantOrder")
@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantOrderController {
	
	@Autowired
	private RestaurantOrderService restaurantOrderService;

	@GET
	@Path("/user/{uid}")
	//@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
	public Response GetResturantsOrderForSpecificUser(@PathParam("uid") String userId) {

		List<RestaurantOrderModel> restaurantOrderModels = null;
		try {
			restaurantOrderModels = restaurantOrderService.GetResturantsOrderForSpecificUser(Integer.parseInt(userId));	

		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}

		if (restaurantOrderModels == null) {
			return Response.ok().status(422).entity(new ResponseError("restaurant order Not Found")).build();

		}
		return Response.ok().entity(restaurantOrderModels).build();

	}
	
	
	@GET
	@Path("/restaurant/{rid}")
	//@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
	public Response GetAllRestaurantOrdersForaRestaurant(@PathParam("rid") String restaurantId) {

		List<RestaurantOrderModel> restaurantOrderModels = null;
		try {
			restaurantOrderModels = restaurantOrderService.GetAllMenuItemsForSpecificRestaurant(Integer.parseInt(restaurantId));	

		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}

		if (restaurantOrderModels == null) {
			return Response.ok().status(422).entity(new ResponseError("restaurant order Not Found")).build();

		}
		return Response.ok().entity(restaurantOrderModels).build();

	}

	@POST
	@Path("user/{userid}/restaurant/{rid}")
	public Response addRestaurantOrder(@PathParam("rid") String restaurantId,@PathParam("userid") String userId,RestaurantOrderModel restaurantOrder) {
		boolean flag = false;
		try {
			flag = restaurantOrderService.CreateRestaurantOrder(Integer.parseInt(restaurantId),Integer.parseInt(userId),restaurantOrder);
		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}
		
		if(flag){
			return Response.ok().build();
		}else{
			return Response.ok().status(422).entity(new ResponseError("restaurant order Creation Failed")).build();

		}

	}

}

