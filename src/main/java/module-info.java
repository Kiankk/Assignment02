module com.mycompany.assignment02 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.mycompany.assignment02.controller to javafx.fxml;
    exports com.mycompany.assignment02;
    exports com.mycompany.assignment02.model;
}
