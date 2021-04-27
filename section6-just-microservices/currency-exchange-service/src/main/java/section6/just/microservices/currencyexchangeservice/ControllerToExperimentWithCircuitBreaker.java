package section6.just.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class ControllerToExperimentWithCircuitBreaker {
	
	private Logger logger =
			LoggerFactory.getLogger(ControllerToExperimentWithCircuitBreaker.class); //can think of it as the thing that executes print statements... I'm not sure why we're using this instead of just System.out.println
	
	@GetMapping("/sample-endpoint")
//	@Retry(name = "default") //just saying... hey if a request to this endpoint fails then try it again a couple times to see if it works.... before sending back an error
	@Retry(name = "sample-api")
	public String sampleEndpoint() {
		logger.info("Request has been sent to Sample Endpoint");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http:somenonexistenturl.com", 
				String.class); //code to cause an error 
		
//		return "Sample Endpoint";
		return forEntity.getBody();
	}
}
