package com.corradowaver.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    Parent root;
    root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
    Scene rootScene = new Scene(root);
    stage.setScene(rootScene);
    stage.show();
  }
}
