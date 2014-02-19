package com.it.sampleProject.database.consts;

import java.math.BigInteger;

/**
 * Class that contains constants for DbUnit inserts and db tests.
 *
 * @author Igor Ivaniuk
 */

public abstract class DbUnitConstants {
    //Event
    public static final BigInteger EVENT_ID = BigInteger.valueOf(1);
    public static final String EVENT_MESSAGE = "Test event message";

    //Exchange
    public static final BigInteger EXCHANGE_ID = BigInteger.valueOf(1);

    //Document
    public static final BigInteger DOCUMENT_ID = BigInteger.valueOf(1);
    public static final String DOCUMENT_BODY = "Test doc body";
    public static final String DOCUMENT_VERSION = "Test doc version";

    //Account
    public static final String ACCOUNT_NAME = "Test Account";
    public static final String ACCOUNT_LOGIN = "Test login";
    public static final String ACCOUNT_PASSWORD_HASH = "Some hash";
    public static final String NEW_ACCOUNT_NAME = "New Test Account";
    public static final String NEW_ACCOUNT_LOGIN = "New Test login";
    public static final String NEW_ACCOUNT_PASSWORD_HASH = "New Some hash";

    //Customer
    public static final String NEW_CUSTOMER_NAME = "New Test Customer";
    public static final String CUSTOMER_NAME = "Test customer";

    //Other
    public static final BigInteger NOT_EXISTS_OBJECT_ID = BigInteger.valueOf(589);
}
