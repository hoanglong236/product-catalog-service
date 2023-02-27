package com.lhstore.productcatalogservice;

import org.testcontainers.containers.MySQLContainer;

public class AbstractTest {

    protected static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
            .withDatabaseName("product-catalog-service-test")
            .withUsername("root")
            .withPassword("Abc12345");

    static {
        mySQLContainer.start();
    }
}
