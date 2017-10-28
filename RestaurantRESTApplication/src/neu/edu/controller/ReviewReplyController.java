package neu.edu.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.entity.Review;
import neu.edu.model.ReviewAndReply;
import neu.edu.model.ReviewModel;
import neu.edu.model.ReviewReplyModel;
import neu.edu.service.ReviewReplyService;
import neu.edu.service.ReviewService;

@Path("/reviewreply")
@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewReplyController {
	
		@Autowired
		private ReviewReplyService reviewReplyService;
	
		@GET
		@Path("/review/{rid}")
		//@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
		public Response GetReviewRepliesForSpecificReview(@PathParam("rid") String reviewId) {

			List<ReviewReplyModel> reviewReplyModels = null;
			try {
				reviewReplyModels = reviewReplyService.GetAllReviewReplyForSpecificReview(Integer.parseInt(reviewId));	

			} catch (NumberFormatException ex) {
				return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
			}

			if (reviewReplyModels == null) {
				return Response.ok().status(422).entity(new ResponseError("Review Reply Not Found")).build();

			}
			return Response.ok().entity(reviewReplyModels).build();

		}

		@GET
		@Path("/{rid}")
		//@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
		public Response GetReviewRepliesForSpecificRestaurant(@PathParam("rid") String restaurantId) {

			List<ReviewAndReply> reviewAndReplyList = null;
			try {
				reviewAndReplyList = reviewReplyService.GetReviewRepliesForSpecificRestaurant(Integer.parseInt(restaurantId));	

			} catch (NumberFormatException ex) {
				return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
			}

			if (reviewAndReplyList == null) {
				return Response.ok().status(422).entity(new ResponseError("Review and Reply Not Found")).build();

			}
			return Response.ok().entity(reviewAndReplyList).build();

		}

		
		@POST
		@Path("/user/{userid}/restaurant/{restaurantid}/review/{rid}")
		public Response addReviewReply(@PathParam("restaurantid") String restaurantId,@PathParam("userid") String userId,@PathParam("rid") String reviewId,ReviewReplyModel reviewReply) {
			boolean flag = false;
			try {
				flag = reviewReplyService.CreateReviewReply(Integer.parseInt(restaurantId),Integer.parseInt(userId),Integer.parseInt(reviewId),reviewReply);
			} catch (NumberFormatException ex) {
				return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
			}
			
			if(flag){
				return Response.ok().build();
			}else{
				return Response.ok().status(422).entity(new ResponseError("Review Reply Creation Failed")).build();

			}

		}

		@DELETE
		@Path("/user/{userid}/restaurant/{restaurantid}/review/{rid}/delete/reply/{replyid}")
		public Response DeleteReviewReply(@PathParam("restaurantid") String restaurantId,@PathParam("userid") String userId,@PathParam("rid") String reviewId,@PathParam("replyid") String replyId,ReviewReplyModel reviewReply) {
			boolean flag = false;
			try {
				flag = reviewReplyService.DeleteReviewReply(Integer.parseInt(restaurantId),Integer.parseInt(userId),Integer.parseInt(reviewId),Integer.parseInt(replyId),reviewReply);
			} catch (NumberFormatException ex) {
				return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
			}
			
			if(flag){
				return Response.ok().build();
			}else{
				return Response.ok().status(422).entity(new ResponseError("Review Reply Creation Failed")).build();

			}

		}
		
		@PUT
		@Path("/user/{userid}/restaurant/{restaurantid}/review/{rid}/update/reply/{replyid}")
		public Response UpdateReviewReply(@PathParam("restaurantid") String restaurantId,@PathParam("userid") String userId,@PathParam("rid") String reviewId,@PathParam("replyid") String replyId, ReviewReplyModel reviewReply) {
			boolean flag = false;
			try {
				flag = reviewReplyService.UpdateReviewReply(Integer.parseInt(restaurantId),Integer.parseInt(userId),Integer.parseInt(reviewId),Integer.parseInt(replyId),reviewReply);
			} catch (NumberFormatException ex) {
				return Response.ok().status(422).entity(new ResponseError("Invalid UserId Format")).build();
			}
			
			if(flag){
				return Response.ok().build();
			}else{
				return Response.ok().status(422).entity(new ResponseError("Review Reply Creation Failed")).build();

			}

		}
}
