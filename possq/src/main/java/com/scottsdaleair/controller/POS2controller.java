package com.scottsdaleair.controller;

import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.Invoice;
import com.scottsdaleair.pdfGenerator.PDFInvoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.File;
import java.net.URL;
import java.awt.Desktop;

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






    // -------------- M E T H O D S --------------

    // Scene Changing
    @FXML
    private void changeScene (ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        URL url;
        FXMLLoader fxmlLoader = new FXMLLoader();

        if(event.getSource()==btnPOSNAV){
            stage = (Stage) btnPOSNAV.getScene().getWindow();
            url = new File("src/main/java/com/scottsdaleair/view/POS_Search_Screen.fxml").toURI().toURL();
        }
        else if(event.getSource()==btnCustomersNAV){
            stage = (Stage) btnCustomersNAV.getScene().getWindow();
            url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml").toURI().toURL();
        }
        else{
            stage = (Stage) btnCustomersNAV.getScene().getWindow();
            url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml").toURI().toURL();
        }

        root = fxmlLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    // PDF Generation Method
    @FXML
    private void genPDF(ActionEvent event) throws Exception{
        //122125
        String invoiceNum = txtInvoiceNum.getText();
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
        }
        catch(Throwable e) {
            System.out.println("Caught");
        }
    }

    @FXML
    private void emailPDF(ActionEvent event) throws Exception{

    }


    // Add Customers to table ----- Refactor this later for more versatility.  Works for basic data testing though
    @FXML
    private void buildData(){
        tblCustomerResults.getItems().clear();
        Customer[] customers;
        if(!txtFirstNameSearch.getText().isEmpty()){
            customers = DatabaseGetter.queryCustomers("fname", txtFirstNameSearch.getText());
        }
        else if(!txtLastNameSearch.getText().isEmpty()){
            customers = DatabaseGetter.queryCustomers("lname", txtLastNameSearch.getText());
        }
        else if(!txtAddressSearch.getText().isEmpty()){
            customers = DatabaseGetter.queryCustomers("address", txtAddressSearch.getText());
        }
        else{
            customers = DatabaseGetter.getAllCustomers();
        }

        ObservableList<Customer> data = FXCollections.observableArrayList();

        //Adding data
        int rows = customers.length;
        int cur = 0;
        while(cur != rows){
            data.add(customers[cur]);
            cur++;
        }

        tblCustomerResults.setItems(data);
    }

}
