module com.course.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.course.client to javafx.fxml;
    exports com.course.client;
}