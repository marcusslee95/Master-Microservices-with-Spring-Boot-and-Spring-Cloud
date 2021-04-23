package section6.just.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController //indicator to spring create instance of this class... that instance will be a restcontroller aka. a thing that takes in requests and sends back responses
public class CurrencyExchangeController {
	
	@Autowired //aka. spring find an instance of this and then assign it to this property
	private Environment environment; //this will have info about environment this service is in
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeRate(@PathVariable String from, @PathVariable String to) {
		CurrencyExchange currencyExchange = new CurrencyExchange(10001L, 
				from, to, BigDecimal.valueOf(65.00));
//		currencyExchange.setEnvironment("8000");
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
}
