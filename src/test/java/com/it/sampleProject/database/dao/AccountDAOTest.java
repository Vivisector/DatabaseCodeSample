/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.it.sampleProject.database.dao;

import com.it.database.exceptions.DuplicateEntryException;
import com.it.sampleProject.BaseIdsDBUnitTest;
import com.it.sampleProject.database.entities.AccountEntity;
import com.it.sampleProject.database.model.Account;
import com.it.sampleProject.database.consts.DbUnitConstants;
import com.it.sampleProject.database.utils.DatabseEntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static com.it.sampleProject.database.utils.DatabseEntityUtils.assertExistsAccount;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Igor Ivaniuk
 */
public class AccountDAOTest extends BaseIdsDBUnitTest {
    @Autowired
    private AccountDAO accountDAO;

    @Test
    public void testCreateAccount() throws DuplicateEntryException {
        final AccountEntity account = Account.mapToEntity(DatabseEntityUtils.prepareNewAccount());

        accountDAO.saveAccountEntity(account);

        assertNotNull(account.getId());
    }

    @Test
    public void testFindAccountByLogin() throws DuplicateEntryException {
        final AccountEntity account = accountDAO.findAccountEntityByLogin(DbUnitConstants.ACCOUNT_LOGIN);

        DatabseEntityUtils.assertExistsAccount(account);
    }

    @Test
    public void testFindAccountByName() throws DuplicateEntryException {
        final AccountEntity account = accountDAO.findAccountEntityByName(DbUnitConstants.ACCOUNT_NAME);

        DatabseEntityUtils.assertExistsAccount(account);
    }

    @Test
    public void testFindAllAccounts() throws DuplicateEntryException {
        final ArrayList<AccountEntity> accountEntities = accountDAO.getAccountEntities();

        assertNotNull(accountEntities);

        boolean foundTestAccount = false;
        for (AccountEntity account : accountEntities) {
            if (DbUnitConstants.ACCOUNT_LOGIN.equals(account.getLogin()))
                foundTestAccount = true;
        }

        assertTrue(foundTestAccount);
    }
}
