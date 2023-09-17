package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password  = properties.getProperty("password");
        connection =  DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s();", tableName);
            statement.execute(sql);
        }
    }
    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "DROP TABLE  %s;", tableName);
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type)  throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s ADD %s %s;", tableName, columnName, type);
            statement.execute(sql);
        }
    }
    public void dropColumn(String tableName, String columnName)  throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)  throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s to %s;", tableName, columnName, newColumnName);
            statement.execute(sql);
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.createTable("testTable");
        tableEditor.addColumn("testTable", "id", "serial primary key");
        tableEditor.addColumn("testTable", "name", "varchar(255)");
        tableEditor.addColumn("testTable", "firstName", "varchar(255)");
        System.out.println(tableEditor.getTableScheme("testTable"));
        tableEditor.renameColumn("testTable", "firstName", "secondName");
        System.out.println(tableEditor.getTableScheme("testTable"));
        tableEditor.dropColumn("testTable", "secondName");
        System.out.println(tableEditor.getTableScheme("testTable"));
        tableEditor.dropTable("testTable");
    }
}