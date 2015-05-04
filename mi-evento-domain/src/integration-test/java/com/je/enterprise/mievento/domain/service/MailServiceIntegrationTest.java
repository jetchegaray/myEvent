package com.je.enterprise.mievento.domain.service;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.util.ClassLoaderUtils;

import com.je.enterprise.mievento.api.dto.event.EventType;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.service.impl.MailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/je/enterprise/mievento/domain/email-context.xml"})
public class MailServiceIntegrationTest {

	
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Autowired
	private TemplateEngine templateEngine;

	private MailService mailService;
	
	@Before
	public void setUp(){
		mailService = new MailService(mailSender, templateEngine);
	}
	
//	@Test
//	public void sendMail_testOk(){
//		mailService.sendPassword(new UserEntity("javimetal2014","etchegarayjavier@live.com.ar", RandomStringUtils.randomAlphanumeric(12), true));
//	}
	
	@Test
	public void sendInvitation_testOk(){
		EventEntity eventEntity = new EventEntity("EventoTest",DateTime.now().toDate(),DateTime.now().plusHours(5).toDate(),null,null,null,EventType.COMMON_EVENT);
		mailService.sendInvitation(eventEntity, "aa@gmail.com", "etchegarayjavier@gmail.com",UUID.randomUUID().toString());
	}
	
	@Ignore
	@Test
	public void getTemplateWirte_ok() throws IOException{
		InputStream inputStrem = ClassLoaderUtils.getClassLoader(ClassLoaderResourceResolver.class).getResourceAsStream("com/je/enterprise/mievento/domain/templates/mail.html");
		
		assertNotNull(inputStrem);
	
	}
}
