package com.corradowaver.app.table;

import com.corradowaver.app.connector.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableDao  {
  private Connection connection;

  public TableDao() {
    this.connection = Connector.getConnection();
  }

  public boolean ifExists(Product product) throws SQLException {
    String title = product.getTitle();
    String sql = "SELECT * FROM products WHERE title = ?";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1, title);
    ResultSet resultSet = ps.executeQuery();
    return resultSet.next();
  }

  public List<Product> getAll() throws SQLException {
      ResultSet resultSet =
              connection.createStatement().executeQuery("SELECT * FROM products");
      return makeProductsList(resultSet);
  }

  public void add(Product product) throws SQLException {
    String prodid = product.getProdid();
    String title = product.getTitle();
    int cost = product.getCost();
    String sql = "INSERT INTO products (prodid, title, cost) VALUES (?, ?, ?)";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1, prodid);
    ps.setString(2, title);
    ps.setInt(3, cost);
    ps.execute();
  }

  public String getPrice(Product product) throws SQLException {
    String title = product.getTitle();
    String sql = "SELECT cost FROM products WHERE title = ?";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1, title);
    ResultSet resultSet = ps.executeQuery();
    resultSet.next();
    return resultSet.getInt("cost");
  }

  public void changePrice(Product product, int newPrice) throws SQLException {
    String title = product.getTitle();
    String sql = "UPDATE products SET cost = ? WHERE title = ?";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setInt(1, newPrice);
    ps.setString(2, title);
    ps.execute();
  }

  public List<Product> filterByPrice(int from, int to) throws SQLException {
    String sql = "SELECT * FROM products WHERE cost BETWEEN ? AND ?";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setInt(1, from);
    ps.setInt(2, to);
    ResultSet resultSet = ps.executeQuery();

    return makeProductsList(resultSet);
  }

  public void delete(Product product) throws SQLException {
    String title = product.getTitle();
    String sql = "DELETE FROM products WHERE title = ?";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1, title);
    ps.execute();
  }

  private List<Product> makeProductsList(ResultSet resultSet) throws SQLException {
    List<Product> products = new ArrayList<>();

    while (resultSet.next()) {
      String title = resultSet.getString("title");
      int cost = resultSet.getInt("cost");

      Product product = new Product(title,cost);
      products.add(product);
    }
    return products;
  }

}
