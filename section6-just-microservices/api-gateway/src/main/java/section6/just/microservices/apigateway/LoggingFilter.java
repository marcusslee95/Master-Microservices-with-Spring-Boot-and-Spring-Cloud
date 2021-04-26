package section6.just.microservices.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter{ //this class has methods that will execute everytime request gets sent to api gateway.... so it's the perfect place to implement cross cutting concerns.... in the method below I'm logging the details of the request.... aka. every time a request gets sent to api gateway... i'm logging it's details 
	
	private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		logger.info("Path of the request received -> {}",
				exchange.getRequest().getPath()); 
		return chain.filter(exchange); //just means keep doing what you're supposed to be doing api gateway... aka. now that you've done the cross cutting concern...... pass the request over to the service that the request should be sent to.
	}

}
