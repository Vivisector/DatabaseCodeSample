package com.it.sampleProject.database.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "camel_messageprocessed")
public class Camel_MessageProcessedEntity implements Serializable {

    private static final long serialVersionUID = -3019663430988019139L;

    public Camel_MessageProcessedEntity() {
    }
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "processorName")
    private String processorName;
    @Column(name = "messageId")
    private String messageId;
    @Column(name = "createdAt")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getProcessorName() {
        return processorName;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

}
