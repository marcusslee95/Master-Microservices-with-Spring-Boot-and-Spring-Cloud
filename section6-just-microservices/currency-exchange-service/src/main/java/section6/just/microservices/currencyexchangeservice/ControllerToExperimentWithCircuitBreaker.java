package section6.just.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class ControllerToExperimentWithCircuitBreaker {
	
	private Logger logger =
			LoggerFactory.getLogger(ControllerToExperimentWithCircuitBreaker.class); //can think of it as the thing that executes print statements... I'm not sure why we're using this instead of just System.out.println
	
	@GetMapping("/sample-endpoint")
//	@Retry(name = "default") //just saying... hey if a request to this endpoint fails then try it again a couple times to see if it works.... before sending back an error
//	@Retry(name = "sample-endpoint")
//	@Retry(name = "sample-endpoint", fallbackMethod="hardCodedResponse") //if all the retry attempts fail.... just send back return value of this method as response instead.... cuz otherwise we'd just be sending back an error
	@CircuitBreaker(name = "sample-endpoint", fallbackMethod="hardCodedResponse") // what is a circuitbreaker? It's the solution for when service is down for a long time (not just momentarily down... in which case you'd use the retry in the line above)... where instead of keep on retrying to execute the request.... we retry to execute a request a couple times and if it's failing... determine something's wrong w/the service so we just stop sending the request and just send back some default response for awhile (cuz y send requests to something that we know isn't working)..... before sending a couple requests again just to see if the issue w/the service was resolved... and if the service is back up working properly... sending rqeuests again to it
	@RateLimiter(name = "sample-endpoint") //used a rate limiter -> it's something that lets you set a cap on the # requests that can be sent to a particular endpoint in a given amount of time...... it's used for preventing overworking an endpoint.... which could cause the whole service to either go down or slow down...
	@Bulkhead(name = "sample-endpoint") //does same thing as rate limiter but sets cap on # of concurrent requests.... 
	public String sampleEndpoint() {
		logger.info("Request has been sent to Sample Endpoint");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http:somenonexistenturl.com", 
				String.class); //code to cause an error 
		
//		return "Sample Endpoint";
		return forEntity.getBody();
	}
	
	public String hardCodedResponse(Exception ex) {
		return "fallback-response.... since all the retry attempts failed / resulted in some error";
	}
}
