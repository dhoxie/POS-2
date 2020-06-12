package com.scottsdaleair.controller;

import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.Invoice;
import com.scottsdaleair.data.PhoneNumber;
import com.scottsdaleair.data.Vehicle;
import com.scottsdaleair.pdfGenerator.PDFInvoice;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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


public class CustomerProfileController {

    @FXML
    private Label labelCustName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtState;
    @FXML
    private TextField txtZIP;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtHomePhone;
    @FXML
    private TextField txtCellPhone;
    @FXML
    private TextField txtWorkPhone;
    @FXML
    private TextArea txtNotes;
    @FXML
    private TextArea txtPrivateNotes;

    @FXML
    private TableView tblAutomobileResults;
    @FXML
    private TableView tblInvoiceResults;

    @FXML
    private Button btnPOSNAV;
    @FXML
    private Button btnCustomersNAV;


    public void loadData(Customer cust){
        labelCustName.setText(cust.getFname() + " " + cust.getLname());
        String[] addr = cust.getAddress().split(" ");
        txtZIP.setText(addr[addr.length-1]);
        txtState.setText(addr[addr.length-2]);
        txtCity.setText(addr[addr.length-3].substring(0, addr[addr.length-3].length()-1));
        String adr = "";
        for (int i = 0; i < addr.length-3; i++) {
            adr += addr[i];
            adr += " ";
        }
        txtAddress.setText(adr);
        txtEmail.setText(cust.getEmail());
        PhoneNumber[] phones = cust.getPhones();
        if(phones != null) {
            for (PhoneNumber phone : phones) {
                if (phone.getName().equals("home")) txtHomePhone.setText(phone.getNum());
                if (phone.getName().equals("cel")) txtCellPhone.setText(phone.getNum());
                if (phone.getName().equals("work")) txtWorkPhone.setText(phone.getNum());
            }
        }

        tblAutomobileResults.setRowFactory(tblAutomobileResults -> {
            TableRow<Vehicle> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getClickCount() == 2 && (!row.isEmpty())){
                    try {
                        viewVehicle(row);
                    } catch (IOException e) {
                        new Exception("Ran into IO error in loading Vehicle Profile Screen");
                    }
                    System.out.println("Double clicked on item");
                }
            });
            return row;
        });

        TableColumn<Vehicle, Vehicle> deleteVehicle = new TableColumn<>("Remove Vehicle");
        deleteVehicle.setMinWidth(40);
        deleteVehicle.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteVehicle.setCellFactory(param -> new TableCell<Vehicle, Vehicle>(){
            private final Button deleteButton = new Button("Remove");

            @Override
            protected void updateItem(Vehicle car, boolean empty){
                super.updateItem(car, empty);

                if(car == null){
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Vehicle temp =  getTableView().getItems().get(car);
                        getTableView().getItems().remove(car);
                    }
                });
            }
        });

        tblAutomobileResults.getColumns().add(deleteVehicle);

        String[] vins = cust.getVehicleVins();
        if(vins != null) {
            Vehicle[] vehicles = new Vehicle[vins.length];
            for (int i = 0; i < vins.length; i++) {
                vehicles[i] = Vehicle.getFromDb(vins[i]);
            }
            ObservableList<Vehicle> data = FXCollections.observableArrayList();

            // Adding data
            int rows = vehicles.length;
            int cur = 0;
            while (cur != rows) {
                data.add(vehicles[cur]);
                cur++;
            }
            tblAutomobileResults.setItems(data);
        }

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

        String[] histids = cust.getHistID();
        System.out.println("Customer: " + cust.toString());
        System.out.println(histids + " - Length of Invoices");
        if(histids != null) {
            Invoice[] invoices = new Invoice[histids.length];
            for (int i = 0; i < histids.length; i++) {
                invoices[i] = Invoice.getFromDb(histids[i]);
            }
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

    private void viewVehicle(TableRow<Vehicle> row) throws IOException {
        String vin = row.getItem().getVin();
        Vehicle[] vehicleList = DBController.queryDB("id", vin, Vehicle.class);
        Vehicle car = vehicleList[0];

        Stage stage = (Stage) btnCustomersNAV.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(new File("src/main/java/com/scottsdaleair/view/Vehicle_Profile_Screen.fxml").toURI().toURL());
        Parent root = loader.load();
        VehicleProfileController controller = loader.getController();

        Invoice[] invoices = getInvoicesOfVehicle(vin);

        controller.loadData(car, invoices);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private Invoice[] getInvoicesOfVehicle(String vin){
        Invoice[] invoices = DBController.queryDB("vehicleVin", vin, Invoice.class);
        return invoices;
    }


    private void viewInvoices(TableRow<Invoice> row) {
        String invoiceNum = row.getItem().getID();
        Invoice invoice = Invoice.getFromDb(invoiceNum);
        Customer cust = Customer.getFromDb(invoice.getCustomerID());
        try {
            if (invoiceNum != null) {
                new PDFInvoice(invoice).start();
                File inv = new File(invoice.getID() + cust.getFname() + cust.getLname() + ".pdf");
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
