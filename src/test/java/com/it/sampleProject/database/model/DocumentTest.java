package com.it.sampleProject.database.model;

import com.it.sampleProject.database.entities.DocumentEntity;
import com.it.sampleProject.database.entities.ExchangeEntity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DocumentTest {

    private ExchangeEntity exchangeEntity;
    private Exchange exchange;

    @Before
    public void setUp() {
        exchangeEntity = ModelTestTools.prepareExchangeEntity();
        exchange = ModelTestTools.prepareExchange();
    }

    @Test
    public void shouldMapToEntityNewExchange() {
        Document doc = new Document();
        doc.setBody(ModelTestTools.BODY);
        doc.setDocumentVersion(ModelTestTools.DOCUMENT_VERSION);
        doc.setBodyType(ModelTestTools.BODY_TYPE);
        doc.setExchange(exchange);
        doc.setFilePath(ModelTestTools.FILE_PATH);
        doc.setId(ModelTestTools.ID);
        doc.setCreationDate(ModelTestTools.TIMESTAMP);

        DocumentEntity result = Document.mapToEntity(doc);

        assertNotNull(result);
        assertEquals(ModelTestTools.BODY, result.getBody());
        assertEquals(ModelTestTools.DOCUMENT_VERSION, result.getDocumentVersion());
        assertEquals(ModelTestTools.BODY_TYPE, result.getBodyType());
        assertEquals(ModelTestTools.FILE_PATH, result.getFilePath());
        assertEquals(ModelTestTools.ID, result.getId());
        assertEquals(ModelTestTools.TIMESTAMP, result.getCreationDate());

        assertNotNull(result.getExchange());
        assertNotSame(exchangeEntity, result.getExchange());
    }

    @Test
    public void shouldMapFromEntityNewExchange() {
        DocumentEntity de = new DocumentEntity();
        de.setBody(ModelTestTools.BODY);
        de.setDocumentVersion(ModelTestTools.DOCUMENT_VERSION);
        de.setBodyType(ModelTestTools.BODY_TYPE);
        de.setExchange(exchangeEntity);
        de.setFilePath(ModelTestTools.FILE_PATH);
        de.setId(ModelTestTools.ID);
        de.setCreationDate(ModelTestTools.TIMESTAMP);

        Document result = Document.mapFromEntity(de);

        assertNotNull(result);
        assertEquals(ModelTestTools.BODY, result.getBody());
        assertEquals(ModelTestTools.DOCUMENT_VERSION, result.getDocumentVersion());
        assertEquals(ModelTestTools.BODY_TYPE, result.getBodyType());
        assertEquals(ModelTestTools.FILE_PATH, result.getFilePath());
        assertEquals(ModelTestTools.ID, result.getId());
        assertEquals(ModelTestTools.TIMESTAMP, result.getCreationDate());

        assertNotNull(result.getExchange());
        assertNotSame(exchange, result.getExchange());

    }
}
