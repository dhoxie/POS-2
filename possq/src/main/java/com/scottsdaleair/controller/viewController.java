package com.scottsdaleair.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class viewController {

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



}

