package com.ecom.service.faq;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.ecom.client.NotificationServiceClient;
import com.ecom.dto.notification.EmailRequest;
 
@Service
public class FaqService {
	@Autowired
	private NotificationServiceClient notificationServiceClient;
	public String enquiryMail(String email, String body) {
		CompletableFuture.runAsync(() -> sendFaq(email, body));
		return "E-mail sent successfully";
	}
 
	private void sendFaq(String email, String body) {

 
	    String adminEmail = "ashtapalak@gmail.com";
 
		EmailRequest adminEmailRequest = new EmailRequest(adminEmail,"Query - " +body+ "\n" + " From " +email , "Query from Customer!");
		notificationServiceClient.sendEmail(adminEmailRequest);
	}
 
}
