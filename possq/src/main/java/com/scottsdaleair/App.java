package com.scottsdaleair;

import java.io.IOException;
import com.scottsdaleair.utils.Configurator;
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
    try {
      Configurator.load();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    launch(args);
  }


}