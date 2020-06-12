package com.scottsdaleair;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
  private final POSWindow window = new POSWindow();

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle(window.title);
    primaryStage.setScene(window.getScene());
    primaryStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }


}