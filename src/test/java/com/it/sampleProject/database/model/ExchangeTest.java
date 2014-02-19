package com.it.sampleProject.database.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.it.sampleProject.database.entities.ExchangeEntity;
import org.junit.Before;
import org.junit.Test;

public class ExchangeTest {

	@Before
	public void setUp() {

	}

	@Test
	public void shouldMapFromEntity() {
		ExchangeEntity entity = ModelTestTools.prepareExchangeEntity();

		Exchange result = Exchange.mapFromEntity(entity);

		assertNotNull(result);
		assertEquals(ModelTestTools.CUSTOMER_ID, result.getCustomer().getId());
		assertEquals(ModelTestTools.EXCHANGE_ID, result.getExchangeId());
		assertEquals(ModelTestTools.ID, result.getId());
		assertEquals(ModelTestTools.ROUTE_ID, result.getRouteId());
		assertEquals(ModelTestTools.TIMESTAMP, result.getCreationDate());
	}

	@Test
	public void shouldMapToEntity() {
		Exchange exchange = ModelTestTools.prepareExchange();

		ExchangeEntity result = Exchange.mapToEntity(exchange);

		assertNotNull(result);
		assertEquals(ModelTestTools.CUSTOMER_ID, result.getCustomer().getId());
		assertEquals(ModelTestTools.EXCHANGE_ID, result.getExchangeId());
		assertEquals(ModelTestTools.ID, result.getId());
		assertEquals(ModelTestTools.ROUTE_ID, result.getRouteId());
		assertEquals(ModelTestTools.TIMESTAMP, result.getCreationDate());
	}

}
