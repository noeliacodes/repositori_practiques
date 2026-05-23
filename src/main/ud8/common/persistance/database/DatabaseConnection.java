package ud8.common.persistance.database;

import ud8.common.utils.AppPropertiesReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    // Singleton pattern
    private static DatabaseConnection instance = null;
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;
    private final Connection connection;

    private DatabaseConnection() {
        dbUrl = AppPropertiesReader.getProperty("app.datasource.url");
        dbUser = AppPropertiesReader.getProperty("app.datasource.username");
        dbPassword = AppPropertiesReader.getProperty("app.datasource.password");

        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException("Connection parameters :\n\n" + getParameters() + "\nOriginal exception message: " + e.getMessage());
        }
    }

    private String getParameters() {
        return String.format("url: %s\nUser: %s\nPassword: %s\n", dbUrl, dbUser, dbPassword);
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public void executeScript(String scriptPath) {
        try {
            ScriptRunner scriptRunner = new ScriptRunner(connection, false, false);

            InputStream scriptStream = getClass().getClassLoader().getResourceAsStream(scriptPath);
            if (scriptStream == null)
                throw new RuntimeException("Script not found: " + scriptPath);

            scriptRunner.runScript(new InputStreamReader(scriptStream));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(String.format("Error executing script %s:\n    %s\n", scriptPath, e.getMessage()));
        }
    }
}
