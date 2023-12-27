package com.course.client;

import com.course.client.service.context.ModelContext;
import com.course.client.service.context.UiContext;
import com.course.client.ui.SceneProvider;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        ModelContext modelContext = new ModelContext(this);
        UiContext uiContext = new UiContext(stage);

        modelContext.getLogger().info("Application startup");

        stage.setTitle("Инвентаризация");
        stage.getIcons().add(new Image("Images/clipboard.png"));
        stage.setScene(new SceneProvider().getPreparedScene("Auth/LoginView.fxml", modelContext, uiContext));
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }

    public void exit()
    {
        try
        {
            stop();
            System.exit(0);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}