package com.course.client.service.context;

import com.course.client.InventoryApplication;
import com.course.client.domain.User;
import com.course.client.service.config.Config;
import com.course.client.service.network.TcpRequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class ModelContext
{
    private TcpRequestHandler requestHandler;
    private InventoryApplication application;
    private User currentUser;

    public Logger getLogger()
    {
        return logger;
    }

    private Logger logger = LogManager.getLogger(ModelContext.class);

    public Config getConfig()
    {
        return config;
    }

    public void setConfig(Config config)
    {
        this.config = config;
    }

    private Config config = new Config();

    private UUID currentListId = null;
    private String currentObjectId = null;
    private UUID currentUserOnEditId = null;

    public ModelContext(InventoryApplication application)
    {
        this.application = application;
        requestHandler = new TcpRequestHandler(config);
    }

    public UUID getCurrentListId()
    {
        return currentListId;
    }

    public void setCurrentListId(UUID currentListId)
    {
        this.currentListId = currentListId;
    }

    public String getCurrentObjectId()
    {
        return currentObjectId;
    }

    public void setCurrentObjectId(String currentObjectId)
    {
        this.currentObjectId = currentObjectId;
    }

    public UUID getCurrentUserOnEditId()
    {
        return currentUserOnEditId;
    }

    public void setCurrentUserOnEditId(UUID currentUserOnEditId)
    {
        this.currentUserOnEditId = currentUserOnEditId;
    }

    public InventoryApplication getApplication()
    {
        return application;
    }

    public void setApplication(InventoryApplication application)
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
}
