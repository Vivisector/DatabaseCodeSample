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
public class EventDAO extends BaseDAO {

    private static final String FIELD_NAME_ID = "id";
    private static final String FIELD_NAME_CREATION_DATE = "creationDate";
    private static final String FIELD_NAME_DOCUMENT_ID = "document.id";
    private static final String FIELD_NAME_EXCHANGE_ID = "exchange.id";
    private static final String FIELD_NAME_CUSTOMER_ID = "customer.id";

    public EventDAO() {
    }

    @SuppressWarnings("unchecked")
	public PagedSearchResults<EventEntity> findEventsWithPaging(FilterOptions filterOptions, List<SortOptionsEntry> sortOptions, Integer firstResult,
            Integer maxResults) {
        List<SortOptionsEntry> eventSorts = null;
        if (sortOptions != null) {
            for (SortOptionsEntry so : sortOptions) {
                if (so.getItemType() == ItemType.EVENT) {
                    if (eventSorts == null) {
                        eventSorts = new ArrayList<SortOptionsEntry>();
                    }
                    eventSorts.add(so);
                }
            }
        }

        if (firstResult == null) {
            firstResult = new Integer(DEFAULT_FIRST_RESULT);
        }
        if (maxResults == null) {
            maxResults = new Integer(DEFAULT_MAX_RESULTS);
        }

        PagedSearchResults<EventEntity> results = new PagedSearchResults<EventEntity>();
        results.setFirstResult(firstResult);
        results.setMaxResults(maxResults);
        results.setTotalRecords(0);
        results.setSearchResults(new ArrayList<EventEntity>());

        DetachedCriteria eventCriteria = DetachedCriteria.forClass(EventEntity.class);
        DetachedCriteria eventCriteriaWithoutSort = DetachedCriteria.forClass(EventEntity.class);

        DAOUtil.addFilterOptionsToDetachedCriteria(filterOptions.getEventsFilter(), eventCriteria, FIELD_NAME_CREATION_DATE);
        DAOUtil.addFilterOptionsToDetachedCriteria(filterOptions.getEventsFilter(), eventCriteriaWithoutSort, FIELD_NAME_CREATION_DATE);

        if (DAOUtil.filterOptionsHaveDocumentFilterEntries(filterOptions)) {
            // If we have search restriction for documents, we need to find document IDs that match the documents restrictions, and then use them in events search
            DetachedCriteria documentCriteria = DetachedCriteria.forClass(DocumentEntity.class);
            DAOUtil.addFilterOptionsToDetachedCriteria(filterOptions.getDocumentsFilter(), documentCriteria, FIELD_NAME_CREATION_DATE);
            documentCriteria.setProjection(Projections.property(FIELD_NAME_ID));
            List<BigInteger> documentIDs = findByCriteria(documentCriteria);
            if (documentIDs.isEmpty()) {
                // If we have events search conditions, and no documents were found that meet them, no need to continue the search
                return results;
            } else {
                eventCriteria.add(Restrictions.in(FIELD_NAME_DOCUMENT_ID, documentIDs.toArray(new BigInteger[0])));
                eventCriteriaWithoutSort.add(Restrictions.in(FIELD_NAME_DOCUMENT_ID, documentIDs.toArray(new BigInteger[0])));
            }
        }

        if (DAOUtil.filterOptionsHaveExchangeFilterEntries(filterOptions)) {
            // If we have search restriction for exchanges, we need to find exchange IDs that match the exchanges restrictions, and then use them in events search
            DetachedCriteria exchangeCriteria = DetachedCriteria.forClass(ExchangeEntity.class);
            DAOUtil.addFilterOptionsToDetachedCriteria(filterOptions.getExchangesFilter(), exchangeCriteria, FIELD_NAME_CREATION_DATE);
            exchangeCriteria.setProjection(Projections.property(FIELD_NAME_ID));
            List<BigInteger> exchangeIDs = findByCriteria(exchangeCriteria);
            if (exchangeIDs.isEmpty()) {
                // If we have events search conditions, and no exchanges were found that meet them, no need to continue the search
                return results;
            } else {
                eventCriteria.add(Restrictions.in(FIELD_NAME_EXCHANGE_ID, exchangeIDs.toArray(new BigInteger[0])));
                eventCriteriaWithoutSort.add(Restrictions.in(FIELD_NAME_EXCHANGE_ID, exchangeIDs.toArray(new BigInteger[0])));
            }
        }

        //Customer search--------------------------------------------------------


        if (DAOUtil.filterOptionsHaveCustomerFilterEntries(filterOptions)) {
            // If we have search restriction for customers, we need to find customer IDs that match the customers restrictions, and then use them in events search
            DetachedCriteria customerCriteria = DetachedCriteria.forClass(CustomerEntity.class);
            DAOUtil.addFilterOptionsToDetachedCriteria(filterOptions.getCustomersFilter(), customerCriteria, null);
            customerCriteria.setProjection(Projections.property(FIELD_NAME_ID));
            List<BigInteger> customersIDs = findByCriteria(customerCriteria);
            if (customersIDs.isEmpty()) {
                // If we have documents search conditions, and no exchanges were found that meet them, no need to continue the search
                return results;
            }
            eventCriteria.add(Restrictions.in(FIELD_NAME_CUSTOMER_ID, customersIDs.toArray(new BigInteger[0])));
            eventCriteriaWithoutSort.add(Restrictions.in(FIELD_NAME_CUSTOMER_ID, customersIDs.toArray(new BigInteger[0])));
        }

        DAOUtil.addSortOptionsToDetachedCriteria(eventSorts, eventCriteria);

        //-----------------------------------------------------------------------

        results = getPagedSearchResults(eventCriteria, eventCriteriaWithoutSort, firstResult, maxResults, getSessionFactory());
        return results;
    }

    public EventEntity findEventEntityById(BigInteger id) {
        EventEntity eventEntity = (EventEntity) getCurrentSession().get(EventEntity.class, id);
        return eventEntity;
    }
}
