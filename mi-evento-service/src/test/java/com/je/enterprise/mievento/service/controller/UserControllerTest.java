package com.je.enterprise.mievento.service.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.beust.jcommander.internal.Lists;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.wedding.Wedding;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.api.dto.user.User;
import com.je.enterprise.mievento.service.controller.UserController;
import com.je.enterprise.mievento.service.request.ProviderTypesRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class UserControllerTest {
 
    private MockMvc mockMvc;
 
    @Mock
    private UserController userServiceMock;
 
    @Autowired
    private WebApplicationContext webApplicationContext;
 
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(userServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void update_ok() throws Exception {
 
        User user = new User();
		user.setEvents(Arrays.asList(new Event(),new Wedding()));
		
		MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter)webApplicationContext.getBean("jsonMessageConverter");
		ObjectMapper mapper = converter.getObjectMapper();
		String json = mapper.writeValueAsString(user);
		
		System.out.println(json);
		 
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/mievento/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isOk())
        .andReturn();
		
		 assertEquals(result.getResponse().getContentAsString(), "bar"); 
    }
    
    @Test
    public void test_ok() throws JsonProcessingException  {
 
    	
		MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter)webApplicationContext.getBean("jsonMessageConverter");
		ObjectMapper mapper = converter.getObjectMapper();
		
		ProviderTypesRequest request = new ProviderTypesRequest(Arrays.asList(ProviderType.PHOTOGRAPHER));
		
		String json = mapper.writeValueAsString(request);
		
		System.out.println(json);
	}
}
