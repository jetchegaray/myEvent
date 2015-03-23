package com.je.enterprise.mievento.domain.service.impl;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.je.enterprise.mievento.api.dto.event.EventType;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.entity.place.PlaceEntity;
import com.je.enterprise.mievento.domain.entity.wedding.EventWithPlaceAndPresentEntity;
import com.je.enterprise.mievento.domain.exception.customize.FailedSendInvitationException;

@Service
public class MailService {

	private JavaMailSenderImpl mailSender;
	private TemplateEngine templateEngine;
	private static final Logger logger = Logger.getLogger(MailService.class);
	
	@Autowired
	public MailService(JavaMailSenderImpl mailSender,TemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}


	public void sendPassword(UserEntity userEntity) {
		try {
			final Context ctx = new Context(Locale.ROOT);
			ctx.setVariable("newPassword", userEntity.getPassword());
	//		ctx.setVariable("imageResourceName", "");
			  
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true, "UTF-8"); // true = multipart
			message.setSubject("Mi-Evento . New Password");
			message.setFrom("etchegarayjavier@gmail.com");
			message.setTo(userEntity.getEmail());
			 
			final String htmlContent = this.templateEngine.process("forgottPasswordTemplate.html", ctx);
			message.setText(htmlContent,true); // true = isHtml
			 
			this.mailSender.send(mimeMessage);
		} catch (MessagingException ex) {
			logger.error("The mail Fail !!." ,ex);
		}
	}


	public void sendInvitation(EventEntity eventEntity, String userEmail,String guestEmail)  {
		try {
			String dayPattern = "dd-MM-yyyy";
			String hourPattern = "HH:mm";
			DateTime initialDate = new DateTime(eventEntity.getInitialDate());
			DateTime finalDate = new DateTime(eventEntity.getFinalDate());
			
			final Context ctx = new Context(Locale.ROOT);
			ctx.setVariable("eventName", eventEntity.getName());
			ctx.setVariable("initialDate", initialDate.toString(dayPattern));
			ctx.setVariable("initialHour", initialDate.toString(hourPattern));
			ctx.setVariable("duration", finalDate.minus(initialDate.getMillis()).toString(hourPattern));
			if (EventType.withPosiblesPlaces().contains(eventEntity.getType()) && ((EventWithPlaceAndPresentEntity)eventEntity).getPlace() != null){
				PlaceEntity placeEntity = ((EventWithPlaceAndPresentEntity)eventEntity).getPlace();
				ctx.setVariable("placeName", placeEntity.getBusinessName());
				ctx.setVariable("placeAddress", placeEntity.getLocation().toString());
			}else{
				ctx.setVariable("placeName", "No hay lugar confirmado aún");
				ctx.setVariable("placeAddress", "");
			}
			ctx.setVariable("userEmail", userEmail);
			
			
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true, "UTF-8"); // true = multipart
			message.setSubject("Mi-Evento . You've invite to an Event");
			message.setFrom("etchegarayjavier@gmail.com");
			message.setTo(guestEmail);
			 
			final String htmlContent = this.templateEngine.process("invitationTemplate.html", ctx);
			message.setText(htmlContent,true); // true = isHtml
			 
			this.mailSender.send(mimeMessage);
			
		} catch (MessagingException ex) {
			throw new FailedSendInvitationException(guestEmail);
		}
		
	}

	
}
