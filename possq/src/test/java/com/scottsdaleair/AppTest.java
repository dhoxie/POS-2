package com.scottsdaleair;

import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

public class AppTest extends ApplicationTest {

  @Override
  public void start(Stage primaryStage) throws Exception {
    // primaryStage.setTitle(window.TITLE);
    // primaryStage.setScene(window.getScene());
    // primaryStage.show();
    new App().start(primaryStage);
  }

  // @Test public void test_should_test() {
  // clickOn("Point of Sale");
  // }

  @Before
  public void setUp() throws Exception {
    FxToolkit.registerPrimaryStage();
    FxToolkit.setupApplication(App.class);
  }

  // @After
  // public void tearDown() throws Exception {
  // FxToolkit.hideStage();
  // release(new KeyCode[] {});
  // release(new MouseButton[] {});
  // }
  //
  @Test
  public void has_pos_navbar() {
    // TextField txfieldFirstName = find("#txtFirstNameSearch");
    clickOn("#txtFirstNameSearch");
    System.out.println("Clicked on field");
    write("This is a test!");

    // clickOn("")
  }

}
