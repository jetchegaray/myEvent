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

	AND_CAT(Pair.of("keyword.alquiler","keyword.auto")),
	AND_CAT_2(Pair.of("keyword.autoescuela","")),
	AND_CAT_3(Pair.of("keyword.auxilio","keyword.mecanico")),
	AND_CAT_4(Pair.of("keyword.cerrajeria","keyword.auto")),
	AND_CAT_5(Pair.of("keyword.verificacion","keyword.automotriz")),
	AND_CAT_6(Pair.of("keyword.concesionaria","keyword.oficial")),
	AND_CAT_7(Pair.of("keyword.agencia","keyword.auto")),
	AND_CAT_8(Pair.of("keyword.estacion","keyword.servicio")),
	AND_CAT_9(Pair.of("keyword.estacionamiento","")),
	AND_CAT_10(Pair.of("keyword.garage","")),
	AND_CAT_11(Pair.of("keyword.estetica","keyword.automotriz")),
	AND_CAT_12(Pair.of("keyword.gestoria","keyword.automotor")),
	AND_CAT_13(Pair.of("keyword.merchandising","keyword.auto")),
	AND_CAT_14(Pair.of("keyword.parabrisas","")),
	AND_CAT_16(Pair.of("keyword.registro","keyword.automotor")),
	AND_CAT_17(Pair.of("keyword.repuestos","keyword.auto")),
	AND_CAT_18(Pair.of("keyword.accesorios","keyword.auto")),
	AND_CAT_19(Pair.of("keyword.seguro","keyword.automotor")),
	AND_CAT_20(Pair.of("keyword.taller","keyword.mecanico")),
	AND_CAT_21(Pair.of("keyword.vtv",""));
	
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
		if (filtering.size() == 2){
			return Pair.of(filtering.get(0), filtering.get(1)); 
		}
		return Pair.of(filtering.get(0),"");
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
