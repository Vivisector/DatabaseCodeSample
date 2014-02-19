package com.it.sampleProject.database.model;

import java.math.BigInteger;
import java.util.Date;

import com.it.sampleProject.database.entities.ExchangeEntity;

public class Exchange {

    private BigInteger id;
    private Customer customer;
    private String routeId;
    private String exchangeId;
    private Date creationDate;

    public static Exchange mapFromEntity(ExchangeEntity entity) {
        Exchange exchange = new Exchange();
        exchange.setId(entity.getId());
        if (entity.getCustomer() != null) {
            exchange.setCustomer(Customer.mapFromEntity(entity.getCustomer()));
        }
        exchange.setExchangeId(entity.getExchangeId());
        exchange.setRouteId(entity.getRouteId());
        exchange.setCreationDate(entity.getCreationDate());
        return exchange;
    }

    public static ExchangeEntity mapToEntity(Exchange exchange) {
        ExchangeEntity exchangeEntity = new ExchangeEntity();
        exchangeEntity.setId(exchange.getId());
        exchangeEntity.setExchangeId(exchange.getExchangeId());
        exchangeEntity.setRouteId(exchange.getRouteId());
        exchangeEntity.setCreationDate(exchange.getCreationDate());
        if (exchange.getCustomer() != null) {
            exchangeEntity.setCustomer(Customer.mapToEntity(exchange.getCustomer()));
        }
        return exchangeEntity;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date timestamp) {
        this.creationDate = timestamp;
    }
}
