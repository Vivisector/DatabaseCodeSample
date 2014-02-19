package com.it.sampleProject.tests;

import com.it.sampleProject.services.DocumentVersionServiceTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.it.sampleProject.utils.IdsToolsTest;
import com.it.sampleProject.utils.PurgeDBOnTimeTest;

/**
 *
 * @author Igor Ivaniuk
 */
@RunWith(Categories.class)
@Suite.SuiteClasses( { DocumentVersionServiceTest.class, PurgeDBOnTimeTest.class, IdsToolsTest.class })
public class UnitTestSuite {
    
}
