package ru.job4j.postgess;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private String strProp;

    public TableEditor(String strProp) throws Exception {
        this.strProp = strProp;
        initConnection();
    }

    private void initConnection() throws Exception {
        Config config = new Config(strProp);
        config.load();
        Class.forName(config.value("hibernate.connection.driver_class"));
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createSql(String sql) throws Exception {
        try (Connection connection = this.connection) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
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
        createSql(sql);
    }

    public void dropTable(String tableName) throws Exception {
        try (Connection connection = this.connection) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "drop table if exists " +  tableName + ";"
                );
                statement.execute(sql);
            }
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "alter table if exists " +  tableName + " add " + columnName + " " + type + " null;"
        );
        createSql(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "alter table if exists " +  tableName + " drop column " + columnName + " ;"
        );
        createSql(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "alter table if exists " + tableName
                        + " rename column " + columnName
                        + " to " + newColumnName + " ;"
        );
        createSql(sql);
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
        TableEditor tableEditor = new TableEditor("./data/app.properties");
        tableEditor.createTable("demo_table");

        TableEditor tableEditor2 = new TableEditor("./data/app.properties");
        tableEditor2.addColumn("demo_table", "price", "integer");

        TableEditor tableEditor3 = new TableEditor("./data/app.properties");
        tableEditor3.renameColumn("demo_table", "price", "priceNew");

        TableEditor tableEditor4 = new TableEditor("./data/app.properties");
        tableEditor4.dropColumn("demo_table", "priceNew");

        TableEditor tableEditor5 = new TableEditor("./data/app.properties");
        tableEditor5.dropTable("demo_table");
    }
}
