package com.example.demo21;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class HelloControllusersATS {

    public TextField txt_type1;
    @FXML
    private TableView<usersATS> table_users;

    @FXML
    private TableColumn<usersATS, Integer> col_id;

    @FXML
    private TableColumn<usersATS, String> col_username;

    @FXML
    private TableColumn<usersATS, String> col_password;

    @FXML
    private TableColumn<usersATS, String> col_email;

    @FXML
    private TableColumn<usersATS, String> col_type;
    @FXML
    private TableColumn<usersATS, String> col_type1;

    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_type;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField filterField;


    ObservableList<usersATS> listM;
    ObservableList<usersATS> dataList;



    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    @FXML
    void getSelected (MouseEvent event){
        index = table_users.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_username.setText(col_username.getCellData(index).toString());
        txt_password.setText(col_password.getCellData(index).toString());
        txt_email.setText(col_email.getCellData(index).toString());
        txt_type.setText(col_type.getCellData(index).toString());
        txt_type1.setText(col_type1.getCellData(index).toString());

    }

    // Обновление пользователей //

    public void Edit (){
        try {
            conn = ConnectSql.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_username.getText();
            String value3 = txt_password.getText();
            String value4 = txt_email.getText();
            String value5 = txt_type.getText();
            String value6 = txt_type1.getText();
            String sql = "update Sotrudniki set user_id= '"+value1+"',User_name= '"+value2+"',Login_user= '"+value3+"',Pass_user= '"+value4+"',Email_user= '"+value5+"',Pass2_user= '"+value6+"' where user_id='"+value1+"'";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Обновление");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    // Удаление пользователей //

    public void Delete(){
        conn = ConnectSql.ConnectDb();
        String sql = "delete from Sotrudniki where user_id = ?";
        try {
            assert conn != null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Пользователь удален");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<usersATS,Integer>("user_id"));
        col_username.setCellValueFactory(new PropertyValueFactory<usersATS,String>("User_name"));
        col_password.setCellValueFactory(new PropertyValueFactory<usersATS,String>("Login_user"));
        col_email.setCellValueFactory(new PropertyValueFactory<usersATS,String>("Pass_user"));
        col_type.setCellValueFactory(new PropertyValueFactory<usersATS,String>("Email_user"));
        col_type1.setCellValueFactory(new PropertyValueFactory<usersATS,String>("Pass2_user"));

        listM = ConnectSql.getDatausers();
        table_users.setItems(listM);
    }

    // Поиск пользователей //

    @FXML
    void search_user() {
        col_id.setCellValueFactory(new PropertyValueFactory<usersATS,Integer>("user_id"));
        col_username.setCellValueFactory(new PropertyValueFactory<usersATS,String>("User_name"));
        col_password.setCellValueFactory(new PropertyValueFactory<usersATS,String>("Login_user"));
        col_email.setCellValueFactory(new PropertyValueFactory<usersATS,String>("Pass_user"));
        col_type.setCellValueFactory(new PropertyValueFactory<usersATS,String>("Email_user"));
        col_type1.setCellValueFactory(new PropertyValueFactory<usersATS,String>("Pass2_user"));

        dataList = ConnectSql.getDatausers();
        table_users.setItems(dataList);
        FilteredList<usersATS> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getUser_name().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches username
                } else if (person.getLogin_user().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches password
                }else if (person.getEmail_user().toLowerCase().contains(lowerCaseFilter)) {
                   return true; // Filter matches password
                }
                else if (String.valueOf(person.getPass_user()).contains(lowerCaseFilter))
                    return true;// Filter matches email

                else
                    return false;
            });
        });
        SortedList<usersATS> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());
        table_users.setItems(sortedData);
    }

    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        search_user();

    }

    @FXML
    private Button Button1;

    @FXML
    void NZ(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-viewusersATS"));
        Stage stage1 = (Stage) Button1.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("База АТС Администратор");
        stage.setScene(scene);
        stage.show();
    }
}