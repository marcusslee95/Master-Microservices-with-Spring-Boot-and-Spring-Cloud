package section6.just.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long>{
	CurrencyExchange findByFromAndTo(String from, String to); //all we did here is write a method signature but Spring will create the implementation of it.... it's smart so it knows that we want to find a particular row.... using the two values 

}
