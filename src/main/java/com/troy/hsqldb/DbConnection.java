package com.troy.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DbConnection {
	final static Logger logger =  LogManager.getLogger(DbConnection.class);
	
    private static String dbURL = "jdbc:hsqldb:file:DB/demo";
    private static String dbUsername = "SA";
    private static String dbPassword = "";
    
    public static Connection getConnection() {
        Connection connection = null;
        try {
        	logger.info("Creating new Database connection.");
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return connection;
    }
    
}
