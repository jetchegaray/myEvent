package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import org.junit.Before;
import org.junit.Test;


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
		assertEquals("evento",words.get(0));
		assertEquals("eventos",words.get(1));
		assertEquals("fotografo",words.get(2));
		assertEquals(9,words.size());
			
	}
	
}
