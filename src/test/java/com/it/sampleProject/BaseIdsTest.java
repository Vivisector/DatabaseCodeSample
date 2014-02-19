/**
 * **************************************************************
 *
 * Copyright IntellectTechnologies LLC. All rights reserved.
 *
 * This software and its associated documentation contain proprietary,
 * confidential and trade secret information of IntellectTechnlogies LLC and
 * except as provided by written agreement with IntellectTechnologies LLC.
 *
 * a) no part may be disclosed, distributed, reproduced, transmitted,
 * transcribed, stored in a retrieval system, adapted or translated in any form
 * or by any means electronic, mechanical, magnetic, optical, chemical, manual
 * or otherwise, and b) the recipient is not entitled to discover through
 * reverse engineering or reverse compiling or other such techniques or
 * processes the trade secrets contained in the software code or to use any such
 * trade secrets contained therein or in the documentation.
 *
 **************************************************************
 */

package com.it.sampleProject;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.info.MigrationInfoDumper;
import com.it.sampleProject.router.CamelContextInit;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import static org.junit.Assert.assertNotNull;

/**
 * @author Igor Ivaniuk
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:applicationContextTest.xml", "classpath:idsDBTest.xml"})
public abstract class BaseIdsTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    protected CamelContextInit camelContextInit;

    protected static HashMap<String, String> dbProperties = new HashMap<String, String>();

    @Before
    public void setUp() {
        assertNotNull(camelContextInit);
    }

    @After
    public void tearDown() throws Exception {
        camelContextInit.stopAll();
    }

    @BeforeClass
    /**
     * DB upgrade schemas in IDS Upgrade project.
     * DB url in datasource.properties must contain idstest.
     */
    public static void dbSetup() {
        getDatabaseConfiguration("datasource.properties");
        setupDatabase();
    }

    private static void getDatabaseConfiguration(String datasourecePropertiesFilename) {
        PropertiesConfiguration dbPropertiesConfiguration = new PropertiesConfiguration();
        System.out.println("datasource file: " + datasourecePropertiesFilename);
        try {
            dbPropertiesConfiguration.load(BaseIdsTest.class.getClassLoader().getResourceAsStream(
                    datasourecePropertiesFilename));
        } catch (ConfigurationException e) {
            System.err.println("Failed to load db properties from: " + datasourecePropertiesFilename + " \r\nError: "
                    + e);
            return;
        }

        dbProperties.put("dbUrl",
                dbPropertiesConfiguration.getString("hibernate.connection.url", "jdbc:mysql://localhost:3327/cmtest"));
        dbProperties.put("username",
                dbPropertiesConfiguration.getString("hibernate.connection.username", "intellectlink"));
        dbProperties.put("password",
                dbPropertiesConfiguration.getString("hibernate.connection.password", "intellectlink"));
        dbProperties.put("dbClass",
                dbPropertiesConfiguration.getString("hibernate.connection.driver_class", "com.mysql.jdbc.Driver"));
    }

    private static void setupDatabase() {
        createDB();
        Flyway flyway = new Flyway();
        flyway.setDataSource(dbProperties.get("dbUrl"), dbProperties.get("username"), dbProperties.get("password"));
        flyway.setInitOnMigrate(true);
        flyway.setInitVersion("2");
        flyway.migrate();
        System.out.println("Database status:");
        System.out.println("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
    }

    private static void createDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(dbProperties.get("dbUrl").replace("idstest", "mysql"),
                    dbProperties.get("username"), dbProperties.get("password"));
            stmt = conn.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS IDSTEST";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException se) {
            System.out.println("createDb sql exception: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("createDb exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}

