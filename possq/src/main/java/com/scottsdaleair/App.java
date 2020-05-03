package com.scottsdaleair;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml")
        .toURI().toURL();
    Parent root = fxmlLoader.load(url);

    primaryStage.setTitle("POS2 - Scottsdale Airport");
    Scene home = new Scene(root, 1280, 800);
    // home.getStylesheets().add("./css/default.css");
    primaryStage.setScene(home);
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }

  // ============================ M E T H O D S
  // =========================================


}