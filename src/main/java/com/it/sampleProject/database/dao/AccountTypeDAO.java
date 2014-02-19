package com.it.sampleProject.database.dao;

import java.util.ArrayList;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.it.sampleProject.database.entities.AccountTypeEntity;

@Repository
public class AccountTypeDAO extends BaseDAO {

    public AccountTypeDAO() {
    }

//  
    @SuppressWarnings("unchecked")
	public ArrayList<AccountTypeEntity> getAccountTypeEntities() {
        DetachedCriteria accountsTypeCriteria = DetachedCriteria.forClass(AccountTypeEntity.class);
        return (ArrayList<AccountTypeEntity>) findByCriteria(accountsTypeCriteria);
    }
}
