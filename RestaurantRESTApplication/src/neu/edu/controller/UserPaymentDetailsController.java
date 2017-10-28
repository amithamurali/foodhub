package neu.edu.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
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

import neu.edu.entity.Review;
import neu.edu.model.RestaurantModel;
import neu.edu.model.ReviewModel;
import neu.edu.model.UserPaymentDetailsModel;
import neu.edu.service.RestaurantService;
import neu.edu.service.UserPaymentDetailsService;

@Path("/userpayment")
@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserPaymentDetailsController {
	
	@Autowired
	private UserPaymentDetailsService userPaymentDetailsService;

	
	@GET
	@Path("/user/{userid}")
	//@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
	public Response getUserPaymentDetailsForSpecificUser(@PathParam("userid") String userId) {

		List<UserPaymentDetailsModel> userPaymentDetails = null;
		try {
			userPaymentDetails = userPaymentDetailsService.GetUserPaymentDetailsForSpecificUser(Integer.parseInt(userId));	

		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}

		if (userPaymentDetails == null) {
			return Response.ok().status(422).entity(new ResponseError("Review Not Found")).build();

		}
		return Response.ok().entity(userPaymentDetails).build();

	}


	
	@POST
	@Path("/user/{userid}")
	@PermitAll
	public Response addUserPaymentDetail(@PathParam("userid") String userId,UserPaymentDetailsModel userPaymentDetails) {
		boolean flag = false;
		try {
			flag = userPaymentDetailsService.CreateUserPaymentDetails(userPaymentDetails,Integer.parseInt(userId));
		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}
		
		if(flag){
			return Response.ok().build();
		}else{
			return Response.ok().status(422).entity(new ResponseError("user payment Creation Failed")).build();

		}

	}

	
}
