package com.corradowaver.store.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
  private static final String URL = "jdbc:mysql://localhost:3306/goods-db?useLegacyDatetimeCode=false&serverTimezone=UTC";
  private static final String USER = "root";
  private static final String PASSWORD = "root";

  public static Connection getConnection() {
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
      return conn;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
