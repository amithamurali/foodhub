package neu.edu.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.entity.Review;
import neu.edu.model.MenuItemsModel;
import neu.edu.model.ReviewModel;
import neu.edu.service.MenuItemsService;
import neu.edu.service.ReviewService;

@Path("/menuitems")
@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MenuItemsController {
	
	@Autowired
	private MenuItemsService menuItemsService;

	
	@GET
	@Path("/restaurant/{rid}")
	//@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
	public Response GetMenuItemsForSpecificRestaurant(@PathParam("rid") String restaurantId) {

		List<MenuItemsModel> menuItemsModels = null;
		try {
			menuItemsModels = menuItemsService.GetAllMenuItemsForSpecificRestaurant(Integer.parseInt(restaurantId));	

		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}

		if (menuItemsModels == null) {
			return Response.ok().status(422).entity(new ResponseError("Menu Items Not Found")).build();

		}
		return Response.ok().entity(menuItemsModels).build();

	}

	@POST
	@Path("/restaurant/{rid}")
	public Response addMenuItem(@PathParam("rid") String restaurantId,MenuItemsModel menuItem) {
		boolean flag = false;
		try {
			flag = menuItemsService.CreateMenuItem(Integer.parseInt(restaurantId),menuItem);
		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}
		
		if(flag){
			return Response.ok().build();
		}else{
			return Response.ok().status(422).entity(new ResponseError("menuItem Creation Failed")).build();

		}

	}
	
	@DELETE
	@Path("/restaurant/{rid}/delete/{menuItemId}")
	public Response deleteMenuItem(@PathParam("rid") String restaurantId,@PathParam("menuItemId") String menuItemId,MenuItemsModel menuItem) {
		boolean flag = false;
		try {
			flag = menuItemsService.DeleteMenuItem(Integer.parseInt(restaurantId),Integer.parseInt(menuItemId),menuItem);
		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}
		
		if(flag){
			return Response.ok().build();
		}else{
			return Response.ok().status(422).entity(new ResponseError("menuItem Creation Failed")).build();

		}

	}

}