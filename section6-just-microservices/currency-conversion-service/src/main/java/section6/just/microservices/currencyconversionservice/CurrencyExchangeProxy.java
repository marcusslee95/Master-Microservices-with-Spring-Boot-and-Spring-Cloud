package section6.just.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-exchange", url="localhost:8000") //telling spring hey... this class is used to send requests to the service that lives at this location / url
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeRate(@PathVariable String from, 
			@PathVariable String to); //even though it actually returns a currencyExchange object... since we wrote our currencyConversion objects to have the same fields as the currencyExchange objects.... (just added a couple more fields)... it automatically gets mapped to a currencyConversion object
}
