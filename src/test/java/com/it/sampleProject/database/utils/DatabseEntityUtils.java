package com.it.sampleProject.database.utils;

import com.it.sampleProject.database.entities.AccountEntity;
import com.it.sampleProject.database.model.Account;
import com.it.sampleProject.database.model.AccountType;
import com.it.sampleProject.database.search.CriteriaEntry;
import com.it.sampleProject.database.search.Filter;
import com.it.sampleProject.database.search.FilterOptions;
import com.it.sampleProject.database.search.enums.ConditionType;
import com.it.sampleProject.database.consts.DbUnitConstants;
import junit.framework.Assert;

import java.math.BigInteger;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Class that contains helper methods for work with test entities.
 *
 * @author Igor Ivaniuk
 */
public class DatabseEntityUtils {
    @SuppressWarnings("rawtypes")
    public static FilterOptions prepareEventFilter() {
        FilterOptions fo = new FilterOptions();
        Filter eventFilter = new Filter();
        HashMap<String, CriteriaEntry> eventFilterEntries = new HashMap<String, CriteriaEntry>();
        CriteriaEntry<String> eventCrit = new CriteriaEntry<String>();
        eventCrit.setValue(DbUnitConstants.EVENT_MESSAGE);
        eventCrit.setConditionType(ConditionType.LIKE);
        eventFilterEntries.put("message", eventCrit);
        eventFilter.setFilterEntries(eventFilterEntries);
        fo.setEventsFilter(eventFilter);
        return fo;
    }

    @SuppressWarnings("rawtypes")
    public static FilterOptions prepareDocumentFilterByBody() {
        FilterOptions fo = new FilterOptions();
        Filter docFilter = new Filter();
        HashMap<String, CriteriaEntry> docFilterEntries = new HashMap<String, CriteriaEntry>();
        CriteriaEntry<String> docCrit = new CriteriaEntry<String>();
        docCrit.setValue(DbUnitConstants.DOCUMENT_BODY);
        docCrit.setConditionType(ConditionType.LIKE);
        docFilterEntries.put("body", docCrit);
        docFilter.setFilterEntries(docFilterEntries);
        fo.setDocumentsFilter(docFilter);
        return fo;
    }

    @SuppressWarnings("rawtypes")
    public static FilterOptions prepareDocumentFilterByBodyAndVersion() {
        FilterOptions fo = new FilterOptions();
        Filter documentFilter = new Filter();
        HashMap<String, CriteriaEntry> filterEntries = new HashMap<String, CriteriaEntry>();
        CriteriaEntry<String> docBody = new CriteriaEntry<String>();
        docBody.setConditionType(ConditionType.EQUALS);
        docBody.setValue(DbUnitConstants.DOCUMENT_BODY);
        filterEntries.put("body", docBody);
        CriteriaEntry<String> docVersion = new CriteriaEntry<String>();
        docVersion.setConditionType(ConditionType.EQUALS);
        docVersion.setValue(DbUnitConstants.DOCUMENT_VERSION);
        filterEntries.put("documentVersion", docVersion);
        documentFilter.setFilterEntries(filterEntries);
        fo.setDocumentsFilter(documentFilter);
        return fo;
    }

    @SuppressWarnings("rawtypes")
    public static FilterOptions prepareExchangeFilter() {
        FilterOptions fo = new FilterOptions();
        Filter exchangeFilter = new Filter();
        HashMap<String, CriteriaEntry> exchangeFilterEntries = new HashMap<String, CriteriaEntry>();
        CriteriaEntry<String> exchangeCrit = new CriteriaEntry<String>();
        exchangeCrit.setValue(DbUnitConstants.EXCHANGE_ID.toString());
        exchangeCrit.setConditionType(ConditionType.EQUALS);
        exchangeFilterEntries.put("exchangeId", exchangeCrit);
        exchangeFilter.setFilterEntries(exchangeFilterEntries);
        fo.setExchangesFilter(exchangeFilter);
        return fo;
    }

    public static void assertExistsAccount(Account actualAccount) {
        assertNotNull(actualAccount);
        assertEquals(DbUnitConstants.ACCOUNT_NAME, actualAccount.getName());
        assertEquals(DbUnitConstants.ACCOUNT_LOGIN, actualAccount.getLogin());
        assertEquals(DbUnitConstants.ACCOUNT_PASSWORD_HASH, actualAccount.getPasswordHash());
    }

    public static void assertExistsAccount(AccountEntity actualAccount) {
        assertNotNull(actualAccount);
        Assert.assertEquals(DbUnitConstants.ACCOUNT_NAME, actualAccount.getName());
        Assert.assertEquals(DbUnitConstants.ACCOUNT_LOGIN, actualAccount.getLogin());
        Assert.assertEquals(DbUnitConstants.ACCOUNT_PASSWORD_HASH, actualAccount.getPasswordHash());
    }

    public static void assertNewAccount(Account actualAccount) {
        assertNotNull(actualAccount);
        assertEquals(DbUnitConstants.NEW_ACCOUNT_NAME, actualAccount.getName());
        assertEquals(DbUnitConstants.NEW_ACCOUNT_LOGIN, actualAccount.getLogin());
        assertEquals(DbUnitConstants.NEW_ACCOUNT_PASSWORD_HASH, actualAccount.getPasswordHash());
    }

    public static void assertNewAccount(AccountEntity actualAccount) {
        assertNotNull(actualAccount);
        Assert.assertEquals(DbUnitConstants.NEW_ACCOUNT_NAME, actualAccount.getName());
        Assert.assertEquals(DbUnitConstants.NEW_ACCOUNT_LOGIN, actualAccount.getLogin());
        Assert.assertEquals(DbUnitConstants.NEW_ACCOUNT_PASSWORD_HASH, actualAccount.getPasswordHash());
    }

    public static Account prepareNewAccount() {
        final Account accountToPersist = new Account();
        accountToPersist.setLogin(DbUnitConstants.NEW_ACCOUNT_LOGIN);
        accountToPersist.setName(DbUnitConstants.NEW_ACCOUNT_NAME);
        accountToPersist.setPasswordHash(DbUnitConstants.NEW_ACCOUNT_PASSWORD_HASH);

        final AccountType accountType = new AccountType();
        accountType.setId(BigInteger.valueOf(1));
        accountToPersist.setAccountType(accountType);

        return accountToPersist;
    }
}
