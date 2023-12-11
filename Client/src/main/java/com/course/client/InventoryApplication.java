package com.course.client;

import com.course.client.controllers.LoginController;
import javafx.application.Application;
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
        stage.setMaximized(true);
    }

    public static void main(String[] args)
    {
        launch();
    }
}