package com.course.client.ui;

import com.course.client.service.context.ModelContext;
import com.course.client.service.context.UiContext;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneProvider
{
    public Scene getPreparedScene(String fxmlFile, ModelContext modelContext, UiContext uiContext)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
            Parent root = loader.load();
            SceneController controller = loader.getController();
            controller.initController(modelContext, uiContext);
            return new Scene(root);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
