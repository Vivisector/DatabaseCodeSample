package com.it.sampleProject.database.model;

import java.math.BigInteger;
import java.util.Date;

import com.it.sampleProject.database.entities.DocumentEntity;
import com.it.sampleProject.database.entities.EventEntity;

public class Event {

    private BigInteger id;
    private Exchange exchange;
    private Document document;
    private String message;
    private Date creationDate;

    public static Event mapFromEntity(EventEntity eventEntity) {
        Event event = new Event();
        event.setId(eventEntity.getId());
        event.setMessage(eventEntity.getMessage());
        event.setCreationDate(eventEntity.getCreationDate());
        if (eventEntity.getExchange() != null) {
            event.setExchange(Exchange.mapFromEntity(eventEntity.getExchange()));
        }
        if (eventEntity.getDocument() != null) {
        	Document document = Document.mapFromEntity(eventEntity.getDocument());
            document.setExchange(event.getExchange());
            event.setDocument(document);
        }
        return event;
    }

    public static EventEntity mapToEntity(Event event) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(event.getId());
        eventEntity.setMessage(event.getMessage());
        eventEntity.setCreationDate(event.getCreationDate());
        if (event.getExchange() != null) {
            eventEntity.setExchange(Exchange.mapToEntity(event.getExchange()));
        }
        if (event.getDocument() != null) {
        	DocumentEntity documentEntity = Document.mapToEntity(event.getDocument());
        	documentEntity.setExchange(eventEntity.getExchange());
        	eventEntity.setDocument(documentEntity);
        }
        return eventEntity;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
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
}
