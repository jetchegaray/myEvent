package com.je.enterprise.mievento.domain.external.apiPlaces.transformer.type;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Sets;
import com.je.enterprise.mievento.domain.utils.UtilsCollections;

public enum ConditionRuleProviderKeyWord {

	AND_CAT(Pair.of("catering","")),
	AND_WEDD_HALL(Pair.of("salon","boda")),
	AND_WEDD_HALL_2(Pair.of("salon","casamiento")),
	AND_BIRTH_HALL(Pair.of("salon","cumpleaños")),
	AND_BIRTH_HALL_2(Pair.of("salon","infantil")),
	AND_PARTY_HALL(Pair.of("salon","fiestas")),
//	AND_PARTY_HALL_2(Pair.of("salon","infantil")),
	AND_BAR_HALL(Pair.of("salon","mitzvah")),
	AND_SUIT(Pair.of("traje","casamiento")),
	AND_DRESS(Pair.of("vestidos","casamiento")),
	AND_ALIZ(Pair.of("alianza","casamiento")),
	AND_RINGS(Pair.of("anillo","casamiento")),
	AND_CAKE(Pair.of("torta","casamiento")),
	AND_DECORATION(Pair.of("decoracion","evento")),
	AND_DECORATION_2(Pair.of("decoracion","fiesta")),
	AND_DECORATION_3(Pair.of("decoracion","casamiento")),
	AND_BARMAN(Pair.of("barman","evento")),
	AND_BARMAN_2(Pair.of("barman","fiesta")),
	AND_DJ(Pair.of("disc","jockey")),
	
	OR_FOT(Pair.of("fotografo","fotografo")),
	OR_VID(Pair.of("video","video"));
	
	
	
	private Pair<String, String> arguments;
	
	private ConditionRuleProviderKeyWord(Pair<String, String> arguments) {
		this.arguments = arguments;
	}
	
	public Pair<String, String> getArguments(){
		return this.arguments; 
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
			keyWords.add(rule.arguments.getLeft());
			keyWords.add(rule.arguments.getRight());
		}
		
		return Sets.newLinkedHashSet(UtilsCollections.shuffle(keyWords));
	}
	

	
	public static Set<String> getKeyWordsWithOut(String providerTypeName){
		
		Set<String> keywords = ConditionRuleProviderKeyWord.getKeyWords();
		Set<String> keywordsWitout = Sets.newLinkedHashSet(); 
		
		for (String keyword : keywords) {
			if (!StringUtils.containsIgnoreCase(providerTypeName,keyword)){
				keywordsWitout.add(keyword);
			}
		}
		return keywordsWitout;
	}
	
	
}
