package com.je.enterprise.mievento.domain.external.apiPlaces.transformer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.TypesPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.process.KeyWordsHandler;

@Component
public class ProviderTypeTransformer {

	KeyWordsHandler keyWordsHandler;

	@Autowired
	public ProviderTypeTransformer(KeyWordsHandler keyWordsHandler) {
		this.keyWordsHandler = keyWordsHandler;
	}
	
	
//	public ProviderType getProviderType(String name,List<String> types){
//		
//		for (String keyWord : keyWordsHandler.getKeywords()) {
//			if (name.contains(keyWord)){
//				if (name.contains("casamiento") && types.contains(TypesPlace.ESTABLISHMENT.getName())){
//					return ProviderType.WEDDING_HALL;
//				}
//				if (name.contains("casamiento") && types.contains(TypesPlace.ESTABLISHMENT.getName())){
//					return ProviderType.WEDDING_HALL;
//				}
//			}
//		}
//		
//	}
	
}
