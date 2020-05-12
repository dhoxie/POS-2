package com.scottsdaleair;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

public class AppTest extends ApplicationTest {

  @Override
  public void start(Stage primaryStage) throws Exception {
    // primaryStage.setTitle(window.TITLE);
    // primaryStage.setScene(window.getScene());
    primaryStage.show();
  }

  /**
   * Sets up the app for testing.
   * @throws Exception if the app doesn't launch correctly
   */
  @Before
  public void setUp() throws Exception {
    // FxToolkit.registerPrimaryStage();
    // FxToolkit.setupApplication(App.class);
    ApplicationTest.launch(App.class);
  }

  /**
   * Ensures the app is ready for future tests.
   * @throws Exception If teardown is unsucessfull
   */
  @After
  public void tearDown() throws Exception {
    FxToolkit.hideStage();
    release(new KeyCode[] {});
    release(new MouseButton[] {});
  }

  public <T extends Node> T find(final String query) {
    return (T) lookup(query).queryAll().iterator().next();
  }

}
