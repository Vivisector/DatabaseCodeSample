package com.it.sampleProject.database.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.it.sampleProject.database.entities.DocumentEntity;
import com.it.sampleProject.database.entities.EventEntity;
import org.junit.Before;
import org.junit.Test;

import com.it.sampleProject.database.entities.ExchangeEntity;

public class EventTest {

	private ExchangeEntity exchangeEntity;
	private Exchange exchange;
	private DocumentEntity documentEntity;
	private Document document;

	@Before
	public void setUp() {
		exchangeEntity = ModelTestTools.prepareExchangeEntity();
		exchange = ModelTestTools.prepareExchange();
		documentEntity = ModelTestTools.prepareDocumentEntity(exchangeEntity);
		document = ModelTestTools.prepareDocument(exchange);
	}

	@Test
	public void shouldMapFromEntity() {
		EventEntity entity = new EventEntity();
		entity.setId(ModelTestTools.ID);
		entity.setMessage(ModelTestTools.MESSAGE);
		entity.setCreationDate(ModelTestTools.TIMESTAMP);
		entity.setExchange(exchangeEntity);
		entity.setDocument(documentEntity);

		Event result = Event.mapFromEntity(entity);

		assertNotNull(result);
		assertEquals(ModelTestTools.ID, result.getId());
		assertEquals(ModelTestTools.MESSAGE, result.getMessage());
		assertEquals(ModelTestTools.TIMESTAMP, result.getCreationDate());

		assertNotNull(result.getDocument());
		assertNotNull(result.getExchange());
	}

	@Test
	public void shouldMapToEntity() {
		Event event = new Event();
		event.setId(ModelTestTools.ID);
		event.setMessage(ModelTestTools.MESSAGE);
		event.setCreationDate(ModelTestTools.TIMESTAMP);
		event.setExchange(exchange);
		event.setDocument(document);

		EventEntity result = Event.mapToEntity(event);

		assertNotNull(result);
		assertEquals(ModelTestTools.ID, result.getId());
		assertEquals(ModelTestTools.MESSAGE, result.getMessage());
		assertEquals(ModelTestTools.TIMESTAMP, result.getCreationDate());

		assertNotNull(result.getDocument());
		assertNotNull(result.getExchange());
	}

}
