package com.it.sampleProject.database.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "archive_exchanges")
public class ArchiveExchangeEntity implements Serializable {

    private static final long serialVersionUID = 3088947919682042536L;

    public ArchiveExchangeEntity() {
    }
    @Id
    @Column(name = "id")
    private BigInteger id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    protected CustomerEntity customer;
    @Column(name = "route_id")
    protected String routeId;
    @Column(name = "exchange_id")
    protected String exchangeId;
    @Column(name = "creationdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date creationDate;
    @Column(name = "archive_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date archiveDate;

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
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

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getArchiveDate() {
        return archiveDate;
    }

    public void setArchiveDate(Date timestamp) {
        this.archiveDate = timestamp;
    }
}
