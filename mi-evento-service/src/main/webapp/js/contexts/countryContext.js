
mieventoContext.service("countryContext", function(){
	
	var allCountries = null;
	
	this.setAllCountries = function(countries){
		allCountries = countries;
	}
	
	this.getAllCountries = function(){
		return allCountries;
	}
	
});