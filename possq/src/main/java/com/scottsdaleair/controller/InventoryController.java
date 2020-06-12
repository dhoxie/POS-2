package com.scottsdaleair.controller;

import com.scottsdaleair.data.*;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class InventoryController {

    // Nav Buttons
    @FXML
    private Button btnPOSNAV;
    @FXML
    private Button btnCustomersNAV;
    @FXML
    private Button btnInventoryNAV;

    //TableViews
    @FXML
    private TableView tblPartsResults;
    @FXML
    private TableView tblServicesResults;
    @FXML
    private TableView tblKitsResults;



    public void loadData(){
        loadParts();
        loadServiecs();
        loadKits();
    }

    private void loadParts(){
        TableColumn<Part, Part> deletePart = new TableColumn<>("Remove Part");
        deletePart.setMinWidth(40);
        deletePart.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deletePart.setCellFactory(param -> new TableCell<Part, Part>(){
            private final Button deleteButton = new Button("Remove");
            @Override
            protected void updateItem(Part part, boolean empty){
                super.updateItem(part, empty);
                if(part == null){
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Vehicle temp =  getTableView().getItems().get(car);
                        getTableView().getItems().remove(part);
                    }
                });
            }
        });
        TableColumn<Part, Part> editPart = new TableColumn<>("Edit Part");
        editPart.setMinWidth(40);
        editPart.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        editPart.setCellFactory(param -> new TableCell<Part, Part>(){
            private final Button editButton = new Button("Remove");
            @Override
            protected void updateItem(Part part, boolean empty){
                super.updateItem(part, empty);
                if(part == null){
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);
                editButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Vehicle temp =  getTableView().getItems().get(car);
                        editPart(part);
                    }
                });
            }
        });
        tblPartsResults.getColumns().add(deletePart);
        tblPartsResults.getColumns().add(editPart);

        Part[] parts = DatabaseGetter.getAll(Part.class);
        if(parts != null) {
            ObservableList<Part> data = FXCollections.observableArrayList();

            // Adding data
            int rows = parts.length;
            int cur = 0;
            while (cur != rows) {
                data.add(parts[cur]);
                cur++;
            }
            tblPartsResults.setItems(data);
        }
    }

    private void loadServiecs(){
        TableColumn<Service, Service> deleteService = new TableColumn<>("Remove Service");
        deleteService.setMinWidth(40);
        deleteService.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteService.setCellFactory(param -> new TableCell<Service, Service>(){
            private final Button deleteButton = new Button("Remove");
            @Override
            protected void updateItem(Service service, boolean empty){
                super.updateItem(service, empty);
                if(service == null){
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Vehicle temp =  getTableView().getItems().get(car);
                        getTableView().getItems().remove(service);
                    }
                });
            }
        });
        TableColumn<Service, Service> editService = new TableColumn<>("Edit Service");
        editService.setMinWidth(40);
        editService.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        editService.setCellFactory(param -> new TableCell<Service, Service>(){
            private final Button editButton = new Button("Remove");
            @Override
            protected void updateItem(Service service, boolean empty){
                super.updateItem(service, empty);
                if(service == null){
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);
                editButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Vehicle temp =  getTableView().getItems().get(car);
                        editService(service);
                    }
                });
            }
        });
        tblServicesResults.getColumns().add(deleteService);
        tblServicesResults.getColumns().add(editService);

        Service[] services = DatabaseGetter.getAll(Service.class);
        if(services != null) {
            ObservableList<Service> data = FXCollections.observableArrayList();

            // Adding data
            int rows = services.length;
            int cur = 0;
            while (cur != rows) {
                data.add(services[cur]);
                cur++;
            }
            tblServicesResults.setItems(data);
        }
    }

    private void loadKits(){
        TableColumn<Kit, Kit> deleteKit = new TableColumn<>("Remove Service");
        deleteKit.setMinWidth(40);
        deleteKit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteKit.setCellFactory(param -> new TableCell<Kit, Kit>(){
            private final Button deleteButton = new Button("Remove");
            @Override
            protected void updateItem(Kit kit, boolean empty){
                super.updateItem(kit, empty);
                if(kit == null){
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Vehicle temp =  getTableView().getItems().get(car);
                        getTableView().getItems().remove(kit);
                    }
                });
            }
        });
        TableColumn<Kit, Kit> editKit = new TableColumn<>("Edit Service");
        editKit.setMinWidth(40);
        editKit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        editKit.setCellFactory(param -> new TableCell<Kit, Kit>(){
            private final Button editButton = new Button("Remove");
            @Override
            protected void updateItem(Kit kit, boolean empty){
                super.updateItem(kit, empty);
                if(kit == null){
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);
                editButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Vehicle temp =  getTableView().getItems().get(car);
                        editKit(kit);
                    }
                });
            }
        });
        tblKitsResults.getColumns().add(deleteKit);
        tblKitsResults.getColumns().add(editKit);

        Kit[] kits = DatabaseGetter.getAll(Kit.class);
        if(kits != null) {
            ObservableList<Kit> data = FXCollections.observableArrayList();

            // Adding data
            int rows = kits.length;
            int cur = 0;
            while (cur != rows) {
                data.add(kits[cur]);
                cur++;
            }
            tblKitsResults.setItems(data);
        }
    }

    private void editPart(Part part) {

    }

    private void editService(Service service) {

    }

    private void editKit(Kit kit) {

    }

    @FXML
    private void addPart() {

    }

    @FXML
    private void addService() {

    }

    @FXML
    private void addKit() {

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
            url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml").toURI()
                    .toURL();
        } else if (event.getSource() == btnInventoryNAV) {
            stage = (Stage) btnPOSNAV.getScene().getWindow();
            url = new File("src/main/java/com/scottsdaleair/view/Inventory_Screen.fxml").toURI().toURL();
        }else {
            stage = (Stage) btnCustomersNAV.getScene().getWindow();
            url = new File("src/main/java/com/scottsdaleair/view/Customer_Search_Screen.fxml").toURI()
                    .toURL();
        }

        root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
