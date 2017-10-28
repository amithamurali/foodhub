package neu.edu.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
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
import neu.edu.model.RestaurantModel;
import neu.edu.model.ReviewModel;
import neu.edu.service.RestaurantService;
import neu.edu.service.ReviewService;


@Path("/review")
@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;

	
	@GET
	@Path("/restaurant/{rid}")
	//@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
	public Response getReviewForSpecificRestaurant(@PathParam("rid") String restaurantId) {

		List<ReviewModel> reviewModels = null;
		try {
			reviewModels = reviewService.GetAllReviewForSpecificRestaurant(Integer.parseInt(restaurantId));	

		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}

		if (reviewModels == null) {
			return Response.ok().status(422).entity(new ResponseError("Review Not Found")).build();

		}
		return Response.ok().entity(reviewModels).build();

	}

	@POST
	@Path("/user/{userid}/restaurant/{rid}")
	public Response addReview(@PathParam("rid") String restaurantId,@PathParam("userid") String userId,Review review) {
		boolean flag = false;
		try {
			flag = reviewService.CreateReview(Integer.parseInt(restaurantId),Integer.parseInt(userId),review);
		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}
		
		if(flag){
			return Response.ok().build();
		}else{
			return Response.ok().status(422).entity(new ResponseError("Review Creation Failed")).build();

		}

	}

	@DELETE
	@Path("/user/{userid}/restaurant/{rid}/delete/review/{reviewid}")
	public Response DeleteReview(@PathParam("rid") String restaurantId,@PathParam("userid") String userId,@PathParam("reviewid") String reviewId, Review review) {
		boolean flag = false;
		try {
			flag = reviewService.DeleteReview(Integer.parseInt(restaurantId),Integer.parseInt(userId),Integer.parseInt(reviewId),review);
		} catch (NumberFormatException ex) {
			return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
		}
		
		if(flag){
			return Response.ok().build();
		}else{
			return Response.ok().status(422).entity(new ResponseError("Review Creation Failed")).build();

		}

	}
}
