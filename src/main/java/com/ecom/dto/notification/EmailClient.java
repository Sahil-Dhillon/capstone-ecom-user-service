package com.ecom.dto.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.client.NotificationServiceClient;

@Service
public class EmailClient {
    private final NotificationServiceClient notificationServiceClient;

    @Autowired
    public EmailClient(NotificationServiceClient notificationServiceClient) {
        this.notificationServiceClient = notificationServiceClient;
    }

    public String sendEmail(EmailRequest emailRequest) {
        return notificationServiceClient.sendEmail(emailRequest);
    }
}


