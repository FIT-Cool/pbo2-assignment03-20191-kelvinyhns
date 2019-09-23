package com.kelvin.controller;

import com.kelvin.Main;
import com.kelvin.entity.category;
import com.kelvin.entity.item;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class maincontroller implements Initializable {
    public TextField txtId;
    public TextField txtName;
    public ComboBox<category> combobox;
    public DatePicker date;
    public Button btnSave;
    public Button btnReset;
    public Button btnUpdate;
    public TableView<item> tableitem;

    public TableColumn<item, String> col01;
    public TableColumn<item, String> col02;
    public TableColumn<item, String> col03;
    public TableColumn<item, String> col04;
    public com.kelvin.entity.category[] category;

    private ObservableList<item> items;
    private ObservableList<category> categories;
    int hitung;

    public ObservableList<item> getItem() {
        if (items == null) {
            items = FXCollections.observableArrayList();
        }
        return items;
    }

    public ObservableList<category> getCategories() {
        if (categories == null) {
            categories = FXCollections.observableArrayList();
        }
        return categories;
    }

    public void onCategory(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/categorymanagement.fxml"));
            VBox root = loader.load();
            categorycontroller controller = loader.getController();
            controller.setMaincontroller(this);
            Stage mainStage = new Stage();
            mainStage.initModality(Modality.WINDOW_MODAL);
            mainStage.setTitle("Category Management");
            mainStage.setScene(new Scene(root));
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void closeAct(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void resetAct(ActionEvent actionEvent) {
        txtName.setText("");
        txtId.setText("");
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
    }

    public void updateAct(ActionEvent actionEvent) {
        item i = tableitem.getSelectionModel().getSelectedItem();
        i.setName(txtName.getText());
        i.setId(txtId.getText());
        i.setCategory((combobox.getValue()));
        i.setDate(date.getValue());
        tableitem.refresh();
        btnSave.setDisable(false);
        btnReset.setDisable(true);
    }

    public void saveAct(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (txtName.getText().isEmpty() || txtId.getText().isEmpty() || combobox.getValue() == null) {
            alert.setTitle("Error");
            alert.setContentText("Please fill category/name/id");
            alert.show();
        } else {
            item i = new item();
            i.setName(txtName.getText());
            hitung = (int) items.stream().filter(p -> p.getName().equalsIgnoreCase(txtName.getText())).count();
            if (hitung > 0) {
                alert.setTitle("Error");
                alert.setContentText("Duplicate item name");
                alert.show();
            } else {
                i.setName(txtName.getText().trim());
                i.setId((txtId.getText().trim()));
                i.setCategory((combobox.getValue()));
                items.add(i);
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableitem.setItems(this.getItem());
        combobox.setItems(this.getCategories());
        col01.setCellValueFactory(data -> {
            item i = data.getValue();
            return new SimpleStringProperty(i.getId());
        });
        col02.setCellValueFactory(data -> {
            item i = data.getValue();
            return new SimpleStringProperty(i.getName());
        });
        col03.setCellValueFactory(data -> {
            item i = data.getValue();
            return new SimpleStringProperty(i.getCategory().getName());
        });
        col04.setCellValueFactory(data -> {
            item i = data.getValue();
            return new SimpleStringProperty(String.valueOf(i.getDate()));
        });
    }


    public void tableClicked(MouseEvent mouseEvent) {
        if (tableitem.getSelectionModel().getSelectedIndex() > -1) {
            item i = tableitem.getSelectionModel().getSelectedItem();
            txtName.setText(i.getName());
            txtId.setText(String.valueOf(i.getId()));
            combobox.getSelectionModel().select(i.getCategory());
            date.getEditor().setText(String.valueOf(i.getDate()));
            btnUpdate.setDisable(false);
            btnSave.setDisable(true);
        }
    }
}
