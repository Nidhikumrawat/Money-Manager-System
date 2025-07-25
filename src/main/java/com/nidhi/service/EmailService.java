package com.nidhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor

public class EmailService {

	@Autowired
	private final JavaMailSender mailSender;
	
	@Value("${spring.mail.properties.mail.smtp.from}")
	private String fromEmail;
	
	public void sendEmail(String to,String subject,String body) {
		
		 if (to == null || subject == null || body == null || fromEmail == null) {
		        throw new RuntimeException("Email parameters must not be null.");
		    }
		
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(fromEmail);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(body);
			mailSender.send(message);
			
			
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
}
