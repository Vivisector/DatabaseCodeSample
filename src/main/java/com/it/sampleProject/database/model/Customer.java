package com.it.sampleProject.database.model;

import java.math.BigInteger;

import com.it.sampleProject.database.entities.CustomerEntity;

public class Customer {

    private BigInteger id;
    private String name;
    private int deleted;

    public static Customer mapFromEntity(CustomerEntity entity) {
        Customer customer = new Customer();
        customer.setId(entity.getId());
        customer.setName(entity.getName());
        customer.setDeleted(entity.getDeleted());
        return customer;
    }

    public static CustomerEntity mapToEntity(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setName(customer.getName());
        customerEntity.setDeleted(customer.getDeleted());
        return customerEntity;
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

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    
}
