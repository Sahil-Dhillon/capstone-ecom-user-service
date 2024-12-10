package com.ecom.dto.notification;

import lombok.Data;

@Data
public class EmailRequest {
	private String recipient;
	private String msgBody;
	private String subject;
	private String attachment;
	public EmailRequest(String recipient, String msgBody, String subject) {
		super();
		this.recipient = recipient;
		this.msgBody = msgBody;
		this.subject = subject;
	}
	public EmailRequest(String recipient, String msgBody, String subject, String attachment) {
		super();
		this.recipient = recipient;
		this.msgBody = msgBody;
		this.subject = subject;
		this.attachment = attachment;
	}
    

}
