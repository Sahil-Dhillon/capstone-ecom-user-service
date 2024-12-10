package com.ecom.controller.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.client.NotificationServiceClient;
import com.ecom.dto.notification.EmailRequest;

@RestController
@RequestMapping("/notify")
public class NotificationController {
 
	@Autowired
    private  NotificationServiceClient notificationServiceClient;
 
   
 
    // Endpoint to send email using Feign client
    @PostMapping("/verify-email")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        return notificationServiceClient.sendEmail(emailRequest);
    }
}