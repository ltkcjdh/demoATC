package com.example.demo21;


import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class HelloController3 {


    public Button Vihod_id;
    @FXML
    private TableView<BazaATS> table_users;

    @FXML
    private TableColumn<BazaATS, Integer> col_id;

    @FXML
    private TableColumn<BazaATS, String> col_username;

    @FXML
    private TableColumn<BazaATS, String> col_password;

    @FXML
    private TableColumn<BazaATS, String> col_email;

    @FXML
    private TableColumn<BazaATS, String> col_type;

    @FXML
    private TextField filterField;


    ObservableList<BazaATS> listM;
    ObservableList<BazaATS> dataList;



    int index = -1;

    Connection conn1 =null;
    ResultSet rs = null;
    PreparedStatement pst1 = null;

    // Обновление пользователей //

    public void Edit (){
        col_id.setCellValueFactory(new PropertyValueFactory<BazaATS,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<BazaATS,String>("tip"));
        col_password.setCellValueFactory(new PropertyValueFactory<BazaATS,String>("proizvoditil"));
        col_email.setCellValueFactory(new PropertyValueFactory<BazaATS,String>("model"));
        col_type.setCellValueFactory(new PropertyValueFactory<BazaATS,String>("nomer"));

        listM = ConnectSql.getBazaATS();
        table_users.setItems(listM);

    }



    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<BazaATS,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<BazaATS,String>("tip"));
        col_password.setCellValueFactory(new PropertyValueFactory<BazaATS,String>("proizvoditil"));
        col_email.setCellValueFactory(new PropertyValueFactory<BazaATS,String>("model"));
        col_type.setCellValueFactory(new PropertyValueFactory<BazaATS,String>("nomer"));

        listM = ConnectSql.getBazaATS();
        table_users.setItems(listM);
    }

    // Поиск пользователей //

    @FXML
    void search_user() {
        col_id.setCellValueFactory(new PropertyValueFactory<BazaATS,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<BazaATS,String>("tip"));
        col_password.setCellValueFactory(new PropertyValueFactory<BazaATS,String>("proizvoditil"));
        col_email.setCellValueFactory(new PropertyValueFactory<BazaATS,String>("model"));
        col_type.setCellValueFactory(new PropertyValueFactory<BazaATS,String>("nomer"));

        dataList = ConnectSql.getBazaATS();
        table_users.setItems(dataList);
        FilteredList<BazaATS> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getTip().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getProizvoditil().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (person.getNomer().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (String.valueOf(person.getModel()).contains(lowerCaseFilter))
                    return true;

                else
                    return false;
            });
        });
        SortedList<BazaATS> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());
        table_users.setItems(sortedData);
    }

    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        search_user();

    }

    public void Vihod() throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view3.fxml"));
        Stage stage1 = (Stage) Vihod_id.getScene().getWindow();
        stage1.close();


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Авторизация");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void Buton_exet(ActionEvent actionEvent) {

        System.exit(1);
    }

    @FXML
    void initialize() throws SQLException {

        }


    }
