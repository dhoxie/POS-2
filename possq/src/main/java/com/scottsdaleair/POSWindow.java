package com.scottsdaleair;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.File;
import java.net.URL;

public class POSWindow {
	public final int SCENE_WIDTH = 1280;
	public final int SCENE_HEIGHT = 800;
	public final String TITLE = "POS2 - Scottsdale Airport";
	private Scene scene;
	private URL url;
	private Parent root;

	public POSWindow() {
		this.scene = null;
		try {
			this.url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml").toURI().toURL();
			this.root = FXMLLoader.load(url);
			this.scene = new Scene(this.root, this.SCENE_WIDTH, this.SCENE_HEIGHT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Scene getScene() {
		return this.scene;
	}

}