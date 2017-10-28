package neu.edu.entity;
// Generated Dec 8, 2016 10:03:32 AM by Hibernate Tools 5.1.0.CR1

/**
 * UserPaymentDetails generated by hbm2java
 */
public class UserPaymentDetails implements java.io.Serializable {

	private Integer userPaymentDetailsId;
	private User user;
	private String cardNumber;
	private String cvv;
	private String expireDate;
	private String cardType;

	public UserPaymentDetails() {
	}

	public UserPaymentDetails(String cardNumber, String cvv, String expireDate, String cardType) {
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expireDate = expireDate;
		this.cardType = cardType;
	}

	public UserPaymentDetails(User user, String cardNumber, String cvv, String expireDate, String cardType) {
		this.user = user;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expireDate = expireDate;
		this.cardType = cardType;
	}

	public Integer getUserPaymentDetailsId() {
		return this.userPaymentDetailsId;
	}

	public void setUserPaymentDetailsId(Integer userPaymentDetailsId) {
		this.userPaymentDetailsId = userPaymentDetailsId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv() {
		return this.cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

}