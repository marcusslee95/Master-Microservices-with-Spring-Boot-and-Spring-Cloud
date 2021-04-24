package section6.just.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
			@PathVariable String to, @PathVariable BigDecimal quantity) {
		
		Map<String, String>uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversion.class, uriVariables); //sending request to currency-exchange-service.... to get the exchange rate 
		CurrencyConversion currencyConversion = responseEntity.getBody();
//		return new CurrencyConversion(10001L, from, to, BigDecimal.ONE,
//				quantity, BigDecimal.ONE, "");
		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalCalculatedAmount(
				quantity.multiply(currencyConversion.getConversionRate()));
		return currencyConversion;//sending back all these details w/the key one being the value of one currency in another currency
	}

}
