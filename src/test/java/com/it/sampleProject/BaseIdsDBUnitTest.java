package com.it.sampleProject;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.it.sampleProject.tests.IntegrationTests;
import com.it.sampleProject.utils.IdsDBUnitDataSetLoader;
import org.junit.experimental.categories.Category;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Igor Ivaniuk
 */

@DatabaseSetup("classpath:DbUnit\\testData.xml")
@DatabaseTearDown("classpath:DbUnit\\testClear.xml")
@DbUnitConfiguration(dataSetLoader = IdsDBUnitDataSetLoader.class)
@Transactional
@Category(IntegrationTests.class)
public abstract class BaseIdsDBUnitTest extends BaseIdsTest {
}
