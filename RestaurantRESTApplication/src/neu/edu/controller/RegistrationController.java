package neu.edu.controller;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.model.RegistrationRequest;
import neu.edu.model.RestLogicalErrorException;
import neu.edu.service.RegistrationService;
import neu.edu.util.Mail;

@Path("/registration")
@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@POST
	@PermitAll
	public Response registerUser(RegistrationRequest registrationRequest) {

		try {
			if (registrationService.register(registrationRequest)) {
				Mail mail = new Mail();
				mail.Sendmail();
				return Response.ok().build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();

	}

}
