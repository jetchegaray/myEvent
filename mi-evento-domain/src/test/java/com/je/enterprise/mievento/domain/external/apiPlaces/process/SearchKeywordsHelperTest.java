package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.context.ContextLocale;
import com.je.enterprise.mievento.domain.external.apiPlaces.transformer.type.BuilderConditionRulesProvider;
import com.je.enterprise.mievento.domain.external.apiPlaces.transformer.type.ConditionRuleProviderKeyWord;
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
		
		 Map<ProviderType,List<ConditionRuleProviderKeyWord>> rulesByType = BuilderConditionRulesProvider.getRules();
		 
		 for (Entry<ProviderType, List<ConditionRuleProviderKeyWord>> entry : rulesByType.entrySet()) {
			 List<ConditionRuleProviderKeyWord> rules = entry.getValue();
			 
			 for (ConditionRuleProviderKeyWord conditionRuleProviderKeyWord : rules) {
				 Pair<String, String> args = conditionRuleProviderKeyWord.getArguments();
				 Assert.assertNotEquals(args.getLeft(), StringUtils.EMPTY);
				 Assert.assertNotEquals(args.getRight(), StringUtils.EMPTY);
			}
		 }
	}
	
}
