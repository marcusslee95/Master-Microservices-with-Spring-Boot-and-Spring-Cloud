package section6.just.microservices.currencyexchangeservice;

import java.math.BigDecimal;

public class CurrencyExchange {
	private Long id;
	private String from;
	private String to; 
	private BigDecimal conversionRate;
	
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
	
	
}
