package com.inalkar.skeleton.jdk8.javafx.util.database;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class LiquibaseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(LiquibaseUtil.class);

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String systemDBUrl;

    @Value("${liquibase.changelog-path}")
    private String changeLogPath;

    public void updateSystemDBSchema() throws LiquibaseException, SQLException {
        updateSchema(systemDBUrl);
    }

    public void updateSchema(final String url) throws LiquibaseException, SQLException {
        Connection c = DriverManager.getConnection(url, userName, password);
        updateSchema(c);
    }

    public void updateSchema(final Connection connection) throws LiquibaseException, SQLException {
        LOGGER.info("Updating database schema");

        Liquibase liquibase;
        try {
            LOGGER.info("Releasing database lock.");
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE DATABASECHANGELOGLOCK\n" +
                                "SET locked=0, lockgranted=null, lockedby=null\n" +
                                "WHERE id=1");
                statement.execute();
            } catch (Exception e) { /* ignore any exception */ }

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor();

            LOGGER.info(String.format("Start updating database schema changelog: %s", changeLogPath));
            liquibase = new Liquibase(changeLogPath, resourceAccessor, database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception e) {
            LOGGER.error("Error updating database schema", e);
            throw new LiquibaseException(e);
        } finally {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e) {
                //nothing to do
            }
        }
    }

}
