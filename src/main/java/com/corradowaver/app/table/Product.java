package com.corradowaver.app.table;

import java.util.UUID;

public class Product {
  private String prodid;
  private String title;
  private int cost;

  public Product(String title, int cost) {
    this.prodid = UUID.randomUUID().toString();
    this.title = title;
    this.cost = cost;
  }

  public Product(String title) {
    this.prodid = UUID.randomUUID().toString();
    this.title = title;
  }

  public String getProdid() {
    return prodid;
  }

  public String getTitle() {
    return title;
  }

  public int getCost() {
    return cost;
  }

}
