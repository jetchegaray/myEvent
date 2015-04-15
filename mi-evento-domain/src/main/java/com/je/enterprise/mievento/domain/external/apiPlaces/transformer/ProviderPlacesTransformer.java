package com.je.enterprise.mievento.domain.external.apiPlaces.transformer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderReviewEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.location.StreetAddressEntity;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.AddressComponent;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.DetailPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.DetailPlaceReview;
import com.je.enterprise.mievento.domain.external.apiPlaces.transformer.type.BuilderConditionRulesProvider;
import com.je.enterprise.mievento.domain.external.apiPlaces.transformer.type.ConditionRuleProviderKeyWord;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class ProviderPlacesTransformer extends
		Transformer<ProviderEntity, DetailPlace> {
	
	private Logger logger = LoggerFactory.getLogger(ProviderPlacesTransformer.class);

	
	@Override
	protected DetailPlace transformDomainToApi(ProviderEntity domainObject) {
		return null;
	}

	@Override
	protected ProviderEntity transformApiToDomain(DetailPlace detailPlace) {
		
		
		String businessName = detailPlace.getName();
		String description = StringUtils.EMPTY;
		LocationEntity location = getLocation(detailPlace);
		String email = StringUtils.EMPTY;
		String cellPhone = (detailPlace.getPhone() != null) ? detailPlace.getPhone() : detailPlace.getInternationalPhone();
		String phone = StringUtils.EMPTY;
		BigDecimal price = BigDecimal.ZERO;
		BigDecimal estimatedPrice = BigDecimal.ZERO;
		List<String> photos = detailPlace.getPhotoLocations();
		
		
		if (photos.isEmpty()){
			logger.debug("Photos vacias para el id : {}",detailPlace.getReference());
			photos.add("../img/noimage.jpg");
		}
		
		List<ProviderReviewEntity> reviews = this.getReviews(detailPlace.getReviews(),detailPlace.getRating());
		ProviderType providerType = getProviderType(detailPlace);
		
		Validate.notNull(providerType,"El providertype es null "+detailPlace.getName());
		Validate.notBlank(cellPhone, "No tiene telefono");
		//only here to eliminate duplicates id
		ProviderEntity entity = new ProviderEntity(detailPlace.getId(), businessName,description,location,email,cellPhone,phone,price,estimatedPrice,photos,providerType,reviews);
		
		return entity;
	}



	private List<ProviderReviewEntity> getReviews(List<DetailPlaceReview> reviewsPlace, Double rating) {
		List<ProviderReviewEntity> reviews = Lists.<ProviderReviewEntity>newArrayList();
		if (reviewsPlace == null){
			return reviews;
		}
		Double ratingDef = (rating != null) ? rating : Double.valueOf(3);
		
		for (DetailPlaceReview detailPlaceReview : reviewsPlace) {
			try{
				Validate.notBlank(detailPlaceReview.getAuthor(),"Author blank");
				Validate.notBlank(detailPlaceReview.getText(),"Text blank");
				reviews.add(new ProviderReviewEntity(detailPlaceReview.getAuthor(),detailPlaceReview.getText(),BigDecimal.valueOf(ratingDef)));
			}catch(Exception ex){
				continue;
			}
		}
		return reviews;
	}


	private LocationEntity getLocation(DetailPlace detailPlace) {
		
		CountryCode countryCode = null;
		String province = null;
		String city = StringUtils.EMPTY;
		String street = StringUtils.EMPTY;
		BigDecimal number = BigDecimal.ZERO;
		String additionalInfo = StringUtils.EMPTY;
		String neighborhood = StringUtils.EMPTY;
		String lng = StringUtils.EMPTY;
		String lat = StringUtils.EMPTY;
		
		for (AddressComponent addressComponent : detailPlace.getAddress()) {
			if (addressComponent.isCountry()){
				countryCode = CountryCode.valueOf(addressComponent.getShortName());
			}
			if (addressComponent.isCity()){
				city = addressComponent.getLongName();
			}
			if (addressComponent.isProvince()){
				province = addressComponent.getLongName();
			}
			if (addressComponent.isStreet()){
				street = addressComponent.getLongName();
			}
			if (addressComponent.isNeighborhood()){
				neighborhood = addressComponent.getLongName();
			}
			if (addressComponent.isNumber()){
				number = BigDecimal.valueOf(Long.valueOf(addressComponent.getLongName()));
			}
		}
		
		if (detailPlace.getGeometry() != null && detailPlace.getGeometry().getLocation() != null){
			Double placeLat = detailPlace.getGeometry().getLocation().getLatitude();
			Double placeLng = detailPlace.getGeometry().getLocation().getLongitude();
			
			if (placeLat != null && placeLng != null){
				lng = String.valueOf(placeLng);
				lat = String.valueOf(placeLat);
			}
		}
		
		Validate.notNull(countryCode," El countrycode es null para el address"+detailPlace.getAddress());
		Validate.notNull(province," El province es null para el address"+detailPlace.getAddress());
		Validate.notNull(city," El city es null para el address"+detailPlace.getAddress());
		
//		logger.info(String.format(" The detail object id : %s, with addresComponent = %s DOESNT MATCH TO ANYONE IN MY MODEL. total dir from api is = %s",detailPlace.getReference(),addressComponent,detailPlace.getAddress()));
		
		StreetAddressEntity streetAddress = new StreetAddressEntity(street, number, additionalInfo, neighborhood);
		return new LocationEntity(countryCode, province, city, streetAddress, lat, lng);
	}

	
	private ProviderType getProviderType(DetailPlace detailPlace){
		
		 Map<ProviderType,List<ConditionRuleProviderKeyWord>> rulesByType = BuilderConditionRulesProvider.getRules();
		 String detailName = detailPlace.getName();
		 
		 for (Entry<ProviderType, List<ConditionRuleProviderKeyWord>> entry : rulesByType.entrySet()) {
			 List<ConditionRuleProviderKeyWord> rules = entry.getValue();
			 
			 for (ConditionRuleProviderKeyWord conditionRuleProviderKeyWord : rules) {
				 Pair<String, String> args = conditionRuleProviderKeyWord.getArguments();
				 if (conditionRuleProviderKeyWord.isAND()){
					 if (StringUtils.containsIgnoreCase(detailName, args.getLeft()) && StringUtils.containsIgnoreCase(detailName, args.getRight())){
						 return entry.getKey();
					 }
				 }else if (conditionRuleProviderKeyWord.isOR()){
					 if (StringUtils.containsIgnoreCase(detailName, args.getLeft()) || StringUtils.containsIgnoreCase(detailName, args.getRight())){
						 return entry.getKey();
					 }
				 }
			}
		 }
		 return null;
		 
	}

	

}
