module com.example.listview {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.listview to javafx.fxml;
    exports com.example.listview;
}