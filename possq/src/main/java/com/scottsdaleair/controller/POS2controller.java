package com.scottsdaleair.controller;

import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.Invoice;
import com.scottsdaleair.email.Email;
import com.scottsdaleair.email.SendInvoice;
import com.scottsdaleair.pdfGenerator.PDFInvoice;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class POS2controller {

  // Nav Buttons
  @FXML
  private Button btnPOSNAV;
  @FXML
  private Button btnCustomersNAV;
  @FXML
  private Button btnInventoryNAV;

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
  private void changeScene(final ActionEvent event) throws IOException {
    Parent root;
    Stage stage;
    FXMLLoader loader;

    if (event.getSource() == btnPOSNAV) {
      stage = (Stage) btnPOSNAV.getScene().getWindow();
      loader = new FXMLLoader(new File("src/main/java/com/scottsdaleair/view/POS_Search_Screen.fxml").toURI().toURL());
    } else if (event.getSource() == btnCustomersNAV) {
      stage = (Stage) btnCustomersNAV.getScene().getWindow();
      loader = new FXMLLoader(
          new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml").toURI().toURL());
    } else if (event.getSource() == btnInventoryNAV) {
      stage = (Stage) btnPOSNAV.getScene().getWindow();
      loader = new FXMLLoader(new File("src/main/java/com/scottsdaleair/view/Inventory_Screen.fxml").toURI().toURL());
    } else {
      stage = (Stage) btnCustomersNAV.getScene().getWindow();
      loader = new FXMLLoader(
          new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml").toURI().toURL());
    }
    root = loader.load();

    if (event.getSource() == btnInventoryNAV) {
      final InventoryController controller = loader.getController();
      controller.loadData();
    }

    final Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  // PDF Generation Method
  @FXML
  private void genPDF(final ActionEvent event) throws Exception {
    final Task<Void> pdfTask = new Task<>() {
      @Override
      public Void call() {
        final String invoiceNum = txtInvoiceNum.getText();
        final Invoice invoice = Invoice.getFromDb(invoiceNum);
        final Customer cust = Customer.getFromDb(invoice.getCustomerID());
        try {
          //
          if (invoiceNum != null) {
            new PDFInvoice(invoice).start();
            final File inv = new File(invoice.getId() + cust.getFname() + cust.getLname() + ".pdf");
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
        } catch (final Throwable e) {
          System.out.println("Caught");
        }
        return null;
      }
    };
    // Event handlers can be attached to pdfTask for user notification.
    new Thread(pdfTask).start();
  }

  @FXML
  private void emailPDF(final ActionEvent event) throws Exception {
    final String invoiceNum = txtInvoiceNum.getText();
    final String email = txtEmailSearch.getText();

    if (invoiceNum.equals("")) {
      buildPopup((Stage) btnPOSNAV.getScene().getWindow(), "Invoice Field is needed");
    } else if (email.equals("")) {
      buildPopup((Stage) btnPOSNAV.getScene().getWindow(), "Email Field is needed");
    } else {
      final Invoice invoice2 = Invoice.getFromDb(invoiceNum);
      final Customer cust = Customer.getFromDb(invoice2.getCustomerID());
      // not checking for if already created yet...
      final Invoice invoice = Invoice.getFromDb(invoiceNum);
      new PDFInvoice(invoice).start();
      final Email theEmail = new Email("This is your invoice from Northwest Automotive Center ",
          invoice.getId() + cust.getFname() + cust.getLname() + ".pdf");
      final SendInvoice tmp = new SendInvoice(email, theEmail);
      tmp.send();
    }
  }

  private void buildPopup(final Stage stage, final String labelText) {
    final Popup popup = new Popup();
    final Label label = new Label(labelText);
    label.setStyle("-fx-background-color: white;");
    final Button button = new Button("OK");
    label.setMinWidth(100);
    label.setMinHeight(80);

    final EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      @Override
      public void handle(final ActionEvent actionEvent) {
        if (popup.isShowing()) {
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
    final HashMap<String, String> query = new HashMap<>();

    if (!txtFirstNameSearch.getText().isEmpty()) {
      query.put("fname", txtFirstNameSearch.getText());
    }
    if (!txtLastNameSearch.getText().isEmpty()) {
      query.put("lname", txtLastNameSearch.getText());
    }
    if (!txtAddressSearch.getText().isEmpty()) {
      query.put("address", txtAddressSearch.getText());
    }

    if (query.isEmpty()) {
      customers = DBController.getAll(Customer.class);
    } else {
      customers = DBController.queryDB(query, Customer.class);
    }

    tblCustomerResults.setRowFactory(tblCustomerResults -> {
      final TableRow<Customer> row = new TableRow<>();
      row.setOnMouseClicked(mouseEvent -> {
        if (mouseEvent.getClickCount() == 2 && (!row.isEmpty())) {
          try {
            viewCustomer(row);
          } catch (final IOException e) {
            new Exception("Ran into IO error in loading Customer Profile Screen");
          }
          System.out.println("Double clicked on item");
        }
      });
      return row;
    });

    final ObservableList<Customer> data = FXCollections.observableArrayList();

    // Adding data
    final int rows = customers.length;
    int cur = 0;
    while (cur != rows) {
      data.add(customers[cur]);
      cur++;
    }

    tblCustomerResults.setItems(data);

  }

  private void viewCustomer(final TableRow<Customer> row) throws IOException {
    final String id = row.getItem().getId();
    final Customer[] custList = DBController.queryDB("id", id, Customer.class);
    final Customer cust = custList[0];

    final Stage stage = (Stage) btnCustomersNAV.getScene().getWindow();

    final FXMLLoader loader = new FXMLLoader(
        new File("src/main/java/com/scottsdaleair/view/Customer_Profile_Screen.fxml").toURI().toURL());
    final Parent root = loader.load();
    final CustomerProfileController controller = loader.getController();
    controller.loadData(cust);
    final Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

}
