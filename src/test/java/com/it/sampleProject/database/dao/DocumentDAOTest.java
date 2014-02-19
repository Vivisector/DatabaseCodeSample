package com.it.sampleProject.database.dao;

import com.it.sampleProject.BaseIdsDBUnitTest;
import com.it.sampleProject.database.PagedSearchResults;
import com.it.sampleProject.database.entities.DocumentEntity;
import com.it.sampleProject.database.entities.ExchangeEntity;
import com.it.sampleProject.database.search.FilterOptions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.it.sampleProject.database.utils.DatabseEntityUtils.prepareDocumentFilterByBodyAndVersion;
import static com.it.sampleProject.database.utils.DatabseEntityUtils.prepareExchangeFilter;
import static org.junit.Assert.*;

/**
 * @author Igor Ivaniuk, Dmytro Stepanyshchenko
 */
public class DocumentDAOTest extends BaseIdsDBUnitTest {
    @Autowired
    private DocumentDAO documentDAO;

    @Test
    public void testFindDocumentById() {
        DocumentEntity result = documentDAO.findDocumentEntityById(DOCUMENT_ID);
        assertNotNull(result);
        assertEquals(DOCUMENT_BODY, result.getBody());
    }

    @Test
    public void testFindDocumentsWithPaging() {
        FilterOptions fo = prepareDocumentFilterByBodyAndVersion();

        PagedSearchResults<DocumentEntity> resultList = documentDAO.findDocumentsWithPaging(fo, null, null, null);

        assertNotNull(resultList);
        assertTrue(resultList.getTotalRecords() > 0);
        DocumentEntity result = resultList.getSearchResults().get(0);
        assertEquals(DOCUMENT_BODY, result.getBody());
        assertEquals(DOCUMENT_VERSION, result.getDocumentVersion());
    }

    @Test
    public void testFindDocumentsByExchange() {
        FilterOptions fo = prepareExchangeFilter();

        PagedSearchResults<DocumentEntity> resultList = documentDAO.findDocumentsWithPaging(fo, null, null, null);

        assertNotNull(resultList);
        assertTrue(resultList.getTotalRecords() > 0);
        DocumentEntity result = resultList.getSearchResults().get(0);
        assertEquals(DOCUMENT_BODY, result.getBody());
        assertEquals(DOCUMENT_VERSION, result.getDocumentVersion());
        assertNotNull(result.getExchange());
        ExchangeEntity resultExchange = result.getExchange();
        assertEquals(EXCHANGE_ID.toString(), resultExchange.getExchangeId());
    }
}