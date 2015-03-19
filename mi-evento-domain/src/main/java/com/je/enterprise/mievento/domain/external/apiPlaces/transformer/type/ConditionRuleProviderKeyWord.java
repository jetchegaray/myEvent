package com.je.enterprise.mievento.domain.external.apiPlaces.transformer.type;

import org.apache.commons.lang3.tuple.Pair;

public enum ConditionRuleProviderKeyWord {

	AND_CAT(Pair.of("catering","")),
	AND_WEDD_HALL(Pair.of("salon","boda")),
	AND_WEDD_HALL_2(Pair.of("salon","casamiento")),
	AND_BIRTH_HALL(Pair.of("salon","cumpleaños")),
	AND_PARTY_HALL(Pair.of("salon","fiestas")),
	AND_BAR_HALL(Pair.of("salon","mitzvah")),
	AND_SUIT(Pair.of("trajes","casamiento")),
	AND_DRESS(Pair.of("vestidos","casamiento")),
	AND_ALIZ(Pair.of("alianzas","casamiento")),
	AND_RINGS(Pair.of("anillos","casamiento")),
	AND_DEC(Pair.of("decoracion","eventos")),
	AND_CAKE(Pair.of("tortas","casamiento")),
	AND_CAKE_2(Pair.of("torta","casamiento")),
	
	OR_FOT(Pair.of("fotografos","fotografo")),
	OR_VID(Pair.of("videos","video")),
	OR_DJ(Pair.of("dj","djs")),
	OR_BARMAN(Pair.of("barman",""));
	
	
	private Pair<String, String> arguments;
	
	private ConditionRuleProviderKeyWord(Pair<String, String> arguments) {
		this.arguments = arguments;
	}
	
	public Pair<String, String> getArguments(){
		return this.arguments; 
	}
	
}
