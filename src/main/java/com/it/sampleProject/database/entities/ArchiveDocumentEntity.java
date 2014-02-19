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
@Table(name = "archive_documents")
public class ArchiveDocumentEntity implements Serializable {

    private static final long serialVersionUID = 9176457008456664062L;

    public ArchiveDocumentEntity() {
    }
    @Id
    @Column(name = "id")
    private BigInteger id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_id")
    private ArchiveExchangeEntity exchange;
	@Column(name = "archive_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date archiveDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    protected CustomerEntity customer;
    @Column(name = "creationdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date creationDate;
    @Column(name = "body")
    protected String body;
    @Column(name = "document_version")
    protected String documentVersion;
    @Column(name = "body_type")
    protected String bodyType;
    @Column(name = "file_path")
    protected String filePath;
    @Column(name = "source_path")
    protected String sourcePath;
    @Column(name = "document_size")
    protected Long documentSize;
    @Column(name = "status")
    protected String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public Long getDocumentSize() {
        return documentSize;
    }

    public void setDocumentSize(Long documentSize) {
        this.documentSize = documentSize;
    }

    public ArchiveExchangeEntity getExchange() {
		return exchange;
	}

	public void setExchange(ArchiveExchangeEntity exchange) {
		this.exchange = exchange;
	}

	public Date getArchiveDate() {
        return archiveDate;
    }

    public void setArchiveDate(Date timestamp) {
        this.archiveDate = timestamp;
    }
}
