db.providers.drop();
db.providers.insert([
{ 
	"className" : "com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity", 
	"businessName" : "Plaza Group", 
	"description" : "Fernando Díaz Photography busca captar una Boda de forma artística, con toques de poesía en cada una de las imágenes de la fiesta. Una mirada muy personal gracias a la pasión con que se dedica el profesional en todos los fotoreportajes de boda. La compañía sabe que el casamiento es un evento único, y por tanto, su trabajo es muy valioso, ya que será el único recuerdo que quede de un día cargado de emociones.",
	"location" : 
		{ 
			"countryCode" : "AR", 
			"province" : "Tucumán",
			"city": "San Miguel de Tucumán",
			"streetAddress" : 
			{ 
				"street" : " Celedonio Gutierrez", 
				"number" : "280",
				"neighborhood" : "",
				"additionalInfo" : ""
			} 
		}, 
	"email" : "Fc@gmail.com", 
	"cellPhone" : "15548798", 
	"phone" : "454879865", 
	"price" : "1", 
	"estimatedPrice" : "10", 
	"picture" : "provider1",
	"providerType" : "PHOTOGRAPHER",
	"version" : NumberLong(1) 
},
{ 
	"className" : "com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity", 
	"businessName" : "Plaza Group", 
	"description" : "Fernando Díaz Photography busca captar una Boda de forma artística, con toques de poesía en cada una de las imágenes de la fiesta. Una mirada muy personal gracias a la pasión con que se dedica el profesional en todos los fotoreportajes de boda. La compañía sabe que el casamiento es un evento único, y por tanto, su trabajo es muy valioso, ya que será el único recuerdo que quede de un día cargado de emociones.",
	"location" : 
		{ 
			"countryCode" : "AR", 
			"province" : "Tucumán",
			"city": "San Miguel de Tucumán",
			"streetAddress" : 
			{ 
				"street" : " Celedonio Gutierrez", 
				"number" : "280",
				"neighborhood" : "",
				"additionalInfo" : ""
			} 
		}, 
	"email" : "Fc@gmail.com", 
	"cellPhone" : "15548798", 
	"phone" : "454879865", 
	"price" : "1", 
	"estimatedPrice" : "10", 
	"picture" : "provider2",
	"providerType" : "PHOTOGRAPHER",
	"version" : NumberLong(1) 
},
{ 
	"className" : "com.je.enterprise.mievento.domain.entity.wedding.PlaceEntity", 
	"m2" : "250", 
	"estimatedQuantityTables" : "19", 
	"estimatedQuantityPerson" : "75", 
	"businessName" : "Salon Pueyrredon", 
	"description" : "El mejor salon de todos loco.Rompermos todo", 
	"location" : 
	{ 
		"countryCode" : "AR", 
		"province" : "CABA", 
		"city" : "Bs As", 
		"streetAddress" : 
		{ 
			"street" : "calleZ", 
			"number" : "14", 
			"neighborhood" : "Recoleta",
			"additionalInfo" : ""
		}
	}, 
	"email" : "salonP@gmail.com", 
	"cellPhone" : "156545787", 
	"phone" : "01144578954", 
	"price" : "1", 
	"estimatedPrice" : "10", 
	"picture" : "provider2",
	"providerType" : "WEDDING_HALL", 
	"version" : NumberLong(1) 
}
],
{
	writeConcern: 2
});