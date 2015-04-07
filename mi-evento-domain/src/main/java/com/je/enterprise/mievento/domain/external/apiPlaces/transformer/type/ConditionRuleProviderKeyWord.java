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
	AND_BIRTH_HALL(Pair.of("salon","cumpleaño")),
	AND_BIRTH_HALL_2(Pair.of("salon","infantil")),
	AND_PARTY_HALL(Pair.of("salon","fiestas")),
//	AND_PARTY_HALL_2(Pair.of("salon","infantil")),
	AND_BAT_HALL(Pair.of("salon","mitzva")),
	AND_BAT_HALL_2(Pair.of("salon","mitzvá")),
	OR_BAT_HALL_3(Pair.of("bat","mitzva")),
	AND_SUIT(Pair.of("traje","novio")),
	AND_SUIT_2(Pair.of("traje","casamiento")),
	AND_SUIT_3(Pair.of("traje","boda")),
	OR_SUIT_4(Pair.of("traje","vestido")),
	AND_DRESS(Pair.of("vestido","novia")),
	AND_DRESS_2(Pair.of("vestido","casamiento")),
	AND_DRESS_3(Pair.of("vestido","boda")),
	AND_ALIZ(Pair.of("alianza","casamiento")),
	AND_ALIZ_2(Pair.of("alianza","novio")),
	AND_ALIZ_3(Pair.of("alianza","boda")),
	AND_ALIZ_4(Pair.of("alianzas","")),
	AND_RINGS(Pair.of("anillo","casamiento")),
	AND_RINGS_2(Pair.of("anillo","novio")),
	AND_RINGS_3(Pair.of("anillo","boda")),
	AND_CAKE(Pair.of("torta","casamiento")),
	AND_CAKE_2(Pair.of("torta","cumpleaño")),
	AND_CAKE_3(Pair.of("torta","fiesta")),
	AND_CAKE_4(Pair.of("torta","boda")),
	AND_DECORATION(Pair.of("decoracion","evento")),
	AND_DECORATION_2(Pair.of("decoracion","fiesta")),
	AND_DECORATION_3(Pair.of("decoracion","casamiento")),
	AND_BARMAN(Pair.of("barman","evento")),
	AND_BARMAN_2(Pair.of("barman","fiesta")),
	AND_BARMAN_3(Pair.of("barman","casamiento")),
	AND_BARMAN_4(Pair.of("barman","boda")),
	AND_BARMAN_5(Pair.of("barman","")),
	AND_DJ(Pair.of("disc","jockey")),
	AND_VID(Pair.of("video","filma")),
	
	OR_FOT(Pair.of("fotografo","fotografo"));
	
	
	
	
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
