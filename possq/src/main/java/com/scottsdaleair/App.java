package com.scottsdaleair;

import java.io.IOException;
import com.scottsdaleair.utils.Configurator;
import com.scottsdaleair.utils.config.DBConfig;
import com.scottsdaleair.utils.config.EmailConfig;

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

  @Override
  public void stop() throws Exception{
    Configurator.saveAll();
  }

  public static void main(String[] args) {
    try {
      Configurator.loadConfig(DBConfig.class);
      Configurator.loadConfig(EmailConfig.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    launch(args);
  }


}