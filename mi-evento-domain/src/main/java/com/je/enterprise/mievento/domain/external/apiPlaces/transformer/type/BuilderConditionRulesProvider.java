package com.je.enterprise.mievento.domain.external.apiPlaces.transformer.type;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;


public class BuilderConditionRulesProvider {

	public static  Map<ProviderType, List<ConditionRuleProviderKeyWord>> getRules(){
		Map<ProviderType, List<ConditionRuleProviderKeyWord>> rulesByProvider = Maps.newLinkedHashMap();
		
		rulesByProvider.put(ProviderType.ALQUILER, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT));
		rulesByProvider.put(ProviderType.AUTOESCUELA, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_2));
		rulesByProvider.put(ProviderType.AUXILIO, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_3));
		rulesByProvider.put(ProviderType.CONCESIONARIA, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_6,ConditionRuleProviderKeyWord.AND_CAT_7));
//		rulesByProvider.put(ProviderType.ENTRETENIMIENTO, Arrays.asList(ConditionRuleProviderKeyWord.AND_DJ));
		rulesByProvider.put(ProviderType.ES_SERVICIO, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_8));
		rulesByProvider.put(ProviderType.ESTACIONAMIENTO, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_9,ConditionRuleProviderKeyWord.AND_CAT_10));
		rulesByProvider.put(ProviderType.ESTETICA, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_11));
		rulesByProvider.put(ProviderType.GESTORIA, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_12));
        rulesByProvider.put(ProviderType.MERCHAND, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_13));
		rulesByProvider.put(ProviderType.PARABRISAS_CRISTALES, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_14));
		rulesByProvider.put(ProviderType.RESPUESTOS, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_17,ConditionRuleProviderKeyWord.AND_CAT_18));
		rulesByProvider.put(ProviderType.REVISACION, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_5));
		rulesByProvider.put(ProviderType.SEGUROS, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_19));
		rulesByProvider.put(ProviderType.TALLER_MECANICO, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_20));
		rulesByProvider.put(ProviderType.VTV, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT_21));
        	
		return rulesByProvider;
	}
}
