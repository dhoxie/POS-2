package com.scottsdaleair;

import javafx.application.Application;
import javafx.stage.Stage;
import com.scottsdaleair.utils.Config;
public class App extends Application {
  private final POSWindow window = new POSWindow();
  private static Config theConfig; 
  @Override
  public void start(Stage primaryStage) throws Exception {
    System.out.println("testing config before ");
   
    primaryStage.setTitle(window.title);
    primaryStage.setScene(window.getScene());
    primaryStage.show();
    System.out.println("testing config after ");
    theConfig = Config.getConfig();
    if(theConfig != null){
      System.out.println(theConfig.getPrimaryDatabaseIp());
      theConfig.changePrimaryDatabase("thisis the new ip" , "this is the newport ");
    }else{
      System.out.println("was null");
    }
    
  }
  @Override 
  public void stop(){
    theConfig.finalize();
    theConfig = null;
  }

  public static void main(String[] args) {
    launch(args);
  }


}