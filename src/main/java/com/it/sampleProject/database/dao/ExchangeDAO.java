package com.it.sampleProject.database.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.it.sampleProject.database.PagedSearchResults;
import com.it.sampleProject.database.entities.CustomerEntity;
import com.it.sampleProject.database.entities.DocumentEntity;
import com.it.sampleProject.database.entities.EventEntity;
import com.it.sampleProject.database.entities.ExchangeEntity;
import com.it.sampleProject.database.search.FilterOptions;
import com.it.sampleProject.database.search.SortOptionsEntry;
import com.it.sampleProject.database.search.enums.ItemType;
import com.it.sampleProject.database.util.DAOUtil;

@Repository
public class ExchangeDAO extends BaseDAO {

    private static final String FIELD_NAME_EXCHANGE_ID = "exchange.id";
    private static final String FIELD_NAME_ID = "id";
    private static final String FIELD_NAME_CREATION_DATE = "creationDate";
    private static final String FIELD_NAME_CUSTOMER_ID = "customer.id";
    private static final String FIELD_NAME_EXCHANGEID = "exchangeId";

    public ExchangeDAO() {
    }

    @SuppressWarnings("unchecked")
    public PagedSearchResults<ExchangeEntity> findExchangesWithPaging(FilterOptions filterOptions, List<SortOptionsEntry> sortOptions, Integer firstResult,
            Integer maxResults) {

        //List<SortOptionsEntry> eventSorts = null;
        List<SortOptionsEntry> exchangeSorts = null;
        //   List<SortOptionsEntry> documentSorts = null;
        if (sortOptions != null) {
            for (SortOptionsEntry so : sortOptions) {
                if (so.getItemType() == ItemType.EXCHANGE) {
                    if (exchangeSorts == null) {
                        exchangeSorts = new ArrayList<SortOptionsEntry>();
                    }
                    exchangeSorts.add(so);
                }
            }
        }

        BigInteger[] exchangeIDArray = null;
        List<BigInteger> exchangeIDsFromEvents = new ArrayList<BigInteger>();
        List<BigInteger> exchangeIDsFromDocuments = new ArrayList<BigInteger>();
        if (firstResult == null) {
            firstResult = new Integer(DEFAULT_FIRST_RESULT);
        }
        if (maxResults == null) {
            maxResults = new Integer(DEFAULT_MAX_RESULTS);
        }
        PagedSearchResults<ExchangeEntity> results = new PagedSearchResults<ExchangeEntity>();
        results.setFirstResult(firstResult);
        results.setMaxResults(maxResults);
        results.setTotalRecords(0);
        results.setSearchResults(new ArrayList<ExchangeEntity>());

        if (DAOUtil.filterOptionsHaveEventFilterEntries(filterOptions)) {
            // If we have search restriction for events, we need to find exchange IDs that match the events restrictions, and then use them in exchanges search
            DetachedCriteria eventCriteria = DetachedCriteria.forClass(EventEntity.class);
            DAOUtil.addFilterOptionsToDetachedCriteria(filterOptions.getEventsFilter(), eventCriteria, FIELD_NAME_CREATION_DATE);
            eventCriteria.setProjection(Projections.property(FIELD_NAME_EXCHANGE_ID));
            exchangeIDsFromEvents = findByCriteria(eventCriteria);
            if (exchangeIDsFromEvents.isEmpty()) {
                // If we have events search conditions, and no exchanges were found that meet them, no need to continue the search
                return results;
            }
        }

        if (DAOUtil.filterOptionsHaveDocumentFilterEntries(filterOptions)) {
            // If we have search restriction for documents, we need to find exchange IDs that match the documents restrictions, and then use them in exchanges
            // search
            DetachedCriteria documentCriteria = DetachedCriteria.forClass(DocumentEntity.class);
            DAOUtil.addFilterOptionsToDetachedCriteria(filterOptions.getDocumentsFilter(), documentCriteria, FIELD_NAME_CREATION_DATE);
            documentCriteria.setProjection(Projections.property(FIELD_NAME_EXCHANGE_ID));
            exchangeIDsFromDocuments = findByCriteria(documentCriteria);
            if (exchangeIDsFromDocuments.isEmpty()) {
                // If we have documents search conditions, and no exchanges were found that meet them, no need to continue the search
                return results;
            }
        }

        if (!exchangeIDsFromEvents.isEmpty()) {
            if (!exchangeIDsFromDocuments.isEmpty()) {
                // If we have sets of exchange IDs both from event and document search fields, we need to find the IDs that match both conditions
                exchangeIDsFromEvents.retainAll(exchangeIDsFromDocuments);
                if (exchangeIDsFromEvents.isEmpty()) {
                    return results;
                }
            }
            exchangeIDArray = exchangeIDsFromEvents.toArray(new BigInteger[0]);
        } else if (!exchangeIDsFromDocuments.isEmpty()) {
            exchangeIDArray = exchangeIDsFromDocuments.toArray(new BigInteger[0]);
        }

        DetachedCriteria exchangeCriteria = DetachedCriteria.forClass(ExchangeEntity.class);
        DetachedCriteria exchangeCriteriaWithoutSort = DetachedCriteria.forClass(ExchangeEntity.class);

        DAOUtil.addFilterOptionsToDetachedCriteria(filterOptions.getExchangesFilter(), exchangeCriteria, FIELD_NAME_CREATION_DATE);
        if (exchangeIDArray != null && exchangeIDArray.length > 0) {
            exchangeCriteria.add(Restrictions.in(FIELD_NAME_ID, exchangeIDArray));
            exchangeCriteriaWithoutSort.add(Restrictions.in(FIELD_NAME_ID, exchangeIDArray));
        }
        //Customer search--------------------------------------------------------


        if (DAOUtil.filterOptionsHaveCustomerFilterEntries(filterOptions)) {
            // If we have search restriction for exchanges, we need to find exchange IDs that match the exchanges restrictions, and then use them in documents search
            DetachedCriteria customerCriteria = DetachedCriteria.forClass(CustomerEntity.class);
            DAOUtil.addFilterOptionsToDetachedCriteria(filterOptions.getCustomersFilter(), customerCriteria, null);
            customerCriteria.setProjection(Projections.property(FIELD_NAME_ID));
            List<BigInteger> customersIDs = findByCriteria(customerCriteria);
            if (customersIDs.isEmpty()) {
                // If we have documents search conditions, and no exchanges were found that meet them, no need to continue the search
                return results;
            }
            exchangeCriteria.add(Restrictions.in(FIELD_NAME_CUSTOMER_ID, customersIDs.toArray(new BigInteger[0])));
            exchangeCriteriaWithoutSort.add(Restrictions.in(FIELD_NAME_CUSTOMER_ID, customersIDs.toArray(new BigInteger[0])));
        }

        DAOUtil.addSortOptionsToDetachedCriteria(exchangeSorts, exchangeCriteria);

        //-----------------------------------------------------------------------
        results = getPagedSearchResults(exchangeCriteria, exchangeCriteriaWithoutSort, firstResult, maxResults, getSessionFactory());
        return results;
    }

    public ExchangeEntity findExchangeEntityById(BigInteger id) {
        ExchangeEntity exchangeEntity = (ExchangeEntity) getCurrentSession().get(ExchangeEntity.class, id);
        return exchangeEntity;
    }

    @SuppressWarnings("unchecked")
	public ExchangeEntity findExchangeEntityByExchangeId(String exchange_id) {
        DetachedCriteria exchCriteria = DetachedCriteria.forClass(ExchangeEntity.class);
        exchCriteria.add(Restrictions.eq(FIELD_NAME_EXCHANGEID, exchange_id));
        //customerCriteria.getExecutableCriteria(HibernateUtil.getSession());
        List<ExchangeEntity> entities = findByCriteria(exchCriteria);
        ExchangeEntity exchangeEntity = null;
        if (entities != null && !entities.isEmpty()) {
            exchangeEntity = entities.get(0);
        }
        return exchangeEntity;
    }
}
