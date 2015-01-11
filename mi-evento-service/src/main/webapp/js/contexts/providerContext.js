
mieventoContext.service("providerContext",function(){
	var detailProvider = null;
	
	this.setDetailProvider = function(provider){
		detailProvider = provider;
	}
	
	this.getDetailProvider = function(){
		return detailProvider;
	}
	
	
	
});