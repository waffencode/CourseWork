package com.course.client.service;

import com.course.client.domain.User;

public class ModelContext
{
    private TcpRequestHandler requestHandler = new TcpRequestHandler();
    private User currentUser;

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
