package com.it.sampleProject.database.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "documents")
public class DocumentEntity implements Serializable {

    private static final long serialVersionUID = 9176457008456664061L;
    @Id
    @SequenceGenerator(name = "documents_seq", sequenceName = "documents_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "documents_seq")
    @Column(name = "id")
    private BigInteger id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_id")
    private ExchangeEntity exchange;
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
    @Column(name = "archive_path")
    protected String archivePath;
    @Column(name = "failure_path")
    protected String failPath;
    @Column(name = "source_path")
    protected String sourcePath;
    @Column(name = "document_size")
    protected Long documentSize;
    @Column(name = "status")
    protected String status;

    public DocumentEntity() {
    }

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

    public ExchangeEntity getExchange() {
        return exchange;
    }

    public void setExchange(ExchangeEntity exchange) {
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

    public String getArchivePath() {
        return archivePath;
    }

    public void setArchivePath(String archivePath) {
        this.archivePath = archivePath;
    }

    public String getFailPath() {
        return failPath;
    }

    public void setFailPath(String failPath) {
        this.failPath = failPath;
    }
}
