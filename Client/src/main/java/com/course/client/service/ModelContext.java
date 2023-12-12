package com.course.client.service;

import com.course.client.InventoryApplication;
import com.course.client.domain.User;

public class ModelContext
{
    private TcpRequestHandler requestHandler = new TcpRequestHandler();

    public InventoryApplication getApplication()
    {
        return application;
    }

    public void setApplication(InventoryApplication application)
    {
        this.application = application;
    }

    private InventoryApplication application;
    private User currentUser;

    public ModelContext(InventoryApplication application)
    {
        this.application = application;
    }

    public TcpRequestHandler getRequestHandler()
    {
        return requestHandler;
    }

    public void setRequestHandler(TcpRequestHandler requestHandler)
    {
        this.requestHandler = requestHandler;
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }

    @Override
    public String toString()
    {
        return "ModelContext{" +
                "requestHandler=" + requestHandler +
                ", currentUser=" + currentUser +
                '}';
    }
}
