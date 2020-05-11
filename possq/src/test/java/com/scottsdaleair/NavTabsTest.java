package com.scottsdaleair;

import static org.testfx.api.FxAssert.verifyThat;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.junit.Test;
import org.testfx.api.FxRobotException;


public class NavTabsTest extends AppTest {

  final String navPaneID = "#paneNav";
  final String paneCustID = "#paneCustomers";
  final String panePOSID = "#panePOS";


  @Test(expected = FxRobotException.class)
  public void clickOnBogusNavElement() {
    clickOn("#btnBogusNAV");
  }

  @Test
  public void ensureSelectingCustomersNavChangesScene() {
    String btnCustNav = "#btnCustomersNAV";
    clickOn(btnCustNav);
    sleep(1000);
    verifyThat(paneCustID, (AnchorPane pane) -> {
      Label paneLabel = (Label) pane.getChildren().get(0);
      return paneLabel.getText().equals("Customers");
    });
  }

  @Test
  public void ensureSelectingPOSNavChangesScene() {
    String btnPOSNav = "#btnPOSNAV";
    clickOn(btnPOSNav);
    sleep(1000);
    verifyThat(panePOSID, (AnchorPane pane) -> {
      Label paneLabel = (Label) pane.getChildren().get(0);
      return paneLabel.getText().equals("Point of Sale");
    });
  }
}
