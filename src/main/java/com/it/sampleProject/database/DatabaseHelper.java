package com.it.sampleProject.database;

import com.it.database.exceptions.DuplicateEntryException;
import com.it.sampleProject.common.Constants;
import com.it.sampleProject.database.entities.*;
import com.it.sampleProject.database.model.*;
import com.it.sampleProject.database.search.FilterOptions;
import com.it.sampleProject.database.search.SortOptionsEntry;
import com.it.sampleProject.database.dao.*;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("databaseHelper")
public class DatabaseHelper {
    Logger logger = Logger.getLogger(DatabaseHelper.class);

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private EventDAO eventDAO;
    @Autowired
    private DocumentDAO documentDAO;
    @Autowired
    private ExchangeDAO exchangeDAO;
    @Autowired
    private AccountTypeDAO accountTypeDAO;
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public AccountTypeDAO getAccountTypeDAO() {
        return accountTypeDAO;
    }

    public void setAccountTypeDAO(AccountTypeDAO accountTypeDAO) {
        this.accountTypeDAO = accountTypeDAO;
    }

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public void setDocumentDAO(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    public void setExchangeDAO(ExchangeDAO exchangeDAO) {
        this.exchangeDAO = exchangeDAO;
    }

    public EventDAO getEventDAO() {
        return eventDAO;
    }

    public DocumentDAO getDocumentDAO() {
        return documentDAO;
    }

    public ExchangeDAO getExchangeDAO() {
        return exchangeDAO;
    }

    public DatabaseHelper() {
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateAccount(Account account) throws DuplicateEntryException {
        AccountEntity accountEntity = accountDAO.findAccountEntityById(account.getId());
        if (accountEntity != null) {
            accountEntity = Account.mapToEntity(account, accountEntity);
            try {
                accountDAO.updateAccountEntity(accountEntity);
            } catch (ConstraintViolationException ex) {
                // failed to insert due to duplicate key
                // need to close current session after exception
                //  HibernateUtil.abortSession(HibernateUtil.getSessionFactory().getCurrentSession());
                throw new DuplicateEntryException("Duplicate Account entry");
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAccount(String name) {
        AccountEntity accountEntity = accountDAO.findAccountEntityByName(name);
        if (accountEntity != null) {
            accountDAO.deleteAccountEntity(accountEntity);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account addAccount(Account account) throws DuplicateEntryException {
        AccountEntity accountEntity = Account.mapToEntity(account);
        try {
            accountDAO.saveAccountEntity(accountEntity);
        } catch (ConstraintViolationException ex) {
            // failed to insert due to duplicate key
            // need to close current session after exception
            // HibernateUtil.abortSession(HibernateUtil.getSessionFactory().getCurrentSession());
            throw new DuplicateEntryException("Duplicate Account entry");
        }
        account.setId(accountEntity.getId());
        return account;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account addAccount(String name, String login, String passwordHash) {
        AccountEntity accountEntity = accountDAO.addAccountEntity(name, login, passwordHash);
        Account account = Account.mapFromEntity(accountEntity);
        return account;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ArrayList<Account> getAccounts() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        ArrayList<AccountEntity> accountEntities = accountDAO.getAccountEntities();
        for (AccountEntity accountEntity : accountEntities) {
            accounts.add(Account.mapFromEntity(accountEntity));
        }
        return accounts;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        ArrayList<CustomerEntity> customerEntities = customerDAO.getCustomerEntities();
        for (CustomerEntity customerEntity : customerEntities) {
            customers.add(Customer.mapFromEntity(customerEntity));
        }
        return customers;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomer(String name) {
        CustomerEntity customerEntity = customerDAO.findCustomerEntityByName(name);
        if (customerEntity != null) {
            customerDAO.deleteCustomerEntity(customerEntity);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = DuplicateEntryException.class)
    public Customer addCustomer(String name) throws DuplicateEntryException {
        CustomerEntity customerEntity = null;
        try {
            customerEntity = customerDAO.addCustomerEntity(name);
        } catch (ConstraintViolationException ex) {
            // failed to insert due to duplicate key
            // need to close current session after exception
            //HibernateUtil.abortSession(HibernateUtil.getSessionFactory().getCurrentSession());
            throw new DuplicateEntryException("Duplicate Customer entry");
        }
        if (customerEntity != null) {
            Customer customer = Customer.mapFromEntity(customerEntity);
            return customer;
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account findAccountByLogin(String login) {
        AccountEntity accountEntity = accountDAO.findAccountEntityByLogin(login);
        if (accountEntity != null) {
            Account account = Account.mapFromEntity(accountEntity);
            return account;
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account findAccountByName(String name) {
        AccountEntity accountEntity = accountDAO.findAccountEntityByName(name);
        if (accountEntity != null) {
            Account account = Account.mapFromEntity(accountEntity);
            return account;
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Customer findCustomerByName(String name) {
        CustomerEntity customerEntity = customerDAO.findCustomerEntityByName(name);
        if (customerEntity != null) {
            Customer customer = Customer.mapFromEntity(customerEntity);
            return customer;
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Customer findCustomerById(BigInteger id) {
        CustomerEntity customerEntity = customerDAO.findCustomerEntityById(id);
        if (customerEntity != null) {
            Customer customer = Customer.mapFromEntity(customerEntity);
            return customer;
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Event findEventById(BigInteger id) {
        EventEntity eventEntity = eventDAO.findEventEntityById(id);
        if (eventEntity != null) {
            Event event = Event.mapFromEntity(eventEntity);
            return event;
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PagedSearchResults<Event> findEventsWithPaging(FilterOptions filterOptions, List<SortOptionsEntry> sortOptions, Integer firstResult,
                                                          Integer maxResults) {
        PagedSearchResults<EventEntity> pagedEntities = eventDAO.findEventsWithPaging(filterOptions, sortOptions, firstResult, maxResults);
        PagedSearchResults<Event> pagedEvents = new PagedSearchResults<Event>();
        pagedEvents.setFirstResult(pagedEntities.getFirstResult());
        pagedEvents.setMaxResults(pagedEntities.getMaxResults());
        pagedEvents.setTotalRecords(pagedEntities.getTotalRecords());
        List<Event> events = new ArrayList<Event>();
        if (pagedEntities.getSearchResults() != null && pagedEntities.getSearchResults().size() > 0) {
            for (EventEntity eventEntity : pagedEntities.getSearchResults()) {
                events.add(Event.mapFromEntity(eventEntity));
            }
        }
        pagedEvents.setSearchResults(events);
        return pagedEvents;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Document findDocumentById(BigInteger id) {
        DocumentEntity documentEntity = documentDAO.findDocumentEntityById(id);
        if (documentEntity != null) {
            Document document = Document.mapFromEntity(documentEntity);
            return document;
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PagedSearchResults<Document> findDocumentsWithPaging(FilterOptions filterOptions, List<SortOptionsEntry> sortOptions, Integer firstResult,
                                                                Integer maxResults) {
        PagedSearchResults<DocumentEntity> pagedEntities = documentDAO.findDocumentsWithPaging(filterOptions, sortOptions, firstResult, maxResults);
        PagedSearchResults<Document> pagedDocuments = new PagedSearchResults<Document>();
        pagedDocuments.setFirstResult(pagedEntities.getFirstResult());
        pagedDocuments.setMaxResults(pagedEntities.getMaxResults());
        pagedDocuments.setTotalRecords(pagedEntities.getTotalRecords());
        List<Document> documents = new ArrayList<Document>();
        if (pagedEntities.getSearchResults() != null && pagedEntities.getSearchResults().size() > 0) {
            for (DocumentEntity documentEntity : pagedEntities.getSearchResults()) {
                documents.add(Document.mapFromEntity(documentEntity));
            }
        }
        pagedDocuments.setSearchResults(documents);
        return pagedDocuments;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Exchange findExchangeById(BigInteger id) {
        ExchangeEntity exchangeEntity = exchangeDAO.findExchangeEntityById(id);
        if (exchangeEntity != null) {
            Exchange exchange = Exchange.mapFromEntity(exchangeEntity);
            return exchange;
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ExchangeEntity findExchangeEntityById(BigInteger id) {
        ExchangeEntity exchangeEntity = exchangeDAO.findExchangeEntityById(id);
        return exchangeEntity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ExchangeEntity findExchangeEntityByExchangeId(String exchange_id) {
        ExchangeEntity exchangeEntity = exchangeDAO.findExchangeEntityByExchangeId(exchange_id);
        return exchangeEntity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PagedSearchResults<Exchange> findExchangesWithPaging(FilterOptions filterOptions, List<SortOptionsEntry> sortOptions, Integer firstResult,
                                                                Integer maxResults) {
        PagedSearchResults<ExchangeEntity> pagedEntities = exchangeDAO.findExchangesWithPaging(filterOptions, sortOptions, firstResult, maxResults);
        PagedSearchResults<Exchange> pagedExchanges = new PagedSearchResults<Exchange>();
        pagedExchanges.setFirstResult(pagedEntities.getFirstResult());
        pagedExchanges.setMaxResults(pagedEntities.getMaxResults());
        pagedExchanges.setTotalRecords(pagedEntities.getTotalRecords());
        List<Exchange> exchanges = new ArrayList<Exchange>();
        if (pagedEntities.getSearchResults() != null && pagedEntities.getSearchResults().size() > 0) {
            for (ExchangeEntity exchangeEntity : pagedEntities.getSearchResults()) {
                exchanges.add(Exchange.mapFromEntity(exchangeEntity));
            }
        }
        pagedExchanges.setSearchResults(exchanges);
        return pagedExchanges;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ArrayList<AccountType> getAccountTypes() {
        ArrayList<AccountType> accountTypes = new ArrayList<AccountType>();
        ArrayList<AccountTypeEntity> accountTypeEntities = accountTypeDAO.getAccountTypeEntities();
        for (AccountTypeEntity accountTypeEntity : accountTypeEntities) {
            accountTypes.add(AccountType.mapFromEntity(accountTypeEntity));
        }
        return accountTypes;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Set<Customer> getCustomers(Account account) {
        Set<Customer> customers;
        if (account.getAccountType().isAdmin()) {
            customers = new HashSet<Customer>(getCustomers());
        } else {
            customers = account.getCustomers();
        }
        return customers;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateDocumentArchivePath(String camelExchangeId, String archivePath) {
        try {
            DocumentEntity doc = getFirstDocumentByCamelExchangeId(camelExchangeId);
            if (doc != null) {
                documentDAO.updateDocumentArchivePath(doc.getId(), archivePath);
            } else {
                logger.debug("No document found with exchangeId:" + camelExchangeId + " and version 0");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateDocumentFailurePath(String camelExchangeId, String failurePath) {
        try {
            DocumentEntity doc = getFirstDocumentByCamelExchangeId(camelExchangeId);
            if (doc != null) {
                documentDAO.updateDocumentFailurePath(doc.getId(), failurePath);
            } else {
                logger.debug("No document found with exchangeId:" + camelExchangeId + " and version 0");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public DocumentEntity getFirstDocumentByCamelExchangeId(String camelExchangeId) {
        ExchangeEntity entity = findExchangeEntityByExchangeId(camelExchangeId);
        return documentDAO.findDocumentEntityByExchangeIdAndDocumentVersion(entity.getId(), Constants.IDS_DEFAULT_DOCUMENT_VERSION);
    }
}
