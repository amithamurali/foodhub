package neu.edu.util;
import java.util.Properties; 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class Mail {
	
	private static String host = "smtp.gmail.com";
    private static String portNumber = "465";
    private static String userName = "amitha.murali@gmail.com";
    private static String password = "murali_88amitha";
    Session session = null;
    private int oneTimePassword = 0;
    
    public int getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(int oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    public Mail()
    {
        Properties props = new Properties();
        //sample connection from Google
        props.setProperty("mail.smtp.host", host);       
        props.setProperty("mail.smtp.auth", "true");       
        props.setProperty("mail.smtps.host", host);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", portNumber);
        props.setProperty("mail.smtp.socketFactory.port", portNumber);
        props.setProperty("mail.smtps.auth", "true");

 
        session = Session.getDefaultInstance(props,
                         new javax.mail.Authenticator() {
                         protected PasswordAuthentication getPasswordAuthentication() {
                             return new PasswordAuthentication(userName,
                                               password);
                         }
                         });   
        
        
        
    }

    public int Sendmail()
    {
        try {
            if(null != session)
            {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userName));
            message.setSubject("welcome to Food Hub");
            // oneTimePassword = Utilities.RandomInteger();
            //oneTimePassword = 334455;
            message.setText("Hi, " +
 "Thank you for registering with us.We are delighted to have you with us."+
"Here is a small introduction to FoodHub:"+
"Foodhub helps you find and order food from wherever you are. "
+ "How it works: you type in an address, we tell you the restaurants that deliver to that locale as well as showing you droves of pickup restaurants near you."+
"Regards,"+
"FoodHubManager");

            Transport.send(message);
            
            }
            // JOptionPane.showMessageDialog(null, "OTP has been sent to your registered email Id", "Info", JOptionPane.INFORMATION_MESSAGE);

        } catch (MessagingException e) {
            //  System.out.println("unable to send email: " + e);
            JOptionPane.showMessageDialog(null, "Error sending email", "Info", JOptionPane.INFORMATION_MESSAGE);

        }
        return oneTimePassword;
    }
   

}
