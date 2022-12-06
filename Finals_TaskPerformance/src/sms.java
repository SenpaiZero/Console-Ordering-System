import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class sms {
	
	//Have 35sms limit per day
	//and 100 sms limit per month
	public void sendReciept(String message_, String phoneNum) {
        Twilio.init("AC47fe22bc76be04706e37fb9b787c6a17", "5b628576c2e44231e76c5f56fac4234a");
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(phoneNum),
                new com.twilio.type.PhoneNumber("+19257225237"),
                message_)
            .create();

        System.out.println(message.getSid());
	}
}
