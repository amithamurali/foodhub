package neu.edu.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.model.RestLogicalErrorException;
import neu.edu.model.UserSession;
import neu.edu.dao.UserDAO;
import neu.edu.entity.User;

@Service
public class AuthenticationService {

	@Autowired
	private UserDAO userDao;

	public UserSession validateUser(String username, String password) throws RestLogicalErrorException {

		User user = userDao.validateUser(username, password);

		UserSession userSession = null;

		if (user != null) {

			userSession = new UserSession();
			userSession.setId(String.valueOf(user.getUserId()));
			userSession.setName(user.getFullName());
			userSession.setRole(user.getUserType());
			userSession.setSuccess(true);

		} else {
			RestLogicalErrorException authResponseErr = new RestLogicalErrorException("Invalid User");
			throw authResponseErr;
		}

		return userSession;
	}

	/**
	 * sessionId = MD5(userId + ipAddr) + prngRandomNumber
	 * 
	 * @param userSession
	 * @return
	 */
	public String generateAuthenticationToken(UserSession userSession, String ipAddress) {
		String token = null;
		
		try {
			String prngRandomNumber = null;
			//Algo for generating Random Uniquie Number - Using Cryptograpy 
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
			
			//Algo for generating hash Generating - One way function
			MessageDigest sha = MessageDigest.getInstance("SHA-1");

			//Generation A prngRandomNumber
			String randomNum = new Integer(prng.nextInt()).toString();

			byte[] result = sha.digest(randomNum.getBytes());
			prngRandomNumber = convertByteArrayToHexString(result);
			System.out.println("Random number: " + randomNum);
			System.out.println("Message digest SHA-1: " + prngRandomNumber);
			
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			String toBeHashed = userSession.getId()+ipAddress+userSession.getName();
			byte[] md5HashByte = md5.digest(toBeHashed.getBytes());
			
			String md5hash = convertByteArrayToHexString(md5HashByte);
			
			System.out.println("Message digest MD5: " + prngRandomNumber);
			token = md5hash+prngRandomNumber;
			

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("SHA1PRNG,SHA-1 algo Doesnt exists");
		}

		if (token == null) {
			token = UUID.randomUUID().toString();
		}
		System.out.println("Token: " + token);

		return token;
	}

	private  String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}
}
