package com.scottsdaleair.controller;

import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.Invoice;
import com.scottsdaleair.pdfGenerator.PDFInvoice;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.awt.Desktop;

public class POS2controller {

    // Nav Buttons
    @FXML
    private Button btn_POS_NAV;
    @FXML
    private Button btn_Customers_NAV;
    @FXML
    private Button btn_Inventory_NAV;
    @FXML
    private Button btn_Calendar_NAV;
    @FXML
    private Button btn_Reports_NAV;
    @FXML
    private Button btn_Settings_NAV;


    // Customer Search Screen
    @FXML
    private AnchorPane pane_Customers;
    @FXML
    private TextField txt_FirstNameSearch;
    @FXML
    private TextField txt_LastNameSearch;
    @FXML
    private Button btn_SearchCustomers;
    @FXML
    private TableView<?> tbl_CustomerResults;



    // POS Search Screen
    @FXML
    private AnchorPane pane_POS;
    @FXML
    private TextField txt_FNameSearch;
    @FXML
    private TextField txt_LNameSearch;
    @FXML
    private TextField txt_AddressSearch;
    @FXML
    private TextField txt_CitySearch;
    @FXML
    private TextField txt_StateSearch;
    @FXML
    private TextField txt_ZipSearch;
    @FXML
    private TextField txt_EmailSearch;
    @FXML
    private TextArea txt_CustomerNotes;
    @FXML
    private TextField txt_HomeSearch;
    @FXML
    private TextField txt_CellSearch;
    @FXML
    private TextField txt_WorkSearch;
    @FXML
    private TextArea txt_PrivateNotes;
    @FXML
    private TextField txt_InvoiceNum;
    @FXML
    private Button btn_GenPDF;





    // -------------- M E T H O D S --------------
    @FXML
    private void changeScene (ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        URL url;
        FXMLLoader fxmlLoader = new FXMLLoader();

        if(event.getSource()==btn_POS_NAV){
            stage = (Stage) btn_POS_NAV.getScene().getWindow();
            url = new File("src/main/java/com/scottsdaleair/view/POS_Search_Screen.fxml").toURI().toURL();
        }
        else if(event.getSource()==btn_Customers_NAV){
            stage = (Stage) btn_Customers_NAV.getScene().getWindow();
            url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml").toURI().toURL();
        }
        else{
            stage = (Stage) btn_Customers_NAV.getScene().getWindow();
            url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml").toURI().toURL();
        }

        root = fxmlLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    @FXML
    private void genPDF(ActionEvent event) throws Exception{
        //122125
        String invoiceNum = txt_InvoiceNum.getText();
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


}
