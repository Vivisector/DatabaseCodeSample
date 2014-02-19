package com.it.sampleProject.database.dao;

import com.it.sampleProject.BaseIdsDBUnitTest;
import com.it.sampleProject.database.PagedSearchResults;
import com.it.sampleProject.database.entities.DocumentEntity;
import com.it.sampleProject.database.entities.EventEntity;
import com.it.sampleProject.database.entities.ExchangeEntity;
import com.it.sampleProject.database.search.FilterOptions;
import com.it.sampleProject.database.consts.DbUnitConstants;
import com.it.sampleProject.database.utils.DatabseEntityUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author Igor Ivaniuk, Dmytro Stepanyshchenko
 */
public class EventDAOTest extends BaseIdsDBUnitTest {

    @Autowired
    private EventDAO eventDAO;

    @Test
    public void testFindEventById() {
        final EventEntity result = eventDAO.findEventEntityById(DbUnitConstants.DOCUMENT_ID);
        assertNotNull(result);
        Assert.assertEquals(DbUnitConstants.EVENT_ID, result.getId());
        Assert.assertEquals(DbUnitConstants.EVENT_MESSAGE, result.getMessage());
    }

    @Test
    public void testFindEventWithPaging() {
        FilterOptions fo = DatabseEntityUtils.prepareEventFilter();

        final PagedSearchResults<EventEntity> resultList = eventDAO.findEventsWithPaging(fo, null, null, null);

        assertNotNull(resultList);
        assertTrue(resultList.getTotalRecords() > 0);
        final EventEntity result = resultList.getSearchResults().get(0);
        Assert.assertEquals(DbUnitConstants.EVENT_ID, result.getId());
        Assert.assertEquals(DbUnitConstants.EVENT_MESSAGE, result.getMessage());
    }

    @Test
    public void testFindEventByExchange() {
        FilterOptions fo = DatabseEntityUtils.prepareExchangeFilter();

        final PagedSearchResults<EventEntity> resultList = eventDAO.findEventsWithPaging(fo, null, null, null);

        assertNotNull(resultList);
        assertTrue(resultList.getTotalRecords() > 0);
        final EventEntity result = resultList.getSearchResults().get(0);
        Assert.assertEquals(DbUnitConstants.EVENT_ID, result.getId());
        Assert.assertEquals(DbUnitConstants.EVENT_MESSAGE, result.getMessage());
        assertNotNull(result.getExchange());
        ExchangeEntity resultExchange = result.getExchange();
        assertEquals(DbUnitConstants.EXCHANGE_ID.toString(), resultExchange.getExchangeId());
    }

    @Test
    public void testFindEventByDocument() {
        FilterOptions fo = DatabseEntityUtils.prepareDocumentFilterByBodyAndVersion();

        final PagedSearchResults<EventEntity> resultList = eventDAO.findEventsWithPaging(fo, null, null, null);

        assertNotNull(resultList);
        assertTrue(resultList.getTotalRecords() > 0);
        final EventEntity result = resultList.getSearchResults().get(0);
        Assert.assertEquals(DbUnitConstants.EVENT_ID, result.getId());
        Assert.assertEquals(DbUnitConstants.EVENT_MESSAGE, result.getMessage());
        assertNotNull(result.getExchange());
        final DocumentEntity resultDocument = result.getDocument();
        Assert.assertEquals(DbUnitConstants.DOCUMENT_VERSION.toString(), resultDocument.getDocumentVersion());
        Assert.assertEquals(DbUnitConstants.DOCUMENT_BODY.toString(), resultDocument.getBody());
    }
}
