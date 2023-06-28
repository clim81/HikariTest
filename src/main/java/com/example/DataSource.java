package com.example;

//Source: https://www.baeldung.com/hikaricp

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;

import java.sql.SQLException;

public class DataSource {
    //Class.forName("org.postgresql.Driver");
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl("\"jdbc:postgresql://localhost:5432\"");
        config.setUsername("postgres");
        //config.setPassword("thepasswordissecret");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        //show_sql = false 
        //min size (pool) = 5 (not sure if min size and minimumIdle are the same)
        config.addDataSourceProperty("minimumIdle", "5");
        //max size (pool) = 50
        config.addDataSourceProperty("maximumPoolSize","50"); 
        //timeout = 1800 (connection, idle, or validate?)
        config.addDataSourceProperty("idleTimeout", "1800");
        //max statements = 0
        //connection pool size = 0

        ds = new HikariDataSource(config);
    }

    //can also do this instead of the whole static block
    //note that the db.properties file has to be in the resource class
    //private static HikariConfig config = new HikariConfig("db.properties");
    private DataSource() {

    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
//seems like c3p0 also has a properties file (ex. hibernate.properties)
//maybe can keep this? do we need for hibernate? or can we replace to create new 
//properties file