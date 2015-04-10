package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.external.apiPlaces.transformer.ProviderTypeKeyword;

@Ignore
public class ProviderTypeKeywordTest {

	
	private ProviderTypeKeyword providerTypeKeyword; 
	
	@Before
	public void setUp(){
		this.providerTypeKeyword = new ProviderTypeKeyword();
		providerTypeKeyword.load();
	}
	
	@Test
	public void getKeywords_ok(){
		
		Map<ProviderType, List<String>> providerTypes = this.providerTypeKeyword.getProviderTypeKeyword();
		
		assertFalse(providerTypes.isEmpty());
		assertEquals(Lists.newArrayList("salon","cumpleaños","fiestas"),providerTypes.get(ProviderType.BIRTHDAY_HALL));
		assertEquals(Lists.newArrayList("tortas","torta","pastel","pasteles"),providerTypes.get(ProviderType.CAKES));
		assertEquals(Lists.newArrayList("catering"),providerTypes.get(ProviderType.CATERING));
		assertEquals(11,providerTypes.size());
			
	}
	
}
