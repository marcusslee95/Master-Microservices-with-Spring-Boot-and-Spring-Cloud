package section6.just.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange", url="localhost:8000") //telling spring hey... this class is used to send requests to the service that lives at this location / url
@FeignClient(name="currency-exchange") //just by not specifying the url of the service we want to send the request to...... feign recognizes ok.... i have to talk to the naming server to get all the urls of the instances of this service..... and then i'll load balance the requests i send... among those different instances (in this particular example that would mean... sending the request to instance 1 of currency exchange service which lives on port 8000 and then to instance 2 which lives on port 8001... rinse and repeat)
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeRate(@PathVariable String from, 
			@PathVariable String to); //even though it actually returns a currencyExchange object... since we wrote our currencyConversion objects to have the same fields as the currencyExchange objects.... (just added a couple more fields)... it automatically gets mapped to a currencyConversion object
}
