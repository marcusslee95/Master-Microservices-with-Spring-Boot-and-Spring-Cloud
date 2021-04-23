package section6.just.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController //indicator to spring create instance of this class... that instance will be a restcontroller aka. a thing that takes in requests and sends back responses
public class CurrencyExchangeController {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeRate(@PathVariable String from, @PathVariable String to) {
		return new CurrencyExchange(10001L, from, to, BigDecimal.valueOf(65.00));
	}
}
