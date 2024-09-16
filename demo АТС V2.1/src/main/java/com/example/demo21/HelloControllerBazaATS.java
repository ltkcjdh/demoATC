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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class HelloControllerBazaATS {

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


    ObservableList<BazaATS> listM;
    ObservableList<BazaATS> dataList;



    int index = -1;

    Connection conn1 =null;
    ResultSet rs = null;
    PreparedStatement pst1 = null;

    // Добовление пользователей //

    public void Add_users (){
        conn1 = ConnectSql.ConnectDb();
        String sql = "insert into BazaATS (tip,proizvoditil,model,nomer)values(?,?,?,? )";
        try {
            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, txt_username.getText());
            pst1.setString(2, txt_password.getText());
            pst1.setString(3, txt_email.getText());
            pst1.setString(4, txt_type.getText());
            pst1.execute();

          //  JOptionPane.showMessageDialog(null, "Пользователь добавлен");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

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

    }

    // Обновление пользователей //

    public void Edit (){
        try {
            conn1 = ConnectSql.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_username.getText();
            String value3 = txt_password.getText();
            String value4 = txt_email.getText();
            String value5 = txt_type.getText();
            String sql = "update BazaATS set id= '"+value1+"',tip= '"+value2+"',proizvoditil= '"+
                    value3+"',model= '"+value4+"',nomer= '"+value5+"' where id='"+value1+"' ";
            pst1= conn1.prepareStatement(sql);
            pst1.execute();
            JOptionPane.showMessageDialog(null, "Обновление");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    // Удаление пользователей //

    public void Delete(){
        conn1 = ConnectSql.ConnectDb();
        String sql = "delete from BazaATS where id = ?";
        try {
            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, txt_id.getText());
            pst1.execute();
            JOptionPane.showMessageDialog(null, "Пользователь удален");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

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
                    return true; // Filter matches username
                } else if (person.getProizvoditil().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches password
                }else if (person.getNomer().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches password
                }
                else if (String.valueOf(person.getModel()).contains(lowerCaseFilter))
                    return true;// Filter matches email

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

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-viewBazaATS"));
        Stage stage1 = (Stage) Vihod_id.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("База АТС Администратор");
        stage.setScene(scene);
        stage.show();
    }
}