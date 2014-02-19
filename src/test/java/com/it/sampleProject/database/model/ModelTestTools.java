package com.it.sampleProject.database.model;

import java.math.BigInteger;
import java.util.Date;

import com.it.sampleProject.database.entities.CustomerEntity;
import com.it.sampleProject.database.entities.DocumentEntity;
import com.it.sampleProject.database.entities.ExchangeEntity;

public class ModelTestTools {

    public static final BigInteger CUSTOMER_ID = new BigInteger("123");
    public static final String CUSTOMER_NAME = "customer id";
    public static final String EXCHANGE_ID = "exchange id";
    public static final BigInteger ID = BigInteger.TEN;
    public static final String ROUTE_ID = "route id";
    public static final Date TIMESTAMP = new Date();
    public static final String BODY = "body";
    public static final String DOCUMENT_VERSION = "1";
    public static final String BODY_TYPE = "body type";
    public static final String FILE_PATH = "file path";
    public static final String MESSAGE = "message";

    public static Customer prepareCustomer() {
        Customer customer = new Customer();
        customer.setId(CUSTOMER_ID);
        customer.setName(CUSTOMER_NAME);
        return customer;
    }

    public static CustomerEntity prepareCustomerEntity() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(CUSTOMER_ID);
        customerEntity.setName(CUSTOMER_NAME);
        return customerEntity;
    }

    public static Exchange prepareExchange() {
        Exchange exchange = new Exchange();

        exchange.setCustomer(prepareCustomer());
        exchange.setExchangeId(EXCHANGE_ID);
        exchange.setId(ID);
        exchange.setRouteId(ROUTE_ID);
        exchange.setCreationDate(TIMESTAMP);
        return exchange;
    }

    public static ExchangeEntity prepareExchangeEntity() {
        ExchangeEntity entity = new ExchangeEntity();
        entity.setCustomer(prepareCustomerEntity());
        entity.setExchangeId(EXCHANGE_ID);
        entity.setId(ID);
        entity.setRouteId(ROUTE_ID);
        entity.setCreationDate(TIMESTAMP);
        return entity;
    }

    public static Document prepareDocument(Exchange exchange) {
        Document doc = new Document();
        doc.setBody(BODY);
        doc.setDocumentVersion(DOCUMENT_VERSION);
        doc.setBodyType(BODY_TYPE);
        doc.setExchange(exchange);
        doc.setFilePath(FILE_PATH);
        doc.setId(ID);
        doc.setCreationDate(TIMESTAMP);
        return doc;
    }

    public static DocumentEntity prepareDocumentEntity(ExchangeEntity exchangeEntity) {
        DocumentEntity de = new DocumentEntity();
        de.setBody(BODY);
        de.setDocumentVersion(DOCUMENT_VERSION);
        de.setBodyType(BODY_TYPE);
        de.setExchange(exchangeEntity);
        de.setFilePath(FILE_PATH);
        de.setId(ID);
        de.setCreationDate(TIMESTAMP);
        return de;
    }
}
