package com.it.sampleProject.database.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.it.sampleProject.database.entities.AccountEntity;

@Repository
public class AccountDAO extends BaseDAO {

    private static final String FIELD_NAME_ACCOUNT_NAME = "name";
    private static final String FIELD_NAME_ACCOUNT_LOGIN = "login";

    public AccountDAO() {
    }


    public void deleteAccountEntity(AccountEntity accountEntity) {
    	  getCurrentSession().delete(accountEntity);
    }

    public void updateAccountEntity(AccountEntity accountEntity) {
    	  getCurrentSession().update(accountEntity);
    }

    public void saveAccountEntity(AccountEntity accountEntity) {
    	  getCurrentSession().save(accountEntity);
    }

    public AccountEntity addAccountEntity(String name, String login, String passwordHash) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(name);
        accountEntity.setLogin(login);
        accountEntity.setPasswordHash(passwordHash);
        saveAccountEntity(accountEntity);
        return accountEntity;
    }

    @SuppressWarnings("unchecked")
	public ArrayList<AccountEntity> getAccountEntities() {
        DetachedCriteria accountsCriteria = DetachedCriteria.forClass(AccountEntity.class);
        return (ArrayList<AccountEntity>) findByCriteria(accountsCriteria);
    }

    public AccountEntity findAccountEntityByLogin(String login) {
        return findAccountByField(FIELD_NAME_ACCOUNT_LOGIN, login);
    }

    public AccountEntity findAccountEntityByName(String name) {
        return findAccountByField(FIELD_NAME_ACCOUNT_NAME, name);
    }

    @SuppressWarnings("unchecked")
	private AccountEntity findAccountByField(String fieldName, Object fieldValue) {
        DetachedCriteria accountCriteria = DetachedCriteria.forClass(AccountEntity.class);
        accountCriteria.add(Restrictions.eq(fieldName, fieldValue));
        List<AccountEntity> accountEntitiesList = findByCriteria(accountCriteria);
        AccountEntity accountEntity = null;
        if (accountEntitiesList != null && !accountEntitiesList.isEmpty()) {
            accountEntity = accountEntitiesList.get(0);
        }
        return accountEntity;

    }

    public AccountEntity findAccountEntityById(BigInteger id) {
        AccountEntity accountEntity = (AccountEntity) getCurrentSession().get(AccountEntity.class, id);
        return accountEntity;
    }
}
