package com.scottsdaleair;

import java.io.File;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class POSWindow {
  public final int sceneWidth = 1280;
  public final int sceneHeight = 800;
  public final String title = "POS2 - Scottsdale Airport";
  private Scene scene;
  private URL url;
  private Parent root;

  /**
   * Creates a POSWindow ojbect.
   * This contains all of the setup and information for an FXML Scene.
   */
  public POSWindow() {
    this.scene = null;
    try {
      this.url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml")
          .toURI().toURL();
      this.root = FXMLLoader.load(url);
      this.scene = new Scene(this.root, this.sceneWidth, this.sceneHeight);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Scene getScene() {
    return this.scene;
  }

}
