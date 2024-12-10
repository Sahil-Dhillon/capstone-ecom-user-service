package com.ecom.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.dto.notification.EmailRequest;

@FeignClient(name = "notification", url = "http://localhost:8080/sendMail")
public interface NotificationServiceClient {
    @PostMapping
    String sendEmail(@RequestBody EmailRequest emailRequest);
}