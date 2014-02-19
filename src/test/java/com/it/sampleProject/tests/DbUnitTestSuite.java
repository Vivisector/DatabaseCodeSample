package com.it.sampleProject.tests;

import com.it.sampleProject.database.DatabaseHelperTest;
import com.it.sampleProject.database.dao.*;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Igor Ivaniuk
 */
@RunWith(Categories.class)
@Suite.SuiteClasses({DatabaseHelperTest.class, AccountDAOTest.class,
        CustomerDAOTest.class, DocumentDAOTest.class,
        EventDAOTest.class, ExchangeDAOTest.class})
public class DbUnitTestSuite {
}
