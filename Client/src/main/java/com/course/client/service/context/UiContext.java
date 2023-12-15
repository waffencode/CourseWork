package com.course.client.service.context;

import javafx.stage.Stage;

public class UiContext
{
    private Stage stage;

    public UiContext(Stage stage)
    {
        this.stage = stage;
    }

    public Stage getStage()
    {
        return stage;
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    @Override
    public String toString()
    {
        return "UiContext{" +
                "stage=" + stage +
                '}';
    }
}
