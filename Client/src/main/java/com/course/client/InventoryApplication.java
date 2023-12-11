package com.course.client;

import com.course.client.ui.NotificationDialog;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryApplication extends Application
{
    public Stage stage;

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        NotificationDialog.showInformationDialog("Test");
        NotificationDialog.showWarningDialog("Test");
        NotificationDialog.showErrorDialog("Test");
    }

    public static void main(String[] args)
    {
        launch();
    }
}