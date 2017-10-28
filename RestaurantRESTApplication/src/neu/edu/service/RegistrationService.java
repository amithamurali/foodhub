package neu.edu.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.model.RegistrationRequest;
import neu.edu.model.RestLogicalErrorException;
import neu.edu.dao.UserDAO;
import neu.edu.entity.User;

@Service
public class RegistrationService {
	
	@Autowired
	private UserDAO userDao;
	
	
	public boolean register(RegistrationRequest registrationRequest) throws RestLogicalErrorException{
		
		if(registrationRequest.getUsername() ==null ||
		   registrationRequest.getPassword() ==null){
			
			throw new RestLogicalErrorException("Registration Parameters incomplete.");
		}else{
			
			User user = new User();
			user.setFullName(registrationRequest.getFirstName() + "" + registrationRequest.getLastName());
			user.setUserName(registrationRequest.getUsername());
			user.setPassword(registrationRequest.getPassword());
		  //user.setAge(registrationRequest.getDateOfBirth());
			user.setGender(registrationRequest.getGender());
			user.setPhoneNumber(registrationRequest.getPhoneNumber());
			user.setEmail(registrationRequest.getEmail());
			user.setAddress(registrationRequest.getAddress());
			user.setUserType(registrationRequest.getUserType());
			
			//Simulation a database Request
			
		
			User userDB = userDao.isUserNameInUse(registrationRequest.getUsername());
			if(userDB == null)
			{
				if(!userDao.CreateNewUser(user)){
					throw new RestLogicalErrorException("Duplicate User.");
				}
			}
			else
			{
				throw new RestLogicalErrorException("Duplicate User.");
			}

			
		}
		
		
		return true;
		
	}

}
