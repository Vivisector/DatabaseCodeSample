package com.it.sampleProject.database.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "archive_events")
public class ArchiveEventEntity implements Serializable {

    private static final long serialVersionUID = 7574830697364747571L;

    public ArchiveEventEntity() {
    }
    @Id
    @Column(name = "id")
    private BigInteger id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_id")
    private ArchiveExchangeEntity exchange;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private ArchiveDocumentEntity document;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    protected CustomerEntity customer;
    @Column(name = "message")
    protected String message;
    @Column(name = "creationdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date creationDate;
    @Column(name = "archive_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date archiveDate;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public ArchiveExchangeEntity getExchange() {
        return exchange;
    }

    public void setExchange(ArchiveExchangeEntity exchange) {
        this.exchange = exchange;
    }

    public ArchiveDocumentEntity getDocument() {
        return document;
    }

    public void setDocument(ArchiveDocumentEntity document) {
        this.document = document;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date timestamp) {
        this.creationDate = timestamp;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public Date getArchiveDate() {
        return archiveDate;
    }

    public void setArchiveDate(Date timestamp) {
        this.archiveDate = timestamp;
    }
}
