package view;

import com.corradowaver.app.table.Product;
import com.corradowaver.app.table.TableController;
import com.corradowaver.app.table.TableDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class AppController {
  private TableDao table = new TableDao();
  private TableController tc = new TableController();

  @FXML
  private TextArea output;
  @FXML
  private TextField input;

  @FXML
  public void initialize() {

  }

  @FXML
  public void onEnter(ActionEvent ae){
    processInput(input.getText());
    input.clear();
  }

  private void processInput(String inputArgs) {
    String[] arguments = inputArgs.split(" ");
    String command = arguments[0];
    switch (command) {
      case "/default_task":
        defaultTask(arguments[1]);
        break;
      case "/add":
        add(arguments);
        break;
      case "/delete":
        delete(arguments[1]);
        break;
      case "/drop_table":
        deleteTable();
        break;
      case "/create_table":
        createTable();
        break;
      case "/show_all":
        showAll();
        break;
      case "/filter":
        filter(arguments);
        break;
      case "/get_price":
        getPrice(arguments[1]);
        break;
      case "/change_price":
        changePrice(arguments);
        break;
      default:
        output.appendText("Invalid command.\n");
        break;
    }
  }

  private void defaultTask(String argument) {
    try {
      output.appendText("Running default task...\n");
      deleteTable();
      createTable();
      var number = Integer.parseInt(argument);
      for (int i = 0; i < number; i++) {
        String title = "prod" + i;
        int cost = (int) (i + (Math.random() * 1000));
        add(title, cost);
      }
    } catch (Exception e) {
      output.appendText(e.getMessage() + "\n");
    }
  }

  private void changePrice(String[] arguments) {
    try {
      var title = arguments[1];
      var newPrice = Integer.parseInt(arguments[2]);
      Product product = new Product(title);
      if ((newPrice > 0) && table.ifExists(product)) {
        table.changePrice(product, newPrice);
        output.appendText("Success.\n");
      } else {
        output.appendText("Invalid price.");
      }
    } catch (Exception e) {
      output.appendText(e.getMessage() + "\n");
    }
  }

  private void getPrice(String argument) {
    try {
      Product product = new Product(argument);
      if (table.ifExists(product)) {
        output.appendText(table.getPrice(product));
      } else {
        output.appendText("Product not found.\n");
      }
    } catch (Exception e) {
      output.appendText(e.getMessage() + "\n");
    }
  }

  private void filter(String[] arguments) {
    try {
      var from = Integer.parseInt(arguments[1]);
      var to = Integer.parseInt(arguments[2]);
      List<Product> products = table.filterByPrice(from, to);
      if (!products.isEmpty()) {
        printList(products);
      } else {
        output.appendText("Empty products list.\n");
      }
    } catch (Exception e) {
      output.appendText(e.getMessage() + "\n");
    }
  }

  private void showAll() {
    try {
      List<Product> products = table.getAll();
      if (!products.isEmpty()) {
        printList(products);
      } else {
        output.appendText("Empty products list.\n");
      }
    } catch (Exception e) {
      output.appendText(e.getMessage() + "\n");
    }
  }

  private void createTable() {
    try {
      tc.createNewTable();
      output.appendText("A new table has been created.\n");
    } catch (Exception e) {
      output.appendText(e.getMessage() + "\n");
    }
  }

  private void deleteTable() {
    try {
      tc.deleteTable();
      output.appendText("An old table has been deleted.\n");
    } catch (Exception e) {
      output.appendText(e.getMessage() + "\n");
    }
  }

  private void delete(String argument) {
    try {
      Product product = new Product(argument);
      if (table.ifExists(product)) {
        table.delete(product);
        output.appendText("Success.\n");
      } else {
        output.appendText("Product not found.\n");
      }
    } catch (Exception e) {
      output.appendText(e.getMessage() + "\n");
    }
  }

  private void add(String title, int cost) {
    String[] arguments = {null, title, String.valueOf(cost)};
    add(arguments);
  }

  private void add(String[] arguments) {
    try {
      var title = arguments[1];
      var cost = Integer.parseInt(arguments[2]);
      Product product = new Product(title, cost);
      if ((cost > 0) && (!table.ifExists(product))) {
        table.add(product);
        output.appendText("Success.\n");
      } else {
        output.appendText("Invalid price or product already exists.\n");
      }
    } catch (Exception e) {
      output.appendText(e.getMessage() + "\n");
    }
  }

  private void printList(List<Product> products) {
    products.forEach(product -> {
      output.appendText(" ____________________ \n");
      output.appendText(product.getTitle() + "\t" + product.getCost() + "\n");
    });
  }

}
