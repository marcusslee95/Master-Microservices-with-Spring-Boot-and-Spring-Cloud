package section6.just.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //indicating to spring "hey I wanna store objects of this type in a db" 
public class CurrencyExchange {
	@Id //indicating this will serve as the primary key aka. the thing used to identify this row
	private Long id;
	@Column(name  = "currency_from") //this is necessary because from in SQL is a special keyword and this property will get evaluated as that.... so need to give it different name
	private String from;
	@Column(name  = "currency_to") //might as well rename for consistency sake
	private String to; 
	private BigDecimal conversionRate;
	private String environment; 
	

	public CurrencyExchange() { //JPA - which we'll use later - needs a no argument constructor
		
	}
	public CurrencyExchange(Long id, String from, String to, BigDecimal conversionRate) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionRate = conversionRate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public BigDecimal getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	
}
