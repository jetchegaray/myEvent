package com.je.enterprise.mievento.domain.service.impl;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;

@Service
public class MailService {

	private JavaMailSenderImpl mailSender;
	private TemplateEngine templateEngine;
	
	@Autowired
	public MailService(JavaMailSenderImpl mailSender,TemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}


	public void send(UserEntity userEntity) throws MessagingException {
		
		final Context ctx = new Context(Locale.ROOT);
		ctx.setVariable("newPassword", userEntity.getPassword());
		ctx.setVariable("imageResourceName", "");
		  
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
		message.setSubject("Mi-Evento . New PassWord");
		message.setFrom("etchegarayjavier@gmail.com");
		message.setTo(userEntity.getEmail());
		 
		final String htmlContent = this.templateEngine.process("mail.html", ctx);
		message.setText(htmlContent, true); // true = isHtml
		 
		this.mailSender.send(mimeMessage);
	}

	
}
