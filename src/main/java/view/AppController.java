package view;

import com.corradowaver.store.user.UserActionsDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AppController {
  private static UserActionsDao ua = new UserActionsDao();

  @FXML
  private Button addButton;
  @FXML
  private TextField addTitleField;
  @FXML
  private TextField addPriceField;

  @FXML
  public void initialize() {

  }

  @FXML
  public void add() {
    String title = addTitleField.getText();
    int price = Integer.getInteger(addPriceField.getText());
    ua.add(title, price);
  }
}
