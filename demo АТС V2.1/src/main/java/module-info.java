module com.example.demo21 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;


    opens com.example.demo21 to javafx.fxml;
    exports com.example.demo21;
}