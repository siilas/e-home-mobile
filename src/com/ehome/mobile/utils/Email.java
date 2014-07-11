package com.ehome.mobile.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

/**
 * Classe que responsável pelo envio de e-mails
 * 
 * @author Silas M. Ferreira
 *
 */
public class Email {

	private static final Logger logger = Logger.getLogger(Email.class);
	
	private Session session;
	private String to;
	private String subject;
	private String message;
	
	public Email() {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", Constants.EMAIL_SERVER);
			props.put("mail.smtp.port", Constants.EMAIL_PORT);
			
			session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(Constants.EMAIL_USER, Constants.EMAIL_PASSWORD);
					}
			  	});
		} catch (Exception e) {
			logger.error("Email()", e);
		}
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void send() {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Constants.EMAIL_USER));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.to));
			message.setSubject(this.subject);
			message.setContent(this.message, "text/html");
 
			Transport.send(message);
		} catch (Exception e) {
			logger.error("send()", e);
		}
	}
}
