package com.example.ptp04_2072008.controller;

import com.example.ptp04_2072008.HelloApplication;
import com.example.ptp04_2072008.model.Category;
import com.example.ptp04_2072008.model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.example.ptp04_2072008.dao.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class mainController {
    public MenuBar menuBar;
    @FXML
    public TableView<Item> tableView;
    @FXML
    public TableColumn<Object, Object> colId;
    @FXML
    public TableColumn<Object, Object> colName;
    @FXML
    public TableColumn<Object, Object> colPrice;
    @FXML
    public TableColumn<Object, Object> colKat;
    @FXML
    public TextField txtId;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtPrice;
    @FXML
    public TextArea txtaDesc;
    @FXML
    public ComboBox<Category> cBoxKat;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnReset;
    public Button btnUpdate;
    public Button btnDel;
    ObservableList <Category> katList;
    ObservableList <Item> itemList;
    categoryDao katDao = new categoryDao();
    itemDao itemDao = new itemDao();
    @FXML
    private Stage stage;
    @FXML
    private FXMLLoader fxmlLoader;
    private ObservableList<Category> namaKategori;

    public void initialize() throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("category-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage = new Stage();
        stage.setTitle("Category Management");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        tampilan();
        btnDel.setDisable(true);
        btnUpdate.setDisable(true);
    }

    public void addData(ActionEvent actionEvent) {
        itemDao.setData(new Item(Integer.parseInt(txtId.getText()), txtName.getText(), Double.parseDouble(txtPrice.getText()), txtaDesc.getText(), cBoxKat.getValue()));
        tampilan();
        reset();
    }

    public void reset() {
        txtaDesc.clear();
        txtId.clear();
        txtName.clear();
        txtPrice.clear();
        cBoxKat.setValue(null);
    }

    public void upData(ActionEvent actionEvent) {
    }

    public void delData(ActionEvent actionEvent) {
    }

    public void tampilan(){
        itemList = itemDao.getData();
        tableView.setItems(itemList);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colKat.setCellValueFactory(new PropertyValueFactory<>("category"));
        katList = katDao.getData();
        cBoxKat.setItems(katList);
    }

    public void gotoKategori() {
        stage.showAndWait();
        kategoriController kControl = fxmlLoader.getController();
        katList = kControl.getKatList();
        namaKategori = FXCollections.observableArrayList();
        cBoxKat.setItems(namaKategori);
        cBoxKat.getSelectionModel().select(0);
        tampilan();
    }

    public void gotoClose(ActionEvent actionEvent) {
        txtaDesc.getScene().getWindow().hide();
    }
}
