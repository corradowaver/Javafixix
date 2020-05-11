package com.corradowaver.app.user;

import com.corradowaver.app.table.Product;
import com.corradowaver.app.table.TableController;
import com.corradowaver.app.table.TableDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserActionsDao {
  private Scanner sc = new Scanner(System.in);
  private TableDao table = new TableDao();
  private TableController tc = new TableController();

  public void task() {
    System.out.println("\t*Running default task*");
    dropTable();
    createTable();
    System.out.print("Enter a number of products: ");
    int number = sc.nextInt();
    for (int i = 0; i < number; i++) {
      String title = "prod" + i;
      int cost = (int) (i + (Math.random() * 1000));
      add(title, cost);
    }
  }

  public void createTable() {
    try {
      tc.createNewTable();
      System.out.println("\t*new table has been created*");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void dropTable() {
    try {
      tc.deleteTable();
      System.out.println("\t*old table has been deleted*");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void printList(List<Product> products) {
    products.forEach(product -> {
      System.out.println(" ____________________ ");
      System.out.printf("|%10s | %7d|%n", product.getTitle(), product.getCost());
    });
  }

  public void showAll() {
    try {
      List<Product> products = table.getAll();
      if (!products.isEmpty()) {
        printList(products);
      } else {
        System.out.println("\t!empty products list!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void filterByPrice(int from, int to) {
    try {
      List<Product> products = table.filterByPrice(from, to);
      if (!products.isEmpty()) {
       printList(products);
      } else {
        System.out.println("\t!empty products list!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void add(String title, int cost){
    try {
      Product product = new Product(title, cost);
      if ((cost > 0) && (!table.ifExists(product))) {
        table.add(product);
        //System.out.println("\t*success*");
      } else {
        System.out.println("\t!invalid price or product already exists!");
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public void getPrice(String title) {
    try {
      Product product = new Product(title);
      if (table.ifExists(product)) {
        System.out.println(table.getPrice(product));
      } else {
        System.out.println("\t!product not found!");
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public void changePrice(String title, int newPrice) {
    try {
      Product product = new Product(title);
      if ((newPrice > 0) && table.ifExists(product)) {
        table.changePrice(product, newPrice);
        System.out.println("\t*success*");
      } else {
        System.out.println("\t!invalid price!");
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public void delete(String title) {
    try {
      Product product = new Product(title);
      if (table.ifExists(product)) {
        table.delete(product);
        System.out.println("\t*success*");
      } else {
        System.out.println("\t!product not found!");
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

}
