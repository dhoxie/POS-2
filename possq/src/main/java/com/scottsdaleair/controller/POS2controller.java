package com.scottsdaleair.controller;

import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.Invoice;
import com.scottsdaleair.email.Email;
import com.scottsdaleair.email.SendInvoice;
import com.scottsdaleair.pdfGenerator.PDFInvoice;
import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class POS2controller {

  // Nav Buttons
  @FXML
  private Button btnPOSNAV;
  @FXML
  private Button btnCustomersNAV;

  // Customer Search Screen
  @FXML
  private TextField txtFirstNameSearch;
  @FXML
  private TextField txtLastNameSearch;
  @FXML
  private TextField txtAddressSearch;
  @FXML
  private TableView<Customer> tblCustomerResults;

  // POS Search Screen
  @FXML
  private TextField txtInvoiceNum;
  @FXML
  private TextField txtEmailSearch;


  // -------------- M E T H O D S --------------

  // Scene Changing
  @FXML
  private void changeScene(ActionEvent event) throws Exception {
    Parent root;
    URL url;
    Stage stage;

    if (event.getSource() == btnPOSNAV) {
      stage = (Stage) btnPOSNAV.getScene().getWindow();
      url = new File("src/main/java/com/scottsdaleair/view/POS_Search_Screen.fxml").toURI().toURL();
    } else if (event.getSource() == btnCustomersNAV) {
      stage = (Stage) btnCustomersNAV.getScene().getWindow();
      url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml")
        .toURI().toURL();
    } else {
      stage = (Stage) btnCustomersNAV.getScene().getWindow();
      url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml")
        .toURI().toURL();
    }

    root = FXMLLoader.load(url);
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  // PDF Generation Method
  @FXML
  private void genPDF(ActionEvent event) throws Exception {
    // 122125
    String invoiceNum = txtInvoiceNum.getText();
    if (invoiceNum.equals("")) {
      // @kayla
      // pop up to say that there needs to be an invoice num enterend;
      buildPopup((Stage) btnPOSNAV.getScene().getWindow(), "Invoice Field is needed");
    }
    else {
      Invoice invoice = Invoice.getFromDb(invoiceNum);
      Customer cust = Customer.getFromDb(invoice.getCustomerID());
      try {
        //
        if (invoiceNum != null) {
          new PDFInvoice(invoice).start();
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

  @FXML
  private void emailPDF(ActionEvent event) throws Exception {
    String invoiceNum = txtInvoiceNum.getText();
    String email = txtEmailSearch.getText();

    if (invoiceNum.equals("")) {
      // @kayla
      // pop up to say that there needs to be an invoice num enterend;
      buildPopup((Stage) btnPOSNAV.getScene().getWindow(), "Invoice Field is needed");
    }
    else if (email.equals("")) {
      // @kayla
      // pop up to say that there needs to be an email entered ;
      buildPopup((Stage) btnPOSNAV.getScene().getWindow(), "Email Field is needed");
    }
    else {
      Invoice invoice2 = Invoice.getFromDb(invoiceNum);
      Customer cust = Customer.getFromDb(invoice2.getCustomerID());
      // not checking for if already created yet...
      Invoice invoice = Invoice.getFromDb(invoiceNum);
      new PDFInvoice(invoice).start();
      Email theEmail = new Email("This is your invoice from Northwest Automotive Center ",
              invoice.getId() + cust.getFname() + cust.getLname() + ".pdf");
      SendInvoice tmp = new SendInvoice(email, theEmail);
      tmp.send();
    }
  }

  private void buildPopup(Stage stage, String labelText){
    Popup popup = new Popup();
    Label label = new Label(labelText);
    label.setStyle("-fx-background-color: white;");
    Button button = new Button("OK");
    label.setMinWidth(100);
    label.setMinHeight(80);

    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        if(popup.isShowing()){
          popup.hide();
        }
      }
    };

    button.setOnAction(event);
    popup.getContent().add(label);
    popup.getContent().add(button);
    popup.show(stage);
  }

  // Add Customers to table ----- Refactor this later for more versatility. Works
  // for basic data testing though
  @FXML
  private void buildData() {
    tblCustomerResults.getItems().clear();
    Customer[] customers;
    if (!txtFirstNameSearch.getText().isEmpty()) {
      customers = DatabaseGetter.queryCustomers("fname", txtFirstNameSearch.getText());
    } else if (!txtLastNameSearch.getText().isEmpty()) {
      customers = DatabaseGetter.queryCustomers("lname", txtLastNameSearch.getText());
    } else if (!txtAddressSearch.getText().isEmpty()) {
      customers = DatabaseGetter.queryCustomers("address", txtAddressSearch.getText());
    } else {
      customers = DatabaseGetter.getAllCustomers();
    }

    ObservableList<Customer> data = FXCollections.observableArrayList();

    // Adding data
    int rows = customers.length;
    int cur = 0;
    while (cur != rows) {
      data.add(customers[cur]);
      cur++;
    }

    tblCustomerResults.setItems(data);
  }

}
