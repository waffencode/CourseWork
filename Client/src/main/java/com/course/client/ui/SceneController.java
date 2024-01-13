package com.course.client.ui;

import com.course.client.service.context.ModelContext;
import com.course.client.service.context.UiContext;

public abstract class SceneController
{
    protected ModelContext modelContext;
    protected UiContext uiContext;

    public ModelContext getModelContext()
    {
        return modelContext;
    }

    public UiContext getUiContext()
    {
        return uiContext;
    }

    public void initController(ModelContext modelContext, UiContext uiContext)
    {
        this.modelContext = modelContext;
        this.uiContext = uiContext;
    }

    protected void goToSceneWithResource(String fxmlFile)
    {
        uiContext.getStage().setScene(new SceneProvider().getPreparedScene(fxmlFile, modelContext, uiContext));
    }
}
