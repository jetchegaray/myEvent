package com.je.enterprise.mievento.domain.service;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
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

import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
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
	
	@Test
	public void sendMail_testOk(){
		mailService.send(new UserEntity("etchegarayjavier@live.com.ar", RandomStringUtils.randomAlphanumeric(12), true));
	}
	
	@Ignore
	@Test
	public void getTemplateWirte_ok() throws IOException{
		InputStream inputStrem = ClassLoaderUtils.getClassLoader(ClassLoaderResourceResolver.class).getResourceAsStream("com/je/enterprise/mievento/domain/templates/mail.html");
		
		assertNotNull(inputStrem);
	
	}
}
