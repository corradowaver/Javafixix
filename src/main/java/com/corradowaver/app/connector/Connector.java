package com.corradowaver.app.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
  private static final String URL = "jdbc:mysql://localhost:3306/goods-db?useLegacyDatetimeCode=false&serverTimezone=UTC";
  private static final String USER = "root";
  private static final String PASSWORD = "root";
  private static Connection connection;

  public static Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    } else {
      return connection;
    }
  }

}
