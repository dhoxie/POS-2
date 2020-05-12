package com.scottsdaleair;

import javafx.application.Application;
import javafx.stage.Stage;
import com.scottsdaleair.utils.Config;
public class App extends Application {
  private final POSWindow window = new POSWindow();
  private static Config theConfig; 
  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle(window.title);
    primaryStage.setScene(window.getScene());
    primaryStage.show();
  }
  @Override 
  public void stop()throws Exception{
    theConfig = Config.getConfig();
    theConfig.finalize();
    theConfig = null;
  }
  

  public static void main(String[] args) {
    launch(args);
  }


}