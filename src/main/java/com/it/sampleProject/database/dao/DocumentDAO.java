package com.it.sampleProject.database.dao;

import com.it.sampleProject.database.PagedSearchResults;
import com.it.sampleProject.database.entities.CustomerEntity;
import com.it.sampleProject.database.entities.DocumentEntity;
import com.it.sampleProject.database.entities.EventEntity;
import com.it.sampleProject.database.entities.ExchangeEntity;
import com.it.sampleProject.database.search.FilterOptions;
import com.it.sampleProject.database.search.SortOptionsEntry;
import com.it.sampleProject.database.search.enums.ItemType;
import com.it.sampleProject.database.util.DAOUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DocumentDAO extends BaseDAO {

    private static final String FIELD_NAME_ID = "id";
    private static final String FIELD_NAME_CREATION_DATE = "creationDate";
    private static final String FIELD_NAME_DOCUMENT_ID = "document.id";
    private static final String FIELD_NAME_DOCUMENT_EXCHANGE_ID = "exchange.id";
    private static final String FIELD_NAME_DOCUMENT_DOCUMENT_VERSION = "documentVersion";
    private static final String FIELD_NAME_EXCHANGE_ID = "exchange.id";
    private static final String FIELD_NAME_CUSTOMER_ID = "customer.id";

    public DocumentDAO() {
    }

    @SuppressWarnings("unchecked")
    public PagedSearchResults<DocumentEntity> findDocumentsWithPaging(FilterOptions filterOptions, List<SortOptionsEntry> sortOptions, Integer firstResult,
                                                                      Integer maxResults) {
        List<SortOptionsEntry> documentSorts = null;
        if (sortOptions != null) {
            for (SortOptionsEntry so : sortOptions) {
                if (so.getItemType() == ItemType.DOCUMENT) {
                    if (documentSorts == null) {
                        documentSorts = new ArrayList<SortOptionsEntry>();
                    }
                    documentSorts.add(so);
                }
            }
        }

        if (firstResult == null) {
            firstResult = new Integer(DEFAULT_FIRST_RESULT);
        }
        if (maxResults == null) {
            maxResults = new Integer(DEFAULT_MAX_RESULTS);
        }
        PagedSearchResults<DocumentEntity> results = new PagedSearchResults<DocumentEntity>();
        results.setFirstResult(firstResult);
        results.setMaxResults(maxResults);
        results.setTotalRecords(0);
        results.setSearchResults(new ArrayList<DocumentEntity>());

        DetachedCriteria documentCriteria = DetachedCriteria.forClass(DocumentEntity.class);
        DetachedCriteria documentCriteriaWithoutSort = DetachedCriteria.forClass(DocumentEntity.class);

        DAOUtil.addFilterOptionsToDetachedCriteria(filterOptions.getDocumentsFilter(), documentCriteria, FIELD_NAME_CREATION_DATE);
        DAOUtil.addFilterOptionsToDetachedCriteria(filterOptions.getDocumentsFilter(), documentCriteriaWithoutSort, FIELD_NAME_CREATION_DATE);

        if (DAOUtil.filterOptionsHaveEventFilterEntries(filterOptions)) {
            // If we have search restriction for events, we need to find document IDs that match the events restrictions, and then use them in documents search
            DetachedCriteria eventCriteria = DetachedCriteria.forClass(EventEntity.class);
            DAOUtil.addFilterOptionsToDetachedCriteria(filterOptions.getEventsFilter(), eventCriteria, FIELD_NAME_CREATION_DATE);
            eventCriteria.setProjection(Projections.property(FIELD_NAME_DOCUMENT_ID));
            List<BigInteger> eventIDs = findByCriteria(eventCriteria);
            if (eventIDs.isEmpty()) {
                // If we have documents search conditions, and no events were found that meet them, no need to continue the search
                return results;
            }
            documentCriteria.add(Restrictions.in(FIELD_NAME_ID, eventIDs.toArray(new BigInteger[0])));
            documentCriteriaWithoutSort.add(Restrictions.in(FIELD_NAME_ID, eventIDs.toArray(new BigInteger[0])));

        }

        if (DAOUtil.filterOptionsHaveExchangeFilterEntries(filterOptions)) {
            // If we have search restriction for exchanges, we need to find exchange IDs that match the exchanges restrictions, and then use them in documents search
            DetachedCriteria exchangeCriteria = DetachedCriteria.forClass(ExchangeEntity.class);
            DAOUtil.addFilterOptionsToDetachedCriteria(filterOptions.getExchangesFilter(), exchangeCriteria, FIELD_NAME_CREATION_DATE);
            exchangeCriteria.setProjection(Projections.property(FIELD_NAME_ID));
            List<BigInteger> exchangeIDs = findByCriteria(exchangeCriteria);
            if (exchangeIDs.isEmpty()) {
                // If we have documents search conditions, and no exchanges were found that meet them, no need to continue the search
                return results;
            }
            documentCriteria.add(Restrictions.in(FIELD_NAME_EXCHANGE_ID, exchangeIDs.toArray(new BigInteger[0])));
            documentCriteriaWithoutSort.add(Restrictions.in(FIELD_NAME_EXCHANGE_ID, exchangeIDs.toArray(new BigInteger[0])));
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
            documentCriteria.add(Restrictions.in(FIELD_NAME_CUSTOMER_ID, customersIDs.toArray(new BigInteger[0])));
            documentCriteriaWithoutSort.add(Restrictions.in(FIELD_NAME_CUSTOMER_ID, customersIDs.toArray(new BigInteger[0])));
        }

        DAOUtil.addSortOptionsToDetachedCriteria(documentSorts, documentCriteria);
        //-----------------------------------------------------------------------

        results = getPagedSearchResults(documentCriteria, documentCriteriaWithoutSort, firstResult, maxResults, getSessionFactory());
        return results;
    }

    public DocumentEntity findDocumentEntityById(BigInteger id) {
        DocumentEntity documentEntity = (DocumentEntity) getCurrentSession().get(DocumentEntity.class, id);
        return documentEntity;
    }

    @SuppressWarnings("unchecked")
    public DocumentEntity findDocumentEntityByExchangeIdAndDocumentVersion(BigInteger exchangeEntityId, String documentVersion) {
        DetachedCriteria docCriteria = DetachedCriteria.forClass(DocumentEntity.class);
        docCriteria.add(Restrictions.eq(FIELD_NAME_DOCUMENT_EXCHANGE_ID, exchangeEntityId));
        docCriteria.add(Restrictions.eq(FIELD_NAME_DOCUMENT_DOCUMENT_VERSION, documentVersion));
        //customerCriteria.getExecutableCriteria(HibernateUtil.getSession());
        List<DocumentEntity> entities = findByCriteria(docCriteria);
        DocumentEntity documentEntity = null;
        if (entities != null && !entities.isEmpty()) {
            documentEntity = entities.get(0);
        }
        return documentEntity;
    }

    @SuppressWarnings("unchecked")
    public List<DocumentEntity> findDocumentEntitiesByExchangeId(BigInteger exchangeEntityId) {
        DetachedCriteria docCriteria = DetachedCriteria.forClass(DocumentEntity.class);
        docCriteria.add(Restrictions.eq(FIELD_NAME_DOCUMENT_EXCHANGE_ID, exchangeEntityId));
        return findByCriteria(docCriteria);
    }


    public void updateDocumentArchivePath(BigInteger id, String archivePath) {
        DocumentEntity documentEntity = findDocumentEntityById(id);
        documentEntity.setArchivePath(archivePath);
        getCurrentSession().saveOrUpdate(documentEntity);
    }

    public void updateDocumentFailurePath(BigInteger id, String failurePath) {
        DocumentEntity documentEntity = findDocumentEntityById(id);
        documentEntity.setFailPath(failurePath);
        getCurrentSession().saveOrUpdate(documentEntity);
    }
}
