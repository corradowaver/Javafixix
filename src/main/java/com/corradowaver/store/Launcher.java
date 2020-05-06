package com.corradowaver.store;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.AppController;

import java.net.URL;

public class Launcher extends Application {

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    URL resource = getClass().getResource("/fxml/Main.fxml");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(resource);
    loader.setController(new AppController());
    Scene rootScene = new Scene(loader.load());
    stage.setScene(rootScene);
    stage.show();
  }
}
