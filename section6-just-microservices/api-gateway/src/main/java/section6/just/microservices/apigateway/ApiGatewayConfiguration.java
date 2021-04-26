package section6.just.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //hey spring.. this class is something that will be used to get all the initial details of the api gateway right.... i.e. what requests the api gateway will receive
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get") //just an example to show 1. we can specify what requests api gateway will react to (in this case that's requests sent to "/get" 2. we can tell api gateway to add some things to the request before it sends it off to some other service 3. we can tell api gateway to change the location / url that request is being sent to (from "/get" to "http://httpbin.org:80"
						.filters(f -> f //this would be the place to do something that is specific to a particular request.... whereas class that implements GlobalFilter i.e. LoggingFilter.... would be perfect to implement stuff that would apply for every request
								.addRequestHeader("MyHeader", "MyURI")
								.addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80")
						)
				.route(p -> p.path("/currency-exchange/**") //1. api gateway will receive requests of this format
						.uri("lb://currency-exchange") //2. it will send that request to this location / url.... what is this locaiton? -> lb just means the load balancer that the naming server uses.... and "currency-exchange" is just the name of the service as it's registered in the naming server.... so really you can just read it as... talk to the naming server to find out all the different locations / urls of the instances of this service.... and then use the load balancer to balance the load across those instances aka. send requests in round robin fashion to those instances
						)
				.route(p -> p.path("/currency-conversion/**") 
						.uri("lb://currency-conversion")
						)
				.route(p -> p.path("/currency-conversion-feign/**") 
						.uri("lb://currency-conversion")
						)
				.build();
	}

}
