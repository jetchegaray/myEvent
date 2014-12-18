package com.je.enterprise.mievento.domain;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
 
@ContextConfiguration(locations = {"classpath:com/je/enterprise/mievento/domain/domain-context.xml"})
public class LoadContextTest extends AbstractJUnit4SpringContextTests{

	@Test
	public void load(){
		//nothing to do
	}
	
	
	
}
