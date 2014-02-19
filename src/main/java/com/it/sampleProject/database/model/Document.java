package com.it.sampleProject.database.model;

import com.it.sampleProject.database.entities.DocumentEntity;

import java.math.BigInteger;
import java.util.Date;

public class Document {

    private BigInteger id;
    private Exchange exchange;
    private Date creationDate;
    private String body;
    private String documentVersion;
    private String bodyType;
    private String filePath;
    private String sourcePath;
    private String archivePath;
    private String failurePath;
    private Long documentSize;
    private String status;

    public static Document mapFromEntity(DocumentEntity documentEntity) {
        Document document = new Document();
        document.setId(documentEntity.getId());
        document.setBody(documentEntity.getBody());
        document.setDocumentVersion(documentEntity.getDocumentVersion());
        document.setBodyType(documentEntity.getBodyType());
        document.setFilePath(documentEntity.getFilePath());
        document.setSourcePath(documentEntity.getSourcePath());
        document.setArchivePath(documentEntity.getArchivePath());
        document.setFailurePath(documentEntity.getFailPath());
        document.setCreationDate(documentEntity.getCreationDate());
        document.setDocumentSize(documentEntity.getDocumentSize());
        document.setStatus(documentEntity.getStatus());
        if (documentEntity.getExchange() != null) {
            document.setExchange(Exchange.mapFromEntity(documentEntity.getExchange()));
        }
        return document;
    }

    public static DocumentEntity mapToEntity(Document document) {
        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setBody(document.getBody());
        documentEntity.setDocumentVersion(document.getDocumentVersion());
        documentEntity.setBodyType(document.getBodyType());
        documentEntity.setFilePath(document.getFilePath());
        documentEntity.setId(document.getId());
        documentEntity.setCreationDate(document.getCreationDate());
        documentEntity.setSourcePath(document.getSourcePath());
        documentEntity.setArchivePath(documentEntity.getArchivePath());
        documentEntity.setFailPath(document.getFailurePath());
        documentEntity.setDocumentSize(document.getDocumentSize());
        documentEntity.setStatus(document.getStatus());
        if (document.getExchange() != null) {
            documentEntity.setExchange(Exchange.mapToEntity(document.getExchange()));
        }
        return documentEntity;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date timestamp) {
        this.creationDate = timestamp;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDocumentVersion() {
        return documentVersion;
    }

    public void setDocumentVersion(String documentVersion) {
        this.documentVersion = documentVersion;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public Long getDocumentSize() {
        return documentSize;
    }

    public void setDocumentSize(Long documentSize) {
        this.documentSize = documentSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArchivePath() {
        return archivePath;
    }

    public void setArchivePath(String archivePath) {
        this.archivePath = archivePath;
    }

    public String getFailurePath() {
        return failurePath;
    }

    public void setFailurePath(String failurePath) {
        this.failurePath = failurePath;
    }
}
