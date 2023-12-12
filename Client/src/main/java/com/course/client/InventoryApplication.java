package com.course.client;

import com.course.client.controllers.LoginController;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.SceneProvider;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryApplication extends Application
{
    public Stage stage;

    @Override
    public void start(Stage stage) throws IOException
    {
        ModelContext modelContext = new ModelContext();
        UiContext uiContext = new UiContext(stage);
        stage.setScene(new SceneProvider().getPreparedScene("LoginView.fxml", modelContext, uiContext));
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}