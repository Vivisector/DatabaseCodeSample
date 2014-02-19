package com.it.sampleProject.database.model;

import java.util.Date;

import com.it.sampleProject.database.entities.ArchiveDocumentEntity;

public class ArchiveDocument extends Document {

    private ArchiveExchange exchange;

    private Date archiveDate;
	
	public Date getArchiveDate() {
		return archiveDate;
	}

	public void setArchiveDate(Date archiveDate) {
		this.archiveDate = archiveDate;
	}

	public static ArchiveDocument mapFromEntity(ArchiveDocumentEntity documentEntity) {
        ArchiveDocument document = new ArchiveDocument();
        document.setId(documentEntity.getId());
        document.setBody(documentEntity.getBody());
        document.setDocumentVersion(documentEntity.getDocumentVersion());
        document.setBodyType(documentEntity.getBodyType());
        document.setFilePath(documentEntity.getFilePath());
        document.setSourcePath(documentEntity.getSourcePath());
        document.setCreationDate(documentEntity.getCreationDate());
        document.setDocumentSize(documentEntity.getDocumentSize());
        document.setStatus(documentEntity.getStatus());
        if (documentEntity.getExchange() != null) {
            document.setExchange(ArchiveExchange.mapFromEntity(documentEntity.getExchange()));
        }
        document.setArchiveDate(documentEntity.getArchiveDate());
        return document;
    }

    public static ArchiveDocumentEntity mapToEntity(ArchiveDocument document) {
    	ArchiveDocumentEntity documentEntity = new ArchiveDocumentEntity();
        documentEntity.setBody(document.getBody());
        documentEntity.setDocumentVersion(document.getDocumentVersion());
        documentEntity.setBodyType(document.getBodyType());
        documentEntity.setFilePath(document.getFilePath());
        documentEntity.setId(document.getId());
        documentEntity.setCreationDate(document.getCreationDate());
        documentEntity.setSourcePath(document.getSourcePath());
        documentEntity.setDocumentSize(document.getDocumentSize());
        documentEntity.setStatus(document.getStatus());
        if (document.getExchange() != null) {
            documentEntity.setExchange(ArchiveExchange.mapToEntity(document.getExchange()));
        }
        documentEntity.setArchiveDate(document.getArchiveDate());
        return documentEntity;
    }

    public ArchiveExchange getExchange() {
        return exchange;
    }

    public void setExchange(ArchiveExchange exchange) {
        this.exchange = exchange;
    }

}
