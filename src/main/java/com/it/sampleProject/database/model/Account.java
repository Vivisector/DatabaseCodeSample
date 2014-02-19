package com.it.sampleProject.database.model;

import com.it.sampleProject.database.entities.AccountEntity;
import com.it.sampleProject.database.entities.CustomerEntity;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Account {

    private BigInteger id;
    private String name;
    private String login;
    private String passwordHash;
    private AccountType accountType;
    private Set<Customer> customers;

    public static Account mapFromEntity(AccountEntity entity) {
        Account account = new Account();
        account.setId(entity.getId());
        account.setName(entity.getName());
        account.setLogin(entity.getLogin());
        account.setPasswordHash(entity.getPasswordHash());
        account.setAccountType(AccountType.mapFromEntity(entity.getAccountType()));
        // map customers
        Set<Customer> customers = new HashSet<Customer>();
        Set<CustomerEntity> customerEntities = entity.getCustomers();
        if (customerEntities != null) {
            for (CustomerEntity customerEntity : customerEntities) {
                customers.add(Customer.mapFromEntity(customerEntity));
            }
        }
        account.setCustomers(customers);
        return account;
    }

    public static AccountEntity mapToEntity(Account account, AccountEntity accountEntity) {
        accountEntity.setId(account.getId());
        accountEntity.setName(account.getName());
        accountEntity.setLogin(account.getLogin());
        accountEntity.setPasswordHash(account.getPasswordHash());
        // map customers
        Set<Customer> customers = account.getCustomers();
        Set<CustomerEntity> customerEntities = new HashSet<CustomerEntity>();
        if (customers != null) {
            for (Customer customer : customers) {
                customerEntities.add(Customer.mapToEntity(customer));
            }
        }
        accountEntity.setCustomers(customerEntities);
        accountEntity.setAccountType(AccountType.mapToEntity(account.getAccountType()));
        return accountEntity;
    }

    public static AccountEntity mapToEntity(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        return mapToEntity(account, accountEntity);
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

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
