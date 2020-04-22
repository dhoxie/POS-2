package com.scottsdaleair.controller;

import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.Invoice;
import com.scottsdaleair.pdfgenerator.PdfInvoice;
import java.awt.Desktop;
import java.io.File;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Pos2controller {

  // Nav Buttons
  @FXML
  private Button btnPosNav;
  @FXML
  private Button btnCustomersNav;
  @FXML
  private Button btnInventoryNav;
  @FXML
  private Button btnCalendarNav;
  @FXML
  private Button btnReportsNav;
  @FXML
  private Button btnSettingsNav;

  // Customer Search Screen
  @FXML
  private AnchorPane paneCustomers;
  @FXML
  private TextField txtFirstNameSearch;
  @FXML
  private TextField txtLastNameSearch;
  @FXML
  private Button btnSearchCustomers;
  @FXML
  private TableView<?> tblCustomerResults;

  // POS Search Screen
  @FXML
  private AnchorPane panePos;
  @FXML
  private TextField txtFnameSearch;
  @FXML
  private TextField txtLnameSearch;
  @FXML
  private TextField txtAddressSearch;
  @FXML
  private TextField txtCitySearch;
  @FXML
  private TextField txtStateSearch;
  @FXML
  private TextField txtZipSearch;
  @FXML
  private TextField txtEmailSearch;
  @FXML
  private TextArea txtCustomerNotes;
  @FXML
  private TextField txtHomeSearch;
  @FXML
  private TextField txtCellSearch;
  @FXML
  private TextField txtWorkSearch;
  @FXML
  private TextArea txtPrivateNotes;
  @FXML
  private TextField txtInvoiceNum;
  @FXML
  private Button btnGenPdf;

  // -------------- M E T H O D S --------------
  @FXML
  private void changeScene(ActionEvent event) throws Exception {
    Stage stage;
    Parent root;
    URL url;
    FXMLLoader fxmlLoader = new FXMLLoader();

    if (event.getSource() == btnPosNav) {
      stage = (Stage) btnPosNav.getScene().getWindow();
      url = new File("src/main/java/com/scottsdaleair/view/POS_Search_Screen.fxml").toURI().toURL();
    } else if (event.getSource() == btnCustomersNav) {
      stage = (Stage) btnCustomersNav.getScene().getWindow();
      url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml")
          .toURI().toURL();
    } else {
      stage = (Stage) btnCustomersNav.getScene().getWindow();
      url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml")
          .toURI().toURL();
    }

    root = fxmlLoader.load(url);
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  private void genPdf(ActionEvent event) throws Exception {
    // 43618446
    String invoiceNum = txtInvoiceNum.getText();
    Invoice invoice = Invoice.getFromDb(invoiceNum);
    Customer cust = Customer.getFromDb(invoice.getCustomerID());
    try {
      //
      if (invoiceNum != null) {
        new PdfInvoice(invoice).start();
        File inv = new File(invoice.getId() + cust.getFname() + cust.getLname() + ".pdf");
        if (inv.exists()) {
          if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(inv);
          } else {
            System.out.println("Desktop not supported");
          }
        }
      } else {
        System.out.println("No Invoice Num");
      }
    } catch (Throwable e) {
      System.out.println("Caught");
    }

  }

}
