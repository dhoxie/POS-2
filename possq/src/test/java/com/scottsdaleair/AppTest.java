package com.scottsdaleair;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import java.io.File;
import java.net.URL;

import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.robot.Motion;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest extends ApplicationTest {

	@Override
	public void start(Stage primaryStage) throws Exception {
//		primaryStage.setTitle(window.TITLE);
//		primaryStage.setScene(window.getScene());
//		primaryStage.show();
		new App().start(primaryStage);
	}
	
//	@Test public void test_should_test() {
//		clickOn("Point of Sale");
//	}

	@Before
	public void setUp() throws Exception {
		FxToolkit.registerPrimaryStage();
		FxToolkit.setupApplication(App.class);
	}

//	@After
//	public void tearDown() throws Exception {
//		FxToolkit.hideStage();
//		release(new KeyCode[] {});
//		release(new MouseButton[] {});
//	}
//	
	@Test
	public void has_pos_navbar() {
//		TextField txfieldFirstName = find("#txtFirstNameSearch");
		clickOn("#txtFirstNameSearch");
		System.out.println("Clicked on field");
		write("This is a test!");
	
//		clickOn("")
	}

}
