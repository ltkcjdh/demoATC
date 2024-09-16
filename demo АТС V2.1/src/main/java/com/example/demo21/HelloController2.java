package com.example.demo21;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;



public class HelloController2 {

    @FXML
    public Label name2;
    public Button baza_user_id;
    public Button Buton_beg_id;
    public Button baza_sklad_id;


    @FXML
    public void Buton_exet(ActionEvent actionEvent) {

        System.exit(1);
    }


    @FXML
    void initialize() throws SQLException {

      //  name2.setText(login123);
    }

    public void Buton_beg_clic (ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view2.fxml"));
        Stage stage1 = (Stage) Buton_beg_id.getScene().getWindow();
        stage1.close();


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("База АТС Администратор");
        stage.setScene(scene);
        stage.show();
    }

    public void baza_user_clic(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view2.fxml"));
        Stage stage1 = (Stage) baza_user_id.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-viewusersATS.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = new Stage();
        stage.setTitle("База пользователей АТС");
        stage.setScene(scene);
        stage.show();
    }

    public void baza_sklad_clic(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view2.fxml"));
        Stage stage1 = (Stage) baza_user_id.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-viewBazaATS.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = new Stage();
        stage.setTitle("База Склад АТС");
        stage.setScene(scene);
        stage.show();

    }
}
