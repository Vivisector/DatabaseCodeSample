package com.it.sampleProject.database.model;

import java.util.Date;

import com.it.sampleProject.database.entities.ArchiveExchangeEntity;

public class ArchiveExchange extends Exchange {

	private Date archiveDate;
	
	public Date getArchiveDate() {
		return archiveDate;
	}

	public void setArchiveDate(Date archiveDate) {
		this.archiveDate = archiveDate;
	}

	public static ArchiveExchange mapFromEntity(ArchiveExchangeEntity entity) {
        ArchiveExchange exchange = new ArchiveExchange();
        exchange.setId(entity.getId());
        if (entity.getCustomer() != null) {
            exchange.setCustomer(Customer.mapFromEntity(entity.getCustomer()));
        }
        exchange.setExchangeId(entity.getExchangeId());
        exchange.setRouteId(entity.getRouteId());
        exchange.setCreationDate(entity.getCreationDate());
        exchange.setArchiveDate(entity.getArchiveDate());
        return exchange;
    }

    public static ArchiveExchangeEntity mapToEntity(ArchiveExchange exchange) {
    	ArchiveExchangeEntity exchangeEntity = new ArchiveExchangeEntity();
        exchangeEntity.setId(exchange.getId());
        exchangeEntity.setExchangeId(exchange.getExchangeId());
        exchangeEntity.setRouteId(exchange.getRouteId());
        exchangeEntity.setCreationDate(exchange.getCreationDate());
        if (exchange.getCustomer() != null) {
            exchangeEntity.setCustomer(Customer.mapToEntity(exchange.getCustomer()));
        }
        exchangeEntity.setArchiveDate(exchange.getArchiveDate());
        return exchangeEntity;
    }

}
