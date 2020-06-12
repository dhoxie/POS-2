package com.scottsdaleair.controller;

import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.Invoice;
import com.scottsdaleair.data.Vehicle;
import com.scottsdaleair.pdfGenerator.PDFInvoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class VehicleProfileController {

    @FXML
    private Label labelCarName;
    @FXML
    private TextField txtLicensePlate;
    @FXML
    private TextField txtYear;
    @FXML
    private TextField txtMake;
    @FXML
    private TextField txtModel;
    @FXML
    private TextField txtMileage;
    @FXML
    private TextField txtMotor;
    @FXML
    private TextField txtVIN;
    @FXML
    private TextArea txtNotes;
    @FXML
    private TextArea txtPrivateNotes;
    @FXML
    private TableView tblInvoiceResults;

    @FXML
    private Button btnPOSNAV;
    @FXML
    private Button btnCustomersNAV;
    @FXML
    private Button btnInventoryNAV;

    public void loadData(Vehicle vehicle, Invoice[] invoices){
        labelCarName.setText(vehicle.getMake() + " " + vehicle.getModel());
        txtLicensePlate.setText(vehicle.getPlate());
        txtMake.setText(vehicle.getMake());
        txtYear.setText(vehicle.getYear());
        txtMileage.setText(vehicle.getMileage());
        txtModel.setText(vehicle.getModel());
        txtMotor.setText(vehicle.getMotor());
        txtVIN.setText(vehicle.getVin());
        /* Ask Luke about field for private and public notes on vehicle */
        txtNotes.setText(vehicle.getComments());

        tblInvoiceResults.setRowFactory(tblInvoiceResults -> {
            TableRow<Invoice> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getClickCount() == 2 && (!row.isEmpty())){
                    viewInvoices(row);
                    System.out.println("Double clicked on item");
                }
            });
            return row;
        });

        if(invoices != null) {
            ObservableList<Invoice> data = FXCollections.observableArrayList();

            // Adding data
            int rows = invoices.length;
            int cur = 0;
            while (cur != rows) {
                data.add(invoices[cur]);
                cur++;
            }

            tblInvoiceResults.setItems(data);
        }
    }


    private void viewInvoices(TableRow<Invoice> row) {
        String invoiceNum = row.getItem().getId();
        Invoice invoice = Invoice.getFromDb(invoiceNum);
        Customer cust = Customer.getFromDb(invoice.getCustomerID());
        try {
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


    // Scene Changing
    @FXML
    private void changeScene(ActionEvent event) throws IOException {
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
        } else if (event.getSource() == btnInventoryNAV) {
            stage = (Stage) btnPOSNAV.getScene().getWindow();
            url = new File("src/main/java/com/scottsdaleair/view/Inventory_Screen.fxml").toURI().toURL();
        }else {
            stage = (Stage) btnCustomersNAV.getScene().getWindow();
            url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml")
                    .toURI().toURL();
        }

        root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
