package com.it.sampleProject.database.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 6467623577461114392L;

    public CustomerEntity() {
    }
    @Id
      @SequenceGenerator(name="customers_seq", sequenceName="customers_seq")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="customers_seq")
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "name")
    private String name;
    @Column(name = "is_deleted")
    private int deleted;

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
