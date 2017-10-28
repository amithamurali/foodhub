package neu.edu.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.model.RestaurantModel;
import neu.edu.service.RestaurantService;
import neu.edu.util.Mail;

@Path("/restaurant")
@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	
	@GET
	@Path("/guestuser")
	@PermitAll
	public Response GetAllResaurant() {

		List<RestaurantModel> restaurantModels = null;
		try {
			restaurantModels = restaurantService.GetAllRestaurant();

		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}

		if (restaurantModels == null) {
			return Response.ok().status(422).entity(new ResponseError("Restaurant Not Found")).build();

		}
		return Response.ok().entity(restaurantModels).build();

	}
	
	@GET
	@Path("/user/{id}")
	//@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
	public Response getRestaurantForSpecificUSer(@PathParam("id") String id) {

		List<RestaurantModel> restaurantModels = null;
		try {
			restaurantModels = restaurantService.GetAllRestaurantForSpecificUser(Integer.parseInt(id));	

		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}

		if (restaurantModels == null) {
			return Response.ok().status(422).entity(new ResponseError("Restaurant Not Found")).build();

		}
		return Response.ok().entity(restaurantModels).build();

	}

	@GET
	@Path("/{restaurantId}")
	//@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
	public Response GetResturantDetailsBasedOnId(@PathParam("restaurantId") String restaurantId) {

		RestaurantModel restaurantModel = null;
		try {
			restaurantModel = restaurantService.GetResturantDetailsBasedOnId(Integer.parseInt(restaurantId));	

		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}

		if (restaurantModel == null) {
			return Response.ok().status(422).entity(new ResponseError("Restaurant Not Found")).build();

		}
		return Response.ok().entity(restaurantModel).build();

	}
	
	
	@PUT
	@Path("/user/{userid}/update/{restaurantid}")
	public Response updateRestaurant(@PathParam("userid") String userid,@PathParam("restaurantid") String restaurantid,RestaurantModel restaurant) {
		boolean flag = false;
		try {
			flag = restaurantService.UpdateRestaurant(restaurant, Integer.parseInt(userid),Integer.parseInt(restaurantid));
		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}
		
		if(flag){
			return Response.ok().build();
		}else{
			return Response.ok().status(422).entity(new ResponseError("Restaurant Creation Failed")).build();

		}

	}
	
	
	@PUT
	@Path("/user/{userid}/disable/{restaurantid}")
	public Response disableRestaurant(@PathParam("userid") String userid,@PathParam("restaurantid") String restaurantid,RestaurantModel restaurant) {
		boolean flag = false;
		try {
			flag = restaurantService.DisableRestaurant(restaurant, Integer.parseInt(userid),Integer.parseInt(restaurantid));
		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}
		
		if(flag){
			return Response.ok().build();
		}else{
			return Response.ok().status(422).entity(new ResponseError("Restaurant disable Failed")).build();

		}

	}
		
	@POST
	@Path("/user/{userid}")
	public Response addRestaurant(@PathParam("userid") String userid,RestaurantModel restaurant) {
		boolean flag = false;
		try {
			flag = restaurantService.CreateRestaurant(restaurant, Integer.parseInt(userid));
		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}
		
		if(flag){
			return Response.ok().build();
		}else{
			return Response.ok().status(422).entity(new ResponseError("Restaurant Creation Failed")).build();

		}

	}

}
