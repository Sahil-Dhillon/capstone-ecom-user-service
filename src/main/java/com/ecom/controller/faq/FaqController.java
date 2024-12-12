package com.ecom.controller.faq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.ecom.model.user.UserDetails;
import com.ecom.service.user.UserService;
 
@RestController
@RequestMapping("/faq")
public class FaqController {
	@Autowired
	private com.ecom.service.faq.FaqService FaqService;
    @PostMapping("/enquiry")
    public ResponseEntity<String> addItemToCart(@RequestBody String enquiry) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        String email=currentUser.getUsername();
        return ResponseEntity.ok(FaqService.enquiryMail(email,enquiry));
    }
 
}
