package com.course.client;

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
        ModelContext modelContext = new ModelContext(this);
        UiContext uiContext = new UiContext(stage);
        stage.setScene(new SceneProvider().getPreparedScene("Auth/LoginView.fxml", modelContext, uiContext));
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }

    public void exit()
    {
        stop();
        System.exit(0);
    }

    @Override
    public void stop()
    {

    }
}