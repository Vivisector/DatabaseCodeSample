/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.it.sampleProject.database.dao;

import com.it.database.exceptions.DuplicateEntryException;
import com.it.sampleProject.BaseIdsDBUnitTest;
import com.it.sampleProject.database.entities.CustomerEntity;
import com.it.sampleProject.database.consts.DbUnitConstants;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Igor Ivaniuk
 */
public class CustomerDAOTest extends BaseIdsDBUnitTest {
    @Autowired
    private CustomerDAO customerDAO;

    @Test
    public void testCreateCustomer() throws DuplicateEntryException {
        final CustomerEntity persistedCustomer = customerDAO.addCustomerEntity(DbUnitConstants.NEW_CUSTOMER_NAME);

        assertNotNull(persistedCustomer.getId());
    }

    @Test
    public void testFindCustomerByName() throws DuplicateEntryException {
        final CustomerEntity customer = customerDAO.findCustomerEntityByName(DbUnitConstants.CUSTOMER_NAME);

        assertNotNull(customer);
        Assert.assertEquals(DbUnitConstants.CUSTOMER_NAME, customer.getName());
    }

    @Test
    public void testFindAllCustomer() throws DuplicateEntryException {
        final ArrayList<CustomerEntity> customerEntities = customerDAO.getCustomerEntities();

        assertNotNull(customerEntities);

        boolean foundTestAccount = false;
        for (CustomerEntity customerEntity : customerEntities) {
            if (DbUnitConstants.CUSTOMER_NAME.equals(customerEntity.getName()))
                foundTestAccount = true;

        }

        assertTrue(foundTestAccount);
    }
}
