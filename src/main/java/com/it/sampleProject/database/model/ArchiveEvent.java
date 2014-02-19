package com.it.sampleProject.database.model;

import java.util.Date;

import com.it.sampleProject.database.entities.ArchiveDocumentEntity;
import com.it.sampleProject.database.entities.ArchiveEventEntity;

public class ArchiveEvent extends Event {

    private ArchiveExchange exchange;
    private ArchiveDocument document;
    private Date archiveDate;
	
	public Date getArchiveDate() {
		return archiveDate;
	}

	public void setArchiveDate(Date archiveDate) {
		this.archiveDate = archiveDate;
	}

    public static ArchiveEvent mapFromEntity(ArchiveEventEntity eventEntity) {
        ArchiveEvent event = new ArchiveEvent();
        event.setId(eventEntity.getId());
        event.setMessage(eventEntity.getMessage());
        event.setCreationDate(eventEntity.getCreationDate());
        if (eventEntity.getExchange() != null) {
            event.setExchange(ArchiveExchange.mapFromEntity(eventEntity.getExchange()));
        }
        if (eventEntity.getDocument() != null) {
        	ArchiveDocument document = ArchiveDocument.mapFromEntity(eventEntity.getDocument());
            document.setExchange(event.getExchange());
            event.setDocument(document);
        }
        event.setArchiveDate(eventEntity.getArchiveDate());
        return event;
    }

    public static ArchiveEventEntity mapToEntity(ArchiveEvent event) {
    	ArchiveEventEntity eventEntity = new ArchiveEventEntity();
        eventEntity.setId(event.getId());
        eventEntity.setMessage(event.getMessage());
        eventEntity.setCreationDate(event.getCreationDate());
        if (event.getExchange() != null) {
            eventEntity.setExchange(ArchiveExchange.mapToEntity(event.getExchange()));
        }
        if (event.getDocument() != null) {
        	ArchiveDocumentEntity documentEntity = ArchiveDocument.mapToEntity(event.getDocument());
        	documentEntity.setExchange(eventEntity.getExchange());
        	eventEntity.setDocument(documentEntity);
        }
        eventEntity.setArchiveDate(event.getArchiveDate());
        return eventEntity;
    }

    public ArchiveExchange getExchange() {
        return exchange;
    }

    public void setExchange(ArchiveExchange exchange) {
        this.exchange = exchange;
    }

    public ArchiveDocument getDocument() {
        return document;
    }

    public void setDocument(ArchiveDocument document) {
        this.document = document;
    }
}
