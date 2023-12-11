package com.course.client.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import com.course.client.InventoryApplication;

public abstract class SceneController
{
    protected Stage stage;
    protected Scene scene;

    protected InventoryApplication application;

    public SceneController(InventoryApplication application)
    {
        this.application = application;
        this.stage = application.stage;
    }

    public Scene getScene()
    {
        return scene;
    }

    public abstract void update();
}
