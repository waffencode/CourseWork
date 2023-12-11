package com.course.client;

import com.course.client.controllers.LoginController;
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
        stage.setScene(new LoginController(this).getScene());
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}