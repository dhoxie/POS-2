package com.scottsdaleair;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;


public class App extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception{
    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml").toURI().toURL();
    Parent root = fxmlLoader.load(url);

    primaryStage.setTitle("POS2 - Scottsdale Airport");
    Scene home = new Scene(root, 1280, 800);
    //home.getStylesheets().add("./css/default.css");
    primaryStage.setScene(home);
    primaryStage.show();

    System.out.println("hello world again!");
  }

  public static void main(String[] args) {
    System.out.println("hello world");
    launch(args);
  }

  // ============================  M E T H O D S  =========================================


}
