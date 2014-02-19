package com.it.sampleProject.database;

import com.it.database.exceptions.DuplicateEntryException;
import com.it.sampleProject.BaseIdsDBUnitTest;
import com.it.sampleProject.database.model.*;
import com.it.sampleProject.database.search.FilterOptions;
import com.it.sampleProject.tests.IntegrationTests;
import com.it.sampleProject.database.consts.DbUnitConstants;
import com.it.sampleProject.database.utils.DatabseEntityUtils;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.*;

@Category(IntegrationTests.class)
public class DatabaseHelperTest extends BaseIdsDBUnitTest {
    private static final int FIRST_RESULT = 0;
    private static final int MAX_RESULTS = 5;

    @Autowired
    private DatabaseHelper databaseHelper;

    @Test
    public void testFoundEventById() {
        Event actualEvent = databaseHelper.findEventById(DbUnitConstants.EVENT_ID);

        assertNotNull(actualEvent);
        Assert.assertEquals(DbUnitConstants.EVENT_ID, actualEvent.getId());
    }

    @Test
    public void testNotFoundEventById() {
        Event actualEvent = databaseHelper.findEventById(DbUnitConstants.NOT_EXISTS_OBJECT_ID);

        assertNull(actualEvent);
    }

    @Test
    public void testFoundEventWithPaging() {
        FilterOptions fo = DatabseEntityUtils.prepareEventFilter();

        PagedSearchResults<Event> result = databaseHelper.findEventsWithPaging(fo, null, FIRST_RESULT, MAX_RESULTS);

        assertNotNull(result);
        assertEquals(FIRST_RESULT, result.getFirstResult());
        assertEquals(MAX_RESULTS, result.getMaxResults());
        assertTrue(result.getSearchResults().size() > 0);
        Event actualEvent = result.getSearchResults().get(0);
        assertNotNull(actualEvent);
        Assert.assertEquals(DbUnitConstants.EVENT_ID, actualEvent.getId());
        Assert.assertEquals(DbUnitConstants.EVENT_MESSAGE, actualEvent.getMessage());
    }

    @Test
    public void testFoundDocumentById() {
        final Document actualDocument = databaseHelper.findDocumentById(DbUnitConstants.DOCUMENT_ID);

        assertNotNull(actualDocument);
        Assert.assertEquals(DbUnitConstants.DOCUMENT_ID, actualDocument.getId());
    }

    @Test
    public void testNotFoundDocumentById() {
        final Document actualDocument = databaseHelper.findDocumentById(DbUnitConstants.NOT_EXISTS_OBJECT_ID);

        assertNull(actualDocument);
    }

    @Test
    public void testFoundDocumentWithPaging() {
        FilterOptions fo = DatabseEntityUtils.prepareDocumentFilterByBody();

        final PagedSearchResults<Document> result = databaseHelper.findDocumentsWithPaging(fo, null, FIRST_RESULT, MAX_RESULTS);

        assertNotNull(result);
        assertEquals(FIRST_RESULT, result.getFirstResult());
        assertEquals(MAX_RESULTS, result.getMaxResults());
        assertTrue(result.getSearchResults().size() > 0);
        final Document actualDocument = result.getSearchResults().get(0);
        assertNotNull(actualDocument);
        Assert.assertEquals(DbUnitConstants.DOCUMENT_ID, actualDocument.getId());
        Assert.assertEquals(DbUnitConstants.DOCUMENT_BODY, actualDocument.getBody());
    }

    @Test
    public void testFoundExchangeById() {
        final Exchange actualExchange = databaseHelper.findExchangeById(DbUnitConstants.EXCHANGE_ID);

        assertNotNull(actualExchange);
        assertEquals(DbUnitConstants.EVENT_ID, actualExchange.getId());
    }

    @Test
    public void testNotFoundExchangeById() {
        final Exchange actualExchange = databaseHelper.findExchangeById(DbUnitConstants.NOT_EXISTS_OBJECT_ID);

        assertNull(actualExchange);
    }

    @Test
    public void testFoundExchangeWithPaging() {
        FilterOptions fo = DatabseEntityUtils.prepareExchangeFilter();

        final PagedSearchResults<Exchange> result = databaseHelper.findExchangesWithPaging(fo, null, FIRST_RESULT, MAX_RESULTS);

        assertNotNull(result);
        assertEquals(FIRST_RESULT, result.getFirstResult());
        assertEquals(MAX_RESULTS, result.getMaxResults());
        assertTrue(result.getSearchResults().size() > 0);
        final Exchange actualExchange = result.getSearchResults().get(0);
        assertNotNull(actualExchange);
        assertEquals(DbUnitConstants.EXCHANGE_ID, actualExchange.getId());
        assertEquals(DbUnitConstants.EXCHANGE_ID.toString(), actualExchange.getExchangeId());
    }

    @Test
    public void testCreateAccount() throws DuplicateEntryException {
        final Account accountToPersist = DatabseEntityUtils.prepareNewAccount();

        final Account persistedAccount = databaseHelper.addAccount(accountToPersist);

        DatabseEntityUtils.assertNewAccount(persistedAccount);
    }

    @Test
    public void testCreateCustomer() throws DuplicateEntryException {
        final Customer persistedCustomer = databaseHelper.addCustomer(DbUnitConstants.NEW_CUSTOMER_NAME);
        final Customer actualCustomer = databaseHelper.findCustomerById(persistedCustomer.getId());

        Assert.assertEquals(DbUnitConstants.NEW_CUSTOMER_NAME, actualCustomer.getName());
    }

    @Test
    public void testFindAccountByLogin() throws DuplicateEntryException {
        final Account account = databaseHelper.findAccountByLogin(DbUnitConstants.ACCOUNT_LOGIN);

        DatabseEntityUtils.assertExistsAccount(account);
    }

    @Test
    public void testFindAccountByName() throws DuplicateEntryException {
        final Account account = databaseHelper.findAccountByName(DbUnitConstants.ACCOUNT_NAME);

        DatabseEntityUtils.assertExistsAccount(account);
    }
}
