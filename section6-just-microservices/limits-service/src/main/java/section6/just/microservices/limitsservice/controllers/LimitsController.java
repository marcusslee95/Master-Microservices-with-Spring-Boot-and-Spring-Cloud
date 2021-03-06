package section6.just.microservices.limitsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import section6.just.microservices.limitsservice.beans.Limits;
import section6.just.microservices.limitsservice.configuration.Configuration;



@RestController
public class LimitsController {
	
	@Autowired //take instance of Configuration class that Spring created.... and assign it to this property of LimitsController instance... since Spring will create an instance of LimitsController when it sees @RestApiController.... it's essentially a specialized version of the @Component annotation
	private Configuration configuration;
	
	@GetMapping("/limits")
	public Limits retrieveLimits() {
//		return new Limits(1,1000);
		return new Limits(configuration.getMinimum(), configuration.getMaximum());
	
	}
}
