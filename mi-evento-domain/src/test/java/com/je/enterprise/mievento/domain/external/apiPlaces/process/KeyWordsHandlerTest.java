package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class KeyWordsHandlerTest {

	
	private KeyWordsHandler keyWordsHandler; 
	
	@Before
	public void setUp(){
		this.keyWordsHandler = new KeyWordsHandler();
		keyWordsHandler.load();
	}
	
	@Test
	public void getKeywords_ok(){
		
		List<String> words = this.keyWordsHandler.getKeywords();
		
		assertFalse(words.isEmpty());
		assertEquals("barman",words.get(0));
		assertEquals("evento",words.get(1));
		assertEquals("eventos",words.get(2));
		assertEquals(10,words.size());
			
	}
	
}
