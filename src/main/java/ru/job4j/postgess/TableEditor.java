package ru.job4j.postgess;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;


    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createSql(Connection connection, String sql) throws Exception {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
                if (!sql.contains("drop table")) {
                System.out.println(getTableScheme(connection, "demo_table"));
                }
            }
    }

    public void createTable(String tableName) throws Exception {
                String sql = String.format(
                        "create table if not exists " +  tableName + "(%s, %s);",
                        "id serial primary key",
                        "name text"
                );
        createSql(connection, sql);
    }

    public void dropTable(String tableName) throws Exception {
                String sql = String.format(
                        "drop table if exists " +  tableName + ";"
                );
        createSql(connection, sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "alter table if exists " +  tableName + " add " + columnName + " " + type + " null;"
        );
        createSql(connection, sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "alter table if exists " +  tableName + " drop column " + columnName + " ;"
        );
        createSql(connection, sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "alter table if exists " + tableName
                        + " rename column " + columnName
                        + " to " + newColumnName + " ;"
        );
        createSql(connection, sql);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
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
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream("./data/app.properties")) {
            properties.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("demo_table");
            tableEditor.addColumn("demo_table", "price", "integer");
            tableEditor.renameColumn("demo_table", "price", "priceNew");
            tableEditor.dropColumn("demo_table", "priceNew");
            tableEditor.dropTable("demo_table");
        }
    }
}
