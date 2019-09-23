package com.kelvin.controller;

import com.kelvin.entity.category;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class categorycontroller implements Initializable {
    public TextField txtIdCat;
    public TextField txtNameCat;
    public TableView<category> tabCategory;
    public TableColumn<category,String> col01cat;
    public TableColumn<category,String>  col02cat;
    public com.kelvin.controller.maincontroller maincontroller;
    public Button savebtn;
    public ObservableList<category> categories ;

    public void setMaincontroller(maincontroller maincontroller) {
        this.maincontroller= maincontroller;
        tabCategory.setItems(maincontroller.getCategories());
    }


    public void btnSaveCat(ActionEvent actionEvent) {
        boolean found=false;
        Alert alert=new Alert(Alert.AlertType.ERROR);
        for (category i:maincontroller.getCategories()){
            if (i.getName().equals(txtNameCat.getText())){
                found=true;
            }
        }
        if (found){
            alert.setContentText("Duplicate category name");
            alert.setTitle("Error");
            alert.show();
        }
        else{
            if (txtNameCat.getText().isEmpty() || txtIdCat.getText().isEmpty()){
                alert.setTitle("Error");
                alert.setContentText("Please fill category name");
                alert.show();
            }
            else{
                category c=new category();
                c.setId(Integer.valueOf(txtIdCat.getText()));
                c.setName(txtNameCat.getText());
                maincontroller.getCategories().add(c);
            }
        }
        txtIdCat.clear();
        txtNameCat.clear();
            }
            
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col01cat.setCellValueFactory(data ->{
            category c = data.getValue();
            return new SimpleStringProperty(c.getName());
        });

        col02cat.setCellValueFactory(data ->{
            category c = data.getValue();
            return new SimpleStringProperty(String.valueOf(c.getId()));
        });

    }

    }

