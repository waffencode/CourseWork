package com.course.client.ui;

import com.course.client.service.*;

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

    public void setContext(ModelContext modelContext, UiContext uiContext)
    {
        this.modelContext = modelContext;
        this.uiContext = uiContext;
    }
}
