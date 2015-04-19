package com.je.enterprise.mievento.domain.external.apiPlaces.transformer.type;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.je.enterprise.mievento.domain.utils.SearchKeywordsHelper;
import com.je.enterprise.mievento.domain.utils.UtilsCollections;

public enum ConditionRuleProviderKeyWord {

	AND_CAT(Pair.of("keyword.catering","keyword.event")),
	AND_CAT_2(Pair.of("keyword.catering","keyword.wedding")),
	AND_CAT_3(Pair.of("keyword.catering","keyword.bithday")),
	AND_CAT_4(Pair.of("keyword.catering","keyword.party")),
	AND_CAT_5(Pair.of("keyword.catering","keyword.marriage")),
	AND_WEDD_HALL(Pair.of("keyword.hall","keyword.marriage")),
	AND_WEDD_HALL_2(Pair.of("keyword.hall","keyword.wedding")),
	AND_BIRTH_HALL(Pair.of("keyword.hall","keyword.bithday")),
	AND_BIRTH_HALL_2(Pair.of("keyword.hall","keyword.child")),
	AND_PARTY_HALL(Pair.of("keyword.hall","keyword.party")),
	AND_EVENT_HALL(Pair.of("keyword.hall","keyword.event")),
	AND_BAT_HALL(Pair.of("keyword.hall","keyword.mitzva")),
	AND_BAT_HALL_2(Pair.of("keyword.hall","mitzvá")),
	AND_BAT_HALL_3(Pair.of("keyword.bat","keyword.mitzva")),
	AND_BAT_HALL_4(Pair.of("keyword.bar","keyword.mitzva")),
	AND_SUIT(Pair.of("keyword.suit","keyword.groom")),
	AND_SUIT_2(Pair.of("keyword.suit","keyword.wedding")),
	AND_SUIT_3(Pair.of("keyword.suit","keyword.marriage")),
	OR_SUIT_4(Pair.of("keyword.suit","keyword.dress")),
	AND_DRESS(Pair.of("keyword.dress","keyword.bride")),
	AND_DRESS_2(Pair.of("keyword.dress","keyword.wedding")),
	AND_DRESS_3(Pair.of("keyword.dress","keyword.marriage")),
	AND_ALIZ(Pair.of("keyword.alliance","keyword.wedding")),
	AND_ALIZ_2(Pair.of("keyword.alliance","keyword.groom")),
	AND_ALIZ_3(Pair.of("keyword.alliance","keyword.marriage")),
	AND_ALIZ_4(Pair.of("keyword.alliances","")),
	AND_RINGS(Pair.of("keyword.ring","keyword.wedding")),
	AND_RINGS_2(Pair.of("keyword.ring","keyword.groom")),
	AND_RINGS_3(Pair.of("keyword.ring","keyword.marriage")),
	AND_CAKE(Pair.of("keyword.cake","keyword.wedding")),
	AND_CAKE_2(Pair.of("keyword.cake","keyword.bithday")),
	AND_CAKE_3(Pair.of("keyword.cake","keyword.party")),
	AND_CAKE_4(Pair.of("keyword.cake","keyword.marriage")),
	AND_DECORATION(Pair.of("keyword.decoration","keyword.event")),
	AND_DECORATION_2(Pair.of("keyword.decoration","keyword.party")),
	AND_DECORATION_3(Pair.of("keyword.decoration","keyword.wedding")),
	AND_BARMAN(Pair.of("keyword.barman","keyword.event")),
	AND_BARMAN_2(Pair.of("keyword.barman","keyword.party")),
	AND_BARMAN_3(Pair.of("keyword.barman","keyword.wedding")),
	AND_BARMAN_4(Pair.of("keyword.barman","keyword.marriage")),
	AND_BARMAN_5(Pair.of("keyword.barman","")),
	AND_DJ(Pair.of("keyword.disc","keyword.jockey")),
	AND_VID(Pair.of("keyword.video","keyword.party")),
	AND_VID_2(Pair.of("keyword.video","keyword.event")),
	AND_VID_3(Pair.of("keyword.video","keyword.bithday")),
	AND_VID_4(Pair.of("keyword.video","keyword.wedding")),
	AND_VID_5(Pair.of("keyword.video","keyword.marriage")),
	
	AND_FOT(Pair.of("keyword.photographer",""));
	
	private static SearchKeywordsHelper searchKeywordsHelper;
	
	static {
		searchKeywordsHelper = new SearchKeywordsHelper();
		searchKeywordsHelper.load();
	}
	
	
	private Pair<String, String> arguments;
	
	private ConditionRuleProviderKeyWord(Pair<String, String> arguments) {
		this.arguments = arguments;
	}
	
	public Pair<String, String> getArguments(){
		List<String> filtering = ConditionRuleProviderKeyWord.searchKeywordsHelper.filtering(Lists.newArrayList(this.arguments.getLeft(),this.arguments.getRight()));
		return Pair.of(filtering.get(0), filtering.get(1)); 
	}

	public boolean isAND() {
		return this.name().contains("AND");
	}
	
	public boolean isOR() {
		return this.name().contains("OR");
	}
	
	
	public static Set<String> getKeyWords(){
		Set<String> keyWords = Sets.newLinkedHashSet();
		
		for (ConditionRuleProviderKeyWord rule : ConditionRuleProviderKeyWord.values()) {
			if (!StringUtils.isEmpty(rule.arguments.getLeft())){
				keyWords.add(rule.arguments.getLeft());
			}
			if (!StringUtils.isEmpty(rule.arguments.getRight())){
				keyWords.add(rule.arguments.getRight());
			}
		}
		
		return  ConditionRuleProviderKeyWord.searchKeywordsHelper.filtering(Sets.newLinkedHashSet(UtilsCollections.shuffle(keyWords)));
	}
	

	
	public static Set<String> getKeyWordsWithOut(String providerTypeName){
		
		Set<String> keywords = ConditionRuleProviderKeyWord.getKeyWords();
		Set<String> keywordsWitout = Sets.newLinkedHashSet(); 
		
		for (String keyword : keywords) {
			if (!StringUtils.containsIgnoreCase(providerTypeName,keyword)){
				keywordsWitout.add(keyword);
			}
		}
		return ConditionRuleProviderKeyWord.searchKeywordsHelper.filtering(keywordsWitout);
	}
	
	
}
