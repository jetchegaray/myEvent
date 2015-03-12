package com.je.enterprise.mievento.domain.service;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.apache.commons.lang3.RandomStringUtils;
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
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.api.dto.location.ProvinceCode;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.entity.location.StreetAddressEntity;
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
		mailService.sendPassword(new UserEntity("javimetal2014","etchegarayjavier@live.com.ar", RandomStringUtils.randomAlphanumeric(12), true));
	}
	
	@Test
	public void sendInvitation_testOk(){
		EventEntity eventEntity = new EventEntity("EventoTest",DateTime.now().toDate(),DateTime.now().toDate(),new CommercialLocationEntity("placeTest", CountryCode.AR, ProvinceCode.CF, "CABA", new StreetAddressEntity("streetTest", BigDecimal.TEN, null, "Villa lugano")),null,null,null,EventType.COMMON_EVENT);
		mailService.sendInvitation(eventEntity, "aa@gmail.com", "etchegarayjavier@gmail.com");
	}
	
	@Ignore
	@Test
	public void getTemplateWirte_ok() throws IOException{
		InputStream inputStrem = ClassLoaderUtils.getClassLoader(ClassLoaderResourceResolver.class).getResourceAsStream("com/je/enterprise/mievento/domain/templates/mail.html");
		
		assertNotNull(inputStrem);
	
	}
}
