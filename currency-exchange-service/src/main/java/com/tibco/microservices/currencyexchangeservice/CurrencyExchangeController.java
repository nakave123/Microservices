/**
 * 
 */
package com.tibco.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pnakave
 *
 */
@RestController //as we are exposing rest api from here.
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private CurrencyExchangeRepository repository;

	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retreiveExchageValue(@PathVariable String from, @PathVariable String to) {
		
		//CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
		
		//Jpa-data automatically converts below into sql statement and returns execution result for it.
		logger.info("retrieveExchange value called with {} to {}", from, to);
		
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		if(currencyExchange==null) {
			throw new RuntimeException("Unable to find data from " + from + " to " + to);
		}
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironmnet(port);
		return currencyExchange;
	}
	
}
