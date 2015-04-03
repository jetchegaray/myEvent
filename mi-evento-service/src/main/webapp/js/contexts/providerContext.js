
mieventoContext.service("providerContext",function(){
	var detailProvider = null;
	
	this.setDetailProvider = function(provider){
		detailProvider = provider;
	}
	
	this.getDetailProvider = function(){
		return detailProvider;
	}
	
	this.getLocationToStringProvider = function(){
		
		var description = "";
		if (angular.isUndefined(detailProvider.location)){
			return description;
		}
		if (!angular.isUndefined(detailProvider.location.province)){
			description = description.concat(" Province : "+detailProvider.location.province);
		}
		if (!angular.isUndefined(detailProvider.location.city)){
			description = description.concat(" City : "+detailProvider.location.city);
		}
		if (!angular.isUndefined(detailProvider.location.streetAddress) && !angular.isUndefined(detailProvider.location.streetAddress.street)){
			description = description.concat(" Street : "+detailProvider.location.streetAddress.street);
		}
		if (!angular.isUndefined(detailProvider.location.streetAddress) && !angular.isUndefined(detailProvider.location.streetAddress.number)){
			description = description.concat(detailProvider.location.streetAddress.number);
		}
		if (!angular.isUndefined(detailProvider.location.streetAddress) && !angular.isUndefined(detailProvider.location.streetAddress.neighborhood)){
			description = description.concat(" neighborhood : "+detailProvider.location.streetAddress.neighborhood);
		}
		return description;
	}
	
	
});