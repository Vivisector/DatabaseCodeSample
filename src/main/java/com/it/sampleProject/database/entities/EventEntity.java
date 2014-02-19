package com.it.sampleProject.database.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "events")
public class EventEntity implements Serializable {

    private static final long serialVersionUID = 7574830697364747570L;

    public EventEntity() {
    }
    @Id
    @SequenceGenerator(name = "events_seq", sequenceName = "events_seq")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="events_seq")
    @Column(name = "id")
    private BigInteger id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_id")
    private ExchangeEntity exchange;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private DocumentEntity document;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    protected CustomerEntity customer;
    @Column(name = "message")
    protected String message;
    @Column(name = "creationdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date creationDate;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public ExchangeEntity getExchange() {
        return exchange;
    }

    public void setExchange(ExchangeEntity exchange) {
        this.exchange = exchange;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
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
}
