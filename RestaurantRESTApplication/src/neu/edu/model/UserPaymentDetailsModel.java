package neu.edu.model;

import neu.edu.entity.User;

public class UserPaymentDetailsModel {
	
	private Integer userPaymentDetailsId;
	private User user;
	private String cardNumber;
	private String cvv;
	private String expireDate;
	private String cardType;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getUserPaymentDetailsId() {
		return userPaymentDetailsId;
	}
	public void setUserPaymentDetailsId(Integer userPaymentDetailsId) {
		this.userPaymentDetailsId = userPaymentDetailsId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	

}
