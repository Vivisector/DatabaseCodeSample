package com.it.sampleProject.database.dao;

import com.it.sampleProject.BaseIdsDBUnitTest;
import com.it.sampleProject.database.PagedSearchResults;
import com.it.sampleProject.database.entities.ExchangeEntity;
import com.it.sampleProject.database.search.FilterOptions;
import com.it.sampleProject.database.consts.DbUnitConstants;
import com.it.sampleProject.database.utils.DatabseEntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author Igor Ivaniuk, Dmytro Stepanyshchenko
 */
public class ExchangeDAOTest extends BaseIdsDBUnitTest {

    @Autowired
    private ExchangeDAO exchangeDAO;

    @Test
    public void testFindEventById() {
        final ExchangeEntity result = exchangeDAO.findExchangeEntityById(DbUnitConstants.EXCHANGE_ID);
        assertNotNull(result);
        assertEquals(DbUnitConstants.EXCHANGE_ID, result.getId());
    }

    @Test
    public void testFindEventWithPaging() {
        FilterOptions fo = DatabseEntityUtils.prepareExchangeFilter();

        final PagedSearchResults<ExchangeEntity> resultList = exchangeDAO.findExchangesWithPaging(fo, null, null, null);

        assertNotNull(resultList);
        assertTrue(resultList.getTotalRecords() > 0);
        final ExchangeEntity result = resultList.getSearchResults().get(0);
        assertEquals(DbUnitConstants.EXCHANGE_ID, result.getId());
    }
}
