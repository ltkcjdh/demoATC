package com.example.demo21;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;

public class HelloController {


    @FXML
    private Button Nev_user;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> dolg;

    @FXML
    private TextField login;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField pass2;

    @FXML
    private Label pass_text;

    @FXML
    private CheckBox showpass;

    @FXML
    private Button onButton;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    String dolg1 = null;


    @FXML
    void Buton_exet(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    public void ONComboBox(ActionEvent event) {
        dolg1 = dolg.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void Buton_on(ActionEvent event) {

        conn = ConnectSql.ConnectDb();
        String sql = "Select * from Sotrudniki where Dolgnost_user = ? and Login_user = ? and Pass_user = ? and Pass2_user = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, dolg.getSelectionModel().getSelectedItem().toString());
            pst.setString(2, login.getText());
            pst.setString(3, pass.getText());
            pst.setString(4, pass2.getText());

            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Данные введены ВЕРНО");

                if (Objects.equals(dolg1, "Администратор")) {

                    FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                    Stage stage1 = (Stage) onButton.getScene().getWindow();
                    stage1.close();


                    //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-viewusersATS.fxml"));
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view2.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    Stage stage = new Stage();
                    stage.setTitle("База АТС Администратор");
                    stage.setScene(scene);
                    stage.show();
                }

                if (Objects.equals(dolg1, "Пользователь")) {

                    FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                    Stage stage1 = (Stage) onButton.getScene().getWindow();
                    stage1.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view3.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                    Stage stage = new Stage();
                    stage.setTitle("База АТС Пользователь");
                    stage.setScene(scene);
                    stage.show();

                }
                if (Objects.equals(dolg1, "Менеджер")) {

                    FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                    Stage stage1 = (Stage) onButton.getScene().getWindow();
                    stage1.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view3.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                    Stage stage = new Stage();
                    stage.setTitle("База АТС Пользователь");
                    stage.setScene(scene);
                    stage.show();
                }

            } else JOptionPane.showMessageDialog(null, "Данные введены НЕВЕРНО");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }


    }

    @FXML
    void showpass_clik(ActionEvent event) {
        if (showpass.isSelected()) {
            pass_text.setText(pass.getText());
        } else {
            pass_text.setText("");
        }
    }


    @FXML
    void Nev_user_Clic(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Stage stage1 = (Stage) onButton.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-viewReg.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Регистрация пользователей");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Администратор", "Пользователь", "Менеджер");
        dolg.setItems(list);

    }
}