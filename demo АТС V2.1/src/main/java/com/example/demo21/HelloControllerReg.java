package com.example.demo21;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelloControllerReg {
    @FXML
    public Button exitclik;
    @FXML
    public Button and_clik1;
    @FXML
    public RadioButton checkB;
    @FXML
    public ToggleGroup groppol;
    @FXML
    public RadioButton checkB1;
    @FXML
    public TextField Voz;
    @FXML
    public TextField Imy2ID;
    @FXML
    public TextField Imy1ID;
    @FXML
    public DatePicker Dataid;
    @FXML
    public CheckBox Soglas;
    @FXML
    public TextField Pass1_ID;
    @FXML
    public TextField Pass2_ID;
    @FXML
    public TextField Email_id;
    @FXML
    public Button exitclik2_id;
    @FXML
    private ComboBox<String> langsComboBox;


    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void and_clik(ActionEvent actionEvent) {
        if (Soglas.isSelected()) {

            // текст
            String username = Imy1ID.getText().toString();
            String username2 = Imy2ID.getText().toString();
            String email = Email_id.getText().toString();
            String pass1 = Pass1_ID.getText().toString();
            String pass2 = Pass2_ID.getText().toString();

            // число
            int vozrost_user = Integer.parseInt(Voz.getText().toString());

            // дата
            String Dolgnost = langsComboBox.getSelectionModel().getSelectedItem().toString();

            java.util.Date date =
                    java.util.Date.from(Dataid.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date Datauser = new java.sql.Date(date.getTime());

            //выбор
            ToggleGroup groppol = new ToggleGroup();
            checkB.setToggleGroup(groppol);
            checkB1.setToggleGroup(groppol);

            RadioButton selon = (RadioButton) groppol.getSelectedToggle();

            String Pol = selon.getText().toString();

            //галочка
            String Soglass = "";
            if (Soglas.isSelected()) Soglass += "даю согласие";


            conn = ConnectSql.ConnectDb();
            String sql = "insert into Sotrudniki (User_name,Login_user,Pass_user,Email_user,Pass2_user,Dolgnost_user,Vozrost_user,Data_user,Pol,Soglass)values(?,?,?,?,?,?,?,?,?,?)";
            try {
                assert conn != null;
                pst = conn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, username2);
                pst.setString(3, pass1);
                pst.setString(4, email);
                pst.setString(5, pass2);

                pst.setString(6, Dolgnost);
                pst.setInt(7, vozrost_user);

                pst.setDate(8, Datauser);
                pst.setString(9, Pol);
                pst.setString(10, Soglass);
                pst.execute();

                JOptionPane.showMessageDialog(null, "Пользователь добавлен");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else {
            System.out.print("???");
        }
    }


    @FXML
    public void ONComboBox(ActionEvent event) {
        String StringONComboBox = langsComboBox.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void exitclik(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Администратор", "Пользователь", "Менеджер","Ведимак");
        langsComboBox.setItems(list);
    }

    public void SoglasON(ActionEvent actionEvent) {


    }

    public void exitclik2(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-viewReg.fxml"));
        Stage stage1 = (Stage) exitclik2_id.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Авторизация");
        stage.setScene(scene);
        stage.show();
    }
}