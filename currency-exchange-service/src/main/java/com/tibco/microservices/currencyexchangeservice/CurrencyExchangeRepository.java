/**
 * 
 */
package com.tibco.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pnakave
 *
 */
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long>{

	CurrencyExchange findByFromAndTo(String from, String to);
}
