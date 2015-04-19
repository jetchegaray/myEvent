package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;
import com.je.enterprise.mievento.domain.context.ContextLocale;
import com.je.enterprise.mievento.domain.utils.SearchKeywordsHelper;

public class SearchKeywordsHelperTest {

	
	private SearchKeywordsHelper searchKeywordsHelper; 
	
	@Before
	public void setUp(){
		this.searchKeywordsHelper = new SearchKeywordsHelper();
	}
	
	@Test
	public void getKeywords_ok() throws IOException{
		ContextLocale.getContextLocale().setLocale(new Locale("es"));
		searchKeywordsHelper.load();

		Set<String> filtering = searchKeywordsHelper.filtering(createListToFilter());
		Iterator<String> iterator = filtering.iterator();
		
		Assert.assertEquals("", iterator.next());
		Assert.assertEquals("", iterator.next());
		Assert.assertEquals("", iterator.next());
		Assert.assertEquals("", iterator.next());
		Assert.assertEquals("", iterator.next());
	}
	
	private Set<String> createListToFilter(){
		return Sets.newHashSet("keyword.catering","keyword.hall","keyword.marriage","keyword.wedding","keyword.bithday","keyword.child");
	}
 	
}
