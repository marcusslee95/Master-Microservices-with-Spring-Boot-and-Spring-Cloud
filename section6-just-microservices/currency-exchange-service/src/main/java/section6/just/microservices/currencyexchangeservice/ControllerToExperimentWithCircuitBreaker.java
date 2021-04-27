package section6.just.microservices.currencyexchangeservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerToExperimentWithCircuitBreaker {
	
	@GetMapping("/sample-endpoint")
	public String sampleEndpoint() {
		return "Sample Endpoint";
	}
}
