package com.je.enterprise.mievento.domain.external.apiPlaces.transformer.type;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;


public class BuilderConditionRulesProvider {

	public static  Map<ProviderType, List<ConditionRuleProviderKeyWord>> getRules(){
		Map<ProviderType, List<ConditionRuleProviderKeyWord>> rulesByProvider = Maps.newLinkedHashMap();
		
		rulesByProvider.put(ProviderType.CATERING, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAT));
		rulesByProvider.put(ProviderType.WEDDING_HALL, Arrays.asList(ConditionRuleProviderKeyWord.AND_WEDD_HALL,ConditionRuleProviderKeyWord.AND_WEDD_HALL_2));
		rulesByProvider.put(ProviderType.PHOTOGRAPHER, Arrays.asList(ConditionRuleProviderKeyWord.OR_FOT));
		rulesByProvider.put(ProviderType.VIDEO, Arrays.asList(ConditionRuleProviderKeyWord.OR_VID));
		rulesByProvider.put(ProviderType.DJ, Arrays.asList(ConditionRuleProviderKeyWord.OR_DJ));
		rulesByProvider.put(ProviderType.BIRTHDAY_HALL, Arrays.asList(ConditionRuleProviderKeyWord.AND_BIRTH_HALL));
		rulesByProvider.put(ProviderType.BAR_MITZVAH_HALL, Arrays.asList(ConditionRuleProviderKeyWord.AND_BAR_HALL));
		rulesByProvider.put(ProviderType.DRESSES_SUITS, Arrays.asList(ConditionRuleProviderKeyWord.AND_DRESS,ConditionRuleProviderKeyWord.AND_SUIT));
		rulesByProvider.put(ProviderType.RINGS, Arrays.asList(ConditionRuleProviderKeyWord.AND_RINGS,ConditionRuleProviderKeyWord.AND_ALIZ));
		rulesByProvider.put(ProviderType.CAKES, Arrays.asList(ConditionRuleProviderKeyWord.AND_CAKE,ConditionRuleProviderKeyWord.AND_CAKE_2));
		rulesByProvider.put(ProviderType.BARMAN, Arrays.asList(ConditionRuleProviderKeyWord.OR_BARMAN));
			
		return rulesByProvider;
	}
}
